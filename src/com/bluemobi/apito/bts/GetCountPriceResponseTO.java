package com.bluemobi.apito.bts;


import java.math.BigDecimal;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取购物车中选中商品总价】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetCountPriceResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//购物车总价
	private BigDecimal countPrice;


    /**设置购物车总价*/
	public void setCountPrice(BigDecimal countPrice){
		this.countPrice=countPrice;
	}
	/**获取购物车总价*/
	public BigDecimal getCountPrice(){
		return this.countPrice;
	}


}
