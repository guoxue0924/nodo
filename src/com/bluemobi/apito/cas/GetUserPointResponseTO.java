package com.bluemobi.apito.cas;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.UserPointTO;

/**
 * 【获取用户积分】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetUserPointResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//用户积分
    private List<UserPointTO> userPoints = new ArrayList<UserPointTO>();
	//当前页码
	private Integer pageIndex;
	//每页数目
	private Integer pageSize;


    /**设置用户积分*/
	public void setUserPoints(List<UserPointTO> userPoints){
		this.userPoints=userPoints;
	}
	/**获取用户积分*/
	public List<UserPointTO> getUserPoints(){
		return this.userPoints;
	}
    /**设置当前页码*/
	public void setPageIndex(Integer pageIndex){
		this.pageIndex=pageIndex;
	}
	/**获取当前页码*/
	public Integer getPageIndex(){
		return this.pageIndex;
	}
    /**设置每页数目*/
	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
	/**获取每页数目*/
	public Integer getPageSize(){
		return this.pageSize;
	}


}
