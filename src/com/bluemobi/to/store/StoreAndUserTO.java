package com.bluemobi.to.store;

import com.appcore.model.AbstractObject;

public class StoreAndUserTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Integer storeId;
    // 用户 ID。关联表：cas_user
    private Long userid;
    // 店铺名称
    private String storeName;
    // 公司名称
    private String companyName;
    // 店铺logo图片
    private String logo;
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
    // 签约开始时间字符串
    private String signingTimeStartStr;
    // 签约到期时间字符串
    private String signingTimeEndStr;
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
    // 用户名
    private String username;
    // 密码
    private String password;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Short getEmployeeNums() {
        return employeeNums;
    }

    public void setEmployeeNums(Short employeeNums) {
        this.employeeNums = employeeNums;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRegionIdShip() {
        return regionIdShip;
    }

    public void setRegionIdShip(Integer regionIdShip) {
        this.regionIdShip = regionIdShip;
    }

    public String getAddressReturn() {
        return addressReturn;
    }

    public void setAddressReturn(String addressReturn) {
        this.addressReturn = addressReturn;
    }

    public String getReturnRule() {
        return returnRule;
    }

    public void setReturnRule(String returnRule) {
        this.returnRule = returnRule;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getRunCategory() {
        return runCategory;
    }

    public void setRunCategory(String runCategory) {
        this.runCategory = runCategory;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageDefault() {
        return imageDefault;
    }

    public void setImageDefault(String imageDefault) {
        this.imageDefault = imageDefault;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessImage() {
        return businessImage;
    }

    public void setBusinessImage(String businessImage) {
        this.businessImage = businessImage;
    }

    public String getBusinessCategoryids() {
        return businessCategoryids;
    }

    public void setBusinessCategoryids(String businessCategoryids) {
        this.businessCategoryids = businessCategoryids;
    }

    public String getLegalpName() {
        return legalpName;
    }

    public void setLegalpName(String legalpName) {
        this.legalpName = legalpName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Byte isOfficial) {
        this.isOfficial = isOfficial;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Byte getIsSigning() {
        return isSigning;
    }

    public void setIsSigning(Byte isSigning) {
        this.isSigning = isSigning;
    }

    public String getSigningTimeStartStr() {
        return signingTimeStartStr;
    }

    public void setSigningTimeStartStr(String signingTimeStartStr) {
        this.signingTimeStartStr = signingTimeStartStr;
    }

    public String getSigningTimeEndStr() {
        return signingTimeEndStr;
    }

    public void setSigningTimeEndStr(String signingTimeEndStr) {
        this.signingTimeEndStr = signingTimeEndStr;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Byte isVerify) {
        this.isVerify = isVerify;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Integer getCreatorUserid() {
        return creatorUserid;
    }

    public void setCreatorUserid(Integer creatorUserid) {
        this.creatorUserid = creatorUserid;
    }

    public Byte getGoodsVerify() {
        return goodsVerify;
    }

    public void setGoodsVerify(Byte goodsVerify) {
        this.goodsVerify = goodsVerify;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
