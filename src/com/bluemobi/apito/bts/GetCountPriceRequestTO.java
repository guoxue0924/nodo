package com.bluemobi.apito.bts;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取购物车中选中商品总价】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetCountPriceRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车id
    private List<Integer> cartId = new ArrayList<Integer>();


    /**设置购物车id*/
	public void setCartId(List<Integer> cartId){
		this.cartId=cartId;
	}
	/**获取购物车id*/
	public List<Integer> getCartId(){
		return this.cartId;
	}


}
