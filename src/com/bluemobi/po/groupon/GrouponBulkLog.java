package com.bluemobi.po.groupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【团购日志表】持久化对象 数据库表：groupon_bulk_log
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
public class GrouponBulkLog extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 团购商品 ID
    private Integer logId;
    // 团购 ID
    private Integer bulkId;
    // 团购商品ID
    private String sku;
    // 团购商品名称
    private String goodsName;
    // 团购用户 ID
    private Integer userid;
    // 团购个数
    private Integer number;
    // 团购价格
    private Float price;
    // 团购使用积分
    private Integer integral;
    // 创建时间
    private Date ctime;

    /** 获取 团购商品 ID 属性 */
    public Integer getLogId() {
        return logId;
    }

    /** 设置 团购商品 ID 属性 */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /** 获取 团购 ID 属性 */
    public Integer getBulkId() {
        return bulkId;
    }

    /** 设置 团购 ID 属性 */
    public void setBulkId(Integer bulkId) {
        this.bulkId = bulkId;
    }

    /** 获取 团购商品ID 属性 */
    public String getSku() {
        return sku;
    }

    /** 设置 团购商品ID 属性 */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /** 获取 团购商品名称 属性 */
    public String getGoodsName() {
        return goodsName;
    }

    /** 设置 团购商品名称 属性 */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /** 获取 团购用户 ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 团购用户 ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 团购个数 属性 */
    public Integer getNumber() {
        return number;
    }

    /** 设置 团购个数 属性 */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /** 获取 团购价格 属性 */
    public Float getPrice() {
        return price;
    }

    /** 设置 团购价格 属性 */
    public void setPrice(Float price) {
        this.price = price;
    }

    /** 获取 团购使用积分 属性 */
    public Integer getIntegral() {
        return integral;
    }

    /** 设置 团购使用积分 属性 */
    public void setIntegral(Integer integral) {
        this.integral = integral;
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
        sb.append("GrouponBulkLog");
        sb.append("{logId=").append(logId);
        sb.append(", bulkId=").append(bulkId);
        sb.append(", sku=").append(sku);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", userid=").append(userid);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", integral=").append(integral);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GrouponBulkLog) {
            GrouponBulkLog grouponBulkLog = (GrouponBulkLog) obj;
            if (this.getLogId().equals(grouponBulkLog.getLogId())) {
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