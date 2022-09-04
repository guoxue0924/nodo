package com.bluemobi.service.cas;

import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【用户收藏】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
public interface CasUserFavoriteService extends MybatisBaseService {
    
    /**
    * 批量删除收藏
    * 
    * @param paramMap
    *            传入的map对象的值为id的String[]数组
    * @return
    */
   int deleteByIds(Map<String, Object> paramMap);
   
   /**
    * 根据skuId统计收藏数量
    */
   int countsByskuId(long skuId);

}
