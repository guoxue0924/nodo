package com.bluemobi.controller.api.cas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.appcore.util.JsonUtil;
import com.bluemobi.apito.UserPointTO;
import com.bluemobi.apito.cas.AddUserPointRequestTO;
import com.bluemobi.apito.cas.AddUserPointResponseTO;
import com.bluemobi.apito.cas.CheckTodayIsRegisterRequestTO;
import com.bluemobi.apito.cas.CheckTodayIsRegisterResponseTO;
import com.bluemobi.apito.cas.GetUserPointRequestTO;
import com.bluemobi.apito.cas.GetUserPointResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.service.cas.CasUserPointService;
import com.bluemobi.to.ResultTO;

/**
 * 【用户积分】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:49:34
 * 
 */
@Controller(value = "casUserPointAPIController")
@RequestMapping("api/casUserPoint")
public class CasUserPointAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserPointAPIController.class);

    @Autowired
    private CasUserPointService casUserPointService;

    /**
     * 每日签到
     * 
     * @param request
     * @param addUserPointRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-03-07 14:49:34
     */
    @RequestMapping(value = "addUserPoint")
    @ResponseBody
    public ResultTO addUserPoint(HttpServletRequest request, String json) {

        AddUserPointRequestTO addUserPointRequestTO = JsonUtil.getObject(json, AddUserPointRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), addUserPointRequestTO.toString() });
        AddUserPointResponseTO addUserPointResponseTO = new AddUserPointResponseTO();
        Integer i = 0;
        try {
            Integer userId = this.getUserid();
            Integer pointType = addUserPointRequestTO.getPointType();
            if (pointType == null) {
                LOGGER.error("添加用户积分失败，积分规则为空。", new Object[] {});
                return ResultTO.newFailResultTO("添加用户积分失败，积分规则为空。", null);
            }
            i = casUserPointService.insertCasUserPoint(pointType, userId);
            if (i == 1) {
                LOGGER.error("添加用户积分失败，没有对应的积分规则。请求ip【{}】，请求的积分规则【{}】", new Object[] { request.getRemoteHost(), addUserPointRequestTO.getPointType() });
                return ResultTO.newFailResultTO("添加用户积分失败，没有对应的积分规则！", null);
            }
            if (i == 2) {
                LOGGER.error("添加用户积分失败，今日已签到过。请求ip【{}】，请求的积分规则【{}】", new Object[] { request.getRemoteHost(), addUserPointRequestTO.getPointType() });
                return ResultTO.newFailResultTO("添加用户积分失败，今日已签到过！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加用户积分出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), addUserPointRequestTO.toString() });
            return ResultTO.newFailResultTO("添加用户积分失败！", null);
        }
        return ResultTO.newSuccessResultTO("添加用户积分成功！", addUserPointResponseTO);
    }

    /**
     * 获取用户所有积分
     * 
     * @param request
     * @param getUserPointRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-03-07 14:49:34
     */
    @RequestMapping(value = "getUserPoint")
    @ResponseBody
    public ResultTO getUserPoint(HttpServletRequest request, String json) {

        GetUserPointRequestTO getUserPointRequestTO = JsonUtil.getObject(json, GetUserPointRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getUserPointRequestTO.toString() });
        GetUserPointResponseTO getUserPointResponseTO = new GetUserPointResponseTO();
        Map<String, Object> param = new HashMap<String, Object>();
        Page<CasUserPoint> page = null;
        try {
            if (this.getUserid() == 0) {
                return ResultTO.newFailResultTO("未获得当前用户，请先登录！", null);
            }
            param.put("userid", this.getUserid());
            page = casUserPointService.page(param, getUserPointRequestTO.getPageIndex(), getUserPointRequestTO.getPageSize());
            if (page != null) {
                userPointTransformation(getUserPointResponseTO, page);
            }
            getUserPointResponseTO.setPageIndex(getUserPointRequestTO.getPageIndex());
            getUserPointResponseTO.setPageSize(getUserPointRequestTO.getPageSize());
            return ResultTO.newSuccessResultTO("获取用户积分成功！", getUserPointResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取所有用户积分出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getUserPointRequestTO.toString() });
            return ResultTO.newFailResultTO("获取用户积分失败！", null);
        }
    }

    /**
     * 转换用户积分数据结构
     * 
     * @auther zhangzheng
     * @date 2016-3-7 下午4:23:17
     * @param getUserPointResponseTO
     * @param page
     */
    private void userPointTransformation(GetUserPointResponseTO getUserPointResponseTO, Page<CasUserPoint> page) {
        List<CasUserPoint> points = page.getData();
        List<UserPointTO> pointTOs = new ArrayList<UserPointTO>();
        UserPointTO pointTO = null;
        CasUserPoint point = null;
        for (int i = 0; i < points.size(); i++) {
            point = points.get(i);
            pointTO = new UserPointTO();
            pointTO.setPointId(point.getPointId());
            pointTO.setPointType(point.getPointType());
            pointTO.setPointName(point.getPointName());
            pointTO.setPoint(point.getPoint());
            pointTO.setCtime(point.getCtime());
            pointTOs.add(pointTO);
        }
        getUserPointResponseTO.setUserPoints(pointTOs);
    }

    /**
     * 获取用户是否已签到
     * 
     * @param request
     * @param checkTodayIsRegisterRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-03-08 14:31:42
     */
    @RequestMapping(value = "checkTodayIsRegister")
    @ResponseBody
    public ResultTO checkTodayIsRegister(HttpServletRequest request, String json) {
        CheckTodayIsRegisterRequestTO checkTodayIsRegisterRequestTO = JsonUtil.getObject(json,CheckTodayIsRegisterRequestTO.class); 
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), checkTodayIsRegisterRequestTO.toString() });
        CheckTodayIsRegisterResponseTO checkTodayIsRegisterResponseTO = new CheckTodayIsRegisterResponseTO();
        Integer i = 0;
        try {
            boolean flag = casUserPointService.checkTodayIsRegister(this.getUserid());
            if (flag) {
                i = 1;
            }
            checkTodayIsRegisterResponseTO.setFlag(i);
            return ResultTO.newSuccessResultTO("获取用户是否已签到成功！", checkTodayIsRegisterResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取用户是否已签到出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), checkTodayIsRegisterRequestTO.toString() });
            return ResultTO.newFailResultTO("获取用户是否已签到失败！", null);
        }
    }
}
