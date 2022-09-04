package com.bluemobi.serviceimpl.advert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.advert.AdvertPageDao;
import com.bluemobi.service.advert.AdvertPageService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 11:11:01
 * 
 */
@Service(value = "advertPageService")
public class AdvertPageServiceImpl extends MybatisBaseServiceImpl implements AdvertPageService {

    @Autowired
    private AdvertPageDao advertPageDao;

    @Override
    public MyBatisBaseDao getDao() {
        return advertPageDao;
    }

}
