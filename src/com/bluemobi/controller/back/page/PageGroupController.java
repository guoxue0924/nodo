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
import com.bluemobi.po.page.PageGroup;
import com.bluemobi.service.page.PageGroupService;
import com.bluemobi.to.ResultTO;

/**
 * 单页分组控制器
 * ClassName: PageGroupController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/pageGroup")
public class PageGroupController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageGroupController.class);
	
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
        return "back/page/group.index";
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
        return pageGroupService.page(map, pageIndex, pageSize);
    }

    /**
     * 初始化添加页面
     * pageGroupAdd
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String pageGroupAdd(Model model) {
        // 加载公共数据
        return "back/page/group.edit";
    }

    /**
     * 初始化修改页面
     * pageGroupEdit
     * 
     * @author kevin
     * @param model
     * @param groupId
     * @return 
     * @since JDK 7
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String pageGroupEdit(Model model, int groupId) {
        // 加载公共数据
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("groupId", groupId);
        model.addAttribute("group", pageGroupService.selectObject(map));
        return "back/page/group.edit";

    }

    /**
     * 保存操作
     * savePageGroup
     * 
     * @author kevin
     * @param pageGroup
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO savePageGroup(PageGroup pageGroup, Integer moveable) {
    	if(moveable == null) {
    		pageGroup.setMoveable((byte)0);
    	} else {
    		pageGroup.setMoveable((byte)1);
    	}
        return pageGroupService.savePageGroup(pageGroup);
    }

    /**
     * 删除分类
     * delPageGroup
     * 
     * @author kevin
     * @param groupId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delPageGroupy(Integer groupId) {
        return pageGroupService.deletePageGroup(groupId);
    }
	
}
