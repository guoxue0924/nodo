package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.OrderTO;

/**
 * 【立即购买】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class FastCreateResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//订单
	private OrderTO order = new OrderTO();


    /**设置订单*/
	public void setOrder(OrderTO order){
		this.order=order;
	}
	/**获取订单*/
	public OrderTO getOrder(){
		return this.order;
	}


}
