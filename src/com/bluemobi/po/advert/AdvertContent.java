package com.bluemobi.po.advert;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【广告表】持久化对象 数据库表：advert_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 11:11:01
 * 
 */
public class AdvertContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
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
    private Date startTime;
    // 结束时间
    private Date endTime;
    // 创建时间
    private Date ctime;
    // 更新时间
    private Date mtime;

    /** 获取 属性 */
    public Integer getContentId() {
        return contentId;
    }

    /** 设置 属性 */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /** 获取 广告位 ID 属性 */
    public Integer getBoardId() {
        return boardId;
    }

    /** 设置 广告位 ID 属性 */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /** 获取 广告类型。1：图片；2：文字；3：flash；4：视频；5：轮播； 属性 */
    public Byte getType() {
        return type;
    }

    /** 设置 广告类型。1：图片；2：文字；3：flash；4：视频；5：轮播； 属性 */
    public void setType(Byte type) {
        this.type = type;
    }

    /** 获取 所属页面 属性 */
    public Integer getPageId() {
        return pageId;
    }

    /** 设置 所属页面 属性 */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /** 获取 广告标题 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 广告标题 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 广告详情 属性 */
    public String getContent() {
        return content;
    }

    /** 设置 广告详情 属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 附件 ID。关联表：trend_attachment 属性 */
    public Long getAttachmentid() {
        return attachmentid;
    }

    /** 设置 附件 ID。关联表：trend_attachment 属性 */
    public void setAttachmentid(Long attachmentid) {
        this.attachmentid = attachmentid;
    }

    /** 获取 绑定资源类型。1：链接地址；2：商品；3：文章；4：自定义； 属性 */
    public Integer getBindType() {
        return bindType;
    }

    /** 设置 绑定资源类型。1：链接地址；2：商品；3：文章；4：自定义； 属性 */
    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    /** 获取 绑定资源内容 属性 */
    public String getBindSource() {
        return bindSource;
    }

    /** 设置 绑定资源内容 属性 */
    public void setBindSource(String bindSource) {
        this.bindSource = bindSource;
    }

    /** 获取 点击次数 属性 */
    public Integer getCount() {
        return count;
    }

    /** 设置 点击次数 属性 */
    public void setCount(Integer count) {
        this.count = count;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 显示状态。0：不显示；1：显示；2：定时显示； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 显示状态。0：不显示；1：显示；2：定时显示； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 开始生效时间 属性 */
    public Date getStartTime() {
        return startTime;
    }

    /** 设置 开始生效时间 属性 */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** 获取 结束时间 属性 */
    public Date getEndTime() {
        return endTime;
    }

    /** 设置 结束时间 属性 */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdvertContent");
        sb.append("{contentId=").append(contentId);
        sb.append(", boardId=").append(boardId);
        sb.append(", type=").append(type);
        sb.append(", pageId=").append(pageId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", attachmentid=").append(attachmentid);
        sb.append(", bindType=").append(bindType);
        sb.append(", bindSource=").append(bindSource);
        sb.append(", count=").append(count);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
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
        if (obj instanceof AdvertContent) {
            AdvertContent advertContent = (AdvertContent) obj;
            if (this.getContentId().equals(advertContent.getContentId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getContentId();
        return pkStr.hashCode();
    }

}