package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【订单详情】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetOrderInfoRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//订单id    1、代付款  2、待收货  3、已完成  4、已取消
	private Integer orderId;


    /**设置订单id    1、代付款  2、待收货  3、已完成  4、已取消*/
	public void setOrderId(Integer orderId){
		this.orderId=orderId;
	}
	/**获取订单id    1、代付款  2、待收货  3、已完成  4、已取消*/
	public Integer getOrderId(){
		return this.orderId;
	}


}
