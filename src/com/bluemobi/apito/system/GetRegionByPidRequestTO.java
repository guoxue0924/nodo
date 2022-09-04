package com.bluemobi.apito.system;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【根据Pid获得地区信息】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetRegionByPidRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//地区父级Id
	private Integer pid;


    /**设置地区父级Id*/
	public void setPid(Integer pid){
		this.pid=pid;
	}
	/**获取地区父级Id*/
	public Integer getPid(){
		return this.pid;
	}


}
