package com.bluemobi.po.admin;


import com.appcore.model.AbstractObject;

/**
 * 【权限组权限表（角色权限表）】持久化对象 数据库表：admin_group_permission
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-04 17:49:04
 * 
 */
public class AdminGroupPermission extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer groupPermissionId;
    // 权限组id（角色id）
    private Integer groupId;
    // 权限id
    private Integer permissionId;

    /** 获取 主键 属性 */
    public Integer getGroupPermissionId() {
        return groupPermissionId;
    }

    /** 设置 主键 属性 */
    public void setGroupPermissionId(Integer groupPermissionId) {
        this.groupPermissionId = groupPermissionId;
    }

    /** 获取 权限组id（角色id） 属性 */
    public Integer getGroupId() {
        return groupId;
    }

    /** 设置 权限组id（角色id） 属性 */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /** 获取 权限id 属性 */
    public Integer getPermissionId() {
        return permissionId;
    }

    /** 设置 权限id 属性 */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminGroupPermission");
        sb.append("{groupPermissionId=").append(groupPermissionId);
        sb.append(", groupId=").append(groupId);
        sb.append(", permissionId=").append(permissionId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminGroupPermission) {
            AdminGroupPermission adminGroupPermission = (AdminGroupPermission) obj;
            if (this.getGroupPermissionId().equals(adminGroupPermission.getGroupPermissionId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getGroupPermissionId();
        return pkStr.hashCode();
    }

}