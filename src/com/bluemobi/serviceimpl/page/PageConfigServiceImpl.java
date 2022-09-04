package com.bluemobi.serviceimpl.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.page.PageConfigDao;
import com.bluemobi.service.page.PageConfigService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 09:33:55
 * 
 */
@Service(value = "pageConfigService")
public class PageConfigServiceImpl extends MybatisBaseServiceImpl implements PageConfigService {

    @Autowired
    private PageConfigDao pageConfigDao;

    @Override
    public MyBatisBaseDao getDao() {
        return pageConfigDao;
    }

}
