package com.bluemobi.controller.back.cas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.service.cas.CasUserPointService;
import com.bluemobi.to.ResultTO;

/**
 * 【用户积分表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:28:43
 * 
 */
@Controller
@RequestMapping("back/casUserPoint")
public class CasUserPointController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserPointController.class);

    @Autowired
    private CasUserPointService casUserPointService;

    /**
     * 将请求参数中的字符串转换成日期格式
     * 
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 进入【用户积分表】主页
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/userPoint.index";
    }

    /**
     * 分页查询【用户积分表】
     * 
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = casUserPointService.page(map, pageIndex, pageSize);
        return page;
    }

    /**
     * 查询【用户积分表】详情
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer pointId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("pointId", pointId);
        model.addAttribute("casUserPoint", casUserPointService.selectObject(map));
        return "back/cas/userPoint.detail";
    }

    /**
     * 进入新增【用户积分表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/cas/userPoint.edit";
    }

    /**
     * 新增【用户积分表】数据
     * 
     * @param casUserPoint
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addCasUserPoint(@ModelAttribute CasUserPoint casUserPoint, BindingResult result) {
        try {
            casUserPointService.insert(casUserPoint);
            LOGGER.info("用户【{}】添加用户积分表数据【{}】成功", new Object[] { this.getUserid(), casUserPoint });
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加用户积分表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), casUserPoint, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 进入修改【用户积分表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer pointId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pointId", pointId);
        model.addAttribute("casUserPoint", casUserPointService.selectObject(map));
        return "back/cas/userPoint.edit";
    }

    /**
     * 修改【用户积分表】数据
     * 
     * @param casUserPoint
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editCasUserPoint(@ModelAttribute CasUserPoint casUserPoint, BindingResult result) {
        try {
            casUserPointService.update(casUserPoint);
            LOGGER.info("用户【{}】修改用户积分表数据【{}】成功", new Object[] { this.getUserid(), casUserPoint });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改用户积分表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), casUserPoint, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }

    /**
     * 删除【用户积分表】数据
     * 
     * @param pointId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-07 14:28:43
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteCasUserPoint(Integer pointId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("pointId", pointId);
            casUserPointService.delete(map);
            LOGGER.info("用户【{}】删除用户积分表数据【{}】成功", new Object[] { this.getUserid(), pointId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除用户积分表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), pointId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

}
