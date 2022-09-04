package com.bluemobi.to.advert;

import com.appcore.model.AbstractObject;

public class AdvertContentTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Integer contentId;
    // 广告位 ID
    private Integer boardId;
    // 广告类型。1：图片；2：文字；3：flash；4：视频；5：轮播；
    private Byte type;
    // 所属页面
    private Integer pageId;
    // 广告标题
    private String title;
    // 广告详情
    private String content;
    // 附件 ID。关联表：trend_attachment
    private Long attachmentid;
    // 绑定资源类型。1：链接地址；2：商品；3：文章；4：自定义；
    private Integer bindType;
    // 绑定资源内容
    private String bindSource;
    // 点击次数
    private Integer count;
    // 序号
    private Integer sortOrder;
    // 显示状态。0：不显示；1：显示；2：定时显示；
    private Byte status;
    // 开始生效时间
    private String startTimeStr;
    // 结束时间
    private String endTimeStr;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAttachmentid() {
        return attachmentid;
    }

    public void setAttachmentid(Long attachmentid) {
        this.attachmentid = attachmentid;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getBindSource() {
        return bindSource;
    }

    public void setBindSource(String bindSource) {
        this.bindSource = bindSource;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

}
