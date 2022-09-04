/**
 * Project Name:nodo 
 * File BaseAPIController.java 
 * Package Name:com.bluemobi.controller.api 
 * Date:2016年2月16日下午5:38:55 
 */
package com.bluemobi.controller.api;

import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.core.pool.TaskPoolFactory;
import com.appcore.util.RandomUtil;
import com.appcore.util.TimeUtil;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasCode;
import com.bluemobi.service.cas.CasCodeService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.cas.TempUser;
import com.bluemobi.util.MessageSender;
import com.bluemobi.util.SimpleMail;

/**
 * 基础api控制器
 * ClassName: baseController
 * Date: 2016年2月16日下午5:38:55

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller(value = "baseAPIController")
@RequestMapping("api")
public class BaseAPIController extends AbstractAPIController{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAPIController.class);
    
    @Autowired
    private CasCodeService casCodeService;
    
    
    /** 邮件名校验正则表达式 */
    private static final String CHECK_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /** 手机号码校验正则表达式 */
    private static final String CHECK_PHONE = "^1[34578][0-9]{1}[0-9]{8}$";
    /** 验证码过期时间（毫秒） */
    private static final long EXP_TIME = 120000L;
    /** 1：用户名注册 */
    private static final int SIMPLE_NAME = 1;
    /** 2：手机注册 */
    private static final int SIMPLE_PHONE = 2;
    /** 9：邮箱注册 */
    private static final int SIMPLE_EMAIL = 9;
    
    /** 用户手机验证码临时缓存 */
    private static final ConcurrentMap<String, TempUser> TEMP_USER_MAP = new ConcurrentHashMap<String, TempUser>();
    
	/**
	 * 发送验证码
	 * @author HeWW
	 * 2016-2-29
	 * @param sendto
	 * @param action
	 * @return
	 */
    @RequestMapping(value = "Cas/SendCode")
    @ResponseBody
	public ResultTO sendVerifyCode(String sendto, String action) {
        // 1,生产验证码
        String smsCode = RandomUtil.getRandomInt(100000, 999999) + "";// 6位随机数字
        // 2,校验用户类型
        int simple = checkUserNameType(sendto);
        // 3,发送邮件
        if (simple == SIMPLE_EMAIL) {
            SimpleMail cn = new SimpleMail();
            // 设置发件人地址、收件人地址和邮件标题
            cn.setAddress(sendto);
            cn.runOrder("您的验证码是:" + smsCode);
        } else if (simple == SIMPLE_PHONE) {
            // 4,发送短信
            MessageSender.sendAuthCode(sendto, smsCode);
        }

        // 5,存储到Map临时缓存对象
        putMapUserTemp(sendto, smsCode);
        // 6,持久化验证码数据
        putDateUserTemp(sendto, smsCode, action);
        LOGGER.info("-----sendcode----验证码是code:【{}】", smsCode);
        
        return ResultTO.newSuccessResultTO("获得验证码成功", null);
	}
    
    /**
     * 判断用户数据类型
     * @author HeWW
     * 2016-2-29
     * @param username
     * @return
     */
    private int checkUserNameType(String username) {
        boolean isMatched = true;
        // 1,初始为用户名
        int simple = SIMPLE_NAME;
        // 2,校验用户名是否是邮件
        Pattern regexEmail = Pattern.compile(CHECK_EMAIL);
        Matcher matcherEmail = regexEmail.matcher(username);
        isMatched = matcherEmail.matches();
        if (!isMatched) {
            // 校验用户名是否是手机号码
            Pattern regexPhone = Pattern.compile(CHECK_PHONE);
            Matcher matcherPhone = regexPhone.matcher(username);
            isMatched = matcherPhone.matches();
            if (!isMatched) {
                simple = SIMPLE_NAME;
            } else {
                simple = SIMPLE_PHONE;
            }
        } else {
            simple = SIMPLE_EMAIL;
        }
        return simple;
    }
    
    /**
     * 存储到Map临时缓存对象
     * @author HeWW
     * 2016-2-29
     * @param sendto
     * @param smsCode
     */
    private void putMapUserTemp(String sendto, String smsCode) {
        TempUser tempUser = new TempUser();
        // 获得当前的毫秒数
        long cTime = System.currentTimeMillis();
        long pTime = cTime + EXP_TIME;// 加上过期时间120毫秒数
        tempUser.setSmsCode(smsCode);
        tempUser.setPhone(sendto);
        tempUser.setCtime(cTime);
        tempUser.setPtime(pTime);
        // 将验证码对象存入临时缓存中
        TEMP_USER_MAP.put(sendto + "_" + smsCode, tempUser);
    }
    
    /**
     * 验证码数据持久化
     * 
     * @author HeWeiwen 2015-9-6
     * @param sendto
     * @param smsCode
     * @param action
     */
    private void putDateUserTemp(String sendto, String smsCode, String action) {
        // 1，获得当前的毫秒数
        long cTime = System.currentTimeMillis();
        long pTime = cTime + EXP_TIME;// 加上过期时间120毫秒数
        CasCode code = new CasCode();
        code.setUserid(new Long(0));
        int simple = checkUserNameType(sendto);
        // 2，判断类型，记录对应类型
        if (simple == SIMPLE_EMAIL) {
            code.setType("Email");
        } else if (simple == SIMPLE_PHONE) {
            code.setType("phone");
        }
        code.setAction(action);
        code.setSendTo(sendto);
        code.setCode(smsCode);
        code.setCtime(new Date());
        code.setExptime(new Date(pTime));
        // 将验证码对象插入数据库
        casCodeService.insert(code);
    }
    
    private static class CleanTempUserMapTask implements Runnable {

        private CleanTempUserMapTask() {

        }

        @Override
        public void run() {
            for (TempUser data : TEMP_USER_MAP.values()) {
                // 获得当前的毫秒数
                int cTimeSecond = TimeUtil.currentTimeSecond();
                if (cTimeSecond > data.getPtime()) {
                    BaseAPIController.LOGGER.info("清理短信验证码Map值【{}】，清理时间【{}】", new Object[] {data.getPhone() + "_" + data.getSmsCode(), TimeUtil.getFormatTime(cTimeSecond,"yyyy-MM-dd HH:mm:ss") });
                    BaseAPIController.TEMP_USER_MAP.remove(data.getPhone() + "_" + data.getSmsCode());
                }
            }
        }
    }

    static {
        CleanTempUserMapTask task = new CleanTempUserMapTask();
        TaskPoolFactory.scheduleAtFixedRate("CleanTempUserMapTask", task, 5L,5L, TimeUnit.MINUTES);
    }

}
