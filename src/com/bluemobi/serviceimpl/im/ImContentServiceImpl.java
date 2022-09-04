package com.bluemobi.serviceimpl.im;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.im.ImContentDao;
import com.bluemobi.service.im.ImContentService;

/**
 * 【ＩＭ消息内容表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 11:01:20
 * 
 */
@Service(value = "imContentService")
public class ImContentServiceImpl extends MybatisBaseServiceImpl implements ImContentService {

    @Autowired
    private ImContentDao imContentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return imContentDao;
    }

	/** 
	 * @see com.bluemobi.service.im.ImContentService#getContacts(int) 
	 */  
	@Override
	public List<Map<String, Object>> getContacts(int categoryId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryId", categoryId);
		return imContentDao.selectContact(param);
	}

}
