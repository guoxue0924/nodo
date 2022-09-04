package com.bluemobi.apito.cas;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.ConsigneeTO;

/**
 * 【获取收货地址列表】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetConsigneesResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//收货地址
    private List<ConsigneeTO> consignees = new ArrayList<ConsigneeTO>();


    /**设置收货地址*/
	public void setConsignees(List<ConsigneeTO> consignees){
		this.consignees=consignees;
	}
	/**获取收货地址*/
	public List<ConsigneeTO> getConsignees(){
		return this.consignees;
	}


}
