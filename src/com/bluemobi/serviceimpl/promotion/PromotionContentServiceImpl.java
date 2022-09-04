package com.bluemobi.serviceimpl.promotion;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.promotion.PromotionContentDao;
import com.bluemobi.po.promotion.PromotionContent;
import com.bluemobi.service.promotion.PromotionContentService;
import com.bluemobi.service.promotion.PromotionGoodsService;

/**
 * 【优惠促销活动主体表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:22
 * 
 */
@Service(value = "promotionContentService")
public class PromotionContentServiceImpl extends MybatisBaseServiceImpl implements PromotionContentService {

    @Autowired
    private PromotionContentDao promotionContentDao;
    @Autowired
    private PromotionGoodsService promotionGoodsService;

    @Override
    public MyBatisBaseDao getDao() {
        return promotionContentDao;
    }

	/** 
	 * @see com.bluemobi.service.promotion.PromotionContentService#savePromotion(com.bluemobi.po.promotion.PromotionContent, java.lang.Integer[]) 
	 */  
	@Override
	public void savePromotion(PromotionContent promotionContent,
			Long[] skuIdList) {
		promotionContent.setIsDel((byte)0);
		if(promotionContent.getPromotionId() == null || promotionContent.getPromotionId() == 0) {
			promotionContent.setCtime(Calendar.getInstance().getTime());
			promotionContentDao.insert(promotionContent);
		} else {
			promotionContent.setMtime(Calendar.getInstance().getTime());
			promotionContentDao.update(promotionContent);
		}
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("promotionId", promotionContent.getPromotionId());
		promotionGoodsService.delete(parameter);
		
		promotionGoodsService.batchSavePromotionGoods(promotionContent.getPromotionId(), skuIdList);
	}

	/** 
	 * @see com.bluemobi.service.promotion.PromotionContentService#checkActive(java.util.Map) 
	 */  
	@Override
	public boolean checkActive(Map<String, Object> param) {
		return promotionContentDao.selectForCheckActive(param) == 0;
	}

	/** 
	 * @see com.bluemobi.service.promotion.PromotionContentService#changeStatus(java.lang.Integer) 
	 */  
	@Override
	public void changeStatus(Integer promotionId) {
		promotionContentDao.updateStatus(promotionId);
	}

}
