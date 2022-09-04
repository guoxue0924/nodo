package com.bluemobi.serviceimpl.goods;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.constant.Symbol;
import com.appcore.dao.MyBatisBaseDao;
import com.appcore.page.Page;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.IDUtil;
import com.appcore.util.JsonUtil;
import com.appcore.util.StringUtil;
import com.bluemobi.apito.GoodsSkuSimplesTO;
import com.bluemobi.apito.PropertyTO;
import com.bluemobi.apito.SkuPropertyTO;
import com.bluemobi.constant.CategoryConstant;
import com.bluemobi.constant.GoodsConstant;
import com.bluemobi.dao.goods.GoodsContentDao;
import com.bluemobi.dao.goods.GoodsContentSkuDao;
import com.bluemobi.dao.goods.GoodsPropertyDao;
import com.bluemobi.dao.trend.TrendAttachmentDao;
import com.bluemobi.dao.trend.TrendPropertyDao;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.goods.GoodsProperty;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.protocol.api.GoodsContentProtocol;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.goods.GoodsContentAndSkuTO;
import com.bluemobi.to.goods.GoodsContentFrontSearchTO;
import com.bluemobi.to.goods.GoodsContentSkuFrontSearchTO;
import com.bluemobi.to.goods.GoodsContentTO;
import com.bluemobi.util.JedisUtil;

/**
 * 【 商品详细内容，包括商品详情、meta 相关字段等】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-02 10:14:35
 * 
 */
