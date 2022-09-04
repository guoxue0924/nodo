package com.bluemobi.service.im;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【ＩＭ消息内容表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 11:01:20
 * 
 */
public interface ImContentService extends MybatisBaseService {
	
	List<Map<String, Object>> getContacts(int categoryId);

}
