package com.bluemobi.serviceimpl.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.comment.CommentAttachmentDao;
import com.bluemobi.service.comment.CommentAttachmentService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:27
 * 
 */
@Service(value = "commentAttachmentService")
public class CommentAttachmentServiceImpl extends MybatisBaseServiceImpl implements CommentAttachmentService {

    @Autowired
    private CommentAttachmentDao commentAttachmentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return commentAttachmentDao;
    }

	/** 
	 * @see com.bluemobi.service.comment.CommentAttachmentService#saveCommentAttachment(java.lang.Integer, java.lang.Long[], long) 
	 */  
	@Override
	public void saveCommentAttachment(Integer id, Long[] imageIds, long userid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("commentId", id);
		param.put("imageIds", imageIds);
		param.put("userid", userid);
		commentAttachmentDao.batchInsert(param);
	}

}
