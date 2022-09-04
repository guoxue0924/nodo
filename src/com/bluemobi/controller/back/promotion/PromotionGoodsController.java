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
import com.bluemobi.po.promotion.PromotionGoods;
import com.bluemobi.service.promotion.PromotionGoodsService;
import com.bluemobi.to.ResultTO;



/**
 * 【优惠促销活动所关联的商品表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:23
 * 
 */
@Controller
@RequestMapping("back/promotionGoods")
public class PromotionGoodsController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionGoodsController.class);
    
    @Autowired
    private PromotionGoodsService promotionGoodsService;
    

    
    /**
     * 进入【优惠促销活动所关联的商品表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "promotion/goods.index";
    }
    
    /**
     * 分页查询【优惠促销活动所关联的商品表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = promotionGoodsService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【优惠促销活动所关联的商品表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", id); 
        model.addAttribute("promotionGoods", promotionGoodsService.selectObject(map));
        return "promotion/goods.detail";
    }
    
    /**
     * 进入新增【优惠促销活动所关联的商品表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "promotion/goods.edit";
    }
    
    /**
     * 新增【优惠促销活动所关联的商品表】数据
     * @param promotionGoods
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addPromotionGoods(@ModelAttribute PromotionGoods promotionGoods, BindingResult result) {
        try {
            promotionGoodsService.insert(promotionGoods);
            LOGGER.info("用户【{}】添加优惠促销活动所关联的商品表数据【{}】成功", new Object[] { this.getUserid(), promotionGoods } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加优惠促销活动所关联的商品表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionGoods, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【优惠促销活动所关联的商品表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id); 
        model.addAttribute("promotionGoods", promotionGoodsService.selectObject(map));
        return "promotion/goods.edit";
    }
    
    /**
     * 修改【优惠促销活动所关联的商品表】数据
     * @param promotionGoods
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editPromotionGoods(@ModelAttribute PromotionGoods promotionGoods, BindingResult result) {
        try {
            promotionGoodsService.update(promotionGoods);
            LOGGER.info("用户【{}】修改优惠促销活动所关联的商品表数据【{}】成功", new Object[] { this.getUserid(), promotionGoods } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改优惠促销活动所关联的商品表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionGoods, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【优惠促销活动所关联的商品表】数据
     * @param id
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:23
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deletePromotionGoods(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            map.put("id", id); 
            promotionGoodsService.delete(map);
            LOGGER.info("用户【{}】删除优惠促销活动所关联的商品表数据【{}】成功", new Object[] { this.getUserid(), id });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除优惠促销活动所关联的商品表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), id, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
