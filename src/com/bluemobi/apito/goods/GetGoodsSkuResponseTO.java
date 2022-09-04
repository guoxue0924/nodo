package com.bluemobi.apito.goods;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.GoodsSkuTO;

/**
 * 【获取商品sku】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetGoodsSkuResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品sku
	private GoodsSkuTO goodsSku = new GoodsSkuTO();


    /**设置商品sku*/
	public void setGoodsSku(GoodsSkuTO goodsSku){
		this.goodsSku=goodsSku;
	}
	/**获取商品sku*/
	public GoodsSkuTO getGoodsSku(){
		return this.goodsSku;
	}


}
