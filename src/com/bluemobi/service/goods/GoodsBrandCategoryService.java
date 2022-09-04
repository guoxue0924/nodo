package com.bluemobi.service.goods;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【品牌与分类关系表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public interface GoodsBrandCategoryService extends MybatisBaseService {

    /**
     * 根据分类id查询品牌信息
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> selectBrandByCategoryId(Map<String, Object> paramMap);

    /**
     * 修改绑定品牌
     * 
     * @param brandIds
     *            品牌id
     * @param categoryId
     *            分类id
     */
    void updateBindBrand(String brandIds, int categoryId);

	List<Map<String, Object>> selectCategoryIdByBrand(Map<String,Object> parameter);

}
