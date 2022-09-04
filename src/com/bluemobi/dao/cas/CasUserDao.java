package com.bluemobi.dao.cas;

import org.apache.ibatis.annotations.Param;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.cas.CasUser;

/**
 * 【用户表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-08-31 10:23:11
 * 
 */
public interface CasUserDao extends MyBatisBaseDao {

	/**
     * 保存新密码
     * @auther FXZ
     * @param password
     */
	int updateNewPassword(CasUser casUser);

}
