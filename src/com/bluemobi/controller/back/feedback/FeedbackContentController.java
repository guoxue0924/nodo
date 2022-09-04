package com.bluemobi.controller.back.feedback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.service.feedback.FeedbackContentService;


/**
 * 留言反馈模块 控制器
 * @date 2015-10-13 15:38:05 
 */
@Controller
@RequestMapping("back/feedbackContent")
public class FeedbackContentController extends AbstractBackController {
    
    @Autowired
    private FeedbackContentService feebackContentService;
    
    /**
     * 初始化留言列表页
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexFeedback(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/feedback/content.index";
    }
    
    
    /**
     * 获取用户所有的留言列表
     * @author AutoCode E-mail:309444359@qq.com
     * @date 2015-05-16 16:55:05 
     * @param to
     * @return Object
     */
    @RequestMapping(value = "page" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAll(Integer hasName, String key, int pageSize, int pageIndex){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hasName", hasName);
        map.put("key", key);
        Page<Map<String, Object>> pages = feebackContentService.page(map, pageIndex, pageSize);
        
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
    }

    /**
     * 获取用户某一条留言的详情
     * @author AutoCode E-mail:309444359@qq.com
     * @date 2015-05-16 16:55:05 
     * @param to
     * @return Object
     */
    @RequestMapping(value = "detail" , method = RequestMethod.GET)
    public String getDetail(Model model, String contentId){
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("contentId", contentId);
        model.addAttribute("feedback", feebackContentService.selectObject(map));
        return "back/feedback/content.detail";
    }




}
