package com.bluemobi.po.im;

import java.util.Date;

import com.appcore.model.AbstractObject;
import com.bluemobi.constant.BaseConstant;

/**
 * 【ＩＭ消息内容表】持久化对象 数据库表：im_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 11:01:20
 * 
 */
public class ImContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Long id;
    // 消息类型，对应关系表：im_category
    private Integer categoryId;
    // 消息内容
    private String content;
    // 发送用户类型（admin,cas）
    private String sendUserType;
    // 发送用户id
    private Long sendUserid;
    // 接收用户类型（admin,cas）
    private String toUserType;
    // 接收用户id
    private Long toUserid;
    // 是否锁定
    private Byte isLock = BaseConstant.STATUS_DISABELD;
    // 是否已读
    private Byte isRead = BaseConstant.STATUS_DISABELD;
    // 是否删除
    private Byte isDel = BaseConstant.STATUS_DISABELD;
    // 发送时间
    private Date ctime;
    // 最后更新时间
    private Date mtime;

    /** 获取 主键id 属性 */
    public Long getId() {
        return id;
    }

    /** 设置 主键id 属性 */
    public void setId(Long id) {
        this.id = id;
    }

    /** 获取 消息类型，对应关系表：im_category 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 消息类型，对应关系表：im_category 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 消息内容 属性 */
    public String getContent() {
        return content;
    }

    /** 设置 消息内容 属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 发送用户类型（admin,cas） 属性 */
    public String getSendUserType() {
        return sendUserType;
    }

    /** 设置 发送用户类型（admin,cas） 属性 */
    public void setSendUserType(String sendUserType) {
        this.sendUserType = sendUserType;
    }

    /** 获取 发送用户id 属性 */
    public Long getSendUserid() {
        return sendUserid;
    }

    /** 设置 发送用户id 属性 */
    public void setSendUserid(Long sendUserid) {
        this.sendUserid = sendUserid;
    }

    /** 获取 接收用户类型（admin,cas） 属性 */
    public String getToUserType() {
        return toUserType;
    }

    /** 设置 接收用户类型（admin,cas） 属性 */
    public void setToUserType(String toUserType) {
        this.toUserType = toUserType;
    }

    /** 获取 接收用户id 属性 */
    public Long getToUserid() {
        return toUserid;
    }

    /** 设置 接收用户id 属性 */
    public void setToUserid(Long toUserid) {
        this.toUserid = toUserid;
    }

    /** 获取 是否锁定 属性 */
    public Byte getIsLock() {
        return isLock;
    }

    /** 设置 是否锁定 属性 */
    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
    }

    /** 获取 是否已读 属性 */
    public Byte getIsRead() {
        return isRead;
    }

    /** 设置 是否已读 属性 */
    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    /** 获取 是否删除 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否删除 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 发送时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 发送时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 最后更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ImContent");
        sb.append("{id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", content=").append(content);
        sb.append(", sendUserType=").append(sendUserType);
        sb.append(", sendUserid=").append(sendUserid);
        sb.append(", toUserType=").append(toUserType);
        sb.append(", toUserid=").append(toUserid);
        sb.append(", isLock=").append(isLock);
        sb.append(", isRead=").append(isRead);
        sb.append(", isDel=").append(isDel);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImContent) {
            ImContent imContent = (ImContent) obj;
            if (this.getId().equals(imContent.getId())) {
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