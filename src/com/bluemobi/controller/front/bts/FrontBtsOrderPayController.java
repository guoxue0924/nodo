package com.bluemobi.controller.front.bts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.po.cas.CasConsignee;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.pay.PayOrderSubmit;
import com.bluemobi.service.bts.BtsCartService;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.service.cas.CasConsigneeService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.CartOrderRequestTO;
import com.bluemobi.to.bts.FastCreateRequestTO;
import com.bluemobi.to.bts.OrderDetailTO;
import com.bluemobi.to.bts.OrderQueryTO;
import com.bluemobi.to.bts.SkuOrderRequestTO;
import com.bluemobi.util.EnumUtil;
import com.bluemobi.util.alipay.config.AlipayConfig;
import com.bluemobi.util.alipay.util.AlipayCore;
import com.bluemobi.util.alipay.util.AlipaySubmit;
import com.bluemobi.util.alipay.util.KuaiDiUtil;

/**
 * ??????
 * 2016-05-23
 * @author  FXZ
 */
@Controller
@RequestMapping("front/bts/order/pay")
public class FrontBtsOrderPayController extends AbstractAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontBtsOrderPayController.class);
    
    @Autowired
    private BtsOrderService btsOrderService;
    @Autowired
    private CasConsigneeService casConsigneeService;
    @Autowired
    private BtsCartService btsCartService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
   
    private CasUser casUser;
    
    private PayOrderSubmit payOrderSubmit;
    
    private BtsOrder btsOrder;
    
    public static String newOutTradeNo = null;
    
    /**
     * ??????????????????????????????????????????UTF-8
     */
    private static String ENCODING = "UTF-8";
    
    /**
     * ?????????????????????????????????????????????URL(???)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
    /**
     * ??????????????????http??????
     */
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";
    
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * ??????????????????
     * getOrderPay
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "toOrder", method = RequestMethod.GET)
    public String getOrderPay(HttpServletRequest request,Integer orderNumber) {
    	List<BtsOrder> orderPaylist=new ArrayList<BtsOrder>();
    	orderPaylist=btsOrderService.selectOrder(orderNumber);
        request.setAttribute("orderPaylist",orderPaylist);
        return "front/bts/order.index";
    } 
    
    /**
     * ???????????? ??????
     * getOrderPay
     * @param request
     * @param orderNumber
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "toPaySuccess", method = RequestMethod.GET)
    public String getOrderPaySuccess(HttpServletRequest request,Integer orderNumber) {
    	List<BtsOrder> orderPaylist=new ArrayList<BtsOrder>();
    	orderPaylist=btsOrderService.selectOrder(orderNumber);
        request.setAttribute("orderPaylist",orderPaylist);
        return "front/bts/pay/pay.success";
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
    @RequestMapping(value = "payOrder", method = RequestMethod.GET)
    public String getPayOrder(HttpServletRequest request,Integer orderNumber) {
    	List<BtsOrder> orderPaylist=new ArrayList<BtsOrder>();
    	String username =getUsername();
    	Integer orderNumber1=5;
    	orderPaylist=btsOrderService.selectOrder(orderNumber1);
    	orderPaylist.add(payOrderSubmit);
        request.setAttribute("orderPaylist",orderPaylist);
//    	return "front/bts/pay/order.pay";
    	return "front/bts/pay/order.alipay";
    }
    
    
    /**
   	 * ???????????????????????????
   	 * @param args
     * @throws UnsupportedEncodingException 
   	 */
    @RequestMapping(value = "useAliPay", method = RequestMethod.GET)
   	public String useAliPay (Model model, HttpServletRequest request,String orderNumber) throws UnsupportedEncodingException {
    	  
    	List<BtsOrder> orderPaylist=new ArrayList<BtsOrder>();
        request.setAttribute("orderPaylist",orderPaylist);
        BtsOrder btsOrder = btsOrderService.getOrderInfo(orderNumber);
        request.setAttribute("btsOrder",btsOrder);
        //?????????????????????????????????????????????????????????????????????
        String out_trade_no = new String(btsOrder.getOrderNumber().getBytes("ISO-8859-1"),"UTF-8");
        
        newOutTradeNo = out_trade_no;
//        String out_trade_no = new String(btsOrder.getOutOrderNumber().getBytes("ISO-8859-1"),"UTF-8");
        //????????????????????? 
        String subject = new String(btsOrder.getOrderNumber().getBytes("ISO-8859-1"),"UTF-8");
//        int subject=Integer.parseInt(subject1);
        //?????????????????????	
        String total_fee = new String(btsOrder.getTotalAmount().toString().getBytes("ISO-8859-1"),"UTF-8");
        //?????????????????????
        String body = new String(btsOrder.getOrderNumber().getBytes("ISO-8859-1"),"UTF-8");
//        String body = new String(btsOrder.getRemark().getBytes("ISO-8859-1"),"UTF-8");
        request.setAttribute("out_trade_no",out_trade_no);
        request.setAttribute("subject",subject);
        request.setAttribute("total_fee",total_fee);
        request.setAttribute("body",body);
        model.addAttribute("btsOrder", btsOrder);
        //??????????????????
        boolean isSuccess = btsOrderService.updateStatus(newOutTradeNo);
        
        
//        return "front/bts/pay/alipayapi";
        return "front/bts/pay/alipay";
	}
       
       
	/**
	* ????????????????????????????????????
	* getOrderDetail
	* @author FXZ
	* @param model
	* @return 
	* @since JDK 7
	*/
	@RequestMapping(value = "goDetail", method = RequestMethod.GET)
	public String getDetailInfoByOutTradeNo(Model model) {
		btsOrderService.updatePayOverStatus(newOutTradeNo);
		OrderDetailTO odTo=(OrderDetailTO) btsOrderService.getDetailInfoByOutTradeNo(newOutTradeNo);
		model.addAttribute("order", odTo);
		model.addAttribute("loggedInUser", getCasUser());
		//??????????????????
		model.addAttribute("kuaidi",KuaiDiUtil.getKuaiDi(odTo));
		//??????????????????
		return "front/bts/order.detail";
	}
       
       
       /**
        * ??????HttpClient 4.3?????????POST??????
        *
        * @param url       ?????????URL
        * @param paramsMap ??????<????????????>Map
        * @return ????????????
        */

       public static String post(String url, Map<String, String> paramsMap) {
           CloseableHttpClient client = HttpClients.createDefault();
           String responseText = "";
           CloseableHttpResponse response = null;
           try {
               HttpPost method = new HttpPost(url);
               if (paramsMap != null) {
                   List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                   for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                       NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                       paramList.add(pair);
                   }
                   method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
               }
               response = client.execute(method);
               HttpEntity entity = response.getEntity();
               if (entity != null) {
                   responseText = EntityUtils.toString(entity);
               }
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               try {
                   response.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
           return responseText;
       }
    
    
}
