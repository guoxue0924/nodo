package com.bluemobi.service.im;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.im.ImCategory;
import com.bluemobi.to.ResultTO;

/**
 * 【IM消息分类表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 16:28:51
 * 
 */
public interface ImCategoryService extends MybatisBaseService {

	/** 
	 * saveImCategory
	 * 
	 * @author kevin
	 * @param category
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO saveImCategory(ImCategory category);

	/** 
	 * deleteCategory
	 * 
	 * @author kevin
	 * @param categoryId
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO deleteCategory(Integer categoryId);

}
