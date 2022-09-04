package com.bluemobi.po.advert;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：advert_frendlink
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-25 13:46:10
 * 
 */
public class AdvertFrendlink extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer linkId;
    // 友情链接名称
    private String linkName;
    // 链接地址
    private String href;
    // 图标
    private String imageUrl;
    // 序号
    private Integer sortOrder;
    // 状态。1：启用；0：关闭；
    private Byte status;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    /** 获取  属性 */
    public Integer getLinkId() {
        return linkId;
    }

    /** 设置  属性 */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /** 获取 友情链接名称 属性 */
    public String getLinkName() {
        return linkName;
    }

    /** 设置 友情链接名称 属性 */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /** 获取 链接地址 属性 */
    public String getHref() {
        return href;
    }

    /** 设置 链接地址 属性 */
    public void setHref(String href) {
        this.href = href;
    }

    /** 获取 图标 属性 */
    public String getImageUrl() {
        return imageUrl;
    }

    /** 设置 图标 属性 */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态。1：启用；0：关闭； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 状态。1：启用；0：关闭； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append("AdvertFrendlink");
        sb.append("{linkId=").append(linkId);
        sb.append(", linkName=").append(linkName);
        sb.append(", href=").append(href);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdvertFrendlink) {
            AdvertFrendlink advertFrendlink = (AdvertFrendlink) obj;
            if (this.getLinkId().equals(advertFrendlink.getLinkId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getLinkId();
        return pkStr.hashCode();
    }

}