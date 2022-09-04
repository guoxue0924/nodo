package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasLoginErrorDao;
import com.bluemobi.service.cas.CasLoginErrorService;

/**
 * 【用户登录失败日志（归档）】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:10
 *
 */
@Service(value="casLoginErrorService")
public class CasLoginErrorServiceImpl extends MybatisBaseServiceImpl implements CasLoginErrorService{
    
    @Autowired
    private CasLoginErrorDao casLoginErrorDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return casLoginErrorDao;
    }
    
    

}


