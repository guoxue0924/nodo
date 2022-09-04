package com.bluemobi.controller.back.trend;

import java.util.HashMap;
import java.util.List;
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
import com.bluemobi.po.trend.TrendPropertyGroup;
import com.bluemobi.service.trend.TrendPropertyGroupService;
import com.bluemobi.service.trend.TrendPropertyToGroupService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.trend.PropertyGroupTO;

@Controller
@RequestMapping("back/trendPropertyGroup")
public class TrendPropertyGroupController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrendPropertyGroupController.class);

    @Autowired
    private TrendPropertyGroupService trendPropertyGroupService;
    @Autowired
    private TrendPropertyToGroupService trendPropertyToGroupService;

    /**
     * 初始化商品属性分组管理页面
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午4:01:03
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化商品组别页面", new Object[] { this.getUserid() });
        return "back/trend/propertyGroup.index";
    }

    /**
     * 分页查询商品属性分组信息
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午4:01:07
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        Page<Map<String, Object>> pages = null;
        try {
            if (key != null && !"".equals(key)) {
                reqMap.put("key", key);
            }
            pages = trendPropertyGroupService.page(reqMap, pageIndex, pageSize);
            LOGGER.info("用户【{}】分页查询商品属性分组信息成功", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】分页查询商品属性分组信息失败。Exception:【{}】", new Object[] { this.getUserid(), e });
        }
        return pages;

    }

    /**
     * 初始化商品属性分组新增页面
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午4:01:27
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化商品属性分组新增页面", new Object[] { this.getUserid() });
        return "back/trend/propertyGroup.edit";
    }

    /**
     * 新增商品属性分组
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午4:01:44
     * @param property
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addTrendPropertyGroup(@ModelAttribute PropertyGroupTO property, BindingResult result) {
        // 多对象保存
        try {
            trendPropertyGroupService.saveTrendPropertyGroup(property);
            LOGGER.info("用户【{}】新增商品属性分组成功", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增商品属性分组失败。Exception:【{}】", new Object[] { this.getUserid(), e });
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 初始化商品属性分组修改页面
     * 
     * @auther zhangzheng
     * @date 2015-10-12 下午1:44:33
     * @param model
     * @param propertyGroupId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, int propertyGroupId) {
        // 加载公共数据
        initIndex(model);
        // 获得详情对象
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("propertyGroupId", propertyGroupId);
        TrendPropertyGroup group = trendPropertyGroupService.selectObject(reqMap);
        // 获得相关属性关联
        List<Map<String, Object>> properties = trendPropertyToGroupService.selectMapList(reqMap);
        model.addAttribute("group", group);
        model.addAttribute("properties", properties);
        LOGGER.info("用户【{}】初始化商品属性分组修改页面", new Object[] { this.getUserid() });
        return "back/trend/propertyGroup.edit";

    }

    /**
     * 修改商品属性分组
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午4:03:27
     * @param property
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editTrendPropertyGroup(@ModelAttribute PropertyGroupTO property, BindingResult result) {
        // 修改商品属性管理
        try {
            trendPropertyGroupService.updateTrendPropertyGroup(property);
            LOGGER.info("用户【{}】修改商品属性分组【{}】修改成功", new Object[] { this.getUserid(), property.getPropertyGroupId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改商品属性分组【{}】修改失败。Exception:【{}】", new Object[] { this.getUserid(), property.getPropertyGroupId(), e });
            return ResultTO.newSuccessResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 删除单个或多个商品属性分组
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午4:03:32
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteTrendPropertyGroup(String ids) {
        try {
            trendPropertyGroupService.deleteTrendPropertyGroup(ids);
            LOGGER.info("用户【{}】删除单个或多个商品属性分组【{}】删除成功", new Object[] { this.getUserid(), ids });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除单个或多个商品属性分组【{}】删除失败。Exception:【{}】", new Object[] { this.getUserid(), ids, e });
            return ResultTO.newSuccessResultTO("删除成功", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);

    }

}
