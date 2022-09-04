package com.bluemobi.controller.back.cas;

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
import com.bluemobi.po.cas.CasUserPointRule;
import com.bluemobi.service.cas.CasUserPointRuleService;
import com.bluemobi.to.ResultTO;

/**
 * 【用户积分规则表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:28:44
 * 
 */
@Controller
@RequestMapping("back/casUserPointRule")
public class CasUserPointRuleController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserPointRuleController.class);

    @Autowired
    private CasUserPointRuleService casUserPointRuleService;

    /**
     * 进入【用户积分规则表】主页
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/userPointRule.index";
    }

    /**
     * 分页查询【用户积分规则表】
     * 
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = casUserPointRuleService.page(map, pageIndex, pageSize);
        return page;
    }

    /**
     * 查询【用户积分规则表】详情
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer pointRoleId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("pointRoleId", pointRoleId);
        model.addAttribute("casUserPointRule", casUserPointRuleService.selectObject(map));
        return "back/cas/userPointRule.detail";
    }

    /**
     * 进入新增【用户积分规则表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/userPointRule.edit";
    }

    /**
     * 新增【用户积分规则表】数据
     * 
     * @param casUserPointRule
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addCasUserPointRule(@ModelAttribute CasUserPointRule casUserPointRule, BindingResult result) {
        try {
            casUserPointRuleService.insert(casUserPointRule);
            LOGGER.info("用户【{}】添加用户积分规则表数据【{}】成功", new Object[] { this.getUserid(), casUserPointRule });
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加用户积分规则表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), casUserPointRule, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 进入修改【用户积分规则表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer pointRoleId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pointRoleId", pointRoleId);
        model.addAttribute("casUserPointRule", casUserPointRuleService.selectObject(map));
        return "back/cas/userPointRule.edit";
    }

    /**
     * 修改【用户积分规则表】数据
     * 
     * @param casUserPointRule
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editCasUserPointRule(@ModelAttribute CasUserPointRule casUserPointRule, BindingResult result) {
        try {
            casUserPointRuleService.update(casUserPointRule);
            LOGGER.info("用户【{}】修改用户积分规则表数据【{}】成功", new Object[] { this.getUserid(), casUserPointRule });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改用户积分规则表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), casUserPointRule, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }

    /**
     * 删除【用户积分规则表】数据
     * 
     * @param pointRoleId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-07 14:28:44
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteCasUserPointRule(Integer pointRoleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("pointRoleId", pointRoleId);
            casUserPointRuleService.delete(map);
            LOGGER.info("用户【{}】删除用户积分规则表数据【{}】成功", new Object[] { this.getUserid(), pointRoleId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除用户积分规则表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), pointRoleId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

}
