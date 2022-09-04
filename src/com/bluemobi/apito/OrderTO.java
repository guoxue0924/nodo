package com.bluemobi.apito;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【订单】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class OrderTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//订单编号
	private String orderNumber;
	//订单状态    0:待处理     1:已签收      2:待付款     3:付款成功     4:待发货     5:已发货  
	private Integer status;
	//下单时间
	private Date ctime;
	//订单id
	private Integer orderId;
	//物流单号
	private String logisticsNumber;
	//总金额
	private BigDecimal totalAmount;
	//总数量
	private Integer count;
	//运费
	private BigDecimal freight;
	//支付状态
	private Integer payStatus;
	//支付类型
	private Integer payType;
	//支付时间
	private Date payTime;
	//发货时间
	private Date deliveryTime;
	//订单子项
	private List<ItemTO> items = new ArrayList<ItemTO>();
	//收货地址
	private ConsigneeTO consignee = new ConsigneeTO();


    /**设置订单编号*/
	public void setOrderNumber(String orderNumber){
		this.orderNumber=orderNumber;
	}
	/**获取订单编号*/
	public String getOrderNumber(){
		return this.orderNumber;
	}
    /**设置订单状态    0:待处理     1:已签收      2:待付款     3:付款成功     4:待发货     5:已发货  */
	public void setStatus(Integer status){
		this.status=status;
	}
	/**获取订单状态    0:待处理     1:已签收      2:待付款     3:付款成功     4:待发货     5:已发货  */
	public Integer getStatus(){
		return this.status;
	}
    /**设置下单时间*/
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	/**获取下单时间*/
	public Date getCtime(){
		return this.ctime;
	}
    /**设置订单id*/
	public void setOrderId(Integer orderId){
		this.orderId=orderId;
	}
	/**获取订单id*/
	public Integer getOrderId(){
		return this.orderId;
	}
    /**设置物流单号*/
	public void setLogisticsNumber(String logisticsNumber){
		this.logisticsNumber=logisticsNumber;
	}
	/**获取物流单号*/
	public String getLogisticsNumber(){
		return this.logisticsNumber;
	}
    /**设置总金额*/
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount=totalAmount;
	}
	/**获取总金额*/
	public BigDecimal getTotalAmount(){
		return this.totalAmount;
	}
    /**设置总数量*/
	public void setCount(Integer count){
		this.count=count;
	}
	/**获取总数量*/
	public Integer getCount(){
		return this.count;
	}
    /**设置运费*/
	public void setFreight(BigDecimal freight){
		this.freight=freight;
	}
	/**获取运费*/
	public BigDecimal getFreight(){
		return this.freight;
	}
    /**设置支付状态*/
	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	/**获取支付状态*/
	public Integer getPayStatus(){
		return this.payStatus;
	}
    /**设置支付类型*/
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	/**获取支付类型*/
	public Integer getPayType(){
		return this.payType;
	}
    /**设置支付时间*/
	public void setPayTime(Date payTime){
		this.payTime=payTime;
	}
	/**获取支付时间*/
	public Date getPayTime(){
		return this.payTime;
	}
    /**设置发货时间*/
	public void setDeliveryTime(Date deliveryTime){
		this.deliveryTime=deliveryTime;
	}
	/**获取发货时间*/
	public Date getDeliveryTime(){
		return this.deliveryTime;
	}
    /**设置订单子项*/
	public void setItems(List<ItemTO> items){
		this.items=items;
	}
	/**获取订单子项*/
	public List<ItemTO> getItems(){
		return this.items;
	}
    /**设置收货地址*/
	public void setConsignee(ConsigneeTO consignee){
		this.consignee=consignee;
	}
	/**获取收货地址*/
	public ConsigneeTO getConsignee(){
		return this.consignee;
	}


}
