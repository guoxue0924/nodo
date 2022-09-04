package com.bluemobi.controller.api.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.appcore.util.JsonUtil;
import com.bluemobi.apito.GoodsSkuSimpleTO;
import com.bluemobi.apito.GoodsSkuTO;
import com.bluemobi.apito.SkuPropertyTO;
import com.bluemobi.apito.goods.GetDefaultGoodsRequestTO;
import com.bluemobi.apito.goods.GetDefaultGoodsResponseTO;
import com.bluemobi.apito.goods.GetGoodsSkuByPropertyRequestTO;
import com.bluemobi.apito.goods.GetGoodsSkuByPropertyResponseTO;
import com.bluemobi.apito.goods.GetGoodsSkuRequestTO;
import com.bluemobi.apito.goods.GetGoodsSkuResponseTO;
import com.bluemobi.apito.goods.SearchGoodsRequestTO;
import com.bluemobi.apito.goods.SearchGoodsResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.goods.GoodsContentFrontSearchTO;

/**
 * 【商品】移动端控制器
 * 
 * @author zhangzheng
 * @date 2016-2-29
 * 
 */
@Controller(value = "goodsContentAPIController")
@RequestMapping("api/goodsContent")
public class GoodsContentAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsContentAPIController.class);

    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private GoodsContentService goodsContentService;

    /**
     * 搜索商品
     * 
     * @auther zhangzheng
     * @date 2016-2-29 上午11:07:54
     * @param request
     * @param searchGoodsRequestTO
     * @return
     */
    @RequestMapping(value = "searchGoods")
    @ResponseBody
    public ResultTO searchGoods(HttpServletRequest request, String json) {

        SearchGoodsRequestTO searchGoodsRequestTO = JsonUtil.getObject(json, SearchGoodsRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), searchGoodsRequestTO.toString() });
        Map<String, Object> param = new HashMap<String, Object>();
        Page<GoodsContentFrontSearchTO> page = null;
        GoodsContentFrontSearchTO searchTO = null;
        GoodsSkuSimpleTO simpleTO = null;
        SearchGoodsResponseTO searchGoodsResponseTO = new SearchGoodsResponseTO();
        Integer pageIndex = searchGoodsRequestTO.getCurrentPage();
        Integer pageSize = 12;
        String keyword = null;
        Integer categoryId = null;
        if (searchGoodsRequestTO.getSearchType() == 1) {
            categoryId = Integer.valueOf(searchGoodsRequestTO.getCondition());
        }
        if (searchGoodsRequestTO.getSearchType() == 2) {
            keyword = searchGoodsRequestTO.getCondition();
        }
        Integer brandId = searchGoodsRequestTO.getBrandId();
        try {
            param.put("keyword", keyword);
            param.put("categoryId", categoryId);
            String sort = searchGoodsRequestTO.getSort();
            page = goodsContentService.selectGoodsFrontSearchResult(pageIndex, pageSize, keyword, categoryId, sort, brandId);
            if (page != null && !page.getData().isEmpty()) {
                List<GoodsContentFrontSearchTO> searchTOs = page.getData();
                for (int i = 0; i < searchTOs.size(); i++) {
                    searchTO = searchTOs.get(i);
                    simpleTO = new GoodsSkuSimpleTO();
                    simpleTO.setSkuId(searchTO.getSkus().get(0).getSkuId().intValue());
                    simpleTO.setName(searchTO.getName());
                    simpleTO.setPrice(searchTO.getSkus().get(0).getPrice().floatValue());
                    simpleTO.setImage(searchTO.getSkus().get(0).getFilepath());
                    searchGoodsResponseTO.getGoodsSkuSimples().add(simpleTO);
                }
            }
            return ResultTO.newSuccessResultTO("搜索商品成功！", searchGoodsResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("搜索商品出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), searchGoodsRequestTO.toString() });
            return ResultTO.newFailResultTO("搜索商品失败！", null);
        }
    }

    /**
     * 获取推荐商品
     * 
     * @auther zhangzheng
     * @date 2016-2-29 上午11:08:10
     * @param request
     * @param getDefaultGoodsRequestTO
     * @return
     */
    @RequestMapping(value = "getDefaultGoods")
    @ResponseBody
    public ResultTO getDefaultGoods(HttpServletRequest request, String json) {

        GetDefaultGoodsRequestTO getDefaultGoodsRequestTO = JsonUtil.getObject(json, GetDefaultGoodsRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getDefaultGoodsRequestTO.toString() });
        Map<String, Object> param = new HashMap<String, Object>();
        List<GoodsSkuSimpleTO> list = null;
        try {
            GetDefaultGoodsResponseTO getDefaultGoodsResponseTO = new GetDefaultGoodsResponseTO();
            list = goodsContentSkuService.selectGoodsContentSkuAPISearch(param, 1, 12);
            getDefaultGoodsResponseTO.setGoodsSkuSimples(list);
            return ResultTO.newSuccessResultTO("获取推荐商品成功！", getDefaultGoodsResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取推荐商品出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getDefaultGoodsRequestTO.toString() });
            return ResultTO.newFailResultTO("获取推荐商品失败！", null);
        }
    }

    /**
     * 获取商品sku
     * 
     * @auther zhangzheng
     * @date 2016-2-29 上午11:08:17
     * @param request
     * @param getGoodsSkuRequestTO
     * @return
     */
    @RequestMapping(value = "getGoodsSku")
    @ResponseBody
    public ResultTO getGoodsSku(HttpServletRequest request, String json) {
        GetGoodsSkuRequestTO getGoodsSkuRequestTO = JsonUtil.getObject(json, GetGoodsSkuRequestTO.class);
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getGoodsSkuRequestTO.toString() });
        GetGoodsSkuResponseTO getGoodsSkuResponseTO = new GetGoodsSkuResponseTO();
        int i = 0;
        try {
            // 处理业务
            GoodsSkuTO goodsSkuTO = goodsContentSkuService.getGoodsContentSku(getGoodsSkuRequestTO.getSkuId());
            if (getUserid() != 0) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("skuId", getGoodsSkuRequestTO.getSkuId());
                param.put("userid", getUserid());
                i = goodsContentSkuService.selectSkuIsFavorite(param);
            }
            goodsSkuTO.setIsFavorite(i);
            getGoodsSkuResponseTO.setGoodsSku(goodsSkuTO);
        } catch (Exception e) {
            LOGGER.error("获取商品sku出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getGoodsSkuRequestTO.toString() });
            return ResultTO.newFailResultTO("获取商品sku失败！", null);
        }
        return ResultTO.newSuccessResultTO("获取商品sku成功！", getGoodsSkuResponseTO);
    }

    /**
     * 通过商品属性获取商品sku
     * 
     * @auther zhangzheng
     * @date 2016-2-29 上午11:08:24
     * @param request
     * @param getGoodsSkuByPropertyRequestTO
     * @return
     */
    @RequestMapping(value = "getGoodsSkuByProperty")
    @ResponseBody
    public ResultTO getGoodsSkuByProperty(HttpServletRequest request, String json) {

        GetGoodsSkuByPropertyRequestTO getGoodsSkuByPropertyRequestTO = JsonUtil.getObject(json, GetGoodsSkuByPropertyRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getGoodsSkuByPropertyRequestTO.toString() });
        try {
            // 处理业务
            int contentId = getGoodsSkuByPropertyRequestTO.getContentId();
            // 拼接属性字符串
            String property = "";
            List<SkuPropertyTO> skuPropertyList = getGoodsSkuByPropertyRequestTO.getSkuProperty();
            for (int i = 0; i < skuPropertyList.size(); i++) {
                SkuPropertyTO skuPropertyTO = skuPropertyList.get(i);
                if (i != 0) {
                    property = property + ",";
                }
                property = property + skuPropertyTO.getPropertyId() + "_" + skuPropertyTO.getPropertyValueId();
            }
            // 查询商品sku
            GoodsSkuTO goodsSkuTO = goodsContentSkuService.getGoodsSkuByProperty(contentId, property);

            GetGoodsSkuByPropertyResponseTO getGoodsSkuByPropertyResponseTO = new GetGoodsSkuByPropertyResponseTO();
            getGoodsSkuByPropertyResponseTO.setGoodsSku(goodsSkuTO);
            return ResultTO.newSuccessResultTO("通过商品属性获取商品sku成功！", getGoodsSkuByPropertyResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("通过商品属性获取商品sku出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getGoodsSkuByPropertyRequestTO.toString() });
            return ResultTO.newFailResultTO("通过商品属性获取商品sku失败！", null);
        }

    }

}
