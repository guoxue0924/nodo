package com.bluemobi.controller.api.bts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.appcore.util.JsonUtil;
import com.bluemobi.apito.ConsigneeTO;
import com.bluemobi.apito.ItemTO;
import com.bluemobi.apito.OrderTO;
import com.bluemobi.apito.bts.CancelOrderRequestTO;
import com.bluemobi.apito.bts.CancelOrderResponseTO;
import com.bluemobi.apito.bts.ConfirmReceiveRequestTO;
import com.bluemobi.apito.bts.ConfirmReceiveResponseTO;
import com.bluemobi.apito.bts.CreateOrderRequestTO;
import com.bluemobi.apito.bts.CreateOrderResponseTO;
import com.bluemobi.apito.bts.FastCreateRequestTO;
import com.bluemobi.apito.bts.FastCreateResponseTO;
import com.bluemobi.apito.bts.GetOrderInfoRequestTO;
import com.bluemobi.apito.bts.GetOrderInfoResponseTO;
import com.bluemobi.apito.bts.GetOrderlistRequestTO;
import com.bluemobi.apito.bts.GetOrderlistResponseTO;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.constant.OrderConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.po.bts.BtsOrderItem;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.CartOrderRequestTO;
import com.bluemobi.to.bts.OrderDetailTO;
import com.bluemobi.to.bts.SkuOrderRequestTO;

/**
 * 【订单】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-29 15:00:04
 * 
 */
