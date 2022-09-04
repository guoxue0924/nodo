package com.bluemobi.serviceimpl.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.goods.GoodsBrandCategoryDao;
import com.bluemobi.po.goods.GoodsBrandCategory;
import com.bluemobi.service.goods.GoodsBrandCategoryService;

/**
 * 【品牌与分类关系表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
@Service(value = "goodsBrandCategoryService")
public class GoodsBrandCategoryServiceImpl extends MybatisBaseServiceImpl implements GoodsBrandCategoryService {

    @Autowired
    private GoodsBrandCategoryDao goodsBrandCategoryDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return goodsBrandCategoryDao;
    }

    public List<Map<String,Object>> selectBrandByCategoryId(Map<String,Object> parameter) {
        return goodsBrandCategoryDao.selectBrandByCategoryId(parameter);
    }
    
    @Override
	public List<Map<String, Object>> selectCategoryIdByBrand(Map<String,Object> parameter) {
		// TODO Auto-generated method stub
		return goodsBrandCategoryDao.selectCategoryIdByBrand(parameter);
	}

    @Override
    public void updateBindBrand(String brandIds, int categoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<GoodsBrandCategory> trainRecordList = new ArrayList<GoodsBrandCategory>();
        GoodsBrandCategory goodsBrandCategory = null;
        map.put("categoryId", categoryId);
        // 删除原来的关联数据
        List<GoodsBrandCategory> brandCategoryList = goodsBrandCategoryDao.selectObjectList(map);
        if (brandCategoryList != null && !brandCategoryList.isEmpty()) {
            goodsBrandCategoryDao.deleteByCategoryList(brandCategoryList);
        }
        if (brandIds != null && !"".equals(brandIds)) {
            // 数据分割
            String[] ids = brandIds.split(",");
            // 循环添加
            for (int i = 0; i < ids.length; i++) {
                goodsBrandCategory = new GoodsBrandCategory();
                goodsBrandCategory.setBrandId(Integer.parseInt(ids[i]));
                goodsBrandCategory.setCategoryId(categoryId);
                goodsBrandCategory.setSortOrder(i + 1);
                trainRecordList.add(goodsBrandCategory);
            }
            goodsBrandCategoryDao.insertGoodsBrandCategories(trainRecordList);
        }
    }

	

}
