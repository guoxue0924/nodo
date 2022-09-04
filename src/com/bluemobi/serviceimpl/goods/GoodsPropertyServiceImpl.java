package com.bluemobi.serviceimpl.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.goods.GoodsPropertyCategoryDao;
import com.bluemobi.dao.goods.GoodsPropertyDao;
import com.bluemobi.po.goods.GoodsProperty;
import com.bluemobi.po.goods.GoodsPropertyCategory;
import com.bluemobi.service.goods.GoodsPropertyService;
import com.bluemobi.to.trend.PropertyAndPropertyValueTO;

/**
 * 【商品属性表，用以记录商品属性值】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-02 10:14:35
 * 
 */
@Service(value = "goodsPropertyService")
public class GoodsPropertyServiceImpl extends MybatisBaseServiceImpl implements GoodsPropertyService {

    @Autowired
    private GoodsPropertyDao goodsPropertyDao;
    @Autowired
    private GoodsPropertyCategoryDao goodsPropertyCategoryDao;

    @Override
    public MyBatisBaseDao getDao() {
        return goodsPropertyDao;
    }

    @Override
    public void updateBindProperty(String propertyIds, Integer categoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        List<GoodsPropertyCategory> goodsPropertyCategoriesList = goodsPropertyCategoryDao.selectObjectList(map);
        if (goodsPropertyCategoriesList != null && !goodsPropertyCategoriesList.isEmpty()) {
            goodsPropertyCategoryDao.deleteByCategoryId(goodsPropertyCategoriesList);
        }

        // 数据分割
        String[] ids = propertyIds.split(",");
        List<GoodsPropertyCategory> propertyCategories = new ArrayList<GoodsPropertyCategory>();
        GoodsPropertyCategory goodsPropertyCategory = null;
        for (int i = 0; i < ids.length; i++) {
            goodsPropertyCategory = new GoodsPropertyCategory();
            String p = ids[i].substring(0, 1);
            if (p.equals("p")) {
                goodsPropertyCategory.setPropertyId(Integer.parseInt(ids[i].substring(1, ids[i].length())));
                goodsPropertyCategory.setPropertyGroupId(0);
                goodsPropertyCategory.setCategoryId(categoryId);
                goodsPropertyCategory.setSortOrder(i + 1);
            } else {
                goodsPropertyCategory.setPropertyId(0);
                goodsPropertyCategory.setPropertyGroupId(Integer.parseInt(ids[i].substring(1, ids[i].length())));
                goodsPropertyCategory.setCategoryId(categoryId);
                goodsPropertyCategory.setSortOrder(i + 1);
            }
            propertyCategories.add(goodsPropertyCategory);
        }
        goodsPropertyCategoryDao.insertGoodsPropertyCategories(propertyCategories);
    }

    @Override
    public void insertGoodsProperties(List<GoodsProperty> list) {
        goodsPropertyDao.insertGoodsProperties(list);
    }

    @Override
    public int deleteByGoodsContentId(Map<String, Object> paramMap) {
        return goodsPropertyDao.deleteByGoodsContentId(paramMap);
    }

    @Override
    public List<PropertyAndPropertyValueTO> selectGoodsAndProperty(Map<String, Object> paramMap) {
        return goodsPropertyDao.selectGoodsAndProperty(paramMap);
    }

    @Override
    public Integer deleteByContentIds(Map<String, Object> paramMap) {
        return goodsPropertyDao.deleteByContentIds(paramMap);
    }

}
