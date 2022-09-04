package com.bluemobi.apito;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【商品分类】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GoodsCategoryTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//商品分类id
	private Integer categoryId;
	//商品分类名称
	private String categoryName;
	//分类图片地址
	private String image;
	//商品子集分类
	private List<GoodsCategoryTO> subList = new ArrayList<GoodsCategoryTO>();


    /**设置商品分类id*/
	public void setCategoryId(Integer categoryId){
		this.categoryId=categoryId;
	}
	/**获取商品分类id*/
	public Integer getCategoryId(){
		return this.categoryId;
	}
    /**设置商品分类名称*/
	public void setCategoryName(String categoryName){
		this.categoryName=categoryName;
	}
	/**获取商品分类名称*/
	public String getCategoryName(){
		return this.categoryName;
	}
    /**设置分类图片地址*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取分类图片地址*/
	public String getImage(){
		return this.image;
	}
    /**设置商品子集分类*/
	public void setSubList(List<GoodsCategoryTO> subList){
		this.subList=subList;
	}
	/**获取商品子集分类*/
	public List<GoodsCategoryTO> getSubList(){
		return this.subList;
	}


}
