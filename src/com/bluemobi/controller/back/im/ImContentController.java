/**
 * Project Name:nodo 
 * File Name:ImContentController.java 
 * Package Name:com.bluemobi.controller.back.im 
 * Date:2016年3月4日上午11:37:04 
 */
package com.bluemobi.controller.back.im;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.im.ImContent;
import com.bluemobi.service.im.ImCategoryService;
import com.bluemobi.service.im.ImContentService;
import com.bluemobi.to.ResultTO;

/**
 * IM消息内容控制器
 * ClassName: ImContentController
 * Date: 2016年3月4日上午11:37:04

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/imContent")
public class ImContentController extends AbstractBackController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImContentController.class);
	
	@Autowired
	private ImContentService imContentService;
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
	 * 初始化消息管理页面
	 * 
	 * @author kevin
	 * @param model
	 * @return 
	 * @since JDK 7
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, Integer categroyId) {
		// 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】消息管理页", new Object[] { this.getUserid() });
        model.addAttribute("categorys", imCategoryService.selectObjectList(null));
        model.addAttribute("contacts",	imContentService.getContacts(1));
        return "back/im/content.index";
	}
	
	/**
	 * 分页获取IM内容列表
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
    public Page<Map<String, Object>> getPage(Integer categoryId, Long sendUserId, String key, Integer pageSize, Integer pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(key)) {
        	map.put("key", key);
        }
        if(categoryId > 0) {
        	map.put("categoryId", categoryId);
        }
        if(sendUserId > 0) {
        	map.put("sendUserId", sendUserId);
        }
        Page<Map<String, Object>> page = imContentService.page(map, 1, 100);
        map.clear();
        for (Map<String, Object> data : page.getData()) {
        	map.put("id", data.get("id"));
        	map.put("isRead", 1);
        	map.put("mtime", Calendar.getInstance().getTime());
        	imContentService.update(map);
		}
        Collections.reverse(page.getData());
        return page;
    }
    
    /**
     * 客服回复用户信息
     * replyContent
     * 
     * @author kevin
     * @param categoryId
     * @param toUserId
     * @param content
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "reply", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO replyContent(Integer categoryId, Long toUserId, String content) {
    	ImContent imContent = new ImContent();
    	imContent.setCategoryId(categoryId);
    	imContent.setContent(content);
    	imContent.setCtime(Calendar.getInstance().getTime());
    	imContent.setSendUserid(1L);
    	imContent.setSendUserType("2");
    	imContent.setToUserid(toUserId);
    	imContent.setToUserType("1");
    	int ret = imContentService.insert(imContent);
    	if(ret == 1) {
    		return ResultTO.newSuccessResultTO(imContent);
    	} 
    	return ResultTO.newFailResultTO(null);
    }
    
    /**
     * 实时加载用户新发消息
     * loadNewContent
     * 
     * @author kevin
     * @param categoryId
     * @param sendUserId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "loadNewMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO loadNewContent(Integer categoryId, Long sendUserId) {
    	Page<Map<String, Object>> page = getPage(categoryId, sendUserId, null, 0, 0);
    	List<Map<String, Object>> contacts = imContentService.getContacts(1);
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("contents", page.getData());
    	data.put("contacts", contacts);
    	return ResultTO.newSuccessResultTO(data);
    }

}
