package com.bluemobi.dao.goods;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.apito.PropertyTO;
import com.bluemobi.po.goods.GoodsProperty;
import com.bluemobi.to.trend.PropertyAndPropertyValueTO;

/**
 * 【商品属性表，用以记录商品属性值】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 17:42:48
 * 
 */
public interface GoodsPropertyDao extends MyBatisBaseDao {

    /**
     * 批量新增属性与商品关联
     * 
     * @auther zhangzheng
     * @date 2015-11-17 下午6:24:38
     * @param list
     */
    void insertGoodsProperties(List<GoodsProperty> list);

    /**
     * 根据商品id批量删除sku
     * 
     * @auther zhangzheng
     * @date 2015-11-19 下午5:46:42
     * @param paramMap
     * @return
     */
    int deleteByGoodsContentId(Map<String, Object> paramMap);

    /**
     * 查询商品、属性和属性值
     * 
     * @auther zhangzheng
     * @date 2016-1-21 下午2:37:43
     * @param paramMap
     * @return
     */
    List<PropertyAndPropertyValueTO> selectGoodsAndProperty(Map<String, Object> paramMap);
    
    /**
     * 查询商品、属性和属性值
     * 
     * @auther zhangzheng
     * @date 2016-1-21 下午2:37:43
     * @param paramMap
     * @return
     */
    List<PropertyTO> selectGoodsProperty(Map<String, Object> paramMap);

    /**
     * 批量删除
     * 
     * @auther zhangzheng
     * @date 2016-1-28 下午2:19:46
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    Integer deleteByContentIds(Map<String, Object> paramMap);

}
