package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【注册】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class RegisterRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//用户名
	private String username;
	//密码
	private String password;
	//验证码
	private String authCode;


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
    /**设置验证码*/
	public void setAuthCode(String authCode){
		this.authCode=authCode;
	}
	/**获取验证码*/
	public String getAuthCode(){
		return this.authCode;
	}


}
