package com.bluemobi.controller.back.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.bluemobi.po.admin.AdminGroup;
import com.bluemobi.po.admin.AdminGroupPermission;
import com.bluemobi.po.admin.AdminPermission;
import com.bluemobi.po.system.SystemNavigation;
import com.bluemobi.service.admin.AdminGroupPermissionService;
import com.bluemobi.service.admin.AdminGroupService;
import com.bluemobi.service.admin.AdminPermissionService;
import com.bluemobi.service.system.SystemNavigationService;
import com.bluemobi.to.ResultTO;

/**
 * 【应用权限表】控制器
 * @author heweiwen
 * 2015-12-4 下午4:29:24
 */
@Controller
@RequestMapping("back/adminPermission")
public class AdminPermissionController extends AbstractBackController {
    
    @Autowired
    private AdminGroupService adminGroupService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminGroupPermissionService adminGroupPermissionService;
    @Autowired
    private SystemNavigationService systemNavigationService;
    
    /**
     * 为用户组授权
     * @author HeWeiwen
     * 2015-11-19
     * @param model
     * @param req
     * @param groupId
     * @param navigationId
     * @return
     * @throws IllegalAccessException
     */
   @RequestMapping(value = "assignGroup" , method = RequestMethod.GET)
    public String assignGroup(Model model, Integer groupId, Integer navigationId) throws IllegalAccessException {
        List<AdminGroupPermission> groupPermissions = new ArrayList<AdminGroupPermission>();
        Map<String, List<AdminPermission>> pMaps = new LinkedHashMap<String, List<AdminPermission>>();
        // 1，获得左侧菜单父级列表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("link", "");
        map.put("status", 1);
        List<SystemNavigation> navigationParent = systemNavigationService.selectObjectList(map);
        // 3，获得权限组对象信息
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("groupId", groupId);
        AdminGroup adminGroup = adminGroupService.selectObject(reqMap);
        // 4，通过权限组数据获得所有权限对象
        if (adminGroup != null) {
            Map<String, Object> groupMap = new HashMap<String, Object>();
            groupMap.put("groupId", groupId);
            groupPermissions = adminGroupPermissionService.selectObjectList(groupMap);
            int navigationIdNew = 0;
            if (navigationId != null) {
                navigationIdNew = navigationId;
            } else {
                navigationIdNew = navigationParent.get(0).getNavigationId();
            }
            // 5，根据子级ID集合查询相关权限数据
            pMaps = this.getPermissionMaps(navigationIdNew);
        }
        // 6，加载公共数据
        initIndex(model);
        model.addAttribute("navigations", navigationParent);// 父级菜单数据
        model.addAttribute("navigationId", navigationId);// 父级菜单ID
        model.addAttribute("permissions", pMaps);// 用户所有权限数据
        model.addAttribute("groupinfo", adminGroup);// 用户所属权限对象
        model.addAttribute("groupPermissions", groupPermissions);// 用户权限的关系数据

        return "back/admin/permissionassign.group";
    }
   
   /**
    * 单个 用户/用户组权限修改
    * @author HeWeiwen
    * 2015-11-19
    * @param groupId
    * @param assign
    * @param permissionId
    * @param navigationId
    * @return
    */
   @RequestMapping(value = "assignPermission", method = RequestMethod.POST)
   @ResponseBody
   public ResultTO assignPermission(Integer groupId, int assign, Integer permissionId) {
       //1,校验数据完整
       checkCompletion(groupId, permissionId);
       //2,进行权限授权和解除授权操作
       boolean isAssign = assign == 1 ? true : false;
       if (isAssign) {
           //2.1,判断没有该权限记录，创建权限记录
           if (!adminGroupPermissionService.hasPermission(groupId, permissionId)) {
               AdminGroupPermission groupPermission = new AdminGroupPermission();
               groupPermission.setGroupId(groupId);
               groupPermission.setPermissionId(permissionId);
               adminGroupPermissionService.insert(groupPermission);
           }
       } else {
           //2.2,取消授权，删除权限记录
           adminGroupPermissionService.deleteAdminGroupPermission(groupId, permissionId);
       }

       return ResultTO.newSuccessResultTO(" 修改权限成功" , null);
   }

