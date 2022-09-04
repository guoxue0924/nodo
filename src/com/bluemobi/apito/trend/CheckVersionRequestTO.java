package com.bluemobi.apito.trend;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【检查APP版本信息】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CheckVersionRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//APP 平台类型    iphone  web  android
	private String platform;
	//APP 当前版本号
	private String vCodeNew;


    /**设置APP 平台类型    iphone  web  android*/
	public void setPlatform(String platform){
		this.platform=platform;
	}
	/**获取APP 平台类型    iphone  web  android*/
	public String getPlatform(){
		return this.platform;
	}
    /**设置APP 当前版本号*/
	public void setVCodeNew(String vCodeNew){
		this.vCodeNew=vCodeNew;
	}
	/**获取APP 当前版本号*/
	public String getVCodeNew(){
		return this.vCodeNew;
	}


}
