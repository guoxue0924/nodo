package com.bluemobi.serviceimpl.trend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.trend.TrendPropertyValueDao;
import com.bluemobi.service.trend.TrendPropertyValueService;

/**
 * 【属性资源值表】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 *
 */
@Service(value="trendPropertyValueService")
public class TrendPropertyValueServiceImpl extends MybatisBaseServiceImpl implements TrendPropertyValueService{
    
    @Autowired
    private TrendPropertyValueDao trendPropertyValueDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return trendPropertyValueDao;
    }


}


