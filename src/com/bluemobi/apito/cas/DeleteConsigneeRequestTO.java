package com.bluemobi.apito.cas;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【删除收货地址】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class DeleteConsigneeRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//收货地址id
	private Integer consigneeId;


    /**设置收货地址id*/
	public void setConsigneeId(Integer consigneeId){
		this.consigneeId=consigneeId;
	}
	/**获取收货地址id*/
	public Integer getConsigneeId(){
		return this.consigneeId;
	}


}
