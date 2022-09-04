package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【添加用户积分】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class AddUserPointRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//积分类型    1:注册奖励；  2:每日登录；  3:每日签到；
	private Integer pointType;


    /**设置积分类型    1:注册奖励；  2:每日登录；  3:每日签到；*/
	public void setPointType(Integer pointType){
		this.pointType=pointType;
	}
	/**获取积分类型    1:注册奖励；  2:每日登录；  3:每日签到；*/
	public Integer getPointType(){
		return this.pointType;
	}


}
