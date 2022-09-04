package com.bluemobi.po.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.appcore.model.AbstractObject;
/**
 * 【system navigation category】持久化对象
 * 数据库表：system_navigation
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 17:15:49
 *
 */
public class SystemNavigation extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer navigationId; 
    //parent navigation_id
    private Integer parentId; 
    //hierarchically category id
    private String hid; 
    //category title
    private String title; 
    //
    private String link; 
    //加载方式。1：ajax 加载；2：iframe 加载；
    private Byte loadType; 
    //
    private String description; 
    //导航 icon
    private String icon; 
    //导航 icon 背景
    private String iconBg; 
    //序号
    private Integer sortOrder; 
    //是否启用。1：是；0：否；
    private Byte status; 
    //
    private Date ctime; 
    //
    private Date mtime; 
    //临时自动（判断递进关系）
    private String strPadding;
    
    /**
     * 当前导航的 子集导航list
     * @author haojian
     * @date 2015-11-24 下午12:39:00 
     */
    @Transient
    private List<SystemNavigation> subList = new ArrayList<SystemNavigation>();
    

    /**获取  属性*/
    public Integer getNavigationId() {
        return navigationId;
    }
    
    /** 设置  属性*/
    public void setNavigationId(Integer navigationId) {
        this.navigationId = navigationId;
    }
    /**获取 parent navigation_id 属性*/
    public Integer getParentId() {
        return parentId;
    }
    
    /** 设置 parent navigation_id 属性*/
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    /**获取 hierarchically category id 属性*/
    public String getHid() {
        return hid;
    }
    
    /** 设置 hierarchically category id 属性*/
    public void setHid(String hid) {
        this.hid = hid;
    }
    /**获取 category title 属性*/
    public String getTitle() {
        return title;
    }
    
    /** 设置 category title 属性*/
    public void setTitle(String title) {
        this.title = title;
    }
    /**获取  属性*/
    public String getLink() {
        return link;
    }
    
    /** 设置  属性*/
    public void setLink(String link) {
        this.link = link;
    }
    /**获取 加载方式。1：ajax 加载；2：iframe 加载； 属性*/
    public Byte getLoadType() {
        return loadType;
    }
    
    /** 设置 加载方式。1：ajax 加载；2：iframe 加载； 属性*/
    public void setLoadType(Byte loadType) {
        this.loadType = loadType;
    }
    /**获取  属性*/
    public String getDescription() {
        return description;
    }
    
    /** 设置  属性*/
    public void setDescription(String description) {
        this.description = description;
    }
    /**获取 导航 icon 属性*/
    public String getIcon() {
        return icon;
    }
    
    /** 设置 导航 icon 属性*/
    public void setIcon(String icon) {
        this.icon = icon;
    }
    /**获取 导航 icon 背景 属性*/
    public String getIconBg() {
        return iconBg;
    }
    
    /** 设置 导航 icon 背景 属性*/
    public void setIconBg(String iconBg) {
        this.iconBg = iconBg;
    }
    /**获取 序号 属性*/
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    /** 设置 序号 属性*/
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    /**获取 是否启用。1：是；0：否； 属性*/
    public Byte getStatus() {
        return status;
    }
    
    /** 设置 是否启用。1：是；0：否； 属性*/
    public void setStatus(Byte status) {
        this.status = status;
    }
    /**获取  属性*/
    public Date getCtime() {
        return ctime;
    }
    
    /** 设置  属性*/
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    /**获取  属性*/
    public Date getMtime() {
        return mtime;
    }
    
    /** 设置  属性*/
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    

    public String getStrPadding() {
        return strPadding;
    }

    public void setStrPadding(String strPadding) {
        this.strPadding = strPadding;
    }
    
    /**
     * 获取当前导航的子集导航
     * @author haojian
     * @date 2015-11-24 下午12:45:46 
     * @return
     * @return List<SystemNavigation>
     */
    public List<SystemNavigation> getSubList() {
        return subList;
    }
    /**
     * 设置当前导航的子集导航
     * @author haojian
     * @date 2015-11-24 下午12:45:58 
     * @param subList
     * @return void
     */
    public void setSubList(List<SystemNavigation> subList) {
        this.subList = subList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SystemNavigation");
        sb.append("{navigationId=").append(navigationId);
        sb.append(", parentId=").append(parentId);
        sb.append(", hid=").append(hid);
        sb.append(", title=").append(title);
        sb.append(", link=").append(link);
        sb.append(", loadType=").append(loadType);
        sb.append(", description=").append(description);
        sb.append(", icon=").append(icon);
        sb.append(", iconBg=").append(iconBg);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof SystemNavigation) {
            SystemNavigation systemNavigation = (SystemNavigation)obj;
            if(this.getNavigationId().equals(systemNavigation.getNavigationId())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getNavigationId();
        return pkStr.hashCode();
    }
    
}