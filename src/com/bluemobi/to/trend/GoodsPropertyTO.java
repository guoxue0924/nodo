package com.bluemobi.to.trend;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 前端商品详情页面查询属性时sql语句中用到的TO对象，包括商品所有字段、属性id、属性名称、属性值id、属性值名称
 * 
 * @author zhangzheng
 * @date 2016-1-21
 * 
 */
public class GoodsPropertyTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    //
    private Long contentId;
    // 所属平台的商品分类 ID
    private Long categoryId;
    // 品牌 ID
    private Integer brandId;
    // 商品发布者的用户 ID。对应表：admin_user
    private Integer userid;
    // 商品名称
    private String name;
    // 商品简介
    private String memo;
    // 商品详情
    private String body;
    // 页面标题
    private String metaTitle;
    // 页面关键词
    private String metaKeywords;
    // 页面描述
    private String metaDescription;
    // 序号。值越大，排位越靠前（不同商品间的排序）。
    private Integer sortOrder;
    // 关联商品 ID，多个以半角逗号分隔
    private String related;
    // 是否开启规格，0不开启，1开启
    private Byte isSpec;
    // 是否标记为删除。1：是；0：否；
    private Byte isDel;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    // 属性ID
    private Integer propertyId;
    // 标签名称
    private String labelName;

    // 属性值ID
    private Integer propertyValueId;
    // 参数可选值
    private String propertyValue;
    // 规格图片
    private String propertyImage;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public Byte getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Byte isSpec) {
        this.isSpec = isSpec;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

}
