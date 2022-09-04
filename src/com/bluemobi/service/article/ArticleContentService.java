package com.bluemobi.service.article;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.article.ArticleContent;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-19 15:53:38
 * 
 */
public interface ArticleContentService extends MybatisBaseService {

	/** 
	 * 保存文章
	 * saveArticleContent
	 * 
	 * @author kevin
	 * @param content
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO saveArticleContent(ArticleContent content);

	/** 
	 * 删除文章
	 * deleteConent
	 * 
	 * @author kevin
	 * @param contentIdList
	 * @param isDelete  是否实际删除
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO deleteConent(List<Integer> contentIdList, boolean isDelete);

	/** 
	 * publishArticleContent
	 * 
	 * @author kevin
	 * @param contentId
	 * @param isPublished
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO publishArticleContent(Integer contentId, boolean isPublished);

	/** 
	 * recommenedArticleContent
	 * 
	 * @author kevin
	 * @param contentId
	 * @param isRecommended
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO recommenedArticleContent(Integer contentId, boolean isRecommended);

	/** 
	 * restoreConent
	 * 
	 * @author kevin
	 * @param contentId
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO restoreConent(Integer contentId);

	List<Map<String, Object>> selectObjectListBycondition();

}
