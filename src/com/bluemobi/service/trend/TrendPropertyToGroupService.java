package com.bluemobi.service.trend;

import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【商品属性与分组关系表】 服务类 接口
 * 
 * @author zhangzheng
 * @date 2015-10-12
 * 
 */
public interface TrendPropertyToGroupService extends MybatisBaseService {

    /**
     * 查询该分组下绑定属性的数量
     * 
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    int selectPropertyCountByGroupId(Map<String, Object> paramMap);

    /**
     * 根据属性分组ID批量删除
     * 
     * @auther zhangzheng
     * @date 2015-10-12 下午3:17:39
     * @param param
     * @return
     */
    int deleteByPropertyGroupIds(Map<String, Object> param);

}
