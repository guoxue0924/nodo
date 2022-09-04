package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【收货地址】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class ConsigneeTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//收货地址id
	private Integer consigneeId;
	//姓名
	private String name;
	//手机号码
	private String mobilePhone;
	//一级地区id
	private Integer regionId1;
	//二级地区id
	private Integer regionId2;
	//三级地区id
	private Integer regionId3;
	//地区名称
	private String regionName;
	//详细地址
	private String address;
	//是否是默认地址
	private Integer isDefault;


    /**设置收货地址id*/
	public void setConsigneeId(Integer consigneeId){
		this.consigneeId=consigneeId;
	}
	/**获取收货地址id*/
	public Integer getConsigneeId(){
		return this.consigneeId;
	}
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
    /**设置一级地区id*/
	public void setRegionId1(Integer regionId1){
		this.regionId1=regionId1;
	}
	/**获取一级地区id*/
	public Integer getRegionId1(){
		return this.regionId1;
	}
    /**设置二级地区id*/
	public void setRegionId2(Integer regionId2){
		this.regionId2=regionId2;
	}
	/**获取二级地区id*/
	public Integer getRegionId2(){
		return this.regionId2;
	}
    /**设置三级地区id*/
	public void setRegionId3(Integer regionId3){
		this.regionId3=regionId3;
	}
	/**获取三级地区id*/
	public Integer getRegionId3(){
		return this.regionId3;
	}
    /**设置地区名称*/
	public void setRegionName(String regionName){
		this.regionName=regionName;
	}
	/**获取地区名称*/
	public String getRegionName(){
		return this.regionName;
	}
    /**设置详细地址*/
	public void setAddress(String address){
		this.address=address;
	}
	/**获取详细地址*/
	public String getAddress(){
		return this.address;
	}
    /**设置是否是默认地址*/
	public void setIsDefault(Integer isDefault){
		this.isDefault=isDefault;
	}
	/**获取是否是默认地址*/
	public Integer getIsDefault(){
		return this.isDefault;
	}


}
