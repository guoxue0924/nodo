package com.bluemobi.service.trend;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.to.trend.PropertyTO;
import com.bluemobi.to.trend.TrendPropertyTO;

/**
 * 【属性资源表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public interface TrendPropertyService extends MybatisBaseService {

    /**
     * 初始化属性和属性值信息
     * 
     * @author haojian
     * @date 2016-1-30 下午4:31:38
     * @return
     * @return int
     */
    int initTrendProperty();

    /**
     * 新增属性
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:52:26
     * @param trendPropertyTO
     */
    void saveTrendProperty(TrendPropertyTO trendPropertyTO);

    /**
     * 修改属性
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:53:25
     * @param trendPropertyTO
     */
    void updateTrendProperty(TrendPropertyTO trendPropertyTO);

    /**
     * 删除属性和属性值
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:53:30
     * @param propertyids
     */
    void deleteTrendPropertyAndValues(String propertyids);

    /**
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:53:41
     * @param paramMap
     * @return
     */
    Map<String, Object> selectMapPropertyAndValue(Map<String, Object> paramMap);

    /**
     * 根据分类id查询和该分类绑定的所有属性
     * 
     * @auther zhangzheng
     * @date 2015-11-12 下午2:20:12
     * @param paramMap
     * @return
     * @throws Exception
     */
    Map<String, Object> selectPropertyByCategoryId(Map<String, Object> paramMap) throws Exception;

    /**
     * 根据商品id查询该商品绑定的所有属性
     * 
     * @auther zhangzheng
     * @date 2015-11-18 下午2:55:09
     * @param paramMap
     * @return
     * @throws Exception
     */
    List<PropertyTO> selectPropertyByGoodsContentId(Map<String, Object> paramMap) throws Exception;

    /**
     * 查询属性是否是图片属性，0：否；1：是
     * 
     * @auther zhangzheng
     * @date 2016-2-25 下午3:29:12
     * @param propertyId
     * @return
     */
    int selectPropertyIsImage(Integer propertyId);

    /**
     * 分类绑定属性页面，修改属性的状态：是否为定价属性
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午2:10:03
     * @param propertyId
     * @param status
     * @param categoryId
     * @return
     */
    int changePropertyType(Integer propertyId, Integer status, Integer categoryId);

    /**
     * 分类绑定页面，修改图片属性
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午2:24:14
     * @param propertyId
     * @param status
     * @param categoryId
     * @return
     */
    int changePropertyImage(Integer propertyId, Integer status, Integer categoryId);

    /**
     * 属性管理页面，修改属性的状态：是否为定价属性
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午3:08:06
     * @param propertyId
     * @param isSpec
     * @return
     */
    int changeSpec(Integer propertyId, Integer isSpec);

}
