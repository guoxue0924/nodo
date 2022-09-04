package com.bluemobi.po.cas;
import java.util.Date;

import com.appcore.model.AbstractObject;
/**
 * 【用户不常用详细信息表,禁止联合查询】持久化对象
 * 数据库表：cas_user_detail
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:11
 *
 */
public class CasUserDetail extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //禁止联合查询
    private Integer userid; 
    //用户生日，即出生日期
    private String birthday; 
    //职业
    private String job; 
    //个性签名
    private String signature; 
    //地区 ID
    private Integer regionId; 
    //地区名称。如：中国 湖北省 武汉市
    private String regionName; 
    //详细地址
    private String address; 
    //邮政编码
    private String zipcode; 
    //注册来源
    private String source; 
    //密保问答，以序列化方式存储
    private String qa; 
    //用户编号
    private String userSn; 
    //公司名称
    private String companyName; 
    //备注类型
    private String remarkType; 
    //备注
    private String remark; 
    //邀请码
    private String invitationCode; 
    //经度
    private String longitude; 
    //纬度
    private String latitude; 

    /**获取 禁止联合查询 属性*/
    public Integer getUserid() {
        return userid;
    }
    
    /** 设置 禁止联合查询 属性*/
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    /**获取 用户生日，即出生日期 属性*/
    public String getBirthday() {
        return birthday;
    }
    
    /** 设置 用户生日，即出生日期 属性*/
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    /**获取 职业 属性*/
    public String getJob() {
        return job;
    }
    
    /** 设置 职业 属性*/
    public void setJob(String job) {
        this.job = job;
    }
    /**获取 个性签名 属性*/
    public String getSignature() {
        return signature;
    }
    
    /** 设置 个性签名 属性*/
    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**获取 地区 ID 属性*/
    public Integer getRegionId() {
        return regionId;
    }
    
    /** 设置 地区 ID 属性*/
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    /**获取 地区名称。如：中国 湖北省 武汉市 属性*/
    public String getRegionName() {
        return regionName;
    }
    
    /** 设置 地区名称。如：中国 湖北省 武汉市 属性*/
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    /**获取 详细地址 属性*/
    public String getAddress() {
        return address;
    }
    
    /** 设置 详细地址 属性*/
    public void setAddress(String address) {
        this.address = address;
    }
    /**获取 邮政编码 属性*/
    public String getZipcode() {
        return zipcode;
    }
    
    /** 设置 邮政编码 属性*/
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    /**获取 注册来源 属性*/
    public String getSource() {
        return source;
    }
    
    /** 设置 注册来源 属性*/
    public void setSource(String source) {
        this.source = source;
    }
    /**获取 密保问答，以序列化方式存储 属性*/
    public String getQa() {
        return qa;
    }
    
    /** 设置 密保问答，以序列化方式存储 属性*/
    public void setQa(String qa) {
        this.qa = qa;
    }
    /**获取 用户编号 属性*/
    public String getUserSn() {
        return userSn;
    }
    
    /** 设置 用户编号 属性*/
    public void setUserSn(String userSn) {
        this.userSn = userSn;
    }
    /**获取 公司名称 属性*/
    public String getCompanyName() {
        return companyName;
    }
    
    /** 设置 公司名称 属性*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**获取 备注类型 属性*/
    public String getRemarkType() {
        return remarkType;
    }
    
    /** 设置 备注类型 属性*/
    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }
    /**获取 备注 属性*/
    public String getRemark() {
        return remark;
    }
    
    /** 设置 备注 属性*/
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**获取 邀请码 属性*/
    public String getInvitationCode() {
        return invitationCode;
    }
    
    /** 设置 邀请码 属性*/
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
    /**获取 经度 属性*/
    public String getLongitude() {
        return longitude;
    }
    
    /** 设置 经度 属性*/
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    /**获取 纬度 属性*/
    public String getLatitude() {
        return latitude;
    }
    
    /** 设置 纬度 属性*/
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUserDetail");
        sb.append("{userid=").append(userid);
        sb.append(", birthday=").append(birthday);
        sb.append(", job=").append(job);
        sb.append(", signature=").append(signature);
        sb.append(", regionId=").append(regionId);
        sb.append(", regionName=").append(regionName);
        sb.append(", address=").append(address);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", source=").append(source);
        sb.append(", qa=").append(qa);
        sb.append(", userSn=").append(userSn);
        sb.append(", companyName=").append(companyName);
        sb.append(", remarkType=").append(remarkType);
        sb.append(", remark=").append(remark);
        sb.append(", invitationCode=").append(invitationCode);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasUserDetail) {
            CasUserDetail casUserDetail = (CasUserDetail)obj;
            if(this.getUserid().equals(casUserDetail.getUserid())){
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