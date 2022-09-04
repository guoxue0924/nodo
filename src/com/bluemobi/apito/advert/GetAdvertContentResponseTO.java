package com.bluemobi.apito.advert;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.AdvertTO;

/**
 * 【获取广告内容】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetAdvertContentResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//广告
    private List<AdvertTO> advertContent = new ArrayList<AdvertTO>();


    /**设置广告*/
	public void setAdvertContent(List<AdvertTO> advertContent){
		this.advertContent=advertContent;
	}
	/**获取广告*/
	public List<AdvertTO> getAdvertContent(){
		return this.advertContent;
	}


}
