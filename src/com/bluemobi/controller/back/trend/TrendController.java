package com.bluemobi.controller.back.trend;

import java.lang.reflect.InvocationTargetException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("back/trend")
public class TrendController extends AbstractBackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrendController.class);

    @Autowired
    private TrendRegionService trendRegionService;

    /**
     * 初始化系统管理页面
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);

        return "back/store/index.index";
    }

    /**
     * 初始化地区管理页面
     */
    @RequestMapping(value = "region", method = RequestMethod.GET)
    public String indexRegion(Model model, Integer page) {
        int pageSize = 0;
        int perpage = 80;
        if (page != null) {
            pageSize = page > 0 ? page : 1;
        }

        // 初始化数据查询（默认为0）
        int pid = 0;
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("pid", pid);
        List<TrendRegion> trendRegionList = trendRegionService.selectObjectList(reqMap);

        // 加载公共数据
        initIndex(model);
        int count = 20;
        model.addAttribute("regions", trendRegionList);
        model.addAttribute("count", count);
        model.addAttribute("ordername", "");
        model.addAttribute("orderby", "");
        model.addAttribute("page", pageSize);
        model.addAttribute("perpage", perpage);

        return "back/trend/region.index";
    }

    /**
     * 初始化地区添加页面
     */
    @RequestMapping(value = "region/add", method = RequestMethod.GET)
    public String indexRegionAdd(Model model, Integer pid) {
        TrendRegion trendRegion = new TrendRegion();
        if (pid != null) {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("regionId", pid);
            trendRegion = trendRegionService.selectObject(reqMap);
        }
        // 加载公共数据
        initIndex(model);
        model.addAttribute("trendRegion", trendRegion);
        model.addAttribute("pid", pid);
        return "back/trend/region.edit";
    }

    /**
     * 初始化地区编辑页面
     */
    @RequestMapping(value = "region/update", method = RequestMethod.GET)
    public String indexRegionEdit(Model model, Integer regionId) {
        TrendRegion trendRegion = new TrendRegion();
        if (regionId != null) {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("regionId", regionId);
            trendRegion = trendRegionService.selectObject(reqMap);
        }
        // 加载公共数据
        initIndex(model);
        model.addAttribute("region", trendRegion);
        return "back/trend/region.edit";
    }

    /**
     * 分页查询用户信息
     */
    @RequestMapping(value = "region/getRegionByPid", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO pagefind(int pid) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("pid", pid);
        List<TrendRegion> trendRegion = trendRegionService.selectObjectList(reqMap);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("regions", trendRegion);

        return ResultTO.newSuccessResultTO(map);
    }

    /**
     * 获取地区列表
     * 
     * @author HeWeiwen 2015-5-18
     * @param region_id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "region/getChilds", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getChilds(int regionId) throws IllegalAccessException, InvocationTargetException {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("pid", regionId);
        List<TrendRegion> trendRegionList = trendRegionService.selectObjectList(reqMap);

        if (trendRegionList != null) {
            return ResultTO.newSuccessResultTO(trendRegionList);
        } else {
            return ResultTO.newFailResultTO("获取地区列表失败", null);
        }
    }

    /**
     * 地区添加
     * 
     * @author HeWeiwen 2015-5-18
     * @param region
     * @param result
     * @return
     */
    @RequestMapping(value = "region/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addRegion(@ModelAttribute TrendRegion region, BindingResult result) {
        LOGGER.info("地区添加");
        // 地区添加
        trendRegionService.insertRegion(region);

        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 地区修改
     * 
     * @author HeWeiwen 2015-5-18
     * @param region
     * @param result
     * @return
     */
    @RequestMapping(value = "region/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editRegion(@ModelAttribute TrendRegion region, BindingResult result) {
        // 地区修改
        trendRegionService.updateRegion(region);

        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 单个删除地区
     * 
     * @auther zhangzheng
     * @date 2016-1-6 下午2:39:50
     * @param regionId
     * @param parentId
     * @return
     */
    @RequestMapping(value = "region/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteRegion(Integer regionId, Integer parentId) {
        int i = 0;
        try {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("regionId", regionId);
            i = trendRegionService.delete(regionId, parentId);
            if (i == 2) {
                LOGGER.error("删除失败，地区【{}】下含有子地区！", new Object[] { regionId });
                return ResultTO.newFailResultTO("删除失败，该地区下含有子地区！", null);
            }
            LOGGER.info("删除地区信息成功！", new Object[] {});
        } catch (Exception e) {
            LOGGER.error("删除地区信息失败！Exception:【{}】", new Object[] { e });
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

}
