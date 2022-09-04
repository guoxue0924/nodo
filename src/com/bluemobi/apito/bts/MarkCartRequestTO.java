package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【勾选购物车商品】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class MarkCartRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车id
	private Integer cartId;
	//是否是选中    0、去掉勾选  1、勾选
	private Integer isMark;


    /**设置购物车id*/
	public void setCartId(Integer cartId){
		this.cartId=cartId;
	}
	/**获取购物车id*/
	public Integer getCartId(){
		return this.cartId;
	}
    /**设置是否是选中    0、去掉勾选  1、勾选*/
	public void setIsMark(Integer isMark){
		this.isMark=isMark;
	}
	/**获取是否是选中    0、去掉勾选  1、勾选*/
	public Integer getIsMark(){
		return this.isMark;
	}


}
