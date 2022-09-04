package com.bluemobi.controller.api.trend;

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
import com.bluemobi.apito.VersionTO;
import com.bluemobi.apito.trend.CheckVersionRequestTO;
import com.bluemobi.apito.trend.CheckVersionResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.trend.TrendVersion;
import com.bluemobi.service.trend.TrendVersionService;
import com.bluemobi.to.ResultTO;

/**
 * 【版本】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-08 15:24:30
 * 
 */
@Controller(value = "versionAPIController")
@RequestMapping("api/version")
public class VersionAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionAPIController.class);

    @Autowired
    private TrendVersionService trendVersionService;
    
	/**
	 * 检查APP版本信息
     * @param request
     * @param CheckVersionRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-03-08 15:24:30
	 */
    @RequestMapping(value = "CheckVersion")
    @ResponseBody
    public ResultTO CheckVersion(HttpServletRequest request, String json) {
        
        CheckVersionRequestTO CheckVersionRequestTO = JsonUtil.getObject(json, CheckVersionRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), CheckVersionRequestTO.toString() });
        
        try{
            //查询数据
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("vCode", CheckVersionRequestTO.getVCodeNew());
            reqMap.put("platform", CheckVersionRequestTO.getPlatform());
            List<TrendVersion> versions = trendVersionService.selectObjectList(reqMap);
            if (versions.size() <= 0) {
                return ResultTO.newFailResultTO("查无此版本", null);
            }
            
            //组合数据
            CheckVersionResponseTO checkVersionResponseTO = new CheckVersionResponseTO();
            checkVersionResponseTO.setPlatform(CheckVersionRequestTO.getPlatform());
            checkVersionResponseTO.setVCodeNew(CheckVersionRequestTO.getVCodeNew());
            
            VersionTO version = new VersionTO();
            version.setContent(versions.get(0).getContent());
            version.setFilepath(versions.get(0).getFilepath());
            version.setFilepathTdc(versions.get(0).getFilepathTdc());
            version.setId(versions.get(0).getId());
            version.setMtime(versions.get(0).getMtime());
            version.setPlatform(versions.get(0).getPlatform());
            version.setSize(versions.get(0).getSize());
            version.setVCode(versions.get(0).getVCode());
            version.setVName(versions.get(0).getVName());
            
            checkVersionResponseTO.setVersion(version);
            
            return ResultTO.newSuccessResultTO("检查APP版本信息成功！", checkVersionResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("检查APP版本信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), CheckVersionRequestTO.toString() });
            return ResultTO.newFailResultTO("检查APP版本信息失败！", null);
        }
		
    }




}
