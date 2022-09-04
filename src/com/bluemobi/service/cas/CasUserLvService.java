package com.bluemobi.service.cas;

import com.appcore.service.MybatisBaseService;

/**
 * 【用户等级表】 服务类 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-11 16:25:18
 *
 */
public interface CasUserLvService extends MybatisBaseService{    
	
	/**
	 * 将所有等级的默认值修改为0
	 * @author HeWW
	 * 2016-3-10
	 */
    void updateIsDefault();

}

