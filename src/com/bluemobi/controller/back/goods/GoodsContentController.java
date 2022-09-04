package com.bluemobi.controller.back.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.page.Page;
import com.appcore.util.JsonUtil;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.constant.GoodsConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.goods.GoodsCategory;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.po.trend.TrendPropertyValue;
import com.bluemobi.service.goods.GoodsBrandCategoryService;
import com.bluemobi.service.goods.GoodsBrandService;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.store.StoreContentService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.service.trend.TrendPropertyService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.goods.GoodsContentSkuTO;
import com.bluemobi.to.goods.GoodsContentTO;
import com.bluemobi.to.trend.PropertyTO;
import com.bluemobi.to.trend.TrendPropertyValueTO;

/**
 * 商品管理
 * 
 * @author zhangzheng
 * @date 2015-9-23
 * 
 */
@Controller
@RequestMapping("back/goodsContent")
public class GoodsContentController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsContentController.class);

    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private GoodsBrandService goodsBrandService;
    @Autowired
    private GoodsBrandCategoryService goodsBrandCategoryService;
    @Autowired
    private StoreContentService storeContentService;
    @Autowired
    private TrendPropertyService trendPropertyService;
    @Autowired
    private TrendAttachmentService trendAttachmentService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private JedisPoolManager jedisPoolManager;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    private static final String CATEGORIES = "categories";
    private static final String BRANDS = "brands";
    private static final String CATEGORY_ID = "categoryId";
    private static final String BRAND_ID = "brandId";
    private static final String CTIME = "ctime";
    private static final String STATUS = "status";

    /**
     * 初始化商品列表页面
     * 
     * @auther zhangzheng
     * @date 2015-11-23 下午2:55:04
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        Jedis jedis = null;
        List<GoodsCategory> categories = null;
        try {
            // 加载公共数据
            initIndex(model);
            jedis = jedisPoolManager.getResource();
            // 获取所有分类信息
            String allCategories = jedis.get("getAllCategories");
            if (allCategories != null && !"".equals(allCategories)) {
                categories = JsonUtil.getObject(allCategories, List.class);
            } else {
                categories = goodsCategoryService.getAllCategories();
                jedis.set("getAllCategories", JsonUtil.getJsonString(categories));
            }
            model.addAttribute(CATEGORIES, categories);
            // 所有品牌信息
            model.addAttribute(BRANDS, goodsBrandService.selectObjectList(null));
            // 所有店铺信息
            model.addAttribute("stores", storeContentService.selectObjectList(null));
            LOGGER.info("用户【{}】初始化商品列表页面", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】初始化商品列表页面失败。Exception:【{}】", new Object[] { this.getUserid(), e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return "back/goods/content.index";
    }

    /**
     * 分页查询商品信息
     * 
     * @auther zhangzheng
     * @date 2015-10-22 下午4:39:21
     * @param pageIndex
     * @param pageSize
     * @param key
     * @param categoryId
     * @param brandId
     * @param isShelf
     * @param priceMin
     * @param priceMax
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(Integer pageIndex, Integer pageSize, String key, Integer categoryId, Integer brandId, Integer isShelf, Integer priceMin, Integer priceMax) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (key != null && !"".equals(key)) {
            param.put("key", key);
        }
        if (categoryId != null && categoryId != 0) {
            param.put(CATEGORY_ID, categoryId);
        }
        if (brandId != null && brandId != 0) {
            param.put(BRAND_ID, brandId);
        }
        if (isShelf != null && isShelf != -1) {
            param.put("isShelf", isShelf);
        }
        if (priceMin != null && priceMin > 0) {
            param.put("priceMin", priceMin);
        }
        if (priceMax != null && priceMax > 0) {
            param.put("priceMax", priceMax);
        }
        param.put("isDel", (byte) 0);
        param.put("userid", this.getUserid());
        Page<Map<String, Object>> page = goodsContentService.page(param, pageIndex, pageSize);

        // 数据格式组装
        Collection<Map<String, Object>> pageData = page.getData();
        List<Map<String, Object>> resultDataList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : pageData) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.putAll(data);
            String timeStr = data.get(CTIME).toString();
            if (timeStr != null && !"".equals(timeStr)) {
                map.put(CTIME, timeStr);
            }
            resultDataList.add(map);
        }
        page.setData(resultDataList);
        LOGGER.info("用户【{}】根据关键字【{}】分类【{}】品牌【{}】是否上架【{}】最低价【{}】最高价【{}】页码【{}】每页最大数【{}】分页查询商品信息", new Object[] { this.getUserid(), key, categoryId, brandId, isShelf, priceMin,
                priceMax, pageIndex, pageSize });
        return page;
    }

    /**
     * 初始化商品新增页面
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:07:51
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            // 加载公共数据
            initIndex(model);
            // 获取所有分类信息
            String allCategories = jedis.get("getAllCategories");
            List<Map<String, Object>> categories = JsonUtil.getObject(allCategories, List.class);
            model.addAttribute(CATEGORIES, categories);
            LOGGER.info("用户【{}】初始化商品新增页面", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】初始化商品新增页面失败。Exception:【{}】", new Object[] { this.getUserid(), e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return "back/goods/content.edit";
    }

    /**
     * 根据分类id获取品牌列表
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:09:58
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "getBrandByCategoryId", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBrandByCategoryId(Integer categoryId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(CATEGORY_ID, categoryId);
        List<Map<String, Object>> brands = goodsBrandCategoryService.selectBrandByCategoryId(param);
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put(BRANDS, brands);
        mapResult.put(STATUS, 0);
        LOGGER.info("用户【{}】根据分类【{}】获取品牌列表", new Object[] { this.getUserid(), categoryId });
        return mapResult;
    }

    /**
     * 根据分类获取相关联的属性信息
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:17:29
     * @param model
     * @param categoryId
     * @param isSpec
     * @return
     */
    @RequestMapping(value = "getPropertyByCategoryId", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getPropertyByCategoryId(Model model, Integer categoryId, Integer isSpec) {
        Map<String, Object> param = new Hashtable<String, Object>();
        if (categoryId != null) {
            param.put("categoryId", categoryId);
        }
        Map<String, Object> properties = null;
        try {
            properties = trendPropertyService.selectPropertyByCategoryId(param);
            LOGGER.info("用户【{}】根据分类【{}】获取相关联的属性信息成功", new Object[] { this.getUserid(), categoryId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】根据分类【{}】获取相关联的属性信息失败。Exception:【{}】", new Object[] { this.getUserid(), categoryId, e });
            return ResultTO.newFailResultTO("查询属性信息错误！", null);
        }
        return ResultTO.newSuccessResultTO(null, properties);
    }

    /**
     * 新增商品时选项关联商品的分页查询
     * 
     * @auther zhangzheng
     * @date 2015-10-27 下午4:04:28
     * @param brandId
     * @param categoryId
     * @param key
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "storeAddPage", method = RequestMethod.GET)
    @ResponseBody
    public Page<Map<String, Object>> storeAddPage(Integer brandId, Integer categoryId, String key, Integer pageIndex, Integer pageSize) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (brandId != null && brandId > 0) {
            param.put("brandId", brandId);
        }
        if (categoryId != null && categoryId > 0) {
            param.put("categoryId", categoryId);
        }
        if (key != null && !"".equals(key)) {
            param.put("key", key);
        }
        Page<Map<String, Object>> page = goodsContentService.page(param, pageIndex, pageSize);
        LOGGER.info("用户【{}】在新增商品页面根据分类【{}】品牌【{}】关键字【{}】页码【{}】每页数量【{}】关联商品的分页查询", new Object[] { this.getUserid(), categoryId, brandId, key, pageIndex, pageSize });
        return page;
    }

    /**
     * 新增商品
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:18:23
     * @param goodsContentTO
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addGoodsContent(@ModelAttribute GoodsContentTO goodsContentTO, BindingResult result) {
        Date date = new Date();
        try {
            Long contentId = goodsContentService.insert(goodsContentTO, date, this.getUserid());
            LOGGER.info("用户【{}】新增商品【{}】成功", new Object[] { this.getUserid(), contentId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增商品失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newFailResultTO("新增失败", null);
        }
        return ResultTO.newSuccessResultTO("新增成功", null);
    }

    /**
     * 初始化商品修改页面
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:11:46
     * @param model
     * @param contentId
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Long contentId) {
        Map<String, Object> param = new HashMap<String, Object>();
        List<PropertyTO> properties = null;
        GoodsContent goodsContent = null;
        List<GoodsContentSku> contentSkus = null;
        GoodsContentSku goodsContentSku = null;
        GoodsContentSkuTO contentSkuTO = null;
        PropertyTO propertyTO = null;
        TrendPropertyValueTO propertyValueTo = null;
        List<TrendPropertyValueTO> propertyValueTos = null;
        List<GoodsContentSkuTO> contentSkuTos = new ArrayList<GoodsContentSkuTO>();
        List<TrendAttachment> contentAttachment = null;
        String property = "";
        Jedis jedis = null;
        List<GoodsCategory> categories = null;
        try {
            jedis = jedisPoolManager.getResource();
            // 获取所有分类信息
            String allCategories = jedis.get("getAllCategories");
            if (allCategories != null && !"".equals(allCategories)) {
                categories = JsonUtil.getObject(allCategories, List.class);
            } else {
                categories = goodsCategoryService.getAllCategories();
                jedis.set("getAllCategories", JsonUtil.getJsonString(categories));
            }
            model.addAttribute(CATEGORIES, categories);
        } catch (Exception e) {
            categories = goodsCategoryService.getAllCategories();
            model.addAttribute(CATEGORIES, categories);
            LOGGER.error("用户【{}】从redis中获取商品分类失败。Exception:【{}】", new Object[] { this.getUserid(), e });
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        try {
            // 加载公共数据
            initIndex(model);

            // 查询商品信息 content
            param.put("contentId", contentId);
            param.put("isDel", 0);
            goodsContent = goodsContentService.selectObject(param);

            // 查询商品属性信息 content, goods_property, trend_property,
            // trend_property_value
            properties = trendPropertyService.selectPropertyByGoodsContentId(param);

            // 根据商品查询商品sku信息 content, content_sku
            contentSkus = goodsContentSkuService.selectObjectList(param);
            if (contentSkus != null && !contentSkus.isEmpty()) {
                for (int i = 0; i < contentSkus.size(); i++) {
                    contentSkuTO = new GoodsContentSkuTO();
                    propertyValueTos = new ArrayList<TrendPropertyValueTO>();
                    goodsContentSku = contentSkus.get(i);
                    BeanUtils.copyProperties(contentSkuTO, goodsContentSku);
                    property = goodsContentSku.getProperty();
                    String[] propertyIds = property.split(",");

                    for (int j = 0; j < propertyIds.length; j++) {
                        String[] strs = propertyIds[j].split("_");

                        for (int k = 0; k < properties.size(); k++) {
                            propertyValueTo = new TrendPropertyValueTO();
                            propertyTO = properties.get(k);
                            // 根据sku查询sku的属性 content_sku(property),
                            // trend_property,
                            if (strs[0].equals(propertyTO.getPropertyId().toString())) {
                                propertyValueTo.setPropertyId(propertyTO.getPropertyId());
                                List<TrendPropertyValue> list = propertyTO.getPropertyValues();

                                for (int l = 0; l < list.size(); l++) {
                                    TrendPropertyValue value = list.get(l);
                                    if (strs[1].equals(value.getPropertyValueId().toString())) {
                                        propertyValueTo.setPropertyValueId(value.getPropertyValueId());
                                        propertyValueTo.setPropertyValue(value.getPropertyValue());
                                    }
                                }

                                propertyValueTos.add(propertyValueTo);
                            }
                        }

                        contentSkuTO.setPropertyValueTos(propertyValueTos);
                    }

                    contentSkuTos.add(contentSkuTO);
                }
            }

            // 查询商品的图片信息
            if (contentSkus != null && !contentSkus.isEmpty()) {
                contentSkus.get(0);
                param.clear();
                String[] attachmentids = contentSkus.get(0).getAttachmentids().split(",");
                param.put("attachmentids", attachmentids);
                contentAttachment = trendAttachmentService.selectTrendAttachmentListByIds(param);
            }
            if (contentAttachment != null && !contentAttachment.isEmpty()) {
                model.addAttribute("contentAttachment", contentAttachment);
            }
            
            // 查询sku图片信息
            List<Map<String, Object>> skuImages = goodsContentSkuService.selectGoodsContentSkuImages(contentId);
            System.out.println(JsonUtil.getJsonString(skuImages));
            model.addAttribute("skuImages", JsonUtil.getJsonString(skuImages));
        } catch (IllegalAccessException e) {
            LOGGER.error("用户【{}】初始化商品【{}】修改页面失败。IllegalAccessException:【{}】", new Object[] { this.getUserid(), contentId, e });
        } catch (InvocationTargetException e) {
            LOGGER.error("用户【{}】初始化商品【{}】修改页面失败。InvocationTargetException:【{}】", new Object[] { this.getUserid(), contentId, e });
        } catch (Exception e) {
            LOGGER.error("用户【{}】初始化商品【{}】修改页面失败。Exception:【{}】", new Object[] { this.getUserid(), contentId, e });
        }

        // 页面拿到的list是字符串，把属性转成页面需要的再传到页面
        // 这里需要修改
        StringBuffer valueId = new StringBuffer();
        for (int i = 0; i < properties.size(); i++) {
            propertyTO = new PropertyTO();
            propertyTO = properties.get(i);
            List<TrendPropertyValue> valueList = propertyTO.getPropertyValues();
            for (int j = 0; j < valueList.size(); j++) {
                TrendPropertyValue propertyValue = valueList.get(j);
                valueId.append(propertyValue.getPropertyValueId() + ",");
            }
            // otherId.append()
        }
        if (valueId != null && valueId.length() > 0) {
            valueId.deleteCharAt(valueId.length() - 1);
        }

        model.addAttribute("contentId", contentId);
        // 属性值ID，多个属性值之间用逗号分隔的字符串
        model.addAttribute("valueId", valueId);
        model.addAttribute("content", goodsContent);
        // 根据商品ID查询得到的商品属性的list
        model.addAttribute("properties", properties);
        // 根据商品ID查询得到的商品sku的list
        model.addAttribute("contentSkus", contentSkus);
        // 生成table的行的对象集合
        // 页面动态table每行的sku信息，在后台进行数据封装并转换成json保存在页面隐藏域中
        JSONArray jsonArray = JSONArray.fromObject(contentSkuTos);
        model.addAttribute("contentSkuTos", jsonArray);
        LOGGER.info("用户【{}】初始化商品【{}】修改页面", new Object[] { this.getUserid(), contentId });
        return "back/goods/content.edit";
    }

    /**
     * 修改商品
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:18:49
     * @param goodsContentTO
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editGoodsContent(@ModelAttribute GoodsContentTO goodsContentTO, BindingResult result) {
        Date date = new Date();
        try {
            goodsContentService.update(goodsContentTO, date, this.getUserid());
            LOGGER.info("用户【{}】修改商品【{}】成功", new Object[] { this.getUserid(), goodsContentTO.getContentId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改商品【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), goodsContentTO.getContentId(), e });
            return ResultTO.newFailResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 将商品放入回收站
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:12:49
     * @param contentIds
     * @return
     */
    @RequestMapping(value = "trash", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteGoodsContent(String contentIds) {
        Date date = new Date();
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            String[] ids = contentIds.split(",");
            param.put("mtime", date);
            param.put("contentIds", ids);
            goodsContentService.deleteNotTrueByIds(param);
            LOGGER.info("用户【{}】将商品【{}】放入回收站成功", new Object[] { this.getUserid(), contentIds });
        } catch (Exception e) {
            LOGGER.error("用户【{}】将商品【{}】放入回收站失败。Exception:【{}】", new Object[] { this.getUserid(), contentIds, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 修改上架状态
     * 
     * @auther zhangzheng
     * @date 2015-10-22 上午9:51:57
     * @param contentId
     *            商品ID
     * @param status
     *            当前的上架状态
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publish(Integer contentId, Byte status) {
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            param.put("contentId", contentId);
            if (status == 1) {
                param.put("isShelf", 0);
                param.put("offSaleTime", new Date());
            } else {
                param.put("isShelf", 1);
                param.put("onSaleTime", new Date());
            }
            goodsContentService.update(param);
            LOGGER.info("用户【{}】修改【{}】上架状态【{}】成功", new Object[] { this.getUserid(), contentId, status });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改【{}】上架状态【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), contentId, status, e });
            return ResultTO.newFailResultTO("上架失败", null);
        }
        return ResultTO.newSuccessResultTO("上架成功", null);
    }

    /**
     * 新增商品时上传图片功能
     * 
     * @auther zhangzheng
     * @date 2015-10-28 下午3:33:06
     * @param files
     *            上传的图片
     * @return map, status : 是否上传成功; data : 上传成功的图片信息
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam(value = "files", required = false) MultipartFile[] files) {
        Map<String, Object> uploadResultMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TrendAttachment trendAttachment = null;
        Long attachmengId = 0l;
        try {
            uploadResultMap = uploadImage(files, GoodsConstant.GOODS_CONTENT_IMAGE);
            if ((Boolean) uploadResultMap.get("flag")) {
                trendAttachment = new TrendAttachment();
                uploadResultMap.put("userid", this.getUserid());
                uploadResultMap.put("filesize", files[0].getSize()); // 单文件上传，获取数组第0个大小即可
                uploadResultMap.put("fileType", Byte.valueOf("1"));
                attachmengId = trendAttachmentService.insertUploadFile(uploadResultMap, trendAttachment);
            }
            LOGGER.info("用户【{}】上传图片【{}】成功", new Object[] { this.getUserid(), attachmengId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】上传图片失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            resultMap.put("status", BaseConstant.STATUS_FAILURE);
            return resultMap;
        }
        resultMap.put("status", BaseConstant.STATUS_SUCCESS);
        resultMap.put("data", trendAttachment);
        return resultMap;
    }
    
    /**
     * 上传sku图片
     */
    @RequestMapping(value = "uploadSkuImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadSkuImage(@RequestParam(value = "files", required = false) MultipartFile[] files, String inputId, String inputName) {
        Map<String, Object> uploadResultMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        TrendAttachment trendAttachment = null;
        Long attachmengId = 0l;
        try {
            uploadResultMap = uploadImage(files, GoodsConstant.GOODS_CONTENT_IMAGE);
            if ((Boolean) uploadResultMap.get("flag")) {
                trendAttachment = new TrendAttachment();
                uploadResultMap.put("userid", this.getUserid());
                uploadResultMap.put("filesize", files[0].getSize()); // 单文件上传，获取数组第0个大小即可
                uploadResultMap.put("fileType", Byte.valueOf("1"));
                attachmengId = trendAttachmentService.insertUploadFile(uploadResultMap, trendAttachment);
            }
            LOGGER.info("用户【{}】上传图片【{}】成功", new Object[] { this.getUserid(), attachmengId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】上传图片失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            resultMap.put("status", BaseConstant.STATUS_FAILURE);
            return resultMap;
        }
        resultMap.put("status", BaseConstant.STATUS_SUCCESS);
        Map<String, Object> map = null;
        try {
            map = com.bluemobi.util.BeanUtils.beanToMap(trendAttachment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propertyValueId = "";
        if (inputId != null && !"".equals(inputId)) {
            propertyValueId = inputId.substring(inputId.indexOf("_") + 1, inputId.length());
        }
        map.put("propertyValueId", propertyValueId);
        map.put("inputId", inputId);
        resultMap.put("data", map);
        return resultMap;
    }
    
    /**
     * 抢购活动查询商品相关信息
     * 
     * @auther zhangzheng
     * @date 2016-1-7 下午3:04:09
     * @param pageIndex
     * @param pageSize
     * @param key
     * @return
     */
    @RequestMapping(value = "pageByGrouponBulk", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> pageByGrouponBulk(Integer pageIndex, Integer pageSize, String key) {
        Map<String, Object> param = new HashMap<String, Object>();
        Page<Map<String, Object>> page = null;
        if (!StringUtils.isEmpty(key)) {
            param.put("key", key);
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 15;
        }
        try {
            page = goodsContentService.page("pageGoodsByGrouponBulk", "pageCountGoodsByGrouponBulk", param, pageIndex, pageSize);
            LOGGER.error("用户【{}】在抢购活动中查询商品信息成功！", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】在抢购活动中查询商品信息失败！Exception:【{}】", new Object[] { this.getUserid(), e });
        }
        return page;
    }

    /**
     * 活动查询商品相关信息
     * 
     * @auther zhangzheng
     * @date 2016-1-12 下午2:57:37
     * @param pageIndex
     * @param pageSize
     * @param categoryId
     * @param brandId
     * @param key
     * @return
     */
    @RequestMapping(value = "pageByActivity", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> pageByxActivity(Integer pageIndex, Integer pageSize, Integer categoryId, Integer brandId, String key) {
        Map<String, Object> param = new HashMap<String, Object>();
        Page<Map<String, Object>> page = null;
        if (categoryId != null && categoryId > 0) {
            param.put("categoryId", categoryId);
        }
        if (brandId != null && brandId > 0) {
            param.put("brandId", brandId);
        }
        if (!StringUtils.isEmpty(key)) {
            param.put("key", key);
        }
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 15;
        }
        try {
            page = goodsContentService.pageByActivity(pageIndex, pageSize, param);
            LOGGER.error("用户【{}】在活动中查询商品信息成功！", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】在活动中查询商品信息失败！Exception:【{}】", new Object[] { this.getUserid(), e });
        }
        return page;
    }

    /**
     * 获取skuid,可以根据实际业务规则修改生成方式以符合业务需求
     * 
     * @auther zhangzheng
     * @date 2015-11-3 下午4:46:11
     * @return
     */
    @RequestMapping(value = "getSku", method = RequestMethod.POST)
    @ResponseBody
    public String getSku() {
        return goodsContentService.getSku();
    }

    /**
     * 商品的文本编辑框图片上传功能
     * 
     * @auther zhangzheng
     * @date 2016-2-17 上午11:41:30
     * @param uploadFile
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "editUploadImage", method = RequestMethod.POST)
    @ResponseBody
    public String imgUpload(@RequestParam(value = "upload", required = false) MultipartFile[] uploadFile, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> uploadResultMap = new HashMap<String, Object>();
        TrendAttachment trendAttachment = null;
        Long attachmengId = 0l;
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        // CKEditor提交的很重要的一个参数
        String callback = request.getParameter("CKEditorFuncNum");
        try {
            uploadResultMap = uploadImage(uploadFile, GoodsConstant.GOODS_CONTENT_IMAGE, true);
            if ((Boolean) uploadResultMap.get("flag")) {
                trendAttachment = new TrendAttachment();
                uploadResultMap.put("userid", this.getUserid());
                uploadResultMap.put("filesize", uploadFile[0].getSize()); // 单文件上传，获取数组第0个大小即可
                uploadResultMap.put("fileType", Byte.valueOf("1"));
                attachmengId = trendAttachmentService.insertUploadFile(uploadResultMap, trendAttachment);
            }
            // 打印一段JS，调用parent页面的CKEditor的函数，传递函数编号和上传后文件的路径；
            out.println("<script type='text/javascript'>");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + BaseConstant.IMG_SERVER_URL + uploadResultMap.get("imageUrl") + "')");
            out.println("</script>");
            LOGGER.info("用户【{}】上传图片【{}】成功", new Object[] { this.getUserid(), attachmengId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】上传图片失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'上传图片失败！');");
            out.println("</script>");
        }
        return null;
    }

}
