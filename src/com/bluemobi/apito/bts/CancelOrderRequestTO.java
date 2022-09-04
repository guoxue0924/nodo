package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【取消订单】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CancelOrderRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//订单编号
	private String orderNumber;
	//取消原因
	private String reason;


    /**设置订单编号*/
	public void setOrderNumber(String orderNumber){
		this.orderNumber=orderNumber;
	}
	/**获取订单编号*/
	public String getOrderNumber(){
		return this.orderNumber;
	}
    /**设置取消原因*/
	public void setReason(String reason){
		this.reason=reason;
	}
	/**获取取消原因*/
	public String getReason(){
		return this.reason;
	}


}
