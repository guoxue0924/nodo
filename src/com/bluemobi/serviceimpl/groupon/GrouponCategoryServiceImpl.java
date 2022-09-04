package com.bluemobi.serviceimpl.groupon;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.groupon.GrouponCategoryDao;
import com.bluemobi.po.groupon.GrouponCategory;
import com.bluemobi.service.groupon.GrouponCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 【团购标签】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
@Service(value = "grouponCategoryService")
public class GrouponCategoryServiceImpl extends MybatisBaseServiceImpl
        implements GrouponCategoryService {

    @Autowired
    private GrouponCategoryDao grouponCategoryDao;

    @Override
    public MyBatisBaseDao getDao() {
        return grouponCategoryDao;
    }

    @Override
    public ResultTO saveGrouponCategory(GrouponCategory category) {
        //查询是否有同名标签
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("title", category.getTitle());
        List<GrouponCategory> sameNameCategorys = this.selectObjectList(parameter);
        
        int ret = 0;
        // 新增
        if(category.getCategoryId() == null || category.getCategoryId() == 0) {
            if(sameNameCategorys == null || sameNameCategorys.isEmpty()) {
                category.setCtime(Calendar.getInstance().getTime());
                ret = this.insert(category);
            } else {
                return ResultTO.newFailResultTO("保存失败,已存在同名标签", null);
            }
        } else {
            //修改
            if(sameNameCategorys.size() == 1) {
                GrouponCategory sameNameCategory = sameNameCategorys.get(0);
                if(sameNameCategory.getCategoryId().intValue() == category.getCategoryId().intValue()) {
                    ret = this.update(category);
                } else {
                    return ResultTO.newFailResultTO("保存失败,已存在同名标签", null);
                }
            } else {
            	ret = this.update(category);
            }
        }
        if(ret == 0) {
            return ResultTO.newFailResultTO("保存失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
        
    }

    @Override
    public ResultTO deleteCategory(Integer categoryId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("parentId", categoryId);
        List<GrouponCategory> childCategorys = this.selectObjectList(parameter);
        int ret = 0;
        if(childCategorys == null || childCategorys.isEmpty()) {
            parameter.clear();
            parameter.put("categoryId", categoryId);
            ret = this.delete(parameter);
        } else {
            return ResultTO.newFailResultTO("删除失败,已有子标签", null);
        }
        if(ret == 0) {
            return ResultTO.newFailResultTO("删除失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
    }

}
