package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【添加收藏信息】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class AddFavoriteRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//收藏类型    收藏类型:  1、商品  2、文章  3、链接  4、其他 
	private Integer type;
	//收藏内容
	private String content;


    /**设置收藏类型    收藏类型:  1、商品  2、文章  3、链接  4、其他 */
	public void setType(Integer type){
		this.type=type;
	}
	/**获取收藏类型    收藏类型:  1、商品  2、文章  3、链接  4、其他 */
	public Integer getType(){
		return this.type;
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
