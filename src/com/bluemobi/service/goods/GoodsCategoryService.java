package com.bluemobi.service.goods;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.apito.GoodsCategoryTO;
import com.bluemobi.po.goods.GoodsCategory;

/**
 * 【商品分类表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public interface GoodsCategoryService extends MybatisBaseService {

    /**
     * 初始化商品分类信息
     * 
     * @auther zhangzheng
     * @date 2015-12-21 下午2:00:27
     */
    void initCategories();

    /**
     * 批量删除商品分类
     * 
     * @auther zhangzheng
     * @date 2016-1-8 下午3:18:33
     * @param categoryIds
     * @return 0:删除成功;1:删除失败，抛出异常;2:删除失败，存在子类;
     * @throws Exception
     */
    int deleteByIds(String categoryIds) throws Exception;

    /**
     * 查询所选id是否含有子类
     * 
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    Map<String, Object> selectCountByParentIds(Map<String, Object> paramMap);

    /**
     * 新增商品分类
     * 
     * @param goodsCategory
     */
    void saveGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 修改商品分类
     * 
     * @param goodsCategory
     */
    void updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 修改绑定品牌
     * 
     * @param brandIds
     *            品牌id
     * @param categoryId
     *            分类id
     */
    void updateBindBrand(String brandIds, int categoryId);

    /**
     * 系统初始化加载分类信息，用于前台页面的分类展示
     * 
     * @auther zhangzheng
     * @date 2016-1-22 下午6:23:54
     * @return
     */
    List<GoodsCategory> loadCategories();

    /**
     * 获取全部分类，排列顺序为类似树状的单list列表
     * 
     * @auther zhangzheng
     * @date 2016-1-26 下午5:22:40
     * @return
     */
    List<GoodsCategory> getAllCategories();

    /**
     * 从redis中获取所有分类信息
     * 
     * @auther zhangzheng
     * @date 2016-2-26 上午10:35:04
     * @return
     */
    List<GoodsCategoryTO> selectObjectListByRedis();

}
