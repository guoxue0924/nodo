package com.bluemobi.service.system;


import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.system.SystemNavigation;

/**
 * 【system navigation category】 服务类 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 17:15:49
 *
 */
public interface SystemNavigationService extends MybatisBaseService{    
    
    /**
     * 根据条件查询菜单信息
     * @author HeWeiwen
     * 2015-7-28
     * @return
     */
    List<SystemNavigation> getSystemNavigationsByWhere();
    
    /**
     * 根据父级Id获得所有相关子级对象ID集合
     * @author HeWeiwen
     * 2015-8-6
     * @param user
     * @return
     */
    List<Integer> getSytemNavigationByParentId(int parentId);
    
    /**
     * 查询所有菜单信息
     * @author HeWeiwen
     * 2015-9-10
     * @return
     */
    List<SystemNavigation> findAllSystemNavigation();

    /**
     * 添加菜单信息
     * @author HeWeiwen
     * 2015-9-11
     * @param navigation
     * @return
     */
    void insertNavigation(SystemNavigation navigation);
    
    /**
     * 修改菜单信息
     * @author HeWeiwen
     * 2015-9-11
     * @param navigation
     * @return
     */
    void updateNavigation(SystemNavigation navigation);
    
    
    /**
     * 校验其下已有二级导航
     * @author HeWeiwen
     * 2015-9-10
     * @return
     */
    List<SystemNavigation> findSystemNavigationByParentId(int parentId);
    
    
    /**
     * 根据用户id查询用户的一级导航和二级导航
     * 导航类结构：一级导航里面有一个二级导航的list
     * @author haojian
     * @date 2015-11-24 下午1:26:05 
     * @param userid
     * @return
     * @return List<SystemNavigation>
     */
    List<SystemNavigation> selectSystemNavigationByUserid(int userid);
    
    /**
     * 根据Hid模糊查询相关所有菜单对象
     * @author HeWeiwen
     * 2015-12-3
     * @param hid
     * @return
     */
    List<SystemNavigation> selectObjectListLikeByHid(int hid);
}

