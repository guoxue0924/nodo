package com.bluemobi.dao.system;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.system.SystemNavigation;

/**
 * 【system navigation category】 数据访问对象 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 17:15:49
 *
 */
public interface SystemNavigationDao extends MyBatisBaseDao{    
    
    
    List<SystemNavigation> selectObjectListByLinkNull(Map<Object, Object> reqMap );

    List<SystemNavigation> selectObjectListByStatus(Map<Object, Object> reqMap );
    
    List<SystemNavigation> selectObjectListByParentId(Map<Object, Object> reqMap );
    
    List<SystemNavigation> selectObjectListLikeByHid(Map<String, Object> reqMap);
    /**
     * 根据用户id查询用户的二级导航
     * @author haojian
     * @date 2015-11-24 上午11:19:23 
     * @param userid
     * @return
     * @return List<SystemNavigation>
     */
    List<SystemNavigation> selectSecondNavigationsByUserid(int userid);
    
    /**
     * 根据二级导航，查询一级导航
     * @author haojian
     * @date 2015-11-24 上午11:19:41 
     * @param secondNavigationList
     * @return
     * @return List<SystemNavigation>
     */
    List<SystemNavigation> selectFirstNavigationsBySecondNavigations(List<SystemNavigation> secondNavigationList);
    
}


