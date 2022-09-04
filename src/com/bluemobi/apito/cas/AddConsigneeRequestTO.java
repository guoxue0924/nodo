package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【添加收货地址】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class AddConsigneeRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//姓名
	private String name;
	//手机号码
	private String mobilePhone;
	//最后一级地区id
	private Integer regionId;
	//详细地址
	private String address;


    /**设置姓名*/
	public void setName(String name){
		this.name=name;
	}
	/**获取姓名*/
	public String getName(){
		return this.name;
	}
    /**设置手机号码*/
	public void setMobilePhone(String mobilePhone){
		this.mobilePhone=mobilePhone;
	}
	/**获取手机号码*/
	public String getMobilePhone(){
		return this.mobilePhone;
	}
    /**设置最后一级地区id*/
	public void setRegionId(Integer regionId){
		this.regionId=regionId;
	}
	/**获取最后一级地区id*/
	public Integer getRegionId(){
		return this.regionId;
	}
    /**设置详细地址*/
	public void setAddress(String address){
		this.address=address;
	}
	/**获取详细地址*/
	public String getAddress(){
		return this.address;
	}


}