   /**
    * 全选  用户/用户组权限修改
    * @author HeWeiwen
    * 2015-12-1
    * @param groupId
    * @param assign
    * @param permissionIds
    * @return
    */
   @RequestMapping(value = "assignPermissionAll", method = RequestMethod.POST)
   @ResponseBody
   public ResultTO assignPermissionArr(Integer groupId, int assign, String permissionIds) {
       //1,进行权限授权和解除授权操作
       boolean isAssign = assign == 1 ? true : false;
       //2,授权权限操作
       adminGroupPermissionService.assignPermission(groupId, isAssign, permissionIds);
       
       return ResultTO.newSuccessResultTO(" 修改权限成功" , null);
   }
    
    /**
     * 初始化权限页面
     * @author HeWeiwen 2015-10-12
     * @param appNow
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "index" , method = RequestMethod.GET)
    public String indexPermission(String navigationIdNow, Model model) {
        //1,查询所有菜单数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("link", "");
        map.put("status", 1);
        List<SystemNavigation> navigationList = systemNavigationService.selectObjectList(map);
        //3,加载公共数据
        initIndex(model);
        model.addAttribute("navigations", navigationList);
        model.addAttribute("navigationIdNow", navigationIdNow);

        return "back/admin/permission.index";
    }
    
    /**
     * 权限设置列表分页查询
     * 
     * @author HeWeiwen 2015-10-10
     * @param appkey
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pagePermission(String navigationId, int pageSize, int pageIndex) {
        Map<String,Object> reqMap = new HashMap<String, Object>();
        //1,通过子级ID集合分页查询数据
        Page<Map<String, Object>> pages = null;
        if (null != navigationId && !"".equals(navigationId)) {
            List<Integer> navigationList = systemNavigationService.getSytemNavigationByParentId(Integer.parseInt(navigationId));
            reqMap.put("permissionList",navigationList);
            pages = adminPermissionService.page("pageByNavList", "pageByNavListCondition", reqMap, pageIndex, pageSize);
        }else {
            pages = adminPermissionService.page(reqMap, pageIndex, pageSize);
        }
        //2，分页数据重组
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : pages.getData()) {
            Map<String, Object> mapResult = new HashMap<String, Object>();
            mapResult.putAll(data);
            //2.1,通过NavigationId查询菜单标题信息
            Map<String, Object> mapNavigation = new HashMap<String, Object>();
            mapNavigation.put("navigationId",data.get("navigationId"));
            SystemNavigation navigation = systemNavigationService.selectObject(mapNavigation);
            if (navigation != null) {
                mapResult.put("title", navigation.getTitle());
            }
            resultList.add(mapResult);
        }
        Map<String, Object> RespMap = new HashMap<String, Object>();
        RespMap.put("data", resultList);
        RespMap.put("count", pages.getCount());
        
        return RespMap;
    }
    
    /**
     * 权限添加
     * @author HeWeiwen 2015-10-12
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "add" , method = RequestMethod.GET)
    public String indexPermissionAdd(Model model) {
        //1,查询所有菜单信息
        Map<String, Object> map = new HashMap<String, Object>();
        List<SystemNavigation> navigationList = systemNavigationService.selectObjectList(map);
        //2,重组菜单数据
        List<SystemNavigation> sysNavTwo = new ArrayList<SystemNavigation>();
        List<SystemNavigation> sysNavThree = new ArrayList<SystemNavigation>();
        for (SystemNavigation data:navigationList) {
            if (data.getLink().equals("") && data.getStatus() == 1) {
                sysNavTwo.add(data);
            } else if (!data.getLink().equals("")) {
                sysNavThree.add(data);
            }
        }
        //3,加载公共数据
        initIndex(model);
        model.addAttribute("sysTwo", sysNavTwo);
        model.addAttribute("sysThree", sysNavThree);

        return "back/admin/permission.edit";
    }
    
    /**
     * 权限设置
     * @author HeWeiwen 2015-10-12
     * @param model
     * @param permissionId
     * @param req
     * @return
     */
    @RequestMapping(value = "edit" , method = RequestMethod.GET)
    public String indexPermissionEdit(Model model, int permissionId) {
        //1，获得权限菜单数据
        Map<String, Object> map = new HashMap<String, Object>();
        List<SystemNavigation> navigationList = systemNavigationService.selectObjectList(map);
        //2，菜单数据重组
        List<SystemNavigation> sysTwo = new ArrayList<SystemNavigation>();
        List<SystemNavigation> sysThree = new ArrayList<SystemNavigation>();
        for (SystemNavigation data:navigationList) {
            if (data.getLink().equals("") && data.getStatus() == 1) {
                sysTwo.add(data);
            } else if (!data.getLink().equals("") && data.getStatus() == 1) {
                sysThree.add(data);
            }
        }
        
        //3，根据permissionId查询权限数据
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("permissionId", permissionId);
        AdminPermission permission = adminPermissionService.selectObject(reqMap);
        //4，获得菜单的父级菜单ID
        Map<String, Object> navMap = new HashMap<String, Object>();
        navMap.put("navigationId", permission.getNavigationId());
        SystemNavigation navigation = systemNavigationService.selectObject(navMap);
        //4，加载公共数据
        initIndex(model);
        model.addAttribute("parentId", navigation.getParentId());
        model.addAttribute("sysTwo", sysTwo);
        model.addAttribute("sysThree", sysThree);
        model.addAttribute("permission", permission);

        return "back/admin/permission.edit";
    }
    
