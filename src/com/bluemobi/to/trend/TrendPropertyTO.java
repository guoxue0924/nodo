package com.bluemobi.to.trend;

import com.bluemobi.po.trend.TrendProperty;

/**
 * 商品属性
 * 
 * @author zhangzheng
 * @date 2015-10-13
 * 
 */
public class TrendPropertyTO extends TrendProperty {

    private static final long serialVersionUID = 1L;

    // 属性值
    private String[] pVal;
    // 序号
    private Integer[] pSortOrder;
    // 是否默认
    private Integer pIsDefault;
    private Integer[] pPropertyValueId;
    // 是否上传了图片
    private boolean[] hasImage;
    // 图片上传完成后用来保存图片路径
    private String[] imageUrl;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String[] getpVal() {
        return pVal;
    }

    public void setpVal(String[] pVal) {
        this.pVal = pVal;
    }

    public Integer[] getpSortOrder() {
        return pSortOrder;
    }

    public void setpSortOrder(Integer[] pSortOrder) {
        this.pSortOrder = pSortOrder;
    }

    public Integer getpIsDefault() {
        return pIsDefault;
    }

    public void setpIsDefault(Integer pIsDefault) {
        this.pIsDefault = pIsDefault;
    }

    public Integer[] getpPropertyValueId() {
        return pPropertyValueId;
    }

    public void setpPropertyValueId(Integer[] pPropertyValueId) {
        this.pPropertyValueId = pPropertyValueId;
    }

    public boolean[] getHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean[] hasImage) {
        this.hasImage = hasImage;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

}
