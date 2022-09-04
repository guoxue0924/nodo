package com.bluemobi.dao.goods;

import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【商品品牌表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:56
 * 
 */
public interface GoodsBrandDao extends MyBatisBaseDao {

    /**
     * 批量删除品牌
     * 
     * @param paramString
     *            调用的sql名
     * @param paramObject
     *            要删除的id数组对象
     * @return
     */
    int deleteByIds(Map<String, Object> parameter);

}
