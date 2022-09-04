package com.bluemobi.controller.back.promotion;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.promotion.PromotionCategory;
import com.bluemobi.service.promotion.PromotionCategoryService;
import com.bluemobi.to.ResultTO;



/**
 * 【优惠促销活动分类】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:21
 * 
 */
@Controller
@RequestMapping("back/promotionCategory")
public class PromotionCategoryController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionCategoryController.class);
    
    @Autowired
    private PromotionCategoryService promotionCategoryService;
    

    
    /**
     * 进入【优惠促销活动分类】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "promotion/category.index";
    }
    
    /**
     * 分页查询【优惠促销活动分类】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = promotionCategoryService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【优惠促销活动分类】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer categoryId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("categoryId", categoryId); 
        model.addAttribute("promotionCategory", promotionCategoryService.selectObject(map));
        return "promotion/category.detail";
    }
    
    /**
     * 进入新增【优惠促销活动分类】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "promotion/category.edit";
    }
    
    /**
     * 新增【优惠促销活动分类】数据
     * @param promotionCategory
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addPromotionCategory(@ModelAttribute PromotionCategory promotionCategory, BindingResult result) {
        try {
            promotionCategoryService.insert(promotionCategory);
            LOGGER.info("用户【{}】添加优惠促销活动分类数据【{}】成功", new Object[] { this.getUserid(), promotionCategory } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加优惠促销活动分类数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionCategory, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【优惠促销活动分类】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer categoryId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId); 
        model.addAttribute("promotionCategory", promotionCategoryService.selectObject(map));
        return "promotion/category.edit";
    }
    
    /**
     * 修改【优惠促销活动分类】数据
     * @param promotionCategory
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editPromotionCategory(@ModelAttribute PromotionCategory promotionCategory, BindingResult result) {
        try {
            promotionCategoryService.update(promotionCategory);
            LOGGER.info("用户【{}】修改优惠促销活动分类数据【{}】成功", new Object[] { this.getUserid(), promotionCategory } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改优惠促销活动分类数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionCategory, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【优惠促销活动分类】数据
     * @param categoryId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:21
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deletePromotionCategory(Integer categoryId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            map.put("categoryId", categoryId); 
            promotionCategoryService.delete(map);
            LOGGER.info("用户【{}】删除优惠促销活动分类数据【{}】成功", new Object[] { this.getUserid(), categoryId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除优惠促销活动分类数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), categoryId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
