package com.bluemobi.apito.cas;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.FavoriteTO;

/**
 * 【获取所有收藏信息】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetFavoritesResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//收藏
    private List<FavoriteTO> favorites = new ArrayList<FavoriteTO>();


    /**设置收藏*/
	public void setFavorites(List<FavoriteTO> favorites){
		this.favorites=favorites;
	}
	/**获取收藏*/
	public List<FavoriteTO> getFavorites(){
		return this.favorites;
	}


}
