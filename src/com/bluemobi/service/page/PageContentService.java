package com.bluemobi.service.page;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.page.PageContent;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 09:33:56
 * 
 */
public interface PageContentService extends MybatisBaseService {

	/** 
	 * savePageContent
	 * 
	 * @author kevin
	 * @param content
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO savePageContent(PageContent content);

	/** 
	 * deletePageContent
	 * 
	 * @author kevin
	 * @param contentId
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO deletePageContent(Integer contentId);

}
