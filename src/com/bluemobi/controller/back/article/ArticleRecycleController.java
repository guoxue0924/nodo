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
import com.bluemobi.service.article.ArticleCategoryService;
import com.bluemobi.service.article.ArticleContentService;
import com.bluemobi.to.ResultTO;

/**
 * 文章回收站控制器
 * ClassName: ArticleContentController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/articleRecycle")
public class ArticleRecycleController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleRecycleController.class);
	
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
	 * 初始化文章回收站页面
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
        LOGGER.info("用户【{}】文章回收站页", new Object[] { this.getUserid() });
        return "back/article/recycle.index";
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
        map.put("isRecycle", 1);
        return articleContentService.page(map, pageIndex, pageSize);
    }
    
    /**
     * 还原文章
     * restoreArticleContent
     * 
     * @author kevin
     * @param contentId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "restore", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO restoreArticleContent(Integer contentId) {
        return articleContentService.restoreConent(contentId);
    }
    
    /**
     * 将文章删除
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
        return articleContentService.deleteConent(contentIdList, true);
    }
	
}
