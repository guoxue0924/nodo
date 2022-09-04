package com.bluemobi.service.admin;


import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.admin.AdminUserGroup;

/**
 * 【后台用户】 服务类 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-28 15:14:39
 *
 */
public interface AdminUserService extends MybatisBaseService{    
    
    
    void insertUser(AdminUser adminUser);

    void updateUser(AdminUser user);
    
    void deleteUser(int userid);
    
    /**
     * 根据GroupId集合获取用户组的所有权限
     * @author HeWeiwen
     * 2015-11-19
     * @param groupList
     * @return
     */
     List<Integer> getGroupPermissions(List<AdminUserGroup> groupList);
    
    /**
     * 获取用户所属权限组
     * @author HeWeiwen
     * 2015-11-19
     * @param userId
     * @return
     */
     List<AdminUserGroup> getGroupPermissionsByUserId(int userId);
}

