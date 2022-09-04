package com.bluemobi.po.trend;


import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：trend_attachment_group
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-02 14:04:38
 * 
 */
public class TrendAttachmentGroup extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Long attachmentGroupId;
    // 附件ID，对应trend_attachment表的ID
    private Long attachmentId;
    // 主ID，对应所关联附件的表的ID
    private Long mainId;
    // 类型，对应所关联附件表的类型
    private String type;

    /** 获取  属性 */
    public Long getAttachmentGroupId() {
        return attachmentGroupId;
    }

    /** 设置  属性 */
    public void setAttachmentGroupId(Long attachmentGroupId) {
        this.attachmentGroupId = attachmentGroupId;
    }

    /** 获取 附件ID，对应trend_attachment表的ID 属性 */
    public Long getAttachmentId() {
        return attachmentId;
    }

    /** 设置 附件ID，对应trend_attachment表的ID 属性 */
    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    /** 获取 主ID，对应所关联附件的表的ID 属性 */
    public Long getMainId() {
        return mainId;
    }

    /** 设置 主ID，对应所关联附件的表的ID 属性 */
    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    /** 获取 类型，对应所关联附件表的类型 属性 */
    public String getType() {
        return type;
    }

    /** 设置 类型，对应所关联附件表的类型 属性 */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendAttachmentGroup");
        sb.append("{attachmentGroupId=").append(attachmentGroupId);
        sb.append(", attachmentId=").append(attachmentId);
        sb.append(", mainId=").append(mainId);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendAttachmentGroup) {
            TrendAttachmentGroup trendAttachmentGroup = (TrendAttachmentGroup) obj;
            if (this.getAttachmentGroupId().equals(trendAttachmentGroup.getAttachmentGroupId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAttachmentGroupId();
        return pkStr.hashCode();
    }

}