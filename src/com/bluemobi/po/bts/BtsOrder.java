package com.bluemobi.po.bts;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【订单表】持久化对象 数据库表：bts_order
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:04
 * 
 */
public class BtsOrder extends AbstractObject {

    public static final long serialVersionUID = 1L;

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
    // 订单状态。0：待处理   1：已签收    2：待付款   3：付款成功   4：待发货   5：已发货 
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
    private Byte isInvoice = 1;
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
    // 积分抵用金额
    private BigDecimal couponAmount;
    // 送货时间
    private Date deliveryTime;
    // 收货时间
    private Date finishTime;
    // 是否取消：0否，1是
    private Byte isCancel = 0;
    // 是否加急 1是 0否
    private Byte isUrgent = 0;
    // 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货;
    private Byte isRefund;
    // 是否标记为删除状态。1：是；0：否；
    private Byte isDel = 0;
    // 用户操作，是否标记为删除状态。1：是；0：否；-1：彻底删除；
    private Byte isDelUser = 0;
    // 创建时间
    private Date ctime;

    /** 获取  属性 */
    public Long getOrderId() {
        return orderId;
    }

    /** 设置  属性 */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /** 获取 支付方式 ID 属性 */
    public Short getPaymentId() {
        return paymentId;
    }

    /** 设置 支付方式 ID 属性 */
    public void setPaymentId(Short paymentId) {
        this.paymentId = paymentId;
    }

    /** 获取 网关标志别名 属性 */
    public String getPaymentAlias() {
        return paymentAlias;
    }

    /** 设置 网关标志别名 属性 */
    public void setPaymentAlias(String paymentAlias) {
        this.paymentAlias = paymentAlias;
    }

    /** 获取 订单号 属性 */
    public String getOrderNumber() {
        return orderNumber;
    }

    /** 设置 订单号 属性 */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /** 获取 外部订单号 属性 */
    public String getOutOrderNumber() {
        return outOrderNumber;
    }

    /** 设置 外部订单号 属性 */
    public void setOutOrderNumber(String outOrderNumber) {
        this.outOrderNumber = outOrderNumber;
    }

    /** 获取 订单类型。0：普通；1：团购；2：抢购 属性 */
    public Byte getOrderType() {
        return orderType;
    }

    /** 设置 订单类型。0：普通；1：团购；2：抢购 属性 */
    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    /** 获取 用户ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 用户名 属性 */
    public String getUsername() {
        return username;
    }

    /** 设置 用户名 属性 */
    public void setUsername(String username) {
        this.username = username;
    }

    /** 获取 订单备注 属性 */
    public String getRemark() {
        return remark;
    }

    /** 设置 订单备注 属性 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获取 订单状态。0：待处理   1：已签收    2：待付款   3：付款成功   4：待发货   5：已发货  属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 订单状态。0：待处理   1：已签收    2：待付款   3：付款成功   4：待发货   5：已发货  属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 支付时间 属性 */
    public Date getPayTime() {
        return payTime;
    }

    /** 设置 支付时间 属性 */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /** 获取 支付状态。1：已支付；0：待支付； 属性 */
    public Byte getPayStatus() {
        return payStatus;
    }

    /** 设置 支付状态。1：已支付；0：待支付； 属性 */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /** 获取 支付类型。1：货到付款；0：在线支付； 属性 */
    public Byte getPayType() {
        return payType;
    }

    /** 设置 支付类型。1：货到付款；0：在线支付； 属性 */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getLogisticsCom() {
		return logisticsCom;
	}

	public void setLogisticsCom(String logisticsCom) {
		this.logisticsCom = logisticsCom;
	}

	/** 获取 物流单号 属性 */
    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    /** 设置 物流单号 属性 */
    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    /** 获取 收货人姓名 属性 */
    public String getConsigneeName() {
        return consigneeName;
    }

    /** 设置 收货人姓名 属性 */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    /** 获取 收货人所在地区 ID，填写最后一级 属性 */
    public Integer getConsigneeRegionId() {
        return consigneeRegionId;
    }

    /** 设置 收货人所在地区 ID，填写最后一级 属性 */
    public void setConsigneeRegionId(Integer consigneeRegionId) {
        this.consigneeRegionId = consigneeRegionId;
    }

    /** 获取 地区名称 属性 */
    public String getConsigneeRegionName() {
        return consigneeRegionName;
    }

    /** 设置 地区名称 属性 */
    public void setConsigneeRegionName(String consigneeRegionName) {
        this.consigneeRegionName = consigneeRegionName;
    }

    /** 获取 收货人详细地址 属性 */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /** 设置 收货人详细地址 属性 */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    /** 获取 收货人邮编 属性 */
    public String getConsigneeZipcode() {
        return consigneeZipcode;
    }

