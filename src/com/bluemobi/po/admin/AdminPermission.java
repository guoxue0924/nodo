package com.bluemobi.po.admin;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【权限表】持久化对象 数据库表：admin_permission
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-04 17:49:04
 * 
 */
public class AdminPermission extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 权限id
    private Integer permissionId;
    // 控制器url地址
    private String url;
    // 菜单id
    private Integer navigationId;
    // 权限名
    private String permissionName;
    // 描述
    private String description;
    // 创建时间
    private Date ctime;
    // 修改时间
    private Date mtime;

    /** 获取 权限id 属性 */
    public Integer getPermissionId() {
        return permissionId;
    }

    /** 设置 权限id 属性 */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /** 获取 控制器url地址 属性 */
    public String getUrl() {
        return url;
    }

    /** 设置 控制器url地址 属性 */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 获取 菜单id 属性 */
    public Integer getNavigationId() {
        return navigationId;
    }

    /** 设置 菜单id 属性 */
    public void setNavigationId(Integer navigationId) {
        this.navigationId = navigationId;
    }

    /** 获取 权限名 属性 */
    public String getPermissionName() {
        return permissionName;
    }

    /** 设置 权限名 属性 */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
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
        sb.append("AdminPermission");
        sb.append("{permissionId=").append(permissionId);
        sb.append(", url=").append(url);
        sb.append(", navigationId=").append(navigationId);
        sb.append(", permissionName=").append(permissionName);
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
        if (obj instanceof AdminPermission) {
            AdminPermission adminPermission = (AdminPermission) obj;
            if (this.getPermissionId().equals(adminPermission.getPermissionId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPermissionId();
        return pkStr.hashCode();
    }

}