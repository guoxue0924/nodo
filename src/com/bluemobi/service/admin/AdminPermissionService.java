package com.bluemobi.service.admin;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.admin.AdminPermission;

/**
 * 【应用权限表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
public interface AdminPermissionService extends MybatisBaseService {

    /**
     * 根据子级菜单ID集合 获得相关所有权限列表
     * @author HeWeiwen
     * 2015-11-19
     * @param userPermissionList
     * @return
     */
    List<AdminPermission> getPermissionListByNavigationIdList(List<Integer> navigationIdList);
    
    /**
     * 查询所有adminPermission列表
     * 
     * @author HeWeiwen 2015-11-19
     * @return
     */
    ConcurrentMap<String, AdminPermission> findAdminPermission();
    
    /**
     * 根据uri地址判断是否有该权限对象
     * @author HeWeiwen
     * 2015-11-25
     * @param uri
     * @return
     */
    boolean isAddPermissionByURI(String uri);

    /**
     * 更新所有adminPermission列表的缓存数据
     * 
     * @author HeWeiwen 2015-11-19
     */
    void updateAdminPermissionAll();
    
    
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
