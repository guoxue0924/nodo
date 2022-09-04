package com.bluemobi.controller.api.system;

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

import com.appcore.util.JsonUtil;
import com.bluemobi.apito.RegionTO;
import com.bluemobi.apito.system.GetRegionByPidRequestTO;
import com.bluemobi.apito.system.GetRegionByPidResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;

/**
 * 【地区】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-08 15:24:30
 * 
 */
@Controller(value = "regionAPIController")
@RequestMapping("api/region")
public class RegionAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionAPIController.class);
    
    @Autowired
    private TrendRegionService trendRegionService;

	/**
	 * 根据Pid获得地区信息
     * @param request
     * @param GetRegionByPidRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-03-08 15:24:30
	 */
    @RequestMapping(value = "GetRegionByPid")
    @ResponseBody
    public ResultTO GetRegionByPid(HttpServletRequest request, String json) {

        GetRegionByPidRequestTO GetRegionByPidRequestTO = JsonUtil.getObject(json, GetRegionByPidRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), GetRegionByPidRequestTO.toString() });
        
        try{
            //处理业务
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("pid", GetRegionByPidRequestTO.getPid());
            List<TrendRegion> trendRegions = trendRegionService.selectObjectList(reqMap);

            //数据重组
            GetRegionByPidResponseTO GetRegionByPidResponseTO = new GetRegionByPidResponseTO();
            List<RegionTO> regionList = new ArrayList<RegionTO>();
            for (TrendRegion data:trendRegions) {
                RegionTO regionTO = new RegionTO();
                regionTO.setPid(data.getPid());
                regionTO.setRegionId(data.getRegionId());
                regionTO.setRegionName(data.getRegionName());
                regionList.add(regionTO);
            }
            GetRegionByPidResponseTO.setRegions(regionList);
            return ResultTO.newSuccessResultTO("根据Pid获得地区信息成功！", GetRegionByPidResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("根据Pid获得地区信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), GetRegionByPidRequestTO.toString() });
            return ResultTO.newFailResultTO("根据Pid获得地区信息失败！", null);
        }
		
    }




}
