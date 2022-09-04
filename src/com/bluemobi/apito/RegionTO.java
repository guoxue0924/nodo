package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【地区】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class RegionTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//地区ID
	private Integer regionId;
	//地区父级ID
	private Integer pid;
	//地区名称
	private String regionName;


    /**设置地区ID*/
	public void setRegionId(Integer regionId){
		this.regionId=regionId;
	}
	/**获取地区ID*/
	public Integer getRegionId(){
		return this.regionId;
	}
    /**设置地区父级ID*/
	public void setPid(Integer pid){
		this.pid=pid;
	}
	/**获取地区父级ID*/
	public Integer getPid(){
		return this.pid;
	}
    /**设置地区名称*/
	public void setRegionName(String regionName){
		this.regionName=regionName;
	}
	/**获取地区名称*/
	public String getRegionName(){
		return this.regionName;
	}


}