    /** 设置 收货人邮编 属性 */
    public void setConsigneeZipcode(String consigneeZipcode) {
        this.consigneeZipcode = consigneeZipcode;
    }

    /** 获取 收货人邮箱 属性 */
    public String getConsigneeEmail() {
        return consigneeEmail;
    }

    /** 设置 收货人邮箱 属性 */
    public void setConsigneeEmail(String consigneeEmail) {
        this.consigneeEmail = consigneeEmail;
    }

    /** 获取 收货人电话 属性 */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    /** 设置 收货人电话 属性 */
    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    /** 获取 是否有发票。1：有；0：无； 属性 */
    public Byte getIsInvoice() {
        return isInvoice;
    }

    /** 设置 是否有发票。1：有；0：无； 属性 */
    public void setIsInvoice(Byte isInvoice) {
        this.isInvoice = isInvoice;
    }

    /** 获取 发票税额 属性 */
    public BigDecimal getInvoiceTax() {
        return invoiceTax;
    }

    /** 设置 发票税额 属性 */
    public void setInvoiceTax(BigDecimal invoiceTax) {
        this.invoiceTax = invoiceTax;
    }

    /** 获取 发票抬头 属性 */
    public String getInvoiceBelong() {
        return invoiceBelong;
    }

    /** 设置 发票抬头 属性 */
    public void setInvoiceBelong(String invoiceBelong) {
        this.invoiceBelong = invoiceBelong;
    }

    /** 获取 运费 属性 */
    public BigDecimal getFreight() {
        return freight;
    }

    /** 设置 运费 属性 */
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    /** 获取 总数量 属性 */
    public Integer getCount() {
        return count;
    }

    /** 设置 总数量 属性 */
    public void setCount(Integer count) {
        this.count = count;
    }

    /** 获取 总金额 属性 */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /** 设置 总金额 属性 */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /** 获取 积分抵用金额 属性 */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /** 设置 积分抵用金额 属性 */
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    /** 获取 送货时间 属性 */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /** 设置 送货时间 属性 */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /** 获取 是否取消：0否，1是 属性 */
    public Byte getIsCancel() {
        return isCancel;
    }

    /** 设置 是否取消：0否，1是 属性 */
    public void setIsCancel(Byte isCancel) {
        this.isCancel = isCancel;
    }

    /** 获取 是否加急 1是 0否 属性 */
    public Byte getIsUrgent() {
        return isUrgent;
    }

    /** 设置 是否加急 1是 0否 属性 */
    public void setIsUrgent(Byte isUrgent) {
        this.isUrgent = isUrgent;
    }

    /** 获取 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货; 属性 */
    public Byte getIsRefund() {
        return isRefund;
    }

    /** 设置 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货; 属性 */
    public void setIsRefund(Byte isRefund) {
        this.isRefund = isRefund;
    }

    /** 获取 是否标记为删除状态。1：是；0：否； 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记为删除状态。1：是；0：否； 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 用户操作，是否标记为删除状态。1：是；0：否；-1：彻底删除； 属性 */
    public Byte getIsDelUser() {
        return isDelUser;
    }

    /** 设置 用户操作，是否标记为删除状态。1：是；0：否；-1：彻底删除； 属性 */
    public void setIsDelUser(Byte isDelUser) {
        this.isDelUser = isDelUser;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsOrder");
        sb.append("{orderId=").append(orderId);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", paymentAlias=").append(paymentAlias);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", outOrderNumber=").append(outOrderNumber);
        sb.append(", orderType=").append(orderType);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", payTime=").append(payTime);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", payType=").append(payType);
        sb.append(", logisticsCom=").append(logisticsCom);
        sb.append(", logisticsNumber=").append(logisticsNumber);
        sb.append(", consigneeName=").append(consigneeName);
        sb.append(", consigneeRegionId=").append(consigneeRegionId);
        sb.append(", consigneeRegionName=").append(consigneeRegionName);
        sb.append(", consigneeAddress=").append(consigneeAddress);
        sb.append(", consigneeZipcode=").append(consigneeZipcode);
        sb.append(", consigneeEmail=").append(consigneeEmail);
        sb.append(", consigneeMobile=").append(consigneeMobile);
        sb.append(", isInvoice=").append(isInvoice);
        sb.append(", invoiceTax=").append(invoiceTax);
        sb.append(", invoiceBelong=").append(invoiceBelong);
        sb.append(", freight=").append(freight);
        sb.append(", count=").append(count);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", isCancel=").append(isCancel);
        sb.append(", isUrgent=").append(isUrgent);
        sb.append(", isRefund=").append(isRefund);
        sb.append(", isDel=").append(isDel);
        sb.append(", isDelUser=").append(isDelUser);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsOrder) {
            BtsOrder btsOrder = (BtsOrder) obj;
            if (this.getOrderId().equals(btsOrder.getOrderId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getOrderId();
        return pkStr.hashCode();
    }

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}