package com.bluemobi.apito.bts;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.OrderTO;

/**
 * 【订单列表】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetOrderlistResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//订单
    private List<OrderTO> orders = new ArrayList<OrderTO>();


    /**设置订单*/
	public void setOrders(List<OrderTO> orders){
		this.orders=orders;
	}
	/**获取订单*/
	public List<OrderTO> getOrders(){
		return this.orders;
	}


}
