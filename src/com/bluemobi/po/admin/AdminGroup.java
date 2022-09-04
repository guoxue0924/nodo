package com.bluemobi.po.admin;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【权限组表(角色表)】持久化对象 数据库表：admin_group
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-04 17:49:04
 * 
 */
public class AdminGroup extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 权限组id（角色id）
    private Integer groupId;
    // 权限组名（角色名）
    private String groupName;
    // 描述
    private String description;
    // 创建时间
    private Date ctime;
    // 修改时间
    private Date mtime;

    /** 获取 权限组id（角色id） 属性 */
    public Integer getGroupId() {
        return groupId;
    }

    /** 设置 权限组id（角色id） 属性 */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /** 获取 权限组名（角色名） 属性 */
    public String getGroupName() {
        return groupName;
    }

    /** 设置 权限组名（角色名） 属性 */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /** 获取 描述 属性 */
    public String getDescription() {
        return description;
    }

    /** 设置 描述 属性 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 修改时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 修改时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminGroup");
        sb.append("{groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", description=").append(description);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminGroup) {
            AdminGroup adminGroup = (AdminGroup) obj;
            if (this.getGroupId().equals(adminGroup.getGroupId())) {
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