package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasUserDetailDao;
import com.bluemobi.service.cas.CasUserDetailService;

/**
 * 【用户不常用详细信息表,禁止联合查询】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:11
 *
 */
@Service(value="casUserDetailService")
public class CasUserDetailServiceImpl extends MybatisBaseServiceImpl implements CasUserDetailService{
    
    @Autowired
    private CasUserDetailDao casUserDetailDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return casUserDetailDao;
    }
    
    

}


