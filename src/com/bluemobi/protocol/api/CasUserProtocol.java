package com.bluemobi.protocol.api;

import com.bluemobi.apito.UserTO;
import com.bluemobi.apito.cas.LoginResponseTO;
import com.bluemobi.po.cas.CasUser;

public class CasUserProtocol {
    
    /**
     * 用户信息
     * @author haojian
     * @date 2016-1-22 上午10:42:26 
     * @param casUser
     * @return
     * @return Map<String,Object>
     */
    public static LoginResponseTO newLoginResponse(CasUser casUser){
        
        LoginResponseTO loginResponse = new LoginResponseTO();
        UserTO user = loginResponse.getUser();
        user.setUserid(casUser.getUserid());
        user.setNickname(casUser.getUsername());
        user.setAvatar(casUser.getAvatar());
        user.setGender((int)casUser.getGender());
        
        return loginResponse;
    }
    

}
