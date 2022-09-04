package com.bluemobi.serviceimpl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.dao.system.SystemParamDao;
import com.bluemobi.service.system.SystemParamService;

/**
 * 系统参数业务实现
 * ClassName: SystemParamServiceImpl
 * Date: 2016年2月18日下午4:47:43

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Service(value="systemParamService")
public class SystemParamServiceImpl implements SystemParamService{
    
    @Autowired
    private SystemParamDao systemParamDao;

	/** 
	 * @see com.bluemobi.service.system.SystemParamService#saveSystemParam(java.lang.String, java.lang.String) 
	 */  
	@Override
	public void saveSystemParam(String paramName, String paramValue) {
		this.systemParamDao.saveParam(paramName, paramValue);
		
	}

	/** 
	 * @see com.bluemobi.service.system.SystemParamService#getAllSystemParam() 
	 */  
	@Override
	public Map<String, String> getAllSystemParam() {
		Map<String, String> paramMap = new HashMap<String, String>();
		
		List<Map<String, Object>> result = this.systemParamDao.getAllSystemParam();
		for (Map<String, Object> map : result) {
			paramMap.put((String)map.get("name"), (String)map.get("value"));
		}
		return paramMap;
	}
    
    
 
}


