package com.bluemobi.service.bts;

import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【订单的商品表(即订单详情表)】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public interface BtsOrderItemService extends MybatisBaseService {
	
	Map<String, Object> getItemForRefund(long itemId);

}
