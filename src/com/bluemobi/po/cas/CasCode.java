package com.bluemobi.po.cas;
import java.util.Date;

import com.appcore.model.AbstractObject;
/**
 * 【用于用户各种动作的码表】持久化对象
 * 数据库表：cas_code
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:10
 *
 */
public class CasCode extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer id; 
    //用户 ID，关联表：cas_user
    private Long userid; 
    //email：邮箱；phone：手机；
    private String type; 
    //register：注册；login：登录；bind：绑定（邮箱、或手机）；forgotpassowrd：忘记密码；
    private String action; 
    //发送至：手机号或 email
    private String sendTo; 
    //码
    private String code; 
    //发送时间
    private Date ctime; 
    //过期时间
    private Date exptime; 

    /**获取  属性*/
    public Integer getId() {
        return id;
    }
    
    /** 设置  属性*/
    public void setId(Integer id) {
        this.id = id;
    }
    /**获取 用户 ID，关联表：cas_user 属性*/
    public Long getUserid() {
        return userid;
    }
    
    /** 设置 用户 ID，关联表：cas_user 属性*/
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    /**获取 email：邮箱；phone：手机； 属性*/
    public String getType() {
        return type;
    }
    
    /** 设置 email：邮箱；phone：手机； 属性*/
    public void setType(String type) {
        this.type = type;
    }
    /**获取 register：注册；login：登录；bind：绑定（邮箱、或手机）；forgotpassowrd：忘记密码； 属性*/
    public String getAction() {
        return action;
    }
    
    /** 设置 register：注册；login：登录；bind：绑定（邮箱、或手机）；forgotpassowrd：忘记密码； 属性*/
    public void setAction(String action) {
        this.action = action;
    }
    /**获取 发送至：手机号或 email 属性*/
    public String getSendTo() {
        return sendTo;
    }
    
    /** 设置 发送至：手机号或 email 属性*/
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
    /**获取 码 属性*/
    public String getCode() {
        return code;
    }
    
    /** 设置 码 属性*/
    public void setCode(String code) {
        this.code = code;
    }
    /**获取 发送时间 属性*/
    public Date getCtime() {
        return ctime;
    }
    
    /** 设置 发送时间 属性*/
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    /**获取 过期时间 属性*/
    public Date getExptime() {
        return exptime;
    }
    
    /** 设置 过期时间 属性*/
    public void setExptime(Date exptime) {
        this.exptime = exptime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasCode");
        sb.append("{id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", type=").append(type);
        sb.append(", action=").append(action);
        sb.append(", sendTo=").append(sendTo);
        sb.append(", code=").append(code);
        sb.append(", ctime=").append(ctime);
        sb.append(", exptime=").append(exptime);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasCode) {
            CasCode casCode = (CasCode)obj;
            if(this.getId().equals(casCode.getId())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }
    
}