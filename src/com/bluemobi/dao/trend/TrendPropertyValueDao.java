package com.bluemobi.dao.trend;

import java.util.List;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.trend.TrendPropertyValue;

/**
 * 【属性资源值表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public interface TrendPropertyValueDao extends MyBatisBaseDao {

    /**
     * 批量新增属性值
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:25:28
     * @param list
     */
    void insertTrendPropertyValues(List<TrendPropertyValue> list);

    /**
     * 批量修改属性值
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:25:45
     * @param list
     */
    void updateTrendPropertyValues(List<TrendPropertyValue> list);

}
