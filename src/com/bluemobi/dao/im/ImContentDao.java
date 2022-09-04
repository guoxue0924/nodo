package com.bluemobi.dao.im;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【ＩＭ消息内容表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 11:01:20
 * 
 */
public interface ImContentDao extends MyBatisBaseDao {

	List<Map<String, Object>> selectContact(Map<String, Object> param);
	
}
