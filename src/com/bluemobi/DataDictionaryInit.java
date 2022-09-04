package com.bluemobi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 初始化缓存数据等
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-12-15 下午3:42:16
 * 
 */
@Component(value = "dataDictionaryInit")
public class DataDictionaryInit {
    
    public Map<Long,String> haoCoupon_isVerify = new HashMap<Long,String>();
    
    public void init() {
        initHaoCouponIsVerifyDD();
    }
    
    /**
     * 初始化【优惠券主表】数据字典
     * @author haojian
     * @date 2016-1-7 上午11:25:01 
     * @return void
     */
    public void initHaoCouponIsVerifyDD(){
        haoCoupon_isVerify.put(-1L, "审核拒绝");
        haoCoupon_isVerify.put(0L, "待审核");
        haoCoupon_isVerify.put(1L, "审核通过");
    }

}
