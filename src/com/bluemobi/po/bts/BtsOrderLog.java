package com.bluemobi.po.bts;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【订单操作日志表】持久化对象 数据库表：bts_order_log
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public class BtsOrderLog extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 订单日志ID
    private Integer logId;
    // 管理员ID
    private Integer adminUserid;
    // 操作分类 1日志 2跟踪记录 3售后消息
    private Byte type;
    // 逻辑外键 订单ID
    private Integer orderId;
    // 订单号
    private String orderNumber;
    // 操作内容
    private String content;
    // 描述
    private String remark;
    // 操作人IP
    private String ip;
    // 创建数据日期
    private Date ctime;

    /** 获取 订单日志ID 属性 */
    public Integer getLogId() {
        return logId;
    }

    /** 设置 订单日志ID 属性 */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /** 获取 管理员ID 属性 */
    public Integer getAdminUserid() {
        return adminUserid;
    }

    /** 设置 管理员ID 属性 */
    public void setAdminUserid(Integer adminUserid) {
        this.adminUserid = adminUserid;
    }

    /** 获取 操作分类 1日志 2跟踪记录 3售后消息 属性 */
    public Byte getType() {
        return type;
    }

    /** 设置 操作分类 1日志 2跟踪记录 3售后消息 属性 */
    public void setType(Byte type) {
        this.type = type;
    }

    /** 获取 逻辑外键 订单ID 属性 */
    public Integer getOrderId() {
        return orderId;
    }

    /** 设置 逻辑外键 订单ID 属性 */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取 订单号 属性 */
    public String getOrderNumber() {
        return orderNumber;
    }

    /** 设置 订单号 属性 */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /** 获取 操作内容 属性 */
    public String getContent() {
        return content;
    }

    /** 设置 操作内容 属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 描述 属性 */
    public String getRemark() {
        return remark;
    }

    /** 设置 描述 属性 */
    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsOrderLog");
        sb.append("{logId=").append(logId);
        sb.append(", adminUserid=").append(adminUserid);
        sb.append(", type=").append(type);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", content=").append(content);
        sb.append(", remark=").append(remark);
        sb.append(", ip=").append(ip);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsOrderLog) {
            BtsOrderLog btsOrderLog = (BtsOrderLog) obj;
            if (this.getLogId().equals(btsOrderLog.getLogId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getLogId();
        return pkStr.hashCode();
    }

}