package com.bluemobi.dao.store;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【已签约商户表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-15 17:24:48
 * 
 */
public interface StoreContentDao extends MyBatisBaseDao {

    /**
     * 查询商铺详情
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:18:41
     * @param parameter
     * @return
     */
    Map<String, Object> selectMapStoreInfo(Map<String, Object> map);

}
