package com.bluemobi.controller.back.log;
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
import com.bluemobi.po.log.LogUserAction;
import com.bluemobi.service.log.LogUserActionService;
import com.bluemobi.to.ResultTO;



/**
 * 【用户行为日志表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-10 11:10:47
 * 
 */
@Controller
@RequestMapping("back/logUserAction")
public class LogUserActionController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUserActionController.class);
    
    @Autowired
    private LogUserActionService logUserActionService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【用户行为日志表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/log/userAction.index";
    }
    
    /**
     * 分页查询【用户行为日志表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = logUserActionService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【用户行为日志表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer logId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("logId", logId); 
        model.addAttribute("logUserAction", logUserActionService.selectObject(map));
        return "back/log/userAction.detail";
    }
    
    /**
     * 进入新增【用户行为日志表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "back/log/userAction.edit";
    }
    
    /**
     * 新增【用户行为日志表】数据
     * @param logUserAction
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addLogUserAction(@ModelAttribute LogUserAction logUserAction, BindingResult result) {
        try {
            logUserActionService.insert(logUserAction);
            LOGGER.info("用户【{}】添加用户行为日志表数据【{}】成功", new Object[] { this.getUserid(), logUserAction } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加用户行为日志表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), logUserAction, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【用户行为日志表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer logId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logId", logId); 
        model.addAttribute("logUserAction", logUserActionService.selectObject(map));
        return "back/log/userAction.edit";
    }
    
    /**
     * 修改【用户行为日志表】数据
     * @param logUserAction
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editLogUserAction(@ModelAttribute LogUserAction logUserAction, BindingResult result) {
        try {
            logUserActionService.update(logUserAction);
            LOGGER.info("用户【{}】修改用户行为日志表数据【{}】成功", new Object[] { this.getUserid(), logUserAction } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改用户行为日志表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), logUserAction, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【用户行为日志表】数据
     * @param logId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteLogUserAction(Integer logId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("logId", logId); 
            logUserActionService.delete(map);
            LOGGER.info("用户【{}】删除用户行为日志表数据【{}】成功", new Object[] { this.getUserid(), logId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除用户行为日志表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), logId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
