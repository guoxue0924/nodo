package com.bluemobi.serviceimpl.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.goods.GoodsPropertyCategoryDao;
import com.bluemobi.service.goods.GoodsPropertyCategoryService;

/**
 * 【商品属性与分类关系表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
@Service(value = "goodsPropertyCategoryService")
public class GoodsPropertyCategoryServiceImpl extends MybatisBaseServiceImpl implements GoodsPropertyCategoryService {

    @Autowired
    private GoodsPropertyCategoryDao goodsPropertyCategoryDao;

    @Override
    public MyBatisBaseDao getDao() {
        return goodsPropertyCategoryDao;
    }

}
