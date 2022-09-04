package com.bluemobi.apito.article;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.ArticleTO;

/**
 * 【获取文章详情】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetArticleInfoResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//文章
	private ArticleTO article = new ArticleTO();


    /**设置文章*/
	public void setArticle(ArticleTO article){
		this.article=article;
	}
	/**获取文章*/
	public ArticleTO getArticle(){
		return this.article;
	}


}
