package com.bluemobi.controller.back.trend;

import java.util.HashMap;
import java.util.List;
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
import com.bluemobi.po.trend.TrendProperty;
import com.bluemobi.po.trend.TrendPropertyValue;
import com.bluemobi.service.trend.TrendPropertyService;
import com.bluemobi.service.trend.TrendPropertyValueService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.trend.TrendPropertyTO;

/**
 * 商品属性
 * 
 * @author zhangzheng
 * @date 2015-11-30
 * 
 */
@Controller
@RequestMapping("back/trendProperty")
public class TrendPropertyController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrendPropertyController.class);

    @Autowired
    private TrendPropertyService trendPropertyService;
    @Autowired
    private TrendPropertyValueService trendPropertyValueService;

    /**
     * 初始化商品属性管理页面
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:43:06
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化商品属性管理页面", new Object[] { this.getUserid() });
        return "back/trend/property.index";
    }

    /**
     * 分页查询商品属性信息
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:43:17
     * @param key
     *            搜索关键字
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Map<String, Object>> page = null;
        try {
            if (key != null && !"".equals(key)) {
                map.put("key", key);
            }
            page = trendPropertyService.page(map, pageIndex, pageSize);
            LOGGER.info("用户【{}】分页查询商品属性信息成功", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】分页查询商品属性信息失败。Exception:【{}】", new Object[] { this.getUserid(), e });
        }
        return page;
    }

    /**
     * 初始化商品属性新增页面
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:43:33
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化商品属性新增页面", new Object[] { this.getUserid() });
        return "back/trend/property.edit";
    }

    /**
     * 新增商品属性
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:43:53
     * @param uploadImages
     * @param trendPropertyTo
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addTrendProperty(@RequestParam(value = "uploadImages", required = false) MultipartFile[] uploadImages, @ModelAttribute TrendPropertyTO trendPropertyTo,
            BindingResult result) {
        Map<String, Object> uploadResultMap = null;
        try {
            int imageArrayIndex = 0; // 图片数组的下标
            MultipartFile[] image = new MultipartFile[1];
            String[] uploadImageUrl = new String[trendPropertyTo.getpVal().length];
            if (uploadImages != null && uploadImages.length > 0) {
                boolean[] hasImage = trendPropertyTo.getHasImage();
                for (int i = 0; i < hasImage.length; i++) {
                    // 判断是否有上传图片，因属性值图片可为空，所以判断隐藏域中的值，标记为上传则取出图片
                    if (hasImage[i]) {
                        image[0] = uploadImages[imageArrayIndex];
                        // 上传图片
                        uploadResultMap = uploadImage(image, GoodsConstant.GOODS_PROPERTY_IMAGE);
                        imageArrayIndex++;
                        // 如果图片上传成功，将图片链接保存到uploadImageUrl数组中
                        if ((Boolean) uploadResultMap.get("flag")) {
                            uploadImageUrl[i] = uploadResultMap.get("imageUrl").toString();
                        }
                    } else {
                        uploadImageUrl[i] = "";
                    }
                }
                // 将图片链接的数组保存到对象中
                trendPropertyTo.setImageUrl(uploadImageUrl);
            }
            // 添加商品属性管理
            trendPropertyService.saveTrendProperty(trendPropertyTo);
            LOGGER.info("用户【{}】新增商品属性成功", new Object[] { this.getUserid() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增商品属性失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newSuccessResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 初始化商品属性修改页面
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:44:21
     * @param model
     * @param propertyId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, int propertyId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("propertyId", propertyId);
        TrendProperty property = trendPropertyService.selectObject(reqMap);
        List<TrendPropertyValue> propertyvalues = trendPropertyValueService.selectObjectList(reqMap);
        model.addAttribute("property", property);
        model.addAttribute("propertyValues", propertyvalues);
        LOGGER.info("用户【{}】初始化商品属性修改页面", new Object[] { this.getUserid() });
        return "back/trend/property.edit";
    }

    /**
     * 修改商品属性
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:44:48
     * @param uploadImages
     * @param trendPropertyTo
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editTrendProperty(@RequestParam(value = "uploadImages", required = false) MultipartFile[] uploadImages, @ModelAttribute TrendPropertyTO trendPropertyTo,
            BindingResult result) {
        Map<String, Object> uploadResultMap = null;
        try {
            int imageArrayIndex = 0; // 图片数组的下标
            MultipartFile[] image = new MultipartFile[1];
            String[] uploadImageUrl = new String[trendPropertyTo.getpVal().length];
            if (uploadImages != null && uploadImages.length > 0) {
                boolean[] hasImage = trendPropertyTo.getHasImage();
                for (int i = 0; i < hasImage.length; i++) {
                    // 判断是否有上传图片，因属性值图片可为空，所以判断隐藏域中的值，标记为上传则取出图片
                    if (hasImage[i]) {
                        image[0] = uploadImages[imageArrayIndex];
                        // 上传图片
                        uploadResultMap = uploadImage(image, GoodsConstant.GOODS_PROPERTY_IMAGE);
                        imageArrayIndex++;
                        // 如果图片上传成功，将图片链接保存到uploadImageUrl数组中
                        if ((Boolean) uploadResultMap.get("flag")) {
                            uploadImageUrl[i] = uploadResultMap.get("imageUrl").toString();
                        }
                    } else {
                        uploadImageUrl[i] = "";
                    }
                }
                // 将图片链接的数组保存到对象中
                trendPropertyTo.setImageUrl(uploadImageUrl);
            }
            // 修改商品属性管理
            trendPropertyService.updateTrendProperty(trendPropertyTo);
            LOGGER.info("用户【{}】修改商品属性【{}】修改成功", new Object[] { this.getUserid(), trendPropertyTo.getPropertyId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改商品属性【{}】修改失败。Exception:【{}】", new Object[] { this.getUserid(), trendPropertyTo.getPropertyId(), e });
            return ResultTO.newFailResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 删除单个或多个商品属性
     * 
     * @auther zhangzheng
     * @date 2015-11-30 下午3:45:09
     * @param ids
     *            一个或多个商品属性ID，多个ID之间用逗号分隔
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteTrendProperty(String ids) {
        try {
            trendPropertyService.deleteTrendPropertyAndValues(ids);
            LOGGER.info("用户【{}】删除商品属性【{}】删除成功", new Object[] { this.getUserid(), ids });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除商品属性【{}】删除失败。Exception:【{}】", new Object[] { this.getUserid(), ids, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 分类绑定属性页面，修改属性的状态：是否为定价属性
     * 
     * @auther zhangzheng
     * @date 2015-11-11 下午4:56:43
     * @param propertyId
     * @param status
     * @return
     */
    @RequestMapping(value = "changePropertyType", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO changePropertyType(Integer propertyId, Integer status, Integer categoryId) {
        int i = 0;
        try {
            i = trendPropertyService.changePropertyType(propertyId, status, categoryId);
            if (i == 1) {
                return ResultTO.newFailResultTO("修改定价属性失败，定价属性设置个数最大为3。", null);
            }
            LOGGER.info("用户【{}】修改定价属性【{}】修改成功", new Object[] { this.getUserid(), propertyId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改定价属性【{}】修改失败。Exception:【{}】", new Object[] { this.getUserid(), propertyId, e });
            return ResultTO.newFailResultTO("修改定价属性失败", null);
        }
        return ResultTO.newSuccessResultTO("修改定价属性成功", null);
    }

    /**
     * 修改定价属性开启状态
     * 
     * @auther zhangzheng
     * @date 2015-12-25 下午3:42:18
     * @param propertyId
     *            属性ID
     * @param isSpec
     *            是否设为定价属性（设为定价属性后将和价格、库存等相关联）。1：是；0：否；
     * @return
     */
    @RequestMapping(value = "changeSpec", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO changeSpec(Integer propertyId, Integer isSpec) {
        int i = 0;
        try {
            i = trendPropertyService.changeSpec(propertyId, isSpec);
            if (i == 1) {
                return ResultTO.newFailResultTO("修改定价属性状态失败，此属性已被某个分类绑定，且该分类已经拥有超过三个定价属性。", null);
            }
            LOGGER.info("用户【{}】修改定价属性【{}】的开启状态【{}】成功", new Object[] { this.getUserid(), propertyId, isSpec });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改定价属性【{}】的开启状态【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), propertyId, isSpec, e });
            return ResultTO.newFailResultTO("修改定价属性状态失败", null);
        }
        return ResultTO.newSuccessResultTO("修改定价属性状态成功", null);
    }

    /**
     * 修改属性状态
     * 
     * @auther zhangzheng
     * @date 2015-12-25 下午3:44:24
     * @param propertyId
     *            属性ID
     * @param status
     *            状态。1：启用；0：不启用；
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publish(Integer propertyId, Integer status) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        try {
            param.put("propertyId", propertyId);
            if (status == 1) {
                param.put("status", 0);
            } else {
                param.put("status", 1);
            }
            trendPropertyService.update(param);
            LOGGER.info("用户【{}】修改属性【{}】的状态【{}】成功", new Object[] { this.getUserid(), propertyId, status });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改属性【{}】的状态【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), propertyId, status, e });
            return ResultTO.newFailResultTO("修改属性状态失败", null);
        }
        return ResultTO.newSuccessResultTO("修改属性状态成功", null);
    }

    /**
     * 分类绑定属性页面，修改图片属性
     * 
     * @auther zhangzheng
     * @date 2016-2-22 下午4:54:19
     * @param propertyId
     * @param status
     * @return
     */
    @RequestMapping(value = "changePropertyImage", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO changePropertyImage(Integer propertyId, Integer status, Integer categoryId) {
        int i = 0;
        try {
            i = trendPropertyService.changePropertyImage(propertyId, status, categoryId);
            if (i == 1) {
                return ResultTO.newFailResultTO("修改定价属性失败，只允许设置一个图片属性。", null);
            }
            LOGGER.info("用户【{}】修改定价属性【{}】修改成功", new Object[] { this.getUserid(), propertyId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改定价属性【{}】修改失败。Exception:【{}】", new Object[] { this.getUserid(), propertyId, e });
            return ResultTO.newFailResultTO("修改定价属性失败", null);
        }
        return ResultTO.newSuccessResultTO("修改定价属性成功", null);
    }

}
