package com.bluemobi.controller.back.goods;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.bluemobi.constant.GoodsConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.goods.GoodsBrand;
import com.bluemobi.service.goods.GoodsBrandService;
import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("back/goodsBrand")
public class GoodsBrandController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsBrandController.class);

    @Autowired
    private GoodsBrandService goodsBrandService;

    /**
     * 初始化品牌管理列表页
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:27:51
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        initIndex(model);
        LOGGER.info("用户【{}】初始化品牌管理列表页", new Object[] { this.getUserid() });
        return "back/goods/brand.index";
    }

    /**
     * 分页查询品牌管理
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:28:04
     * @param key
     *            搜索关键字
     * @param pageIndex
     *            当前页
     * @param pageSize
     *            每页显示条数
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (key != null && !"".equals(key)) {
            map.put("key", key);
        }
        Page<Map<String, Object>> brandPages = goodsBrandService.page(map, pageIndex, pageSize);
        LOGGER.info("用户【{}】根据关键字【{}】页码【{}】每页最大数【{}】分页查询品牌管理", new Object[] { this.getUserid(), key, pageIndex, pageSize });
        return brandPages;
    }

    /**
     * 初始化品牌新增页面
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:28:21
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化品牌新增页面", new Object[] { this.getUserid() });
        return "back/goods/brand.edit";
    }

    /**
     * 新增品牌
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:28:43
     * @param brandLogoImage
     * @param brand
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addGoodsBrand(@RequestParam(value = "brandLogoImage", required = false) MultipartFile[] brandLogoImage, @ModelAttribute GoodsBrand brand, BindingResult result) {
        Date date = new Date();
        Map<String, Object> uploadResultMap = null;
        try {
            if (brandLogoImage != null && brandLogoImage.length > 0) {
                uploadResultMap = uploadImage(brandLogoImage, GoodsConstant.BRAND_LOGO_IMAGE);
                if (uploadResultMap != null && (Boolean) uploadResultMap.get("flag")) {
                    brand.setBrandLogo(uploadResultMap.get("imageUrl").toString());
                }
            }
            brand.setCtime(date);
            brand.setMtime(date);
            brand.setSortOrder(0);
            brand.setStatus((byte) 1);
            goodsBrandService.insert(brand);
            LOGGER.info("用户【{}】新增品牌【{}】成功", new Object[] { this.getUserid(), brand.getBrandId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增品牌失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newFailResultTO("新增品牌失败", null);
        }
        return ResultTO.newSuccessResultTO("新增品牌成功", null);
    }

    /**
     * 初始化品牌修改页面
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:29:00
     * @param model
     * @param brandId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 加载公共数据
        initIndex(model);
        map.put("brandId", brandId);
        GoodsBrand brand = goodsBrandService.selectObject(map);
        model.addAttribute("brand", brand);
        LOGGER.info("用户【{}】初始化品牌修改页面", new Object[] { this.getUserid() });
        return "back/goods/brand.edit";
    }

    /**
     * 修改品牌
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:29:16
     * @param brandLogoImage
     * @param brand
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editGoodsBrand(@RequestParam(value = "brandLogoImage", required = false) MultipartFile[] brandLogoImage, @ModelAttribute GoodsBrand brand, BindingResult result) {
        Date date = new Date();
        Map<String, Object> uploadResultMap = null;
        try {
            if (brandLogoImage != null && brandLogoImage.length > 0) {
                uploadResultMap = uploadImage(brandLogoImage, GoodsConstant.BRAND_LOGO_IMAGE);
                if (uploadResultMap != null && (Boolean) uploadResultMap.get("flag")) {
                    brand.setBrandLogo(uploadResultMap.get("imageUrl").toString());
                }
            }
            brand.setMtime(date);
            goodsBrandService.update(brand);
            LOGGER.info("用户【{}】修改品牌【{}】成功", new Object[] { this.getUserid(), brand.getBrandId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改品牌【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), brand.getBrandId(), e });
            return ResultTO.newFailResultTO("修改品牌失败", null);
        }
        return ResultTO.newSuccessResultTO("修改品牌成功", null);
    }

    /**
     * 批量删除品牌
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:29:31
     * @param brandIds
     *            批量删除的品牌ID
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteGoodsBrand(String brandIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] ids = brandIds.split(",");
            map.put("brandIds", ids);
            goodsBrandService.deleteByIds(map);
            LOGGER.info("用户【{}】批量删除品牌【{}】成功", new Object[] { this.getUserid(), brandIds });
        } catch (Exception e) {
            LOGGER.error("用户【{}】批量删除品牌【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), brandIds, e });
            return ResultTO.newFailResultTO("删除品牌失败", null);
        }
        return ResultTO.newSuccessResultTO("删除品牌成功", null);
    }

}
