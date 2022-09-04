package com.bluemobi.controller.back.goods;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.service.goods.GoodsBrandService;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("back/goodsTrash")
public class GoodsTrashController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsTrashController.class);

    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private GoodsBrandService goodsBrandService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    private static final String CATEGORIES = "categories";
    private static final String BRANDS = "brands";
    private static final String CATEGORY_ID = "categoryId";
    private static final String BRAND_ID = "brandId";

    /**
     * 初始化商品回收站页面
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:13:22
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        // 获取所有分类信息
        model.addAttribute(CATEGORIES, goodsCategoryService.getAllCategories());
        // 所有品牌信息
        model.addAttribute(BRANDS, goodsBrandService.selectObjectList(null));
        LOGGER.info("用户【{}】初始化商品回收站页面", new Object[] { this.getUserid() });
        return "back/goods/trash.index";
    }

    /**
     * 分页查询商品回收站信息
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:14:11
     * @param pageIndex
     * @param pageSize
     * @param key
     * @param categoryId
     * @param brandId
     * @param priceMin
     * @param priceMax
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(Integer pageIndex, Integer pageSize, String key, Integer categoryId, Integer brandId, Integer priceMin, Integer priceMax) {
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
        if (priceMin != null && priceMin > 0) {
            param.put("priceMin", priceMin);
        }
        if (priceMax != null && priceMax > 0) {
            param.put("priceMax", priceMax);
        }
        param.put("isDel", 1);
        Page<Map<String, Object>> page = goodsContentService.page(param, pageIndex, pageSize);
        LOGGER.info("用户【{}】根据关键字【{}】分类【{}】品牌【{}】最低价【{}】最高价【{}】页码【{}】每页最大数【{}】分页查询商品回收站信息", new Object[] { this.getUserid(), key, categoryId, brandId, priceMin, priceMax,
                pageIndex, pageSize });
        return page;
    }

    /**
     * 彻底删除商品
     * 
     * @auther zhangzheng
     * @date 2015-10-20 下午1:14:38
     * @param contentIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteGoodsContent(String contentIds) {
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            String[] ids = contentIds.split(",");
            param.put("contentIds", ids);
            goodsContentService.deleteByIds(param);
            LOGGER.info("用户【{}】彻底删除商品【{}】成功", new Object[] { this.getUserid(), contentIds });
        } catch (Exception e) {
            LOGGER.error("用户【{}】彻底删除商品【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), contentIds, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 还原商品
     * 
     * @auther zhangzheng
     * @date 2016-3-10 下午4:36:32
     * @param contentId
     * @return
     */
    @RequestMapping(value = "returnGoods", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO returnGoods(String contentId) {
        Date date = new Date();
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            param.put("contentId", contentId);
            param.put("mtime", date);
            param.put("isDel", 0);
            goodsContentService.update(param);
            LOGGER.info("用户【{}】还原商品【{}】成功", new Object[] { this.getUserid(), contentId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】还原商品【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), contentId, e });
            return ResultTO.newFailResultTO("还原商品失败", null);
        }
        return ResultTO.newSuccessResultTO("还原商品成功", null);
    }

}
