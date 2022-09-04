package com.bluemobi.apito.goods;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.GoodsCategoryTO;

/**
 * 【获取商品分类】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetGoodsCategoryResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品分类
    private List<GoodsCategoryTO> goodsCategory = new ArrayList<GoodsCategoryTO>();


    /**设置商品分类*/
	public void setGoodsCategory(List<GoodsCategoryTO> goodsCategory){
		this.goodsCategory=goodsCategory;
	}
	/**获取商品分类*/
	public List<GoodsCategoryTO> getGoodsCategory(){
		return this.goodsCategory;
	}


}
