package com.bluemobi.serviceimpl.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.coupon.CouponRelationDao;
import com.bluemobi.service.coupon.CouponRelationService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:20
 * 
 */
@Service(value = "couponRelationService")
public class CouponRelationServiceImpl extends MybatisBaseServiceImpl implements CouponRelationService {

    @Autowired
    private CouponRelationDao couponRelationDao;

    @Override
    public MyBatisBaseDao getDao() {
        return couponRelationDao;
    }

}
