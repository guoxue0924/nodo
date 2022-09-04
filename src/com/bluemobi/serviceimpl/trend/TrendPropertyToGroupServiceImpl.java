package com.bluemobi.serviceimpl.trend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.trend.TrendPropertyToGroupDao;
import com.bluemobi.service.trend.TrendPropertyToGroupService;

/**
 * 【商品属性与分组关系表】 服务类 实现类
 * 
 * @author zhangzheng
 * @date 2015-10-12
 * 
 */
@Service(value = "trendPropertyToGroupService")
public class TrendPropertyToGroupServiceImpl extends MybatisBaseServiceImpl implements TrendPropertyToGroupService {

    @Autowired
    private TrendPropertyToGroupDao trendPropertyToGroupDao;

    @Override
    public MyBatisBaseDao getDao() {
        return trendPropertyToGroupDao;
    }

    @Override
    public int selectPropertyCountByGroupId(Map<String, Object> parameter) {
        return trendPropertyToGroupDao.selectPropertyCountByGroupId(parameter);
    }

    public int deleteByPropertyGroupIds(Map<String, Object> param) {
        return trendPropertyToGroupDao.deleteByPropertyGroupIds(param);
    }

}
