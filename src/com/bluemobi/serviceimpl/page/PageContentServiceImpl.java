package com.bluemobi.serviceimpl.page;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.page.PageContentDao;
import com.bluemobi.po.page.PageContent;
import com.bluemobi.service.page.PageContentService;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 09:33:56
 * 
 */
@Service(value = "pageContentService")
public class PageContentServiceImpl extends MybatisBaseServiceImpl implements PageContentService {

    @Autowired
    private PageContentDao pageContentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return pageContentDao;
    }

	/** 
	 * @see com.bluemobi.service.page.PageContentService#savePageContent(com.bluemobi.po.page.PageContent) 
	 */  
	@Override
	public ResultTO savePageContent(PageContent content) {
		
		//查询是否有同名标签
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("pageUrl", content.getPageUrl());
        List<PageContent> sameUrlContent = this.selectObjectList(parameter);
        parameter.clear();
        parameter.put("title", content.getTitle());
        List<PageContent> sameTitleContent = this.selectObjectList(parameter);
		
        int ret = 0;
        // 新增
        if(content.getId() == null || content.getId() == 0) {
        	boolean isHasSameUrl = !(sameUrlContent == null || sameUrlContent.isEmpty());
        	boolean isHasSameTitle = !(sameTitleContent == null || sameTitleContent.isEmpty());
            if(!isHasSameUrl && !isHasSameTitle) {
            	content.setCtime(Calendar.getInstance().getTime());
                ret = this.insert(content);
            } else {
            	if(isHasSameUrl) {
            		return ResultTO.newFailResultTO("保存失败,已存在相同网页名称", null);
            	}
            	if(isHasSameTitle) {
            		return ResultTO.newFailResultTO("保存失败,已存在相同标题", null);
            	}
            }
        } else {
            //修改
        	if(sameTitleContent.size() == 1) {
        		PageContent sameTitlePageContent = sameTitleContent.get(0);
        		if(sameTitlePageContent.getId().intValue() == content.getId().intValue()) {
        			ret = this.update(content);
        		} else {
        			return ResultTO.newFailResultTO("保存失败,已存在相同标题", null);
        		}
        	} 
        }
		if(ret == 1) {
			return ResultTO.newSuccessResultTO(null);
		} else {
			return ResultTO.newFailResultTO("操作失败", null);
		}
	}

	/** 
	 * @see com.bluemobi.service.page.PageContentService#deletePageContent(java.lang.Integer) 
	 */  
	@Override
	public ResultTO deletePageContent(Integer contentId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", contentId);
		int ret = this.pageContentDao.delete(param);
		if(ret == 1) {
			return ResultTO.newSuccessResultTO(null);
		} else {
			return ResultTO.newFailResultTO("删除失败", null);
		}
	}

}
