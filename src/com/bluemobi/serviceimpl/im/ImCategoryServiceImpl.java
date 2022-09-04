package com.bluemobi.serviceimpl.im;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.im.ImCategoryDao;
import com.bluemobi.po.im.ImCategory;
import com.bluemobi.service.im.ImCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 【IM消息分类表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 16:28:51
 * 
 */
@Service(value = "imCategoryService")
public class ImCategoryServiceImpl extends MybatisBaseServiceImpl implements ImCategoryService {

    @Autowired
    private ImCategoryDao imCategoryDao;

    @Override
    public MyBatisBaseDao getDao() {
        return imCategoryDao;
    }

	/** 
	 * @see com.bluemobi.service.im.ImCategoryService#saveImCategory(com.bluemobi.po.im.ImCategory) 
	 */  
	@Override
	public ResultTO saveImCategory(ImCategory category) {
		//查询是否有同名标签
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("categoryName", category.getCategoryName());
        List<ImCategory> sameNameCategorys = this.selectObjectList(parameter);
        
        int ret = 0;
        // 新增
        if(category.getCategoryId() == null || category.getCategoryId() == 0) {
            if(sameNameCategorys == null || sameNameCategorys.isEmpty()) {
                category.setCtime(Calendar.getInstance().getTime());
                ret = this.insert(category);
            } else {
                return ResultTO.newFailResultTO("保存失败,已存在同名分类", null);
            }
        } else {
            //修改
            if(sameNameCategorys.size() == 1) {
            	ImCategory sameNameCategory = sameNameCategorys.get(0);
                if(sameNameCategory.getCategoryId().intValue() == category.getCategoryId().intValue()) {
                    ret = this.update(category);
                } else {
                    return ResultTO.newFailResultTO("保存失败,已存在同名分组", null);
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

	/** 
	 * @see com.bluemobi.service.im.ImCategoryService#deleteCategory(java.lang.Integer) 
	 */  
	@Override
	public ResultTO deleteCategory(Integer categoryId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("parentId", categoryId);
        int ret = this.delete(parameter);
        if(ret == 0) {
            return ResultTO.newFailResultTO("删除失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

}
