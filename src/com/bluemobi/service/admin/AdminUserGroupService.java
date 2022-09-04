package com.bluemobi.service.admin;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.admin.AdminUserGroup;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
public interface AdminUserGroupService extends MybatisBaseService {

    /**
     * 更新用户与用户组关系
     * @author HeWeiwen
     * 2015-11-18
     * @param userid
     * @param groupids
     */
    void updateUserGroup(int userid, int[] groupids);
    
    /**
     * 根据userId删除所有权限组权限相关数据
     * @author HeWeiwen
     * 2015-11-24
     * @param userid
     */
    void deleteByUserId(int userid);
    
    /**
     * 根据用户ID获得权限组对象集合
     * @author HeWeiwen
     * 2015-11-18
     * @param userid
     * @return
     */
    List<AdminUserGroup> getAdminUserGroupByUserId(int userid);
}
