package com.bluemobi.dao.bts;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【订单的商品表(即订单详情表)】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public interface BtsOrderItemDao extends MyBatisBaseDao {

	Map<String, Object> getItemForRefund(long itemId);
	
}
