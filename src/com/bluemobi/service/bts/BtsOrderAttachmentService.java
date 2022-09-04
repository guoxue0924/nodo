package com.bluemobi.service.bts;

import com.appcore.service.MybatisBaseService;

/**
 * 【订单附件关系表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public interface BtsOrderAttachmentService extends MybatisBaseService {

	/** 
	 * 保存退货单和附件关系
	 * saveCommentAttachment
	 * 
	 * @author kevin
	 * @param id
	 * @param filePaths 
	 * @param userid TODO
	 * @since JDK 7 
	 */  
	void saveRefundAttachment(Integer id, Long[] imageIds);

	
}
