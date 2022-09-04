package com.bluemobi.apito;

import java.util.ArrayList;
import java.util.List;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【一组商品sku简单信息】
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-20 14:29:43 
 */
@JsonIgnoreProperties
public class GoodsSkuSimplesTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//商品sku简单信息    用于商品列表展示
	private List<GoodsSkuSimpleTO> goodsSkuSimple = new ArrayList<GoodsSkuSimpleTO>();


    /**设置商品sku简单信息    用于商品列表展示*/
	public void setGoodsSkuSimple(List<GoodsSkuSimpleTO> goodsSkuSimple){
		this.goodsSkuSimple=goodsSkuSimple;
	}
	/**获取商品sku简单信息    用于商品列表展示*/
	public List<GoodsSkuSimpleTO> getGoodsSkuSimple(){
		return this.goodsSkuSimple;
	}


}
