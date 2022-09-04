package com.bluemobi.apito.goods;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取商品sku】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetGoodsSkuRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品skuId
	private Integer skuId;


    /**设置商品skuId*/
	public void setSkuId(Integer skuId){
		this.skuId=skuId;
	}
	/**获取商品skuId*/
	public Integer getSkuId(){
		return this.skuId;
	}


}
