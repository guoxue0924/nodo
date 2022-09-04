package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【购物车添加商品】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class AddToCartRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品skuId
	private Long skuId;
	//商品数量
	private Integer quantity;


    /**设置商品skuId*/
	public void setSkuId(Long skuId){
		this.skuId=skuId;
	}
	/**获取商品skuId*/
	public Long getSkuId(){
		return this.skuId;
	}
    /**设置商品数量*/
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}
	/**获取商品数量*/
	public Integer getQuantity(){
		return this.quantity;
	}


}
