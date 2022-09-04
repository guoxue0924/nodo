package com.bluemobi.service.promotion;

import com.appcore.service.MybatisBaseService;

/**
 * 【优惠促销活动所关联的商品表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:23
 * 
 */
public interface PromotionGoodsService extends MybatisBaseService {
	
	int batchSavePromotionGoods(int promotionId, Long[] skuIdList);
	
}
