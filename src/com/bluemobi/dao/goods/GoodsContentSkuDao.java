package com.bluemobi.dao.goods;

import java.util.List;
import java.util.Map;
import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.goods.GoodsContentSku;

/**
 * 【商品主表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 17:42:48
 * 
 */
/**
 * @author Administrator
 *
 */
public interface GoodsContentSkuDao extends MyBatisBaseDao {

    List<GoodsContentSku> selectFromCart(Map<String, Object> parameter);

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
     * 批量删除
     * 
     * @auther zhangzheng
     * @date 2016-1-28 下午2:22:49
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    Integer deleteByGoodsContentIds(Map<String, Object> paramMap);

    /**
     * 根据skuID将sku状态改为已删除
     * 
     * @auther zhangzheng
     * @date 2016-3-15 下午3:04:28
     * @param skuIds
     *            skuid，如：“1,2,3”
     * @return
     */
    int deleteSkus(String skuIds);

    /**
     * 查询用户是否已收藏过此商品
     * selectObject
     * @auther zhangzheng
     * @date 2016-3-23 下午2:08:43
     * @param param
     * @return 0：未收藏；1：已收藏；
     */
    int selectSkuIsFavorite(Map<String, Object> param);
    
//    /**
//     * 查询商品详情（价格方面）
//     * selectObject
//     * @auther fxz
//     * @date 2016-7-6 
//     * @param parameter
//     * @return 
//     */
//    GoodsContentSku selectObject(Map<String, Object> parameter);

}
