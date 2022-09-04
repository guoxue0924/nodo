package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【确认收货】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class ConfirmReceiveRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//订单编号
	private String orderNumber;
	//订单状态（固定为1）
	private Integer status;


    /**设置订单编号*/
	public void setOrderNumber(String orderNumber){
		this.orderNumber=orderNumber;
	}
	/**获取订单编号*/
	public String getOrderNumber(){
		return this.orderNumber;
	}
    /**设置订单状态（固定为1）*/
	public void setStatus(Integer status){
		this.status=status;
	}
	/**获取订单状态（固定为1）*/
	public Integer getStatus(){
		return this.status;
	}


}