    /**
     * 添加权限-保存
     * @author HeWeiwen
     * 2015-11-18
     * @param permission
     * @param result
     * @return
     */
   @RequestMapping(value = "add", method = RequestMethod.POST)
   @ResponseBody
   public ResultTO permissionAddSave(@ModelAttribute AdminPermission permission, BindingResult result) {
       //1,添加模块 - 保存
       adminPermissionService.insert(permission);
       //2,更新权限的缓存数据
       adminPermissionService.updateAdminPermissionAll();
       return ResultTO.newSuccessResultTO(" 添加模块 - 成功", null);
   }

   /**
    * 修改权限 - 保存
    * @author HeWeiwen
    * 2015-11-18
    * @param permission
    * @param result
    * @return
    */
   @RequestMapping(value = "edit", method = RequestMethod.POST)
   @ResponseBody
   public ResultTO permissionEdit(@ModelAttribute AdminPermission permission, BindingResult result) {
       //1,修改模块 - 保存
       adminPermissionService.update(permission);
       //2,更新权限的缓存数据
       adminPermissionService.updateAdminPermissionAll();
       return ResultTO.newSuccessResultTO(" 修改模块 - 成功", null);
   }
   
   /**
    * 删除权限
    * @author HeWeiwen
    * 2015-11-18
    * @param permissionId
    * @return
    */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deletePermission(int permissionId) {
        if (permissionId < 1) {
            return ResultTO.newFailResultTO("权限ID错误", null);
        }
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("permissionId", permissionId);
        // 1,删除关联数据
        adminGroupPermissionService.deleteByPermissionId(permissionId);
        // 2,删除数据
        adminPermissionService.delete(reqMap);
        // 3,更新权限的缓存数据
        adminPermissionService.updateAdminPermissionAll();
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    /**
     * 数据校验
     * @author HeWeiwen
     * 2015-11-18
     * @param groupId
     * @param permissionId
     * @return
     */
    private ResultTO checkCompletion(Integer groupId, Integer permissionId) {
        if (groupId < 1) {
            return ResultTO.newFailResultTO(" 用户组ID错误" , null);
        }
        if (permissionId < 1) {
            return ResultTO.newFailResultTO("权限ID错误" , null);
        }
        return ResultTO.newSuccessResultTO("",null);
    }
    
    /**
     * 获得权限集合对象
     * @author HeWeiwen
     * 2015-11-23
     * @param navigationIdNew
     * @param allNavigation
     * @return
     */
    private Map<String, List<AdminPermission>> getPermissionMaps(int navigationId){
        Map<String, List<AdminPermission>> pMaps = new LinkedHashMap<String, List<AdminPermission>>();
        //通过父级菜单查询所有子级菜单数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", navigationId);
        List<SystemNavigation> navigations = systemNavigationService.selectObjectList(map);
        //遍历子级集合获得List对象
        List<Integer> sysNavList = new ArrayList<Integer>();
        for (SystemNavigation data:navigations) {
            sysNavList.add(data.getNavigationId());
        }
        //通过子级菜单Id集合查询所有权限对象集合
        List<AdminPermission> permissions = adminPermissionService.getPermissionListByNavigationIdList(sysNavList);
        for (AdminPermission permission:permissions) {
            String key = "";
            //重组子级菜单数据
            for (SystemNavigation navigation:navigations) {
                if (navigation.getNavigationId() == permission.getNavigationId()) {
                    key = navigation.getTitle();
                }
            }
            List<AdminPermission> list = pMaps.get(key);
            if (null == list) {
                list = new ArrayList<AdminPermission>();
                pMaps.put(key, list);
            }
            list.add(permission);
        }
        return pMaps;
    }
    
}
