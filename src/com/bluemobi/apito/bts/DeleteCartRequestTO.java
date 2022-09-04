package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【删除购物车信息】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class DeleteCartRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车id
	private Integer cartId;


    /**设置购物车id*/
	public void setCartId(Integer cartId){
		this.cartId=cartId;
	}
	/**获取购物车id*/
	public Integer getCartId(){
		return this.cartId;
	}


}
