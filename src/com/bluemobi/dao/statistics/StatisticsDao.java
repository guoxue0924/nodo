/**
 * Project Name:nodo 
 * File Name:StatisticsDao.java 
 * Package Name:com.bluemobi.dao.statistics 
 * Date:2016年2月17日下午5:29:34 
 */
package com.bluemobi.dao.statistics;

import java.util.Date;

import org.apache.ibatis.annotations.Param;


/**
 * ClassName: StatisticsDao
 * Date: 2016年2月17日下午5:29:34

 * @author kevin
 * @version 
 * @since JDK 7
 */
public interface StatisticsDao {
	
	int getUserCount(@Param("startTime") Date registerStartTime, @Param("endTime") Date registeEndTime);
	
	int getLoginUserCount(@Param("startTime") Date loginStartTime, @Param("endTime") Date loginEndTime);
	
	int getOrderCount(@Param("status") Integer orderStatus);
	
	int getRefundOrderCount();
	
	int getOnSellGoodsCount(@Param("startTime") Date onSaleStartTime, @Param("endTime") Date onSaleEndTime);
	
	int getOffSellGoodsCount(@Param("startTime") Date offSaleStartTime, @Param("endTime") Date offSaleEndTime);
	
	int getGoodsCount();
}
