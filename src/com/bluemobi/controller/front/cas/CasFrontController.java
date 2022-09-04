package com.bluemobi.controller.front.cas;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.appcore.util.AuthCodeUtil;
import com.appcore.util.SessionManager;
import com.appcore.util.SftpUtil;
import com.appcore.util.StringUtil;
import com.appcore.util.UUIDService;
import com.bluemobi.conf.Config;
import com.bluemobi.conf.ImageServerConfig;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.constant.CasConstant;
import com.bluemobi.constant.OrderConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.cas.CasUserDetail;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.bts.BtsCartService;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.service.cas.CasUserDetailService;
import com.bluemobi.service.cas.CasUserFavoriteService;
import com.bluemobi.service.cas.CasUserService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.OrderDetailTO;
import com.bluemobi.to.cas.SetNewPasswordTO;
import com.bluemobi.to.image.ImageCutTO;
import com.bluemobi.util.EnumUtil;
import com.bluemobi.util.ImageUtils;
import com.bluemobi.util.alipay.util.SendmailUtil;
import com.bluemobi.util.message.shortMessage;

/**
 * Web端会员中心 
 * @author heweiwen
 * 2016-1-7 上午11:12:11
 */
@Controller
@RequestMapping("front/cas")
public class CasFrontController extends AbstractAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CasFrontController.class);
    @Autowired
    private CasUserService casUserService;
    @Autowired
    private CasUserDetailService casUserDetailService;
    @Autowired
    private TrendRegionService trendRegionService;
    @Autowired
    private BtsOrderService btsOrderService;
    @Autowired
    private BtsCartService btsCartService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private CasUserFavoriteService casUserFavoriteService;

    /** 定义公共名字 */
    private static final String USERNAME = "username";
    /** 会员id的属性名 */
    private static final String USERID_FIELD = "userid";
    
    /** 设置对应的模板变量值 */
    String tpl_value="#code#="+randomNum(); 
    
    /** 发送给客户信息 */
