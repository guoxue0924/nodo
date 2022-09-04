package com.bluemobi.po.article;

import java.util.Date;

import com.appcore.model.AbstractObject;
import com.bluemobi.constant.ArticleConstant;
import com.bluemobi.constant.BaseConstant;

/**
 * 【】持久化对象 数据库表：article_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-19 15:53:38
 * 
 */
public class ArticleContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer contentId;
    // 分类id
    private Integer categoryId;
    // 标题
    private String title;
    // 副标题
    private String subtitle;
    // 短标题
    private String slug;
    // 图片
    private String image;
    // ip地址
    private String ip;
    // 文章标签
    private String label;
    // (0:未发布;1:发布;-1:删除;)0: unavailable; 1: published; -1: deleted;
    private Byte status = ArticleConstant.ARTICLE_STATUS.WAIT_PULISH.getCode().byteValue();
    // 用户类型
    private String userType;
    // 发布者 ID。对应于 admin_user 或 cas_user 表中的 userid
    private Integer userid;
    // 文章主体
    private String body;
    // 文章推荐 （ 1-推荐, 0-不推荐）
    private Byte isRecommend = BaseConstant.STATUS_DISABELD;
    // 描述标签
    private String metaDescription;
    // 后缀关键字
    private String metaKeywords;
    // 后缀标题
    private String metaTitle;
    // 序号
    private Integer sortOrder;
    // 创建时间
    private Date ctime;
    // 修改时间
    private Date mtime;
    // 发布时间
    private Date ptime;

    /** 获取 主键id 属性 */
    public Integer getContentId() {
        return contentId;
    }

    /** 设置 主键id 属性 */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /** 获取 分类id 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 分类id 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 标题 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 标题 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 副标题 属性 */
    public String getSubtitle() {
        return subtitle;
    }

    /** 设置 副标题 属性 */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /** 获取 短标题 属性 */
    public String getSlug() {
        return slug;
    }

    /** 设置 短标题 属性 */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /** 获取 图片 属性 */
    public String getImage() {
        return image;
    }

    /** 设置 图片 属性 */
    public void setImage(String image) {
        this.image = image;
    }

    /** 获取 ip地址 属性 */
    public String getIp() {
        return ip;
    }

    /** 设置 ip地址 属性 */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /** 获取 文章标签 属性 */
    public String getLabel() {
        return label;
    }

    /** 设置 文章标签 属性 */
    public void setLabel(String label) {
        this.label = label;
    }

    /** 获取 (0:未发布;1:发布;-1:删除;)0: unavailable; 1: published; -1: deleted; 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 (0:未发布;1:发布;-1:删除;)0: unavailable; 1: published; -1: deleted; 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 用户类型 属性 */
    public String getUserType() {
        return userType;
    }

    /** 设置 用户类型 属性 */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /** 获取 发布者 ID。对应于 admin_user 或 cas_user 表中的 userid 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 发布者 ID。对应于 admin_user 或 cas_user 表中的 userid 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 文章主体 属性 */
    public String getBody() {
        return body;
    }

    /** 设置 文章主体 属性 */
    public void setBody(String body) {
        this.body = body;
    }

    /** 获取 文章推荐 （ 1-推荐, 0-不推荐） 属性 */
    public Byte getIsRecommend() {
        return isRecommend;
    }

    /** 设置 文章推荐 （ 1-推荐, 0-不推荐） 属性 */
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    /** 获取 描述标签 属性 */
    public String getMetaDescription() {
        return metaDescription;
    }

    /** 设置 描述标签 属性 */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    /** 获取 后缀关键字 属性 */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /** 设置 后缀关键字 属性 */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    /** 获取 后缀标题 属性 */
    public String getMetaTitle() {
        return metaTitle;
    }

    /** 设置 后缀标题 属性 */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 修改时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 修改时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 发布时间 属性 */
    public Date getPtime() {
        return ptime;
    }

    /** 设置 发布时间 属性 */
    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ArticleContent");
        sb.append("{contentId=").append(contentId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", subtitle=").append(subtitle);
        sb.append(", slug=").append(slug);
        sb.append(", image=").append(image);
        sb.append(", ip=").append(ip);
        sb.append(", label=").append(label);
        sb.append(", status=").append(status);
        sb.append(", userType=").append(userType);
        sb.append(", userid=").append(userid);
        sb.append(", body=").append(body);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", metaDescription=").append(metaDescription);
        sb.append(", metaKeywords=").append(metaKeywords);
        sb.append(", metaTitle=").append(metaTitle);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", ptime=").append(ptime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArticleContent) {
            ArticleContent articleContent = (ArticleContent) obj;
            if (this.getContentId().equals(articleContent.getContentId())) {
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