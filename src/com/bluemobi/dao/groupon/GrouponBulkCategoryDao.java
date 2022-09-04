/**
 * Project Name:nodo 
 * File Name:GrouponBulkCategoryDao.java 
 * Package Name:com.bluemobi.dao.groupon 
 * Date:2016年1月5日下午2:01:28 
 */
package com.bluemobi.dao.groupon;

import java.util.List;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.groupon.GrouponBulkCategory;

/**
 * ClassName: GrouponBulkCategoryDao
 * Date: 2016年1月5日下午2:01:28

 * @author kevin
 * @version 
 * @since JDK 7
 */
public interface GrouponBulkCategoryDao extends MyBatisBaseDao {
	
	int batchInsert(List<GrouponBulkCategory> list);

}
