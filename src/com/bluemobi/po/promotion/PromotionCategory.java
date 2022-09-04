package com.bluemobi.po.promotion;


import com.appcore.model.AbstractObject;

/**
 * 【优惠促销活动分类】持久化对象 数据库表：promotion_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:21
 * 
 */
public class PromotionCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer categoryId;
    // 分类标题
    private String title;
    // 模板文件名称
    private String templateName;

    /** 获取  属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置  属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 分类标题 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 分类标题 属性 */
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PromotionCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", templateName=").append(templateName);
        sb.append(", title=").append(title);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PromotionCategory) {
            PromotionCategory promotionCategory = (PromotionCategory) obj;
            if (this.getCategoryId().equals(promotionCategory.getCategoryId())) {
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