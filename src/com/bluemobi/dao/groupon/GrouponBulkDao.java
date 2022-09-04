package com.bluemobi.dao.groupon;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.groupon.GrouponBulk;
import com.bluemobi.to.groupon.GrouponBulkTO;

/**
 * 【团购表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:17
 * 
 */
public interface GrouponBulkDao extends MyBatisBaseDao {

    List<GrouponBulk> selectForCheckTime(Map<String, Object> parameter);
    
    GrouponBulk selectBySku(Map<String, Object> parameter);
    
    GrouponBulkTO selectDetail(int bulkId);
    
}
