package com.bluemobi.service.goods;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.apito.GoodsSkuSimpleTO;
import com.bluemobi.apito.GoodsSkuTO;
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.goods.GoodsContentSku;

/**
 * 【商品主表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-02 10:14:35
 * 
 */
public interface GoodsContentSkuService extends MybatisBaseService {

    /**
     * 获取商品sku信息
     * 
     * @author haojian
     * @date 2015-12-15 下午4:18:43
     * @param skuId
     * @return
     * @return GoodsContentSku
     */
    GoodsContentSku selectGoodsContentSku(long skuId);

    /**
     * 通过skuId获取sku详细 用于前端查询sku
     * 
     * @author haojian
     * @date 2016-2-20 下午3:55:30
     * @param skuId
     * @return
     * @return GoodsSkuTO
     */
    GoodsSkuTO getGoodsContentSku(long skuId);

    /**
     * 通过商品id和sku属性字符串，获取sku详情 用于前端查询sku
     * 
     * @author haojian
     * @date 2016-2-20 下午3:55:46
     * @param contentId
     * @param property
     * @return
     * @return GoodsSkuTO
     */
    GoodsSkuTO getGoodsSkuByProperty(long contentId, String property);

    /**
     * 根据商品id，获取商品sku信息列表
     * 
     * @author haojian
     * @date 2016-1-28 上午10:56:37
     * @param contentId
     * @return
     * @return List<GoodsContentSku>
     */
    List<GoodsContentSku> selectGoodsContentSkuListByContentId(long contentId) throws Exception;

    /**
     * 获取推荐商品列表 用于手机端，网页前端展示
     * 
     * @author haojian
     * @date 2016-1-28 下午1:42:06
     * @return
     * @return List<GoodsContentSku>
     */
    List<GoodsContentSku> getDefultGoodsContentSkus();

    /**
     * 根据给定的购物车, 查询购物车中的sku信息
     * 
     * @author liuyt
     * @date 2015-11-19 下午5:59:59
     * @param cartList
     * @return
     * @version
     */
    List<GoodsContentSku> selectSkuFromCart(List<BtsCart> cartList);

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
     * 查询sku图片信息
     * 
     * @auther zhangzheng
     * @date 2016-2-25 下午2:48:36
     * @param contentId
     * @return
     */
    List<Map<String, Object>> selectGoodsContentSkuImages(Long contentId) throws Exception;

    /**
     * 移动端搜索商品
     * 
     * @auther zhangzheng
     * @date 2016-2-29 上午10:22:05
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<GoodsSkuSimpleTO> selectGoodsContentSkuAPISearch(Map<String, Object> param, Integer pageIndex, Integer pageSize);
    
    /**
     * 查询用户是否已收藏过此商品
     * 
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
//	GoodsContentSku selectObject(Integer skuId);

}
