package com.bluemobi.dao.admin;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.admin.AdminGroupPermission;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
public interface AdminGroupPermissionDao extends MyBatisBaseDao {

    /**
     * 通过groupId和PermissionId删除数据
     * @author HeWeiwen
     * 2015-11-19
     * @param map
     */
    void deleteByPermission(Map<String, Object> map);
    
    /**
     * 通过PermissionId删除数据
     * @author HeWeiwen
     * 2015-11-19
     * @param map
     */
    void deleteByPermissionId(Map<String, Object> map);
    
    /**
     * 通过GroupId集合查询所有权限关联ID
     * @author HeWeiwen
     * 2015-11-19
     * @param map
     * @return 
     */
    List<AdminGroupPermission> getGroupPermissionList(Map<String, Object> map);
}
