package com.bluemobi.dao.trend;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.to.trend.PropertyAndPropertyValueTO;

/**
 * 【属性资源表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public interface TrendPropertyDao extends MyBatisBaseDao {

    Map<String, Object> selectMapPropertyAndValue(Map<String, Object> map);

    /**
     * 根据分类id查询和该分类绑定的所有属性
     * 
     * @auther zhangzheng
     * @date 2015-11-12 下午2:20:12
     * @param paramMap
     * @return
     */
    List<PropertyAndPropertyValueTO> selectPropertyByCategoryId(Map<String, Object> paramMap);

    /**
     * 根据商品id查询该商品绑定的所有属性
     * 
     * @auther zhangzheng
     * @date 2015-11-18 下午1:53:22
     * @param paramMap
     * @return
     */
    List<PropertyAndPropertyValueTO> selectPropertyByGoodsContentId(Map<String, Object> paramMap);

    /**
     * 根据属性id和属性值id查询字符串，返回结果为“属性:属性值”，如“颜色:红色”
     * 
     * @auther zhangzheng
     * @date 2016-1-12 下午4:13:08
     * @param paramMap
     * @return
     */
    String selectPropertyAndValueName(Map<String, Object> paramMap);

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
     * 根据分类ID查询定价属性个数
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午2:13:12
     * @param categoryId
     * @return
     */
    int selectPropertyIsSpecCountByCategoryId(Integer categoryId);

    /**
     * 根据分类ID查询图片属性个数
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午2:13:59
     * @param categoryId
     * @return
     */
    int selectPropertyIsImageCountByCategoryId(Integer categoryId);

}
