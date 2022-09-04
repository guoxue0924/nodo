package com.bluemobi.service.statistics;



/**
 * 统计服务接口
 * ClassName: StatisticsService
 * Date: 2016年2月17日下午3:37:41

 * @author kevin
 * @version 
 * @since JDK 7
 */
public interface StatisticsService {    
    
	/**
	 * 统计指定时间跨度的注册用户量
	 * statisticsRegisterUser
	 * 
	 * @author kevin
	 * @param timeSpan 时间跨度(天,周,月等) 如果为null,代表统计当前所有用户
	 * @return 
	 * @since JDK 7
	 */
	int statisticsRegisterUser(Integer timeSpan);
	
	/**
	 * 统计指定时间跨度的登录用户量
	 * statisticsLoginUser
	 * 
	 * @author kevin
	 * @param timeSpan 时间跨度(天,周,月等) 如果为null,代表统计当前所有用户
	 * @return 
	 * @since JDK 7
	 */
	int statisticsLoginUser(Integer timeSpan);
	
	
	/**
	 * 统计指定状态的订单数量
	 * statisticOrderCount
	 * 
	 * @author kevin
	 * @param orderStatus 如果为null,代表统计当前所有类型订单
	 * @return 
	 * @since JDK 7
	 */
	int statisticOrderCount(Integer orderStatus);
	
	/**
	 * 统计有退货的订单数量
	 * statisticRefundOrderCount
	 * 
	 * @author kevin
	 * @return 
	 * @since JDK 7
	 */
	int statisticRefundOrderCount();
	
	/**
	 * 统计指定时间跨度的上架商品数量
	 * statisticsOnSellGoods
	 * 
	 * @author kevin
	 * @param timeSpan 时间跨度(天,周,月等) 如果为null,代表统计当前所有上架商品
	 * @return 
	 * @since JDK 7
	 */
	int statisticsOnSellGoods(Integer timeSpan);
	
	/**
	 * 统计指定时间跨度的下架商品数量
	 * statisticsOffSellGoods
	 * 
	 * @author kevin
	 * @param timeSpan 时间跨度(天,周,月等) 如果为null,代表统计当前所有上架商品
	 * @return 
	 * @since JDK 7
	 */
	int statisticsOffSellGoods(Integer timeSpan);
	
	/**
	 * 统计所有商品数量
	 * statisticsGoods
	 * 
	 * @author kevin
	 * @return 
	 * @since JDK 7
	 */
	int statisticsGoods();
	
}

