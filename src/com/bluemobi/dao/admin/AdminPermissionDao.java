package com.bluemobi.dao.admin;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.admin.AdminPermission;

/**
 * 【应用权限表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
public interface AdminPermissionDao extends MyBatisBaseDao {

    /**
     * 根据permissionId集合查询所有对象
     * @author HeWeiwen
     * 2015-11-19
     * @param map
     * @return
     */
    List<AdminPermission> getAdminPermissionById(Map<String, Object> map);
    
    /**
     * 根据菜单ID集合查询相关权限对象
     * @author HeWeiwen
     * 2015-11-20
     * @param map
     * @return
     */
    List<AdminPermission> getAdminPermissionByNavigationIdList(Map<String, Object> map);
    
    /**
     * 条件查询评论分页信息
     * @author HeWeiwen
     * 2015-10-26
     * @param map
     * @return
     */
    List<Map<String, Object>> pageByNavList(Map<String, Object> map);

    /**
     * 分页总条数
     * @author HeWeiwen
     * 2015-11-20
     * @param map
     * @return
     */
    int pageByNavListCondition(Map<String, Object> map);
    
    
    /**
     * 根据用户id，查询用户的所有权限信息
     * @author haojian
     * @date 2015-11-24 上午11:15:40 
     * @param userid
     * @return
     * @return List<AdminPermission>
     */
    List<AdminPermission> selectAdminPermissionListByUserid(int userid);
    
}
