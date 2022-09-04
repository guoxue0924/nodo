package com.bluemobi.service.bts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.CartOrderRequestTO;
import com.bluemobi.to.bts.OrderDetailTO;
import com.bluemobi.to.bts.SkuOrderRequestTO;

/**
 * 【订单表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:04
 * 
 */
public interface BtsOrderService extends MybatisBaseService {

    /**
     * 根据商品创建订单
     * @author liuyt
     * @date 2015-11-18 上午11:43:27
     * @param userId 
     * @param skuOrderReq TODO
     */
    ResultTO createOrderFromGoodsContent(int userId, SkuOrderRequestTO skuOrderReq,String remark,BigDecimal goodsAmount);

    /**
     * 根据购物车创建订单
     * @author liuyt
     * @date 2015-11-18 上午11:43:53
     * @param cartOrderReq TODO
     */
    ResultTO createOrderFromCart(int userId, CartOrderRequestTO cartOrderReq,String remark,BigDecimal goodsAmount);

    /**
     * 用户删除订单
     * @author liuyt
     * @date 2015-11-19 下午4:14:40
     * @param orderId
     */
    ResultTO deleteOrderForUser(Long orderId);

    /**
     * 用户取消订单
     * @author liuyt
     * @date 2015-11-19 下午4:14:49
     */
    ResultTO cancelOrderForUser(Long orderId);

    /**
     * 用户确认收货订单
     */
    ResultTO confirmOrderForUser(Long orderId);

	/** 
	 * 获取订单详情
	 */  
	OrderDetailTO getDetailInfo(Long orderId);

	/** 
	 * 获取订单信息
	 * 
	 */ 
	List<BtsOrder> selectOrder(Integer orderNumber);

	/** 
	 * 根据订单号获取部分订单信息
	 */ 
	BtsOrder getOrderInfo(String orderNumber);

	/** 
	 * 根据订单号获取订单信息
	 */
//	Object OrderInfo(String orderNum);
	BtsOrder OrderInfo(String orderNum);

	/** 
	 * 根据orderId获取订单信息
	 */
	BtsOrder OrderInfoByOrderId(long orderId);

	/** 
	 * 根据newOutTradeNo获取订单详情信息
	 */
	Object getDetailInfoByOutTradeNo(String newOutTradeNo);

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
	 */
	boolean updateIntegral(CasUserPoint casUserPoint, String newOrderNumber);

}
