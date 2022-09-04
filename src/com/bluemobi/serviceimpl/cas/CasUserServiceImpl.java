package com.bluemobi.serviceimpl.cas;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.StringUtil;
import com.appcore.util.UUIDService;
import com.bluemobi.apito.cas.RegisterRequestTO;
import com.bluemobi.dao.cas.CasUserDao;
import com.bluemobi.dao.cas.CasUserDetailDao;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.cas.CasUserDetail;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.service.cas.CasUserService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.cas.UpdatePwdTO;

/**
 * 【用户表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:11
 * 
 */
@Service(value = "casUserService")
public class CasUserServiceImpl extends MybatisBaseServiceImpl implements CasUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserServiceImpl.class);

    @Autowired
    private CasUserDao casUserDao;
    @Autowired
    private CasUserDetailDao casUserDetailDao;
    @Autowired
    private TrendAttachmentService trendAttachmentService;

    @Override
    public MyBatisBaseDao getDao() {
        return casUserDao;
    }

    private CasUser getUser(long userId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userid", userId);
        return this.selectObject(parameter);
    }

    @Override
    public ResultTO updateVerify(long userId) {
        CasUser user = getUser(userId);
        user.setIsVerify((byte) (user.getIsVerify().byteValue() == 1 ? 0 : 1));
        int r = this.update(user);
        if (r == 0) {
            return ResultTO.newFailResultTO("更新失败, 请联系管理员", null);
        } else {
            return ResultTO.newSuccessResultTO("", null);
        }
    }

    @Override
    public ResultTO updatePwd(UpdatePwdTO pwdTO) {
        CasUser user = getUser(pwdTO.getUserId());

//        // 1. 校验原密码是否正确
//        String passwordMd5 = StringUtil.md5(StringUtil.md5(pwdTO.getOldPassword()) + user.getSalt());
//        if (!passwordMd5.equals(user.getPassword())) {
//            return ResultTO.newFailResultTO("原密码不正确,请检查后重新输入!", null);
//        }

        // 2. 校验新密码和重复新密码是否一致
//        if (!pwdTO.getPassword().equals(pwdTO.getRepassword())) {
//            return ResultTO.newFailResultTO("新密码和重复密码不一致,请检查后重新输入!", null);
//        }

        // 3. 如果新密码和原密码一致，则不操作，直接返回成功结果
        if (pwdTO.getPassword().equals(pwdTO.getOldPassword())) {
            return ResultTO.newSuccessResultTO("更新成功!", null);
        }

        // 4. 更新密码
        user = new CasUser();
        user.setUserid(pwdTO.getUserId());
//        user.setPassword(StringUtil.md5(StringUtil.md5(pwdTO.getPassword()) + user.getSalt()));
        user.setPassword(StringUtil.md5(pwdTO.getPassword()));
        user.setMtime(Calendar.getInstance().getTime());
        int ret = this.update(user);
        if (ret == 1) {
            return ResultTO.newSuccessResultTO("更新成功!", null);
        } else {
            return ResultTO.newFailResultTO("更新失败,请联系管理员!", null);
        }
    }

    /**
     * 手机端用户注册
     * 
     * @author haojian
     * @date 2016-1-26 下午1:50:24
     * @param registerRequestTO
     * @return
     * @return CasUser
     */
    @Override
    public CasUser regiest(RegisterRequestTO registerRequestTO) {

        // 1、判断用户名是否存在

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", registerRequestTO.getUsername());
        List<CasUser> casUserList = casUserDao.selectObjectList(map);
        if (!casUserList.isEmpty()) {
            LOGGER.error("用户名【{}】已经存在，不能再注册！", new Object[] { registerRequestTO.getUsername() });
            throw new RuntimeException("用户名已经存在");
        }

        CasUser casUser = new CasUser();
        
        // 混淆码
        String salt = UUIDService.getUUID().subSequence(0, 6).toString();
        casUser.setSalt(salt);
        casUser.setUsername(registerRequestTO.getUsername());
        casUser.setUserLvId((short)1);//默认注册等级
        casUser.setGender((byte)0);//默认设置 性别。1：男；2：女；0：未知； 属性*/
        casUser.setCtime(new Date());
        casUser.setStatus((byte) 0);// 用户状态。1：已激活；0：未激活；
        casUser.setIsVerify((byte) 0);// 是否审核。1：是；0：否；
        casUser.setIsDel((byte) 0);//是否删除,1,是；0：否
        // 如果是手机号码验证码注册时不设置密码
        if (!"".equals(registerRequestTO.getPassword())) {
            // 密码进行MD5加密
//            String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(registerRequestTO.getPassword()) + salt);
            String passwordMd5Md5 = StringUtil.md5(registerRequestTO.getPassword());
            casUser.setPassword(passwordMd5Md5);
            casUser.setEncrypt("Md5Md5");
        }
        int resultUser = casUserDao.insert(casUser);
        if (resultUser == 1) {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("username", registerRequestTO.getUsername());
            CasUser userMap = (CasUser) (casUserDao.selectObjectList(reqMap).size() > 0 ? casUserDao.selectObjectList(reqMap).get(0) : null);
            CasUserDetail casUserDetail = new CasUserDetail();
            casUserDetail.setUserid(userMap.getUserid());
            casUserDetailDao.insert(casUserDetail);
        }
        return casUser;
    }

    @Override
    public CasUser updateAvatarAPI(Map<String, Object> param, Integer userid) throws Exception {
        // 保存附件表
        TrendAttachment trendAttachment = new TrendAttachment();
        trendAttachmentService.insertUploadFile(param, trendAttachment);
        // 更新用户表
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userid", userid);
        userMap.put("avatar", trendAttachment.getFilepath());
        casUserDao.update(userMap);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        CasUser casUser = casUserDao.selectObject(map);
        return casUser;
    }

    /**
     * 保存新密码
     * @auther FXZ
     * @param password
     */
	public int updateNewPassword(CasUser casUser) {
		casUser.setPassword(StringUtil.md5(casUser.getPassword()));
		int num=casUserDao.updateNewPassword(casUser);
		return num;
	}

}
