package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasLoginLogDao;
import com.bluemobi.service.cas.CasLoginLogService;

/**
 * 【用户正常登录日志（归档）】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:10
 *
 */
@Service(value="casLoginLogService")
public class CasLoginLogServiceImpl extends MybatisBaseServiceImpl implements CasLoginLogService{
    
    @Autowired
    private CasLoginLogDao casLoginLogDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return casLoginLogDao;
    }
    
    

}


