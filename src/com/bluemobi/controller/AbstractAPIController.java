package com.bluemobi.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.appcore.util.SessionManager;
import com.bluemobi.constant.CasConstant;
import com.bluemobi.po.cas.CasUser;

/**
 * 抽象的API控制器
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-15 上午10:08:40 
 *
 */
public abstract class AbstractAPIController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAPIController.class);

    /**
     * 获取userId
     * @author haojian
     * @date 2015-10-15 上午10:09:30 
     * @return
     * @return int
     */
    @Override
    public int getUserid() {

        CasUser casUser = this.getCasUser();
        
        int userid = 0;
        if(casUser!=null){
            userid = casUser.getUserid();
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
        CasUser casUser = this.getCasUser();
        if(casUser!=null){
            return casUser.getUsername();
        }else{
            return String.valueOf(getUserid());
        }
        
    }
    
    /**
     * 获取普通用户
     * @author haojian
     * @date 2015-12-2 上午9:48:53 
     * @param request
     * @return
     * @return CasUser
     */
    public CasUser getCasUser(){
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CasUser casUser = SessionManager.getAttribute(request, CasConstant.KEY_CAS_USER);
        return casUser;
    
    }
    
}
