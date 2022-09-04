package com.bluemobi.apito.goods;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.GoodsSkuSimpleTO;

/**
 * 【搜索商品】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class SearchGoodsResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品sku简单信息
    private List<GoodsSkuSimpleTO> goodsSkuSimples = new ArrayList<GoodsSkuSimpleTO>();


    /**设置商品sku简单信息*/
	public void setGoodsSkuSimples(List<GoodsSkuSimpleTO> goodsSkuSimples){
		this.goodsSkuSimples=goodsSkuSimples;
	}
	/**获取商品sku简单信息*/
	public List<GoodsSkuSimpleTO> getGoodsSkuSimples(){
		return this.goodsSkuSimples;
	}


}
