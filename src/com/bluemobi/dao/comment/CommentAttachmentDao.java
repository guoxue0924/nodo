package com.bluemobi.dao.comment;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:27
 * 
 */
public interface CommentAttachmentDao extends MyBatisBaseDao {

	int batchInsert(Map<String, Object> param);
	
}
