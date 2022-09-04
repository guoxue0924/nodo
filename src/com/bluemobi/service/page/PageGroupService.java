package com.bluemobi.service.page;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.page.PageGroup;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 09:33:56
 * 
 */
public interface PageGroupService extends MybatisBaseService {

	/** 
	 * savePageGroup
	 * 
	 * @author kevin
	 * @param pageGroup
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO savePageGroup(PageGroup pageGroup);

	/** 
	 * deletePageGroup
	 * 
	 * @author kevin
	 * @param groupId
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO deletePageGroup(Integer groupId);

}
