package com.bluemobi.service.article;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.article.ArticleCategory;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-19 15:53:37
 * 
 */
public interface ArticleCategoryService extends MybatisBaseService {

	/** 
	 * 保存文章分类
	 * saveArticleCategory
	 * 
	 * @author kevin
	 * @param category
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO saveArticleCategory(ArticleCategory category);

	/** 
	 * 删除文章分类
	 * deleteCategory
	 * 
	 * @author kevin
	 * @param categoryId
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO deleteCategory(Integer categoryId);

}
