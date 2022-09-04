package com.bluemobi.to.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【立即购买】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class FastCreateRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品id
	private Integer skuId;
	//购买数量
	private Integer buyNum;
	//收货地址id
	private Integer consigneeId;
	//支付方式    1、支付宝
	private Integer payType;


    /**设置商品id*/
	public void setSkuId(Integer skuId){
		this.skuId=skuId;
	}
	/**获取商品id*/
	public Integer getSkuId(){
		return this.skuId;
	}
    /**设置购买数量*/
	public void setBuyNum(Integer buyNum){
		this.buyNum=buyNum;
	}
	/**获取购买数量*/
	public Integer getBuyNum(){
		return this.buyNum;
	}
    /**设置收货地址id*/
	public void setConsigneeId(Integer consigneeId){
		this.consigneeId=consigneeId;
	}
	/**获取收货地址id*/
	public Integer getConsigneeId(){
		return this.consigneeId;
	}
    /**设置支付方式    1、支付宝*/
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	/**获取支付方式    1、支付宝*/
	public Integer getPayType(){
		return this.payType;
	}


}
