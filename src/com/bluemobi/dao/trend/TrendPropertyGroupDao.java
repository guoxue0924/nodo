package com.bluemobi.dao.trend;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【属性资源分组表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public interface TrendPropertyGroupDao extends MyBatisBaseDao {

    /**
     * 批量删除属性分组
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:24:13
     * @param map
     */
    void deleteByGroupIds(Map<String, Object> map);

}
