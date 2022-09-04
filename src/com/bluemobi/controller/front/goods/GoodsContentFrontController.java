package com.bluemobi.controller.front.goods;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.page.Page;
import com.bluemobi.constant.CategoryConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.po.trend.TrendPropertyValue;
import com.bluemobi.service.advert.AdvertContentService;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.service.cas.CasUserFavoriteService;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.goods.GoodsPropertyService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.comment.CommentDetailTO;
import com.bluemobi.to.advert.AdvertDetailTO;
import com.bluemobi.to.goods.GoodsContentFrontSearchTO;
import com.bluemobi.to.goods.GoodsContentSearchParamTO;
import com.bluemobi.to.goods.GoodsContentSkuFrontTO;
import com.bluemobi.to.trend.PropertyAndPropertyValueTO;
import com.bluemobi.to.trend.PropertyTO;
import com.bluemobi.util.JedisUtil;

@Controller
@RequestMapping("front/goodsContent")
public class GoodsContentFrontController extends AbstractAPIController {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsContentFrontController.class);

    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private TrendAttachmentService trendAttachmentService;
    @Autowired
    private GoodsPropertyService goodsPropertyService;
    @Autowired
    private CasUserFavoriteService casUserFavoriteService;
    @Autowired
    private CommentContentService commentContentService;
    @Autowired
    private AdvertContentService advertContentService;
    @Autowired
    private BtsOrderService btsOrderService;
    @Autowired
    private JedisPoolManager jedisPoolManager;

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String index(Model model, Long skuId) {
        Map<String, Object> param = new HashMap<String, Object>();
        Long contentId = 0l;
        GoodsContent content = null;
        List<GoodsContentSku> contentSkus = null;
        GoodsContentSku sku = null;
        GoodsContentSku skuDetail = null; // 用于页面展现的当前sku信息
        String attachmentids = "";
        List<TrendAttachment> attachments = null;
        List<PropertyAndPropertyValueTO> propertyAndPropertyValueTOs = null;
        PropertyAndPropertyValueTO propertyAndPropertyValueTO = null;
        List<PropertyTO> propertyTOs = new ArrayList<PropertyTO>();
        PropertyTO propertyTO = null;
        TrendPropertyValue propertyValue = null;
        List<TrendPropertyValue> propertyValues = null;
        int tempNewPropertyId = 0;
        int tempOldPropertyId = 0;
        GoodsContentSkuFrontTO skuFrontTO = null;
        List<GoodsContentSkuFrontTO> skuFrontTOs = new ArrayList<GoodsContentSkuFrontTO>();
        List<Integer> propertyIdList = new ArrayList<Integer>();
        List<Integer> propertyValueIdList = new ArrayList<Integer>();
        Integer favoriteCount = 0;
        Jedis jedis = null;
        String categoryUrl = "";
        try {
            param.put("skuId", skuId);
            skuDetail = goodsContentSkuService.selectObject(param);
            model.addAttribute("skuDetail", skuDetail);
            contentId = skuDetail.getContentId();
            param.clear();
            param.put("contentId", contentId);
            content = goodsContentService.selectObject(param);
            contentSkus = goodsContentSkuService.selectObjectList(param);
            if (contentSkus != null && !contentSkus.isEmpty()) {
                // 获取属性信息，从商品关联查，不从sku查
                propertyAndPropertyValueTOs = goodsPropertyService.selectGoodsAndProperty(param);
                if (propertyAndPropertyValueTOs != null && !propertyAndPropertyValueTOs.isEmpty()) {
                    for (int i = 0; i < propertyAndPropertyValueTOs.size(); i++) {
                        propertyAndPropertyValueTO = propertyAndPropertyValueTOs.get(i);
                        tempNewPropertyId = propertyAndPropertyValueTO.getPropertyId();
                        if (tempNewPropertyId != tempOldPropertyId) {
                            propertyTO = new PropertyTO();
                            propertyTO.setPropertyId(propertyAndPropertyValueTO.getPropertyId());
                            propertyTO.setLabelName(propertyAndPropertyValueTO.getLabelName());

                            propertyValue = new TrendPropertyValue();
                            propertyValue.setPropertyId(propertyAndPropertyValueTO.getPropertyId());
                            propertyValue.setPropertyValueId(propertyAndPropertyValueTO.getPropertyValueId());
                            propertyValue.setPropertyValue(propertyAndPropertyValueTO.getPropertyValue());
                            propertyValue.setPropertyImage(propertyAndPropertyValueTO.getPropertyImage());

                            propertyValues = new ArrayList<TrendPropertyValue>();
                            propertyValues.add(propertyValue);
                            propertyTO.setPropertyValues(propertyValues);

                            propertyTOs.add(propertyTO);

                            tempOldPropertyId = tempNewPropertyId;
                        } else {
                            propertyTO = propertyTOs.get(propertyTOs.size() - 1);
                            propertyValue = new TrendPropertyValue();
                            propertyValue.setPropertyId(propertyAndPropertyValueTO.getPropertyId());
                            propertyValue.setPropertyValueId(propertyAndPropertyValueTO.getPropertyValueId());
                            propertyValue.setPropertyValue(propertyAndPropertyValueTO.getPropertyValue());
                            propertyValue.setPropertyImage(propertyAndPropertyValueTO.getPropertyImage());

                            propertyTO.getPropertyValues().add(propertyValue);
                        }
                    }
                }
                // sku拆分属性信息，创建新的skuto，里面带上属性信息
                for (int i = 0; i < contentSkus.size(); i++) {
                    skuFrontTO = new GoodsContentSkuFrontTO();
                    sku = contentSkus.get(i);
                    BeanUtils.copyProperties(skuFrontTO, sku);
                    String property = sku.getProperty();
                    String[] propertyStr = property.split(",");
                    List<Integer> list1 = new ArrayList<Integer>();
                    List<Integer> list2 = new ArrayList<Integer>();
                    for (int j = 0; j < propertyStr.length; j++) {
                        String str = propertyStr[j];
                        String[] strs = str.split("_");
                        if (skuId.equals(sku.getSkuId())) {
                            propertyIdList.add(Integer.valueOf(strs[0]));
                            propertyValueIdList.add(Integer.valueOf(strs[1]));
                        }
                        list1.add(Integer.valueOf(strs[0]));
                        list2.add(Integer.valueOf(strs[1]));
                    }
                    skuFrontTO.setPropertyIds(list1);
                    skuFrontTO.setPropertyValueIds(list2);
                    skuFrontTOs.add(skuFrontTO);
                }

                // 获取图片信息
                sku = contentSkus.get(0);
                attachmentids = sku.getAttachmentids();
                if (attachmentids != null && !"".equals(attachmentids)) {
                    String[] str = attachmentids.split(",");
                    param.clear();
                    param.put("attachmentids", str);
                    attachments = trendAttachmentService.selectTrendAttachmentListByIds(param);
                }
            }

            favoriteCount = casUserFavoriteService.countsByskuId(skuId);

            jedis = jedisPoolManager.getResource();
            categoryUrl = JedisUtil.getObjectFromMapById(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_URL, content.getCategoryId() + "", String.class);

            // 获取热销商品
            List<Map<String, Object>> hotSalesList = goodsContentService.selectHotSales(null);
            model.addAttribute("hotSalesList", hotSalesList);
            // 获取热门收藏商品
            List<Map<String, Object>> hotCollectList = goodsContentService.selectHotCollectList(null);
            model.addAttribute("hotCollectList", hotCollectList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }

        model.addAttribute("goods", content);
        model.addAttribute("skuId", skuId);
        model.addAttribute("favoriteCount", favoriteCount);
        model.addAttribute("skus", skuFrontTOs);
        model.addAttribute("categoryUrl", categoryUrl);
        // model.addAttribute("contentSkus",
        // JsonUtil.getJsonString(skuFrontTOs));
        model.addAttribute("attachments", attachments);
        model.addAttribute("properties", propertyTOs);
        model.addAttribute("propertyIdList", propertyIdList);
        model.addAttribute("propertyValueIdList", propertyValueIdList);
        model.addAttribute("loggedInUser", getCasUser());
        Map<String, Object> statisticsMap = commentContentService.getCommentStatistics(skuId);
        model.addAllAttributes(statisticsMap);
        return "front/goods/index.detail";
    }

    /**
     * 切换属性请求
     * 
     * @auther zhangzheng
     * @date 2016-3-16 下午4:27:21
     * @param model
     * @param contentId
     * @param propertyFront
     * @return
     */
    @RequestMapping(value = "getDetail", method = RequestMethod.GET)
    public String indexDetail(Model model, Integer contentId, String propertyFront) {
        String str = "";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("contentId", contentId);
        param.put("property", propertyFront);
        List<GoodsContentSku> skus = goodsContentSkuService.selectObjectList(param);
        if (skus != null && !skus.isEmpty()) {
            str = this.index(model, skus.get(0).getSkuId());
            model.addAttribute("skuDetail", skus.get(0));
        }
        return str;
    }

    /**
     * 商品详情页面切换属性时获取对应sku的信息
     * 
     * @auther zhangzheng
     * @date 2016-1-30 下午1:30:16
     * @param property
     * @param contentId
     * @return
     */
    @RequestMapping(value = "getSkuInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getSkuInfo(String property, Integer contentId) {
        Map<String, Object> param = new HashMap<String, Object>();
        GoodsContentSku sku = null;
        param.put("property", property);
        param.put("contentId", contentId);
        List<GoodsContentSku> skus = goodsContentSkuService.selectObjectList(param);
        if (skus != null && !skus.isEmpty()) {
            sku = skus.get(0);
        }
        return ResultTO.newSuccessResultTO(sku);
    }

    /**
     * 前端首页搜索、点击分类搜索
     * 
     * @auther zhangzheng
     * @date 2016-2-17 上午9:39:52
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model, GoodsContentSearchParamTO paramTO) throws UnsupportedEncodingException {
        Page<GoodsContentFrontSearchTO> page = null;

        Integer pageIndex = paramTO.getPageIndex();
        Integer pageSize = paramTO.getPageSize();
        
        String keyword = paramTO.getKeyword();
        Integer categoryId = paramTO.getCategoryId();
        Integer brandId = paramTO.getBrandId();
        String sort = paramTO.getSort();

        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        pageSize = 12;
        try {
            	page = goodsContentService.selectGoodsFrontSearchResult(pageIndex, pageSize, keyword, categoryId, sort, brandId);
            // 搜索页广告
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("pageId", 12);
            param.put("boardId", 13);
            List<AdvertDetailTO> advertContents = advertContentService.selectAdvertDetails(param);
            model.addAttribute("adverts", advertContents);

            // int pageCount = (int) Math.floor(page.getCount()/pageSize);
            int count = (int) page.getCount();
            int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            model.addAttribute("goods", page.getData());
            model.addAttribute("count", count);
            model.addAttribute("sort", sort);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("pageIndex", pageIndex);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("categoryId", categoryId);
            //modify by guoxue 2016-07-04 begin
            if(keyword !=null){
//            	model.addAttribute("keyword", new String(keyword.getBytes("ISO-8859-1"),"UTF-8"));
            	model.addAttribute("keyword", keyword);
            }
            //modify by guoxue 2016-07-04 end
            model.addAttribute("loggedInUser", getCasUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "front/goods/goods.search";
    }
    
    
    
    
    /**
     * 前端首页搜索、点击分类搜索
     * 
     * @auther zhangzheng
     * @date 2016-2-17 上午9:39:52
     * @param model
     * @return
     */
    @RequestMapping(value = "Allsearch", method = RequestMethod.GET)
    public String Allsearch(Model model, GoodsContentSearchParamTO paramTO) {
        Page<GoodsContentFrontSearchTO> page = null;
        Integer pageIndex = paramTO.getPageIndex();
        Integer pageSize = paramTO.getPageSize();
        String keyword = paramTO.getKeyword();
        Integer categoryId = paramTO.getCategoryId();
        Integer brandId = paramTO.getBrandId();
        String sort = paramTO.getSort();

        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        pageSize = 12;
        try {
            page = goodsContentService.selectGoodsFrontSearchResult(pageIndex, pageSize, keyword, categoryId, sort, brandId);
            // 搜索页广告
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("pageId", 12);
            param.put("boardId", 13);
            List<AdvertDetailTO> advertContents = advertContentService.selectAdvertDetails(param);
            model.addAttribute("adverts", advertContents);

            // int pageCount = (int) Math.floor(page.getCount()/pageSize);
            int count = (int) page.getCount();
            int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            model.addAttribute("goods", page.getData());
            model.addAttribute("count", count);
            model.addAttribute("sort", sort);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("pageIndex", pageIndex);
            model.addAttribute("pageCount", pageCount);
            model.addAttribute("keyword", keyword);
            model.addAttribute("loggedInUser", getCasUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "front/goods/goods.Allsearch";
    }
    

    /**
     * 分页查询评论信息 pageComment
     * 
     * @author kevin
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     * @since JDK 7
     */
    @RequestMapping(value = "commentPage", method = RequestMethod.POST)
    public String pageComment(Model model, Long skuId, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("skuId", skuId);
        Page<CommentDetailTO> pages = commentContentService.page(map, pageIndex, pageSize);
        model.addAttribute("page", pages);
        return "front/goods/comment.list";
    }

    /**
     * 分页查询销售记录信息 pageSaleHistory
     * 
     * @author kevin
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     * @since JDK 7
     */
    @RequestMapping(value = "saleHistoryPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<List<Map<String, Object>>> pageSaleHistory(Model model, Long skuId, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("skuId", skuId);
        Page<List<Map<String, Object>>> pages = btsOrderService.page("saleHistoryPage", "saleHistoryCount", map, pageIndex, pageSize);
        // model.addAttribute("page", pages);
        return pages;
    }

}
