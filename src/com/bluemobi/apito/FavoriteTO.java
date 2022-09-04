package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【收藏】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class FavoriteTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//收藏id
	private Integer favoriteId;
	//收藏类型    收藏类型:  1、商品  2、文章  3、链接  4、其他 
	private Integer type;
	//收藏内容
	private String content;
	//名称
	private String name;
	//图片
	private String image;
	//价格
	private Float price;
	//收藏时间
	private String ctime;


    /**设置收藏id*/
	public void setFavoriteId(Integer favoriteId){
		this.favoriteId=favoriteId;
	}
	/**获取收藏id*/
	public Integer getFavoriteId(){
		return this.favoriteId;
	}
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
    /**设置名称*/
	public void setName(String name){
		this.name=name;
	}
	/**获取名称*/
	public String getName(){
		return this.name;
	}
    /**设置图片*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取图片*/
	public String getImage(){
		return this.image;
	}
    /**设置价格*/
	public void setPrice(Float price){
		this.price=price;
	}
	/**获取价格*/
	public Float getPrice(){
		return this.price;
	}
    /**设置收藏时间*/
	public void setCtime(String ctime){
		this.ctime=ctime;
	}
	/**获取收藏时间*/
	public String getCtime(){
		return this.ctime;
	}


}
