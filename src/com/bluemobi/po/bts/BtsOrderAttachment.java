package com.bluemobi.po.bts;


import com.appcore.model.AbstractObject;

/**
 * 【订单附件关系表】持久化对象 数据库表：bts_order_attachment
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public class BtsOrderAttachment extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 所针对表的主键 ID，或唯一标识字段，比如退货单号
    private String toId;
    // 附件ID
    private Integer attachmentid;
    // 上传的用户 ID
    private Integer userid;
    // 附件类型。order：订单；order_return：退货单；
    private String type;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 所针对表的主键 ID，或唯一标识字段，比如退货单号 属性 */
    public String getToId() {
        return toId;
    }

    /** 设置 所针对表的主键 ID，或唯一标识字段，比如退货单号 属性 */
    public void setToId(String toId) {
        this.toId = toId;
    }

    /** 获取 附件ID 属性 */
    public Integer getAttachmentid() {
        return attachmentid;
    }

    /** 设置 附件ID 属性 */
    public void setAttachmentid(Integer attachmentid) {
        this.attachmentid = attachmentid;
    }

    /** 获取 上传的用户 ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 上传的用户 ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 附件类型。order：订单；order_return：退货单； 属性 */
    public String getType() {
        return type;
    }

    /** 设置 附件类型。order：订单；order_return：退货单； 属性 */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsOrderAttachment");
        sb.append("{id=").append(id);
        sb.append(", toId=").append(toId);
        sb.append(", attachmentid=").append(attachmentid);
        sb.append(", userid=").append(userid);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsOrderAttachment) {
            BtsOrderAttachment btsOrderAttachment = (BtsOrderAttachment) obj;
            if (this.getId().equals(btsOrderAttachment.getId()) && this.getAttachmentid().equals(btsOrderAttachment.getAttachmentid()) && this.getUserid().equals(btsOrderAttachment.getUserid()) && this.getType().equals(btsOrderAttachment.getType())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId() + this.getAttachmentid() + this.getUserid() + this.getType();
        return pkStr.hashCode();
    }

}