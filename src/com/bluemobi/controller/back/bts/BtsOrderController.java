/**
 * Project Name:nodo 
 * File Name:BtsOrderController.java 
 * Package Name:com.bluemobi.controller.back.bts 
 * Date:2015年12月28日上午11:31:02 
 */
package com.bluemobi.controller.back.bts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.constant.OrderConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.OrderDetailTO;
import com.bluemobi.to.bts.OrderQueryTO;
import com.bluemobi.util.EnumUtil;

/**
 * ClassName: BtsOrderController
 * Date: 2015年12月28日上午11:31:02

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller("backOrderController")
@RequestMapping("back/order")
public class BtsOrderController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BtsOrderController.class);
	
	@Autowired
    private BtsOrderService btsOrderService;
	
	@InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
	
	/**
	 * 
	 * index 
	 * 初始化订单管理页面
	 * 
	 * @author kevin
	 * @param model
	 * @return 
	 * @since JDK 7
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
		// 加载公共数据
        initIndex(model);
        model.addAttribute("orderStatus", EnumUtil.parseEnum(OrderConstant.OrderStatus.class));
        model.addAttribute("paymentStatus", EnumUtil.parseEnum(OrderConstant.PaymentStatus.class));
        model.addAttribute("orderType",EnumUtil.parseEnum(OrderConstant.OrderType.class));
        model.addAttribute("payType",EnumUtil.parseEnum(OrderConstant.PayType.class));
        LOGGER.info("用户【{}】订单管理页", new Object[] { this.getUserid() });
        return "back/bts/order.index";
	}
	
	/**
	 * 初始化用户订单列表详情
	 * @author HeWW
	 * 2016-2-22
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "indexOrderDetail", method = RequestMethod.GET)
    public String indexByuserId(Model model,Integer userid) {
	    model.addAttribute("userid", userid);
        return "back/cas/casUser.detail.order";
    }
	
	/**
	 * 
	 * getPage 分页获取订单列表
	 * 
	 * @author kevin
	 * @param queryTO  订单查询对象
	 * @return 
	 * @since JDK 7
	 */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> getPage(OrderQueryTO queryTO) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", queryTO.getKey());
        map.put("status", queryTO.getOrderStatus() == null ? null : queryTO.getOrderStatus().byteValue());
        map.put("payStatus", queryTO.getPaymentStatus() == null ? null : queryTO.getPaymentStatus().byteValue());
        map.put("startDate", queryTO.getStartDate());
        map.put("endDate", queryTO.getEndDate());
        map.put("orderType", queryTO.getOrderType());
        Page<Map<String, Object>> pages = btsOrderService.page(map,
        		queryTO.getPageIndex(), queryTO.getPageSize());
        return pages;
    }
    
    /**
     * 根据用户ID查询所有订单信息
     * @author HeWW
     * 2016-2-18
     * @param queryTO
     * @param userid
     * @return
     */
    @RequestMapping(value = "pageByuserId", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> getByuserId(OrderQueryTO queryTO,Integer userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", queryTO.getKey());
        map.put("status", queryTO.getOrderStatus() == null ? null : queryTO.getOrderStatus().byteValue());
        map.put("payStatus", queryTO.getPaymentStatus() == null ? null : queryTO.getPaymentStatus().byteValue());
        map.put("startDate", queryTO.getStartDate());
        map.put("endDate", queryTO.getEndDate());
        map.put("orderType", queryTO.getOrderType());
        map.put("userid", userid);
        Page<Map<String, Object>> pages = btsOrderService.page(map,
                queryTO.getPageIndex(), queryTO.getPageSize());
        return pages;
    }
    
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getDetail(Model model, Long orderId) {
    	LOGGER.info("用户【{}】订单【{}】详情页", new Object[] { this.getUserid(), orderId });
    	OrderDetailTO order = btsOrderService.getDetailInfo(orderId);
    	model.addAttribute("order", order);
    	model.addAttribute("payWay", EnumUtil.parseEnum(OrderConstant.PayWay.class).get(order.getPaymentId().toString()));
    	model.addAttribute("payType", EnumUtil.parseEnum(OrderConstant.PayType.class).get(order.getPayType().toString()));
    	model.addAttribute("payStatus", EnumUtil.parseEnum(OrderConstant.PaymentStatus.class).get(order.getPayStatus().toString()));
    	model.addAttribute("orderStatus", EnumUtil.parseEnum(OrderConstant.OrderStatus.class).get(order.getStatus().toString()));
    	// 加载公共数据
        initIndex(model);
    	return "back/bts/order.detail";
    }
    
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String getEditPage(Model model, Long orderId) {
    	model.addAttribute("orderStatus", EnumUtil.parseEnum(OrderConstant.OrderStatus.class));
        model.addAttribute("paymentStatus", EnumUtil.parseEnum(OrderConstant.PaymentStatus.class));
        model.addAttribute("logistics", EnumUtil.parseEnum(OrderConstant.Logistics.class));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", orderId);
        model.addAttribute("order", btsOrderService.selectObject(map));
    	return "back/bts/order.edit";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveEdit(BtsOrder order,HttpServletRequest request) {
    	int ret = 0;
    	try {
    		order.setLogisticsCom(request.getParameter("logistics"));
    		ret = btsOrderService.update(order);
    	} catch (Exception ex) {
    		return ResultTO.newFailResultTO("系统异常", null);
    	}
    	if(ret == 0) {
    		return ResultTO.newFailResultTO("保存失败", null);
    	} 
        return ResultTO.newSuccessResultTO("", null);
    }
	
}
