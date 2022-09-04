package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【购物车】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CartTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//购物车id
	private Integer cartId;
	//库存量单位id
	private Integer skuId;
	//商品名称
	private String name;
	//图片地址
	private String image;
	//价格
	private Float price;
	//商品数量
	private Integer quantity;
	//是否是选中    0、去掉勾选  1、勾选
	private Integer isMark;


    /**设置购物车id*/
	public void setCartId(Integer cartId){
		this.cartId=cartId;
	}
	/**获取购物车id*/
	public Integer getCartId(){
		return this.cartId;
	}
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
	public void setImage(String image){
		this.image=image;
	}
	/**获取图片地址*/
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
    /**设置商品数量*/
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}
	/**获取商品数量*/
	public Integer getQuantity(){
		return this.quantity;
	}
    /**设置是否是选中    0、去掉勾选  1、勾选*/
	public void setIsMark(Integer isMark){
		this.isMark=isMark;
	}
	/**获取是否是选中    0、去掉勾选  1、勾选*/
	public Integer getIsMark(){
		return this.isMark;
	}


}
