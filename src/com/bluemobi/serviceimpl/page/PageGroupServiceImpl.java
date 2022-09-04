package com.bluemobi.serviceimpl.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.page.PageGroupDao;
import com.bluemobi.po.page.PageGroup;
import com.bluemobi.service.page.PageGroupService;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 09:33:56
 * 
 */
@Service(value = "pageGroupService")
public class PageGroupServiceImpl extends MybatisBaseServiceImpl implements PageGroupService {

    @Autowired
    private PageGroupDao pageGroupDao;

    @Override
    public MyBatisBaseDao getDao() {
        return pageGroupDao;
    }

	/** 
	 * @see com.bluemobi.service.page.PageGroupService#savePageGroup(com.bluemobi.po.page.PageGroup) 
	 */  

	public ResultTO savePageGroup(PageGroup pageGroup) {

		//查询是否有同名标签
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("groupName", pageGroup.getGroupName());
        List<PageGroup> sameNameGroup = this.selectObjectList(parameter);
        parameter.clear();
        parameter.put("folder", pageGroup.getFolder());
        List<PageGroup> sameFolderGroup = this.selectObjectList(parameter);
        
		
		int ret = 0;
        // 新增
        if(pageGroup.getGroupId() == null || pageGroup.getGroupId() == 0) {
        	boolean isHasSameName = !(sameNameGroup == null || sameNameGroup.isEmpty());
        	boolean isHasSameFolder = !(sameFolderGroup == null || sameFolderGroup.isEmpty());
            if(!isHasSameName && !isHasSameFolder) {
                ret = this.insert(pageGroup);
            } else {
            	if(isHasSameFolder) {
            		return ResultTO.newFailResultTO("保存失败,已存在同名分组", null);
            	}
            	if(isHasSameFolder) {
            		return ResultTO.newFailResultTO("保存失败,已存在同名目录", null);
            	}
            }
        } else {
            //修改
        	boolean hasUpdate = false;
            if(sameNameGroup.size() == 1) {
            	PageGroup sameNamePageGroup = sameNameGroup.get(0);
                if(sameNamePageGroup.getGroupId().intValue() == pageGroup.getGroupId().intValue()) {
                    ret = this.update(pageGroup);
                    hasUpdate = true;
                } else {
                    return ResultTO.newFailResultTO("保存失败,已存在同名分组", null);
                }
            } else {
            	ret = this.update(pageGroup);
            	hasUpdate = true;
            }
            if(!hasUpdate) {
            	if(sameFolderGroup.size() == 1) {
            		PageGroup sameFolderPageGroup = sameFolderGroup.get(0);
            		if(sameFolderPageGroup.getGroupId().intValue() == pageGroup.getGroupId().intValue()) {
            			ret = this.update(pageGroup);
            		} else {
            			return ResultTO.newFailResultTO("保存失败,已存在同目录分组", null);
            		}
            	} else {
            		ret = this.update(pageGroup);
            	}
            }
        }
        if(ret == 0) {
            return ResultTO.newFailResultTO("保存失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

	/** 
	 * @see com.bluemobi.service.page.PageGroupService#deletePageGroup(java.lang.Integer) 
	 */  
	@Override
	public ResultTO deletePageGroup(Integer groupId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("groupId", groupId);
        int count = this.pageGroupDao.countPageByGroupId(groupId);
        int ret = 0;
        if(count == 0) {
            ret = this.delete(parameter);
        } else {
            return ResultTO.newFailResultTO("删除失败,已有单页使用", null);
        }
        if(ret == 0) {
            return ResultTO.newFailResultTO("删除失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

}
