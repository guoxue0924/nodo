package com.bluemobi.apito;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【商品sku】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GoodsSkuTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//库存量单位id
	private Integer skuId;
	//商品名称
	private String name;
	//图片地址
    private List<String> images = new ArrayList<String>();
	//价格
	private Float price;
	//库存
	private Integer stock;
	//属性    当前sku所属商品的所有属性，用于展示商品下面所有的属性  
	private List<PropertyTO> propertys = new ArrayList<PropertyTO>();
	//sku属性    当前sku的属性，用于展示当前sku的属性
	private List<SkuPropertyTO> skuPropertys = new ArrayList<SkuPropertyTO>();
	//商品ID
	private Integer contentId;
	//市场价格
	private Float priceMarket;
	//是否已收藏    0:未收藏；  1:已收藏；
	private Integer isFavorite;
	//商品详情
	private String body;


    /**设置库存量单位id*/
	public void setSkuId(Integer skuId){
		this.skuId=skuId;
	}
	/**获取库存量单位id*/
	public Integer getSkuId(){
		return this.skuId;
	}
    /**设置商品名称*/
	public void setName(String name){
		this.name=name;
	}
	/**获取商品名称*/
	public String getName(){
		return this.name;
	}
    /**设置图片地址*/
	public void setImages(List<String> images){
		this.images=images;
	}
	/**获取图片地址*/
	public List<String> getImages(){
		return this.images;
	}
    /**设置价格*/
	public void setPrice(Float price){
		this.price=price;
	}
	/**获取价格*/
	public Float getPrice(){
		return this.price;
	}
    /**设置库存*/
	public void setStock(Integer stock){
		this.stock=stock;
	}
	/**获取库存*/
	public Integer getStock(){
		return this.stock;
	}
    /**设置属性    当前sku所属商品的所有属性，用于展示商品下面所有的属性  */
	public void setPropertys(List<PropertyTO> propertys){
		this.propertys=propertys;
	}
	/**获取属性    当前sku所属商品的所有属性，用于展示商品下面所有的属性  */
	public List<PropertyTO> getPropertys(){
		return this.propertys;
	}
    /**设置sku属性    当前sku的属性，用于展示当前sku的属性*/
	public void setSkuPropertys(List<SkuPropertyTO> skuPropertys){
		this.skuPropertys=skuPropertys;
	}
	/**获取sku属性    当前sku的属性，用于展示当前sku的属性*/
	public List<SkuPropertyTO> getSkuPropertys(){
		return this.skuPropertys;
	}
    /**设置商品ID*/
	public void setContentId(Integer contentId){
		this.contentId=contentId;
	}
	/**获取商品ID*/
	public Integer getContentId(){
		return this.contentId;
	}
    /**设置市场价格*/
	public void setPriceMarket(Float priceMarket){
		this.priceMarket=priceMarket;
	}
	/**获取市场价格*/
	public Float getPriceMarket(){
		return this.priceMarket;
	}
    /**设置是否已收藏    0:未收藏；  1:已收藏；*/
	public void setIsFavorite(Integer isFavorite){
		this.isFavorite=isFavorite;
	}
	/**获取是否已收藏    0:未收藏；  1:已收藏；*/
	public Integer getIsFavorite(){
		return this.isFavorite;
	}
    /**设置商品详情*/
	public void setBody(String body){
		this.body=body;
	}
	/**获取商品详情*/
	public String getBody(){
		return this.body;
	}


}
