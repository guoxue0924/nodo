package com.bluemobi.apito.advert;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取广告内容】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetAdvertContentRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//广告页id
	private Integer pageId;
	//广告位id
	private Integer boardId;


    /**设置广告页id*/
	public void setPageId(Integer pageId){
		this.pageId=pageId;
	}
	/**获取广告页id*/
	public Integer getPageId(){
		return this.pageId;
	}
    /**设置广告位id*/
	public void setBoardId(Integer boardId){
		this.boardId=boardId;
	}
	/**获取广告位id*/
	public Integer getBoardId(){
		return this.boardId;
	}


}
