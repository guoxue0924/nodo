package com.bluemobi.po.goods;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【商品品牌表】持久化对象 数据库表：goods_brand
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:56
 * 
 */
public class GoodsBrand extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer brandId;
    // 品牌名称
    private String brandName;
    // 商品品牌英文名
    private String brandNameEn;
    // 品牌官网地址
    private String brandUrl;
    // 品牌介绍
    private String brandDesc;
    // 品牌标志地址
    private String brandLogo;
    // 品牌关键字。多个以竖线“|”进行分隔
    private String brandKeywords;
    // 是否启用。1：是；0：否；
    private Byte status;
    // 序号
    private Integer sortOrder;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 属性 */
    public Integer getBrandId() {
        return brandId;
    }

    /** 设置 属性 */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /** 获取 品牌名称 属性 */
    public String getBrandName() {
        return brandName;
    }

    /** 设置 品牌名称 属性 */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /** 获取 商品品牌英文名 属性 */
    public String getBrandNameEn() {
        return brandNameEn;
    }

    /** 设置 商品品牌英文名 属性 */
    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn;
    }

    /** 获取 品牌官网地址 属性 */
    public String getBrandUrl() {
        return brandUrl;
    }

    /** 设置 品牌官网地址 属性 */
    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    /** 获取 品牌介绍 属性 */
    public String getBrandDesc() {
        return brandDesc;
    }

    /** 设置 品牌介绍 属性 */
    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    /** 获取 品牌标志地址 属性 */
    public String getBrandLogo() {
        return brandLogo;
    }

    /** 设置 品牌标志地址 属性 */
    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    /** 获取 品牌关键字。多个以竖线“|”进行分隔 属性 */
    public String getBrandKeywords() {
        return brandKeywords;
    }

    /** 设置 品牌关键字。多个以竖线“|”进行分隔 属性 */
    public void setBrandKeywords(String brandKeywords) {
        this.brandKeywords = brandKeywords;
    }

    /** 获取 是否启用。1：是；0：否； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 是否启用。1：是；0：否； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GoodsBrand");
        sb.append("{brandId=").append(brandId);
        sb.append(", brandName=").append(brandName);
        sb.append(", brandNameEn=").append(brandNameEn);
        sb.append(", brandUrl=").append(brandUrl);
        sb.append(", brandDesc=").append(brandDesc);
        sb.append(", brandLogo=").append(brandLogo);
        sb.append(", brandKeywords=").append(brandKeywords);
        sb.append(", status=").append(status);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsBrand) {
            GoodsBrand goodsBrand = (GoodsBrand) obj;
            if (this.getBrandId().equals(goodsBrand.getBrandId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getBrandId();
        return pkStr.hashCode();
    }

}