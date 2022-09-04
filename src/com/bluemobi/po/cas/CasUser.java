package com.bluemobi.po.cas;
import java.util.Date;

import com.appcore.model.AbstractObject;
/**
 * 【用户表】持久化对象
 * 数据库表：cas_user
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:11
 *
 */
public class CasUser extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer userid; 
    //用户所属组别 ID
    private Short userGroupId; 
    //用户所属等级 ID
    private Short userLvId; 
    //用户名
    private String username; 
    //密码
    private String password; 
    //冻结器密码
    private String freeze; 
    //第三方平台ID
    private String thirdid; 
    //password enhanced vars
    private String salt; 
    //password encrypt handle or function
    private String encrypt; 
    //座机号码
    private String tel; 
    //用户手机号
    private String phone; 
    //手机验证状态。1：验证通过；0：验证未通过；
    private Byte verifiedPhone; 
    //邮箱
    private String email; 
    //绑定邮箱验证码
    private String validateEmailCode;
    //邮箱验证状态。1：验证通过；0：验证未通过；
    private Byte verifiedEmail; 
    //昵称，默认同用户名 username
    private String nickname; 
    //真实姓名
    private String realname; 
    //身份证号，18位
    private String idcard; 
    //性别。1：男；2：女；0：未知；
    private Byte gender; 
    //头像
    private String avatar; 
    //设备类型。android、iphone、ipad
    private String deviceType; 
    //设备序列号
    private String deviceNo; 
    //通道 ID（适用于安卓推送）
    private String channelid; 
    //用户状态。1：已激活；0：未激活；
    private Byte status; 
    //是否审核。1：是；0：否；
    private Byte isVerify; 
    //是否标记为删除。1：是；0：否；
    private Byte isDel; 
    //最后登陆时间
    private Date lastLoginTime; 
    //最后登陆ip
    private String lastLoginIp; 
    //帐号封停结束时间
    private Date banEtime; 
    //创建时间
    private Date ctime; 
    //手机验证码
    private String messageText; 
    
    //最后一次更新时间
    private Date mtime; 
    
    //会员积分
    private Integer userintegral; 

    /**获取  属性*/
    public Integer getUserid() {
        return userid;
    }
    
    /** 设置  属性*/
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    /**获取 用户所属组别 ID 属性*/
    public Short getUserGroupId() {
        return userGroupId;
    }
    
    /** 设置 用户所属组别 ID 属性*/
    public void setUserGroupId(Short userGroupId) {
        this.userGroupId = userGroupId;
    }
    /**获取 用户所属等级 ID 属性*/
    public Short getUserLvId() {
        return userLvId;
    }
    
    /** 设置 用户所属等级 ID 属性*/
    public void setUserLvId(Short userLvId) {
        this.userLvId = userLvId;
    }
    /**获取 用户名 属性*/
    public String getUsername() {
        return username;
    }
    
    /** 设置 用户名 属性*/
    public void setUsername(String username) {
        this.username = username;
    }
    /**获取 密码 属性*/
    public String getPassword() {
        return password;
    }
    
    /** 设置 密码 属性*/
    public void setPassword(String password) {
        this.password = password;
    }
    /**获取 冻结器密码 属性*/
    public String getFreeze() {
        return freeze;
    }
    
    /** 设置 冻结器密码 属性*/
    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }
    /**获取 第三方平台ID 属性*/
    public String getThirdid() {
        return thirdid;
    }
    
    /** 设置 第三方平台ID 属性*/
    public void setThirdid(String thirdid) {
        this.thirdid = thirdid;
    }
    /**获取 password enhanced vars 属性*/
    public String getSalt() {
        return salt;
    }
    
    /** 设置 password enhanced vars 属性*/
    public void setSalt(String salt) {
        this.salt = salt;
    }
    /**获取 password encrypt handle or function 属性*/
    public String getEncrypt() {
        return encrypt;
    }
    
    /** 设置 password encrypt handle or function 属性*/
    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }
    /**获取 座机号码 属性*/
    public String getTel() {
        return tel;
    }
    
    /** 设置 座机号码 属性*/
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**获取 用户手机号 属性*/
    public String getPhone() {
        return phone;
    }
    
    /** 设置 用户手机号 属性*/
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**获取 手机验证状态。1：验证通过；0：验证未通过； 属性*/
    public Byte getVerifiedPhone() {
        return verifiedPhone;
    }
    
    /** 设置 手机验证状态。1：验证通过；0：验证未通过； 属性*/
    public void setVerifiedPhone(Byte verifiedPhone) {
        this.verifiedPhone = verifiedPhone;
    }
    /**获取 邮箱 属性*/
    public String getEmail() {
        return email;
    }
    
    /** 设置 邮箱 属性*/
    public void setEmail(String email) {
        this.email = email;
    }
    /**获取 邮箱验证状态。1：验证通过；0：验证未通过； 属性*/
    public Byte getVerifiedEmail() {
        return verifiedEmail;
    }
    
    /** 设置 邮箱验证状态。1：验证通过；0：验证未通过； 属性*/
    public void setVerifiedEmail(Byte verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }
    /**获取 昵称，默认同用户名 username 属性*/
    public String getNickname() {
        return nickname;
    }
    
    /** 设置 昵称，默认同用户名 username 属性*/
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**获取 真实姓名 属性*/
    public String getRealname() {
        return realname;
    }
    
    /** 设置 真实姓名 属性*/
    public void setRealname(String realname) {
        this.realname = realname;
    }
    /**获取 身份证号，18位 属性*/
    public String getIdcard() {
        return idcard;
    }
    
    /** 设置 身份证号，18位 属性*/
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    /**获取 性别。1：男；2：女；0：未知； 属性*/
    public Byte getGender() {
        return gender;
    }
    
    /** 设置 性别。1：男；2：女；0：未知； 属性*/
    public void setGender(byte gender) {
        this.gender = gender;
    }
    /**获取 头像 属性*/
    public String getAvatar() {
        return avatar;
    }
    
    /** 设置 头像 属性*/
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    /**获取 设备类型。android、iphone、ipad 属性*/
    public String getDeviceType() {
        return deviceType;
    }
    
    /** 设置 设备类型。android、iphone、ipad 属性*/
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    /**获取 设备序列号 属性*/
    public String getDeviceNo() {
        return deviceNo;
    }
    
    /** 设置 设备序列号 属性*/
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    /**获取 通道 ID（适用于安卓推送） 属性*/
    public String getChannelid() {
        return channelid;
    }
    
    /** 设置 通道 ID（适用于安卓推送） 属性*/
    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }
    /**获取 用户状态。1：已激活；0：未激活； 属性*/
    public Byte getStatus() {
        return status;
    }
    
    /** 设置 用户状态。1：已激活；0：未激活； 属性*/
    public void setStatus(Byte status) {
        this.status = status;
    }
    /**获取 是否审核。1：是；0：否； 属性*/
    public Byte getIsVerify() {
        return isVerify;
    }
    
    /** 设置 是否审核。1：是；0：否； 属性*/
    public void setIsVerify(Byte isVerify) {
        this.isVerify = isVerify;
    }
    /**获取 是否标记为删除。1：是；0：否； 属性*/
    public Byte getIsDel() {
        return isDel;
    }
    
    /** 设置 是否标记为删除。1：是；0：否； 属性*/
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
    /**获取 最后登陆时间 属性*/
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    /** 设置 最后登陆时间 属性*/
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    /**获取 最后登陆ip 属性*/
    public String getLastLoginIp() {
        return lastLoginIp;
    }
    
    /** 设置 最后登陆ip 属性*/
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    /**获取 帐号封停结束时间 属性*/
    public Date getBanEtime() {
        return banEtime;
    }
    
    /** 设置 帐号封停结束时间 属性*/
    public void setBanEtime(Date banEtime) {
        this.banEtime = banEtime;
    }
    /**获取 创建时间 属性*/
    public Date getCtime() {
        return ctime;
    }
    
    /** 设置 创建时间 属性*/
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    /**获取 最后一次更新时间 属性*/
    public Date getMtime() {
        return mtime;
    }
    
    /** 设置 最后一次更新时间 属性*/
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    
    /**  获取会员积分*/
    public Integer getUserintegral() {
		return userintegral;
	}
    /**  设置会员积分属性*/
	public void setUserintegral(Integer userintegral) {
		this.userintegral = userintegral;
	}
	/**  获取手机验证码*/
	public String getMessageText() {
		return messageText;
	}
	/**  设置手机验证码*/
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUser");
        sb.append("{userid=").append(userid);
        sb.append(", userGroupId=").append(userGroupId);
        sb.append(", userLvId=").append(userLvId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", freeze=").append(freeze);
        sb.append(", thirdid=").append(thirdid);
        sb.append(", salt=").append(salt);
        sb.append(", encrypt=").append(encrypt);
        sb.append(", tel=").append(tel);
        sb.append(", phone=").append(phone);
        sb.append(", verifiedPhone=").append(verifiedPhone);
        sb.append(", email=").append(email);
        sb.append(", verifiedEmail=").append(verifiedEmail);
        sb.append(", nickname=").append(nickname);
        sb.append(", realname=").append(realname);
        sb.append(", idcard=").append(idcard);
        sb.append(", gender=").append(gender);
        sb.append(", avatar=").append(avatar);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", deviceNo=").append(deviceNo);
        sb.append(", channelid=").append(channelid);
        sb.append(", status=").append(status);
        sb.append(", isVerify=").append(isVerify);
        sb.append(", isDel=").append(isDel);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", banEtime=").append(banEtime);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", userintegral=").append(userintegral);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasUser) {
            CasUser casUser = (CasUser)obj;
            if(this.getUserid().equals(casUser.getUserid())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getUserid();
        return pkStr.hashCode();
    }

	public String getValidateEmailCode() {
		return validateEmailCode;
	}

	public void setValidateEmailCode(String validateEmailCode) {
		this.validateEmailCode = validateEmailCode;
	}
    
}