@Service(value = "goodsContentService")
public class GoodsContentServiceImpl extends MybatisBaseServiceImpl implements GoodsContentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsContentServiceImpl.class);

    @Autowired
    private GoodsContentDao goodsContentDao;
    @Autowired
    private GoodsContentSkuDao goodsContentSkuDao;
    @Autowired
    private GoodsPropertyDao goodsPropertyDao;
    @Autowired
    private JedisPoolManager jedisPoolManager;
    @Autowired
    private TrendPropertyDao trendPropertyDao;
    @Autowired
    private TrendAttachmentDao trendAttachmentDao;
    @Autowired
    private TrendAttachmentService trendAttachmentService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;

    @Override
    public MyBatisBaseDao getDao() {
        return goodsContentDao;
    }

    /**
     * 初始化商品信息，包括商品主表，sku表等信息
     * 
     * @author haojian
     * @date 2015-12-15 下午4:17:46
     * @return
     * @return int
     */
    @Override
    public int initGoodsContent() {
        Jedis jedis = null;
        // 商品主表
        List<GoodsContent> goodsList = null;

        try {
            jedis = jedisPoolManager.getResource();
            // 1、初始化商品主表信息
            LOGGER.info("启动服务器时候，初始化商品主表信息...");
            goodsList = goodsContentDao.selectObjectList(null);
            LOGGER.info("启动服务器时候，初始化商品主表信息结束，商品主表数据量【{}】...", new Object[] { goodsList.size() });

            for (GoodsContent goodsContent : goodsList) {
                initGoodsInRedis(jedis, goodsContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("启动服务器时候，初始化商品sku表信息失败。Exception:【{}】", new Object[] { e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }

        return goodsList.size();

    }

    /**
     * 将商品缓存到redis中
     * 
     * @auther zhangzheng
     * @date 2016-3-4 上午10:33:36
     * @param jedis
     * @param goodsContent
     */
    private void initGoodsInRedis(Jedis jedis, GoodsContent goodsContent) {
        List<GoodsContentSku> skuList;
        // 2、初始化商品的所有属性
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("contentId", goodsContent.getContentId());
        List<PropertyTO> propertyTOList = goodsPropertyDao.selectGoodsProperty(param);
        // 设置商品的所有属性
        goodsContent.setPropertyTOList(propertyTOList);

        // 3、初始化sku信息
        skuList = this.initSkuByGoodsContent(jedis, goodsContent);
        if (skuList.isEmpty()) {
            LOGGER.error("商品id【{}】,名称【{}】没有对应的sku！", new Object[] { goodsContent.getContentId(), goodsContent.getName() });
        }

        Map<Long, GoodsContentSku> skuMap = new HashMap<Long, GoodsContentSku>();
        // 创建skuMap，批量存储到redis
        for (GoodsContentSku sku : skuList) {
            skuMap.put(sku.getSkuId(), sku);
            // 将skuId放到商品主表下
            goodsContent.getSkuIdList().add(String.valueOf(sku.getSkuId()));

            // 4、将 商品id+sku属性字符串 作为key， skuId作为值，放入到缓存中
            JedisUtil.putObjectToMap(jedis, GoodsConstant.CACHE_PROPERTY_ID_AND_VALUE_MAP, sku.getContentId() + "_" + sku.getProperty(), sku.getSkuId());
        }

        // 5、将商品主表放到缓存中
        JedisUtil.putObjectToMap(jedis, GoodsConstant.CACHE_GOODS_CONTENT_MAP, goodsContent.getContentId(), goodsContent);
        // 6、将商品sku表放到缓存中
        JedisUtil.putObjectsToMap(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, skuMap);
        // 7、将商品主子表关联关系放到缓存中
        JedisUtil.putMainIdAndDetailIds(jedis, GoodsConstant.CACHE_GOODS_CONTENT_ID, goodsContent.getContentId(), goodsContent.getSkuIdList());
    }

    @SuppressWarnings("unused")
    private static void initGoodsSkuSimplesTO(Jedis jedis, GoodsContent goodsContent, List<GoodsContentSku> goodsContentSkuList) {

        GoodsSkuSimplesTO goodsSkuSimplesTO = GoodsContentProtocol.newGoodsSkuSimplesTO(goodsContentSkuList);

        JedisUtil.putObjectToMap(jedis, GoodsConstant.CACHE_GOODS_CONTENT_MAP, goodsContent.getContentId(), goodsSkuSimplesTO);

    }

    /**
     * 根据主商品初始化sku列表
     * 
     * @author haojian
     * @date 2016-1-30 下午4:55:11
     * @param jedis
     * @param goodsContent
     * @return
     * @return List<GoodsContentSku>
     */
    public List<GoodsContentSku> initSkuByGoodsContent(Jedis jedis, GoodsContent goodsContent) {

        // 1、根据商品id查询sku信息
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("contentId", goodsContent.getContentId());
        List<GoodsContentSku> skuList = goodsContentSkuDao.selectObjectList(paraMap);
        try {
            jedis = jedisPoolManager.getResource();
            for (GoodsContentSku sku : skuList) {
                // 3、设置商品名称
                sku.setName(goodsContent.getName());
                
                // 4、初始化sku自身的属性
                String property = sku.getProperty();
                String[] pp = property.split(",");
                
                for (String p : pp) {
                    String[] ss = p.split(Symbol.xiaHuaXian);
                    SkuPropertyTO skuPropertyTO = new SkuPropertyTO();
                    skuPropertyTO.setPropertyId(Integer.valueOf(ss[0]));
                    skuPropertyTO.setPropertyValueId(Integer.valueOf(ss[1]));
                    sku.getSkuPropertyTOList().add(skuPropertyTO);
                }
                
                // 5、初始化商品附件
                List<TrendAttachment> attachmentList = new ArrayList<TrendAttachment>();
                String attachmentids = sku.getAttachmentids();
                if (attachmentids != null && !"".equals(attachmentids)) {
                    int[] ii = StringUtil.stringToIntArray(attachmentids, Symbol.douHao);
                    Map<String, Object> paraMap3 = new HashMap<String, Object>();
                    paraMap3.clear();
                    paraMap3.put("attachmentids", ii);
                    attachmentList = trendAttachmentService.selectTrendAttachmentListByIds(paraMap3);
                }
                sku.setImages(trendAttachmentListToImages(attachmentList));
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return skuList;
    }

    /**
     * 将附件组转换成图片地址数组
     * 
     * @author haojian
     * @date 2016-2-20 下午2:55:41
     * @param list
     * @return
     * @return String[]
     */
    private static List<String> trendAttachmentListToImages(List<TrendAttachment> list) {
        List<String> images = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            TrendAttachment t = list.get(i);
            images.add(t.getFilepath());
        }
        return images;
    }

    /**
     * 获取商品信息
     * 
     * @author haojian
     * @date 2015-12-15 下午4:18:43
     * @param id
     * @return
     * @return GoodsContent
     */
    @Override
    public GoodsContent selectGoodsContent(long id) {
        GoodsContent goodsContent = null;
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            goodsContent = JedisUtil.getObjectFromMapById(jedis, GoodsConstant.CACHE_GOODS_CONTENT_MAP, String.valueOf(id), GoodsContent.class);
            LOGGER.info("从redis中获取到id为【{}】的商品信息：【{}】", new Object[] { id, goodsContent });
            if (goodsContent == null) {
                // 去数据库中查找商品信息，并放入到redis
                goodsContent = goodsContentDao.selectObject(id);
                LOGGER.info("从数据库中获取到id为【{}】的商品信息：【{}】，并将其存放到redis中", new Object[] { id, goodsContent });
                JedisUtil.putObjectToMap(jedis, GoodsConstant.CACHE_GOODS_CONTENT_MAP, goodsContent.getContentId().toString(), goodsContent);

                // 此处还需初始化跟商品主表关联的sku信息

            }
        } catch (Exception e) {
            LOGGER.error("查询商品信息失败。Exception:【{}】", new Object[] { e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return goodsContent;
    }

    @Override
    public int deleteNotTrueByIds(Map<String, Object> paramMap) {
        return goodsContentDao.deleteNotTrueByIds(paramMap);
    }

    @Override
    public int deleteByIds(Map<String, Object> paramMap) {
        Jedis jedis = null;
        GoodsContent goodsContent = null;
        try {
            jedis = jedisPoolManager.getResource();
            if (paramMap != null && paramMap.get("contentIds") != null) {
                String[] ids = (String[]) paramMap.get("contentIds");
                if (ids != null && ids.length > 0) {
                    for (int i = 0; i < ids.length; i++) {
                        goodsContent = selectGoodsContent(Long.valueOf(ids[i]));
                        deleteContentIdAndSkuPropertyRedis(jedis, goodsContent);
                        jedis.hdel(GoodsConstant.CACHE_GOODS_CONTENT_MAP, ids[i]);
                        jedis.hdel(GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, ids[i]);
                        jedis.del(GoodsConstant.CACHE_GOODS_CONTENT_ID + ids[i]);
                    }
                }
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        // 删除属性关联表
        int i = goodsPropertyDao.deleteByContentIds(paramMap);
        // 删除sku
        int j = goodsContentSkuDao.deleteByGoodsContentIds(paramMap);
        // 删除商品
        int k = goodsContentDao.deleteByIds(paramMap);
        return i + j + k;
    }

    /**
     * 删除redis中property_id_and_value_map下对应的缓存
     * 
     * @auther zhangzheng
     * @date 2016-3-4 上午11:34:52
     * @param jedis
     * @param goodsContent
     */
    private void deleteContentIdAndSkuPropertyRedis(Jedis jedis, GoodsContent goodsContent) {
        List<GoodsContentSku> skuList;
        skuList = this.initSkuByGoodsContent(jedis, goodsContent);
        if (skuList.isEmpty()) {
            LOGGER.error("商品id【{}】,名称【{}】没有对应的sku！", new Object[] { goodsContent.getContentId(), goodsContent.getName() });
        }
        for (GoodsContentSku sku : skuList) {
            jedis.hdel(GoodsConstant.CACHE_PROPERTY_ID_AND_VALUE_MAP, sku.getContentId() + "_" + sku.getProperty());
        }
    }

    @Override
    public Page<Map<String, Object>> pageApi(Map<String, Object> paramMap, Integer pageIndex, Integer pageSize) {
        return goodsContentDao.pageApi(paramMap, pageIndex, pageSize);
    }

    @Override
    public List<GoodsContent> selectObjectListByIdAndParentId(Map<String, Object> paramMap) {
        return goodsContentDao.selectObjectListByIdAndParentId(paramMap);
    }

    @Override
    public Page<Map<String, Object>> pageGoodsContent(Map<String, Object> paramMap, Integer pageIndex, Integer pageSize) {
        return goodsContentDao.pageGoodsContent(paramMap, pageIndex, pageSize);
    }

    @Override
    public List<GoodsContentAndSkuTO> selectAllContentAndSku(Map<String, Object> paramMap) {
        return goodsContentDao.selectAllContentAndSku(paramMap);
    }

    @Override
    public GoodsContentAndSkuTO selectContentAndSku(Long skuId) {
        GoodsContentAndSkuTO contentAndSkuTO = new GoodsContentAndSkuTO();
        GoodsContentSku sku = goodsContentSkuService.selectGoodsContentSku(skuId);
        GoodsContent content = selectGoodsContent(sku.getContentId().intValue());
        try {
            BeanUtils.copyProperties(contentAndSkuTO, sku);
            contentAndSkuTO.setSkuCtime(sku.getCtime());
            contentAndSkuTO.setSkuMtime(sku.getMtime());
            contentAndSkuTO.setSkuIsDel(sku.getIsDel());
            contentAndSkuTO.setSkuSortOrder(sku.getSortOrder());
            BeanUtils.copyProperties(contentAndSkuTO, content);
            List<String> images = sku.getImages();
            if (!images.isEmpty()) {
                contentAndSkuTO.setFilepath(images.get(0));
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("封装GoodsContentAndSkuTO异常。Exception:【{}】", new Object[] { e });
        } catch (InvocationTargetException e) {
            LOGGER.error("封装GoodsContentAndSkuTO异常。Exception:【{}】", new Object[] { e });
        }
        return contentAndSkuTO;
    }

    @Override
    public Long insert(GoodsContentTO goodsContentTO, Date date, Integer userid) throws IllegalAccessException, InvocationTargetException {
        GoodsContent goodsContent = new GoodsContent();

        copyAndAddGoodsContentProperty(goodsContentTO, date, goodsContent, userid);
        goodsContentDao.insert(goodsContent);
        //1(新增完之后，goodsContent是否发生变化)

        addSkuAndProperty(goodsContentTO, date, goodsContent);
        // 更新缓存
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            initGoodsInRedis(jedis, goodsContent);
            //2(初始化缓存时，goodsContent是否和之前的值相同，应该是操作完数据库的值才对)
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return goodsContent.getContentId();
    }

    @Override
    public int update(GoodsContentTO goodsContentTO, Date date, Integer userid) throws IllegalAccessException, InvocationTargetException {
        Map<String, Object> param = new HashMap<String, Object>();
        GoodsContent goodsContent = new GoodsContent();

        copyAndAddGoodsContentProperty(goodsContentTO, date, goodsContent, userid);
        goodsContentDao.update(goodsContent);

        // 删除原来关联的属性
        param.put("contentId", goodsContent.getContentId());
        goodsPropertyDao.deleteByGoodsContentId(param);
//        goodsContentSkuDao.deleteByGoodsContentId(param);

        addSkuAndProperty(goodsContentTO, date, goodsContent);

        // 更新缓存
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            initGoodsInRedis(jedis, goodsContent);
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return 0;
    }

    /**
     * 添加商品sku和属性信息
     * 
     * @auther zhangzheng
     * @date 2015-11-24 下午1:20:54
     * @param goodsContentTO
     *            页面提交的商品to对象
     * @param date
     * @param goodsContent
     *            新增或修改的商品对象
     */
    private void addSkuAndProperty(GoodsContentTO goodsContentTO, Date date, GoodsContent goodsContent) {
        GoodsContentSku goodsContentSku = null;
        GoodsProperty goodsProperty = null;
        StringBuffer sb = null;
        StringBuffer attachmentid = null;
        if (goodsContentTO.getPropertyId() != null) {
            Integer[] stock = goodsContentTO.getStock();
            String[] weight = goodsContentTO.getWeight();
            String[] length = goodsContentTO.getLength();
            String[] wide = goodsContentTO.getWide();
            String[] height = goodsContentTO.getHeight();
            BigDecimal[] price = goodsContentTO.getPrice();
            //2016-07-10 张伟添加商品积分start
            Integer[] priceIntegral=goodsContentTO.getPriceIntegral();
            //2016-07-10 张伟添加商品积分end
            BigDecimal[] priceMarket = goodsContentTO.getPriceMarket();
            BigDecimal[] priceCost = goodsContentTO.getPriceCost();
            Integer[] propertyId = goodsContentTO.getPropertyId();
            Integer[] propertyValueId = goodsContentTO.getPropertyValueId();
            Integer[] propertyId2 = goodsContentTO.getPropertyId2();
            Integer[] propertyValueId2 = goodsContentTO.getPropertyValueId2();
            Integer[] propertyId3 = goodsContentTO.getPropertyId3();
            Integer[] propertyValueId3 = goodsContentTO.getPropertyValueId3();
            String[] properties = goodsContentTO.getProperty();
            // Integer[] attachmentids = goodsContentTO.getAttachmentid();
            String[] skuImagePropertyValueIds = goodsContentTO.getSkuImagePropertyValueIds();
            String[] skuImageAttachmentids = goodsContentTO.getSkuImageAttachmentids();
            Integer[] skuIds = goodsContentTO.getSkuId();

            // 拼装商品上传图片的id
            // if (attachmentids != null && attachmentids.length > 0) {
            // for (int i = 0; i < attachmentids.length; i++) {
            // attachmentid.append(attachmentids[i] + ",");
            // }
            // attachmentid.deleteCharAt(attachmentid.length() - 1);
            // }

            // 获取sku图片的属性值和已上传图片的附件id并拼装
            List<Map<String, Object>> skuImages = new ArrayList<Map<String, Object>>();
            Map<String, Object> skuImage = null;
            if (skuImagePropertyValueIds != null && skuImagePropertyValueIds.length > 0 && skuImageAttachmentids != null && skuImageAttachmentids.length > 0) {
                for (int i = 0; i < skuImageAttachmentids.length; i++) {
                    skuImage = new HashMap<String, Object>();
                    skuImage.put("attachmentid", skuImageAttachmentids[i]);
                    skuImage.put("propertyValueId", skuImagePropertyValueIds[i]);
                    skuImages.add(skuImage);
                }
            }

            // 拼装价格属性id和属性值id
            List<GoodsProperty> goodsProperties = new ArrayList<GoodsProperty>();
            if (propertyId != null && propertyId.length > 0) {
                addProperties(goodsContent, propertyId, propertyValueId, goodsProperties);
            }

            if (propertyId2 != null && propertyId2.length > 0) {
                addProperties(goodsContent, propertyId2, propertyValueId2, goodsProperties);
            }

            if (propertyId3 != null && propertyId3.length > 0) {
                addProperties(goodsContent, propertyId3, propertyValueId3, goodsProperties);
            }

            if (properties != null && properties.length > 0) {
                for (int i = 0; i < properties.length; i++) {
                    String[] pro = properties[i].split("_");
                    goodsProperty = new GoodsProperty();
                    goodsProperty.setContentId(goodsContent.getContentId());
                    goodsProperty.setPropertyId(Integer.valueOf(pro[0]));
                    goodsProperty.setPropertyValueId(Integer.valueOf(pro[1]));
                    goodsProperties.add(goodsProperty);
                }
            }

            if (goodsProperties != null && !goodsProperties.isEmpty()) {
                goodsPropertyDao.insertGoodsProperties(goodsProperties);
            }
            
            // 获取商品的所有skuId
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("contentId", goodsContentTO.getContentId());
            List<GoodsContentSku> list = goodsContentSkuDao.selectObjectList(map);
            Map<Integer, Integer> skuIdMap = new HashMap<Integer, Integer>();
            if (list != null && !list.isEmpty()) {
                for (GoodsContentSku sku : list) {
                    skuIdMap.put(sku.getSkuId().intValue(), sku.getSkuId().intValue());
                }
            }

            for (int i = 0; i < price.length; i++) {
                sb = new StringBuffer();
                attachmentid = new StringBuffer();
                goodsContentSku = new GoodsContentSku();
                if (propertyId != null) {
                    sb.append(propertyId[i] + "_" + propertyValueId[i] + ",");
                }
                if (propertyId2 != null) {
                    sb.append(propertyId2[i] + "_" + propertyValueId2[i] + ",");
                }
                if (propertyId3 != null) {
                    sb.append(propertyId3[i] + "_" + propertyValueId3[i] + ",");
                }
                if (sb != null && sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }

                // 拼装sku图片id
                for (int j = 0; j < skuImages.size(); j++) {
                    skuImage = skuImages.get(j);
                    if (propertyId != null) {
                        if (propertyValueId[i].equals(Integer.valueOf(skuImage.get("propertyValueId").toString()))) {
                            attachmentid.append(skuImage.get("attachmentid") + ",");
                        }
                    }
                    if (propertyId2 != null) {
                        if (propertyValueId2[i].equals(Integer.valueOf(skuImage.get("propertyValueId").toString()))) {
                            attachmentid.append(skuImage.get("attachmentid") + ",");
                        }
                    }
                    if (propertyId3 != null) {
                        if (propertyValueId3[i].equals(Integer.valueOf(skuImage.get("propertyValueId").toString()))) {
                            attachmentid.append(skuImage.get("attachmentid") + ",");
                        }
                    }
                }
                if (attachmentid.length() > 1) {
                    attachmentid.deleteCharAt(attachmentid.length() - 1);
                }

                goodsContentSku.setAttachmentids(attachmentid.toString());
                goodsContentSku.setContentId(goodsContent.getContentId());
                goodsContentSku.setStock(stock[i]);
                goodsContentSku.setProperty(sb.toString());
                goodsContentSku.setPrice(price[i]);
                goodsContentSku.setPriceIntegral(priceIntegral[i]);
                goodsContentSku.setPriceCost(priceCost[i]);
                goodsContentSku.setPriceMarket(priceMarket[i]);
                goodsContentSku.setWeight(weight[i]);
                goodsContentSku.setLength(length[i]);
                goodsContentSku.setWide(wide[i]);
                goodsContentSku.setHeight(height[i]);
                goodsContentSku.setIsDel(Byte.valueOf("0"));
                goodsContentSku.setIsShelf(Byte.valueOf("1"));
                goodsContentSku.setCtime(date);
                goodsContentSku.setMtime(date);
                goodsContentSku.setSku(getSku());
                
                if (skuIds != null && skuIds.length > 0 && skuIds[i] != 0) {
                    goodsContentSku.setSkuId(Long.valueOf(skuIds[i]));
                    goodsContentSkuDao.update(goodsContentSku);
                    if (skuIdMap.get(skuIds[i]) != null) {
                        skuIdMap.remove(skuIds[i]);
                    }
                    skuIdMap.remove(skuIds[i]);
                } else {
                    // 可以修改为批量新增
                    goodsContentSkuDao.insert(goodsContentSku);
                }
            }
         // 将所有删除的sku状态改为已删除
            StringBuffer delSkuId = new StringBuffer();
            for (Map.Entry<Integer, Integer> entry : skuIdMap.entrySet()) {
                delSkuId.append(entry.getValue() + ",");
            }
            if (delSkuId.length() > 0) {
                delSkuId.deleteCharAt(delSkuId.length() - 1);
            }
            goodsContentSkuDao.deleteSkus(delSkuId.toString());
        }
    }

    /**
     * 为商品类赋值
     * 
     * @auther zhangzheng
     * @date 2015-11-3 下午2:47:47
     * @param goodsContentTO
     * @param date
     * @param goodsContent
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void copyAndAddGoodsContentProperty(GoodsContentTO goodsContentTO, Date date, GoodsContent goodsContent, Integer userid) throws IllegalAccessException,
            InvocationTargetException {
        BeanUtils.copyProperties(goodsContent, goodsContentTO);
        goodsContent.setCtime(date);
        goodsContent.setMtime(date);
        goodsContent.setUserid(userid);
        goodsContent.setIsDel((byte) 0);
        goodsContent.setIsSpec((byte) 1);
        goodsContent.setIsShelf((byte) 0);
        goodsContent.setOffSaleTime(date);
    }

    /**
     * 将属性id和属性值id封装到GoodsProperty对象中，并添加到list数组中
     * 
     * @auther zhangzheng
     * @date 2015-11-17 下午6:29:17
     * @param goodsContent
     *            新增的商品对象
     * @param propertiesId
     *            属性id数组
     * @param propertiesValueId
     *            属性值id数组
     * @param goodsProperties
     *            封装GoodsProperty对象的数组
     */
    private void addProperties(GoodsContent goodsContent, Integer[] propertiesId, Integer[] propertiesValueId, List<GoodsProperty> goodsProperties) {
        GoodsProperty goodsProperty;
        for (int i = 0; i < propertiesId.length; i++) {
            goodsProperty = new GoodsProperty();
            goodsProperty.setContentId(goodsContent.getContentId());
            goodsProperty.setPropertyId(propertiesId[i]);
            goodsProperty.setPropertyValueId(propertiesValueId[i]);
            goodsProperties.add(goodsProperty);
        }
    }

    /**
     * 获取skuid,可以根据实际业务规则修改生成方式以符合业务需求
     * 
     * @auther zhangzheng
     * @date 2015-11-3 下午4:46:11
     * @return
     */
    public String getSku() {
        Long l = IDUtil.getId();
        return l.toString();
    }

    @Override
    public Page<Map<String, Object>> pageByActivity(Integer pageIndex, Integer pageSize, Map<String, Object> paramMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        StringBuffer sb = null;
        paramMap.put("offset", (pageIndex - 1) * pageSize);
        paramMap.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> data = goodsContentDao.pageByActivity(paramMap);
        Integer count = goodsContentDao.pageCountByActivity(paramMap);

        // 如果查询结果不为空，获取sku的property属性并解析，将属性名称和属性值拼装成字符串
        if (data != null && !data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                Map<String, Object> map = data.get(i);
                String property = (String) map.get("property");
                String[] s = property.split(",");
                sb = new StringBuffer();
                for (int j = 0; j < s.length; j++) {
                    if (s[j] != null && !"".equals(s[j])) {
                        String[] ss = s[j].split("_");
                        param.put("propertyId", ss[0]);
                        param.put("propertyValueId", ss[1]);
                        String name = trendPropertyDao.selectPropertyAndValueName(param);
                        sb.append(name + ",");
                    }
                }
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                data.get(i).put("property", sb.toString());
            }
        }

        page.setCount(count);
        page.setData(data);
        page.setPage(pageIndex);
        page.setPageSize(pageSize);
        return page;
    }

    public String getDefaultImage(Long skuId) {
        GoodsContentSku goodsContentSku = goodsContentSkuService.selectGoodsContentSku(skuId);
        return goodsContentSku.getImages().get(0);
    }

    public List<Map<String, Object>> getTopGoods(Map<String, Object> param, Integer pageIndex, Integer pageSize) throws Exception {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        Map<String, Object> map = null;
        List<Map<String, Object>> topGoods = new ArrayList<Map<String, Object>>();
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            List<String> allGoodsRedis = jedis.hvals(GoodsConstant.CACHE_GOODS_CONTENT_MAP);
            GoodsContent content = null;
            GoodsContentSku sku = null;
            if (allGoodsRedis != null && !allGoodsRedis.isEmpty()) {
                for (int i = 0; i < allGoodsRedis.size(); i++) {
                    map = new HashMap<String, Object>();
                    content = JsonUtil.getObject(allGoodsRedis.get(i), GoodsContent.class);
                    if (content != null && content.getIsDel() != null && content.getIsDel() == 1) {
                        continue;
                    }
                    map = com.bluemobi.util.BeanUtils.beanToMap(content);
                    List<GoodsContentSku> skus = goodsContentSkuService.selectGoodsContentSkuListByContentId(content.getContentId().intValue());
                    if (skus != null && !skus.isEmpty()) {
                        sku = skus.get(0);
                    }
                    if (skus != null && !sku.getImages().isEmpty()) {
                        map.put("attachment", sku.getImages().get(0));
                    }
                    map.put("skuId", sku.getSkuId());
                    topGoods.add(map);
                }
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return topGoods;
    }

    public List<Map<String, Object>> getHotSales(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
//        param.put("offset", (pageIndex - 1) * pageSize);
//        param.put("rows", pageSize);
//        param.put("isDel", 0);
//        List<Map<String, Object>> mapList = goodsContentDao.page(param);
//        Map<String, Object> map = null;
//        TrendAttachment attachment = null;
        List<Map<String, Object>> hotSales = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < mapList.size(); i++) {
//            map = mapList.get(i);
//            String attachmentids = map.get("attachmentids").toString();
//            String[] ids = attachmentids.split(",");
//            param.clear();
//            param.put("attachmentid", ids[0]);
//            attachment = trendAttachmentDao.selectObject(param);
//            if (attachment != null) {
//                map.put("attachment", attachment.getFilepath());
//            }
//            hotSales.add(map);
//        }
        return hotSales;
    }

    public List<Map<String, Object>> getPanicBuying(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        // param.put("offset", (pageIndex - 1) * pageSize);
        param.put("offset", 2);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        Map<String, Object> map = null;
        TrendAttachment attachment = null;
        List<Map<String, Object>> panicBuying = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String attachmentids = map.get("attachmentids").toString();
            String[] ids = attachmentids.split(",");
            param.clear();
            param.put("attachmentid", ids[0]);
            attachment = trendAttachmentDao.selectObject(param);
            if (attachment != null && attachment.getFilepath() != null){
                map.put("attachment", attachment.getFilepath());
            } else {
                map.put("attachment", "");
            }
            panicBuying.add(map);
        }
        return panicBuying;
    }

    public List<Map<String, Object>> getGuessYouWouldLike(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        Map<String, Object> map = null;
        TrendAttachment attachment = null;
        List<Map<String, Object>> guessYouWouldLike = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String attachmentids = map.get("attachmentids").toString();
            String[] ids = attachmentids.split(",");
            param.clear();
            param.put("attachmentid", ids[0]);
            attachment = trendAttachmentDao.selectObject(param);
            if (attachment != null && attachment.getFilepath() != null){
                map.put("attachment", attachment.getFilepath());
            } else {
                map.put("attachment", "");
            }
            guessYouWouldLike.add(map);
        }
        return guessYouWouldLike;
    }

    public List<Map<String, Object>> getHotReview(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        Map<String, Object> map = null;
        TrendAttachment attachment = null;
        List<Map<String, Object>> hotReview = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String attachmentids = map.get("attachmentids").toString();
            String[] ids = attachmentids.split(",");
            param.clear();
            param.put("attachmentid", ids[0]);
            attachment = trendAttachmentDao.selectObject(param);
            if (attachment != null && attachment.getFilepath() != null){
                map.put("attachment", attachment.getFilepath());
            } else {
                map.put("attachment", "");
            }
            hotReview.add(map);
        }
        return hotReview;
    }

    public List<Map<String, Object>> getNewGoods(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        Map<String, Object> map = null;
        TrendAttachment attachment = null;
        List<Map<String, Object>> newGoods = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String attachmentids = map.get("attachmentids").toString();
            String[] ids = attachmentids.split(",");
            param.clear();
            param.put("attachmentid", ids[0]);
            attachment = trendAttachmentDao.selectObject(param);
            if (attachment != null && attachment.getFilepath() != null){
                map.put("attachment", attachment.getFilepath());
            } else {
                map.put("attachment", "");
            }
            newGoods.add(map);
        }
        return newGoods;
    }

    public List<Map<String, Object>> getNearPanicBuying(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        Map<String, Object> map = null;
        TrendAttachment attachment = null;
        List<Map<String, Object>> nearPanicBuying = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String attachmentids = map.get("attachmentids").toString();
            String[] ids = attachmentids.split(",");
            param.clear();
            param.put("attachmentid", ids[0]);
            attachment = trendAttachmentDao.selectObject(param);
            map.put("attachment", attachment.getFilepath());
            nearPanicBuying.add(map);
        }
        return nearPanicBuying;
    }

    public List<Map<String, Object>> getSpecialDiscount(Map<String, Object> param, Integer pageIndex, Integer pageSize) {
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        param.put("isDel", 0);
        List<Map<String, Object>> mapList = goodsContentDao.page(param);
        Map<String, Object> map = null;
        TrendAttachment attachment = null;
        List<Map<String, Object>> specialDiscount = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String attachmentids = map.get("attachmentids").toString();
            String[] ids = attachmentids.split(",");
            param.clear();
            param.put("attachmentid", ids[0]);
            attachment = trendAttachmentDao.selectObject(param);
            map.put("attachment", attachment.getFilepath());
            specialDiscount.add(map);
        }
        return specialDiscount;
    }

    @Override
    public Page<GoodsContentFrontSearchTO> selectGoodsFrontSearchResult(Integer pageIndex, Integer pageSize, String keyword, Integer categoryId, String sort, Integer brandId) throws Exception {
        Page<GoodsContentFrontSearchTO> page = new Page<GoodsContentFrontSearchTO>();
        Map<String, Object> param = new HashMap<String, Object>();
        List<GoodsContentFrontSearchTO> searchList = new ArrayList<GoodsContentFrontSearchTO>();
        GoodsContentFrontSearchTO searchTO = null;
        GoodsContentSkuFrontSearchTO skuSearchTO = null;
        Map<String, Object> map = null;
        List<GoodsContentSku> skus = null;
        GoodsContentSku sku = null;

        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        if (keyword != null && !"".equals(keyword)) {
        	param.put("key", keyword);
        }
        if (brandId != null) {
            param.put("brandId", brandId);
        }
        if (categoryId != null) {
            Integer[] categoryIds = getCategoryIdsByCategoryId(categoryId);
            param.put("categoryIds", categoryIds);
        }
        param.put("isDel", 0);

        // 1、默认排序；
        // 2、销量从高到低；
        // 3、销量从低到高；
        // 4、价格从高到低；
        // 5、价格从低到高。
        if (sort == null) {
            param.put("orderByDefault", "orderByDefault");
        } else if ("1".equals(sort)) {
            param.put("orderByDefault", "orderByDefault");
        } else if ("2".equals(sort)) {
            param.put("orderBySales", "orderBySales");
            param.put("desc", "desc");
        } else if ("3".equals(sort)) {
            param.put("orderBySales", "orderBySales");
            param.put("asc", "asc");
        } else if ("4".equals(sort)) {
            param.put("orderByPrice", "orderByPrice");
            param.put("desc", "desc");
        } else if ("5".equals(sort)) {
            param.put("orderByPrice", "orderByPrice");
            param.put("asc", "asc");
        } else if ("salesVolume".equals(sort)) { // 按销量排序
            param.put("orderBySales", "orderBySales");
        } else if ("price".equals(sort)) { // 按价格排序
            param.put("orderByPrice", "orderByPrice");
        } else {
            param.put("orderByDefault", "orderByDefault");
        }

        List<Map<String, Object>> list = goodsContentDao.pageByFrontSearch(param);
        int count = goodsContentDao.pageCountByFrontSearch(param);

        for (int i = 0; i < list.size(); i++) {
            searchTO = new GoodsContentFrontSearchTO();
            map = list.get(i);
            Long contentId = (Long) map.get("contentId");
            searchTO.setContentId(contentId);
            searchTO.setName(map.get("name").toString());
            skus = goodsContentSkuService.selectGoodsContentSkuListByContentId(contentId);
            if (skus != null && !skus.isEmpty()) {
                for (int j = 0; j < skus.size(); j++) {
                    sku = skus.get(j);
                    String attachmentids = sku.getAttachmentids();
                    if (attachmentids != null && !"".equals(attachmentids)) {
                        int[] ids = StringUtil.stringToIntArray(attachmentids, Symbol.douHao);
                        param.clear();
                        param.put("attachmentids", ids);
                        List<TrendAttachment> attachments = trendAttachmentService.selectTrendAttachmentListByIds(param);
                        skuSearchTO = new GoodsContentSkuFrontSearchTO();
                        skuSearchTO.setSkuId(sku.getSkuId());
                        skuSearchTO.setPrice(sku.getPrice());
                        if (attachments.size() < 3) {
                            skuSearchTO.setFilepath(attachments.get(0).getFilepath());
                        } else {
                            skuSearchTO.setFilepath(attachments.get(j % 3).getFilepath());
                        }
                        searchTO.getSkus().add(skuSearchTO);
                    }
                }
            }
            searchList.add(searchTO);
        }

        page.setCount(count);
        page.setData(searchList);
        page.setPage(pageIndex);
        page.setPageSize(pageSize);
        return page;
    }

    /**
     * 根据分类ID获得此ID和此ID下的子ID
     * 
     * @auther zhangzheng
     * @date 2016-2-22 下午1:31:50
     * @param categoryId
     * @return 分类ID的数组
     */
    public Integer[] getCategoryIdsByCategoryId(Integer categoryId) {
        Jedis jedis = jedisPoolManager.getResource();
        List<String> list = null;
        Integer[] categoryIds = null;
        try {
            list = JedisUtil.getDetailIdsByMainId(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_AND_SON_ID, categoryId);
            categoryIds = JedisUtil.strCollectionToIntegerArray(list);
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return categoryIds;
    }

    @Override
    public List<Map<String, Object>> selectHotSales(Map<String, Object> param) {
        List<Map<String, Object>> list = goodsContentDao.selectHotSales(param);
        Map<String, Object> map = null;
        GoodsContentSku sku = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                map = list.get(i);
                sku = goodsContentSkuService.selectGoodsContentSku(Long.valueOf(map.get("skuId").toString()));
                if (sku.getImages() != null && !sku.getImages().isEmpty()) {
                    map.put("image", sku.getImages().get(0));
                } else {
                    map.put("image", "");
                }
                if (map.get("salesVolume") == null) {
                    map.put("salesVolume", 0);
                }
                list.set(i, map);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectHotCollectList(Map<String, Object> param) {
        List<Map<String, Object>> list = goodsContentDao.selectHotCollectList(param);
        Map<String, Object> map = null;
        GoodsContentSku sku = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                map = list.get(i);
                sku = goodsContentSkuService.selectGoodsContentSku(Long.valueOf(map.get("skuId").toString()));
                if (sku.getImages() != null && !sku.getImages().isEmpty()) {
                    map.put("image", sku.getImages().get(0));
                } else {
                    map.put("image", "");
                }
                list.set(i, map);
            }
        }
        return list;
    }

}
