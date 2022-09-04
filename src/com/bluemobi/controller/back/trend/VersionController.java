package com.bluemobi.controller.back.trend;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.bluemobi.po.trend.TrendVersion;
import com.bluemobi.service.trend.TrendVersionService;
import com.bluemobi.to.ResultTO;

/**
 * 系统设置控制器逻辑处理
 * @author heweiwen
 * 2015-5-12 上午11:45:46
 */
@Controller
@RequestMapping("back/version")
public class VersionController extends AbstractBackController{
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);
    
    @Autowired
    private TrendVersionService trendVersionService;
    
    /**
     * 初始化版本管理页面
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexVersion(Model model,HttpServletRequest req) {
        //加载公共数据
        initIndex(model);
        
        return "back/trend/version.index";
    }
    
    /**
     * 初始化版本添加页面
     * @author HeWeiwen
     * 2015-5-18
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String indexVersionAdd(Model model,HttpServletRequest req) {
        //加载公共数据
        initIndex(model);
        
        return "back/trend/version.edit";
    }
    
    /**
     * 初始化版本编辑页面
     * @author HeWeiwen
     * 2015-5-18
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String indexVersionEdit(Model model,HttpServletRequest req,Integer id) {
        TrendVersion version = new TrendVersion();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id", id);
        if (id != null) {
            version = trendVersionService.selectObject(map);
        }
        //加载公共数据
        initIndex(model);
        model.addAttribute("version", version);
        
        return "back/trend/version.edit";
    }
    
    /**
     * 分页查询版本列表信息
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> versionPage(String platform, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (platform != null && !"".equals(platform)) {
            map.put("platform", platform);
        }
        Page<Map<String, Object>> pages = trendVersionService.page(map, pageIndex, pageSize);
        return pages;
    }
    
    /**
     * 添加版本
     * @author HeWeiwen
     * 2015-5-18
     * @param version
     * @param result
     * @return
     */
    @RequestMapping(value = "add" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addVersion(@ModelAttribute TrendVersion version,BindingResult result) {
        LOGGER.info("添加版本");
        //添加版本
        version.setCtime(new Date());
        version.setMtime(new Date());
        //添加版本
        trendVersionService.insert(version);
        
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    /**
     * 修改版本
     * @author HeWeiwen
     * 2015-4-27
     * @param storeContents
     * @param result
     * @return
     */
    @RequestMapping(value = "edit" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editVersion(@ModelAttribute TrendVersion version,BindingResult result) {
        //修改版本
        trendVersionService.updateVersion(version);
        return ResultTO.newSuccessResultTO("修改成功", null);
    }
    
    /**
     * 修改发布状态
     * @author HeWeiwen
     * 2015-5-18
     * @param id
     * @return
     */
    @RequestMapping(value = "publish" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publishVersion(Integer id) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("id", id);
        if (null == trendVersionService.selectObject(map)) {
            return ResultTO.newFailResultTO("修改发布状态失败", null);
        }
        //修改状态
        TrendVersion version = trendVersionService.publishVsersion(id);
        
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("status", version.getStatus());
        return ResultTO.newSuccessResultTO("修改发布状态成功", datas);
    }
    
    /**
     * 删除版本
     * @author HeWeiwen
     * 2015-4-27
     * @param storeContents
     * @param result
     * @return
     */
    @RequestMapping(value = "delete" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteVersion(Integer id) {
        //物理删除
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("id", id);
        trendVersionService.delete(map);
        
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    
}

