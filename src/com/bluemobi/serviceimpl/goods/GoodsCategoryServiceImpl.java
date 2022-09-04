package com.bluemobi.serviceimpl.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONObject;
import com.appcore.cache.JedisPoolManager;
import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.JsonUtil;
import com.bluemobi.apito.GoodsCategoryTO;
import com.bluemobi.constant.CategoryConstant;
import com.bluemobi.constant.GoodsConstant;
import com.bluemobi.dao.goods.GoodsBrandCategoryDao;
import com.bluemobi.dao.goods.GoodsCategoryDao;
import com.bluemobi.po.goods.GoodsBrandCategory;
import com.bluemobi.po.goods.GoodsCategory;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.util.JedisUtil;

/**
 * 【商品分类表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
@Service(value = "goodsCategoryService")
public class GoodsCategoryServiceImpl extends MybatisBaseServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryDao goodsCategoryDao;
    @Autowired
    private GoodsBrandCategoryDao goodsBrandCategoryDao;
    @Autowired
    private JedisPoolManager jedisPoolManager;

    @Override
    public MyBatisBaseDao getDao() {
        return goodsCategoryDao;
    }

    @SuppressWarnings("unchecked")
    public int deleteByIds(String categoryIds) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Jedis jedis = null;
        List<Integer> categoryIdList = null;
        try {
            jedis = jedisPoolManager.getResource();
            String[] ids = categoryIds.split(",");
            map.put("categoryIds", ids);
            Map<String, Object> countMap = goodsCategoryDao.selectCountByParentIds(map);
            long count = 0;
            if (countMap != null && !countMap.isEmpty()) {
                count = (Long) countMap.get("count");
            }
            if (count < 1) {
                goodsCategoryDao.deleteByIds(map);
                String s = jedis.get("getAllCategories");
                List<Map<String, Object>> list = JsonUtil.getObject(s, List.class);
                for (String sid : ids) {
                    for (int i = 0; i < list.size(); i++) {
                        Map<String, Object> m = list.get(i);
                        if (sid.equals(m.get("categoryId"))) {
                            list.remove(i);
                            break;
                        }
                    }
                }
                jedis.set("getAllCategories", JsonUtil.getJsonString(list));

                List<GoodsCategory> categories = goodsCategoryDao.selectObjectList(null);
                for (int i = 0; i < ids.length; i++) {
                    categoryIdList = new ArrayList<Integer>();
                    categoryIdList = getCategoryIds(categories, categoryIdList, Integer.valueOf(ids[i]));
                    //更新缓存中分类实体
                    JedisUtil.putMainIdAndDetailIds(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_AND_SON_ID, Integer.valueOf(ids[i]), categoryIdList);
                    //更新缓存中分类list
                    jedis.set("categories", JsonUtil.getJsonString(categories));
                    List<GoodsCategory> resultList = getCategoriesList(categories);
                    jedis.set("getAllCategories", JsonUtil.getJsonString(resultList));
                }
            } else {
                return GoodsConstant.CATEGORY_DELETE_FAIL_EXIS;
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return GoodsConstant.CATEGORY_DELETE_SUCCESS;
    }

    public Map<String, Object> selectCountByParentIds(Map<String, Object> parameter) {
        return goodsCategoryDao.selectCountByParentIds(parameter);
    }

    @Override
    public void initCategories() {
        Jedis jedis = null;
        GoodsCategory category = null;
        List<Integer> categoryIds = null;
        Map<String, Object> categoryFullNameMap = new HashMap<String, Object>();
        List<GoodsCategoryTO> categoryAPI = new ArrayList<GoodsCategoryTO>();
        try {
            jedis = jedisPoolManager.getResource();
            List<GoodsCategory> categories = goodsCategoryDao.selectObjectList(null);
            jedis.set("categories", JsonUtil.getJsonString(categories));
            List<GoodsCategory> resultList = getCategoriesList(categories);
            jedis.set("getAllCategories", JsonUtil.getJsonString(resultList));

            List<GoodsCategory> categoriesAPI = new ArrayList<GoodsCategory>();
            for (GoodsCategory c : categories) {
                if (c.getParentId() == 0) {
                    categoriesAPI.add(c);
                }
            }
            categoryAPI = getCategoriesAPI(categoriesAPI);
            jedis.set("categoryAPI", JsonUtil.getJsonString(categoryAPI));

            // 分类ID和其子ID
            for (int i = 0; i < categories.size(); i++) {
                categoryIds = new ArrayList<Integer>();
                category = categories.get(i);
                categoryIds = getCategoryIds(categories, categoryIds, category.getCategoryId());
                JedisUtil.putMainIdAndDetailIds(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_AND_SON_ID, category.getCategoryId(), categoryIds);
            }

            // 根据最后一级分类ID存储一到三级分类结构
            Map<Integer, GoodsCategory> categoryMap = new HashMap<Integer, GoodsCategory>();
            for (GoodsCategory goodsCategory : categories) {
                categoryMap.put(goodsCategory.getCategoryId(), goodsCategory);
            }
            for (int i = 0; i < categories.size(); i++) {
                GoodsCategory goodsCategory = categories.get(i);
                String name = "";
                name = goodsCategory.getCategoryName();
                name = getCategoryName(categoryMap, goodsCategory, name);
                categoryFullNameMap.put(goodsCategory.getCategoryId() + "", name);
            }
            JedisUtil.putObjectsToMap(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_URL, categoryFullNameMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
    }

    /**
     * 拼装完整的分类名称
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午2:30:15
     * @param goodsCategory
     * @param name
     * @return
     */
    private String getCategoryName(Map<Integer, GoodsCategory> categoryMap, GoodsCategory goodsCategory, String name) {
        if (goodsCategory.getParentId() != null && goodsCategory.getParentId() > 0) {
            GoodsCategory parent = categoryMap.get(goodsCategory.getParentId());
            name = parent.getCategoryName() + " > " + name;
            return getCategoryName(categoryMap, parent, name);
        }
        return name;
    }

    /**
     * 根据ID获取ID和子ID
     * 
     * @auther zhangzheng
     * @date 2016-2-22 下午1:53:04
     * @param categoryId
     * @return
     */
    public List<Integer> getCategoryIds(List<GoodsCategory> categories, List<Integer> categoryIds, Integer categoryId) {
        categoryIds.add(categoryId);
        List<GoodsCategory> list = selectObjectListByParentId(categories, categoryId);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                GoodsCategory category = list.get(i);
                getCategoryIds(categories, categoryIds, category.getCategoryId());
            }
        }
        return categoryIds;
    }

    /**
     * 在所有分类中根据父分类id获取子集list
     * 
     * @auther zhangzheng
     * @date 2016-3-9 上午10:15:35
     * @param categories
     *            所有分类对象
     * @param parentId
     *            父级分类ID
     * @return
     */
    private List<GoodsCategory> selectObjectListByParentId(List<GoodsCategory> categories, Integer parentId) {
        List<GoodsCategory> list = new ArrayList<GoodsCategory>();
        if (categories != null && !categories.isEmpty() && parentId != null) {
            for (GoodsCategory category : categories) {
                if (category.getParentId() == parentId) {
                    list.add(category);
                }
            }
        }
        return list;
    }

    /**
     * 组装数据，结构为全部展现的树状list结构
     * 
     * @auther zhangzheng
     * @date 2016-1-26 下午4:50:07
     * @param jedis
     * @param categories
     */
    private List<GoodsCategory> getCategoriesList(List<GoodsCategory> categories) {
        List<GoodsCategory> resultList = new ArrayList<GoodsCategory>();
        Map<Integer, GoodsCategory> map = new HashMap<Integer, GoodsCategory>();
        // map和list中保存的是对象地址，引用的是内存中同一个对象，所以在map中获取的和list中获取的是同一个对象。
        for (GoodsCategory category : categories) {
            map.put(category.getCategoryId(), category);
        }
        GoodsCategory category = null;
        List<GoodsCategory> list = new ArrayList<GoodsCategory>();
        for (int i = 0; i < categories.size(); i++) {
            category = categories.get(i);
            if (category.getParentId() == 0) {
                list.add(category);
                continue;
            }
            // map中引用的和list中获取的是内存中同一个对象，所以从map中获取的分类对象的sublist添加的数据，在list中也存在。
            map.get(category.getParentId()).getSubList().add(category);
        }
        getResultList(list, resultList);
        return resultList;
    }

    /**
     * 组装数据
     * 
     * @auther zhangzheng
     * @date 2016-1-26 下午4:46:21
     * @param list
     * @param resultList
     */
    public static void getResultList(List<GoodsCategory> list, List<GoodsCategory> resultList) {
        for (GoodsCategory category : list) {
            GoodsCategory c = addCategory(category);
            resultList.add(c);
            if (category.getSubList().size() > 0) {
                getResultList(category.getSubList(), resultList);
            }
        }
    }

    /**
     * 组装移动端分类结构
     * 
     * @auther zhangzheng
     * @date 2016-3-14 下午1:43:59
     * @param categories
     * @return
     */
    private List<GoodsCategoryTO> getCategoriesAPI(List<GoodsCategory> categories) {
        List<GoodsCategoryTO> resultList = new ArrayList<GoodsCategoryTO>();
        GoodsCategory category = null;
        GoodsCategoryTO categoryTO = null;
        for (int i = 0; i < categories.size(); i++) {
            category = categories.get(i);
            categoryTO = getCategoryTOObject(category);
            if (!category.getSubList().isEmpty()) {
                List<GoodsCategory> secondList = category.getSubList();
                for (int j = 0; j < secondList.size(); j++) {
                    GoodsCategory second = secondList.get(j);
                    GoodsCategoryTO secondTO = getCategoryTOObject(second);
                    if (!second.getSubList().isEmpty()) {
                        List<GoodsCategory> thirdList = second.getSubList();
                        for (int k = 0; k < thirdList.size(); k++) {
                            GoodsCategory third = thirdList.get(k);
                            GoodsCategoryTO thirdTO = getCategoryTOObject(third);
                            secondTO.getSubList().add(thirdTO);
                        }
                    }
                    categoryTO.getSubList().add(secondTO);
                }
            }
            resultList.add(categoryTO);
        }
        return resultList;
    }

    private GoodsCategoryTO getCategoryTOObject(GoodsCategory category) {
        GoodsCategoryTO categoryTO = new GoodsCategoryTO();
        categoryTO.setCategoryId(category.getCategoryId());
        categoryTO.setCategoryName(category.getCategoryName());
        categoryTO.setImage(category.getImage());
        return categoryTO;
    }

    /**
     * 创建新的category对象并赋值
     * 
     * @auther zhangzheng
     * @date 2016-1-26 下午4:46:21
     * @param list
     * @param resultList
     */
    private static GoodsCategory addCategory(GoodsCategory category) {
        GoodsCategory c = new GoodsCategory();
        c.setCategoryId(category.getCategoryId());
        c.setCategoryName(category.getCategoryName());
        c.setCtime(category.getCtime());
        c.setDescription(category.getDescription());
        c.setGrade(category.getGrade());
        c.setImage(category.getImage());
        c.setMtime(category.getMtime());
        c.setParentId(category.getParentId());
        c.setSortOrder(category.getSortOrder());
        c.setStatus(category.getStatus());
        return c;
    }

    public void saveGoodsCategory(GoodsCategory goodsCategory) {
        Date date = new Date();
        goodsCategory.setCtime(date);
        goodsCategory.setMtime(date);
        goodsCategory.setStatus(1);
        if (goodsCategory.getParentId() != null && goodsCategory.getParentId() > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("categoryId", goodsCategory.getParentId());
            GoodsCategory parent = goodsCategoryDao.selectObject(map);
            goodsCategory.setGrade(parent.getGrade() + 1);
        } else {
            goodsCategory.setGrade(1);
        }
        if (goodsCategory.getSortOrder() == null) {
            goodsCategory.setSortOrder(0);
        }
        goodsCategoryDao.insert(goodsCategory);

        // 更新缓存中id的子集
        Jedis jedis = jedisPoolManager.getResource();
        List<Integer> categoryIds = new ArrayList<Integer>();
        List<GoodsCategory> categories = null;
        try {
            categories = goodsCategoryDao.selectObjectList(null);
            categoryIds = getCategoryIds(categories, categoryIds, goodsCategory.getCategoryId());
            //更新缓存中分类实体
            JedisUtil.putMainIdAndDetailIds(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_AND_SON_ID, goodsCategory.getCategoryId(), categoryIds);
            //更新缓存中分类list
            jedis.set("categories", JsonUtil.getJsonString(categories));
            List<GoodsCategory> resultList = getCategoriesList(categories);
            jedis.set("getAllCategories", JsonUtil.getJsonString(resultList));
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
    }

    public void updateGoodsCategory(GoodsCategory goodsCategory) {
        goodsCategory.setMtime(new Date());
        goodsCategoryDao.update(goodsCategory);

        // 更新缓存中id的子集
        Jedis jedis = jedisPoolManager.getResource();
        List<Integer> categoryIds = new ArrayList<Integer>();
        List<GoodsCategory> categories = null;
        try {
            categories = goodsCategoryDao.selectObjectList(null);
            categoryIds = getCategoryIds(categories, categoryIds, goodsCategory.getCategoryId());
            //更新缓存中分类实体
            JedisUtil.putMainIdAndDetailIds(jedis, CategoryConstant.CACHE_GOODS_CATEGORY_AND_SON_ID, goodsCategory.getCategoryId(), categoryIds);
            //更新缓存中分类list
            jedis.set("categories", JsonUtil.getJsonString(categories));
            List<GoodsCategory> resultList = getCategoriesList(categories);
            jedis.set("getAllCategories", JsonUtil.getJsonString(resultList));
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
    }

    @Override
    public void updateBindBrand(String brandIds, int categoryId) {
        // 删除原来的关联数据
        Map<String, Object> map = new HashMap<String, Object>();
        List<GoodsBrandCategory> list = new ArrayList<GoodsBrandCategory>();
        GoodsBrandCategory goodsBrandCategory = new GoodsBrandCategory();
        List<GoodsBrandCategory> brandCategoryList = goodsBrandCategoryDao.selectObjectList(map);
        goodsBrandCategoryDao.deleteByCategoryList(brandCategoryList);
        // 数据分割
        String[] brands = brandIds.split(",");
        // 循环添加
        goodsBrandCategory.setCategoryId(categoryId);
        goodsBrandCategory.setSortOrder(1);
        for (int i = 0; i < brands.length; i++) {
            goodsBrandCategory.setBrandId(Integer.parseInt(brands[i]));
            list.add(goodsBrandCategory);
        }
        goodsBrandCategoryDao.insertGoodsBrandCategories(list);
    }

    @Override
    public List<GoodsCategory> loadCategories() {
        List<GoodsCategory> categories = null;
        Map<Integer, GoodsCategory> map = new HashMap<Integer, GoodsCategory>();
        List<GoodsCategory> list = new ArrayList<GoodsCategory>();
        categories = goodsCategoryDao.selectObjectList(null);
        for (GoodsCategory category : categories) {
            map.put(category.getCategoryId(), category);
        }
        for (GoodsCategory category : categories) {
            GoodsCategory parent = map.get(category.getParentId());
            if (parent != null) {
                parent.getSubList().add(category);
            } else {
                list.add(category);
            }
        }
        return list;
    }

    @Override
    public List<GoodsCategory> getAllCategories() {
        List<GoodsCategory> categories = goodsCategoryDao.selectObjectList(null);
        List<GoodsCategory> result = getCategoriesList(categories);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GoodsCategoryTO> selectObjectListByRedis() {
        List<JSONObject> categories = null;
        Jedis jedis = null;
        List<GoodsCategoryTO> list = new ArrayList<GoodsCategoryTO>();
        try {
            jedis = jedisPoolManager.getResource();
            String categoriesRedis = jedis.get("categoryAPI");
            if (categoriesRedis != null && !"".equals(categoriesRedis)) {
                categories = JsonUtil.getObject(categoriesRedis, List.class);
            } else {
                List<GoodsCategoryTO> categoryRedis = null;
                List<GoodsCategory> goodsCategories = goodsCategoryDao.selectObjectList(null);
                getCategoriesList(goodsCategories);
                List<GoodsCategory> categoriesAPI = new ArrayList<GoodsCategory>();
                for (GoodsCategory c : goodsCategories) {
                    if (c.getParentId() == 0) {
                        categoriesAPI.add(c);
                    }
                }
                categoryRedis = getCategoriesAPI(categoriesAPI);
                jedis.set("categoryAPI", JsonUtil.getJsonString(categoryRedis));
            }

            GoodsCategoryTO category = null;
            for (JSONObject str : categories) {
                category = JsonUtil.getObject(str.toJSONString(), GoodsCategoryTO.class);
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return list;
    }

}
