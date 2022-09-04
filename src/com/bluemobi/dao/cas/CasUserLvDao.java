package com.bluemobi.dao.cas;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【用户等级表】 数据访问对象 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-11 16:25:18
 *
 */
public interface CasUserLvDao extends MyBatisBaseDao{    
	
    
    /**
     * 将所有等级的默认值修改为0
     * @author HeWW
     * 2016-3-10
     */
    void updateIsDefault();


}


