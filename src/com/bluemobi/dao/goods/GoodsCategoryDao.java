package com.bluemobi.dao.goods;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【商品分类表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public interface GoodsCategoryDao extends MyBatisBaseDao {

    /**
     * 批量删除商品分类
     * 
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    int deleteByIds(Map<String, Object> map);

    /**
     * 查询所选id是否含有子类
     * 
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    Map<String, Object> selectCountByParentIds(Map<String, Object> map);

}
