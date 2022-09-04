package com.bluemobi.po.cas;
import java.util.Date;

import com.appcore.model.AbstractObject;
/**
 * 【用户登录失败日志（归档）】持久化对象
 * 数据库表：cas_login_error
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:10
 *
 */
public class CasLoginError extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer id; 
    //
    private String ip; 
    //
    private String sessionid; 
    //
    private String username; 
    //
    private Date ctime; 

    /**获取  属性*/
    public Integer getId() {
        return id;
    }
    
    /** 设置  属性*/
    public void setId(Integer id) {
        this.id = id;
    }
    /**获取  属性*/
    public String getIp() {
        return ip;
    }
    
    /** 设置  属性*/
    public void setIp(String ip) {
        this.ip = ip;
    }
    /**获取  属性*/
    public String getSessionid() {
        return sessionid;
    }
    
    /** 设置  属性*/
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
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
    public Date getCtime() {
        return ctime;
    }
    
    /** 设置  属性*/
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasLoginError");
        sb.append("{id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", sessionid=").append(sessionid);
        sb.append(", username=").append(username);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasLoginError) {
            CasLoginError casLoginError = (CasLoginError)obj;
            if(this.getId().equals(casLoginError.getId())){
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