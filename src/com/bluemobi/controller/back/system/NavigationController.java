package com.bluemobi.controller.back.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.system.SystemNavigation;
import com.bluemobi.service.system.SystemNavigationService;
import com.bluemobi.to.ResultTO;

/**
 * 菜单管理
 * @author heweiwen
 * 2015-11-30 下午4:59:05
 */
@Controller
@RequestMapping("back/navigation")
public class NavigationController extends AbstractBackController{
    
    @Autowired
    private SystemNavigationService systemNavigationService;
    
     /**
      * 初始化左侧菜单
      * @author HeWeiwen
      * 2015-9-9
      * @param model
      * @param req
      * @return
      */
    @RequestMapping(value = "index" , method = RequestMethod.GET)
    public String indexNavigation(Model model,HttpServletRequest req) {
        //1,查询所有菜单数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("link", "");
        map.put("status", 1);
        List<SystemNavigation> navigationList = systemNavigationService.selectObjectList(map);
        //2,加载公共数据
        initIndex(model);
        model.addAttribute("navigations", navigationList);
        return "back/navigation/navigation.index";
    }
    
    @RequestMapping(value = "add" , method = RequestMethod.GET)
    public String indexNavigationSave(Model model,HttpServletRequest req) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("link", "   ");
        List<Map<Object, Object>> navigations = systemNavigationService.selectObjectList(map);
        //加载公共数据
        initIndex(model);
        //加载父级菜单数据
        model.addAttribute("navigations", navigations);
        
        return "back/navigation/navigation.edit";
    }
    
    @RequestMapping(value = "edit" , method = RequestMethod.GET)
    public String indexNavigationEdit(Model model,HttpServletRequest req,int navigationId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("navigationId", navigationId);
        List<SystemNavigation> navigation = systemNavigationService.selectObjectList(reqMap);
        //查询父级菜单数据
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("link", "   ");
        List<Map<Object, Object>> navigations = systemNavigationService.selectObjectList(map);
        //加载公共数据
        initIndex(model);
        //加载菜单对象数据
        model.addAttribute("navigation", navigation.get(0));
        //加载父级菜单数据
        model.addAttribute("navigations", navigations);
        
        return "back/navigation/navigation.edit";
    }
    
    /**
     * 查询所有菜单信息
     * @author HeWeiwen
     * 2015-9-10
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageNavigation(Integer navigationId) {
        List<SystemNavigation> navigationList = null;
        if (null != navigationId && !"".equals(navigationId)) {
            //通过父级Hid模糊查询所有相关菜单对象
            navigationList = systemNavigationService.selectObjectListLikeByHid(navigationId);
        }else {
            //查询所有菜单对象
            navigationList =  systemNavigationService.findAllSystemNavigation();
        }
        //重组菜单数据
        List<SystemNavigation> navigationResultList = new ArrayList<SystemNavigation>();
        for (SystemNavigation data : navigationList) {
            if (data.getLink().equals("")){
                data.setStrPadding("");
            } else if (!data.getLink().equals("")) {
                data.setStrPadding("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            navigationResultList.add(data);
        }
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", navigationResultList);
        return mapResult;
    }
    
    @RequestMapping(value = "getNavigationById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNavigationById(Integer navigationId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", navigationId);
        List<SystemNavigation> navigationList = systemNavigationService.selectObjectList(map);
        
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", navigationList);
        return mapResult;
    }
    
    /**
     * 添加菜单
     * @author HeWeiwen
     * 2015-7-28
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveNavigation(SystemNavigation navigation){
        //添加用户
        systemNavigationService.insertNavigation(navigation);
        return ResultTO.newSuccessResultTO("添加菜单 - 成功", null);
    }
    
    /**
     * 修改菜单内容
     * @author HeWeiwen
     * 2015-9-16
     * @param navigation
     * @return
     */
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateNavigation(SystemNavigation navigation){
        //修改菜单
        systemNavigationService.updateNavigation(navigation);
        return ResultTO.newSuccessResultTO("修改菜单 - 成功", null);
    }
    
    /**
     * 删除菜单 
     * @author HeWeiwen
     * 2015-9-18
     * @param navigationId
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteNavigation(Integer navigationId){
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("navigationId", navigationId);
        SystemNavigation navigation = systemNavigationService.selectObject(reqMap);
        if (null == navigation) {
            return ResultTO.newFailResultTO("该导航不存在，删除失败", null);
        }
        //校验其下已有二级导航
        List<SystemNavigation> parentList = systemNavigationService.findSystemNavigationByParentId(navigation.getNavigationId());
        if (!parentList.isEmpty()) {
            return ResultTO.newFailResultTO("其下已有二级导航，不可删除当前导航", null);
        }
        //删除
        systemNavigationService.delete(reqMap);
        return ResultTO.newSuccessResultTO("删除菜单 - 成功", null);
    }
    
    /**
     * 修改审核
     * @author HeWeiwen
     * 2015-9-18
     * @param navigationId
     * @return
     */
    @RequestMapping(value = "publish",method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publishNavigation(Integer navigationId){
        //1,根据Id查询菜单对象
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("navigationId", navigationId);
        SystemNavigation navigation = systemNavigationService.selectObject(reqMap);
        //1.1,判断状态，并修改状态
        if (null == navigation) {
            return ResultTO.newFailResultTO("该导航不存在，修改审核失败", null);
        }
        if (navigation.getStatus() == 1) {
            navigation.setStatus((byte)0);
        } else {
            navigation.setStatus((byte)1);
        }
        //1.2,修改状态
        systemNavigationService.update(navigation);
        //2,查询是否有父级菜单或者是子级菜单相关的
        Map<String,Object> parentMap = new HashMap<String, Object>();
        parentMap.put("parentId", navigationId);
        List<SystemNavigation> parentList = systemNavigationService.selectObjectList(parentMap);
        
        if (parentList.size() > 0) {
            if (navigation.getStatus() == 0) {
                for (SystemNavigation data:parentList) {
                    data.setStatus(navigation.getStatus());
                    //2.1,修改所有它子级菜单的状态
                    systemNavigationService.update(data);
                }
            }
        }
       SystemNavigation navigationResult = systemNavigationService.selectObject(reqMap);
       return ResultTO.newSuccessResultTO("修改审核 - 成功", navigationResult);
    }
}