@Controller(value = "btsOrderAPIController")
@RequestMapping("api/btsOrder") 
public class BtsOrderAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BtsOrderAPIController.class);
    
    @Autowired
    private BtsOrderService btsOrderService;


	/**
	 * 创建订单
     * @param request
     * @param createOrderRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "createOrder")
    @ResponseBody
    public ResultTO createOrder(HttpServletRequest request, String json ) {
        
        CreateOrderRequestTO createOrderRequestTO = JsonUtil.getObject(json, CreateOrderRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteAddr(), createOrderRequestTO.toString() });
        String remark = request.getParameter("remark");
        BigDecimal goodsAmount=new BigDecimal(0);
        try{
        	CartOrderRequestTO cartOrderReq = new CartOrderRequestTO();
        	cartOrderReq.setCartList(createOrderRequestTO.getCartId().toArray(new Integer[createOrderRequestTO.getCartId().size()]));
        	cartOrderReq.setConsigneeId(createOrderRequestTO.getConsigneeId());
        	cartOrderReq.setIp(request.getRemoteAddr());
        	cartOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
        	cartOrderReq.setPaymentId(createOrderRequestTO.getPayType());
            //处理业务
        	ResultTO rt = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
        	
        	
        	CreateOrderResponseTO createOrderResponseTO = new CreateOrderResponseTO();
        	OrderDetailTO detailTO = (OrderDetailTO) rt.getData();
            OrderTO order = convertToForApi(detailTO);
            createOrderResponseTO.setOrder(order);
        	
            return ResultTO.newSuccessResultTO("创建订单成功！", createOrderResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("创建订单出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteAddr(), createOrderRequestTO.toString() });
            return ResultTO.newFailResultTO("创建订单失败！", null);
        }
		
    }

	/** 
	 * convertToForApi
	 * 
	 * @author kevin
	 * @param detailTO
	 * @return 
	 * @since JDK 7 
	 */  
	private OrderTO convertToForApi(OrderDetailTO detailTO) {
		OrderTO to = new OrderTO();
		to.setCount(detailTO.getCount());
		to.setCtime(detailTO.getCtime());
		to.setDeliveryTime(detailTO.getDeliveryTime());
		to.setFreight(detailTO.getFreight());
		to.setLogisticsNumber(detailTO.getLogisticsNumber());
		to.setOrderId(detailTO.getOrderId().intValue());
		to.setOrderNumber(detailTO.getOrderNumber());
		to.setPayStatus(detailTO.getPayStatus().intValue());
		to.setPayTime(detailTO.getPayTime());
		to.setStatus(detailTO.getStatus().intValue());
		to.setTotalAmount(detailTO.getTotalAmount());
		List<ItemTO> itemList = new ArrayList<ItemTO>();
		ItemTO item;
		for(BtsOrderItem orderItem : detailTO.getItems()) {
			item = new ItemTO();
			item.setBuyNum(orderItem.getBuyNum().intValue());
			item.setBuyPrice(orderItem.getBuyPrice());
			item.setGoodsImage(orderItem.getGoodsImage());
			item.setGoodsName(orderItem.getGoodsName());
			item.setIsComment(orderItem.getIsComment() == null?false:true);
			item.setItemId(orderItem.getItemId().intValue());
			item.setSkuId(orderItem.getSkuId().intValue());
			itemList.add(item);
		}
		to.setItems(itemList);
		ConsigneeTO ct = new ConsigneeTO();
		ct.setAddress(detailTO.getConsigneeAddress());
		ct.setMobilePhone(detailTO.getConsigneeMobile());
		ct.setName(detailTO.getConsigneeName());
		ct.setRegionId3(detailTO.getConsigneeRegionId());
		to.setConsignee(ct);
		return to;
	}

	/**
	 * 订单详情
     * @param request
     * @param getOrderInfoRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "getOrderInfo")
    @ResponseBody
    public ResultTO getOrderInfo(HttpServletRequest request, String json) {
        
        GetOrderInfoRequestTO getOrderInfoRequestTO = JsonUtil.getObject(json, GetOrderInfoRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteAddr(), getOrderInfoRequestTO.toString() });
        
        try{
            //处理业务
        	OrderDetailTO detailTO = btsOrderService.getDetailInfo(getOrderInfoRequestTO.getOrderId().longValue());
        	
        	GetOrderInfoResponseTO getOrderInfoResponseTO = new GetOrderInfoResponseTO();
        	OrderTO order = convertToForApi(detailTO);
        	getOrderInfoResponseTO.setOrder(order);
        	
            return ResultTO.newSuccessResultTO("订单详情成功！", getOrderInfoResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("订单详情出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteAddr(), getOrderInfoRequestTO.toString() });
            return ResultTO.newFailResultTO("订单详情失败！", null);
        }
		
    }

	/**
	 * 取消订单
     * @param request
     * @param cancelOrderRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "cancelOrder")
    @ResponseBody
    public ResultTO cancelOrder(HttpServletRequest request, String json) {
        
        CancelOrderRequestTO cancelOrderRequestTO = JsonUtil.getObject(json, CancelOrderRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteAddr(), cancelOrderRequestTO.toString() });
        
        try{
            //处理业务
        	Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("orderNumber", cancelOrderRequestTO.getOrderNumber());
        	BtsOrder order = btsOrderService.selectObject(paramMap);
        	ResultTO rt = btsOrderService.cancelOrderForUser(order.getOrderId());
        	
        	CancelOrderResponseTO cancelOrderResponseTO = new CancelOrderResponseTO();
        	
            if(rt.getStatus() == BaseConstant.STATUS_FAILURE) {
            	return ResultTO.newFailResultTO("取消订单失败！", null);
            }
            return ResultTO.newSuccessResultTO("取消订单成功！", cancelOrderResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("取消订单出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteAddr(), cancelOrderRequestTO.toString() });
            return ResultTO.newFailResultTO("取消订单失败！", null);
        }
		
    }

	/**
	 * 立即购买
     * @param request
     * @param fastCreateRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "fastCreate")
    @ResponseBody
    public ResultTO fastCreate(HttpServletRequest request, String json) {
        
        FastCreateRequestTO fastCreateRequestTO = JsonUtil.getObject(json, FastCreateRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteAddr(), fastCreateRequestTO.toString() });
        String remark = request.getParameter("remark");
        BigDecimal goodsAmount=new BigDecimal(0);
        try{
            //处理业务
        	SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
        	skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
        	skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
        	skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
        	skuOrderReq.setIp(request.getRemoteAddr());
        	skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
        	skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
            //处理业务
        	ResultTO rt = btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
        	
        	FastCreateResponseTO fastCreateResponseTO = new FastCreateResponseTO();
        	OrderDetailTO detailTO = (OrderDetailTO) rt.getData();
        	OrderTO order = convertToForApi(detailTO);
        	fastCreateResponseTO.setOrder(order);
        	
            return ResultTO.newSuccessResultTO("立即购买成功！", fastCreateResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("立即购买出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteAddr(), fastCreateRequestTO.toString() });
            return ResultTO.newFailResultTO("立即购买失败！", null);
        }
		
    }

	/**
	 * 订单列表
     * @param request
     * @param getOrderlistRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "getOrderlist")
    @ResponseBody
    public ResultTO getOrderlist(HttpServletRequest request, String json) {
        
        GetOrderlistRequestTO getOrderlistRequestTO = JsonUtil.getObject(json, GetOrderlistRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getOrderlistRequestTO.toString() });
        
        try{
            //处理业务
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", getOrderlistRequestTO.getStatus());
         	map.put("userid", getUserid());
         	map.put("isUserDel", 0);
            Page<OrderDetailTO> pages = btsOrderService.page("frontPage","frontPageCount",map,
            		 getOrderlistRequestTO.getPageNum(), getOrderlistRequestTO.getPageSize());
            List<OrderTO> orders = null;
            if(pages.getCount() > 1) {
                orders = new ArrayList<OrderTO>();
            	for(OrderDetailTO detailTO : pages.getData()) {
            	    orders.add(convertToForApi(detailTO));
            	}
            }
            
            GetOrderlistResponseTO getOrderlistResponseTO = new GetOrderlistResponseTO();
            getOrderlistResponseTO.setOrders(orders);
            
            return ResultTO.newSuccessResultTO("订单列表成功！", getOrderlistResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("订单列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getOrderlistRequestTO.toString() });
            return ResultTO.newFailResultTO("订单列表失败！", null);
        }
		
    }

	/**
	 * 确认收货
     * @param request
     * @param confirmReceiveRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "confirmReceive")
    @ResponseBody
    public ResultTO confirmReceive(HttpServletRequest request,String json) {

        ConfirmReceiveRequestTO confirmReceiveRequestTO = JsonUtil.getObject(json, ConfirmReceiveRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), confirmReceiveRequestTO.toString() });
        
        try{
            //处理业务
        	Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("orderNumber", confirmReceiveRequestTO.getOrderNumber());
        	BtsOrder order = btsOrderService.selectObject(paramMap);
        	paramMap.clear();
        	paramMap.put("orderId", order.getOrderId());
        	paramMap.put("status", OrderConstant.OrderStatus.SIGNED.getCode().byteValue());
        	btsOrderService.update(paramMap);
        	
        	ConfirmReceiveResponseTO confirmReceiveResponseTO = new ConfirmReceiveResponseTO();
        	
            return ResultTO.newSuccessResultTO("确认收货成功！", confirmReceiveResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("确认收货出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), confirmReceiveRequestTO.toString() });
            return ResultTO.newFailResultTO("确认收货失败！", null);
        }
		
    }




}
