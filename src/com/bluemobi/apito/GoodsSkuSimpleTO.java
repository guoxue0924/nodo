package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【商品sku简单信息】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GoodsSkuSimpleTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//库存量单位id
	private Integer skuId;
	//商品名称
	private String name;
	//图片地址    默认使用sku的第一张图片
	private String image;
	//价格
	private Float price;


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
    /**设置图片地址    默认使用sku的第一张图片*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取图片地址    默认使用sku的第一张图片*/
	public String getImage(){
		return this.image;
	}
    /**设置价格*/
	public void setPrice(Float price){
		this.price=price;
	}
	/**获取价格*/
	public Float getPrice(){
		return this.price;
	}


}
