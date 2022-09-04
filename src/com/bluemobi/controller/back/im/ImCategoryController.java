/**
 * Project Name:nodo 
 * File Name:ImCategoryController.java 
 * Package Name:com.bluemobi.controller.back.im 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.back.im;

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
import com.bluemobi.po.im.ImCategory;
import com.bluemobi.service.im.ImCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * IM分类控制器
 * ClassName: ImCategoryController
 * Date: 2016年3月4日下午5:07:58

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/imCategory")
public class ImCategoryController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImCategoryController.class);
	
	@Autowired
	private ImCategoryService imCategoryService;
	
	@InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
	
	/**
	 * 
	 * index 
	 * 初始化IM分类页面
	 * 
	 * @author kevin
	 * @param model
	 * @return 
	 * @since JDK 7
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
		// 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】IM分类页", new Object[] { this.getUserid() });
        return "back/im/category.index";
	}
	
	/**
	 * 分页获取IM分类列表
	 * getPage
	 * 
	 * @author kevin
	 * @param key
	 * @param pageSize 
	 * @param pageIndex
	 * @return 
	 * @since JDK 7
	 */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> getPage(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(key)) {
        	map.put("key", key);
        }
        return imCategoryService.page(map, pageIndex, pageSize);
    }

    /**
     * 初始化添加页面
     * imCategoryAdd
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String imCategoryAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys", imCategoryService.selectObjectList(null));
        return "back/im/category.edit";
    }

    /**
     * 初始化修改页面
     * imCategoryEdit
     * 
     * @author kevin
     * @param model
     * @param categoryId
     * @param req
     * @return 
     * @since JDK 7
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String imCategoryEdit(Model model, int categoryId,
            HttpServletRequest req) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("categoryId", categoryId);
        model.addAttribute("category", imCategoryService.selectObject(map));
        return "back/im/category.edit";

    }

    /**
     * 保存操作
     * saveimCategory
     * 
     * @author kevin
     * @param category
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveImCategory(ImCategory category) {
        return imCategoryService.saveImCategory(category);
    }

    /**
     * 删除分类
     * delimCategory
     * 
     * @author kevin
     * @param categoryId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delImCategory(Integer categoryId) {
        return imCategoryService.deleteCategory(categoryId);
    }
	
}
