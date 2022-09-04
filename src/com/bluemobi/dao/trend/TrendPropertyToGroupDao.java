package com.bluemobi.dao.trend;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.trend.TrendPropertyToGroup;

/**
 * 【商品属性与分组关系表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public interface TrendPropertyToGroupDao extends MyBatisBaseDao {

    /**
     * 批量新增属性分组和属性的关联
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:27:21
     * @param list
     */
    void insertTrendPropertyToGroups(List<TrendPropertyToGroup> list);

    /**
     * 根据属性分组ID删除属性分组和属性关联
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:27:03
     * @param map
     */
    void deleteByGroupId(Map<String, Object> map);

    /**
     * 查询属性分组ID已绑定属性的数量
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:28:06
     * @param map
     * @return
     */
    int selectPropertyCountByGroupId(Map<String, Object> map);

    /**
     * 根据ID批量删除
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:33:46
     * @param map
     * @return
     */
    int deleteByPropertyGroupIds(Map<String, Object> map);

}
