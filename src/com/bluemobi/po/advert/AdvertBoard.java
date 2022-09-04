package com.bluemobi.po.advert;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：advert_board
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 13:59:03
 * 
 */
public class AdvertBoard extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer boardId;
    //
    private Date ctime;
    // 广告位高度。单位：像素
    private Short height;
    //
    private String memo;
    //
    private Date mtime;
    //
    private String name;
    // 所属页面 ID
    private Integer pageId;
    // 序号
    private Short sortOrder;
    // 状态。1：启用；0：不启用；
    private Byte status;
    // 广告类型。1：图片；2：文字；3：flash；4：视频；5：轮播；6.背景
    private Byte type;
    // 广告位宽度。单位：像素
    private Short width;

    /** 获取 属性 */
    public Integer getBoardId() {
        return boardId;
    }

    /** 设置 属性 */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /** 获取 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 广告位高度。单位：像素 属性 */
    public Short getHeight() {
        return height;
    }

    /** 设置 广告位高度。单位：像素 属性 */
    public void setHeight(Short height) {
        this.height = height;
    }

    /** 获取 属性 */
    public String getMemo() {
        return memo;
    }

    /** 设置 属性 */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /** 获取 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 属性 */
    public String getName() {
        return name;
    }

    /** 设置 属性 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 所属页面 ID 属性 */
    public Integer getPageId() {
        return pageId;
    }

    /** 设置 所属页面 ID 属性 */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /** 获取 序号 属性 */
    public Short getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态。1：启用；0：不启用； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 状态。1：启用；0：不启用； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 广告类型。1：图片；2：文字；3：flash；4：视频；5：轮播；6.背景 属性 */
    public Byte getType() {
        return type;
    }

    /** 设置 广告类型。1：图片；2：文字；3：flash；4：视频；5：轮播；6.背景 属性 */
    public void setType(Byte type) {
        this.type = type;
    }

    /** 获取 广告位宽度。单位：像素 属性 */
    public Short getWidth() {
        return width;
    }

    /** 设置 广告位宽度。单位：像素 属性 */
    public void setWidth(Short width) {
        this.width = width;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdvertBoard");
        sb.append("{boardId=").append(boardId);
        sb.append(", ctime=").append(ctime);
        sb.append(", height=").append(height);
        sb.append(", memo=").append(memo);
        sb.append(", mtime=").append(mtime);
        sb.append(", name=").append(name);
        sb.append(", pageId=").append(pageId);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", width=").append(width);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdvertBoard) {
            AdvertBoard advertBoard = (AdvertBoard) obj;
            if (this.getBoardId().equals(advertBoard.getBoardId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getBoardId();
        return pkStr.hashCode();
    }

}