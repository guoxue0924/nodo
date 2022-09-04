package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取用户是否已签到】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CheckTodayIsRegisterResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//是否已签到    0:未签到；  1:已签到；
	private Integer flag;


    /**设置是否已签到    0:未签到；  1:已签到；*/
	public void setFlag(Integer flag){
		this.flag=flag;
	}
	/**获取是否已签到    0:未签到；  1:已签到；*/
	public Integer getFlag(){
		return this.flag;
	}


}
