/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.front.cas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.apito.UserPointTO;
import com.bluemobi.constant.ArticleConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.dao.article.ArticleCategoryDao;
import com.bluemobi.dao.article.ArticleContentDao;
import com.bluemobi.po.article.ArticleCategory;
import com.bluemobi.po.article.ArticleContent;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.service.article.ArticleCategoryService;
import com.bluemobi.service.article.ArticleContentService;
import com.bluemobi.service.cas.CasUserPointService;
import com.bluemobi.to.bts.RefundPageTO;
import com.bluemobi.to.cas.CasUserPointPageTO;
import com.bluemobi.to.cas.CasUserPointTO;



/**
 * 文章管理控制器
 * ClassName: ArticleContentController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("front/casUserPoint")
public class UserPointController extends AbstractAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserPointController.class);
	
	@Autowired
	private CasUserPointService casUserPointService;
	
    
    /**
     * 初始化会员页面
     * memberPoints
     * 
     * @author guoxue
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String articleContentDetail(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        return "front/cas/casUserPointDetail";
    }
    
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<CasUserPointPageTO> pageConsignee(CasUserPointTO cupt) throws ParseException {
      //1,根据用户名模糊分页查询用户列表
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("stage", "".equals(cupt.getStage())?null:cupt.getStage());
        map.put("startDate","".equals(cupt.getStartDate())?null:cupt.getStartDate());
        map.put("endDate","".equals(cupt.getEndDate())?null:cupt.getEndDate());
     	map.put("description","".equals(cupt.getDescription())?null:cupt.getDescription() ); 
     	map.put("userid",getCasUser().getUserid());
    	 Page<CasUserPointPageTO> pages = casUserPointService.page("frontPage","frontPageCount",map,
         		cupt.getPageIndex(), cupt.getPageSize());
    	 int totlePoint = 0;
    	 Map<String, Object> map2 = new HashMap<String, Object>();
    	 map2.put("userid",getCasUser().getUserid());
    	 List<CasUserPoint> list = casUserPointService.selectObjectList(map2);
    	 for(int i= 0 ;i<list.size();i++){
    		 totlePoint += list.get(i).getPoint();
    	 }
    	
    	 for(int i= 0 ;i<pages.getData().size();i++){
    		 pages.data.get(i).setTotlePoint(totlePoint);
    	 }
    	 
         return pages;
    }
   
}
