package com.bluemobi.apito;


import java.util.Date;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【用户积分】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class UserPointTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//积分ID
	private Integer pointId;
	//积分类型    1:注册奖励；  2:每日登录；  3:每日签到；
	private Integer pointType;
	//积分名称
	private String pointName;
	//积分
	private Integer point;
	//积分获得时间
	private Date ctime;


    /**设置积分ID*/
	public void setPointId(Integer pointId){
		this.pointId=pointId;
	}
	/**获取积分ID*/
	public Integer getPointId(){
		return this.pointId;
	}
    /**设置积分类型    1:注册奖励；  2:每日登录；  3:每日签到；*/
	public void setPointType(Integer pointType){
		this.pointType=pointType;
	}
	/**获取积分类型    1:注册奖励；  2:每日登录；  3:每日签到；*/
	public Integer getPointType(){
		return this.pointType;
	}
    /**设置积分名称*/
	public void setPointName(String pointName){
		this.pointName=pointName;
	}
	/**获取积分名称*/
	public String getPointName(){
		return this.pointName;
	}
    /**设置积分*/
	public void setPoint(Integer point){
		this.point=point;
	}
	/**获取积分*/
	public Integer getPoint(){
		return this.point;
	}
    /**设置积分获得时间*/
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	/**获取积分获得时间*/
	public Date getCtime(){
		return this.ctime;
	}


}
