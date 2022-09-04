package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.ConsigneeTO;

/**
 * 【获取默认收货地址】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetDefaultConsigneeResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//收货地址
	private ConsigneeTO consignee = new ConsigneeTO();


    /**设置收货地址*/
	public void setConsignee(ConsigneeTO consignee){
		this.consignee=consignee;
	}
	/**获取收货地址*/
	public ConsigneeTO getConsignee(){
		return this.consignee;
	}


}
