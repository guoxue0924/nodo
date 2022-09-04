package com.bluemobi.dao.coupon;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.to.coupon.CustomerCouponTO;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:20
 * 
 */
public interface CouponListingDao extends MyBatisBaseDao {

    List<CustomerCouponTO> customerPage(Map<String, Object> parameter);

    int customerPageCount(Map<String, Object> parameter);
    
    List<Map<String, Object>> frontPage(Map<String, Object> parameter);
    
    int frontPageCount(Map<String, Object> parameter);

}
