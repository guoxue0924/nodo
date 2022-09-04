package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasUserGroupDao;
import com.bluemobi.service.cas.CasUserGroupService;

/**
 * 【】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-11 10:53:21
 *
 */
@Service(value="casUserGroupService")
public class CasUserGroupServiceImpl extends MybatisBaseServiceImpl implements CasUserGroupService{
	
	@Autowired
	private CasUserGroupDao casUserGroupDao;
	
	@Override
	public MyBatisBaseDao getDao() {
		return casUserGroupDao;
	}
	
	

}


