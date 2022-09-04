package com.bluemobi.serviceimpl.feedback;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.constant.FeedbackConstant;
import com.bluemobi.dao.feedback.FeedbackContentDao;
import com.bluemobi.po.feedback.FeedbackContent;
import com.bluemobi.service.feedback.FeedbackContentService;
import com.bluemobi.to.ResultTO;

/**
 * 【留言反馈表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 13:29:30
 * 
 */
@Service(value = "feedbackContentService")
public class FeedbackContentServiceImpl extends MybatisBaseServiceImpl
        implements FeedbackContentService {

    @Autowired
    private FeedbackContentDao feedbackContentDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return feedbackContentDao;
    }

    @Override
    public ResultTO insertFeedbackContent(String body, String userId, String ip) {
        FeedbackContent feedback = new FeedbackContent();
        feedback.setBody(body);
        feedback.setCtime(Calendar.getInstance().getTime());
        feedback.setIp(ip);
        feedback.setStatus(FeedbackConstant.WAIT_PROCESS);
        feedback.setUserid(Integer.parseInt(userId));
        int i = feedbackContentDao.insert(feedback);
        if (i == 0) {
            return ResultTO.newFailResultTO("添加失败", null);
        } 
        return ResultTO.newSuccessResultTO(null);
    }

}
