package com.bluemobi.service.cas;

import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.apito.cas.RegisterRequestTO;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.cas.UpdatePwdTO;

/**
 * 【用户表】 服务类 接口
 * 
 * @date 2015-08-31 10:23:11
 * 
 */
public interface CasUserService extends MybatisBaseService {

    ResultTO updateVerify(long userId);

    ResultTO updatePwd(UpdatePwdTO pwdTO);

    /**
     * 手机端用户注册
     * 
     * @author haojian
     * @date 2016-1-26 下午1:50:24
     * @param registerRequestTO
     * @return
     * @return CasUser
     */
    CasUser regiest(RegisterRequestTO registerRequestTO);

    /**
     * 手机端用户修改头像
     * 
     * @auther zhangzheng
     * @date 2016-2-29 下午2:29:05
     * @param files
     * @return
     */
    CasUser updateAvatarAPI(Map<String, Object> map, Integer userid) throws Exception;
   
    /**
     * 保存新密码
     * @auther FXZ
     * @param password
     */
	int updateNewPassword(CasUser casUser);

}
