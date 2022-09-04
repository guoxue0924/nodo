package com.bluemobi.util.alipay.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class PaymentAction {  
	/** 
     * 获取支付宝交易订单号 
     * @return 
     */  
    public synchronized static String getOrderNum(){  
        Date date=new Date();  
        DateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
        return df.format(date);  
    }  
  
  
   /* protected HttpServletRequest getRequest() {  
        return ServletActionContext.getRequest();  
    }  */
  
  
  
    //支付宝交易订单号  
    String orderNum = getOrderNum();  
      
    // 此次交易的总金额  
//    getRequest().setAttribute("totalMoney","0.01");  
    //此次交易的订单号  
//    getRequest().setAttribute("out_trade_no", orderNum);  
    //商品名称描述  
//    getRequest().setAttribute("subject", "商品名称");  
      
      
    //这里省略了将此次订单信息存到数据库的流程
}

