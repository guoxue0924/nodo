package com.bluemobi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.appcore.util.AjaxUtil;
import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.system.SystemNavigation;

/**
 * 抽象的web控制器
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-26 下午5:14:41 
 *
 */
public abstract class AbstractBackController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBackController.class);
    
    /**
     * 获取userId
     * @author haojian
     * @date 2015-10-15 上午10:09:30 
     * @return
     * @return int
     */
    public int getUserid() {
        
        int userid = 0;
        
        AdminUser adminUser = getAdminUser();
        if(adminUser!=null){
            userid =  adminUser.getUserid();
        }
        
        return userid;
    }
    
    /**
     * 获取用户名
     * @author haojian
     * @date 2015-12-2 上午9:48:47 
     * @param request
     * @return
     * @return String
     */
    public String getUsername(){
        
        String username = "";
        
        AdminUser adminUser = getAdminUser();
        if(adminUser!=null){
            username = adminUser.getUsername();
        }
        
        return username;
    }
    
    /**
     * 获取管理员用户
     * @author haojian
     * @date 2015-12-2 上午9:48:53 
     * @param request
     * @return
     * @return AdminUser
     */
    public AdminUser getAdminUser(){
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AdminUser adminUser = (AdminUser) SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
        return adminUser;
        
    }

    /**
     * 初始化界面调用
     * 
     * @author HeWeiwen 2015-7-17
     * @param model
     * @param req
     */
    @SuppressWarnings("unchecked")
	public void initIndex(Model model) {
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        //1,检测是否是ajax请求，是否需要重新加载数据
        if (AjaxUtil.checkIsAjax(request)) {
            LOGGER.debug("ajax请求，不查询导航栏！");
            return;
        }
        //2,通过tokenID获得用户对象
        AdminUser adminUser = SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
        model.addAttribute("loggedInUser", adminUser);
        
        //3,通过用户信息获得所有用户权限菜单数据
        List<SystemNavigation> navigationList = SessionManager.getAttribute(request, AdminConstant.KEY_NAVIGATION_LIST);
        model.addAttribute("userNavsList", navigationList);
    }
    
}
