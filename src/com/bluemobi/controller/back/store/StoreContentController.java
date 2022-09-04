package com.bluemobi.controller.back.store;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.appcore.util.TimeUtil;
import com.bluemobi.constant.StoreConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.store.StoreContent;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.store.StoreContentService;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.store.StoreAndUserTO;

/**
 * 商户管理
 * 
 * @author zhangzheng
 * 
 */
@Controller
@RequestMapping("back/storeContent")
public class StoreContentController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreContentController.class);

    @Autowired
    private StoreContentService storeContentService;
    @Autowired
    private TrendRegionService trendRegionService;

    /**
     * 初始化商户管理页面
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:13:14
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化商户管理页面", new Object[] { this.getUserid() });
        return "back/store/content.index";
    }

    /**
     * 分页查询商户信息
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:13:20
     * @param isVerify
     * @param status
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(int isVerify, int status, String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (isVerify == 0 || isVerify == 1) {
            map.put("isVerify", isVerify);
        }
        if (status == 0 || status == 1) {
            map.put("status", status);
        }
        if (key != null) {
            map.put("key", key);
        }
        Page<Map<String, Object>> pages = storeContentService.page(map, pageIndex, pageSize);
        LOGGER.info("用户【{}】根据关键字【{}】审核状态【{}】启用状态【{}】页码【{}】每页最大数【{}】分页查询商户信息", new Object[] { this.getUserid(), key, isVerify, status, pageIndex, pageSize });
        return pages;
    }

    /**
     * 初始化商户新增页面
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:13:30
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化商户新增页面", new Object[] { this.getUserid() });
        return "back/store/content.edit";
    }

    /**
     * 新增商户
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:13:40
     * @param businessImageFile
     *            营业执照图片
     * @param logoImageFile
     *            商铺LOGO
     * @param storeAndUserTO
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addStoreContent(@RequestParam(value = "businessImageFile", required = false) MultipartFile[] businessImageFile,
            @RequestParam(value = "logoImageFile", required = false) MultipartFile[] logoImageFile, @ModelAttribute StoreAndUserTO storeAndUserTO, BindingResult result) {
        Date date = new Date();
        Map<String, Object> uploadResultMap = null;
        try {
            if (businessImageFile != null && businessImageFile.length > 0) {
                uploadResultMap = uploadImage(businessImageFile, StoreConstant.STORE_BUSINESSLICENCE_IMAGE);
                if (uploadResultMap != null && (Boolean) uploadResultMap.get("flag")) {
                    storeAndUserTO.setBusinessImage(uploadResultMap.get("imageUrl").toString());
                }
            }
            if (logoImageFile != null && logoImageFile.length > 0) {
                uploadResultMap = uploadImage(logoImageFile, StoreConstant.STORE_LOGO_IMAGE);
                if (uploadResultMap != null && (Boolean) uploadResultMap.get("flag")) {
                    storeAndUserTO.setLogo(uploadResultMap.get("imageUrl").toString());
                }
            }
            Integer storeId = storeContentService.insert(date, storeAndUserTO);
            LOGGER.info("用户【{}】新增商户【{}】成功", new Object[] { this.getUserid(), storeId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增商户失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 初始化商户修改页面
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:14:24
     * @param model
     * @param storeId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, int storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 加载公共数据
        initIndex(model);

        map.put("storeId", storeId);
        Map<String, Object> store = storeContentService.selectMapStoreInfo(map);

        // 获得所在地
        String hid[] = null;
        if (store.get("hid") != null && !"".equals(store.get("hid"))) {
            hid = store.get("hid").toString().split(":");
            model.addAttribute("regionHidArr", hid);
        }
        // 获得退货地址信息
        if (store.get("hidShip") != null && !"".equals(store.get("hidShip"))) {
            hid = store.get("hidShip").toString().split(":");
            model.addAttribute("regionHidShipArr", hid);
        }

        if (store.get("signingTimeStart") != null && !"".equals(store.get("signingTimeStart"))) {
            model.addAttribute("signingTimeStart", TimeUtil.getFormatDate((Date) store.get("signingTimeStart"), "yyyy-MM-dd"));
        }
        if (store.get("signingTimeEnd") != null && !"".equals(store.get("signingTimeEnd"))) {
            model.addAttribute("signingTimeEnd", TimeUtil.getFormatDate((Date) store.get("signingTimeEnd"), "yyyy-MM-dd"));
        }

        model.addAttribute("store", store);

        LOGGER.info("用户【{}】初始化商户【{}】修改页面", new Object[] { this.getUserid(), storeId });
        return "back/store/content.edit";
    }

    /**
     * 修改商户信息
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:14:37
     * @param businessImageFile
     *            营业执照图片
     * @param logoImageFile
     *            商铺LOGO
     * @param storeAndUserTO
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editStoreContent(@RequestParam(value = "businessImageFile", required = false) MultipartFile[] businessImageFile,
            @RequestParam(value = "logoImageFile", required = false) MultipartFile[] logoImageFile, @ModelAttribute StoreAndUserTO storeAndUserTO, BindingResult result) {
        Map<String, Object> uploadResultMap = null;
        StoreContent store = new StoreContent();
        try {
            if (businessImageFile != null && businessImageFile.length > 0) {
                uploadResultMap = uploadImage(businessImageFile, StoreConstant.STORE_BUSINESSLICENCE_IMAGE);
                if (uploadResultMap != null && (Boolean) uploadResultMap.get("flag")) {
                    storeAndUserTO.setBusinessImage(uploadResultMap.get("imageUrl").toString());
                }
            }
            if (logoImageFile != null && logoImageFile.length > 0) {
                uploadResultMap = uploadImage(logoImageFile, StoreConstant.STORE_LOGO_IMAGE);
                if (uploadResultMap != null && (Boolean) uploadResultMap.get("flag")) {
                    storeAndUserTO.setLogo(uploadResultMap.get("imageUrl").toString());
                }
            }
            BeanUtils.copyProperties(store, storeAndUserTO);
            store.setSigningTimeStart(TimeUtil.getDateByFormatDate(storeAndUserTO.getSigningTimeStartStr(), "yyyy-MM-dd"));
            store.setSigningTimeEnd(TimeUtil.getDateByFormatDate(storeAndUserTO.getSigningTimeEndStr(), "yyyy-MM-dd"));
            storeContentService.update(store);
            LOGGER.info("用户【{}】修改商户【{}】信息成功", new Object[] { this.getUserid(), storeAndUserTO.getStoreId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改商户【{}】信息失败。Exception:【{}】", new Object[] { this.getUserid(), storeAndUserTO.getStoreId(), e });
            return ResultTO.newFailResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 查看商户详情页面
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:15:12
     * @param model
     * @param storeId
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, int storeId) {
        // 加载公共数据
        initIndex(model);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        Map<String, Object> store = storeContentService.selectMapStoreInfo(map);
        if (store.get("regionName") == null || "".equals(store.get("regionName").toString().trim())) {
            store.put("regionName", store.get("regionNameLocal"));
        }
        if (store.get("signingTimeStart") != null && !"".equals(store.get("signingTimeStart"))) {
            store.put("signingTimeStart", TimeUtil.getFormatDate((Date) store.get("signingTimeStart"), "yyyy-MM-dd"));
        }
        if (store.get("signingTimeEnd") != null && !"".equals(store.get("signingTimeEnd"))) {
            store.put("signingTimeEnd", TimeUtil.getFormatDate((Date) store.get("signingTimeEnd"), "yyyy-MM-dd"));
        }
        model.addAttribute("store", store);
        LOGGER.error("用户【{}】查看商户【{}】详情页面", new Object[] { this.getUserid(), storeId });
        return "back/store/content.detail";
    }

    /**
     * 校验店铺名称是否被注册
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:15:18
     * @param storeName
     * @return
     */
    @RequestMapping(value = "checkStoreName", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO checkStoreName(String storeName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("storeName", storeName);
        StoreContent store = storeContentService.selectObject(map);
        if (store != null) {
            LOGGER.info("用户【{}】校验店铺名称【{}】是否被注册，校验结果：已注册。", new Object[] { this.getUserid(), storeName });
            return ResultTO.newFailResultTO(null);
        } else {
            LOGGER.info("用户【{}】校验店铺名称【{}】是否被注册，校验结果：未注册。", new Object[] { this.getUserid(), storeName });
            return ResultTO.newSuccessResultTO(null);
        }
    }

    /**
     * 校验公司名称是否被注册
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:15:31
     * @param companyName
     * @return
     */
    @RequestMapping(value = "checkCompanyName", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO checkCompanyName(String companyName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("companyName", companyName);
        StoreContent store = storeContentService.selectObject(map);
        if (store != null) {
            LOGGER.info("用户【{}】校验公司名称【{}】是否被注册，校验结果：已注册。", new Object[] { this.getUserid(), companyName });
            return ResultTO.newFailResultTO(null);
        } else {
            LOGGER.info("用户【{}】校验公司名称【{}】是否被注册，校验结果：未注册。", new Object[] { this.getUserid(), companyName });
            return ResultTO.newSuccessResultTO(null);
        }
    }

    /**
     * 根据父级 ID 获取子级地区(redis)
     * 
     * @auther zhangzheng
     * @date 2015-12-16 下午6:01:18
     * @param companyName
     * @return
     */
    @RequestMapping(value = "getRegionByPid", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getRegionByPid(int pid) {
        List<TrendRegion> regions = null;
        try {
            regions = trendRegionService.getRegionByPid(pid);
        } catch (Exception e) {
            LOGGER.error("根据父级 ID 获取子级地区失败。Exception【{}】", new Object[] { e });
        }
        return ResultTO.newSuccessResultTO(regions);
    }

}
