package com.bluemobi.apito;


import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【版本】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class VersionTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//ID
	private Integer id;
	//平台类型
	private String platform;
	//vCode
	private Short vCode;
	//版本名称
	private BigDecimal vName;
	//版本详情
	private String content;
	//文件路径
	private String filepath;
	//安装包大小
	private String size;
	//二维码图片
	private String filepathTdc;
	//最后一次更新时间
	private Date mtime;


    /**设置ID*/
	public void setId(Integer id){
		this.id=id;
	}
	/**获取ID*/
	public Integer getId(){
		return this.id;
	}
    /**设置平台类型*/
	public void setPlatform(String platform){
		this.platform=platform;
	}
	/**获取平台类型*/
	public String getPlatform(){
		return this.platform;
	}
    /**设置vCode*/
	public void setVCode(Short vCode){
		this.vCode=vCode;
	}
	/**获取vCode*/
	public Short getVCode(){
		return this.vCode;
	}
    /**设置版本名称*/
	public void setVName(BigDecimal vName){
		this.vName=vName;
	}
	/**获取版本名称*/
	public BigDecimal getVName(){
		return this.vName;
	}
    /**设置版本详情*/
	public void setContent(String content){
		this.content=content;
	}
	/**获取版本详情*/
	public String getContent(){
		return this.content;
	}
    /**设置文件路径*/
	public void setFilepath(String filepath){
		this.filepath=filepath;
	}
	/**获取文件路径*/
	public String getFilepath(){
		return this.filepath;
	}
    /**设置安装包大小*/
	public void setSize(String size){
		this.size=size;
	}
	/**获取安装包大小*/
	public String getSize(){
		return this.size;
	}
    /**设置二维码图片*/
	public void setFilepathTdc(String filepathTdc){
		this.filepathTdc=filepathTdc;
	}
	/**获取二维码图片*/
	public String getFilepathTdc(){
		return this.filepathTdc;
	}
    /**设置最后一次更新时间*/
	public void setMtime(Date mtime){
		this.mtime=mtime;
	}
	/**获取最后一次更新时间*/
	public Date getMtime(){
		return this.mtime;
	}


}
