package com.bluemobi.po.cas;
import java.util.Date;

import com.appcore.model.AbstractObject;
/**
 * 【用户正常登录日志（归档）】持久化对象
 * 数据库表：cas_login_log
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:10
 *
 */
public class CasLoginLog extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Long logid; 
    //
    private Long userid; 
    //
    private String username; 
    //
    private String sessionid; 
    //
    private String ip; 
    //
    private String ua; 
    //
    private Date ctime; 

    /**获取  属性*/
    public Long getLogid() {
        return logid;
    }
    
    /** 设置  属性*/
    public void setLogid(Long logid) {
        this.logid = logid;
    }
    /**获取  属性*/
    public Long getUserid() {
        return userid;
    }
    
    /** 设置  属性*/
    public void setUserid(Long userid) {
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
    public String getSessionid() {
        return sessionid;
    }
    
    /** 设置  属性*/
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
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
    public String getUa() {
        return ua;
    }
    
    /** 设置  属性*/
    public void setUa(String ua) {
        this.ua = ua;
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
        sb.append("CasLoginLog");
        sb.append("{logid=").append(logid);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", sessionid=").append(sessionid);
        sb.append(", ip=").append(ip);
        sb.append(", ua=").append(ua);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasLoginLog) {
            CasLoginLog casLoginLog = (CasLoginLog)obj;
            if(this.getLogid().equals(casLoginLog.getLogid())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getLogid();
        return pkStr.hashCode();
    }
    
}