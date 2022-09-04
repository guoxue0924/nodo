package com.bluemobi.dao.promotion;

import java.util.List;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.promotion.PromotionGoods;

/**
 * 【优惠促销活动所关联的商品表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:23
 * 
 */
public interface PromotionGoodsDao extends MyBatisBaseDao {
	
	int batchInsert(List<PromotionGoods> list);

}
