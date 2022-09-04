package com.bluemobi.service.feedback;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.to.ResultTO;

/**
 * 【留言反馈表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 13:29:30
 * 
 */
public interface FeedbackContentService extends MybatisBaseService {

    ResultTO insertFeedbackContent(String body, String userId, String ip);

}
