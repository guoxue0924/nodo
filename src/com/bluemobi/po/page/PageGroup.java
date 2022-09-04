package com.bluemobi.po.page;


import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：page_group
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 10:07:22
 * 
 */
public class PageGroup extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer groupId;
    // 目录名
    private String folder;
    // 分组名称
    private String groupName;
    // 是否允许被删除。1：允许；0：不允许；
    private Byte moveable;

    /** 获取  属性 */
    public Integer getGroupId() {
        return groupId;
    }

    /** 设置  属性 */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /** 获取 目录名 属性 */
    public String getFolder() {
        return folder;
    }

    /** 设置 目录名 属性 */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /** 获取 分组名称 属性 */
    public String getGroupName() {
        return groupName;
    }

    /** 设置 分组名称 属性 */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /** 获取 是否允许被删除。1：允许；0：不允许； 属性 */
    public Byte getMoveable() {
        return moveable;
    }

    /** 设置 是否允许被删除。1：允许；0：不允许； 属性 */
    public void setMoveable(Byte moveable) {
        this.moveable = moveable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PageGroup");
        sb.append("{groupId=").append(groupId);
        sb.append(", folder=").append(folder);
        sb.append(", groupName=").append(groupName);
        sb.append(", moveable=").append(moveable);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PageGroup) {
            PageGroup pageGroup = (PageGroup) obj;
            if (this.getGroupId().equals(pageGroup.getGroupId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getGroupId();
        return pkStr.hashCode();
    }

}