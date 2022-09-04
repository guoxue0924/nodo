package com.bluemobi.service.store;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.to.store.StoreAndUserTO;

/**
 * 【已签约商户表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-15 17:24:48
 * 
 */
public interface StoreContentService extends MybatisBaseService {

    /**
     * 查询商铺详情
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:18:41
     * @param parameter
     * @return
     */
    Map<String, Object> selectMapStoreInfo(Map<String, Object> parameter);

    /**
     * 新增商铺
     * 
     * @auther zhangzheng
     * @date 2015-12-1 下午2:00:01
     * @param date
     * @param storeAndUserTO
     * @return
     */
    int insert(Date date, StoreAndUserTO storeAndUserTO) throws IllegalAccessException, InvocationTargetException;

}
