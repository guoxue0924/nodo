package com.bluemobi.dao.page;

import org.apache.ibatis.annotations.Param;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 09:33:56
 * 
 */
public interface PageGroupDao extends MyBatisBaseDao {
	
	int countPageByGroupId(@Param("groupId") int groupId);

}
