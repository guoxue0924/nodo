package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasCodeDao;
import com.bluemobi.service.cas.CasCodeService;

/**
 * 【用于用户各种动作的码表】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:10
 *
 */
@Service(value="casCodeService")
public class CasCodeServiceImpl extends MybatisBaseServiceImpl implements CasCodeService{
    
    @Autowired
    private CasCodeDao casCodeDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return casCodeDao;
    }
    
    

}


