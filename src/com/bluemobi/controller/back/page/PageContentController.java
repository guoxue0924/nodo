/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.back.page;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.page.PageContent;
import com.bluemobi.service.page.PageContentService;
import com.bluemobi.service.page.PageGroupService;
import com.bluemobi.to.ResultTO;

/**
 * 单页内容控制器
 * ClassName: PageContentController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/pageContent")
public class PageContentController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageContentController.class);
	
	@Autowired
	private PageContentService pageContentService;
	@Autowired
	private PageGroupService pageGroupService;
	
	/**
	 * 
	 * index 
	 * 单页分组页面
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
        LOGGER.info("用户【{}】单页分组页", new Object[] { this.getUserid() });
        return "back/page/content.index";
	}
	
	/**
	 * 分页获取单页分组 列表
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
        return pageContentService.page(map, pageIndex, pageSize);
    }

    /**
     * 初始化添加页面
     * pageContentAdd
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String pageContentAdd(Model model, String et) {
        // 加载公共数据
    	initIndex(model);
    	model.addAttribute("groups", pageGroupService.selectObjectList(null));
        return "back/page/content.edit." + (et == null ? "ck" : et);
    }

    /**
     * 初始化修改页面
     * pageContentEdit
     * 
     * @author kevin
     * @param model
     * @param contentId
     * @return 
     * @since JDK 7
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String pageContentEdit(Model model, int contentId, String et) {
        // 加载公共数据
    	initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", contentId);
        model.addAttribute("groups", pageGroupService.selectObjectList(null));
        model.addAttribute("content", pageContentService.selectObject(map));
        return "back/page/content.edit." + (et == null ? "ck" : et);

    }

    /**
     * 保存操作
     * savePageContent
     * 
     * @author kevin
     * @param pageGroup
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO savePageContent(PageContent content) {
        return pageContentService.savePageContent(content);
    }

    /**
     * 删除单页
     * delPageContent
     * 
     * @author kevin
     * @param groupId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delPageContent(Integer contentId) {
        return pageContentService.deletePageContent(contentId);
    }
	
}
