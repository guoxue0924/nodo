package com.bluemobi.serviceimpl.promotion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.promotion.PromotionGoodsDao;
import com.bluemobi.po.promotion.PromotionGoods;
import com.bluemobi.service.promotion.PromotionGoodsService;

/**
 * 【优惠促销活动所关联的商品表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:23
 * 
 */
@Service(value = "promotionGoodsService")
public class PromotionGoodsServiceImpl extends MybatisBaseServiceImpl implements PromotionGoodsService {

    @Autowired
    private PromotionGoodsDao promotionGoodsDao;

    @Override
    public MyBatisBaseDao getDao() {
        return promotionGoodsDao;
    }

	/** 
	 * @see com.bluemobi.service.promotion.PromotionGoodsService#batchSavePromotionGoods(int, java.lang.Long[]) 
	 */  
	@Override
	public int batchSavePromotionGoods(int promotionId, Long[] skuIdList) {
		List<PromotionGoods> relationList = new ArrayList<PromotionGoods>();
		PromotionGoods relation = null;
		for (Long skuId : skuIdList) {
			if(skuId > 0) {
				relation = new PromotionGoods();
				relation.setPromotionId(promotionId);
				relation.setSkuId(skuId);
				relationList.add(relation);
			}
		}
		return promotionGoodsDao.batchInsert(relationList);
	}

}
