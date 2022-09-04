package com.bluemobi.to.coupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 优惠券列表查询对象
 * @ClassName CouponListQueryTO
 * @author liuyt
 * @date 2015-11-2 下午3:35:59
 * @version
 */
public class CouponListQueryTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 优惠券状态
    private String status;

    // 优惠券类型
    private String type;

    // 搜索关键字(优惠券名称,用户名称模糊查询)
    private String key;

    // 创建开始时间
    private Date createStartDate;

    // 创建结束时间
    private Date createEndDate;

    // 修改开始时间
    private Date modifyStartDate;

    // 修改结束时间
    private Date modifyEndDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreateStartDate() {
        return createStartDate;
    }

    public void setCreateStartDate(Date createStartDate) {
        this.createStartDate = createStartDate;
    }

    public Date getCreateEndDate() {
        return createEndDate;
    }

    public void setCreateEndDate(Date createEndDate) {
        this.createEndDate = createEndDate;
    }

    public Date getModifyStartDate() {
        return modifyStartDate;
    }

    public void setModifyStartDate(Date modifyStartDate) {
        this.modifyStartDate = modifyStartDate;
    }

    public Date getModifyEndDate() {
        return modifyEndDate;
    }

    public void setModifyEndDate(Date modifyEndDate) {
        this.modifyEndDate = modifyEndDate;
    }

}
