package com.bluemobi.to.bts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;
import com.bluemobi.po.bts.BtsOrderItem;

public class OrderDetailTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//
	private Long orderId;
	// 支付方式 ID
	private Short paymentId;
	// 网关标志别名
	private String paymentAlias;
	// 订单号
	private String orderNumber;
	// 外部订单号
	private String outOrderNumber;
	// 订单类型。0：普通；1：团购；2：抢购
	private Byte orderType;
	// 用户ID
	private Integer userid;
	// 用户名
	private String username;
	// 订单备注
	private String remark;
	// 订单状态。0：待处理 1：已签收 2：待付款 3：付款成功 4：待发货 5：已发货
	private Byte status;
	// 支付时间
	private Date payTime;
	// 支付状态。1：已支付；0：待支付；
	private Byte payStatus;
	// 支付类型。1：货到付款；0：在线支付；
	private Byte payType;
	// 物流公司编号
	private String logisticsCom;
	// 物流单号
	private String logisticsNumber;
	// 收货人姓名
	private String consigneeName;
	// 收货人所在地区 ID，填写最后一级
	private Integer consigneeRegionId;
	// 地区名称
	private String consigneeRegionName;
	// 收货人详细地址
	private String consigneeAddress;
	// 收货人邮编
	private String consigneeZipcode;
	// 收货人邮箱
	private String consigneeEmail;
	// 收货人电话
	private String consigneeMobile;
	// 是否有发票。1：有；0：无；
	private Byte isInvoice;
	// 发票税额
	private BigDecimal invoiceTax;
	// 发票抬头
	private String invoiceBelong;
	// 运费
	private BigDecimal freight;
	// 总数量
	private Integer count;
	// 总金额
	private BigDecimal totalAmount;
	// 总金额
	private BigDecimal buyPrice;
	// 积分抵用金额
	private BigDecimal couponAmount;
	// 送货时间
	private Date deliveryTime;
	// 收货时间
	private Date finishTime;
	// 是否取消：0否，1是
	private Byte isCancel;
	// 是否加急 1是 0否
	private Byte isUrgent;
	// 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货;
	private Byte isRefund;
	// 是否标记为删除状态。1：是；0：否；
	private Byte isDel;
	// 用户操作，是否标记为删除状态。1：是；0：否；-1：彻底删除；
	private Byte isDelUser;
	// 创建时间
	private Date ctime;

	private List<BtsOrderItem> items;

	// 评价时间
	private Date commentTime;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Short getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Short paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentAlias() {
		return paymentAlias;
	}

	public void setPaymentAlias(String paymentAlias) {
		this.paymentAlias = paymentAlias;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOutOrderNumber() {
		return outOrderNumber;
	}

	public void setOutOrderNumber(String outOrderNumber) {
		this.outOrderNumber = outOrderNumber;
	}

	public Byte getOrderType() {
		return orderType;
	}

	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
	}

	
	public String getLogisticsCom() {
		return logisticsCom;
	}

	public void setLogisticsCom(String logisticsCom) {
		this.logisticsCom = logisticsCom;
	}

	public String getLogisticsNumber() {
		return logisticsNumber;
	}

	public void setLogisticsNumber(String logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public Integer getConsigneeRegionId() {
		return consigneeRegionId;
	}

	public void setConsigneeRegionId(Integer consigneeRegionId) {
		this.consigneeRegionId = consigneeRegionId;
	}

	public String getConsigneeRegionName() {
		return consigneeRegionName;
	}

	public void setConsigneeRegionName(String consigneeRegionName) {
		this.consigneeRegionName = consigneeRegionName;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeZipcode() {
		return consigneeZipcode;
	}

	public void setConsigneeZipcode(String consigneeZipcode) {
		this.consigneeZipcode = consigneeZipcode;
	}

	public String getConsigneeEmail() {
		return consigneeEmail;
	}

	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public Byte getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Byte isInvoice) {
		this.isInvoice = isInvoice;
	}

	public BigDecimal getInvoiceTax() {
		return invoiceTax;
	}

	public void setInvoiceTax(BigDecimal invoiceTax) {
		this.invoiceTax = invoiceTax;
	}

	public String getInvoiceBelong() {
		return invoiceBelong;
	}

	public void setInvoiceBelong(String invoiceBelong) {
		this.invoiceBelong = invoiceBelong;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Byte getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Byte isCancel) {
		this.isCancel = isCancel;
	}

	public Byte getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Byte isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Byte getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(Byte isRefund) {
		this.isRefund = isRefund;
	}

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
	}

	public Byte getIsDelUser() {
		return isDelUser;
	}

	public void setIsDelUser(Byte isDelUser) {
		this.isDelUser = isDelUser;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public List<BtsOrderItem> getItems() {
		return items;
	}

	public void setItems(List<BtsOrderItem> items) {
		this.items = items;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
}
