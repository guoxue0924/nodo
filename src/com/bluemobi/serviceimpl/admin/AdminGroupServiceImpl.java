package com.bluemobi.serviceimpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminGroupDao;
import com.bluemobi.service.admin.AdminGroupService;

/**
 * 【管理员分组表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:14
 * 
 */
@Service(value = "adminGroupService")
public class AdminGroupServiceImpl extends MybatisBaseServiceImpl implements AdminGroupService {

    @Autowired
    private AdminGroupDao adminGroupDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminGroupDao;
    }

}
