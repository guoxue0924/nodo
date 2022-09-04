package com.bluemobi.apito.bts;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【订单列表】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetOrderlistRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//订单状态    订单状态。  0:待处理     1:已签收      2:待付款     3:付款成功     4:待发货     5:已发货 
	private Integer status;
	//页码
	private Integer pageNum;
	//每页记录数
	private Integer pageSize;


    /**设置订单状态    订单状态。  0:待处理     1:已签收      2:待付款     3:付款成功     4:待发货     5:已发货 */
	public void setStatus(Integer status){
		this.status=status;
	}
	/**获取订单状态    订单状态。  0:待处理     1:已签收      2:待付款     3:付款成功     4:待发货     5:已发货 */
	public Integer getStatus(){
		return this.status;
	}
    /**设置页码*/
	public void setPageNum(Integer pageNum){
		this.pageNum=pageNum;
	}
	/**获取页码*/
	public Integer getPageNum(){
		return this.pageNum;
	}
    /**设置每页记录数*/
	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
	/**获取每页记录数*/
	public Integer getPageSize(){
		return this.pageSize;
	}


}
