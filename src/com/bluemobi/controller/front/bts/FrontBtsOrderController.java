package com.bluemobi.controller.front.bts;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.constant.OrderConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.cas.CasUserDao;
import com.bluemobi.dao.cas.CasUserPointDao;
import com.bluemobi.dao.goods.GoodsContentSkuDao;
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.po.cas.CasConsignee;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.bts.BtsCartService;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.service.cas.CasConsigneeService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.CartOrderRequestTO;
import com.bluemobi.to.bts.FastCreateRequestTO;
import com.bluemobi.to.bts.OrderDetailTO;
import com.bluemobi.to.bts.OrderQueryTO;
import com.bluemobi.to.bts.SkuOrderRequestTO;
import com.bluemobi.util.EnumUtil;
import com.bluemobi.util.alipay.util.KuaiDiUtil;

/**
 * Web端订单中心 
 * @author heweiwen
 * 2016-1-7 上午11:12:11
 */
@Controller
@RequestMapping("front/bts/order")
public class FrontBtsOrderController extends AbstractAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontBtsOrderController.class);
    
    @Autowired
    private BtsOrderService btsOrderService;
    @Autowired
    private CasConsigneeService casConsigneeService;
    @Autowired
    private BtsCartService btsCartService;
    @Autowired
    private CasUserPointDao casUserPointDao;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    
    public static String newOrderNumber = null;
    @Autowired
    private TrendRegionService trendRegionService;

    
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 初始化实物交易订单页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userIndex(Model model, Integer status) {
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("orderStatus", EnumUtil.parseEnum(OrderConstant.OrderStatus.class));
        model.addAttribute("initStatus", status);
        return "front/bts/order.index";
    }
    
    /**
     * 初始化订单核对页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String checkIndex(Model model, Integer[] cartIds ,Integer ifcart) {
        int userid = this.getUserid();
        //1，地区信息
        Map<String, Object> consigneeMap = new HashMap<String, Object>();
        consigneeMap.put("userid", userid);
        consigneeMap.put("isDefault", (byte)1);
        CasConsignee consignee = (CasConsignee) (casConsigneeService.selectObjectList(consigneeMap).size()>0?casConsigneeService.selectObjectList(consigneeMap).get(0):null);
        //4，商品清单
        List<BtsCart> cartList = btsCartService.selectSkuFromCart(cartIds);
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        double countPrice = 0.00;
		Integer countPoint = 0;
        for (BtsCart data:cartList) {
            Long skuId = data.getSkuId();
            Map<String, Object> cartMap = new HashMap<String, Object>();
            GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId.intValue());
            //将购物车商品数量转换成BigDecimal 进行小计价格技术
            BigDecimal quantity = new BigDecimal((short)data.getQuantity());
            double subPrice = goodsSku.getPrice().multiply(quantity).doubleValue();
            //将购物车积分进行累加
            //将购物车积分进行累加
            if(goodsSku.getPriceIntegral()!=null){
            	countPoint+=goodsSku.getPriceIntegral()*data.getQuantity();
            }else{
            	countPoint+=goodsSku.getPrice().intValue()*data.getQuantity()*10;
            }
            countPrice += subPrice;
            cartMap.put("cartId",data.getCartId());
            cartMap.put("quantity",data.getQuantity());
            cartMap.put("skuId",data.getSkuId());
            cartMap.put("price", goodsSku.getPrice());
            cartMap.put("priceIntegral", goodsSku.getPriceIntegral());
            cartMap.put("subPrice",subPrice);
            cartMap.put("name",goodsSku.getName());
            cartMap.put("DefaultImage", goodsSku.getImages().size()>0?goodsSku.getImages().get(0):"");
            resultList.add(cartMap);
        }
      //add by guoxue 2016-06-23 begin
        //通过regionId获取默认地址的运费
        String expressFee = "0.00";
        Integer ef = 0;
        if(consignee!=null){
        	
            
            if("" != consignee.getRegionId()+""){
            	TrendRegion trendRegion = trendRegionService.selectObject(consignee.getRegionId());
            	
                 if(trendRegion == null){
                 	 expressFee = "0.00";
                 }else{
                 	 if( trendRegion.getExpressFee()==null || trendRegion.getExpressFee() == 0 ){
                    	  expressFee = "0.00";
                    }else{
                    	expressFee =trendRegion.getExpressFee().toString() + ".00";
                    	ef = trendRegion.getExpressFee();
                    }
                 }
            } else{
            	 expressFee = "0.00";
            }
        }
        
      
        double totlePrice = countPrice + ef;
        
        //add by fxz 2016-0704 start
        Integer cartId = cartList.get(0).getCartId();
        //add by fxz 2016-0704 end
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("consignee", consignee);
        model.addAttribute("carts", resultList);
        model.addAttribute("totalprice", countPrice);
        model.addAttribute("countPoint", countPoint);
        model.addAttribute("allTotalprice", totlePrice);
        model.addAttribute("expressFee", expressFee);
        //add by fxz 2016-0704 start
        model.addAttribute("cartId", cartId);
        //add by fxz 2016-0704 end
        
        //add by guoxue 2016-06-23 end
        return "front/bts/check.index";
    }
    
    @RequestMapping(value = "fastCheck", method = RequestMethod.POST)
    public String fastCheck(Model model, Integer fastSkuId,Integer fastQuantity) {
        int userid = this.getUserid();
        
        //1，地区信息
        Map<String, Object> consigneeMap = new HashMap<String, Object>();
        consigneeMap.put("userid", userid);
        consigneeMap.put("isDefault", (byte)1);
        CasConsignee consignee = (CasConsignee) (casConsigneeService.selectObjectList(consigneeMap).size()>0?casConsigneeService.selectObjectList(consigneeMap).get(0):null);
        //2，付款信息
        //3，优惠券信息
        
        //4，商品清单
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        double countPrice = 0.00;
        Map<String, Object> cartMap = new HashMap<String, Object>();
        
        GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(fastSkuId.intValue());
        
        //将购物车商品数量转换成BigDecimal 进行小计价格技术
        BigDecimal quantitys = new BigDecimal(fastQuantity.shortValue());
        double subPrice = goodsSku.getPrice().multiply(quantitys).doubleValue();
        countPrice += subPrice;
        //将购物车积分进行累加
        Integer countPoint=0;
        if(goodsSku.getPriceIntegral()!=null){
        	countPoint+=goodsSku.getPriceIntegral()*fastQuantity.shortValue();
        }else{
        	countPoint+=goodsSku.getPrice().intValue()*fastQuantity.shortValue()*10;
        }
        cartMap.put("quantity",fastQuantity);
        cartMap.put("skuId",fastSkuId);
        cartMap.put("price", goodsSku.getPrice());
        cartMap.put("priceIntegral", goodsSku.getPriceIntegral());
        cartMap.put("subPrice",subPrice);
        cartMap.put("name",goodsSku.getName());
        if(goodsSku.getImages() !=null && goodsSku.getImages().size()!=0){
        	cartMap.put("DefaultImage", goodsSku.getImages().get(0));
        }
        
        resultList.add(cartMap);
        
      //add by guoxue 2016-06-23 begin
        //通过regionId获取默认地址的运费
        String expressFee = "";
        Integer ef = 0;
        if(consignee!=null && consignee.getRegionId()!=null &&"" != consignee.getRegionId().toString()){
        	TrendRegion trendRegion = trendRegionService.selectObject(consignee.getRegionId());
        	
             if(trendRegion == null){
             	 expressFee = "0.00";
             }else{
             	 if(trendRegion.getExpressFee() == null || trendRegion.getExpressFee()==0){
                	  expressFee = "0.00";
                }else{
                	expressFee =trendRegion.getExpressFee().toString() + ".00";
                	ef = trendRegion.getExpressFee();
                }
             }
        } else{
        	 expressFee = "0.00";
        }
        
        double totlePrice = countPrice + ef;
        //add by fxz 2016-0704 start
        model.addAttribute("skuId",fastSkuId);
        //add by fxz 2016-0704 end
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("consignee", consignee);
        model.addAttribute("carts", resultList);
        model.addAttribute("totalprice", countPrice);
        model.addAttribute("countPoint", countPoint);
        model.addAttribute("allTotalprice", totlePrice);
        model.addAttribute("expressFee", expressFee);
        //add by guoxue 2016-06-23 end
        return "front/bts/check.index";
    }
    
    
	@RequestMapping(value = "gocarpay", method = RequestMethod.GET)
	public String gocarpay(Model model, HttpServletRequest request) {
		String orderNumber = request.getParameter("orderNumber");
		model.addAttribute("loggedInUser", getCasUser());
		model.addAttribute("order", btsOrderService.OrderInfo(orderNumber));
		return "front/bts/order.gopay";
	}
    
    @RequestMapping(value = "gopay", method = RequestMethod.GET)
    public String gopay(Model model,String order_number) {
        model.addAttribute("loggedInUser", getCasUser());
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", order_number);
		model.addAttribute("order", btsOrderService.OrderInfo(newOrderNumber));
        return "front/bts/order.gopay";
    }
    
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public String getPage(OrderQueryTO queryTO, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", queryTO.getKey());
        map.put("status", queryTO.getOrderStatus() == null ? null : queryTO.getOrderStatus().byteValue());
        map.put("startDate", queryTO.getStartDate());
        map.put("endDate", queryTO.getEndDate());
    	map.put("userid", getUserid());
    	map.put("isUserDel", queryTO.getIsUserDel() == null ? 0 : queryTO.getIsUserDel());
        Page<OrderDetailTO> pages = btsOrderService.page("frontPage","frontPageCount",map,queryTO.getPageIndex(), queryTO.getPageSize());
        model.addAttribute("page", pages);
        model.addAttribute("userDelStatus", "1");
        model.addAttribute("waitPayStatus", OrderConstant.PaymentStatus.WAIT.getCode().toString());
        model.addAttribute("orderStatus", EnumUtil.parseEnum(OrderConstant.OrderStatus.class));
        return "front/bts/order.list";
    }
    /**
     * 进入收货页面
     * getOrderRecieve
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @param orderNumber
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "receive", method = RequestMethod.GET)
    public String getOrderRecieve(Model model, long orderId, String orderNumber) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderNumber", orderNumber);
        return "front/bts/order.receive";
    }
    
    /**
     * 保存收货状态
     * saveOrderRecieve
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "receive", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveOrderRecieve(Model model, long orderId) {
    	try {
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("orderId", orderId);
    		param.put("status", OrderConstant.OrderStatus.SIGNED.getCode().byteValue());
    		btsOrderService.update(param);
    		return ResultTO.newSuccessResultTO(null);
    	} catch(Exception e) {
    		return ResultTO.newFailResultTO("操作失败",null);
    	}
    }
    
    /**
     * 进入取消订单页面
     * getOrderCancel
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "cancel", method = RequestMethod.GET)
    public String getOrderCancel(Model model, long orderId, String orderNumber) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderNumber", orderNumber);
        return "front/bts/order.cancel";
    }
    
    /**
     * 保存取消状态
     * saveOrderCancel
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveOrderCancel(Model model, long orderId) {
    	try {
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("orderId", orderId);
    		param.put("status", OrderConstant.OrderStatus.CANCEL.getCode().byteValue());
    		btsOrderService.update(param);
    		return ResultTO.newSuccessResultTO(null);
    	} catch(Exception e) {
    		return ResultTO.newFailResultTO("操作失败",null);
    	}
    }
    
    /**
     * 订单删除/还原/彻底删除
     * saveOrderDelete
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @param isDelete
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveOrderDelete(Model model, long orderId, String isDelete) {
    	try {
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("orderId", orderId);
    		param.put("isDelUser", isDelete == null ? '0' : isDelete);
    		btsOrderService.update(param);
    		return ResultTO.newSuccessResultTO(null);
    	} catch(Exception e) {
    		return ResultTO.newFailResultTO("操作失败",null);
    	}
    }
    /**
     * 订单支付
     * getOrderPay
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public String getOrderPay(Model model, long orderId) {
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
        model.addAttribute("order", btsOrderService.OrderInfoByOrderId(orderId));
        return "front/bts/order.gopay";
    }
    
    /**
     * 订单详情页
     * getOrderDetail
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getOrderDetail(Model model, long orderId) {
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
    	model.addAttribute("loggedInUser", getCasUser());
    	OrderDetailTO odTo=btsOrderService.getDetailInfo(orderId);
    	model.addAttribute("order", odTo);
    	//查询物流信息
    	model.addAttribute("kuaidi",KuaiDiUtil.getKuaiDi(odTo));
        return "front/bts/order.detail";
    }
    /**
     * 支付跳转页
     * 
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "orderPay" , method = RequestMethod.POST)
    public String orderPay(Model model) {
          return "front/bts/order.tLzfgopay";
    }
    
    
    @RequestMapping(value = "logistics", method = RequestMethod.GET)
    public String logistics(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        return "front/bts/logistics";
    }
 
    /**
     * 订单支付
     * 	三种方式:现金、积分、积分+现金
     * @param model
     * @param response
     * @param request
     * @param cartOrderReq
     * @param fastCreateRequestTO
     * @param cartIds
     * @return
     */
    @RequestMapping(value = "payAll", method = RequestMethod.POST)
    @ResponseBody
	public ResultTO payAll(Model model, HttpServletResponse response,HttpServletRequest request,CartOrderRequestTO cartOrderReq,FastCreateRequestTO fastCreateRequestTO,Integer[] cartIds) {
		try {
			//获取支付类型：现金、积分、积分+现金
			String radio=request.getParameter("radio");
			//备注
			String remark = request.getParameter("remark");
			//订单总金额 当积分加现金支付时,需要修改商品总金额
			BigDecimal goodsAmount=new BigDecimal(0);
			ResultTO resultTo=null;
			
			if (radio == null || "".equals(radio) || "".equals(radio.trim())) {
				return ResultTO.newFailResultTO("对不起您未选择支持类型！", "fail");
			}
			
			//判断支付方式1.现金支付 2.积分支付 3.积分+现金支付
			/**现金支付*/
			if("1".equals(radio)){
				if (cartIds!=null && cartIds.length > 0) {
		            //购物车提交创建订单操作
		            cartOrderReq.setCartList(cartIds);
		            //创建订单
		            resultTo = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
		        }else {
		            //立即购买订单创建处理
		            SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
		            skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
		            skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
		            skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
		            skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
		            skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
		            //创建订单
		            resultTo= btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
		        } 
				//创建订单结果
				if (resultTo.getStatus() == 0) {
	                OrderDetailTO orderTO = (OrderDetailTO) resultTo.getData();
	                BtsOrder object = btsOrderService.OrderInfo(orderTO.getOrderNumber());
	                //成功返回OBJECT
	                return ResultTO.newSuccessResultTO("订单创建成功！", object);
	            }else{
	            	return ResultTO.newSuccessResultTO(resultTo.getMsg(), "fail");
	            }
			}else{
				/**积分或现金支付*/
				Integer xfPoint=0;
				//获取用户总积分
				Integer userPoint = casUserPointDao.selectUserAllPoint(getUserid());
				Integer userTotalIPoint = userPoint==null?0:userPoint;
                //商品总积分
                Integer countPoint=Integer.valueOf(request.getParameter("countPoint"));
				/**积分支付*/
				if("2".equals(radio)){
					
					//购物车购买
					if(cartIds!=null && cartIds.length > 0){
						//用户积分>=商品积分
						if (userTotalIPoint >= countPoint) {
							//用户购买完商品后所剩积分
							xfPoint = -countPoint;
							//创建订单
			            	cartOrderReq.setCartList(cartIds);
				            resultTo = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
						} else {
							return ResultTO.newFailResultTO("您当前可用积分为【"+userTotalIPoint+"积分】,不足以购买此商品【"+countPoint+"积分】！","fail");
						}
			            
					}else{
						xfPoint = -countPoint;
						/**立即购买积分支付*/
						SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
				        skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
				        skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
				        skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
				        skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
				        skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
				        //创建订单
				        resultTo= btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
					}
				}
				
				//输入金额
				Integer inputPrice=0;
				/**积分+现金支付*/
				if("3".equals(radio)){
			        /**检查输入积分与金额是否满支付条件*/ 	
					resultTo=checkPoint(request, xfPoint, goodsAmount);
					if(resultTo.getStatus() != 0){
						return ResultTO.newFailResultTO(resultTo.getMsg(), "fail"); 
					}else{
						Map<String,Object> map=(Map) resultTo.getData();
						xfPoint=(Integer) map.get("xfPoint");
						inputPrice=(Integer) map.get("inputPrice");
						goodsAmount=(BigDecimal) map.get("goodsAmount");
					}
					
					//判断用户是立即购买还是购物车购买
					if(cartIds!=null && cartIds.length > 0){
						cartOrderReq.setCartList(cartIds);
			            //创建订单
			            resultTo = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
					}else{
						//立即购买
						SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
				        skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
				        skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
				        skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
				        skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
				        skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
				        //创建订单
				        resultTo= btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
					}
			            
				}
				
				//获取订单信息
				OrderDetailTO orderTO = (OrderDetailTO) resultTo.getData();
                String orderNumber = orderTO.getOrderNumber();
                BtsOrder object = btsOrderService.OrderInfo(orderTO.getOrderNumber());
				//扣积分
	            if (resultTo.getStatus() == 0 ) {
	            	//纯积分支付
	            	if("2".equals(radio) || (inputPrice==0 && "3".equals(radio) )){
	            		/**扣除用户积分并修改订单状态*/ 
						CasUserPoint cup=new CasUserPoint();
						cup.setUserid(getUserid());
						cup.setPointType(4);
						cup.setPointName("积分消耗");
						cup.setPoint(xfPoint);
						cup.setCtime(new Date());
						cup.setStatus(0);
						if("3".equals(radio)){
							cup.setIsUsed(0);
						}else{
							cup.setIsUsed(1);
						}
						cup.setOrderNumber(orderNumber);
						if(!btsOrderService.updateIntegral(cup,orderNumber)){
							return ResultTO.newFailResultTO("积分扣减失败！", "fail");
						}
	            	}
	            	
					//如果是积分支付 或者 积分+现金支付时输入现金为0，回转orderId中转订单详情页
					if("2".equals(radio) || (inputPrice==0 && "3".equals(radio) )){
						return ResultTO.newSuccessResultTO("订单创建成功！", object.getOrderId());
					}else{
						//跳转支付页
						return ResultTO.newSuccessResultTO("订单创建成功！", object);
					}
	            }else{
            		return ResultTO.newFailResultTO("交易失败！", "fail");
	            }
			}
				
		} catch (Exception e) {
			LOGGER.error("交易失败！",e);
			return ResultTO.newFailResultTO("交易失败！", "fail");
		}
	}
    
    
    /**
     * 
     * @param request
     * @param xfPoint 消费积分
     * @param goodsAmount 订单总金额 当积分加现金支付时,需要修改商品总金额
     * @return
     */
    public ResultTO checkPoint(HttpServletRequest request,Integer xfPoint,BigDecimal goodsAmount){
    	try {
			//输入积分
			Integer inputPoint=0;
			//输入金额
			Integer inputPrice=0;
			
			if(request.getParameter("checkitRMB")!=null && request.getParameter("checkitRMB")!="" && request.getParameter("checkitRMB").trim()!=""){
				inputPrice=Integer.parseInt(request.getParameter("checkitRMB"));
			}
			if(request.getParameter("checkitJF")!=null && request.getParameter("checkitJF")!="" && request.getParameter("checkitJF").trim()!=""){
				inputPoint=Integer.parseInt(request.getParameter("checkitJF"));
			}
			
			//商品总金额
			Double totalprice=Double.valueOf(request.getParameter("totalprice"));
			//输入的支付的总金额
			double vPrice=inputPrice+inputPoint/10;
			
			//获取用户总积分
			Integer userPoint = casUserPointDao.selectUserAllPoint(getUserid());
			Integer userTotalIPoint = userPoint==null?0:userPoint;
			//商品总积分
			Integer countPoint=Integer.valueOf(request.getParameter("countPoint"));
			
			//商品总金额要与用户输入的总金额，并且用户积分数大于等于输入积分数
			if(vPrice==totalprice.intValue() && userTotalIPoint>=inputPoint){
				// 转换积分类型并取整
				xfPoint=-inputPoint/10*10; 
				goodsAmount=new BigDecimal(inputPrice);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("xfPoint", xfPoint);
				map.put("goodsAmount", goodsAmount);
				map.put("inputPrice", inputPrice);
				
				return ResultTO.newSuccessResultTO(null, map);
			}else{
				return ResultTO.newFailResultTO(("输入积分+现金有误，或您的积分【"+userTotalIPoint+"积分】,不足以购买此商品【"+countPoint+"积分】【"+totalprice+"元】！"), "fail");
			}
		} catch (Exception e) {
			LOGGER.error("checkPoint error!"+e);
			e.printStackTrace();
			return ResultTO.newFailResultTO(("交易失败！"), "fail");
		}
		
    	
    }
}
