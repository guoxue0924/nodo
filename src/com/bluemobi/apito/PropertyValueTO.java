package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【属性值】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class PropertyValueTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//属性值id
	private Integer propertyValueId;
	//属性值名称
	private String propertyValueName;
	//属性值对应的图片
	private String image;


    /**设置属性值id*/
	public void setPropertyValueId(Integer propertyValueId){
		this.propertyValueId=propertyValueId;
	}
	/**获取属性值id*/
	public Integer getPropertyValueId(){
		return this.propertyValueId;
	}
    /**设置属性值名称*/
	public void setPropertyValueName(String propertyValueName){
		this.propertyValueName=propertyValueName;
	}
	/**获取属性值名称*/
	public String getPropertyValueName(){
		return this.propertyValueName;
	}
    /**设置属性值对应的图片*/
	public void setImage(String image){
		this.image=image;
	}
	/**获取属性值对应的图片*/
	public String getImage(){
		return this.image;
	}


}
