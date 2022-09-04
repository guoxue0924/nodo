package com.bluemobi.apito.goods;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.SkuPropertyTO;

/**
 * 【通过商品属性获取商品sku】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetGoodsSkuByPropertyRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品id
	private Integer contentId;
	//sku属性
    private List<SkuPropertyTO> skuProperty = new ArrayList<SkuPropertyTO>();


    /**设置商品id*/
	public void setContentId(Integer contentId){
		this.contentId=contentId;
	}
	/**获取商品id*/
	public Integer getContentId(){
		return this.contentId;
	}
    /**设置sku属性*/
	public void setSkuProperty(List<SkuPropertyTO> skuProperty){
		this.skuProperty=skuProperty;
	}
	/**获取sku属性*/
	public List<SkuPropertyTO> getSkuProperty(){
		return this.skuProperty;
	}


}
