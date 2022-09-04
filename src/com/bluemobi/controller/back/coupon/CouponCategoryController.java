package com.bluemobi.controller.back.coupon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.constant.CouponConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.coupon.CouponCategory;
import com.bluemobi.po.coupon.CouponRelation;
import com.bluemobi.po.goods.GoodsCategory;
import com.bluemobi.service.coupon.CouponCategoryService;
import com.bluemobi.service.coupon.CouponRelationService;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.coupon.CouponCategoryQueryTO;
import com.bluemobi.to.coupon.CouponCategoryTO;
import com.bluemobi.util.BeanUtils;

/**
 * 优惠券管理模块 控制器
 * 
 * @ClassName CouponCategoryController
 * @author liuyt
 * @date 2015-10-27 下午3:44:55
 * @version
 */
@Controller
@RequestMapping("back/couponCategory")
public class CouponCategoryController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponCategoryController.class);

    @Autowired
    private CouponCategoryService couponCategoryService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private CouponRelationService couponRelationService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
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
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/coupon/category.index";
    }

    /**
     * 分页获取优惠券列表
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
    public Map<String, Object> getPage(CouponCategoryQueryTO query,
            int pageSize, int pageIndex) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = BeanUtils.beanToMap(query);
        Page<Map<String, Object>> pages = couponCategoryService.page(map,
                pageIndex, pageSize);
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
    }

    /**
     * 查看优惠券详情
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:09:38
     * @param model
     * @param bulkId
     * @return
     * @version
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getDetail(Model model, String couponId,
            HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("couponId", couponId);
        CouponCategoryTO couponTO = new CouponCategoryTO();
        couponTO.setCoupon((CouponCategory) couponCategoryService
                .selectObject(map));
        couponTO.setRelation((CouponRelation) couponRelationService
                .selectObject(map));
        model.addAttribute("couponTO", couponTO);
        map.clear();
        map.put("categoryId", couponTO.getRelation().getRelationContent());
        model.addAttribute("category", goodsCategoryService.selectObject(map));
        // 加载公共数据
        initIndex(model);
        return "back/coupon/category.detail";
    }

    /**
     * 初始化添加页面
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:34:28
     * @param model
     * @param req
     * @return
     * @version
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String couponCategoryAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        List<GoodsCategory> categories = goodsCategoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "back/coupon/category.edit";
    }

    /**
     * 初始化修改页面
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:35:00
     * @param model
     * @param bulkId
     * @param req
     * @return
     * @version
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String couponCategoryEdit(Model model, String couponId,
            HttpServletRequest req) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("couponId", couponId);
//        CouponCategoryTO couponTO = new CouponCategoryTO();
//        couponTO.setCoupon((CouponCategory) couponCategoryService
//                .selectObject(map));
//        couponTO.setRelation((CouponRelation) couponRelationService
//                .selectObject(map));
        model.addAttribute("coupon", couponCategoryService.selectObject(map));
        return "back/coupon/category.edit";

    }

    /**
     * 保存操作
     * 
     * @author liuyt
     * @date 2015-10-21 上午11:34:28
     * @param model
     * @param req
     * @return
     * @version
     */
    @RequestMapping(value = {"add","edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveCouponCategory(CouponCategory coupon) {
    	CouponCategoryTO couponTO = new CouponCategoryTO();
    	couponTO.setCoupon(coupon);
        return couponCategoryService.saveCouponCategory(couponTO);
    }

    /**
     * 删除活动(只做逻辑删除)
     * 
     * @author liuyt
     * @date 2015-10-22 下午4:00:12
     * @param bulkId
     * @return
     * @version
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delCouponCategory(Integer couponId) {
        return couponCategoryService.deleteCouponCategory(couponId);
    }

    /**
     * 更新优惠券状态
     * 
     * @author liuyt
     * @date 2015-11-3 下午5:38:44
     * @param couponId
     * @return
     * @version
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateCouponCategoryStatus(Integer couponId, Integer status) {
        return couponCategoryService.updateCouponCategoryStatus(couponId,
                status);
    }
    
    /**
     * 上传图片
     * uploadFile
     * 
     * @author kevin
     * @param files
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile imageFile) {
        Map<String, Object> uploadResultMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            uploadResultMap = uploadImage(new MultipartFile[]{imageFile}, CouponConstant.COUPON_IMAGE);
            LOGGER.info("用户【{}】上传图片【{}】成功", new Object[] { this.getUserid(), imageFile.getName() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】上传图片失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            resultMap.put("status", BaseConstant.STATUS_FAILURE);
            return resultMap;
        }
        resultMap.put("status", BaseConstant.STATUS_SUCCESS);
        resultMap.put("url", uploadResultMap.get("imageUrl") == null ? "" : uploadResultMap.get("imageUrl"));
        return resultMap;
    }

}
