package com.bluemobi.serviceimpl.article;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.constant.ArticleConstant;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.dao.article.ArticleContentDao;
import com.bluemobi.po.article.ArticleContent;
import com.bluemobi.service.article.ArticleContentService;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-19 15:51:48
 * 
 */
@Service(value = "articleContentService")
public class ArticleContentServiceImpl extends MybatisBaseServiceImpl implements ArticleContentService {

    @Autowired
    private ArticleContentDao articleContentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return articleContentDao;
    }

	/** 
	 * @see com.bluemobi.service.article.ArticleContentService#saveArticleContent(com.bluemobi.po.article.ArticleContent) 
	 */  
	@Override
	public ResultTO saveArticleContent(ArticleContent content) {
        
        int ret = 0;
        if(content.getContentId() == null || content.getContentId() == 0) {
        	// 新增
        	content.setCtime(Calendar.getInstance().getTime());
        	content.setMtime(Calendar.getInstance().getTime());
        	if(content.getStatus().intValue() == ArticleConstant.ARTICLE_STATUS.PULISHED.getCode().intValue()) {
        		content.setPtime(Calendar.getInstance().getTime());
        	}
            ret = this.insert(content);
        } else {
            //修改
        	content.setMtime(Calendar.getInstance().getTime());
            ret = this.update(content);
        }
        if(ret == 0) {
            return ResultTO.newFailResultTO("保存失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

	/** 
	 * @see com.bluemobi.service.article.ArticleContentService#deleteConent(java.util.List, boolean) 
	 */  
	@Override
	public ResultTO deleteConent(List<Integer> contentIdList, boolean isDelete) {
		if(isDelete) {
			articleContentDao.deleteBatch(contentIdList);
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("list", contentIdList);
			param.put("status", ArticleConstant.ARTICLE_STATUS.DELETED.getCode());
			articleContentDao.updateBatch(param);
		}
        return ResultTO.newSuccessResultTO(null);
	}

	/** 
	 * @see com.bluemobi.service.article.ArticleContentService#publishArticleContent(java.lang.Integer, boolean) 
	 */  
	@Override
	public ResultTO publishArticleContent(Integer contentId, boolean isPublished) {
		int ret = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("contentId", contentId);
		if(isPublished) {
			param.put("status", ArticleConstant.ARTICLE_STATUS.WAIT_PULISH.getCode());
		} else {
			param.put("status", ArticleConstant.ARTICLE_STATUS.PULISHED.getCode());
			param.put("ptime", Calendar.getInstance().getTime());
		}
		param.put("mtime", Calendar.getInstance().getTime());
		ret = articleContentDao.update(param);
		if(ret == 0) {
            return ResultTO.newFailResultTO("操作失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

	/** 
	 * @see com.bluemobi.service.article.ArticleContentService#recommenedArticleContent(java.lang.Integer, boolean) 
	 */  
	@Override
	public ResultTO recommenedArticleContent(Integer contentId, boolean isRecommended) {
		int ret = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("contentId", contentId);
		param.put("isRecommend", isRecommended ? BaseConstant.STATUS_ENABLED : BaseConstant.STATUS_DISABELD);
		param.put("mtime", Calendar.getInstance().getTime());
		ret = articleContentDao.update(param);
		if(ret == 0) {
            return ResultTO.newFailResultTO("操作失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

	/** 
	 * @see com.bluemobi.service.article.ArticleContentService#restoreConent(java.lang.Integer) 
	 */  
	@Override
	public ResultTO restoreConent(Integer contentId) {
		int ret = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("contentId", contentId);
		param.put("status", ArticleConstant.ARTICLE_STATUS.WAIT_PULISH.getCode());
		param.put("mtime", Calendar.getInstance().getTime());
		ret = articleContentDao.update(param);
		if(ret == 0) {
            return ResultTO.newFailResultTO("操作失败", null);
        } else {
            return ResultTO.newSuccessResultTO(null);
        }
	}

	@Override
	public List<Map<String, Object>> selectObjectListBycondition() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = articleContentDao.selectObjectListBycondition();
		return list;
	}

}
