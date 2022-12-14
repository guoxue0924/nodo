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
 * Web??????????????? 
 * @author heweiwen
 * 2016-1-7 ??????11:12:11
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
     * ?????????????????????????????????
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
     * ???????????????????????????
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String checkIndex(Model model, Integer[] cartIds ,Integer ifcart) {
        int userid = this.getUserid();
        //1???????????????
        Map<String, Object> consigneeMap = new HashMap<String, Object>();
        consigneeMap.put("userid", userid);
        consigneeMap.put("isDefault", (byte)1);
        CasConsignee consignee = (CasConsignee) (casConsigneeService.selectObjectList(consigneeMap).size()>0?casConsigneeService.selectObjectList(consigneeMap).get(0):null);
        //4???????????????
        List<BtsCart> cartList = btsCartService.selectSkuFromCart(cartIds);
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        double countPrice = 0.00;
		Integer countPoint = 0;
        for (BtsCart data:cartList) {
            Long skuId = data.getSkuId();
            Map<String, Object> cartMap = new HashMap<String, Object>();
            GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId.intValue());
            //?????????????????????????????????BigDecimal ????????????????????????
            BigDecimal quantity = new BigDecimal((short)data.getQuantity());
            double subPrice = goodsSku.getPrice().multiply(quantity).doubleValue();
            //??????????????????????????????
            //??????????????????????????????
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
        //??????regionId???????????????????????????
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
        
        //1???????????????
        Map<String, Object> consigneeMap = new HashMap<String, Object>();
        consigneeMap.put("userid", userid);
        consigneeMap.put("isDefault", (byte)1);
        CasConsignee consignee = (CasConsignee) (casConsigneeService.selectObjectList(consigneeMap).size()>0?casConsigneeService.selectObjectList(consigneeMap).get(0):null);
        //2???????????????
        //3??????????????????
        
        //4???????????????
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        double countPrice = 0.00;
        Map<String, Object> cartMap = new HashMap<String, Object>();
        
        GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(fastSkuId.intValue());
        
        //?????????????????????????????????BigDecimal ????????????????????????
        BigDecimal quantitys = new BigDecimal(fastQuantity.shortValue());
        double subPrice = goodsSku.getPrice().multiply(quantitys).doubleValue();
        countPrice += subPrice;
        //??????????????????????????????
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
        //??????regionId???????????????????????????
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
     * ??????????????????
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
     * ??????????????????
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
    		return ResultTO.newFailResultTO("????????????",null);
    	}
    }
    
    /**
     * ????????????????????????
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
     * ??????????????????
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
    		return ResultTO.newFailResultTO("????????????",null);
    	}
    }
    
    /**
     * ????????????/??????/????????????
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
    		return ResultTO.newFailResultTO("????????????",null);
    	}
    }
    /**
     * ????????????
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
     * ???????????????
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
    	//??????????????????
    	model.addAttribute("kuaidi",KuaiDiUtil.getKuaiDi(odTo));
        return "front/bts/order.detail";
    }
    /**
     * ???????????????
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
     * ????????????
     * 	????????????:????????????????????????+??????
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
			//?????????????????????????????????????????????+??????
			String radio=request.getParameter("radio");
			//??????
			String remark = request.getParameter("remark");
			//??????????????? ???????????????????????????,???????????????????????????
			BigDecimal goodsAmount=new BigDecimal(0);
			ResultTO resultTo=null;
			
			if (radio == null || "".equals(radio) || "".equals(radio.trim())) {
				return ResultTO.newFailResultTO("????????????????????????????????????", "fail");
			}
			
			//??????????????????1.???????????? 2.???????????? 3.??????+????????????
			/**????????????*/
			if("1".equals(radio)){
				if (cartIds!=null && cartIds.length > 0) {
		            //?????????????????????????????????
		            cartOrderReq.setCartList(cartIds);
		            //????????????
		            resultTo = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
		        }else {
		            //??????????????????????????????
		            SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
		            skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
		            skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
		            skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
		            skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
		            skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
		            //????????????
		            resultTo= btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
		        } 
				//??????????????????
				if (resultTo.getStatus() == 0) {
	                OrderDetailTO orderTO = (OrderDetailTO) resultTo.getData();
	                BtsOrder object = btsOrderService.OrderInfo(orderTO.getOrderNumber());
	                //????????????OBJECT
	                return ResultTO.newSuccessResultTO("?????????????????????", object);
	            }else{
	            	return ResultTO.newSuccessResultTO(resultTo.getMsg(), "fail");
	            }
			}else{
				/**?????????????????????*/
				Integer xfPoint=0;
				//?????????????????????
				Integer userPoint = casUserPointDao.selectUserAllPoint(getUserid());
				Integer userTotalIPoint = userPoint==null?0:userPoint;
                //???????????????
                Integer countPoint=Integer.valueOf(request.getParameter("countPoint"));
				/**????????????*/
				if("2".equals(radio)){
					
					//???????????????
					if(cartIds!=null && cartIds.length > 0){
						//????????????>=????????????
						if (userTotalIPoint >= countPoint) {
							//????????????????????????????????????
							xfPoint = -countPoint;
							//????????????
			            	cartOrderReq.setCartList(cartIds);
				            resultTo = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
						} else {
							return ResultTO.newFailResultTO("???????????????????????????"+userTotalIPoint+"?????????,???????????????????????????"+countPoint+"????????????","fail");
						}
			            
					}else{
						xfPoint = -countPoint;
						/**????????????????????????*/
						SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
				        skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
				        skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
				        skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
				        skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
				        skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
				        //????????????
				        resultTo= btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
					}
				}
				
				//????????????
				Integer inputPrice=0;
				/**??????+????????????*/
				if("3".equals(radio)){
			        /**????????????????????????????????????????????????*/ 	
					resultTo=checkPoint(request, xfPoint, goodsAmount);
					if(resultTo.getStatus() != 0){
						return ResultTO.newFailResultTO(resultTo.getMsg(), "fail"); 
					}else{
						Map<String,Object> map=(Map) resultTo.getData();
						xfPoint=(Integer) map.get("xfPoint");
						inputPrice=(Integer) map.get("inputPrice");
						goodsAmount=(BigDecimal) map.get("goodsAmount");
					}
					
					//????????????????????????????????????????????????
					if(cartIds!=null && cartIds.length > 0){
						cartOrderReq.setCartList(cartIds);
			            //????????????
			            resultTo = btsOrderService.createOrderFromCart(getUserid(), cartOrderReq,remark,goodsAmount);
					}else{
						//????????????
						SkuOrderRequestTO skuOrderReq = new SkuOrderRequestTO();
				        skuOrderReq.setQuantity(fastCreateRequestTO.getBuyNum());
				        skuOrderReq.setSkuId(fastCreateRequestTO.getSkuId());
				        skuOrderReq.setConsigneeId(fastCreateRequestTO.getConsigneeId());
				        skuOrderReq.setOrderType(OrderConstant.OrderType.COMMON.getCode().byteValue());
				        skuOrderReq.setPaymentId(fastCreateRequestTO.getPayType());
				        //????????????
				        resultTo= btsOrderService.createOrderFromGoodsContent(getUserid(), skuOrderReq,remark,goodsAmount);
					}
			            
				}
				
				//??????????????????
				OrderDetailTO orderTO = (OrderDetailTO) resultTo.getData();
                String orderNumber = orderTO.getOrderNumber();
                BtsOrder object = btsOrderService.OrderInfo(orderTO.getOrderNumber());
				//?????????
	            if (resultTo.getStatus() == 0 ) {
	            	//???????????????
	            	if("2".equals(radio) || (inputPrice==0 && "3".equals(radio) )){
	            		/**???????????????????????????????????????*/ 
						CasUserPoint cup=new CasUserPoint();
						cup.setUserid(getUserid());
						cup.setPointType(4);
						cup.setPointName("????????????");
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
							return ResultTO.newFailResultTO("?????????????????????", "fail");
						}
	            	}
	            	
					//????????????????????? ?????? ??????+??????????????????????????????0?????????orderId?????????????????????
					if("2".equals(radio) || (inputPrice==0 && "3".equals(radio) )){
						return ResultTO.newSuccessResultTO("?????????????????????", object.getOrderId());
					}else{
						//???????????????
						return ResultTO.newSuccessResultTO("?????????????????????", object);
					}
	            }else{
            		return ResultTO.newFailResultTO("???????????????", "fail");
	            }
			}
				
		} catch (Exception e) {
			LOGGER.error("???????????????",e);
			return ResultTO.newFailResultTO("???????????????", "fail");
		}
	}
    
    
    /**
     * 
     * @param request
     * @param xfPoint ????????????
     * @param goodsAmount ??????????????? ???????????????????????????,???????????????????????????
     * @return
     */
    public ResultTO checkPoint(HttpServletRequest request,Integer xfPoint,BigDecimal goodsAmount){
    	try {
			//????????????
			Integer inputPoint=0;
			//????????????
			Integer inputPrice=0;
			
			if(request.getParameter("checkitRMB")!=null && request.getParameter("checkitRMB")!="" && request.getParameter("checkitRMB").trim()!=""){
				inputPrice=Integer.parseInt(request.getParameter("checkitRMB"));
			}
			if(request.getParameter("checkitJF")!=null && request.getParameter("checkitJF")!="" && request.getParameter("checkitJF").trim()!=""){
				inputPoint=Integer.parseInt(request.getParameter("checkitJF"));
			}
			
			//???????????????
			Double totalprice=Double.valueOf(request.getParameter("totalprice"));
			//???????????????????????????
			double vPrice=inputPrice+inputPoint/10;
			
			//?????????????????????
			Integer userPoint = casUserPointDao.selectUserAllPoint(getUserid());
			Integer userTotalIPoint = userPoint==null?0:userPoint;
			//???????????????
			Integer countPoint=Integer.valueOf(request.getParameter("countPoint"));
			
			//????????????????????????????????????????????????????????????????????????????????????????????????
			if(vPrice==totalprice.intValue() && userTotalIPoint>=inputPoint){
				// ???????????????????????????
				xfPoint=-inputPoint/10*10; 
				goodsAmount=new BigDecimal(inputPrice);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("xfPoint", xfPoint);
				map.put("goodsAmount", goodsAmount);
				map.put("inputPrice", inputPrice);
				
				return ResultTO.newSuccessResultTO(null, map);
			}else{
				return ResultTO.newFailResultTO(("????????????+?????????????????????????????????"+userTotalIPoint+"?????????,???????????????????????????"+countPoint+"????????????"+totalprice+"?????????"), "fail");
			}
		} catch (Exception e) {
			LOGGER.error("checkPoint error!"+e);
			e.printStackTrace();
			return ResultTO.newFailResultTO(("???????????????"), "fail");
		}
		
    	
    }
}
