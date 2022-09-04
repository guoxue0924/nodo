package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【用户】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class UserTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//用户id
	private Integer userid;
	//昵称
	private String nickname;
	//图像地址
	private String avatar;
	//性别    0、未知  1、男  2、女
	private Integer gender;


    /**设置用户id*/
	public void setUserid(Integer userid){
		this.userid=userid;
	}
	/**获取用户id*/
	public Integer getUserid(){
		return this.userid;
	}
    /**设置昵称*/
	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	/**获取昵称*/
	public String getNickname(){
		return this.nickname;
	}
    /**设置图像地址*/
	public void setAvatar(String avatar){
		this.avatar=avatar;
	}
	/**获取图像地址*/
	public String getAvatar(){
		return this.avatar;
	}
    /**设置性别    0、未知  1、男  2、女*/
	public void setGender(Integer gender){
		this.gender=gender;
	}
	/**获取性别    0、未知  1、男  2、女*/
	public Integer getGender(){
		return this.gender;
	}


}
