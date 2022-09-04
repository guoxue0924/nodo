package com.bluemobi.service.comment;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.comment.CommentContent;
import com.bluemobi.to.comment.AddGoodsCommentTO;
import com.bluemobi.to.comment.CommentDetailTO;
import com.bluemobi.to.comment.OrderCommentTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:28
 * 
 */
public interface CommentContentService extends MybatisBaseService {

    /**
     * 逻辑删除评论（修改is_del的状态）
     * @author HeWeiwen
     * 2015-10-22
     * @param id
     */
    void deleteLogic(String id);
    
    /**
     * 根据categoryId和toId获得用户评论
     * @author HeWeiwen
     * 2015-10-22
     * @param categoryId
     * @param toId
     * @return
     */
    List<CommentContent> getCommentContentList(int categoryId,int toId);
    
    /**
     * 添加评论
     * @author HeWeiwen
     * 2015-10-22
     * @param addGoodsCommentTO
     */
    void saveCommentContentToApi(AddGoodsCommentTO addGoodsCommentTO);

	/** 
	 * 添加评论
	 * addComment
	 * 
	 * @author kevin
	 * @param commentTO
	 * @param userid 
	 * @param ip 
	 * @since JDK 7 
	 */  
	void addComment(OrderCommentTO commentTO, int userid, String ip);

	/** 
	 * 获取评论详情
	 * getCommentDetail
	 * 
	 * @author kevin
	 * @param id
	 * @return 
	 * @since JDK 7 
	 */  
	CommentDetailTO getCommentDetail(Integer id);

	/** 
	 * getCommentStatistics
	 * 
	 * @author kevin
	 * @param skuId
	 * @return 
	 * @since JDK 7 
	 */  
	Map<String, Object> getCommentStatistics(Long skuId);
}
