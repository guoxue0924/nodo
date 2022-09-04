package com.bluemobi.dao.promotion;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【优惠促销活动主体表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:22
 * 
 */
public interface PromotionContentDao extends MyBatisBaseDao {
	
	int selectForCheckActive(Map<String, Object> param);
	
	int updateStatus(int promotionId);
	
}
