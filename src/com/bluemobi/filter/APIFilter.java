package com.bluemobi.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

import com.appcore.security.BmSession;
import com.appcore.util.CookieUtil;
import com.appcore.util.JsonUtil;
import com.appcore.util.SessionManager;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.constant.CasConstant;
import com.bluemobi.constant.LogConstant;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.LogUtil;
import com.bluemobi.util.RegularUtil;

/**
 * 前端过滤器
 * 过滤手机端请求
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-12-28 下午1:45:17 
 *
 */
public class APIFilter implements Filter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(APIFilter.class);
    
    /** 静态资源后缀列表 */
    private List<String> staticResourceSuffixList;
    /** 访客访问url列表，无需登录即可访问 */
    private List<String> visitUrlList;
    /** 用户开放url列表，登录用户即可访问，未登录不可访问(不需要特殊授权) */
    private List<String> userOpenUrlList;
    

    public void destroy() {
        LOGGER.info("销毁FrontFilter...");
    }
    
    public void init(FilterConfig conf) throws ServletException {
        LOGGER.info("初始化FrontFilter...");
    }

    /**
     * 过滤前端需登录才能访问的url
     * 若未登录，或登录过期，就跳转到登录页面
     * @author haojian
     * @date 2016-1-12 下午3:43:54 
     * @param request
     * @param response
     * @param chain
     * @throws IOException ServletException
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
        
        //调试用，测试手机端是否能传递cookie过来
        String bmSessionId = CookieUtil.getCookieValue(request, SessionManager.BM_SESSION_ID);
        LOGGER.debug("手机端cookie中的bmSessionId=【{}】", new Object[]{bmSessionId} );
        
        //2、校验是否有session，如果没有就创建session
        boolean hasSession = SessionManager.hasSession(request);
        if(!hasSession){
            BmSession session = SessionManager.createSession(request, response);
            LOGGER.info("创建session...【{}】", new Object[]{ session });
        }
        
        //3、记录用户行为操作日志
        LogUtil.logUserAction(request, LogConstant.LOG_TYPE_API);
        
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
        CasUser casUser = SessionManager.getAttribute(request, CasConstant.KEY_CAS_USER);
        if (casUser == null) {
            LOGGER.error("session中没有用户数据！请登录！");
            processRedirect(request, response, servletPath);
            return;
        }
        
        chain.doFilter(req, resp);
        return;
    }
    
    /**
     * session过期后，返回resultTO的json对象到客户端（手机端或网页端）
     * 客户端应解析此消息，并跳转到登录界面
     * @author haojian
     * @date 2016-1-12 下午3:43:54 
     * @param request
     * @param response
     * @param servletPath
     * @throws IOException
     * @return void
     */
    private void processRedirect(HttpServletRequest request, HttpServletResponse response, String servletPath) throws IOException {
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", servletPath);
        ResultTO resultTO = ResultTO.newSessionInvalidResultTO("session已过期，请重新登录！", map);
        String jsonResult = JsonUtil.getJsonString(resultTO);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(jsonResult);
        out.flush();
        out.close();
        
    }

    public List<String> getStaticResourceSuffixList() {
        return staticResourceSuffixList;
    }

    public void setStaticResourceSuffixList(List<String> staticResourceSuffixList) {
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
