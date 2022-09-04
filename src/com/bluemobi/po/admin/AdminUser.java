package com.bluemobi.po.admin;

import java.util.Date;

import javax.persistence.Transient;

import com.appcore.model.AbstractObject;
/**
 * 【后台用户】持久化对象
 * 数据库表：admin_user
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-28 15:14:39
 *
 */
public class AdminUser extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer userid; 
    //
    private String username; 
    //
    private String password; 
    //干扰码
    private String salt; 
    //邮箱
    private String email; 
    //昵称
    private String nickname; 
    //全名
    private String fullname; 
    //性别1:男，2:女，0为未知
    private Byte gender; 
    //身份证
    private String idcard; 
    //用户来源：local/epass usb token
    private String domain; 
    //创建时间
    private Date ctime; 
    //最后一次更新时间
    private Date mtime; 
    //用户状态：0未激活，1已激活
    private Byte status; 
    //是否在线。1：是；0：否；
    private Byte isOnline; 
    
    @Transient
    private int[] groups;

    /**获取  属性*/
    public Integer getUserid() {
        return userid;
    }
    
    /** 设置  属性*/
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    /**获取  属性*/
    public String getUsername() {
        return username;
    }
    
    /** 设置  属性*/
    public void setUsername(String username) {
        this.username = username;
    }
    /**获取  属性*/
    public String getPassword() {
        return password;
    }
    
    /** 设置  属性*/
    public void setPassword(String password) {
        this.password = password;
    }
    /**获取 干扰码 属性*/
    public String getSalt() {
        return salt;
    }
    
    /** 设置 干扰码 属性*/
    public void setSalt(String salt) {
        this.salt = salt;
    }
    /**获取 邮箱 属性*/
    public String getEmail() {
        return email;
    }
    
    /** 设置 邮箱 属性*/
    public void setEmail(String email) {
        this.email = email;
    }
    /**获取 昵称 属性*/
    public String getNickname() {
        return nickname;
    }
    
    /** 设置 昵称 属性*/
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**获取 全名 属性*/
    public String getFullname() {
        return fullname;
    }
    
    /** 设置 全名 属性*/
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    /**获取 性别1:男，2:女，0为未知 属性*/
    public Byte getGender() {
        return gender;
    }
    
    /** 设置 性别1:男，2:女，0为未知 属性*/
    public void setGender(Byte gender) {
        this.gender = gender;
    }
    /**获取 身份证 属性*/
    public String getIdcard() {
        return idcard;
    }
    
    /** 设置 身份证 属性*/
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    /**获取 用户来源：local/epass usb token 属性*/
    public String getDomain() {
        return domain;
    }
    
    /** 设置 用户来源：local/epass usb token 属性*/
    public void setDomain(String domain) {
        this.domain = domain;
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
    /**获取 用户状态：0未激活，1已激活 属性*/
    public Byte getStatus() {
        return status;
    }
    
    /** 设置 用户状态：0未激活，1已激活 属性*/
    public void setStatus(Byte status) {
        this.status = status;
    }
    /**获取 是否在线。1：是；0：否； 属性*/
    public Byte getIsOnline() {
        return isOnline;
    }
    
    /** 设置 是否在线。1：是；0：否； 属性*/
    public void setIsOnline(Byte isOnline) {
        this.isOnline = isOnline;
    }
    
    public int[] getGroups() {
        return groups;
    }

    public void setGroups(int[] groups) {
        this.groups = groups;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminUser");
        sb.append("{userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", email=").append(email);
        sb.append(", nickname=").append(nickname);
        sb.append(", fullname=").append(fullname);
        sb.append(", gender=").append(gender);
        sb.append(", idcard=").append(idcard);
        sb.append(", domain=").append(domain);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", status=").append(status);
        sb.append(", isOnline=").append(isOnline);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminUser) {
            AdminUser adminUser = (AdminUser)obj;
            if(this.getUserid().equals(adminUser.getUserid())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getUserid();
        return pkStr.hashCode();
    }
    
    
}