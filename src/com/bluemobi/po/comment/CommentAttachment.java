package com.bluemobi.po.comment;


import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：comment_attachment
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:27
 * 
 */
public class CommentAttachment extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer commentId;
    // 
    private Integer attachmentid;
    // 
    private String type;
    // 上传的用户 ID
    private Integer userid;

    /** 获取  属性 */
    public Integer getCommentId() {
        return commentId;
    }

    /** 设置  属性 */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /** 获取  属性 */
    public Integer getAttachmentid() {
        return attachmentid;
    }

    /** 设置  属性 */
    public void setAttachmentid(Integer attachmentid) {
        this.attachmentid = attachmentid;
    }

    /** 获取  属性 */
    public String getType() {
        return type;
    }

    /** 设置  属性 */
    public void setType(String type) {
        this.type = type;
    }

    /** 获取 上传的用户 ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 上传的用户 ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CommentAttachment");
        sb.append("{commentId=").append(commentId);
        sb.append(", attachmentid=").append(attachmentid);
        sb.append(", type=").append(type);
        sb.append(", userid=").append(userid);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CommentAttachment) {
            CommentAttachment commentAttachment = (CommentAttachment) obj;
            if (this.getCommentId().equals(commentAttachment.getCommentId()) && this.getAttachmentid().equals(commentAttachment.getAttachmentid())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCommentId() + this.getAttachmentid();
        return pkStr.hashCode();
    }

}