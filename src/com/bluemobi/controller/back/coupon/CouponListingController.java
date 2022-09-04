package com.bluemobi.controller.back.coupon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.service.coupon.CouponListingService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.coupon.CouponListQueryTO;
import com.bluemobi.util.BeanUtils;

/**
 * 优惠券列表模块 控制器
 * @ClassName GrouponCategoryController
 * @author liuyt
 * @date 2015-10-23 上午10:14:21
 * @version
 */
@Controller
@RequestMapping("back/couponListing")
public class CouponListingController extends AbstractBackController {

    @Autowired
    private CouponListingService couponListingService;
    
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 初始化列表页
     * 
     * @author liuyt
     * @date 2015-10-21 上午10:28:40
     * @param model
     * @param request
     * @return string
     * @version 1.0
     */
    @RequestMapping(value = "indexUserDetail", method = RequestMethod.GET)
    public String indexCasUserDetail(Model model,Integer userid) {
        model.addAttribute("userid", userid);
        return "back/cas/casUser.detail.coupon";
    }
    
    /**
     * 初始化用户个人优惠券信息
     * 
     * @author liuyt
     * @date 2015-10-21 上午10:28:40
     * @param model
     * @param request
     * @return string
     * @version 1.0
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/coupon/listing.index";
    }

    /**
     * 分页获取分类标签列表
     * 
     * @author liuyt
     * @date 2015-10-21 上午10:29:16
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @version
     * @throws Exception 
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPage(CouponListQueryTO query,
            int pageSize, int pageIndex) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = BeanUtils.beanToMap(query);
        Page<Map<String, Object>> pages = couponListingService.page(map,
                pageIndex, pageSize);

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
    }
    
    /**
     * 根据用户Id查询优惠券信息
     * @author HeWW
     * 2016-2-19
     * @param query
     * @param pageSize
     * @param pageIndex
     * @param userid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "pageCouponByuserid", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPageCouponByuserid(CouponListQueryTO query,int pageSize, int pageIndex,int userid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = BeanUtils.beanToMap(query);
        map.put("userid", userid);
        Page<Map<String, Object>> pages = couponListingService.page(map,
                pageIndex, pageSize);

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
    }
    
    /**
     * 更新优惠券状态
     * @author liuyt
     * @date 2015-11-4 上午9:53:38
     * @param couponId
     * @param status
     * @return
     * @version
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateCouponCategoryStatus(Integer cpnsId, Integer status) {
         return couponListingService.updateCouponListsStatus(cpnsId, status);
    }

}
