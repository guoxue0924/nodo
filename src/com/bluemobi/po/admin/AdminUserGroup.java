package com.bluemobi.po.admin;


import com.appcore.model.AbstractObject;

/**
 * 【用户权限组表（用户角色表）】持久化对象 数据库表：admin_user_group
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-04 17:49:05
 * 
 */
public class AdminUserGroup extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer userGroupId;
    // 用户id
    private Integer userId;
    // 权限组id（角色id）
    private Integer groupId;

    /** 获取 主键 属性 */
    public Integer getUserGroupId() {
        return userGroupId;
    }

    /** 设置 主键 属性 */
    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    /** 获取 用户id 属性 */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id 属性 */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 权限组id（角色id） 属性 */
    public Integer getGroupId() {
        return groupId;
    }

    /** 设置 权限组id（角色id） 属性 */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminUserGroup");
        sb.append("{userGroupId=").append(userGroupId);
        sb.append(", userId=").append(userId);
        sb.append(", groupId=").append(groupId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminUserGroup) {
            AdminUserGroup adminUserGroup = (AdminUserGroup) obj;
            if (this.getUserGroupId().equals(adminUserGroup.getUserGroupId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getUserGroupId();
        return pkStr.hashCode();
    }

}