/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.back.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.page.PageConfig;
import com.bluemobi.service.page.PageConfigService;
import com.bluemobi.to.ResultTO;

/**
 * 单页设置控制器
 * ClassName: PageConfigController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/pageConfig")
public class PageConfigController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageConfigController.class);
	
	@Autowired
	private PageConfigService pageConfigService;
	
	/**
	 * 
	 * index 
	 * 初始化单页设置页面
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
        LOGGER.info("用户【{}】单页设置页", new Object[] { this.getUserid() });
        model.addAttribute("pageConfig", pageConfigService.selectObjectList(null));
        return "back/page/config.index";
	}
	
    /**
     * 保存操作
     * savePageConfig
     * 
     * @author kevin
     * @param category
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO savePageConfig(@RequestParam("configIds[]") Integer[] configIds, @RequestParam("configValues[]") String[] configValues) {
    	PageConfig config = null;
    	for (int i = 0; i < configIds.length; i++) {
    		config = new PageConfig();
    		config.setId(configIds[i]);
    		config.setValue(configValues[i]);
    		pageConfigService.update(config);
		}
    	return ResultTO.newSuccessResultTO(null);
    }

}
