package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【修改购物车信息】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class ModifyCartRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车id
	private Integer cartId;
	//增加数量
	private Integer quantity;


    /**设置购物车id*/
	public void setCartId(Integer cartId){
		this.cartId=cartId;
	}
	/**获取购物车id*/
	public Integer getCartId(){
		return this.cartId;
	}
    /**设置增加数量*/
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}
	/**获取增加数量*/
	public Integer getQuantity(){
		return this.quantity;
	}


}
