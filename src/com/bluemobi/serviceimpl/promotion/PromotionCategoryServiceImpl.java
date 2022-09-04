package com.bluemobi.serviceimpl.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.promotion.PromotionCategoryDao;
import com.bluemobi.service.promotion.PromotionCategoryService;

/**
 * 【优惠促销活动分类】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:21
 * 
 */
@Service(value = "promotionCategoryService")
public class PromotionCategoryServiceImpl extends MybatisBaseServiceImpl implements PromotionCategoryService {

    @Autowired
    private PromotionCategoryDao promotionCategoryDao;

    @Override
    public MyBatisBaseDao getDao() {
        return promotionCategoryDao;
    }

}
