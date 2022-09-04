package com.bluemobi.to.bts;

import com.appcore.model.AbstractObject;

public abstract class AbstractOrderRequestTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Integer consigneeId;

    private Integer paymentId;

    private Byte orderType;

    private String ip;

    public Integer getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Integer consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
