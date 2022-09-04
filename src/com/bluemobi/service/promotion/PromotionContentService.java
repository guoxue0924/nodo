package com.bluemobi.service.promotion;

import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.promotion.PromotionContent;

/**
 * 【优惠促销活动主体表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:22
 * 
 */
public interface PromotionContentService extends MybatisBaseService {

	/** 
	 * 保存促销活动信息
	 * savePromotion
	 * 
	 * @author kevin
	 * @param promotionContent
	 * @param skuIdList 
	 * @since JDK 7 
	 */  
	void savePromotion(PromotionContent promotionContent, Long[] skuIdList);
	
	boolean checkActive(Map<String, Object> param);

	/** 
	 * changeStatus
	 * 
	 * @author kevin
	 * @param promotionId 
	 * @since JDK 7 
	 */  
	void changeStatus(Integer promotionId);
	
}
