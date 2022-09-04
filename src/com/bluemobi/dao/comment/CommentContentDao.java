package com.bluemobi.dao.comment;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.comment.CommentContent;
import com.bluemobi.to.comment.CommentDetailTO;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:28
 * 
 */
public interface CommentContentDao extends MyBatisBaseDao {

    /**
     * 获得所有评论
     * @author HeWeiwen
     * 2015-10-26
     * @param map
     * @return
     */
    List<CommentContent> getCommentContentList(Map<String, Object> map);
    
    /**
     * 条件查询评论分页信息
     * @author HeWeiwen
     * 2015-10-26
     * @param map
     * @return
     */
    List<Map<String, Object>> pageCommentContent(Map<String, Object> map);

    /**
     * 分页总条数
     * @author HeWeiwen
     * 2015-10-26
     * @param map
     * @return
     */
    int pageCommentContentCount(Map<String, Object> map);
    
    /**
     * 批量保存评论信息
     * batchInsert
     * 
     * @author kevin
     * @param contents
     * @return 
     * @since JDK 7
     */
    int batchInsert(List<CommentContent> contents);
    
    /**
     * 获取评论详情
     * getCommentDetail
     * 
     * @author kevin
     * @param id
     * @return 
     * @since JDK 7
     */
    CommentDetailTO getCommentDetail(int id);
    
    /**
     * 获取sku的评论统计信息
     * getCommentStatistics
     * 
     * @author kevin
     * @param skuId
     * @return 
     * @since JDK 7
     */
    List<Map<String, Object>> getCommentStatistics(long skuId);
}
