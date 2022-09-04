package com.bluemobi.controller.api.cas;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.util.JsonUtil;
import com.appcore.util.RandomUtil;
import com.appcore.util.SessionManager;
import com.appcore.util.StringUtil;
import com.bluemobi.apito.UserTO;
import com.bluemobi.apito.cas.ChangePasswordRequestTO;
import com.bluemobi.apito.cas.ChangePasswordResponseTO;
import com.bluemobi.apito.cas.GetAuthCodeRequestTO;
import com.bluemobi.apito.cas.GetAuthCodeResponseTO;
import com.bluemobi.apito.cas.GetCasUserRequestTO;
import com.bluemobi.apito.cas.GetCasUserResponseTO;
import com.bluemobi.apito.cas.LoginRequestTO;
import com.bluemobi.apito.cas.LoginResponseTO;
import com.bluemobi.apito.cas.RegisterRequestTO;
import com.bluemobi.apito.cas.RegisterResponseTO;
import com.bluemobi.apito.cas.UpdateAvatarRequestTO;
import com.bluemobi.apito.cas.UpdateAvatarResponseTO;
import com.bluemobi.apito.cas.UpdateCasUserRequestTO;
import com.bluemobi.apito.cas.UpdateCasUserResponseTO;
import com.bluemobi.constant.CasConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasCode;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.protocol.api.CasUserProtocol;
import com.bluemobi.service.cas.CasCodeService;
import com.bluemobi.service.cas.CasUserDetailService;
import com.bluemobi.service.cas.CasUserService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.cas.TempUser;
import com.bluemobi.to.cas.UpdatePwdTO;
import com.bluemobi.util.MessageSender;

/**
 * 【用户】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-26 10:29:30
 * 
 */
