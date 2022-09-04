package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【文章】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class ArticleTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//文章id
	private Integer contentId;
	//文章标题
	private String title;
	//副标题
	private String subtitle;
	//文章图片
	private String image;
	//文章正文
	private String body;


    /**设置文章id*/
	public void setContentId(Integer contentId){
		this.contentId=contentId;
	}
	/**获取文章id*/
	public Integer getContentId(){
		return this.contentId;
	}
    /**设置文章标题*/
	public void setTitle(String title){
		this.title=title;
	}
	/**获取文章标题*/
	public String getTitle(){
		return this.title;
	}
    /**设置副标题*/
	public void setSubtitle(String subtitle){
		this.subtitle=subtitle;
	}
	/**获取副标题*/
	public String getSubtitle(){
		return this.subtitle;
	}
    /**设置文章图片*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取文章图片*/
	public String getImage(){
		return this.image;
	}
    /**设置文章正文*/
	public void setBody(String body){
		this.body=body;
	}
	/**获取文章正文*/
	public String getBody(){
		return this.body;
	}


}
