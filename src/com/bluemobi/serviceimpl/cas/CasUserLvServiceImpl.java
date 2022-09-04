package com.bluemobi.serviceimpl.cas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasUserLvDao;
import com.bluemobi.service.cas.CasUserLvService;

/**
 * 【用户等级表】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-11 16:25:18
 *
 */
@Service(value="casUserLvService")
public class CasUserLvServiceImpl extends MybatisBaseServiceImpl implements CasUserLvService{
	
	@Autowired
	private CasUserLvDao casUserLvDao;
	
	@Override
	public MyBatisBaseDao getDao() {
		return casUserLvDao;
	}

    @Override
    public void updateIsDefault() {
        casUserLvDao.updateIsDefault();
    }
	
	

}


