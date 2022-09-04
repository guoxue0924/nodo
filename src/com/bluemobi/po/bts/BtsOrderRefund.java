package com.bluemobi.po.bts;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【退货流程日志表】持久化对象 数据库表：bts_order_refund
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public class BtsOrderRefund extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer refundId;
    // 订单id
    private Integer orderId;
    // 退货单号
    private String refundSn;
    // 订单详情ID
    private Long itemId;
    // 订单号
    private String orderNumber;
    // 需要补交费用 
    private BigDecimal price;
    // 退货原因
    private String reason;
    // 操作者类型。admin：后台管理员；cas：前台会员；
    private String operatorType;
    // 操作者用户 ID
    private Integer operatorUserid;
    // 操作人IP
    private String ip;
    // 创建数据日期
    private Date ctime;
    // 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货；
    private Byte status;
    // 是否标记为删除状态。1：是；0：否；2 彻底删除
    private Byte isDel;

    /** 获取  属性 */
    public Integer getRefundId() {
        return refundId;
    }

    /** 设置  属性 */
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    /** 获取 订单id 属性 */
    public Integer getOrderId() {
        return orderId;
    }

    /** 设置 订单id 属性 */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 退货单号 属性 */
    public String getRefundSn() {
        return refundSn;
    }

    /** 设置 退货单号 属性 */
    public void setRefundSn(String refundSn) {
        this.refundSn = refundSn;
    }

    /** 获取 订单详情ID 属性 */
    public Long getItemId() {
        return itemId;
    }

    /** 设置 订单详情ID 属性 */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /** 获取 订单号 属性 */
    public String getOrderNumber() {
        return orderNumber;
    }

    /** 设置 订单号 属性 */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /** 获取 需要补交费用  属性 */
    public BigDecimal getPrice() {
        return price;
    }

    /** 设置 需要补交费用  属性 */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /** 获取 退货原因 属性 */
    public String getReason() {
        return reason;
    }

    /** 设置 退货原因 属性 */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /** 获取 操作者类型。admin：后台管理员；cas：前台会员； 属性 */
    public String getOperatorType() {
        return operatorType;
    }

    /** 设置 操作者类型。admin：后台管理员；cas：前台会员； 属性 */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /** 获取 操作者用户 ID 属性 */
    public Integer getOperatorUserid() {
        return operatorUserid;
    }

    /** 设置 操作者用户 ID 属性 */
    public void setOperatorUserid(Integer operatorUserid) {
        this.operatorUserid = operatorUserid;
    }

    /** 获取 操作人IP 属性 */
    public String getIp() {
        return ip;
    }

    /** 设置 操作人IP 属性 */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /** 获取 创建数据日期 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建数据日期 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 退货状态。1：已成功退货；2：等待审核(已申请退货)；3：已审核/待退货； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 是否标记为删除状态。1：是；0：否；2 彻底删除 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记为删除状态。1：是；0：否；2 彻底删除 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsOrderRefund");
        sb.append("{refundId=").append(refundId);
        sb.append(", orderId=").append(orderId);
        sb.append(", refundSn=").append(refundSn);
        sb.append(", itemId=").append(itemId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", price=").append(price);
        sb.append(", reason=").append(reason);
        sb.append(", operatorType=").append(operatorType);
        sb.append(", operatorUserid=").append(operatorUserid);
        sb.append(", ip=").append(ip);
        sb.append(", ctime=").append(ctime);
        sb.append(", status=").append(status);
        sb.append(", isDel=").append(isDel);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsOrderRefund) {
            BtsOrderRefund btsOrderRefund = (BtsOrderRefund) obj;
            if (this.getRefundId().equals(btsOrderRefund.getRefundId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getRefundId();
        return pkStr.hashCode();
    }

}