package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasUserPointRuleDao;
import com.bluemobi.service.cas.CasUserPointRuleService;

/**
 * 【用户积分规则表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:48:41
 * 
 */
@Service(value = "casUserPointRuleService")
public class CasUserPointRuleServiceImpl extends MybatisBaseServiceImpl implements CasUserPointRuleService {

    @Autowired
    private CasUserPointRuleDao casUserPointRuleDao;

    @Override
    public MyBatisBaseDao getDao() {
        return casUserPointRuleDao;
    }

}
