package com.bluemobi.serviceimpl.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.apito.GoodsSkuSimpleTO;
import com.bluemobi.apito.GoodsSkuSimplesTO;
import com.bluemobi.apito.GoodsSkuTO;
import com.bluemobi.constant.GoodsConstant;
import com.bluemobi.dao.goods.GoodsContentDao;
import com.bluemobi.dao.goods.GoodsContentSkuDao;
import com.bluemobi.dao.trend.TrendAttachmentDao;
import com.bluemobi.dao.trend.TrendPropertyDao;
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.protocol.api.GoodsContentProtocol;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.util.JedisUtil;

/**
 * 【商品主表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-02 10:14:35
 * 
 */
@Service(value = "goodsContentSkuService")
public class GoodsContentSkuServiceImpl extends MybatisBaseServiceImpl implements GoodsContentSkuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsContentSkuServiceImpl.class);

    @Autowired
    private GoodsContentSkuDao goodsContentSkuDao;
    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private TrendAttachmentDao trendAttachmentDao;
    @Autowired
    private TrendPropertyDao trendPropertyDao;
    @Autowired
    private GoodsContentDao goodsContentDao;

    @Autowired
    private JedisPoolManager jedisPoolManager;

    @Override
    public MyBatisBaseDao getDao() {
        return goodsContentSkuDao;
    }

    /**
     * 获取商品sku信息
     * 
     * @author haojian
     * @date 2015-12-15 下午4:18:43
     * @param skuId
     * @return
     * @return GoodsContentSku
     */
    @Override
    public GoodsContentSku selectGoodsContentSku(long skuId) {

        Jedis jedis = null;
        GoodsContentSku goodsContentSku = null;

        try {
            jedis = jedisPoolManager.getResource();
            goodsContentSku = JedisUtil.getObjectFromMapById(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, String.valueOf(skuId), GoodsContentSku.class);
            LOGGER.info("从redis中获取到id为【{}】的商品sku信息：【{}】", new Object[] { skuId, goodsContentSku });
            if (goodsContentSku == null) {
                // 去数据库中查找商品信息，并放入到redis
//              goodsContentSku = goodsContentSkuDao.selectObject(skuId);
//              LOGGER.info("从数据库中获取到id为【{}】的商品sku信息：【{}】，并将其存放到redis中", new Object[] { skuId, goodsContentSku });
//              JedisUtil.putObjectToMap(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, goodsContentSku.getSkuId().toString(), goodsContentSku);
              goodsContentService.initGoodsContent();
            }
        } catch (Exception e) {
            LOGGER.error("查询商品sku信息失败。Exception:【{}】", new Object[] { e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }

        return goodsContentSku;
    }

    /**
     * 通过skuId获取sku详细 用于前端查询sku
     * 
     * @author haojian
     * @date 2016-2-20 下午3:55:30
     * @param skuId
     * @return
     * @return GoodsSkuTO
     */
    @Override
    public GoodsSkuTO getGoodsContentSku(long skuId) {

        GoodsContentSku goodsContentSku = this.selectGoodsContentSku(skuId);

        GoodsContent goodsContent = goodsContentService.selectGoodsContent(goodsContentSku.getContentId());

        GoodsSkuTO to = GoodsContentProtocol.newGoodsSkuTO(goodsContent, goodsContentSku);

        return to;

    }

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
    @Override
    public GoodsSkuTO getGoodsSkuByProperty(long contentId, String property) {

        Jedis jedis = null;
        long skuId = 0;
        try {
            jedis = jedisPoolManager.getResource();
            skuId = JedisUtil.getObjectFromMapById(jedis, GoodsConstant.CACHE_PROPERTY_ID_AND_VALUE_MAP, contentId + "_" + property, Long.class);

        } catch (Exception e) {
            LOGGER.error("查询商品sku信息失败。Exception:【{}】", new Object[] { e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }

        GoodsSkuTO to = this.getGoodsContentSku(skuId);

        return to;

    }

    /**
     * 根据商品id，获取商品sku信息列表
     * 
     * @author haojian
     * @date 2016-1-28 上午10:56:37
     * @param contentId
     * @return
     * @return List<GoodsContentSku>
     */
    @Override
    public List<GoodsContentSku> selectGoodsContentSkuListByContentId(long contentId) throws Exception {

        Jedis jedis = null;
        List<GoodsContentSku> skuList = null;

        try {
            jedis = jedisPoolManager.getResource();
            // JedisUtil.putMainIdAndDetailIds(jedis,
            // GoodsConstant.CACHE_GOODS_CONTENT_ID, goods.getContentId(),
            // goods.getSkuIdList());
            List<String> skuIdList = JedisUtil.getDetailIdsByMainId(jedis, GoodsConstant.CACHE_GOODS_CONTENT_ID, contentId);
            // JedisUtil.putObjectsToMap(jedis,
            // GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, skuMap);
            skuList = JedisUtil.getObjectsFromMapByIds(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, skuIdList, GoodsContentSku.class);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("初始化商品sku表信息失败。Exception:【{}】", new Object[] { e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }

        return skuList;

    }

    /**
     * 获取推荐商品列表 （测试数据，非正式） 用于手机端，网页前端展示
     * 
     * @author haojian
     * @date 2016-1-28 下午1:42:06
     * @return
     * @return List<GoodsContent>
     */
    @Override
    public List<GoodsContentSku> getDefultGoodsContentSkus() {
        List<GoodsContentSku> skuList = null;
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            // 获取所有的key，实际值需要部分
            Set<String> keySet = jedis.hkeys(GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP);

            skuList = JedisUtil.getObjectsFromMapByIds(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, keySet, GoodsContentSku.class);

        } catch (Exception e) {
            LOGGER.error("查询商品信息失败。Exception:【{}】", new Object[] { e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return skuList;
    }

    @Override
    public List<GoodsContentSku> selectSkuFromCart(List<BtsCart> cartLIst) {
        List<Integer> skuIdList = new ArrayList<Integer>();
        for (BtsCart cart : cartLIst) {
            skuIdList.add(cart.getSkuId().intValue());
        }
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("skuIds", skuIdList);
        return this.goodsContentSkuDao.selectFromCart(parameter);
    }

    @Override
    public int deleteByGoodsContentId(Map<String, Object> paramMap) {
        return goodsContentSkuDao.deleteByGoodsContentId(paramMap);
    }

    public Integer deleteByGoodsContentIds(Map<String, Object> paramMap) {
        return goodsContentSkuDao.deleteByGoodsContentIds(paramMap);
    }

    @Override
    public List<Map<String, Object>> selectGoodsContentSkuImages(Long contentId) throws Exception {
        List<Map<String, Object>> skuImages = new ArrayList<Map<String, Object>>();
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> attachmentParam = new HashMap<String, Object>();
        param.put("contentId", contentId);
        List<GoodsContentSku> contentSkus = goodsContentSkuDao.selectObjectList(param);
        GoodsContentSku sku = null;
        String attachmentids = "";
        List<TrendAttachment> attachments = null;
        TrendAttachment attachment = null;
        Map<String, Object> skuImage = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> hasSkuImage = new ArrayList<Map<String, Object>>();
        if (contentSkus != null && !contentSkus.isEmpty()) {
            for (int i = 0; i < contentSkus.size(); i++) {
                sku = contentSkus.get(i);
                // 得到图片属性的属性id和属性值id
                String property = sku.getProperty();
                String[] ss = property.split(",");
                for (int j = 0; j < ss.length; j++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String[] sss = ss[j].split("_");
                    map.put("propertyId", sss[0]);
                    map.put("propertyValueId", sss[1]);
                    list.add(map);
                }
            }

            // 将此sku的图片属性的属性id和属性值id存入hasSkuImage中
            for (int i = 0; i < list.size(); i++) {
                int isImage = trendPropertyDao.selectPropertyIsImage(Integer.valueOf(list.get(i).get("propertyId").toString()));
                if (isImage == 1) {
                    list.get(i).put("isImage", isImage);
                    hasSkuImage.add(list.get(i));
                }
            }

            // 组装sku图片结果
            for (int i = 0; i < contentSkus.size(); i++) {
                sku = contentSkus.get(i);

                // 获取这个sku的图片属性的属性值
                String property = sku.getProperty();
                String[] properties = property.split(",");
                boolean flag = false;
                String propertyId = "";
                String propertyValueId = "";
                for (int ii = 0; ii < properties.length; ii++) {
                    String[] strs = properties[ii].split("_");
                    if (!flag) {
                        for (int j = 0; j < hasSkuImage.size(); j++) {
                            Map<String, Object> map = hasSkuImage.get(j);
                            if (strs[0].equals(map.get("propertyId"))) {
                                flag = true;
                                propertyId = strs[0];
                                propertyValueId = strs[1];
                                break;
                            }
                        }
                    }
                }

                // 将附件信息转成map并添加propertyValueId值，最后添加到结果list中
                attachmentids = sku.getAttachmentids();
                attachmentParam.put("attachmentids", attachmentids.split(","));
                attachments = trendAttachmentDao.selectTrendAttachmentListByIds(attachmentParam);
                if (attachments != null && !attachments.isEmpty()) {
                    for (int j = 0; j < attachments.size(); j++) {
                        attachment = attachments.get(j);
                        // skuImage = BeanUtils.beanToMap(attachment);
                        skuImage = new HashMap<String, Object>();
                        skuImage.put("attachmentid", attachment.getAttachmentid());
                        skuImage.put("filepath", attachment.getFilepath());
                        skuImage.put("propertyValueId", propertyValueId);
                        skuImage.put("propertyId", propertyId);

                        boolean c = false;
                        for (int j2 = 0; j2 < skuImages.size(); j2++) {
                            Map<String, Object> check = skuImages.get(j2);
                            if (attachment.getFilepath().equals(check.get("filepath"))) {
                                c = true;
                                break;
                            }
                        }
                        if (!c) {
                            skuImages.add(skuImage);
                        }
                    }
                }
            }
        }
        return skuImages;
    }

    @Override
    public List<GoodsSkuSimpleTO> selectGoodsContentSkuAPISearch(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        GoodsSkuSimplesTO skuSimplesTO = null;
        List<GoodsSkuSimpleTO> result = new ArrayList<GoodsSkuSimpleTO>();
        try {
            if (mapList != null && !mapList.isEmpty()) {
                for (int i = 0; i < mapList.size(); i++) {
                    Map<String, Object> map = mapList.get(i);
                    List<GoodsContentSku> contentSkus;
                    contentSkus = selectGoodsContentSkuListByContentId(Long.valueOf(map.get("contentId").toString()));
                    if (contentSkus != null && !contentSkus.isEmpty()) {
                        skuSimplesTO = GoodsContentProtocol.newGoodsSkuSimplesTO(contentSkus);
                        if (skuSimplesTO != null && skuSimplesTO.getGoodsSkuSimple() != null && !skuSimplesTO.getGoodsSkuSimple().isEmpty()) {
                            result.add(skuSimplesTO.getGoodsSkuSimple().get(0));
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int selectSkuIsFavorite(Map<String, Object> param) {
        return goodsContentSkuDao.selectSkuIsFavorite(param);
    }
    
//    /**
//     * 查询商品详情（价格方面）
//     * selectObject
//     * @auther fxz
//     * @date 2016-7-6 
//     * @param parameter
//     * @return 
//     */
//    @Override
//    public GoodsContentSku selectObject(Integer skuId) {
//        Map<String, Object> parameter = new HashMap<String, Object>();
//        parameter.put("skuIds", skuId);
//        return goodsContentSkuDao.selectObject(parameter);
//    }
    
}
