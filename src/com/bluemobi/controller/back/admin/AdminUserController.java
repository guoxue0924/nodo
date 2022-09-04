package com.bluemobi.controller.back.admin;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.admin.AdminUserGroup;
import com.bluemobi.service.admin.AdminGroupService;
import com.bluemobi.service.admin.AdminUserGroupService;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.to.ResultTO;

/**
 * 管理员管理
 * @author heweiwen
 * 2015-11-17 下午3:23:17
 */
@Controller
@RequestMapping("back/adminUser")
public class AdminUserController extends AbstractBackController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminGroupService adminGroupService;
    @Autowired
    private AdminUserGroupService adminUserGroupService;
    
    /**
     * 初始化管理员管理页面
     * 
     * @author HeWeiwen 2015-7-17
     * @param model
     * @return
     */
    @RequestMapping(value = "index" , method = RequestMethod.GET)
    public String indexUser(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/admin/user.index";
    }

    /**
     *  管理员添加
     * @author HeWeiwen
     * 2015-11-24
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String indexUserAdd(Model model) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        // 加载公共数据
        initIndex(model);
        // 查询 权限组对象集合，添加多选显示
        model.addAttribute("groups", adminGroupService.selectObjectList(map));
        return "back/admin/user.edit";
    }
    
    /**
     * 平台用户详情初始化
     * @author HeWeiwen
     * 2015-12-3
     * @param userid
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String indexUserDetail(int userid, Model model) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("userid", userid);
        AdminUser user = adminUserService.selectObject(reqMap);
        // 加载公共数据
        initIndex(model);
        model.addAttribute("user", user);
        return "back/admin/user.detail";
    }
    
    /**
     * 管理员修改
     * @author HeWeiwen
     * 2015-11-17
     * @param model
     * @param userid
     * @param req
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String indexUserUpdate(Model model, int userid) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        //1,根据userid查询用户对象
        Map<String, Integer> mapUser = new HashMap<String, Integer>();
        mapUser.put("userid", userid);
        AdminUser user = adminUserService.selectObject(mapUser);
        //2,根据userid查询用户关联权限组对象
        Map<String, Integer> mapGroup = new HashMap<String, Integer>();
        mapGroup.put("userId", userid);
        List<AdminUserGroup> userGroups = adminUserGroupService.selectObjectList(mapGroup);
        //3,重组用户权限组数据
        List<Integer> groupids = new ArrayList<Integer>();
        for (int i = 0; i < userGroups.size(); i++) {
            groupids.add(userGroups.get(i).getGroupId());
        }
        //4,加载公共数据
        initIndex(model);
        model.addAttribute("userid", userid);
        model.addAttribute("user", user);
        model.addAttribute("groups", adminGroupService.selectObjectList(map));
        model.addAttribute("user_groupids", groupids);

        return "back/admin/user.edit";
    }
    
   
    
    /**
     * 用户列表
     * @author HeWeiwen
     * 2015-11-17
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageUser(String key, int pageSize, int pageIndex) {
        //1,根据用户名模糊分页查询用户列表
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != key && !"".equals(key)) {
            map.put("username", key);
            map.put("fullname", key);
        }
        Page<Map<String, Object>> pages = adminUserService.page(map, pageIndex, pageSize);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //2,用户列表数据重组
        for (Map<String, Object> data : pages.getData()) {
            String groupStr = "";
            Map<String, Object> mapResult = new HashMap<String, Object>();
            mapResult.putAll(data);
            //2.1,根据userid查询该用户下所有权限组数据
            Map<String, Integer> mapGroup = new HashMap<String, Integer>();
            mapGroup.put("userid", (Integer) data.get("userid"));
            List<AdminUserGroup> userGroups = adminUserGroupService.selectObjectList(mapGroup);
            if (!userGroups.isEmpty()) {
                //遍历查询权限组信息
                for (AdminUserGroup group : userGroups) {
                    Map<String, Object> mapAdminGroup = new HashMap<String, Object>();
                    mapAdminGroup.put("groupId", group.getGroupId());
                    List<AdminGroup> adminGroupList = adminGroupService.selectObjectList(mapAdminGroup);
                    if (!adminGroupList.isEmpty() && (Integer) data.get("userid") == group.getUserId()) {
                        for (AdminGroup adminGroup : adminGroupList) {
                            groupStr += adminGroup.getGroupName() + ",";
                        }
                    }
                }
            }
            if (groupStr.length() > 0) {
                groupStr = groupStr.substring(0, groupStr.length() - 1);
            }
            mapResult.put("groups", groupStr);
            resultList.add(mapResult);
        }
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", resultList);
        mapResult.put("count", pages.getCount());
        return mapResult;
    }
    
    
    
    /**
     * 添加用户
     * @author HeWeiwen
     * 2015-11-18
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveUser(@ModelAttribute AdminUser user, BindingResult result) {
        //1,根据用户名校验用户是否存在
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("username", user.getUsername());
        List<AdminUser> userList = adminUserService.selectObjectList(reqMap);
        if (userList.size() > 0) {
            return ResultTO.newFailResultTO("添加失败，用户名重复", reqMap);
        }
        //2,添加用户
        adminUserService.insertUser(user);
        //3,更新用户与用户组关系
        if (user.getGroups() != null) {
            adminUserGroupService.updateUserGroup(user.getUserid(), user.getGroups());
        }
        return ResultTO.newSuccessResultTO(" 添加用户 - 成功", null);
    }
    
    /**
     * 修改用户信息
     * @author HeWeiwen
     * 2015-11-18
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateUser(@ModelAttribute AdminUser user, BindingResult result) {
        //1,修改用户信息
        adminUserService.updateUser(user);
        //2,更新用户与用户组关系
        adminUserGroupService.updateUserGroup(user.getUserid(), user.getGroups());
        
        return ResultTO.newSuccessResultTO(" 修改用户 - 成功" , null);
    }
    
    /**
    * 删除用户
    * 
    * @author HeWeiwen 2015-7-21
    * @param userid
    * @return
    */
   @RequestMapping(value = "delete", method = RequestMethod.POST)
   @ResponseBody
   public ResultTO deleteUser(int userid) {
       if (userid < 1) {
           return ResultTO.newFailResultTO("用户ID错误" , null);
       }
       //1,删除用户
       Map<String, Integer> reqMap = new HashMap<String, Integer>();
       reqMap.put("userid", userid);
       adminUserService.delete(reqMap);
       //2,更新用户与用户组关系
       adminUserGroupService.deleteByUserId(userid);
       
       return ResultTO.newSuccessResultTO(" 删除成功" , null);
   }
    
    
}
