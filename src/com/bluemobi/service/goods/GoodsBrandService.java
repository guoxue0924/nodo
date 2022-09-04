package com.bluemobi.service.goods;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.goods.GoodsBrand;

/**
 * 【商品品牌表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:56
 * 
 */
public interface GoodsBrandService extends MybatisBaseService {

    /**
     * 批量删除品牌
     * 
     * @param paramMap
     *            传入的map对象的值为id的String[]数组
     * @return
     */
    int deleteByIds(Map<String, Object> paramMap);

    /**
     * 系统初始化加载，用于前台页面分类子页面右侧的品牌展示
     * 
     * @auther zhangzheng
     * @date 2016-1-22 下午6:29:35
     * @param param
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<GoodsBrand> loadGoodsBrands();

	List<Map<String, Object>> loadGoodsBrandsbyCategory();

}
