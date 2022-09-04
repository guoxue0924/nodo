package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【删除收藏】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class DeleteFavoriteRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//收藏id
	private Integer favoriteId;
	//收藏内容
	private String content;


    /**设置收藏id*/
	public void setFavoriteId(Integer favoriteId){
		this.favoriteId=favoriteId;
	}
	/**获取收藏id*/
	public Integer getFavoriteId(){
		return this.favoriteId;
	}
    /**设置收藏内容*/
	public void setContent(String content){
		this.content=content;
	}
	/**获取收藏内容*/
	public String getContent(){
		return this.content;
	}


}
