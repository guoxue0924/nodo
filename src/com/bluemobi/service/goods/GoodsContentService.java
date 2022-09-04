package com.bluemobi.service.goods;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appcore.page.Page;
import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.to.goods.GoodsContentAndSkuTO;
import com.bluemobi.to.goods.GoodsContentFrontSearchTO;
import com.bluemobi.to.goods.GoodsContentTO;

/**
 * 【 商品详细内容，包括商品详情、meta 相关字段等】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-02 10:14:35
 * 
 */
public interface GoodsContentService extends MybatisBaseService {

    /**
     * 初始化商品信息，包括商品主表，sku表等信息
     * 
     * @author haojian
     * @date 2015-12-15 下午4:17:46
     * @return
     * @return int
     */
    int initGoodsContent();

    /**
     * 获取商品信息
     * 
     * @author haojian
     * @date 2015-12-15 下午4:18:43
     * @param id
     * @return
     * @return GoodsContent
     */
    GoodsContent selectGoodsContent(long id);

    /**
     * 将商品放入回收站
     * 
     * @auther zhangzheng
     * @date 2015-10-22 下午4:07:46
     * @param paramMap
     * @return
     */
    int deleteNotTrueByIds(Map<String, Object> paramMap);

    /**
     * 批量删除
     * 
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    int deleteByIds(Map<String, Object> paramMap);

    /**
     * 获取商品列表接口分页查询
     * 
     * @auther zhangzheng
     * @date 2015-10-22 下午5:03:59
     * @param paramMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> pageApi(Map<String, Object> paramMap, Integer pageIndex, Integer pageSize);

    /**
     * 根据商品ID和商品的父ID查询商品
     * 
     * @auther zhangzheng
     * @date 2015-10-23 下午5:08:43
     * @param paramMap
     * @return 商品的list数组
     */
    List<GoodsContent> selectObjectListByIdAndParentId(Map<String, Object> paramMap);

    /**
     * 获取新增商品时用于关联的商品列表
     * 
     * @auther zhangzheng
     * @date 2015-10-22 下午5:03:59
     * @param paramMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> pageGoodsContent(Map<String, Object> paramMap, Integer pageIndex, Integer pageSize);

    /**
     * 查询商品和商品sku信息
     * 
     * @auther zhangzheng
     * @date 2015-11-19 下午2:51:45
     * @param paramMap
     * @return
     */
    List<GoodsContentAndSkuTO> selectAllContentAndSku(Map<String, Object> paramMap);

    /**
     * 根据skuId查询商品和商品sku信息
     * 
     * @auther zhangzheng
     * @date 2016-2-2 下午4:02:47
     * @param paramMap
     * @return
     */
    GoodsContentAndSkuTO selectContentAndSku(Long skuId);

    /**
     * 新增商品信息
     * 
     * @auther zhangzheng
     * @date 2015-11-20 上午11:09:17
     * @param goodsContentTO
     * @return
     */
    Long insert(GoodsContentTO goodsContentTO, Date date, Integer userid) throws IllegalAccessException, InvocationTargetException;

    /**
     * 修改商品信息
     * 
     * @auther zhangzheng
     * @date 2015-11-20 上午11:09:50
     * @param goodsContentTO
     * @return
     */
    int update(GoodsContentTO goodsContentTO, Date date, Integer userid) throws IllegalAccessException, InvocationTargetException;

    /**
     * 获取skuid,可以根据实际业务规则修改生成方式以符合业务需求
     * 
     * @auther zhangzheng
     * @date 2015-11-3 下午4:46:11
     * @return
     */
    String getSku();

    /**
     * 活动中商品信息查询
     * 
     * @auther zhangzheng
     * @date 2016-1-12 下午4:59:50
     * @param pageIndex
     * @param pageSize
     * @param paramMap
     * @return
     */
    Page<Map<String, Object>> pageByActivity(Integer pageIndex, Integer pageSize, Map<String, Object> paramMap);

    /**
     * 根据skuid获取默认图片地址
     * 
     * @auther zhangzheng
     * @date 2016-1-20 下午3:14:28
     * @param skuId
     * @return
     */
    String getDefaultImage(Long skuId);

    /**
     * 查询首页的商品头条
     * 
     * @auther zhangzheng
     * @date 2016-1-18 下午4:45:02
     * @return
     */
    List<Map<String, Object>> getTopGoods(Map<String, Object> param, Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 获取热卖商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午1:30:04
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getHotSales(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 获取疯狂抢购商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午2:09:45
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getPanicBuying(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 获取猜您喜欢商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午2:10:11
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getGuessYouWouldLike(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 获取热评商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午2:10:23
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getHotReview(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 获取新品上架商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午2:10:35
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getNewGoods(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 获得首页上部右侧近期抢购商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午4:56:01
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getNearPanicBuying(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 获得限时折扣商品
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午4:57:30
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getSpecialDiscount(Map<String, Object> param, Integer pageIndex, Integer pageSize);

    /**
     * 前台搜索商品
     * 
     * @auther zhangzheng
     * @date 2016-2-18 下午3:59:57
     * @param pageIndex
     *            当前页码
     * @param pageSize
     *            每页条数
     * @param keyword
     *            关键字
     * @param categoryId
     *            分类ID
     * @param sort
     *            排序规则
     * @return
     */
    Page<GoodsContentFrontSearchTO> selectGoodsFrontSearchResult(Integer pageIndex, Integer pageSize, String keyword, Integer categoryId, String sort,Integer brandId) throws Exception;
    
    /**
     * 搜索热销商品
     * 
     * @auther zhangzheng
     * @date 2016-3-11 下午2:52:58
     * @param param
     * @return
     */
    List<Map<String, Object>> selectHotSales(Map<String, Object> param);

    /**
     * 搜索热门收藏商品
     * 
     * @auther zhangzheng
     * @date 2016-3-11 下午2:53:30
     * @param param
     * @return
     */
    List<Map<String, Object>> selectHotCollectList(Map<String, Object> param);

}
