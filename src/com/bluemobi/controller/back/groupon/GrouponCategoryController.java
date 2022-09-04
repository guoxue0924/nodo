package com.bluemobi.controller.back.groupon;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.groupon.GrouponCategory;
import com.bluemobi.service.groupon.GrouponCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 团购抢购分类标签模块 控制器
 * @ClassName GrouponCategoryController
 * @author liuyt
 * @date 2015-10-23 上午10:14:21
 * @version
 */
@Controller
@RequestMapping("back/grouponCategory")
public class GrouponCategoryController extends AbstractBackController {

    @Autowired
    private GrouponCategoryService grouponCategoryService;
    
    /**
     * 初始化列表页
     * 
     * @author liuyt
     * @date 2015-10-21 上午10:28:40
     * @param model
     * @param request
     * @return string
     * @version 1.0
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/groupon/category.index";
    }

    /**
     * 分页获取分类标签列表
     * 
     * @author liuyt
     * @date 2015-10-21 上午10:29:16
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @version
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPage(String key,
            int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> pages = grouponCategoryService.page(map,
                pageIndex, pageSize);

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
    }

    /**
     * 初始化添加页面
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:34:28
     * @param model
     * @param req
     * @return
     * @version
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String grouponCategoryAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys",
                grouponCategoryService.selectObjectList(null));
        return "back/groupon/category.edit";
    }

    /**
     * 初始化修改页面
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:35:00
     * @param model
     * @param bulkId
     * @param req
     * @return
     * @version
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String grouponCategoryEdit(Model model, int categoryId,
            HttpServletRequest req) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("categoryId", categoryId);
        model.addAttribute("category", grouponCategoryService.selectObject(map));
        model.addAttribute("categorys",
                grouponCategoryService.selectObjectList(null));
        return "back/groupon/category.edit";

    }

    /**
     * 保存操作
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:34:28
     * @param model
     * @param req
     * @return
     * @version
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveGrouponCategory(GrouponCategory category) {
        return grouponCategoryService.saveGrouponCategory(category);
    }

    /**
     * 删除标签
     * 
     * @author liuyt
     * @date 2015-10-22 下午4:00:12
     * @param bulkId
     * @return
     * @version
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delGrouponCategory(Integer categoryId) {
        return grouponCategoryService.deleteCategory(categoryId);
    }
}
