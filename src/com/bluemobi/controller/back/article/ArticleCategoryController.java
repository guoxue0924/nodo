/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.back.article;

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
import com.bluemobi.po.article.ArticleCategory;
import com.bluemobi.service.article.ArticleCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 文章分类控制器
 * ClassName: ArticleCategoryController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/articleCategory")
public class ArticleCategoryController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCategoryController.class);
	
	@Autowired
	private ArticleCategoryService articleCategoryService;
	
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
	 * 初始化文章分类页面
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
        LOGGER.info("用户【{}】文章分类页", new Object[] { this.getUserid() });
        return "back/article/category.index";
	}
	
	/**
	 * 分页获取文章分类列表
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
        return articleCategoryService.page(map, pageIndex, pageSize);
    }

    /**
     * 初始化添加页面
     * articleCategoryAdd
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String articleCategoryAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys", articleCategoryService.selectObjectList(null));
        return "back/article/category.edit";
    }

    /**
     * 初始化修改页面
     * articleCategoryEdit
     * 
     * @author kevin
     * @param model
     * @param categoryId
     * @param req
     * @return 
     * @since JDK 7
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String articleCategoryEdit(Model model, int categoryId,
            HttpServletRequest req) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("categoryId", categoryId);
        model.addAttribute("category", articleCategoryService.selectObject(map));
        model.addAttribute("categorys",	articleCategoryService.selectObjectList(map));
        return "back/article/category.edit";

    }

    /**
     * 保存操作
     * saveArticleCategory
     * 
     * @author kevin
     * @param category
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveArticleCategory(ArticleCategory category) {
        return articleCategoryService.saveArticleCategory(category);
    }

    /**
     * 删除分类
     * delArticleCategory
     * 
     * @author kevin
     * @param categoryId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delArticleCategory(Integer categoryId) {
        return articleCategoryService.deleteCategory(categoryId);
    }
	
}
