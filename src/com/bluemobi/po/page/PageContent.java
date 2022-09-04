package com.bluemobi.po.page;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：page_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 10:07:23
 * 
 */
public class PageContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 所属分组 ID。关联表：page_group
    private Integer groupId;
    // 标题
    private String title;
    // 详情
    private String body;
    // 简介
    private String memo;
    // 单页主题图片
    private String filePath;
    // 链接转发地址
    private String forwardUrl;
    // 单页路径
    private String pageUrl;
    // 是否共享页面头尾文件。1：是；0：否；
    private Byte isShareCommon;
    // 序号
    private Integer sortOrder;
    // 
    private Date ctime;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 所属分组 ID。关联表：page_group 属性 */
    public Integer getGroupId() {
        return groupId;
    }

    /** 设置 所属分组 ID。关联表：page_group 属性 */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /** 获取 标题 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 标题 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 详情 属性 */
    public String getBody() {
        return body;
    }

    /** 设置 详情 属性 */
    public void setBody(String body) {
        this.body = body;
    }

    /** 获取 简介 属性 */
    public String getMemo() {
        return memo;
    }

    /** 设置 简介 属性 */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /** 获取 单页主题图片 属性 */
    public String getFilePath() {
        return filePath;
    }

    /** 设置 单页主题图片 属性 */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /** 获取 链接转发地址 属性 */
    public String getForwardUrl() {
        return forwardUrl;
    }

    /** 设置 链接转发地址 属性 */
    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    /** 获取 单页路径 属性 */
    public String getPageUrl() {
        return pageUrl;
    }

    /** 设置 单页路径 属性 */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /** 获取 是否共享页面头尾文件。1：是；0：否； 属性 */
    public Byte getIsShareCommon() {
        return isShareCommon;
    }

    /** 设置 是否共享页面头尾文件。1：是；0：否； 属性 */
    public void setIsShareCommon(Byte isShareCommon) {
        this.isShareCommon = isShareCommon;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取  属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置  属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PageContent");
        sb.append("{id=").append(id);
        sb.append(", groupId=").append(groupId);
        sb.append(", title=").append(title);
        sb.append(", body=").append(body);
        sb.append(", memo=").append(memo);
        sb.append(", filePath=").append(filePath);
        sb.append(", forwardUrl=").append(forwardUrl);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", isShareCommon=").append(isShareCommon);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PageContent) {
            PageContent pageContent = (PageContent) obj;
            if (this.getId().equals(pageContent.getId())) {
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