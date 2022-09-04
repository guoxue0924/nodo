/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.back.article;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.article.ArticleContent;
import com.bluemobi.service.article.ArticleCategoryService;
import com.bluemobi.service.article.ArticleContentService;
import com.bluemobi.to.ResultTO;

/**
 * 文章管理控制器
 * ClassName: ArticleContentController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/articleContent")
public class ArticleContentController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleContentController.class);
	
	@Autowired
	private ArticleContentService articleContentService;
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
	 * 初始化文章管理页面
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
        LOGGER.info("用户【{}】文章管理页", new Object[] { this.getUserid() });
        model.addAttribute("categorys",	articleCategoryService.selectObjectList(null));
        return "back/article/content.index";
	}
	
	/**
	 * 分页获取文章列表
	 * getPage
	 * 
	 * @author kevin
	 * @param categoryId
	 * @param key
	 * @param pageSize
	 * @param pageIndex
	 * @return 
	 * @since JDK 7
	 */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> getPage(Integer categoryId, String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(key)) {
        	map.put("key", key);
        }
        if(categoryId != null) {
        	map.put("categoryId", categoryId);
        }
        map.put("isRecycle", 0);
        return articleContentService.page(map, pageIndex, pageSize);
    }

    /**
     * 初始化添加页面
     * articleContentAdd
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String articleContentAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys", articleCategoryService.selectObjectList(null));
        return "back/article/content.edit";
    }
    
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
        return "back/article/content.detail";
    }

    /**
     * 初始化修改页面
     * articleContentEdit
     * 
     * @author kevin
     * @param model
     * @param categoryId
     * @param req
     * @return 
     * @since JDK 7
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String articleContentEdit(Model model, int contentId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("contentId", contentId);
        model.addAttribute("content", articleContentService.selectObject(map));
        model.addAttribute("categorys", articleCategoryService.selectObjectList(null));
        return "back/article/content.edit";

    }

    /**
     * 保存操作
     * saveArticleContent
     * 
     * @author kevin
     * @param content
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveArticleContent(ArticleContent content) {
        return articleContentService.saveArticleContent(content);
    }

    /**
     * 将文章扔进回收站
     * delArticleContent
     * 
     * @author kevin
     * @param contentId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delArticleContent(@RequestParam("contentIds[]") Integer[] contentIds) {
    	List<Integer> contentIdList = Arrays.asList(contentIds);
        return articleContentService.deleteConent(contentIdList, false);
    }
    
    /**
     * 发布/撤销操作
     * publishArticleContent
     * 
     * @author kevin
     * @param contentId
     * @param isPublished 是否已发布
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publishArticleContent(Integer contentId, boolean isPublished) {
        return articleContentService.publishArticleContent(contentId, isPublished);
    }
    
    /**
     * 推荐/撤销操作
     * recommendedArticleContent
     * 
     * @author kevin
     * @param contentId
     * @param isRecommended 是否已推荐
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "recommended", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO recommendedArticleContent(Integer contentId, boolean isRecommended) {
        return articleContentService.recommenedArticleContent(contentId, isRecommended);
    }
	
}
