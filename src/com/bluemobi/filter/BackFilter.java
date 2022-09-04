package com.bluemobi.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appcore.security.BmSession;
import com.appcore.util.AjaxUtil;
import com.appcore.util.JsonUtil;
import com.appcore.util.SessionManager;
import com.bluemobi.conf.Config;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.constant.LogConstant;
import com.bluemobi.po.admin.AdminPermission;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.service.admin.AdminPermissionService;
import com.bluemobi.util.LogUtil;
import com.bluemobi.util.RegularUtil;

/**
 * 后台过滤器
 * 过滤后台管理系统的请求
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2016-1-16 下午2:27:25 
 *
 */
public class BackFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackFilter.class);
    
    @Autowired
    private AdminPermissionService adminPermissionService;
    /** 静态资源后缀列表 */
    private List<String> staticResourceSuffixList;
    /** 访客访问url列表，无需登录即可访问 */
    private List<String> visitUrlList;
    /** 用户开放url列表，登录用户即可访问，未登录不可访问(不需要特殊授权) */
    private List<String> userOpenUrlList;
    

    public void destroy() {
        LOGGER.info("销毁BackFilter...");
    }
    
    public void init(FilterConfig conf) throws ServletException {
        LOGGER.info("初始化BackFilter...");
    }

    /**
     * 过滤请求
     * @author haojian
     * @date 2016-1-16 下午2:26:56 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @return void
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        String servletPath = request.getServletPath(); 
        LOGGER.info("servletPath=【{}】，请求地址=【{}】，请求参数=【{}】", new Object[]{servletPath, request.getRequestURL(), JsonUtil.getJsonString(request.getParameterMap()) } );
        
        // 1、处理静态资源
        for (String staticResourceSuffix : staticResourceSuffixList) {
            if (servletPath.endsWith(staticResourceSuffix)) {
                chain.doFilter(req, resp);
                LOGGER.debug("静态资源...");
                return;
            }
        }
        
        //2、校验是否有session，如果没有就创建session
        boolean hasSession = SessionManager.hasSession(request);
        if(!hasSession){
            BmSession session = SessionManager.createSession(request, response);
            LOGGER.info("创建session...【{}】", new Object[]{ session });
        }
        
        //3、记录管理员用户行为操作日志
        LogUtil.logUserAction(request, LogConstant.LOG_TYPE_BACK);
        
        //4、处理访客访问url
        boolean matchResult = false;
        for (String visitUrl : visitUrlList) {
            matchResult = RegularUtil.match(visitUrl, servletPath);
            if (matchResult) {
                chain.doFilter(req, resp);
                return;
            }
        }
        
        //5、校验用户是否登录
        AdminUser adminUser = SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
        if (adminUser == null) {
            LOGGER.error("session中没有用户数据！请登录！");
            processRedirect(request, response, servletPath);
            return;
        }
        
        //6、 用户开放url列表
        boolean matchResult2 = false;
        for (String userOpenUrl : userOpenUrlList) {
            matchResult2 = RegularUtil.match(userOpenUrl, servletPath);
            if (matchResult2) {
                chain.doFilter(req, resp);
                return;
            }
        }
        
        //7、判断URI请求是否在权限数据中（不在数据中 直接放开过滤）
        if (adminPermissionService.isAddPermissionByURI(servletPath)) {
            LOGGER.info("【{}】请求不在权限数据中，放开对它的过滤", new Object[]{servletPath});
            chain.doFilter(req, resp);
            return;
        }
        
        //8、获得当前用户权限数据（绝对匹配数据）
        Map<String, AdminPermission> permissionMap = SessionManager.getAttribute(request, AdminConstant.KEY_PERMISSION_MAP);
        if (permissionMap.containsKey(servletPath)) {
        	request.setAttribute("currentNavId", permissionMap.get(servletPath).getNavigationId());
            chain.doFilter(req, resp);
            return;
        } else {
            LOGGER.error("请求不在权限数据中，请授权访问权限！");
            notPermissionRedirect(request, response, servletPath);
            return;
        }
        
    }

	private void processRedirect(HttpServletRequest request, HttpServletResponse response,String servletPath) throws IOException {

		if (AjaxUtil.checkIsAjax(request)) {
			//session过期状态
			response.setHeader("sessionstatus", "timeout");
		    //根据uri判断是否为菜单url,依据为带有2个/. 如果是,则在header中增加redirectUri变量
		    //以便前端js获取使用(具体使用参见common.js的processSessionTimeout方法)
		    if(servletPath.split("/").length == 2) {
		    	response.setHeader("redirectUri", servletPath);
		    }
		} else {
			response.sendRedirect(Config.BASE_URL + Config.SEND_REDIRECT + servletPath);
		}
	}
	/**
	 * 无权限时重定向操作
	 * @author HeWW
	 * 2016-3-11
	 * @param request
	 * @param response
	 * @param servletPath
	 * @throws IOException
	 */
	private void notPermissionRedirect(HttpServletRequest request, HttpServletResponse response,String servletPath) throws IOException {

        if (AjaxUtil.checkIsAjax(request)) {
            //session过期状态
            response.setHeader("sessionstatus", "notPermission");
            //根据uri判断是否为菜单url,依据为带有2个/. 如果是,则在header中增加redirectUri变量
            //以便前端js获取使用(具体使用参见common.js的processSessionTimeout方法)
            if(servletPath.split("/").length == 2) {
                response.setHeader("redirectUri", servletPath);
            }
        } else {
            response.sendRedirect(Config.BASE_URL + Config.SEND_REDIRECT + servletPath);
        }
    }
	
    public List<String> getStaticResourceSuffixList() {
        return staticResourceSuffixList;
    }

    public void setStaticResourceSuffixList(
            List<String> staticResourceSuffixList) {
        this.staticResourceSuffixList = staticResourceSuffixList;
    }
    
    public List<String> getVisitUrlList() {
        return visitUrlList;
    }

    public void setVisitUrlList(List<String> visitUrlList) {
        this.visitUrlList = visitUrlList;
    }

    public List<String> getUserOpenUrlList() {
        return userOpenUrlList;
    }

    public void setUserOpenUrlList(List<String> userOpenUrlList) {
        this.userOpenUrlList = userOpenUrlList;
    }

}
