package com.bluemobi.dao.cas;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【用户收藏】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
public interface CasUserFavoriteDao extends MyBatisBaseDao {

    /**
     * 批量删除用户收藏
     * 
     * @param paramString
     *            调用的sql名
     * @param paramObject
     *            要删除的id数组对象
     * @return
     */
    int deleteMultiple(Map<String, Object> parameter);
    
    /**
     * 根据skuId统计收藏数量
     */
    int countsByskuId(Map<String, Object> parameter);
}
