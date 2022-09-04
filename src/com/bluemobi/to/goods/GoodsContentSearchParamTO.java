package com.bluemobi.to.goods;

import com.appcore.model.AbstractObject;

/**
 * 前台搜索条件TO
 * 
 * @author zhangzheng
 * @date 2016-2-22
 * 
 */
public class GoodsContentSearchParamTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Integer pageIndex;
    private Integer pageSize;
    // 关键字
    private String keyword;
    // 分类ID
    private Integer categoryId;
    // 品牌ID
    private Integer brandId;
    // 排序方式
    private String sort;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	
    

}
