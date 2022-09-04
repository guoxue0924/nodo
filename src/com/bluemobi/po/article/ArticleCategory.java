package com.bluemobi.po.article;

import java.util.Date;

import com.appcore.model.AbstractObject;


/**
 * 文章分类
 * ClassName: ArticleCategory
 * Date: 2016年2月19日下午3:13:57

 * @author kevin
 * @version 
 * @since JDK 7
 */
public class ArticleCategory extends AbstractObject {

	private static final long serialVersionUID = -6706755911248447471L;
	//
	private Integer categoryId;
	// 父级分类 ID
	private Integer parentId;
    // 分类名称
    private String title;
    // 描述
    private String description;
    // 排序
    private Integer sortOrder = 0;
    // 创建时间
    private Date ctime;
    // 修改时间 
    private Date mtime;
    
    /** 获取 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 标签名称 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 标签名称 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 上级分类 ID 属性 */
    public Integer getParentId() {
        return parentId;
    }

    /** 设置 上级分类 ID 属性 */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 获取 排序 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序 属性 */
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GrouponCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", parentId=").append(parentId);
        sb.append(", description=").append(description);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArticleCategory) {
            ArticleCategory articleCategory = (ArticleCategory) obj;
            if (this.getCategoryId().equals(articleCategory.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCategoryId();
        return pkStr.hashCode();
    }

}