package com.bluemobi.serviceimpl.groupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.groupon.GrouponBulkLogDao;
import com.bluemobi.service.groupon.GrouponBulkLogService;

/**
 * 【团购日志表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
@Service(value = "grouponBulkLogService")
public class GrouponBulkLogServiceImpl extends MybatisBaseServiceImpl implements
        GrouponBulkLogService {

    @Autowired
    private GrouponBulkLogDao grouponBulkLogDao;

    @Override
    public MyBatisBaseDao getDao() {
        return grouponBulkLogDao;
    }

}
