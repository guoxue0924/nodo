package com.bluemobi.dao.bts;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.to.bts.OrderDetailTO;

/**
 * 【订单表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:04
 * 
 */
public interface BtsOrderDao extends MyBatisBaseDao {
	
	OrderDetailTO getDetailInfo(Long orderId);
	
	List<OrderDetailTO> frontPage(Map<String, Object> param);
	  
	int frontPageCount(Map<String, Object> param);
	
	List<Map<String, Object>> saleHistoryPage(Map<String, Object> param);
	
	int saleHistoryCount(Map<String, Object> param);

	/** 
	 * 获取订单信息 
	 */ 
	List<BtsOrder> selectMap(Integer orderNumber);

	/** 
	 * 根据订单号获取部分订单信息
	 */
	BtsOrder getOrderInfo(String orderNumber);

	/** 
	 * 根据订单号获取订单信息
	 */
	Object OrderInfo(String orderNum);

	/** 
	 * 根据orderId获取订单信息
	 */
	BtsOrder OrderInfoByOrderId(long orderId);

	/** 
	 * 根据newOutTradeNo获取订单详情信息
	 */
	OrderDetailTO getDetailInfoByOutTradeNo(String newOutTradeNo);

	/** 
	 * 根据newOutTradeNo修改未支付前的订单状态
	 */
	boolean updateStatus(String newOutTradeNo);
	
	/** 
	 * 根据newOutTradeNo修改支付完的订单状态
	 */
	boolean updatePayOverStatus(String newOutTradeNo);

	/** 
	 * 根据username扣除用户积分
	 * Integer newUserintegralTotal,
	 */
	boolean updateIntegral(CasUser casUser);

	
}
