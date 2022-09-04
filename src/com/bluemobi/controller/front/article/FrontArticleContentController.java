/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.front.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluemobi.constant.ArticleConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.dao.article.ArticleCategoryDao;
import com.bluemobi.dao.article.ArticleContentDao;
import com.bluemobi.po.article.ArticleCategory;
import com.bluemobi.po.article.ArticleContent;
import com.bluemobi.service.article.ArticleCategoryService;
import com.bluemobi.service.article.ArticleContentService;



/**
 * 文章管理控制器
 * ClassName: ArticleContentController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("front/articleContent")
public class FrontArticleContentController extends AbstractAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontArticleContentController.class);
	
	@Autowired
	private ArticleContentService articleContentService;
	@Autowired
	private ArticleCategoryService articleCategoryService;
	
    
    /**
     * 文章详情
     * articleContentDetail
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String articleContentDetail(Model model, Integer contentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contentId", contentId);
        model.addAttribute("content", articleContentService.selectObject(map));
        model.addAttribute("loggedInUser", getCasUser());
        return "front/article/articleDetail";
    }
    /**
     * 初始化帮助中心
     * articleContentDetail
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "helpCenter", method = RequestMethod.GET)
    public String helpCenter(Model model,Integer categoryId) {	
    	
    	List<ArticleCategoryDao> list = articleCategoryService.selectObjectList(null);
    	ArticleCategory  articleCategory = articleCategoryService.selectObject(categoryId);
    	model.addAttribute("category",list);
    	model.addAttribute("categoryTitle", articleCategory.getTitle()); 
    	
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        model.addAttribute("content", articleContentService.selectObjectList(map));
        
        List<ArticleCategoryDao> list2 = articleContentService.selectObjectList(null);
        
        model.addAttribute("categoryId",categoryId);	
        model.addAttribute("contentNew",list2);
        model.addAttribute("loggedInUser", getCasUser());
        return "front/article/customService";
    }
    
    /**
     * 初始化帮助中心
     * articleContentDetail
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "helpCenterDetail", method = RequestMethod.GET)
    public String helpCenterDetail(Model model,Integer contentId,Integer categoryId) {	
    	
    	List<ArticleCategoryDao> list = articleCategoryService.selectObjectList(null);
    	ArticleCategory  articleCategory = articleCategoryService.selectObject(categoryId);
    	model.addAttribute("category",list);
    	model.addAttribute("categoryTitle", articleCategory.getTitle()); 
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        model.addAttribute("content", articleContentService.selectObjectList(map));
        List<ArticleCategoryDao> list2 = articleContentService.selectObjectList(null);
        model.addAttribute("contentNew",list2);
    	
    	
    	Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("contentId", contentId);
        model.addAttribute("contentDetail", articleContentService.selectObject(map2));
        model.addAttribute("categoryId",categoryId);
        model.addAttribute("loggedInUser", getCasUser());
        return "front/article/customServiceDetail";
    }
	
}
