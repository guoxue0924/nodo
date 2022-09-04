package com.bluemobi.apito;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【属性】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class PropertyTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//属性id
	private Integer propertyId;
	//属性名称
	private String propertyName;
	//属性值
	private List<PropertyValueTO> propertyValues = new ArrayList<PropertyValueTO>();


    /**设置属性id*/
	public void setPropertyId(Integer propertyId){
		this.propertyId=propertyId;
	}
	/**获取属性id*/
	public Integer getPropertyId(){
		return this.propertyId;
	}
    /**设置属性名称*/
	public void setPropertyName(String propertyName){
		this.propertyName=propertyName;
	}
	/**获取属性名称*/
	public String getPropertyName(){
		return this.propertyName;
	}
    /**设置属性值*/
	public void setPropertyValues(List<PropertyValueTO> propertyValues){
		this.propertyValues=propertyValues;
	}
	/**获取属性值*/
	public List<PropertyValueTO> getPropertyValues(){
		return this.propertyValues;
	}


}
