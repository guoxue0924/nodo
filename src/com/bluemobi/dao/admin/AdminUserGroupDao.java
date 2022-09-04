package com.bluemobi.dao.admin;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
public interface AdminUserGroupDao extends MyBatisBaseDao {
    
    /**
     * 删除所有权限组关心结构
     * @author HeWeiwen
     * 2015-11-18
     * @param map
     */
    void deleteAll(Map<String, Object> map);
}
