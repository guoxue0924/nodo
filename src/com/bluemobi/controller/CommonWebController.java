package com.bluemobi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import com.bluemobi.constant.OrderConstant;
import com.bluemobi.constant.StatisticsConstant;
import com.bluemobi.service.statistics.StatisticsService;
import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("back")
public class CommonWebController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonWebController.class);
	
	@Autowired
	private StatisticsService statisticsService;
	
	/**
	 * 404错误页面
	 * @author kevin
	 * @date 2015年12月14日下午1:40:43
	 * @version
	 * @param model
	 * @return
	 */
	@RequestMapping("404")
    public String pageNotFound(Model model) {
    	initIndex(model);
    	return "404";
    }

	/**
	 * 异常信息处理(目前只针对ajax的调用异常)
	 * @author kevin
	 * @date 2015年12月14日下午1:47:44
	 * @version
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("error")
    @ResponseBody
    public ResultTO parseErrorMessage(HttpServletRequest request, HttpServletResponse response) {
    	Exception ex = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
    	LOGGER.error(ex.getMessage());
		return ResultTO.newFailResultTO(ex.getMessage(), null);
    }
	
	/**
     * 登陆后的主页地址
     * 
     * @author HeWeiwen 2015-6-11
     * @param model
     * @return
     */
    @RequestMapping("")
    public String getContinue(Model model) {
        // 加载公共数据
        initIndex(model);
        /**
         * 会员数据统计
         */
        model.addAttribute("totalUser", statisticsService.statisticsRegisterUser(null));
        model.addAttribute("oneDayRegister", statisticsService.statisticsRegisterUser(StatisticsConstant.TIME_SPAN_DAY));
        model.addAttribute("oneWeekRegister", statisticsService.statisticsRegisterUser(StatisticsConstant.TIME_SPAN_WEEK));
        model.addAttribute("oneMonthRegister", statisticsService.statisticsRegisterUser(StatisticsConstant.TIME_SPAN_MONTH));
        model.addAttribute("todayLogin", statisticsService.statisticsLoginUser(StatisticsConstant.TIME_SPAN_TODAY));
        
        /**
         * 订单数据统计
         */
        model.addAttribute("totalOrder", statisticsService.statisticOrderCount(null));
        model.addAttribute("waitProcessOrder", statisticsService.statisticOrderCount(OrderConstant.OrderStatus.WAIT_PROCESS.getCode()));
        model.addAttribute("waitPayOrder", statisticsService.statisticOrderCount(OrderConstant.OrderStatus.WAIT_PAY.getCode()));
        model.addAttribute("waitDeliveryOrder", statisticsService.statisticOrderCount(OrderConstant.OrderStatus.WAIT_DELIVERY.getCode()));
        model.addAttribute("refundOrder", statisticsService.statisticRefundOrderCount());
        
        /**
         * 商品数据统计
         */
        model.addAttribute("totalGoods", statisticsService.statisticsGoods());
        model.addAttribute("todayOnSellGoods", statisticsService.statisticsOnSellGoods(StatisticsConstant.TIME_SPAN_TODAY));
        model.addAttribute("onSellGoods", statisticsService.statisticsOnSellGoods(null));
        model.addAttribute("offSellGoods", statisticsService.statisticsOffSellGoods(null));
        
        /**
         * 帮助信息
         */
        return "back/index.index";
    }
}