//    private static String messageText ="尊敬的用户您于"+System.nanoTime()+"申请可美会员，您的手机验证码是："+randomNum();
    private static String messageText ="【金交在线】尊敬的用户您正在申请可美会员，您的手机验证码是："+randomNum();

    /**
     * 初始化用户中心页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String userIndex(Model model) {
        //获得订单信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getUserid());
        Page<OrderDetailTO> pages = btsOrderService.page("frontPage","frontPageCount",map,1, 3);
        //订单状态数据处理
        int WAIT_PAY = 0;//(2, "待付款"),
        int WAIT_DELIVERY = 0;//(4, "待发货"),
        List<OrderDetailTO> orderList = pages.getData();
        for (OrderDetailTO order:orderList) {
            if (order.getStatus() == 2) {
                WAIT_PAY ++;
            }
            if (order.getStatus() == 4) {
                WAIT_DELIVERY ++;
            }
        }
        //获得购物车信息
        List<Map<String, Object>> list = btsCartService.selectMapList(map);
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        for (Map<String, Object> data:list) {
            int skuId = Integer.parseInt(String.valueOf(data.get("skuId")));
            Map<String, Object> cartMap = new HashMap<String, Object>();
            cartMap.putAll(data);
            GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId);
            if(goodsSku!=null){
            	 cartMap.put("contentId", goodsSku.getContentId());
                 cartMap.put("price", goodsSku.getPrice());
                 cartMap.put("name",goodsSku.getName());
                 cartMap.put("DefaultImage", goodsSku.getImages().get(0));
                 resultList.add(cartMap);
            }
        }
        
        //获得商品收藏
        List<Map<String, Object>> favoriteList = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>>  favoriteMaps= casUserFavoriteService.selectMapList(map);
        for (Map<String, Object> data:favoriteMaps) {
            Map<String, Object> favoriteMap = new HashMap<String, Object>();
            favoriteMap.putAll(data);
            GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(Long.valueOf(data.get("specificationid").toString()));
            if (goodsSku != null) {
                favoriteMap.put("serialize", goodsSku);
                favoriteMap.put("DefaultImage", goodsSku.getImages().get(0));
                favoriteList.add(favoriteMap);
            }
        }
        model.addAttribute("favorites",favoriteList);
        model.addAttribute("carts",resultList);
        model.addAttribute("orders", orderList);
        CasUser casUser = getCasUser();
        CasUser newCasUser = casUserService.selectObject(casUser);
        model.addAttribute("loggedInUser", newCasUser);
        model.addAttribute("orderStatus", EnumUtil.parseEnum(OrderConstant.OrderStatus.class));
        model.addAttribute("WAIT_PAY", WAIT_PAY);
        model.addAttribute("WAIT_DELIVERY", WAIT_DELIVERY);
        return "front/cas/user.index";
    }
    
    /**
     * 初始化登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String signIn(Model model) {
        return "front/cas/login";
    }
    
    /**
     * 初始化服务协议页面
     * @param model
     * @return
     */
    @RequestMapping(value = "agreement", method = RequestMethod.GET)
    public String agreement(Model model) {
        return "front/cas/agreement";
    }
    
    /**
     * 初始化注册页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        return "front/cas/register";
    }
    
    /**
     * 初始化忘记密码页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "forgotPassword", method = RequestMethod.GET)
    public String forgotpasswordIndex(Model model) {
        return "front/cas/forgotPassword";
    }
    
    /**
     * 初始化忘记密码页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "forgotPasswordUpdate", method = RequestMethod.POST)
    public String forgotpasswordUpdate(Model model,HttpServletRequest request, HttpServletResponse response
       	 ) throws IOException  {
    	LOGGER.info("-----forgotpasswordUpdate-----");
    	 // 获取用户输入的手机验证码
         String messageText = request.getParameter("messageText");
         String phone = request.getParameter("phone");
    	 String messageText1=tpl_value.substring(tpl_value.length()-6,tpl_value.length());
         if (messageText1.equals(messageText)) {
             // 1.1,校验密码的一致性
        	 model.addAttribute("phone", phone);
             return "front/cas/forgotPasswordUpdate";
         }
         return "front/cas/forgotPassword";
    }

    /**
     * 初始化修改用户资料页面
     * @author HeWW
     * 2016-1-13
     * @param model
     * @return
     */
    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String accountIndex(Model model) {
        //获得用户详情
        Map<String, Object> reqMap = new HashMap<String, Object>();
        if (getCasUser() != null) {
            reqMap.put(USERID_FIELD, getCasUser().getUserid());
            CasUserDetail casUserDetail = casUserDetailService.selectObject(reqMap);
            model.addAttribute("userDetail", casUserDetail);
        }
        //初始化省市数据
        Map<String,Object> regionMap = new HashMap<String, Object>();
        regionMap.put("pid", 3743);//中国id为：2743
        List<TrendRegion> region = trendRegionService.selectObjectList(regionMap);
        
        model.addAttribute("loggedInUser", casUserService.selectObject(reqMap));
        model.addAttribute("region",region);
        return "front/cas/account.index";
    }
    
    /**
     * 初始化修改头像页面
     * @author HeWW
     * 2016-1-13
     * @param model
     * @return
     */
    @RequestMapping(value = "account/avatar", method = RequestMethod.GET)
    public String accountAvatarIndex(Model model) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put(USERID_FIELD, getCasUser().getUserid());
        model.addAttribute("loggedInUser", casUserService.selectObject(reqMap));
        return "front/cas/account.avatar";
    }
    
    /**
     * 初始化编辑图片页面
     * 
     * @author HeWW 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "account/uploadAvatar", method = RequestMethod.POST)
    public String accountUploadAvatar(@RequestParam(value = "pic", required = false) MultipartFile[] pic, Model model) {
        try {
            Map<String, Object> uploadResultMap = null;
            if (pic != null && pic.length > 0) {
                uploadResultMap = uploadImage(pic, BaseConstant.TEMP_IMAGE, true);
                if ((Boolean) uploadResultMap.get("flag")) {
                    model.addAttribute("tempImage", uploadResultMap.get("imageUrl"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        model.addAttribute("loggedInUser", getCasUser());
        return "front/cas/account.avatarupload";
    }
    
    
    /**
     * 初始化平台客户页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    public String feedback(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        
        return "front/cas/feedback.index";
    }
    
    
    /**
     * 初始化账户安全页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "security", method = RequestMethod.GET)
    public String security(Model model) {
    	
       
        CasUser casUser = getCasUser();
        CasUser newCasUser = casUserService.selectObject(casUser);
        model.addAttribute("loggedInUser", newCasUser);
        return "front/cas/security.index";
    }
    
    /**
     * 初始化账户安全页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "security/edit", method = RequestMethod.GET)
    public String securityEdit(String type,Model model) {
//    	if("password".equals(type)){
//    		CasUser casUser = getCasUser();
//            CasUser newCasUser = casUserService.selectObject(casUser);
//            model.addAttribute("loggedInUser", newCasUser);
//            return "front/cas/security.modifyPassword";
//    	}
        model.addAttribute("type", type);
        CasUser casUser = getCasUser();
        CasUser newCasUser = casUserService.selectObject(casUser);
        model.addAttribute("loggedInUser", newCasUser);
        return "front/cas/security.edit";
    }
    
    /**
     * 验证验证码
     * @author guoxue
     * 2016-7-15
     * @param model
     * @return
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO verifyCode(Model model,HttpServletRequest request, HttpServletResponse response
       	 ) throws IOException  {
    	LOGGER.info("-----vcode-----");
    	 // 获取用户输入的手机验证码
    	 String data = "fail";
         String vcode = request.getParameter("vcode");
    	 String messageText1=tpl_value.substring(tpl_value.length()-6,tpl_value.length());
         if (messageText1.equals(vcode)) {
        	 data = "success";
        	 return ResultTO.newSuccessResultTO(data);
         }
         return ResultTO.newFailResultTO(data);
    }
    
    /**
     * 修改密码
     * @author guoxue
     * 2016-7-15
     * @param model
     * @return
     */
    @RequestMapping(value = "setNewPassword", method = RequestMethod.POST)
    public String setNewPassword(Model model,SetNewPasswordTO setNewPasswordTO
       	 ) throws IOException  {
    	LOGGER.info("-----setNewPassword-----");
    	 // 获取用户输入的手机验证码
         String newPassword = setNewPasswordTO.getNewPassword();
         if("".equals(newPassword) || newPassword==null){
        	 model.addAttribute("type", "password");
             model.addAttribute("loggedInUser", getCasUser());
        	 return "front/cas/security.edit";
         }else{
        	//设置新密码
        	 CasUser casUser = getCasUser();
        	 casUser.setPassword(StringUtil.md5(newPassword));
        	 casUserService.update(casUser);
        	 
        	 return "front/cas/login";
         }
        
    }
    
    /**
     *绑定邮箱发送邮件
     * @author guoxue
     * 2016-7-13
     * @param model
     * @return
     */
    @RequestMapping(value = "security/bindEmile", method = RequestMethod.POST)
    public String securityBindEmile(String email,Model model) {
    	String rad = (randomNum()*100)+"";
    	String radMd5 = StringUtil.md5(StringUtil.md5(rad));
    	SendmailUtil se = new SendmailUtil();
    	CasUser casUser = getCasUser();
    	String emailTitle = "金交在线邮箱绑定";
    	String emailContext = "请点击下面地址绑定邮箱："+ Config.BASE_URL+"/front/cas/security/validateEmail?id="+casUser.getUserid()+"&token="+radMd5;
		se.doSendHtmlEmail(emailTitle, emailContext, email);
        //将验证码保存到数据库
		
		casUser.setValidateEmailCode(radMd5);
		casUser.setEmail(email);
		casUserService.update(casUser);
		model.addAttribute("type", "sendEmail");
        return "front/cas/security.edit";
    }
    
    
    /**
     *验证绑定邮箱
     * @author guoxue
     * 2016-7-13
     * @param model
     * @return
     */
    @RequestMapping(value = "security/validateEmail", method = RequestMethod.GET)
    public String securityValidateEmail(String token,String id,Model model) {
    	//从数据库取出验证码
    	CasUser casUser = new CasUser();
    	casUser.setUserid(Integer.parseInt(id));
    	CasUser newCasUser = casUserService.selectObject(casUser);
    	String validateEmailCode = newCasUser.getValidateEmailCode();
    	if(token.equals(validateEmailCode)){
    		//验证成功,将verifyiedEmail改变成1
    		casUser.setVerifiedEmail(Byte.valueOf("1"));
    		casUserService.update(casUser);
    		model.addAttribute("validateEmail", 1);
    		
    	}else{
    		//验证失败
    		model.addAttribute("validateEmail", 0);
    		
    	}
    	
    	return "front/cas/security.result";
    }
    
    
    
   
    /**
     * 初始化我的消息页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "message", method = RequestMethod.GET)
    public String message(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        
        return "front/cas/message.index";
    }
    
    
    /**
     * 获得验证码
     * @author HeWW
     * 2016-1-8
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public void createAuthCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 创建验证码，并将验证图片输出到客户端，将验证码md5后保存到客户端cookie
        AuthCodeUtil.createAuthCodeAndOutput(120, 40, 4, request,response);
    }
    
    /**
     * 保存找回密码
     * @author FXZ
     * 2016-06-16
     * @param request
     * @param username
     * @param password
     * @param repassword
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "updateNewPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateNewPassword(HttpServletRequest request, HttpServletResponse response,
            String username, String password, String repassword) throws IOException {
        LOGGER.info("-----saveNewPassword-----");
        
        Map<String, Object> reqMap = new HashMap<String, Object>();
        CasUser casUser = new CasUser();
        ResultTO resultTO = null;
        // 1,用户名注册
        if (repassword.equals(password)) {
            // 1.1,校验密码的一致性
        	casUser.setUsername(username);
        	casUser.setPassword(password);
        	// 2，用户信息录入
        	int resultUser = casUserService.updateNewPassword(casUser);
        	if (resultUser == 1) {
        		// 3,找回密码后相关逻辑
        		return ResultTO.newSuccessResultTO("密码找回成功！", "/front/cas/account.index");
        	} else {
        		return ResultTO.newFailResultTO("密码找回失败！", null);
        	}
        }else{
        	return ResultTO.newFailResultTO("密码找回失败！", null);
        }
    }
    
    /**
     * 登录验证
     * @author HeWW
     * 2016-1-8
     * @param model
     * @param request
     * @param response
     * @param captcha
     * @param username
     * @param password
     * @param urlContinue
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO loginUser(Model model, HttpServletRequest request, HttpServletResponse response, 
            String captcha, String username, String password) 
                    throws IOException, ServletException, IllegalAccessException, InvocationTargetException {

        //1、校验验证码是否正确
        boolean isValid = AuthCodeUtil.checkAuthCode(captcha, request, response);
        if (!isValid) {
            LOGGER.info("用户验证码输入错误");
            return ResultTO.newFailResultTO("验证码错误", "captcha");
        }
        
        //2、校验用户名密码长度
        if (username.length()<3 || password.length()<6) {
            return ResultTO.newFailResultTO("请输入正确的用户名和密码", "username");
        }
        
        //3、校验用户名密码
        //查询用户信息
        CasUser user = null;
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("username", username);
        List<CasUser> userList = casUserService.selectObjectList(reqMap);
        if (!userList.isEmpty()) {
            user = userList.get(0);
        }
        if (user == null ) {
            return ResultTO.newFailResultTO("用户名不存在", "username");
        }
        //校验密码
//        String pwd = StringUtil.md5(password) + user.getSalt();
//        if (!StringUtil.md5(pwd).equals(user.getPassword())) {
//            return ResultTO.newFailResultTO("密码错误", "captcha");
//        }
        
        String pwd = StringUtil.md5(password);
        if (!pwd.equals(user.getPassword())) {
            return ResultTO.newFailResultTO("密码错误", "captcha");
        }
        
        //4、登录成功后操作
        //4.1、创建token
        //4.2、将tokenId写出到客户端浏览器
        //4.3、把user对象，保存到用户session中
        SessionManager.setAttribute(request,CasConstant.KEY_CAS_USER, user);
        
        //5、登陆后记录用户行为
        //记录登陆时间
        user.setLastLoginTime(new Date());
        //记录用户ip地址
        if (request.getHeader("x-forwarded-for") == null) { 
        	 InetAddress netAddress = getInetAddress();  
            user.setLastLoginIp(getHostIp(netAddress));
        }else{
            user.setLastLoginIp(request.getHeader("x-forwarded-for"));
        }
        //持久化用户数据
        casUserService.update(user);
        
        //5、设置跳转uri
        String data = "/front/index/index";
        LOGGER.info("登录成功返回信息：【{}】", new Object[]{data});
        return ResultTO.newSuccessResultTO("登录成功", data);
    }


    /**
     * 登出操作
     * 
     * @author HeWeiwen 2015-8-18
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String signOut(Model model, HttpServletRequest request, HttpServletResponse response) {
        SessionManager.removeAttribute(request, CasConstant.KEY_CAS_USER);
//        CookieUtil.removeCookie(SessionUtil.bm_token, request, response);
        LOGGER.info("登出成功");

        return "front/cas/login";
    }
    
    
    /**
     * 会员注册
     * @author HeWW
     * 2016-1-11
     * @param request
     * @param simple
     * @param username
     * @param password
     * @param repassword
     * @param code
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO register(HttpServletRequest request, HttpServletResponse response,
            String username, String password, String repassword, String email,String captcha,
            String messageText,String phone) throws IOException {
        LOGGER.info("-----register-----");
        //1、校验验证码是否正确
        boolean isValid = AuthCodeUtil.checkAuthCode(captcha, request, response);
        if (!isValid) {
            LOGGER.info("用户验证码输入错误");
            return ResultTO.newFailResultTO("验证码有误，请重新输入", "captcha");
        }
        
        Map<String, Object> reqMap = new HashMap<String, Object>();
        CasUser casUser = new CasUser();
        ResultTO resultTO = null;
        // 1,用户名注册
        if (!"".equals(repassword) && !repassword.equals(password)) {
            // 1.1,校验密码的一致性
            return ResultTO.newFailResultTO("两次输入的密码不一致，请重新输入", "repassword");
        }
        casUser.setUsername(phone);
        //3,校验email是否被使用过
//        Map<String, Object> emailReq = new HashMap<String, Object>();
//        emailReq.put("email", email);
//        List<Map<String, Object>> emailUser = casUserService.selectMapList(emailReq);
//        if (!emailUser.isEmpty()) {
//            return ResultTO.newFailResultTO("该Email已经存在！请重新输入！", "email");
//        }
//        casUser.setEmail(email);
        // 4,校验用户是否已存在
        String userName =casUser.getUsername();
        reqMap.put(USERNAME, userName);
        List<Map<String, Object>> reqUser = casUserService
                .selectMapList(reqMap);
        if (!reqUser.isEmpty()) {
            return ResultTO.newFailResultTO("该用户已经存在，请登陆！", "userIsExist");
        }
        // 5,验证手机验证码是否正确
        String messageText1=tpl_value.substring(tpl_value.length()-6,tpl_value.length());
        if (!"".equals(messageText) && !messageText1.equals(messageText)) {
            // 1.1,校验密码的一致性
            return ResultTO.newFailResultTO("手机验证码有误，请重新输入", "messageText");
        }
        // 6，用户信息录入
        int resultUser = casUserInsert(casUser, repassword, userName);
        if (resultUser == 1) {
            // 7,注册成功后相关逻辑
            resultTO = registerContinue(userName,request,response);
        } else {
        	resultTO = ResultTO.newFailResultTO("对不起，您的注册失败了，请您稍候再试。", null);
        }
       
        return resultTO;
    }
    
    /**
     * 发送短信验证码
     * @author FXZ
     * 2016-06-06
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "sendShortMessage", method = RequestMethod.GET)
    public String sendShortMessage(HttpServletRequest request, HttpServletResponse response
    	 ) throws IOException {
        LOGGER.info("-----sendShortMessage-----");
        // 云片网 模板的id
        long tpl_id=944811;
        ResultTO resultTO = null;
        // 获取用户输入的手机号
        String phone = request.getParameter("phone");
        // 云片网的apikey
        String apikey ="855ea2b877854e9e0eeef484ae408dac";
      //设置对应的模板变量值  
//        String tpl_value="#code#="+randomNum(); 
        // 1,判断手机验证码是否一致
        if(phone.length()==11){
        	//调用发送短信功能
        	shortMessage.tplSendSms(apikey,tpl_id, tpl_value, phone);
//        	resultTO = ResultTO.newSuccessResultTO("短信发送成功！", null);
        }else{
        	resultTO = ResultTO.newFailResultTO("请输入正确	的手机号！", null);
        }
        return "front/cas/register";
    }
    
    
    @RequestMapping(value = "forgotpassword/index", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO forGotPassword(String username, String phone,
            String password, String repassword, String code) {
        LOGGER.info("-----forgotpassword-----");
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("phone", phone);

        // 2,根据唯一手机号码查询用户对象
        List<CasUser> casUserList = casUserService.selectObjectList(userMap);
        if (casUserList.isEmpty()) {
            return ResultTO.newFailResultTO("用户不存在", null);
        }
        CasUser casUser = casUserList.get(0);
        // 3,判断用户名是否为空，如果不为空，比较用户信息
        String usernameNew = username;
        if (null != usernameNew && !"".equals(usernameNew)) {
            // 比较用户信息是否一致
            if (!usernameNew.equals(casUser.getUsername())) {
                return ResultTO.newFailResultTO("手机号码与用户信息和不一致", null);
            }
        }
        // 4,校验密码的一致性
        if (!"".equals(repassword) && !repassword.equals(password)) {
            return ResultTO.newFailResultTO("找回密码接口密码不一致", null);
        }
        // 5,密码进行MD5加密
        String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(password)
                + casUser.getSalt());
        casUser.setPassword(passwordMd5Md5);
        // 6,持久化数据(修改密码)
        int resultUser = casUserService.update(casUser);
        if (resultUser == 1) {
            return ResultTO.newSessionInvalidResultTO("成功找回密码！", null); 
        } else {
            return ResultTO.newFailResultTO("找回密码失败！", null);
        }
    }
    
    /**
     * 根据父级Id查询地区信息
     * @author HeWW
     * 2016-1-14
     * @param parentId
     * @return
     */
    @RequestMapping(value = "getArea", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO GetArea(Integer parentId){
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("pid", parentId);
        List<TrendRegion> region = trendRegionService.selectObjectList(reqMap);
        return ResultTO.newSuccessResultTO("", region);
    }
    
    @RequestMapping(value = "account", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO accountUpdate(@ModelAttribute CasUser user,CasUserDetail userDetail,BindingResult result){
        user.setUserid(getCasUser().getUserid());
        user.setMtime(new Date());
        casUserService.update(user);
        
        userDetail.setUserid(user.getUserid());
        casUserDetailService.update(userDetail);
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    
    /**
     * 录入用户信息
     * @author HeWeiwen 2015-9-17
     * @param request
     * @param casUser
     * @param password
     * @param username
     * @return 扩展参数获取<范例> String gender = request.getParameter("gender") == null
     *         ? "" : request.getParameter("gender"); 扩展字段录入<范例>
     *         casUser.setGender((byte)(Integer.parseInt(gender)));
     */
    private int casUserInsert(CasUser casUser, String password, String username) {
        String salt = UUIDService.getUUID().subSequence(0, 6).toString();        
        casUser.setSalt(salt);
        casUser.setUsername(username);
        casUser.setUserLvId((short)1);//默认注册等级
        casUser.setCtime(new Date());
        casUser.setStatus((byte) 0);// 用户状态。1：已激活；0：未激活；
        casUser.setIsVerify((byte) 0);// 是否审核。1：是；0：否；
        casUser.setIsDel((byte) 0);//是否删除,1,是；0：否
        // 如果是手机号码验证码注册时不设置密码
        if (!"".equals(password)) {
            // 密码进行MD5加密
//            String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(password)
//                    + salt);
        	String passwordMd5Md5 = StringUtil.md5(password);
            casUser.setPassword(passwordMd5Md5);
            casUser.setEncrypt("Md5Md5");
        }
        int resultUser = casUserService.insert(casUser);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put(USERNAME, username);
        CasUser userMap = (CasUser) (casUserService.selectObjectList(reqMap)
                .size() > 0 ? casUserService.selectObjectList(reqMap).get(0)
                : null);
        CasUserDetail casUserDetail = new CasUserDetail();
        casUserDetail.setUserid(userMap.getUserid());
        casUserDetailService.insert(casUserDetail);

        return resultUser;
    }
    
    /**
     * 注册成功后相关逻辑
     * @author HeWW
     * 2016-1-11
     * @param simple
     * @param username
     * @param code
     * @return
     */
    private ResultTO registerContinue(String username, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put(USERNAME, username);
        CasUser respUser = (CasUser) (casUserService.selectObjectList(reqMap).size() > 0 ? casUserService.selectObjectList(reqMap).get(0): null);
        if (null == respUser) {
            ResultTO.newFailResultTO("注册失败！用户返回参数查询失败", "");
        }
        //4.1、创建token
        //4.2、将tokenId写出到客户端浏览器
        //4.3、把user对象，保存到用户session中
        SessionManager.setAttribute(request,CasConstant.KEY_CAS_USER, respUser);
        
//        return ResultTO.newSuccessResultTO("注册成功！", "/front/cas/index");
        return ResultTO.newSuccessResultTO("注册成功！", "/front/cas/account.index");
    }
    
    /**
     * 裁剪图片功能
     * 
     * @auther zhangzheng
     * @date 2016-1-15 下午2:50:20
     * @param cut
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "cutAndSave", method = RequestMethod.POST)
    @ResponseBody
    public void cutAndSave(ImageCutTO cut, HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
        String fileName = "";
        try {
            cut.setImageUrl(BaseConstant.BASE_IMAGE_ADDRESS + cut.getImageUrl());
            fileName = cut.getImageUrl().substring(cut.getImageUrl().lastIndexOf("/") + 1, cut.getImageUrl().length());
            cut.setSaveUrl(BaseConstant.BASE_IMAGE_ADDRESS + CasConstant.CAS_FRONT_USER_AVATAR_IMAGE  + fileName);
            Double multiple = cut.getMultiple();
            Integer x1 = (int) (cut.getX1() * multiple);
            Integer y1 = (int) (cut.getY1() * multiple);
            Integer x2 = (int) (cut.getX2() * multiple);
            Integer y2 = (int) (cut.getY2() * multiple);
            ImageUtils.cutImg(cut.getImageUrl(), x1, y1, x2, y2, cut.getWidth(), cut.getHeight(), cut.getSaveUrl());

            // 图片裁剪成功后，将图片上传至服务器
            String localFilePathName = BaseConstant.BASE_IMAGE_ADDRESS + CasConstant.CAS_FRONT_USER_AVATAR_IMAGE  + fileName;
            String HOST = ImageServerConfig.HOST;
            short PORT = ImageServerConfig.PORT;;
            String USERNAME = ImageServerConfig.USER_NAME;
            String PASSWORD = ImageServerConfig.PASSWORD;
            String BASE_IMAGE_PATH = ImageServerConfig.BASE_IMAGE_PATH;
            SftpUtil.upload(HOST, PORT, USERNAME, PASSWORD, localFilePathName, BASE_IMAGE_PATH + CasConstant.CAS_FRONT_USER_AVATAR_IMAGE + "/", fileName);
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CasUser user = getCasUser();
        user.setAvatar(CasConstant.CAS_FRONT_USER_AVATAR_IMAGE  + fileName);
        casUserService.update(user);
        
        //重定向跳转
        response.sendRedirect(Config.BASE_URL + "/front/cas/account/avatar");
    }
    
    /**
     * @auther FXZ
     * 生成6位随机数
     * 2016-06-08
     * @return 生成的随机数
     */
    
    public static int randomNum(){
    	Random random =new Random();
    	int x= random.nextInt(899999);
    	int y= 100000;
    	int z= x+y;
    	return z;
    }
    public static InetAddress getInetAddress(){  
    	  
    	        try{  
    	            return InetAddress.getLocalHost();  
    	        }catch(UnknownHostException e){  
    	            System.out.println("unknown host!");  
    	        }  
    	        return null;  
    	  
    	    }  
    public static String getHostIp(InetAddress netAddress){  
    	        if(null == netAddress){  
    	            return null;  
    	        }  
    	        String ip = netAddress.getHostAddress(); //get the ip address  
    	        return ip;  
    	   } 


    
}
