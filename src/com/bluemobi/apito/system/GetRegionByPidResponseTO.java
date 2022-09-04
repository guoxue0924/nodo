package com.bluemobi.apito.system;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.RegionTO;

/**
 * 【根据Pid获得地区信息】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetRegionByPidResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//地区
    private List<RegionTO> regions = new ArrayList<RegionTO>();


    /**设置地区*/
	public void setRegions(List<RegionTO> regions){
		this.regions=regions;
	}
	/**获取地区*/
	public List<RegionTO> getRegions(){
		return this.regions;
	}


}
