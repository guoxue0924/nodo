package com.bluemobi.dao.bts;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.to.bts.RefundDetailTO;
import com.bluemobi.to.bts.RefundPageTO;

/**
 * 【退货流程日志表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public interface BtsOrderRefundDao extends MyBatisBaseDao {
	
	RefundDetailTO getDetailInfo(int refundId);
	
	List<RefundPageTO> frontPage(Map<String, Object> param);
	
	int frontPageCount(Map<String, Object> param);

}
