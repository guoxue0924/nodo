package com.bluemobi.controller.back.groupon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.bluemobi.po.groupon.GrouponBulk;
import com.bluemobi.service.groupon.GrouponBulkService;
import com.bluemobi.service.groupon.GrouponCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 团购活动管理模块 控制器
 * 
 * @ClassName GrouponBulkController
 * @author liuyt
 * @date 2015-10-21 上午10:17:58
 * @version 1.0
 */
@Controller
@RequestMapping("back/grouponBulk")
public class GrouponBulkController extends AbstractBackController {

    @Autowired
    private GrouponBulkService grouponBulkService;

    @Autowired
    private GrouponCategoryService grouponCategoryService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CustomDateEditor editor = new CustomDateEditor(df, false);
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
        return "back/groupon/bulk.index";
    }

    /**
     * 分页获取团购活动列表
     * 
     * @author liuyt
     * @date 2015-10-21 上午10:29:16
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @version
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPage(String key, String status, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(key)) {
        	map.put("key", key);
        }
        if(!StringUtils.isEmpty(status)) {
        	map.put("status", status);
        	map.put("currentTime", Calendar.getInstance().getTime());
        }
        Page<Map<String, Object>> pages = grouponBulkService.page(map, pageIndex, pageSize);

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
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
    public String grouponBulkAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("categorys",
                grouponCategoryService.selectObjectList(null));
        return "back/groupon/bulk.edit";
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
    public String grouponBulkEdit(Model model, Integer bulkId,
            HttpServletRequest req) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("bulk", grouponBulkService.getGrouponBulkDetail(bulkId));
        model.addAttribute("categorys",
                grouponCategoryService.selectObjectList(null));
        return "back/groupon/bulk.edit";

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
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveGrouponBulk(GrouponBulk bulk, Integer[] categoryIds) {
        return grouponBulkService.saveGrouponBulk(bulk, categoryIds);
    }

    /**
     * 删除活动(只能删除未开始的活动)
     * 
     * @author liuyt
     * @date 2015-10-22 下午4:00:12
     * @param bulkId
     * @return
     * @version
     */
    @RequestMapping( value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delGrouponBulk(Integer bulkId) {
        return grouponBulkService.deleteBulk(bulkId);
    }

}
