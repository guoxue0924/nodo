/**
 * Project Name:nodo 
 * File Name:GrouponBulkCategory.java 
 * Package Name:com.bluemobi.po.groupon 
 * Date:2016年1月5日下午1:58:31 
 */
package com.bluemobi.po.groupon;

import com.appcore.model.AbstractObject;

/**
 * 团购抢购信息和标签关系
 * 
 * ClassName: GrouponBulkCategory Date: 2016年1月5日下午1:58:31
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class GrouponBulkCategory extends AbstractObject {

	private static final long serialVersionUID = -5020088274879656854L;

	// 主键id
	private int id;
	// 团购抢购信息id
	private int bulkId;
	// 标签id
	private int categoryId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBulkId() {
		return bulkId;
	}

	public void setBulkId(int bulkId) {
		this.bulkId = bulkId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
