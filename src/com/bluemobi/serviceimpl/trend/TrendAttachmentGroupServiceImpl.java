package com.bluemobi.serviceimpl.trend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.trend.TrendAttachmentGroupDao;
import com.bluemobi.service.trend.TrendAttachmentGroupService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-02 14:04:38
 * 
 */
@Service(value = "trendAttachmentGroupService")
public class TrendAttachmentGroupServiceImpl extends MybatisBaseServiceImpl implements TrendAttachmentGroupService {

    @Autowired
    private TrendAttachmentGroupDao trendAttachmentGroupDao;

    @Override
    public MyBatisBaseDao getDao() {
        return trendAttachmentGroupDao;
    }

}
