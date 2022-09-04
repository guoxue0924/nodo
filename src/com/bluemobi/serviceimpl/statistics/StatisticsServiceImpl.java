/**
 * Project Name:nodo 
 * File Name:StatisticsServiceImpl.java 
 * Package Name:com.bluemobi.serviceimpl.statistics 
 * Date:2016年2月17日下午5:08:18 
 */
package com.bluemobi.serviceimpl.statistics;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.constant.StatisticsConstant;
import com.bluemobi.dao.statistics.StatisticsDao;
import com.bluemobi.service.statistics.StatisticsService;

/**
 * ClassName: StatisticsServiceImpl Date: 2016年2月17日下午5:08:18
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
@Service(value = "statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsDao statisticsDao;

	/**
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticsRegisterUser(java.lang.Integer)
	 */
	@Override
	public int statisticsRegisterUser(Integer timeSpan) {
		Date[] datePair = this.generateDatePairByTimeSpan(timeSpan);
		return statisticsDao.getUserCount(datePair[0], datePair[1]);
	}

	/**
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticsLoginUser(java.lang.Integer)
	 */
	@Override
	public int statisticsLoginUser(Integer timeSpan) {
		Date[] datePair = this.generateDatePairByTimeSpan(timeSpan);
		return statisticsDao.getLoginUserCount(datePair[0], datePair[1]);
	}

	/**
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticOrderCount(java.lang.Integer)
	 */
	@Override
	public int statisticOrderCount(Integer orderStatus) {
		return statisticsDao.getOrderCount(orderStatus);
	}

	/**
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticRefundOrderCount()
	 */
	@Override
	public int statisticRefundOrderCount() {
		return statisticsDao.getRefundOrderCount();
	}

	/**
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticsOnSellGoods(java.lang.Integer)
	 */
	@Override
	public int statisticsOnSellGoods(Integer timeSpan) {
		Date[] datePair = this.generateDatePairByTimeSpan(timeSpan);
		return statisticsDao.getOnSellGoodsCount(datePair[0], datePair[1]);
	}

	/**
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticsOffSellGoods(java.lang.Integer)
	 */
	@Override
	public int statisticsOffSellGoods(Integer timeSpan) {
		Date[] datePair = this.generateDatePairByTimeSpan(timeSpan);
		return statisticsDao.getOffSellGoodsCount(datePair[0], datePair[1]);
	}
	
	/** 
	 * @see com.bluemobi.service.statistics.StatisticsService#statisticsGoods() 
	 */  
	@Override
	public int statisticsGoods() {
		return statisticsDao.getGoodsCount();
	}


	private Date[] generateDatePairByTimeSpan(Integer timeSpan) {
		Date[] datePair = new Date[2];
		if (timeSpan == null) {
			return datePair;
		}
		Date current = Calendar.getInstance().getTime();
		switch (timeSpan.intValue()) {
			case StatisticsConstant.TIME_SPAN_DAY:
				datePair[1] = current;
				datePair[0] = DateUtils.addDays(current, -1);
				break;
			case StatisticsConstant.TIME_SPAN_WEEK:
				datePair[1] = current;
				datePair[0] = DateUtils.addWeeks(current, -1);
				break;
			case StatisticsConstant.TIME_SPAN_MONTH:
				datePair[1] = current;
				datePair[0] = DateUtils.addMonths(current, -1);
				break;
			case StatisticsConstant.TIME_SPAN_TODAY:
				datePair[1] = current;
				datePair[0] = DateUtils.setHours(current, 0);
				datePair[0] = DateUtils.setMinutes(datePair[0], 0);
				datePair[0] = DateUtils.setSeconds(datePair[0], 0);
				break;
			default:
				break;
		}
		return datePair;
	}

}
