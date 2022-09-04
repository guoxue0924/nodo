package com.bluemobi.service.comment;

import com.appcore.service.MybatisBaseService;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:27
 * 
 */
public interface CommentAttachmentService extends MybatisBaseService {

	/** 
	 * 保存评论和附件关系
	 * saveCommentAttachment
	 * 
	 * @author kevin
	 * @param id
	 * @param filePaths 
	 * @param userid TODO
	 * @since JDK 7 
	 */  
	void saveCommentAttachment(Integer id, Long[] imageIds, long userid);

}
