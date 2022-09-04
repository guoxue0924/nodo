package com.bluemobi.controller.front.cas;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.coupon.CouponListingService;



/**
 * 【用户优惠券】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
@Controller
@RequestMapping("front/cas/coupon")
public class FrontCasCouponController extends AbstractAPIController {
    
    @Autowired
    private CouponListingService couponListingService;
    
    /**
     * 初始化优惠券页面
     * index
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "front/cas/coupon.index";
    }
    
    /**
     * 分页获取用户优惠券
     * pageUserCoupon
     * 
     * @author kevin
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> pageUserCoupon(Model model, int pageSize, int pageIndex, Integer status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getCasUser().getUserid());
        if(status != null) {
        	map.put("status", status);
        }
        try {
        	
        	Page<Map<String, Object>> pages = couponListingService.page("frontPage","frontPageCount",map, pageIndex, pageSize);
        	return pages;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
    
}
