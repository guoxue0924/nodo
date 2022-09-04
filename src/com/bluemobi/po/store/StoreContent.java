package com.bluemobi.po.store;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【已签约商户表】持久化对象 数据库表：store_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-15 17:24:48
 * 
 */
public class StoreContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer storeId;
    // 用户 ID。关联表：cas_user
    private Integer userid;
    // 店铺名称
    private String storeName;
    // 公司名称
    private String companyName;
    // 店铺logo图片
    private String logo;
    // 公司成立日期
    private Date setupDate;
    // 公司注册资本
    private BigDecimal registerCapital;
    // 公司员工数
    private Short employeeNums;
    // 所在地区 ID
    private Integer regionId;
    // 地区名称。如：中国 湖北省 武汉市
    private String regionName;
    // 详细地址
    private String address;
    // 发货地区 ID
    private Integer regionIdShip;
    // 退货收货地址
    private String addressReturn;
    // 退货条约
    private String returnRule;
    // 营业时间
    private String businessTime;
    // 经营类别
    private String runCategory;
    // 公司电话
    private String tel;
    // 传真号码
    private String fax;
    // 公司主页
    private String homepage;
    // 电子邮箱
    private String email;
    // 店铺主页图片
    private String imageDefault;
    // 营业执照号码
    private String businessLicense;
    // 营业执照图片
    private String businessImage;
    // 主营业务
    private String businessCategoryids;
    // 法人姓名
    private String legalpName;
    // 简单描述
    private String description;
    // 是否标记为官方店铺。1：是；0：否；
    private Byte isOfficial;
    // 商户来源
    private String source;
    // 是否签约 1：是；0：否；
    private Byte isSigning;
    // 签约开始时间
    private Date signingTimeStart;
    // 签约到期时间
    private Date signingTimeEnd;
    // 状态。0：未启用； 1：正常； -1：删除
    private Byte status;
    // 是否通过审核。1：审核通过；0：待审核；-1：审核未通过（即驳回）
    private Byte isVerify;
    // 驳回理由
    private String rejectionReason;
    // 创建者 ID。对应表：admin_user
    private Integer creatorUserid;
    // 发布商品需要平台审核。1：需要；0：不需要；
    private Byte goodsVerify;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;
    // 审核通过时间
    private Date verifyTime;

    /** 获取 属性 */
    public Integer getStoreId() {
        return storeId;
    }

    /** 设置 属性 */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** 获取 用户 ID。关联表：cas_user 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户 ID。关联表：cas_user 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 店铺名称 属性 */
    public String getStoreName() {
        return storeName;
    }

    /** 设置 店铺名称 属性 */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /** 获取 公司名称 属性 */
    public String getCompanyName() {
        return companyName;
    }

    /** 设置 公司名称 属性 */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** 获取 店铺logo图片 属性 */
    public String getLogo() {
        return logo;
    }

    /** 设置 店铺logo图片 属性 */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /** 获取 公司成立日期 属性 */
    public Date getSetupDate() {
        return setupDate;
    }

    /** 设置 公司成立日期 属性 */
    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    /** 获取 公司注册资本 属性 */
    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    /** 设置 公司注册资本 属性 */
    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    /** 获取 公司员工数 属性 */
    public Short getEmployeeNums() {
        return employeeNums;
    }

    /** 设置 公司员工数 属性 */
    public void setEmployeeNums(Short employeeNums) {
        this.employeeNums = employeeNums;
    }

    /** 获取 所在地区 ID 属性 */
    public Integer getRegionId() {
        return regionId;
    }

    /** 设置 所在地区 ID 属性 */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    /** 获取 地区名称。如：中国 湖北省 武汉市 属性 */
    public String getRegionName() {
        return regionName;
    }

    /** 设置 地区名称。如：中国 湖北省 武汉市 属性 */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /** 获取 详细地址 属性 */
    public String getAddress() {
        return address;
    }

    /** 设置 详细地址 属性 */
    public void setAddress(String address) {
        this.address = address;
    }

    /** 获取 发货地区 ID 属性 */
    public Integer getRegionIdShip() {
        return regionIdShip;
    }

    /** 设置 发货地区 ID 属性 */
    public void setRegionIdShip(Integer regionIdShip) {
        this.regionIdShip = regionIdShip;
    }

    /** 获取 退货收货地址 属性 */
    public String getAddressReturn() {
        return addressReturn;
    }

    /** 设置 退货收货地址 属性 */
    public void setAddressReturn(String addressReturn) {
        this.addressReturn = addressReturn;
    }

    /** 获取 退货条约 属性 */
    public String getReturnRule() {
        return returnRule;
    }

    /** 设置 退货条约 属性 */
    public void setReturnRule(String returnRule) {
        this.returnRule = returnRule;
    }

    /** 获取 营业时间 属性 */
    public String getBusinessTime() {
        return businessTime;
    }

    /** 设置 营业时间 属性 */
    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    /** 获取 经营类别 属性 */
    public String getRunCategory() {
        return runCategory;
    }

    /** 设置 经营类别 属性 */
    public void setRunCategory(String runCategory) {
        this.runCategory = runCategory;
    }

    /** 获取 公司电话 属性 */
    public String getTel() {
        return tel;
    }

    /** 设置 公司电话 属性 */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /** 获取 传真号码 属性 */
    public String getFax() {
        return fax;
    }

    /** 设置 传真号码 属性 */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /** 获取 公司主页 属性 */
    public String getHomepage() {
        return homepage;
    }

    /** 设置 公司主页 属性 */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /** 获取 电子邮箱 属性 */
    public String getEmail() {
        return email;
    }

    /** 设置 电子邮箱 属性 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获取 店铺主页图片 属性 */
    public String getImageDefault() {
        return imageDefault;
    }

    /** 设置 店铺主页图片 属性 */
    public void setImageDefault(String imageDefault) {
        this.imageDefault = imageDefault;
    }

    /** 获取 营业执照号码 属性 */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /** 设置 营业执照号码 属性 */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    /** 获取 营业执照图片 属性 */
    public String getBusinessImage() {
        return businessImage;
    }

    /** 设置 营业执照图片 属性 */
    public void setBusinessImage(String businessImage) {
        this.businessImage = businessImage;
    }

    /** 获取 主营业务 属性 */
    public String getBusinessCategoryids() {
        return businessCategoryids;
    }

    /** 设置 主营业务 属性 */
    public void setBusinessCategoryids(String businessCategoryids) {
        this.businessCategoryids = businessCategoryids;
    }

    /** 获取 法人姓名 属性 */
    public String getLegalpName() {
        return legalpName;
    }

    /** 设置 法人姓名 属性 */
    public void setLegalpName(String legalpName) {
        this.legalpName = legalpName;
    }

    /** 获取 简单描述 属性 */
    public String getDescription() {
        return description;
    }

    /** 设置 简单描述 属性 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 是否标记为官方店铺。1：是；0：否； 属性 */
    public Byte getIsOfficial() {
        return isOfficial;
    }

    /** 设置 是否标记为官方店铺。1：是；0：否； 属性 */
    public void setIsOfficial(Byte isOfficial) {
        this.isOfficial = isOfficial;
    }

    /** 获取 商户来源 属性 */
    public String getSource() {
        return source;
    }

    /** 设置 商户来源 属性 */
    public void setSource(String source) {
        this.source = source;
    }

    /** 获取 是否签约 1：是；0：否； 属性 */
    public Byte getIsSigning() {
        return isSigning;
    }

    /** 设置 是否签约 1：是；0：否； 属性 */
    public void setIsSigning(Byte isSigning) {
        this.isSigning = isSigning;
    }

    /** 获取 签约开始时间 属性 */
    public Date getSigningTimeStart() {
        return signingTimeStart;
    }

    /** 设置 签约开始时间 属性 */
    public void setSigningTimeStart(Date signingTimeStart) {
        this.signingTimeStart = signingTimeStart;
    }

    /** 获取 签约到期时间 属性 */
    public Date getSigningTimeEnd() {
        return signingTimeEnd;
    }

    /** 设置 签约到期时间 属性 */
    public void setSigningTimeEnd(Date signingTimeEnd) {
        this.signingTimeEnd = signingTimeEnd;
    }

    /** 获取 状态。0：未启用； 1：正常； -1：删除 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 状态。0：未启用； 1：正常； -1：删除 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 是否通过审核。1：审核通过；0：待审核；-1：审核未通过（即驳回） 属性 */
    public Byte getIsVerify() {
        return isVerify;
    }

    /** 设置 是否通过审核。1：审核通过；0：待审核；-1：审核未通过（即驳回） 属性 */
    public void setIsVerify(Byte isVerify) {
        this.isVerify = isVerify;
    }

    /** 获取 驳回理由 属性 */
    public String getRejectionReason() {
        return rejectionReason;
    }

    /** 设置 驳回理由 属性 */
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    /** 获取 创建者 ID。对应表：admin_user 属性 */
    public Integer getCreatorUserid() {
        return creatorUserid;
    }

    /** 设置 创建者 ID。对应表：admin_user 属性 */
    public void setCreatorUserid(Integer creatorUserid) {
        this.creatorUserid = creatorUserid;
    }

    /** 获取 发布商品需要平台审核。1：需要；0：不需要； 属性 */
    public Byte getGoodsVerify() {
        return goodsVerify;
    }

    /** 设置 发布商品需要平台审核。1：需要；0：不需要； 属性 */
    public void setGoodsVerify(Byte goodsVerify) {
        this.goodsVerify = goodsVerify;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 最后一次更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 审核通过时间 属性 */
    public Date getVerifyTime() {
        return verifyTime;
    }

    /** 设置 审核通过时间 属性 */
    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("StoreContent");
        sb.append("{storeId=").append(storeId);
        sb.append(", userid=").append(userid);
        sb.append(", storeName=").append(storeName);
        sb.append(", companyName=").append(companyName);
        sb.append(", logo=").append(logo);
        sb.append(", setupDate=").append(setupDate);
        sb.append(", registerCapital=").append(registerCapital);
        sb.append(", employeeNums=").append(employeeNums);
        sb.append(", regionId=").append(regionId);
        sb.append(", regionName=").append(regionName);
        sb.append(", address=").append(address);
        sb.append(", regionIdShip=").append(regionIdShip);
        sb.append(", addressReturn=").append(addressReturn);
        sb.append(", returnRule=").append(returnRule);
        sb.append(", businessTime=").append(businessTime);
        sb.append(", runCategory=").append(runCategory);
        sb.append(", tel=").append(tel);
        sb.append(", fax=").append(fax);
        sb.append(", homepage=").append(homepage);
        sb.append(", email=").append(email);
        sb.append(", imageDefault=").append(imageDefault);
        sb.append(", businessLicense=").append(businessLicense);
        sb.append(", businessImage=").append(businessImage);
        sb.append(", businessCategoryids=").append(businessCategoryids);
        sb.append(", legalpName=").append(legalpName);
        sb.append(", description=").append(description);
        sb.append(", isOfficial=").append(isOfficial);
        sb.append(", source=").append(source);
        sb.append(", isSigning=").append(isSigning);
        sb.append(", signingTimeStart=").append(signingTimeStart);
        sb.append(", signingTimeEnd=").append(signingTimeEnd);
        sb.append(", status=").append(status);
        sb.append(", isVerify=").append(isVerify);
        sb.append(", rejectionReason=").append(rejectionReason);
        sb.append(", creatorUserid=").append(creatorUserid);
        sb.append(", goodsVerify=").append(goodsVerify);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", verifyTime=").append(verifyTime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StoreContent) {
            StoreContent storeContent = (StoreContent) obj;
            if (this.getStoreId().equals(storeContent.getStoreId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getStoreId();
        return pkStr.hashCode();
    }

}