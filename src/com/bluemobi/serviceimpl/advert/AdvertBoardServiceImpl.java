package com.bluemobi.serviceimpl.advert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.advert.AdvertBoardDao;
import com.bluemobi.service.advert.AdvertBoardService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 11:11:00
 * 
 */
@Service(value = "advertBoardService")
public class AdvertBoardServiceImpl extends MybatisBaseServiceImpl implements AdvertBoardService {

    @Autowired
    private AdvertBoardDao advertBoardDao;

    @Override
    public MyBatisBaseDao getDao() {
        return advertBoardDao;
    }

}
