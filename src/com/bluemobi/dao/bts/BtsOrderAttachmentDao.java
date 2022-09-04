package com.bluemobi.dao.bts;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【订单附件关系表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public interface BtsOrderAttachmentDao extends MyBatisBaseDao {

	int batchInsert(Map<String, Object> param);
	
}
