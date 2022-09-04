package com.bluemobi.po.feedback;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【留言反馈表】持久化对象 数据库表：feedback_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 13:35:15
 * 
 */
public class FeedbackContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer contentId;
    // 留言内容
    private String body;
    //
    private Date ctime;
    //
    private String ip;
    // 回复内容
    private String reply;
    // 回复时间
    private Date replyTime;
    // 是否已处理。1：是；0：否；
    private Byte status;
    //
    private String title;
    // 若发布者为登录会员，则记录会员 ID
    private Integer userid;

    private String realname;

    /** 获取 属性 */
    public Integer getContentId() {
        return contentId;
    }

    /** 设置 属性 */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /** 获取 留言内容 属性 */
    public String getBody() {
        return body;
    }

    /** 设置 留言内容 属性 */
    public void setBody(String body) {
        this.body = body;
    }

    /** 获取 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 属性 */
    public String getIp() {
        return ip;
    }

    /** 设置 属性 */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /** 获取 回复内容 属性 */
    public String getReply() {
        return reply;
    }

    /** 设置 回复内容 属性 */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /** 获取 回复时间 属性 */
    public Date getReplyTime() {
        return replyTime;
    }

    /** 设置 回复时间 属性 */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /** 获取 是否已处理。1：是；0：否； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 是否已处理。1：是；0：否； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 若发布者为登录会员，则记录会员 ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 若发布者为登录会员，则记录会员 ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FeedbackContent");
        sb.append("{contentId=").append(contentId);
        sb.append(", body=").append(body);
        sb.append(", ctime=").append(ctime);
        sb.append(", ip=").append(ip);
        sb.append(", reply=").append(reply);
        sb.append(", replyTime=").append(replyTime);
        sb.append(", status=").append(status);
        sb.append(", title=").append(title);
        sb.append(", userid=").append(userid);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FeedbackContent) {
            FeedbackContent feedbackContent = (FeedbackContent) obj;
            if (this.getContentId().equals(feedbackContent.getContentId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getContentId();
        return pkStr.hashCode();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

}