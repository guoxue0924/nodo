package com.bluemobi.dao.goods;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.goods.GoodsBrandCategory;

/**
 * 【品牌与分类关系表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public interface GoodsBrandCategoryDao extends MyBatisBaseDao {

    /**
     * 根据分类id查询品牌信息
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> selectBrandByCategoryId(Map<String, Object> map);
    
    List<Map<String, Object>> selectCategoryIdByBrand(
			Map<String, Object> parameter);

    /**
     * 删除品牌和分类的关联
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:04:48
     * @param list
     */
    void deleteByCategoryList(List<GoodsBrandCategory> list);

    /**
     * 批量新增品牌和分类的关联
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:05:43
     * @param list
     */
    void insertGoodsBrandCategories(List<GoodsBrandCategory> list);

	

}
