package com.bluemobi.controller.back.cas;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.bluemobi.po.cas.CasUserLv;
import com.bluemobi.service.cas.CasUserGroupService;
import com.bluemobi.service.cas.CasUserLvService;
import com.bluemobi.to.ResultTO;

/**
 * 会员等级管理
 * @author liuyt , heweiwen
 * 2015-12-4 下午3:35:58
 */
@Controller
@RequestMapping("back/casuserLevel")
public class CasUserLevelController extends AbstractBackController {

    @Autowired
    private CasUserGroupService casUserGroupService;
    @Autowired
    private CasUserLvService casUserLvService;

    /**
     * 初始化会员等级页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String indexLv(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/lv.index";
    }

    /**
     * 分页查询会员等级信息
     * 
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageLv(int pageIndex, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Map<String, Object>> lvPages = casUserLvService.page(map,
                pageIndex, pageSize);
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", lvPages.getData());
        mapResult.put("count", lvPages.getCount());
        return mapResult;
    }

    /**
     * 初始化会员等级新增页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String indexLvAdd(Model model) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<CasUserGroup> userGroupList = casUserGroupService
                .selectObjectList(map);
        // 加载公共数据
        initIndex(model);
        model.addAttribute("userGroupList", userGroupList);
        return "back/cas/lv.edit";
    }

    /**
     * 新增会员等级
     * 
     * @param userLv
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addLv(@ModelAttribute CasUserLv userLv) {
        userLv.setStatus((byte) 1);
        Date date = new Date();
        userLv.setCtime(date);
        userLv.setMtime(date);
        casUserLvService.insert(userLv);
        return ResultTO.newSuccessResultTO("添加用户等级成功", null);
    }

    /**
     * 初始化会员等级修改页面
     * 
     * @param userLvId
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String indexLvEdit(int userLvId, Model model) {
        Map<String, Integer> groupMap = new HashMap<String, Integer>();
        Map<String, Integer> lvMap = new HashMap<String, Integer>();
        lvMap.put("userLvId", userLvId);

        List<CasUserGroup> userGroupList = casUserGroupService
                .selectObjectList(groupMap);
        CasUserLv lv = casUserLvService.selectObject(lvMap);

        // 加载公共数据
        initIndex(model);
        model.addAttribute("userGroupList", userGroupList);
        model.addAttribute("lv", lv);
        return "back/cas/lv.edit";
    }

    /**
     * 修改会员等级信息
     * 
     * @param userlv
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editLv(@ModelAttribute CasUserLv userlv) {
        userlv.setMtime(new Date());
        if (userlv.getIsDefault() == 1) {
            //将原有默认指修改为非默认
            casUserLvService.updateIsDefault();
            userlv.setIsDefault((byte) (userlv.getIsDefault() == 1 ? 1 : 0));
        }
        casUserLvService.update(userlv);

        return ResultTO.newSuccessResultTO("修改会员等级成功", null);
    }

    /**
     * 删除会员等级
     * 
     * @param userLvId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteLv(int userLvId) {
        Map<String, Integer> lvMap = new HashMap<String, Integer>();
        lvMap.put("userLvId", userLvId);
        casUserLvService.delete(lvMap);
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
}
