package com.bluemobi.po.promotion;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【优惠促销活动主体表】持久化对象 数据库表：promotion_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:22
 * 
 */
public class PromotionContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer promotionId;
    // 分类 ID
    private Integer categoryId;
    // 活动标题
    private String title;
    // 活动内容描述
    private String content;
    // 序列化存储的活动规则具体数据
    private String rules;
    // 是否是全场活动。1：是；0：否；
    private Byte isOverall;
    // 是否启用。1：是；0：否；
    private Byte status;
    // 是否标记为删除。1：是；0：否；
    private Byte isDel;
    // 活动开始时间
    private Date startTime;
    // 活动结束时间
    private Date endTime;
    // 记录创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;
    
    /** 获取  属性 */
    public Integer getPromotionId() {
        return promotionId;
    }

    /** 设置  属性 */
    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    /** 获取 分类 ID 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 分类 ID 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 活动标题 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 活动标题 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 活动内容描述 属性 */
    public String getContent() {
        return content;
    }

    /** 设置 活动内容描述 属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 序列化存储的活动规则具体数据 属性 */
    public String getRules() {
        return rules;
    }

    /** 设置 序列化存储的活动规则具体数据 属性 */
    public void setRules(String rules) {
        this.rules = rules;
    }

    /** 获取 是否是全场活动。1：是；0：否； 属性 */
    public Byte getIsOverall() {
        return isOverall;
    }

    /** 设置 是否是全场活动。1：是；0：否； 属性 */
    public void setIsOverall(Byte isOverall) {
        this.isOverall = isOverall;
    }

    /** 获取 是否启用。1：是；0：否； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 是否启用。1：是；0：否； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 是否标记为删除。1：是；0：否； 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记为删除。1：是；0：否； 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 活动开始时间 属性 */
    public Date getStartTime() {
        return startTime;
    }

    /** 设置 活动开始时间 属性 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** 获取 活动结束时间 属性 */
    public Date getEndTime() {
        return endTime;
    }

    /** 设置 活动结束时间 属性 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /** 获取 记录创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 记录创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 最后一次更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PromotionContent");
        sb.append("{promotionId=").append(promotionId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", rules=").append(rules);
        sb.append(", isOverall=").append(isOverall);
        sb.append(", status=").append(status);
        sb.append(", isDel=").append(isDel);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PromotionContent) {
            PromotionContent promotionContent = (PromotionContent) obj;
            if (this.getPromotionId().equals(promotionContent.getPromotionId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPromotionId();
        return pkStr.hashCode();
    }

}