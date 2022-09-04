/**
 * Project Name:nodo 
 * File Name:GrouponBulkCategoryService.java 
 * Package Name:com.bluemobi.service.groupon 
 * Date:2016年1月5日下午2:02:38 
 */
package com.bluemobi.service.groupon;

import com.appcore.service.MybatisBaseService;

/**
 * ClassName: GrouponBulkCategoryService
 * Date: 2016年1月5日下午2:02:38

 * @author kevin
 * @version 
 * @since JDK 7
 */
public interface GrouponBulkCategoryService extends MybatisBaseService {
	
	int batchSaveGrouponBulkCategory(int bulkId, Integer[] categoryIds);
	
}
