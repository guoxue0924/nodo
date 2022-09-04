/**
 * Project Name:nodo 
 * File Name:GrouponBulkCategory.java 
 * Package Name:com.bluemobi.serviceimpl.groupon 
 * Date:2016年1月5日下午2:03:17 
 */
package com.bluemobi.serviceimpl.groupon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.groupon.GrouponBulkCategoryDao;
import com.bluemobi.po.groupon.GrouponBulkCategory;
import com.bluemobi.service.groupon.GrouponBulkCategoryService;

/**
 * ClassName: GrouponBulkCategory
 * Date: 2016年1月5日下午2:03:17

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Service(value = "grouponBulkCategoryService")
public class GrouponBulkCategoryServiceImpl extends MybatisBaseServiceImpl  implements GrouponBulkCategoryService {

	@Autowired
	private GrouponBulkCategoryDao grouponBulkCategoryDao;

	@Override
	public MyBatisBaseDao getDao() {
		return grouponBulkCategoryDao;
	}

	/** 
	 * @see com.bluemobi.service.groupon.GrouponBulkCategoryService#batchSaveGrouponBulkCategory(int, java.lang.Integer[]) 
	 */  
	@Override
	public int batchSaveGrouponBulkCategory(int bulkId, Integer[] categoryIds) {
		List<GrouponBulkCategory> list = new ArrayList<GrouponBulkCategory>();
		GrouponBulkCategory bulkCategory = null;
		for (Integer categoryId : categoryIds) {
			bulkCategory = new GrouponBulkCategory();
			bulkCategory.setBulkId(bulkId);
			bulkCategory.setCategoryId(categoryId);
			list.add(bulkCategory);
		}
		return grouponBulkCategoryDao.batchInsert(list);
	}


}
