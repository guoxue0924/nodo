package com.bluemobi.to.bts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;

public class RefundDetailTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//
	private int refundId;
	// 退货单号
	private String refundSn;
	// 商品名称
	private String goodsName;
	// 商品数量
	private int number;
	// 订单号
	private String orderNumber;
	// 退货原因
	private String reason;
	// 退货状态
	private Byte status;
	// 退单时间
	private Date ctime;

	private String username;

	private BigDecimal buyPrice;

	private List<String> picList;

	private long skuId;

	private String goodsImage;
	
	private BigDecimal totalAmount;

	public int getRefundId() {
		return refundId;
	}

	public void setRefundId(int refundId) {
		this.refundId = refundId;
	}

	public String getRefundSn() {
		return refundSn;
	}

	public void setRefundSn(String refundSn) {
		this.refundSn = refundSn;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
