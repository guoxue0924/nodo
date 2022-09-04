package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【商品品牌】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GoodsBrandTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//品牌id
	private Integer brandId;
	//品牌名称
	private String name;
	//品牌图片地址
	private String image;


    /**设置品牌id*/
	public void setBrandId(Integer brandId){
		this.brandId=brandId;
	}
	/**获取品牌id*/
	public Integer getBrandId(){
		return this.brandId;
	}
    /**设置品牌名称*/
	public void setName(String name){
		this.name=name;
	}
	/**获取品牌名称*/
	public String getName(){
		return this.name;
	}
    /**设置品牌图片地址*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取品牌图片地址*/
	public String getImage(){
		return this.image;
	}


}
