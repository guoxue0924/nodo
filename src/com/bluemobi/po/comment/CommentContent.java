package com.bluemobi.po.comment;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：comment_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:28
 * 
 */
public class CommentContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 评论分类 ID
    private Integer categoryId;
    // 
    private String content;
    // 
    private Date ctime;
    // 
    private String ip;
    // 是否是追加评论。1：是；0：否；
    private Byte isAgen;
    // 是否标记为删除。1：是；0：否；
    private Byte isDel;
    // 最后一次回复的用户 ID
    private Long lastreplyUserid;
    // 
    private Date mtime;
    // 支持数量。也叫赞、顶等
    private Integer numsAgree;
    // 回复数量
    private Integer numsReply;
    // 父级评论 ID
    private Integer pid;
    // 商品满意度评分。分值：0 - 100
    private Byte rankBase;
    // 物流满意度评分。分值：0 - 100
    private Byte rankLogistics;
    // 发货速度满意度评分。分值：0 - 100
    private Byte rankSpeed;
    // 是否在前台显示。1：是；0：否；
    private Byte status;
    // 
    private String title;
    // 评论所针对的 ID。比如商品 ID 或者优惠券 ID 等
    private Long toId;
    // 评论针对的订单商品编号
    private Long toOrderItemId;
    // 评论针对的店铺 ID
    private Integer toStoreId;
    // 
    private String userType;
    //  若发布者为登录会员，则记录会员 ID
    private Long userid;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 评论分类 ID 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 评论分类 ID 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取  属性 */
    public String getContent() {
        return content;
    }

    /** 设置  属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取  属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置  属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取  属性 */
    public String getIp() {
        return ip;
    }

    /** 设置  属性 */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /** 获取 是否是追加评论。1：是；0：否； 属性 */
    public Byte getIsAgen() {
        return isAgen;
    }

    /** 设置 是否是追加评论。1：是；0：否； 属性 */
    public void setIsAgen(Byte isAgen) {
        this.isAgen = isAgen;
    }

    /** 获取 是否标记为删除。1：是；0：否； 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记为删除。1：是；0：否； 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 最后一次回复的用户 ID 属性 */
    public Long getLastreplyUserid() {
        return lastreplyUserid;
    }

    /** 设置 最后一次回复的用户 ID 属性 */
    public void setLastreplyUserid(Long lastreplyUserid) {
        this.lastreplyUserid = lastreplyUserid;
    }

    /** 获取  属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置  属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 支持数量。也叫赞、顶等 属性 */
    public Integer getNumsAgree() {
        return numsAgree;
    }

    /** 设置 支持数量。也叫赞、顶等 属性 */
    public void setNumsAgree(Integer numsAgree) {
        this.numsAgree = numsAgree;
    }

    /** 获取 回复数量 属性 */
    public Integer getNumsReply() {
        return numsReply;
    }

    /** 设置 回复数量 属性 */
    public void setNumsReply(Integer numsReply) {
        this.numsReply = numsReply;
    }

    /** 获取 父级评论 ID 属性 */
    public Integer getPid() {
        return pid;
    }

    /** 设置 父级评论 ID 属性 */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /** 获取 商品满意度评分。分值：0 - 100 属性 */
    public Byte getRankBase() {
        return rankBase;
    }

    /** 设置 商品满意度评分。分值：0 - 100 属性 */
    public void setRankBase(Byte rankBase) {
        this.rankBase = rankBase;
    }

    /** 获取 物流满意度评分。分值：0 - 100 属性 */
    public Byte getRankLogistics() {
        return rankLogistics;
    }

    /** 设置 物流满意度评分。分值：0 - 100 属性 */
    public void setRankLogistics(Byte rankLogistics) {
        this.rankLogistics = rankLogistics;
    }

    /** 获取 发货速度满意度评分。分值：0 - 100 属性 */
    public Byte getRankSpeed() {
        return rankSpeed;
    }

    /** 设置 发货速度满意度评分。分值：0 - 100 属性 */
    public void setRankSpeed(Byte rankSpeed) {
        this.rankSpeed = rankSpeed;
    }

    /** 获取 是否在前台显示。1：是；0：否； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 是否在前台显示。1：是；0：否； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取  属性 */
    public String getTitle() {
        return title;
    }

    /** 设置  属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 评论所针对的 ID。比如商品 ID 或者优惠券 ID 等 属性 */
    public Long getToId() {
        return toId;
    }

    /** 设置 评论所针对的 ID。比如商品 ID 或者优惠券 ID 等 属性 */
    public void setToId(Long toId) {
        this.toId = toId;
    }

    /** 获取 评论针对的订单商品编号 属性 */
    public Long getToOrderItemId() {
        return toOrderItemId;
    }

    /** 设置 评论针对的订单商品编号 属性 */
    public void setToOrderItemId(Long toOrderItemId) {
        this.toOrderItemId = toOrderItemId;
    }

    /** 获取 评论针对的店铺 ID 属性 */
    public Integer getToStoreId() {
        return toStoreId;
    }

    /** 设置 评论针对的店铺 ID 属性 */
    public void setToStoreId(Integer toStoreId) {
        this.toStoreId = toStoreId;
    }

    /** 获取  属性 */
    public String getUserType() {
        return userType;
    }

    /** 设置  属性 */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /** 获取  若发布者为登录会员，则记录会员 ID 属性 */
    public Long getUserid() {
        return userid;
    }

    /** 设置  若发布者为登录会员，则记录会员 ID 属性 */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CommentContent");
        sb.append("{id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", content=").append(content);
        sb.append(", ctime=").append(ctime);
        sb.append(", ip=").append(ip);
        sb.append(", isAgen=").append(isAgen);
        sb.append(", isDel=").append(isDel);
        sb.append(", lastreplyUserid=").append(lastreplyUserid);
        sb.append(", mtime=").append(mtime);
        sb.append(", numsAgree=").append(numsAgree);
        sb.append(", numsReply=").append(numsReply);
        sb.append(", pid=").append(pid);
        sb.append(", rankBase=").append(rankBase);
        sb.append(", rankLogistics=").append(rankLogistics);
        sb.append(", rankSpeed=").append(rankSpeed);
        sb.append(", status=").append(status);
        sb.append(", title=").append(title);
        sb.append(", toId=").append(toId);
        sb.append(", toOrderItemId=").append(toOrderItemId);
        sb.append(", toStoreId=").append(toStoreId);
        sb.append(", userType=").append(userType);
        sb.append(", userid=").append(userid);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CommentContent) {
            CommentContent commentContent = (CommentContent) obj;
            if (this.getId().equals(commentContent.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}