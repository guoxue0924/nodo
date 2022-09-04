/**
 * Project Name:nodo 
 * File Name:BtsOrderRefundController.java 
 * Package Name:com.bluemobi.controller.back.bts 
 * Date:2015年12月28日下午2:18:33 
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
import com.bluemobi.po.bts.BtsOrderRefund;
import com.bluemobi.service.bts.BtsOrderRefundService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.RefundDetailTO;
import com.bluemobi.to.bts.RefundQueryTO;
import com.bluemobi.util.EnumUtil;

/**
 * ClassName: BtsOrderRefundController</br>
 * Date: 2015年12月28日下午2:18:33

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller("backRefundController")
@RequestMapping("back/refund")
public class BtsOrderRefundController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BtsOrderRefundController.class);
	
	@Autowired
    private BtsOrderRefundService btsOrderRefundService;
	
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
	 * 初始化退货单管理页面
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
        model.addAttribute("refundStatus", EnumUtil.parseEnum(OrderConstant.RefundStatus.class));
        LOGGER.info("用户【{}】订单管理页", new Object[] { this.getUserid() });
        return "back/bts/refund.index";
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
    public Page<RefundDetailTO> getPage(RefundQueryTO queryTO) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", queryTO.getKey());
        map.put("status", queryTO.getRefundStatus() == null ? null : queryTO.getRefundStatus().byteValue());
        map.put("startDate", queryTO.getStartDate());
        map.put("endDate", queryTO.getEndDate());
        Page<RefundDetailTO> pages = btsOrderRefundService.page(map,
        		queryTO.getPageIndex(), queryTO.getPageSize());
        return pages;
    }
    
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String getEditPage(Model model, Integer refundId) {
    	model.addAttribute("refundStatus", EnumUtil.parseEnum(OrderConstant.RefundStatus.class));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("refundId", refundId);
        model.addAttribute("refund", btsOrderRefundService.selectObject(map));
    	return "back/bts/refund.edit";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveEdit(BtsOrderRefund refund) {
    	int ret = 0;
    	try {
    		ret = btsOrderRefundService.update(refund);
    	} catch (Exception ex) {
    		return ResultTO.newFailResultTO("系统异常", null);
    	}
    	if(ret == 0) {
    		return ResultTO.newFailResultTO("保存失败", null);
    	} 
        return ResultTO.newSuccessResultTO("", null);
    }
    
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getDetail(Model model, int refundId) {
    	LOGGER.info("用户【{}】退货单【{}】详情页", new Object[] { this.getUserid(), refundId });
    	RefundDetailTO refund = btsOrderRefundService.getDetailInfo(refundId);
    	model.addAttribute("refund", refund);
    	model.addAttribute("status", EnumUtil.parseEnum(OrderConstant.RefundStatus.class).get(refund.getStatus().toString()));
    	// 加载公共数据
        initIndex(model);
    	return "back/bts/refund.detail";
    }
}
