package com.bluemobi.serviceimpl.bts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.bts.BtsCartMarkDao;
import com.bluemobi.service.bts.BtsCartMarkService;

/**
 * 【购物车】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-30 11:39:12
 * 
 */
@Service(value = "btsCartMarkService")
public class BtsCartMarkServiceImpl extends MybatisBaseServiceImpl implements BtsCartMarkService {

    @Autowired
    private BtsCartMarkDao btsCartMarkDao;

    @Override
    public MyBatisBaseDao getDao() {
        return btsCartMarkDao;
    }

}
