package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【sku属性】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class SkuPropertyTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//属性id
	private Integer propertyId;
	//属性值id
	private Integer propertyValueId;


    /**设置属性id*/
	public void setPropertyId(Integer propertyId){
		this.propertyId=propertyId;
	}
	/**获取属性id*/
	public Integer getPropertyId(){
		return this.propertyId;
	}
    /**设置属性值id*/
	public void setPropertyValueId(Integer propertyValueId){
		this.propertyValueId=propertyValueId;
	}
	/**获取属性值id*/
	public Integer getPropertyValueId(){
		return this.propertyValueId;
	}


}
