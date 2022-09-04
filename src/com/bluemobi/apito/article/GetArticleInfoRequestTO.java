package com.bluemobi.apito.article;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取文章详情】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetArticleInfoRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//文章id
	private Integer contentId;


    /**设置文章id*/
	public void setContentId(Integer contentId){
		this.contentId=contentId;
	}
	/**获取文章id*/
	public Integer getContentId(){
		return this.contentId;
	}


}
