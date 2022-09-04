package com.bluemobi.controller.back.advert;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.advert.AdvertPage;
import com.bluemobi.service.advert.AdvertPageService;
import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("back/advertPage")
public class AdvertPageController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertPageController.class);

    @Autowired
    private AdvertPageService advertPageService;

    /* 常用字符串 */
    private static final String PAGE_ID = "pageId";
    private static final String KEY = "key";

    /**
     * 初始化广告页
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:50:18
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】初始化广告页", new Object[] { this.getUserid() });
        return "back/advert/page.index";
    }

    /**
     * 分页查询广告页
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:49:57
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, Integer pageSize, Integer pageIndex) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(KEY, key);
        Page<Map<String, Object>> pages = advertPageService.page(param, pageIndex, pageSize);
        LOGGER.info("用户【{}】根据关键字【{}】页码【{}】每页最大数【{}】分页查询广告页", new Object[] { this.getUserid(), key, pageIndex, pageSize });
        return pages;
    }

    /**
     * 初始化广告页新增
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:50:27
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        LOGGER.error("用户【{}】初始化广告页新增", new Object[] { this.getUserid() });
        return "back/advert/page.edit";
    }

    /**
     * 新增广告页
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:50:43
     * @param page
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdvertPage(@ModelAttribute AdvertPage page, BindingResult result) {
        try {
            // 添加广告页
            page.setCtime(new Date());
            advertPageService.insert(page);
            LOGGER.info("用户【{}】新增广告页【{}】成功", new Object[] { this.getUserid(), page.getPageId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增广告页失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 初始化广告页修改
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:50:51
     * @param model
     * @param pageId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer pageId) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        param.put(PAGE_ID, pageId);
        AdvertPage page = advertPageService.selectObject(param);
        // 加载公共数据
        initIndex(model);
        model.addAttribute("page", page);
        LOGGER.info("用户【{}】初始化广告页修改", new Object[] { this.getUserid() });
        return "back/advert/page.edit";
    }

    /**
     * 修改广告页
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:51:02
     * @param page
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdvertPage(@ModelAttribute AdvertPage page, BindingResult result) {
        try {
            // 修改广告页
            advertPageService.update(page);
            LOGGER.info("用户【{}】修改广告页【{}】成功", new Object[] { this.getUserid(), page.getPageId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改广告页【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), page.getPageId(), e });
            return ResultTO.newFailResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 删除广告页
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午4:51:13
     * @param pageId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdvertPage(Integer pageId) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        try {
            param.put(PAGE_ID, pageId);
            // 删除广告页
            advertPageService.delete(param);
            LOGGER.info("用户【{}】删除广告页【{}】成功", new Object[] { this.getUserid(), pageId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除广告页【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), pageId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

}
