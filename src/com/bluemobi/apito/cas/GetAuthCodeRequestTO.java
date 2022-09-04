package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取验证码】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetAuthCodeRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//验证码类型    1、登录  2、注册  3、修改密码
	private Integer authCodeType;
	//手机号码
	private String phone;


    /**设置验证码类型    1、登录  2、注册  3、修改密码*/
	public void setAuthCodeType(Integer authCodeType){
		this.authCodeType=authCodeType;
	}
	/**获取验证码类型    1、登录  2、注册  3、修改密码*/
	public Integer getAuthCodeType(){
		return this.authCodeType;
	}
    /**设置手机号码*/
	public void setPhone(String phone){
		this.phone=phone;
	}
	/**获取手机号码*/
	public String getPhone(){
		return this.phone;
	}


}
