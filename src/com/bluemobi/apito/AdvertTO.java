package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【广告】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class AdvertTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//广告id
	private Integer advertId;
	//广告图片地址
	private String image;
	//绑定资源内容
	private String bindSource;
	//广告标题
	private String title;
	//广告详情
	private String content;


    /**设置广告id*/
	public void setAdvertId(Integer advertId){
		this.advertId=advertId;
	}
	/**获取广告id*/
	public Integer getAdvertId(){
		return this.advertId;
	}
    /**设置广告图片地址*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取广告图片地址*/
	public String getImage(){
		return this.image;
	}
    /**设置绑定资源内容*/
	public void setBindSource(String bindSource){
		this.bindSource=bindSource;
	}
	/**获取绑定资源内容*/
	public String getBindSource(){
		return this.bindSource;
	}
    /**设置广告标题*/
	public void setTitle(String title){
		this.title=title;
	}
	/**获取广告标题*/
	public String getTitle(){
		return this.title;
	}
    /**设置广告详情*/
	public void setContent(String content){
		this.content=content;
	}
	/**获取广告详情*/
	public String getContent(){
		return this.content;
	}


}
