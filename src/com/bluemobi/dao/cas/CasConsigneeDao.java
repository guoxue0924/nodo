package com.bluemobi.dao.cas;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.cas.CasConsignee;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 17:01:42
 * 
 */
public interface CasConsigneeDao extends MyBatisBaseDao {
    
    CasConsignee selectForOrder(Map<String, Object> parameter);
    
}
