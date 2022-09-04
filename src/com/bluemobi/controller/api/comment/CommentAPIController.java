package com.bluemobi.controller.api.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.appcore.util.JsonUtil;
import com.bluemobi.apito.CommentTO;
import com.bluemobi.apito.comment.AddCommentRequestTO;
import com.bluemobi.apito.comment.GetCommentsRequestTO;
import com.bluemobi.apito.comment.GetCommentsResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.comment.CommentDetailTO;
import com.bluemobi.to.comment.ItemCommentTO;
import com.bluemobi.to.comment.OrderCommentTO;

/**
 * 【评论】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-29 15:00:04
 * 
 */
@Controller(value = "commentAPIController")
@RequestMapping("api/comment")
public class CommentAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentAPIController.class);
    
    @Autowired
    private CommentContentService commentContentService;


	/**
	 * 发表评论
     * @param request
     * @param addCommentRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "addComment")
    @ResponseBody
    public ResultTO addComment(HttpServletRequest request,String json ) {
        
        AddCommentRequestTO addCommentRequestTO = JsonUtil.getObject(json, AddCommentRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), addCommentRequestTO.toString() });
        
        try{
            //处理业务
        	OrderCommentTO commentTO = new OrderCommentTO();
        	ItemCommentTO ict = new ItemCommentTO();
        	ict.setContent(addCommentRequestTO.getContent());
        	ict.setRankBase(addCommentRequestTO.getRankBase());
        	ict.setSkuId(addCommentRequestTO.getSkuId().longValue());
        	List<ItemCommentTO> list = new ArrayList<ItemCommentTO>();
        	list.add(ict);
        	commentTO.setItemComments(list);
        	commentTO.setRankLogistics(addCommentRequestTO.getRankLogistics());
        	commentTO.setRankSpeed(addCommentRequestTO.getRankSpeed());
        	commentContentService.addComment(commentTO, getUserid(), request.getRemoteHost());
            
            return ResultTO.newSuccessResultTO("发表评论成功！", commentTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("发表评论出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), addCommentRequestTO.toString() });
            return ResultTO.newFailResultTO("发表评论失败！", null);
        }
		
    }

	/**
	 * 获取评论列表
     * @param request
     * @param getCommentsRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "getComments")
    @ResponseBody
    public ResultTO getComments(HttpServletRequest request, String json ) {
        
        GetCommentsRequestTO getCommentsRequestTO = JsonUtil.getObject(json, GetCommentsRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getCommentsRequestTO.toString() });
        
        try{
            //处理业务
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("skuId", getCommentsRequestTO.getSkuId());
        	Page<CommentDetailTO> pages;
        	if(getCommentsRequestTO.getIsDefault()) {
        		pages = commentContentService.page(map, 1, 3);
        	} else {
        		pages = commentContentService.page(map, getCommentsRequestTO.getPageNum(), getCommentsRequestTO.getPageSize());
        	}
        	List<CommentTO> commentList = null;
        	if(pages.getCount() > 0) {
        		commentList = new ArrayList<CommentTO>();
        		for (CommentDetailTO detailTO : pages.getData()){
        			commentList.add(convertToForApi(detailTO));
        		}
        	}
        	GetCommentsResponseTO response = new GetCommentsResponseTO();
        	response.setComment(commentList);
        	response.setCount(pages.getCount());
        	response.setPage(pages.getPage());
        	response.setPageSize(pages.getPageSize());
            return ResultTO.newSuccessResultTO("获取评论列表成功！", response);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取评论列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getCommentsRequestTO.toString() });
            return ResultTO.newFailResultTO("获取评论列表失败！", null);
        }
		
    }

	/** 
	 * convertToForApi
	 * 
	 * @author kevin
	 * @param detailTO
	 * @return 
	 * @since JDK 7 
	 */  
	private CommentTO convertToForApi(CommentDetailTO detailTO) {
		CommentTO to = new CommentTO();
		to.setAvatar(detailTO.getGoodsImage());
		to.setContent(detailTO.getContent());
		to.setNickname(detailTO.getNickname());
		to.setRankBase(detailTO.getRankBase().intValue());
		to.setRankLogistics(detailTO.getRankLogistics().intValue());
		to.setRankSpeed(detailTO.getRankSpeed().intValue());
		to.setUserid(detailTO.getUserid());
		return to;
	}




}
