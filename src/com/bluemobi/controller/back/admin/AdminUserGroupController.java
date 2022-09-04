package com.bluemobi.controller.back.admin;

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

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.admin.AdminGroup;
import com.bluemobi.service.admin.AdminGroupService;
import com.bluemobi.to.ResultTO;

/**
 * 【用户组】控制器
 * @author heweiwen
 * 2015-12-4 下午4:32:57
 */
@Controller
@RequestMapping("back/adminUserGroup")
public class AdminUserGroupController extends AbstractBackController {
    
    @Autowired
    private AdminGroupService adminGroupService;
    /**
     * 初始化用户组页面
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexGroup(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/admin/group.index";
    }
    
    /**
     * 初始化添加用户组
     * @author HeWeiwen
     * 2015-11-24
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String indexGroupSave(Model model) {
        //加载公共数据
        initIndex(model);
        return "back/admin/group.edit";
    }
    /**
     * 初始化修改用户组
     * @author HeWeiwen
     * 2015-11-24
     * @param model
     * @param req
     * @param groupId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String indexGroupEdit(Model model, int groupId) {
        //1,加载公共数据
        initIndex(model);
        //2,根据用户组ID查询用户组对象
        Map<String, Integer> mapGroup = new HashMap<String, Integer>();
        mapGroup.put("groupId", groupId);
        model.addAttribute("group", adminGroupService.selectObject(mapGroup));

        return "back/admin/group.edit";
    }
    /**
     * 用户组列表
     * @author HeWeiwen
     * 2015-11-17
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageGroup() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> dates = adminGroupService.selectMapList(map);

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", dates);
        mapResult.put("count", null);
        return mapResult;
    }
    
    /**
     * 添加用户组
     * @author HeWeiwen
     * 2015-11-17
     * @param group
     * @param result
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveGroup(@ModelAttribute AdminGroup group, BindingResult result) {
        // 添加用户
        adminGroupService.insert(group);
        return ResultTO.newSuccessResultTO(" 添加 - 成功", null);
    }
    
    /**
     * 修改用户组信息
     * @author HeWeiwen
     * 2015-11-24
     * @param group
     * @param result
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateGroup(@ModelAttribute AdminGroup group, BindingResult result) {
        //修改用户组信息
        adminGroupService.update(group);
        
        return ResultTO.newSuccessResultTO(" 修改用户 - 成功" , null);
    }
    
    /**
     * 删除权限组
     * @author HeWeiwen
     * 2015-11-17
     * @param groupid
     * @param delUser
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteGroup(int groupId, int delUser) {
        if (groupId < 1) {
            return ResultTO.newFailResultTO("用户ID错误" , null);
        }
        Map<String, Integer> reqMap = new HashMap<String, Integer>();
        reqMap.put("groupId", groupId);
        // 删除用户组数据
        adminGroupService.delete(reqMap);

        return ResultTO.newSuccessResultTO(" 删除成功" , null);
    }
    
}
