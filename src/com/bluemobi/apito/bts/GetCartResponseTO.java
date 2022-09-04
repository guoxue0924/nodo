package com.bluemobi.apito.bts;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.CartTO;

/**
 * 【获取购物车信息】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetCartResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车
    private List<CartTO> carts = new ArrayList<CartTO>();
	//购物车总价
	private BigDecimal countPrice;


    /**设置购物车*/
	public void setCarts(List<CartTO> carts){
		this.carts=carts;
	}
	/**获取购物车*/
	public List<CartTO> getCarts(){
		return this.carts;
	}
    /**设置购物车总价*/
	public void setCountPrice(BigDecimal countPrice){
		this.countPrice=countPrice;
	}
	/**获取购物车总价*/
	public BigDecimal getCountPrice(){
		return this.countPrice;
	}


}
