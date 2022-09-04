package com.bluemobi.controller.back.cas;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.cas.CasUserGroup;
import com.bluemobi.service.cas.CasUserGroupService;
import com.bluemobi.to.ResultTO;

/**
 * 会员分组管理
 * 
 * @author liuyt ,heweiwen
 * 2015-12-4 下午3:19:24
 */
@Controller
@RequestMapping("back/casuserGroup")
public class CasUserGroupController extends AbstractBackController {

    @Autowired
    private CasUserGroupService casUserGroupService;

    /**
     * 初始化会员分组页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexGroup(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/group.index";
    }

    /**
     * 分页查询会员分组
     * 
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageGroup(int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Map<String, Object>> groupPages = casUserGroupService.page(map,
                pageIndex, pageSize);
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", groupPages.getData());
        mapResult.put("count", groupPages.getCount());
        return mapResult;
    }

    /**
     * 初始化会员分组新增页面
     * 
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String indexGroupAdd(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/group.edit";
    }

    /**
     * 新增会员分组
     * 
     * @param groupName
     *            分组名称
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addGroup(String groupName) {
        CasUserGroup group = new CasUserGroup();
        group.setGroupName(groupName);
        casUserGroupService.insert(group);
        return ResultTO.newSuccessResultTO("添加会员分组成功", null);
    }

    /**
     * 初始化会员分组修改页面
     * 
     * @param model
     * @param userGroupId
     * @param req
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String indexGroupEdit(Model model, int userGroupId,
            HttpServletRequest req) {
        // 加载公共数据
        initIndex(model);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("userGroupId", userGroupId);
        CasUserGroup group = casUserGroupService.selectObject(map);
        model.addAttribute("userGroupId", userGroupId);
        model.addAttribute("group", group);
        return "back/cas/group.edit";
    }

    /**
     * 修改会员分组
     * 
     * @param group
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editGroup(@ModelAttribute CasUserGroup group) {
        casUserGroupService.update(group);
        return ResultTO.newSuccessResultTO("修改用户分组成功", null);
    }

    /**
     * 删除会员分组
     * 
     * @param userGroupId
     *            会员分组ID
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteGroup(int userGroupId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("userGroupId", userGroupId);
        casUserGroupService.delete(map);
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

}
