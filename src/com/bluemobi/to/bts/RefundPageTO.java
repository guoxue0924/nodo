/**
 * Project Name:nodo 
 * File Name:OrderQueryTO.java 
 * Package Name:com.bluemobi.to.bts 
 * Date:2015年12月28日下午4:50:13 
 */
package com.bluemobi.to.bts;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 
 * ClassName: RefundPageTO Date: 2016年1月27日下午2:33:10
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class RefundPageTO extends AbstractObject {

	private static final long serialVersionUID = 3375674661613694452L;

	//
	private int refundId;
	// 退货单号
	private String refundSn;
	// 商品名称
	private String goodsName;
	// 商品数量
	private int buyNum;
	// 订单号
	private String orderNumber;
	// 退货状态
	private Byte status;
	// 退单时间
	private Date ctime;
	// 商品skuId
	private long skuId;
	// 商品图片
	private String goodsImage;

	private long orderId;

	private BigDecimal buyPrice;

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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

}
