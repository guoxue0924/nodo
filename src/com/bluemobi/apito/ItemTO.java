package com.bluemobi.apito;


import java.math.BigDecimal;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【订单子项】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class ItemTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//订单项编号
	private Integer itemId;
	//商品编号
	private Integer skuId;
	//商品名称
	private String goodsName;
	//商品图片
	private String goodsImage;
	//购买价格
	private BigDecimal buyPrice;
	//购买数量
	private Integer buyNum;
	//是否评价
	private Boolean isComment;


    /**设置订单项编号*/
	public void setItemId(Integer itemId){
		this.itemId=itemId;
	}
	/**获取订单项编号*/
	public Integer getItemId(){
		return this.itemId;
	}
    /**设置商品编号*/
	public void setSkuId(Integer skuId){
		this.skuId=skuId;
	}
	/**获取商品编号*/
	public Integer getSkuId(){
		return this.skuId;
	}
    /**设置商品名称*/
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}
	/**获取商品名称*/
	public String getGoodsName(){
		return this.goodsName;
	}
    /**设置商品图片*/
	public void setGoodsImage(String goodsImage){
		this.goodsImage=goodsImage;
	}
	/**获取商品图片*/
	public String getGoodsImage(){
		return this.goodsImage;
	}
    /**设置购买价格*/
	public void setBuyPrice(BigDecimal buyPrice){
		this.buyPrice=buyPrice;
	}
	/**获取购买价格*/
	public BigDecimal getBuyPrice(){
		return this.buyPrice;
	}
    /**设置购买数量*/
	public void setBuyNum(Integer buyNum){
		this.buyNum=buyNum;
	}
	/**获取购买数量*/
	public Integer getBuyNum(){
		return this.buyNum;
	}
    /**设置是否评价*/
	public void setIsComment(Boolean isComment){
		this.isComment=isComment;
	}
	/**获取是否评价*/
	public Boolean getIsComment(){
		return this.isComment;
	}


}
