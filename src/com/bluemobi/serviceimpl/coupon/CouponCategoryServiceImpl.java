package com.bluemobi.serviceimpl.coupon;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.page.Page;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.constant.CouponConstant;
import com.bluemobi.dao.coupon.CouponCategoryDao;
import com.bluemobi.po.coupon.CouponCategory;
import com.bluemobi.service.coupon.CouponCategoryService;
import com.bluemobi.service.coupon.CouponRelationService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.coupon.CouponCategoryTO;

/**
 * 【优惠券主表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:19
 * 
 */
@Service(value = "couponCategoryService")
public class CouponCategoryServiceImpl extends MybatisBaseServiceImpl implements
        CouponCategoryService {

    @Autowired
    private CouponCategoryDao couponCategoryDao;
    @Autowired
    private CouponRelationService couponRelationServiced;

    @Override
    public MyBatisBaseDao getDao() {
        return couponCategoryDao;
    }

    @Override
    public ResultTO saveCouponCategory(CouponCategoryTO couponTO) {
        CouponCategory coupon = couponTO.getCoupon();
        
        if(coupon.getValidEtime().before(coupon.getValidStime())) {
        	return ResultTO.newFailResultTO("有效期结束时间不能早于开始时间", null);
        }
        if(coupon.getGrantStime().after(coupon.getValidStime())) {
        	return ResultTO.newFailResultTO("发放开始时间不能晚于有效期开始时间", null);
        }
        if(coupon.getGrantEtime().after(coupon.getValidEtime())) {
        	return ResultTO.newFailResultTO("发放结束时间不能早于有效期结束时间", null);
        }
        if(coupon.getGrantEtime().before(coupon.getGrantStime())) {
        	return ResultTO.newFailResultTO("发放结束时间不能早于发放结束时间", null);
        }
        
        if(coupon.getCouponId() == null || coupon.getCouponId() == 0) {
            coupon.setCtime(Calendar.getInstance().getTime());
            coupon.setDisabled(CouponConstant.ENABLED_STATUS);
            coupon.setIsDel(CouponConstant.UNDELETE_MARK);
            coupon.setStatus(CouponConstant.UNUSED_STATUS);
            coupon.setCouponType(CouponConstant.ALL_SKU_TYPE);
            try {
	            int ret = this.insert(coupon);
	            if (ret == 0) {
	                return ResultTO.newFailResultTO("保存失败", null);
	            }
        	} catch (Exception e) {
        		e.printStackTrace();
        	}

        } else {
            coupon.setMtime(Calendar.getInstance().getTime());
            int ret = this.update(coupon);
            if (ret == 0) {
                return ResultTO.newFailResultTO("保存失败", null);
            }
        }
        return ResultTO.newSuccessResultTO(null);
    }

    @Override
    public ResultTO deleteCouponCategory(Integer couponId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("couponId", couponId);
        CouponCategory coupon = this.selectObject(parameter);
        coupon.setIsDel(coupon.getIsDel() == CouponConstant.HAS_DELETED_MARK ? CouponConstant.UNDELETE_MARK : CouponConstant.HAS_DELETED_MARK);
        coupon.setMtime(Calendar.getInstance().getTime());
        int ret = this.update(coupon);
        if (ret == 0) {
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO(null);
    }

    @Override
    public ResultTO updateCouponCategoryStatus(Integer couponId, Integer status) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("couponId", couponId);
        CouponCategory coupon = this.selectObject(parameter);
        if(coupon.getStatus().intValue() != status) {
            return ResultTO.newFailResultTO("更新失败,状态已改变", null);
        }
        
        switch(coupon.getStatus()) {
            case CouponConstant.UNUSED_STATUS : coupon.setStatus(CouponConstant.USED_STATUS);break;
            case CouponConstant.USED_STATUS : coupon.setStatus(CouponConstant.LOCKED_STATUS);break;
            case CouponConstant.LOCKED_STATUS : coupon.setStatus(CouponConstant.USED_STATUS);break;
        }
        coupon.setMtime(Calendar.getInstance().getTime());
        int ret = this.update(coupon);
        if (ret == 0) {
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO(null);
    }

    @Override
    public Page<CouponCategory> getAvailableCouponCategorys(Integer type, Integer pageSize, Integer pageIndex) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("type", type);
        return this.page(parameter, pageIndex, pageSize);
    }

}
