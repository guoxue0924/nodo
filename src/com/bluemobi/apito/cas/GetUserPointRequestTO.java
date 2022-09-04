package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取用户积分】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetUserPointRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//当前页码
	private Integer pageIndex;
	//每页数目
	private Integer pageSize;


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
