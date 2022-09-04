package com.bluemobi.apito.trend;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.VersionTO;

/**
 * 【检查APP版本信息】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CheckVersionResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//APP 平台类型
	private String platform;
	//APP 当前版本号
	private String vCodeNew;
	//版本
	private VersionTO version = new VersionTO();


    /**设置APP 平台类型*/
	public void setPlatform(String platform){
		this.platform=platform;
	}
	/**获取APP 平台类型*/
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
    /**设置版本*/
	public void setVersion(VersionTO version){
		this.version=version;
	}
	/**获取版本*/
	public VersionTO getVersion(){
		return this.version;
	}


}
