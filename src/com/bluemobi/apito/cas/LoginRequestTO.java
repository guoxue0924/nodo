package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【登录】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class LoginRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//登录类型    1、普通密码登录  2、动态密码登录  
	private Integer loginType;
	//用户名
	private String username;
	//密码
	private String password;


    /**设置登录类型    1、普通密码登录  2、动态密码登录  */
	public void setLoginType(Integer loginType){
		this.loginType=loginType;
	}
	/**获取登录类型    1、普通密码登录  2、动态密码登录  */
	public Integer getLoginType(){
		return this.loginType;
	}
    /**设置用户名*/
	public void setUsername(String username){
		this.username=username;
	}
	/**获取用户名*/
	public String getUsername(){
		return this.username;
	}
    /**设置密码*/
	public void setPassword(String password){
		this.password=password;
	}
	/**获取密码*/
	public String getPassword(){
		return this.password;
	}


}
