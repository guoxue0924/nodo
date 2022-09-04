package com.bluemobi.po.advert;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：advert_page
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 13:59:03
 * 
 */
public class AdvertPage extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer pageId;
    //
    private Date ctime;
    // 序号
    private Integer sortOrder;
    //
    private String title;

    /** 获取 属性 */
    public Integer getPageId() {
        return pageId;
    }

    /** 设置 属性 */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /** 获取 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdvertPage");
        sb.append("{pageId=").append(pageId);
        sb.append(", ctime=").append(ctime);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", title=").append(title);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdvertPage) {
            AdvertPage advertPage = (AdvertPage) obj;
            if (this.getPageId().equals(advertPage.getPageId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPageId();
        return pkStr.hashCode();
    }

}