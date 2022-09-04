package com.bluemobi.service.coupon;

import com.appcore.page.Page;
import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.coupon.CouponCategory;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.coupon.CouponCategoryTO;

/**
 * 【优惠券主表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:19
 * 
 */
public interface CouponCategoryService extends MybatisBaseService {

    /**
     * 保存优惠券主表信息(包括新增和修改)
     * @author liuyt
     * @date 2015-10-29 上午10:13:37
     * @param coupon
     * @return
     * @version
     */
    ResultTO saveCouponCategory(CouponCategoryTO couponTO);

    /**
     * 删除指定的优惠券主表,只做逻辑删除, 修改删除标记
     * @author liuyt
     * @date 2015-10-29 上午10:14:08
     * @param couponId
     * @return
     * @version
     */
    ResultTO deleteCouponCategory(Integer couponId);

    /**
     * 更新指定优惠券主表的状态，顺序依次为启用，锁定，解锁
     * @author liuyt
     * @date 2015-11-3 下午5:39:19
     * @param couponId
     * @param status 
     * @return
     * @version
     */
    ResultTO updateCouponCategoryStatus(Integer couponId, Integer status);

    /**
     * 前端分页展示可领用的优惠券信息
     * @author liuyt
     * @date 2015-11-4 下午4:07:35
     * @param type      优惠券类型 
     * @param pageSize  每页数量
     * @param pageIndex 页码
     * @return
     * @version
     */
    Page<CouponCategory> getAvailableCouponCategorys(Integer type, Integer pageSize, Integer pageIndex);

}
