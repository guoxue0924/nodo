package com.bluemobi.serviceimpl.coupon;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.page.Page;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.constant.CouponConstant;
import com.bluemobi.dao.coupon.CouponListingDao;
import com.bluemobi.po.coupon.CouponCategory;
import com.bluemobi.po.coupon.CouponListing;
import com.bluemobi.service.coupon.CouponCategoryService;
import com.bluemobi.service.coupon.CouponListingService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.coupon.CustomerCouponTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:20
 * 
 */
@Service(value = "couponListingService")
public class CouponListingServiceImpl extends MybatisBaseServiceImpl implements CouponListingService {

    @Autowired
    private CouponListingDao couponListingDao;
    @Autowired
    private CouponCategoryService couponCategoryService;

    @Override
    public MyBatisBaseDao getDao() {
        return couponListingDao;
    }

    @Override
    public ResultTO updateCouponListsStatus(Integer cpnsId, Integer status) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("cpnsId", cpnsId);
        CouponListing couponListing = this.selectObject(parameter);
        if(couponListing.getCpnsStatus().intValue() != status) {
            return ResultTO.newFailResultTO("更新失败,状态已改变", null);
        }
        
        switch(couponListing.getCpnsStatus()) {
            case CouponConstant.USED_STATUS : couponListing.setCpnsStatus(CouponConstant.LOCKED_STATUS);break;
            case CouponConstant.LOCKED_STATUS : couponListing.setCpnsStatus(CouponConstant.USED_STATUS);break;
        }
        couponListing.setMtime(Calendar.getInstance().getTime());
        int ret = this.update(couponListing);
        if (ret == 0) {
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO(null);
    }

    @Override
    public synchronized ResultTO createCouponList(Integer couponId, int userid) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("couponId", couponId);
        CouponCategory category = couponCategoryService.selectObject(parameter);
        if(category == null) {
            return ResultTO.newFailResultTO("指定的优惠信息不存在", null);
        }
        
        if(category.getStatus() == CouponConstant.LOCKED_STATUS) {
            return ResultTO.newFailResultTO("指定的优惠券已锁定，暂时不可领取", null);
        }
        
        int currentTotal = this.couponListingDao.pageCount(parameter);
        if(currentTotal == category.getTotal()) {
            return ResultTO.newFailResultTO("指定的优惠券已领完", null);
        }
        
        CouponListing listing = new CouponListing();
        listing.setCouponId(couponId);
        listing.setCpnsStatus(CouponConstant.UNUSED_STATUS);
        listing.setCtime(Calendar.getInstance().getTime());
        listing.setUserid(userid);
        int ret = this.couponListingDao.insert(listing);
        if(ret == 0) {
            return ResultTO.newFailResultTO("领取失败", null);
        }
        return ResultTO.newSuccessResultTO("领取成功", null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Page<CouponListing> getCouponListings(int userId, Integer status,
            Integer type, Integer sort, Integer pageSize, Integer pageIndex) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("type", type);
        parameter.put("userId", userId);
        parameter.put("status", status);
        parameter.put("sort", sort);
        
        int count = this.couponListingDao.customerPageCount(parameter);
        int offset = (pageIndex - 1) * pageSize;
        parameter.put("offset", Integer.valueOf(offset));
        parameter.put("rows", Integer.valueOf(pageSize));
        List<CustomerCouponTO> list = this.couponListingDao.customerPage(parameter);
        return new Page(pageIndex, pageSize, count, list);
    }

}
