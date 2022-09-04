package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.UserTO;

/**
 * 【修改用户信息】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class UpdateCasUserRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//用户
	private UserTO user = new UserTO();


    /**设置用户*/
	public void setUser(UserTO user){
		this.user=user;
	}
	/**获取用户*/
	public UserTO getUser(){
		return this.user;
	}


}