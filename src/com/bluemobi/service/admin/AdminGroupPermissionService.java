package com.bluemobi.service.admin;

import com.appcore.service.MybatisBaseService;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
public interface AdminGroupPermissionService extends MybatisBaseService {

    /**
     * 判断是否有该权限
     * @author HeWeiwen
     * 2015-11-19
     * @param groupId
     * @param permissionId
     * @return
     */
    boolean hasPermission(int groupId,int permissionId);
    
    /**
     * 根据groupId和permissionId字段删除权限对象
     * @author HeWeiwen
     * 2015-11-19
     * @param groupId
     * @param permissionId
     */
    void deleteAdminGroupPermission(int groupId,int permissionId);
    
    /**
     * 根据groupId和permissionId字段删除权限对象
     * @author HeWeiwen
     * 2015-11-19
     * @param groupId
     * @param permissionId
     */
    void deleteByPermissionId(int permissionId);
    
    /**
     * 权限授权操作
     * @author HeWeiwen
     * 2015-12-1
     * @param groupId
     * @param isAssign
     * @param permissionIds
     */
    void assignPermission(int groupId, boolean isAssign, String permissionIds);
}