@Controller(value = "casUserAPIController")
@RequestMapping("api/casUser")
public class CasUserAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserAPIController.class);

    @Autowired
    private CasUserService casUserService;
    @Autowired
    private CasUserDetailService casUserDetailService;
    @Autowired
    private CasCodeService casCodeService;

    /** 定义公共名字 */
    private static final String USERNAME = "username";
    /** 验证码过期时间（毫秒） */
    private static final long EXP_TIME = 120000L;

    /** 用户手机验证码临时缓存 */
    private static final ConcurrentMap<String, TempUser> TEMP_USER_MAP = new ConcurrentHashMap<String, TempUser>();

    /**
     * 登录
     * 
     * @param request
     * @param loginRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-01-26 10:29:30
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public ResultTO login(HttpServletRequest request, String json) {
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), json.toString() });

        LoginRequestTO loginRequestTO = JsonUtil.getObject(json, LoginRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), loginRequestTO.toString() });

        try {
            ResultTO resultTO = null;
            // 1,校验验证码的时效性
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put(USERNAME, loginRequestTO.getUsername());
            // 2,根据用户名查询用户对象
            List<CasUser> casUserList = casUserService.selectObjectList(userMap);
            // 3,无用户时手机号码验证码注册登录
            if (casUserList.isEmpty()) {
                // 查询无用户（手机号码+验证码），注册登录
                LOGGER.info("用户名【{}】不存在！", new Object[] { loginRequestTO.getUsername() });
                return ResultTO.newFailResultTO("用户名不存在！", null);
            }
            CasUser casUser = casUserList.get(0);
            // 4,用户名+密码登录
            String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(loginRequestTO.getPassword()) + casUser.getSalt());
            if (!passwordMd5Md5.equals(casUser.getPassword())) {
                // 密码不正确
                LOGGER.info("用户【{}】登录的时候，密码【{}】不正确！", new Object[] { loginRequestTO.getUsername(), loginRequestTO.getPassword() });
                return ResultTO.newFailResultTO("密码不正确！", null);
            } else {
                // 5,登录成功，返回tokenId
                resultTO = loginContinue(request, casUser);
            }
            return resultTO;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("登录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), loginRequestTO.toString() });
            return ResultTO.newFailResultTO("登录失败！", null);
        }

    }

    /**
     * 注册
     * 
     * @param request
     * @param registerRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-01-26 10:29:30
     */
    @RequestMapping(value = "register")
    @ResponseBody
    public ResultTO register(HttpServletRequest request, String json) {

        RegisterRequestTO registerRequestTO = JsonUtil.getObject(json, RegisterRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), registerRequestTO.toString() });

        try {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            CasUser casUser = new CasUser();
            // 1,用户名注册
            if ("".equals(registerRequestTO.getPassword())) {
                // 1.1,校验密码的一致性
                return ResultTO.newFailResultTO("用户名注册密码为空", null);
            }
            casUser.setUsername(registerRequestTO.getUsername());
            // 2,校验用户是否已存在
            reqMap.put(USERNAME, registerRequestTO.getUsername());
            List<Map<String, Object>> reqUser = casUserService.selectMapList(reqMap);
            if (!reqUser.isEmpty()) {
                return ResultTO.newFailResultTO("该用户已经存在！请登陆！", null);
            }
            // 3，用户信息录入
            CasUser resultUser = casUserService.regiest(registerRequestTO);
            if (resultUser.getUserid() > 0) {
                RegisterResponseTO registerResponseTO = new RegisterResponseTO();
                return ResultTO.newSuccessResultTO("注册成功！", registerResponseTO);
            } else {
                return ResultTO.newFailResultTO("注册失败！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("注册出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), registerRequestTO.toString() });
            return ResultTO.newFailResultTO("注册失败！", null);
        }

    }

    /**
     * 修改密码
     * 
     * @param request
     * @param changePasswordRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-01-26 10:29:30
     */
    @RequestMapping(value = "changePassword")
    @ResponseBody
    public ResultTO changePassword(HttpServletRequest request, String json) {
        
        ChangePasswordRequestTO changePasswordRequestTO = JsonUtil.getObject(json, ChangePasswordRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), changePasswordRequestTO.toString() });

        try {
         // 处理业务
            // 1,校验验证码的时效性
            /*ResultTO resultTO = null;
            if (null != changePasswordRequestTO.getAuthCode() && !"".equals(changePasswordRequestTO.getAuthCode())) {
                // 校验验证码
                resultTO = checkCode(changePasswordRequestTO.getUsername(), changePasswordRequestTO.getAuthCode());
                if (BaseConstant.STATUS_FAILURE == resultTO.getStatus()) {
                    return resultTO;
                }
            }*/
            
            Map<String,Object> reqMap = new HashMap<String, Object>();
            reqMap.put("username", changePasswordRequestTO.getUsername());
            List<CasUser> userList = casUserService.selectObjectList(reqMap);
            CasUser user = null ;
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
            if (user == null) {
                return ResultTO.newFailResultTO("用户名不存在", null);
            } 
            String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(changePasswordRequestTO.getPassword()) + user.getSalt());
            user.setPassword(passwordMd5Md5);
            user.setMtime(Calendar.getInstance().getTime());
            casUserService.update(user);
            
            ChangePasswordResponseTO changePasswordResponseTO = new ChangePasswordResponseTO();
            return ResultTO.newSuccessResultTO("修改密码成功！", changePasswordResponseTO);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改密码出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), changePasswordRequestTO.toString() });
            return ResultTO.newFailResultTO("修改密码失败！", null);
        }

    }

    /**
     * 获取验证码
     * 
     * @param request
     * @param getAuthCodeRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-01-26 10:29:30
     */
    @RequestMapping(value = "getAuthCode")
    @ResponseBody
    public ResultTO getAuthCode(HttpServletRequest request, String json) {

        GetAuthCodeRequestTO getAuthCodeRequestTO = JsonUtil.getObject(json, GetAuthCodeRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getAuthCodeRequestTO.toString() });

        try {
            
            // 1,生产验证码
            String smsCode = RandomUtil.getRandomInt(100000, 999999) + "";// 6位随机数字
            // 2,发送短信
            MessageSender.sendAuthCode(getAuthCodeRequestTO.getPhone().toString(), smsCode);

            // 3,存储到Map临时缓存对象
            putMapUserTemp(getAuthCodeRequestTO.getPhone(), smsCode);
            // 4,持久化验证码数据
            putDateUserTemp(getAuthCodeRequestTO.getPhone(), smsCode, getAuthCodeRequestTO.getAuthCodeType());
            LOGGER.info("-----sendcode----验证码是code:【{}】", smsCode);

            GetAuthCodeResponseTO getAuthCodeResponseTO = new GetAuthCodeResponseTO();
            return ResultTO.newSuccessResultTO("获取验证码成功！", getAuthCodeResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取验证码出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getAuthCodeRequestTO.toString() });
            return ResultTO.newFailResultTO("获取验证码失败！", null);
        }

    }

    @RequestMapping(value = "updateCasUser")
    @ResponseBody
    public ResultTO updateCasUser(HttpServletRequest request, String json) {
        
        UpdateCasUserRequestTO updateCasUserRequestTO =JsonUtil.getObject(json, UpdateCasUserRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), updateCasUserRequestTO.toString() });
        try {
            // 1,根据用户ID查询用户所有信息
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            CasUser user = casUserService.selectObject(reqMap);
            user.setMtime(Calendar.getInstance().getTime());
            user.setAvatar(updateCasUserRequestTO.getUser().getAvatar());
            user.setNickname(updateCasUserRequestTO.getUser().getNickname());
            user.setGender(updateCasUserRequestTO.getUser().getGender().byteValue());

            int ret = casUserService.update(user);
            if (ret == 0) {
                return ResultTO.newFailResultTO("修改用户信息成功！", null);
            }
            UpdateCasUserResponseTO updateCasUserResponseTO = new UpdateCasUserResponseTO();
            return ResultTO.newSuccessResultTO("修改用户信息成功！", updateCasUserResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取验证码出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), updateCasUserRequestTO.toString() });
            return ResultTO.newFailResultTO("修改用户信息成功！", null);
        }

    }

    /**
     * 修改用户头像
     * 
     * @auther zhangzheng
     * @date 2016-2-29 下午2:50:39
     * @param request
     * @param updateAvatarRequestTO
     * @param files
     * @return
     */
    @RequestMapping(value = "updateAvatar")
    @ResponseBody
    public ResultTO updateAvatar(HttpServletRequest request, String json, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        
        UpdateAvatarRequestTO updateAvatarRequestTO = JsonUtil.getObject(json, UpdateAvatarRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), updateAvatarRequestTO.toString() });
        Map<String, Object> uploadResultMap = null;
        CasUser casUser = null;
        try {
            // 上传图片
            uploadResultMap = uploadImage(files, CasConstant.CAS_FRONT_USER_AVATAR_IMAGE);
            // 保存数据库
            if ((Boolean) uploadResultMap.get("flag")) {
                uploadResultMap.put("userid", this.getUserid());
                uploadResultMap.put("filesize", files[0].getSize()); // 单文件上传，获取数组第0个大小即可
                uploadResultMap.put("fileType", Byte.valueOf("1"));
                casUser = casUserService.updateAvatarAPI(uploadResultMap, getUserid());
            }
            // 返回值
            UpdateAvatarResponseTO updateAvatarResponseTO = new UpdateAvatarResponseTO();
            updateAvatarResponseTO.getUser().setAvatar(uploadResultMap.get("imageUrl") + "");
            updateAvatarResponseTO.getUser().setGender(casUser.getGender().intValue());
            updateAvatarResponseTO.getUser().setNickname(casUser.getNickname());
            updateAvatarResponseTO.getUser().setUserid(this.getUserid());
            return ResultTO.newSuccessResultTO("修改用户头像成功！", updateAvatarResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改用户头像出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), updateAvatarRequestTO.toString() });
            return ResultTO.newFailResultTO("修改用户头像失败！", null);
        }
    }
    
    /**
     * 获得用户信息
     * @author HeWW
     * 2016-3-8
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "getCasUser")
    @ResponseBody
    public ResultTO getCasUser(HttpServletRequest request, String json) {
        
        GetCasUserRequestTO getCasUserRequestTO = JsonUtil.getObject(json, GetCasUserRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] {  request.getRemoteHost(), getCasUserRequestTO.toString() });

        try {
            // 1,根据用户ID查询用户所有信息
            Map<String,Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            CasUser user = casUserService.selectObject(reqMap);
            //2，组合数据
            GetCasUserResponseTO getCasUserResponseTO = new GetCasUserResponseTO();
            UserTO userTO = new UserTO();
            userTO.setAvatar(user.getAvatar());
            userTO.setGender(user.getGender().intValue());
            userTO.setNickname(user.getNickname());
            userTO.setUserid(user.getUserid());
            getCasUserResponseTO.setUser(userTO);
            return ResultTO.newSuccessResultTO("获得用户信息成功！", getCasUserResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获得用户信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getCasUserRequestTO.toString() });
            return ResultTO.newFailResultTO("获得用户信息成功！", null);
        }

    }
    
    /**
     * 注销登陆
     * 
     * @author HeWW 2016-3-1
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO logout(HttpServletRequest request) {
        SessionManager.removeAttribute(request, CasConstant.KEY_CAS_USER);

        LOGGER.info("登出成功");

        return ResultTO.newSuccessResultTO("用户成功注销！", null);
    }

    /**
     * 登陆完成后续相关操作
     * 
     * @author HeWW 2016-2-24
     * @param casUser
     * @return
     */
    private ResultTO loginContinue(HttpServletRequest request, CasUser casUser) {
        // 1,登录成功
        LoginResponseTO loginResponse = CasUserProtocol.newLoginResponse(casUser);
        LOGGER.info("用户【{}】登录成功！", new Object[] { casUser.getUsername() });

        // 2、登录成功后操作
        // 2.1、把user对象，保存到session中
        // 用户登录成功后，需要通过sessionId设置用户属性到自定义session中
        SessionManager.setAttribute(request, CasConstant.KEY_CAS_USER, casUser);

        return ResultTO.newSuccessResultTO("登录成功", loginResponse);
    }

    /**
     * 存储到Map临时缓存对象
     * 
     * @author HeWW 2016-2-29
     * @param sendto
     * @param smsCode
     */
    private void putMapUserTemp(String phone, String smsCode) {
        TempUser tempUser = new TempUser();
        // 获得当前的毫秒数
        long cTime = System.currentTimeMillis();
        long pTime = cTime + EXP_TIME;// 加上过期时间120毫秒数
        tempUser.setSmsCode(smsCode);
        tempUser.setPhone(phone);
        tempUser.setCtime(cTime);
        tempUser.setPtime(pTime);
        // 将验证码对象存入临时缓存中
        TEMP_USER_MAP.put(phone + "_" + smsCode, tempUser);
    }

    /**
     * 验证码数据持久化
     * 
     * @author HeWW 2016-3-1
     * @param phone
     * @param smsCode
     * @param AuthCodeType
     *            （1，login：登录、2，register：注册、3，forgotpassword'：忘记密码）
     */
    private void putDateUserTemp(String phone, String smsCode, int AuthCodeType) {
        String action = "";
        // 匹配类型
        if (AuthCodeType == 1) {
            action = "login";
        }
        if (AuthCodeType == 2) {
            action = "register";
        }
        if (AuthCodeType == 3) {
            action = "forgotpassword";
        }
        // 1，获得当前的毫秒数
        long cTime = System.currentTimeMillis();
        long pTime = cTime + EXP_TIME;// 加上过期时间120毫秒数
        CasCode code = new CasCode();
        code.setUserid(new Long(0));
        code.setType("phone");
        code.setAction(action);
        code.setSendTo(phone);
        code.setCode(smsCode);
        code.setCtime(new Date());
        code.setExptime(new Date(pTime));
        // 将验证码对象插入数据库
        casCodeService.insert(code);
    }

}
