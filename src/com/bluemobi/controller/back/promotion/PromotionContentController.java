package com.bluemobi.controller.back.promotion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.promotion.PromotionContent;
import com.bluemobi.service.goods.GoodsBrandService;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.service.promotion.PromotionCategoryService;
import com.bluemobi.service.promotion.PromotionContentService;
import com.bluemobi.to.ResultTO;



/**
 * 【优惠促销活动主体表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:22
 * 
 */
@Controller
@RequestMapping("back/promotionContent")
public class PromotionContentController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionContentController.class);
    
    @Autowired
    private PromotionContentService promotionContentService;
    @Autowired
    private PromotionCategoryService promotionCategoryService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsBrandService goodsBrandService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【优惠促销活动主体表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys", promotionCategoryService.selectObjectList(null));
        return "back/promotion/content.index";
    }
    
    /**
     * 分页查询【优惠促销活动主体表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(Integer type, String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(key)) {
        	map.put("key", key);
        }
        if(type != null && type > 0) {
        	map.put("categoryId", type);
        }
        Page<Map<String, Object>> page = promotionContentService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【优惠促销活动主体表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer promotionId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("promotionId", promotionId); 
        model.addAttribute("promotionContent", promotionContentService.selectObject(map));
        return "back/promotion/content.detail";
    }
    
    /**
     * 进入新增【优惠促销活动主体表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys", promotionCategoryService.selectObjectList(null));
        model.addAttribute("goodsCategorys", goodsCategoryService.getAllCategories());
        model.addAttribute("brands", goodsBrandService.selectObjectList(null));
        return "back/promotion/content.edit";
    }
    
    /**
     * 保存【优惠促销活动主体表】数据
     * @param promotionContent
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = {"add","edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addPromotionContent(PromotionContent promotionContent, Long[] skuIdList) {
        try {
        	if(promotionContent.getEndTime().before(promotionContent.getStartTime())) {
        		return ResultTO.newFailResultTO("结束时间不能小于开始时间", null);
        	}
            promotionContentService.savePromotion(promotionContent, skuIdList);
            LOGGER.info("用户【{}】保存优惠促销活动主体表数据【{}】成功", new Object[] { this.getUserid(), promotionContent } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】保存优惠促销活动主体表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionContent, e });
            return ResultTO.newFailResultTO("保存失败", null);
        }
        return ResultTO.newSuccessResultTO("保存成功", null);
    }
    
    /**
     * 进入修改【优惠促销活动主体表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer promotionId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("promotionId", promotionId); 
        model.addAttribute("content", promotionContentService.selectObject(map));
        model.addAttribute("categorys", promotionCategoryService.selectObjectList(null));
        model.addAttribute("goodsCategorys", goodsCategoryService.getAllCategories());
        model.addAttribute("brands", goodsBrandService.selectObjectList(null));
        return "back/promotion/content.edit";
    }
    
    
    /**
     * 删除【优惠促销活动主体表】数据
     * @param promotionId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deletePromotionContent(Integer promotionId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            map.put("promotionId", promotionId); 
            map.put("isDel", 1);
            promotionContentService.update(map);
            LOGGER.info("用户【{}】删除优惠促销活动主体表数据【{}】成功", new Object[] { this.getUserid(), promotionId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除优惠促销活动主体表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
      
    /**
     * 删除【优惠促销活动主体表】数据
     * @param promotionId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-08 16:25:22
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updatePromotionStatus(Integer promotionId) {
        try {
            promotionContentService.changeStatus(promotionId);
            LOGGER.info("用户【{}】删除优惠促销活动主体表数据【{}】成功", new Object[] { this.getUserid(), promotionId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除优惠促销活动主体表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), promotionId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    /**
     * 
     * 根据模板名称获取模板页面
     * 
     * @author kevin
     * @param templateName
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "getTemplate", method = RequestMethod.GET)
    public String getTemplatePage(String templateName) {
        return "back/promotion/template/" + templateName;
    }
    
    @RequestMapping(value = "checkActive", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO checkActive(Integer promotionId, Long skuId, Date startTime, Date endTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean ret = false;
        try {
        	if(promotionId != null) {
        		map.put("promotionId", promotionId); 
        	}
        	map.put("skuId", skuId);
        	map.put("startTime", startTime);
        	map.put("endTime", endTime);
        	ret= promotionContentService.checkActive(map);
        } catch (Exception e) {
            return ResultTO.newFailResultTO("查询异常", null);
        }
        if(ret) {
        	return ResultTO.newSuccessResultTO("", null);
        }
        return ResultTO.newFailResultTO("该商品已参加其他活动", null);
    }
    
}
