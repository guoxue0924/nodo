package com.bluemobi.service.system;

import java.util.Map;

/**
 * 系统参数业务接口
 * ClassName: SystemParamService
 * Date: 2016年2月18日下午4:45:05

 * @author kevin
 * @version 
 * @since JDK 7
 */
public interface SystemParamService {    
    
    void saveSystemParam(String paramName, String paramValue);
    
    Map<String, String> getAllSystemParam();
	
}

