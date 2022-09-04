package com.bluemobi.apito.bts;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【创建订单】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CreateOrderRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车id
    private List<Integer> cartId = new ArrayList<Integer>();
	//收货地址id
	private Integer consigneeId;
	//支付方式    1、支付宝
	private Integer payType;


    /**设置购物车id*/
	public void setCartId(List<Integer> cartId){
		this.cartId=cartId;
	}
	/**获取购物车id*/
	public List<Integer> getCartId(){
		return this.cartId;
	}
    /**设置收货地址id*/
	public void setConsigneeId(Integer consigneeId){
		this.consigneeId=consigneeId;
	}
	/**获取收货地址id*/
	public Integer getConsigneeId(){
		return this.consigneeId;
	}
    /**设置支付方式    1、支付宝*/
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	/**获取支付方式    1、支付宝*/
	public Integer getPayType(){
		return this.payType;
	}


}
