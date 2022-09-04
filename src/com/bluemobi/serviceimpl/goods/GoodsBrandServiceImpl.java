package com.bluemobi.serviceimpl.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.goods.GoodsBrandDao;
import com.bluemobi.po.goods.GoodsBrand;
import com.bluemobi.service.goods.GoodsBrandCategoryService;
import com.bluemobi.service.goods.GoodsBrandService;

/**
 * 【商品品牌表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:56
 * 
 */
@Service(value = "goodsBrandService")
public class GoodsBrandServiceImpl extends MybatisBaseServiceImpl implements GoodsBrandService {

    @Autowired
    private GoodsBrandDao goodsBrandDao;
    
    @Autowired
    private GoodsBrandCategoryService goodsBrandCategoryService;

    @Override
    public MyBatisBaseDao getDao() {
        return goodsBrandDao;
    }

    public int deleteByIds(Map<String, Object> parameter) {
        return goodsBrandDao.deleteByIds(parameter);
    }

    @Override
    public List<GoodsBrand> loadGoodsBrands() {
        Map<String, Object> param = new HashMap<String, Object>();
        int pageIndex = 1;
        int pageSize = 10;
        param.put("offset", (pageIndex - 1) * pageSize);
        param.put("rows", pageSize);
        List<GoodsBrand> list = goodsBrandDao.page(param);
        return list;
    }

	@Override
	public List<Map<String, Object>> loadGoodsBrandsbyCategory() {
		// TODO Auto-generated method stub
		 List<Map<String, Object>> brandCategoryList = goodsBrandCategoryService.selectCategoryIdByBrand(null);
		return brandCategoryList;
	}
}
