package com.bluemobi.dao.goods;

import java.util.List;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.goods.GoodsPropertyCategory;

/**
 * 【商品属性与分类关系表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public interface GoodsPropertyCategoryDao extends MyBatisBaseDao {

    /**
     * 根据分类ID删除属性与分类关联
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:13:57
     * @param list
     */
    void deleteByCategoryId(List<GoodsPropertyCategory> list);

    /**
     * 批量新增属性与分类关联
     * 
     * @auther zhangzheng
     * @date 2015-10-26 上午11:14:25
     * @param list
     */
    void insertGoodsPropertyCategories(List<GoodsPropertyCategory> list);

}
