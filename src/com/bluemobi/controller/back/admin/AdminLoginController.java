package com.bluemobi.controller.back.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.util.AuthCodeUtil;
import com.appcore.util.SessionManager;
import com.appcore.util.StringUtil;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.admin.AdminPermission;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.system.SystemNavigation;
import com.bluemobi.service.admin.AdminPermissionService;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.system.SystemNavigationService;
import com.bluemobi.to.ResultTO;

/**
 * 登陆
 * 
 * @author haojian
 * @date 2015-6-9 上午10:52:37
 * 
 */
@Controller
@RequestMapping("back/adminLogin")
public class AdminLoginController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private SystemNavigationService systemNavigationService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String urlContinue) {

        model.addAttribute("urlContinue", urlContinue);

        return "back/admin/login";
    }

    /**
     * 获得验证码
     * 
     * @author haojian
     * @date 2015-9-25 下午2:25:49
     * @param request
     * @param response
     * @throws IOException
     * @return void
     */
    @RequestMapping("code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 创建验证码，并将验证图片输出到客户端，将验证码md5后保存到客户端cookie
        AuthCodeUtil.createAuthCodeAndOutput(200, 70, 4, request, response);

    }

    /**
     * 用户登录
     * 
     * @author haojian
     * @date 2015-9-25 下午2:27:40
     * @param model
     * @param req
     * @param resp
     * @param captcha
     * @param username
     * @param password
     * @param urlContinue
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @return ResultTO
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO login(Model model, HttpServletRequest request, HttpServletResponse response, 
            String captcha, String username, String password, String urlContinue) 
                    throws IOException, ServletException, IllegalAccessException, InvocationTargetException {

        //1、校验验证码是否正确
        boolean isValid = AuthCodeUtil.checkAuthCode(captcha, request, response);
        if (!isValid) {
            LOGGER.info("用户验证码输入错误");
            return ResultTO.newFailResultTO("验证码错误", null);
        }
        
        //2、校验用户名密码长度
        if (username.length()<3 || password.length()<6) {
            return ResultTO.newFailResultTO("请输入正确的用户名和密码", null);
        }
        
        //3、校验用户名密码
        //查询用户信息
        AdminUser user = null;
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("username", username);
        List<AdminUser> userList = adminUserService.selectObjectList(reqMap);
        if (!userList.isEmpty()) {
            user = userList.get(0);
        }
        if (user == null ) {
            return ResultTO.newFailResultTO("用户名不存在", null);
        } else if (user.getStatus() < 1) {
            return ResultTO.newFailResultTO("用户已被禁止", null);
        } else if (!"local".equals(user.getDomain())) {
            return ResultTO.newFailResultTO("非本地用户，暂不提供认证登录", null);
        }
        //校验密码
//        String pwd = StringUtil.md5(password) + user.getSalt();
//        if (!StringUtil.md5(pwd).equals(user.getPassword())) {
//            return ResultTO.newFailResultTO("密码错误", null);
//        }
        
        String pwd = StringUtil.md5(password);
        if (!pwd.equals(user.getPassword())) {
            return ResultTO.newFailResultTO("密码错误", null);
        }
        
        //4、登录成功后操作
        //4.3、把user对象，保存到session中
        SessionManager.setAttribute(request, AdminConstant.KEY_ADMIN_USER, user);
        //4.4、获取用户的权限map，保存到用户session中
        //权限map，key：url
        Map<String, AdminPermission> permissionMap = new HashMap<String, AdminPermission>();
        List<AdminPermission> permissionsList = adminPermissionService.selectAdminPermissionListByUserid(user.getUserid());
        for(AdminPermission p : permissionsList){
            permissionMap.put(p.getUrl(), p);
        }
        SessionManager.setAttribute(request, AdminConstant.KEY_PERMISSION_MAP, permissionMap);
        //4.5、获取用户的导航信息，保存到用户session中
        List<SystemNavigation> navigationList = systemNavigationService.selectSystemNavigationByUserid(user.getUserid());
        SessionManager.setAttribute(request, AdminConstant.KEY_NAVIGATION_LIST, navigationList);
        
        //4.6,修改用户登录状态
        user.setIsOnline((byte)1);
        //4.7，持久化用户数据
        adminUserService.update(user);
        
        //5、设置跳转uri
        String data = null;
        if (urlContinue == null || "".equals(urlContinue) || "/".equals(urlContinue) || "/back/adminLogin/logout".equals(urlContinue)) {
            data = "/back";
        } else {
            data = urlContinue;
        }
        //String data = "/back";
        LOGGER.info("登录成功返回信息：【{}】", new Object[]{data});
        return ResultTO.newSuccessResultTO("登录成功", data);
    }


    /**
     * 退出登录
     * 如果客户端cookie里面有token，需要清除token
     * @author haojian
     * @date 2016-1-16 上午10:15:47 
     * @param model
     * @param request
     * @param response
     * @return
     * @return String
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
        SessionManager.removeAttribute(request, AdminConstant.KEY_ADMIN_USER);
        //CookieUtil.removeCookie("这里是tokenId", request, response);
        LOGGER.info("登出成功");

        return "back/admin/login";
    }

}