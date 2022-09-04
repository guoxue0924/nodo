package com.bluemobi.po.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;
import com.bluemobi.apito.PropertyTO;

/**
 * 【 商品详细内容，包括商品详情、meta 相关字段等】持久化对象 数据库表：goods_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-17 15:44:21
 * 
 */
public class GoodsContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

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
    // 是否上架销售。1：是；0：否；
    private Byte isShelf;
    // 上架时间
    private Date onSaleTime;
    // 下架时间
    private Date offSaleTime;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    // 商品skuId列表
    private List<String> skuIdList = new ArrayList<String>();
    
    //当前商品下面的所有属性列表
    private List<PropertyTO> propertyTOList = new ArrayList<PropertyTO>();

    /** 获取 属性 */
    public Long getContentId() {
        return contentId;
    }

    /** 设置 属性 */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /** 获取 所属平台的商品分类 ID 属性 */
    public Long getCategoryId() {
        return categoryId;
    }

    /** 设置 所属平台的商品分类 ID 属性 */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 品牌 ID 属性 */
    public Integer getBrandId() {
        return brandId;
    }

    /** 设置 品牌 ID 属性 */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /** 获取 商品发布者的用户 ID。对应表：admin_user 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 商品发布者的用户 ID。对应表：admin_user 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 商品名称 属性 */
    public String getName() {
        return name;
    }

    /** 设置 商品名称 属性 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 商品简介 属性 */
    public String getMemo() {
        return memo;
    }

    /** 设置 商品简介 属性 */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /** 获取 商品详情 属性 */
    public String getBody() {
        return body;
    }

    /** 设置 商品详情 属性 */
    public void setBody(String body) {
        this.body = body;
    }

    /** 获取 页面标题 属性 */
    public String getMetaTitle() {
        return metaTitle;
    }

    /** 设置 页面标题 属性 */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    /** 获取 页面关键词 属性 */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /** 设置 页面关键词 属性 */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    /** 获取 页面描述 属性 */
    public String getMetaDescription() {
        return metaDescription;
    }

    /** 设置 页面描述 属性 */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    /** 获取 序号。值越大，排位越靠前（不同商品间的排序）。 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号。值越大，排位越靠前（不同商品间的排序）。 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 关联商品 ID，多个以半角逗号分隔 属性 */
    public String getRelated() {
        return related;
    }

    /** 设置 关联商品 ID，多个以半角逗号分隔 属性 */
    public void setRelated(String related) {
        this.related = related;
    }

    /** 获取 是否开启规格，0不开启，1开启 属性 */
    public Byte getIsSpec() {
        return isSpec;
    }

    /** 设置 是否开启规格，0不开启，1开启 属性 */
    public void setIsSpec(Byte isSpec) {
        this.isSpec = isSpec;
    }

    /** 获取 是否标记为删除。1：是；0：否； 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记为删除。1：是；0：否； 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 是否上架销售。1：是；0：否； 属性 */
    public Byte getIsShelf() {
        return isShelf;
    }

    /** 设置 是否上架销售。1：是；0：否； 属性 */
    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    /** 获取 上架时间 属性 */
    public Date getOnSaleTime() {
        return onSaleTime;
    }

    /** 设置 上架时间 属性 */
    public void setOnSaleTime(Date onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    /** 获取 下架时间 属性 */
    public Date getOffSaleTime() {
        return offSaleTime;
    }

    /** 设置 下架时间 属性 */
    public void setOffSaleTime(Date offSaleTime) {
        this.offSaleTime = offSaleTime;
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

    public List<String> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<String> skuIdList) {
        this.skuIdList = skuIdList;
    }

    public List<PropertyTO> getPropertyTOList() {
        return propertyTOList;
    }

    public void setPropertyTOList(List<PropertyTO> propertyTOList) {
        this.propertyTOList = propertyTOList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GoodsContent");
        sb.append("{contentId=").append(contentId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
        sb.append(", userid=").append(userid);
        sb.append(", name=").append(name);
        sb.append(", memo=").append(memo);
        sb.append(", body=").append(body);
        sb.append(", metaTitle=").append(metaTitle);
        sb.append(", metaKeywords=").append(metaKeywords);
        sb.append(", metaDescription=").append(metaDescription);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", related=").append(related);
        sb.append(", isSpec=").append(isSpec);
        sb.append(", isDel=").append(isDel);
        sb.append(", isShelf=").append(isShelf);
        sb.append(", onSaleTime=").append(onSaleTime);
        sb.append(", offSaleTime=").append(offSaleTime);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsContent) {
            GoodsContent goodsContent = (GoodsContent) obj;
            if (this.getContentId().equals(goodsContent.getContentId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getContentId();
        return pkStr.hashCode();
    }

}