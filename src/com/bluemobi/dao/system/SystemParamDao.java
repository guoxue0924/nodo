package com.bluemobi.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 系统参数数据接口
 * ClassName: SystemParamDao
 * Date: 2016年2月18日下午4:44:03

 * @author kevin
 * @version 
 * @since JDK 7
 */
public interface SystemParamDao {

	/** 
	 * 保存系统参数
	 * 
	 * @author kevin
	 * @param name
	 * @param value 
	 * @since JDK 7 
	 */  
	void saveParam(@Param("name") String paramName, @Param("value") String paramValue);

	/** 
	 * 获取所有系统参数
	 * 
	 * @author kevin
	 * @return 
	 * @since JDK 7 
	 */  
	List<Map<String, Object>> getAllSystemParam();    
    
    
}


