package com.bluemobi.controller.front.cas;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.feedback.FeedbackContent;
import com.bluemobi.service.feedback.FeedbackContentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.comment.CommentDetailTO;
import com.bluemobi.to.comment.OrderCommentTO;




/**
 * 咨询客服/消息反馈控制器
 * ClassName: FrontCasFeedbackController
 * Date: 2016年3月9日下午4:42:05

 * @author kevin
 * @version 
 * @since JDK 7
 */
//@Controller
//@RequestMapping("front/cas/feedback")
public class FrontCasFeedbackController extends AbstractAPIController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontCasFeedbackController.class);
    
    @Autowired
    private FeedbackContentService feedbackContentService;
    
    /**
     * 初始化咨询客服主页面
     * index
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        return "front/cas/feedback.index";
    }
    
    /**
     * 分页查询咨询客服内容
     * pageComment
     * 
     * @author kevin
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<FeedbackContent> pageFeedback(Model model, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getCasUser().getUserid());
        return feedbackContentService.page(map, pageIndex, pageSize);
    }
    
    /**
     * 进入咨询客服页面
     * goFeedback
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String goFeedback(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        return "front/cas/feedback.add";
    }
    
    /**
     * 保存评价信息
     * saveComment
     * 
     * @author kevin
     * @param model
     * @param comments
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveComment(Model model, OrderCommentTO commentTO) {
        try {
//        	commentContentService.addComment(commentTO, this.getUserid(), this.getAccessIp());
        	return ResultTO.newSuccessResultTO(null);
        } catch (Exception e) {
        	return ResultTO.newFailResultTO("操作失败", null);
        }
    }
    
}
