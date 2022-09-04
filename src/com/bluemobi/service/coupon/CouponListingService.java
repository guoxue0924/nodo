package com.bluemobi.service.coupon;

import com.appcore.page.Page;
import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.coupon.CouponListing;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:20
 * 
 */
public interface CouponListingService extends MybatisBaseService {

    /**
     * 更新指定优惠券子表的状态，顺序依次为锁定，解锁
     * @author liuyt
     * @date 2015-11-4 上午9:54:21
     * @param cpnsId
     * @param status
     * @return
     * @version
     */
    ResultTO updateCouponListsStatus(Integer cpnsId, Integer status);

    /**
     * 用户领取优惠券
     * @author liuyt
     * @date 2015-11-4 下午1:14:04
     * @param couponId
     * @param userid
     * @return
     * @version
     */
    ResultTO createCouponList(Integer couponId, int userid);

    /**
     * 获取指定用户优惠券信息列表，分页展示
     * @author liuyt
     * @date 2015-11-4 下午1:15:15
     * @param userid
     * @param status
     * @param type
     * @param sort
     * @param pageIndex 
     * @param pageSize 
     * @return
     * @version
     */
    Page<CouponListing> getCouponListings(int userId, Integer status, Integer type, Integer sort, Integer pageSize, Integer pageIndex);

}
