package com.bluemobi.controller.api.cas;

import java.util.ArrayList;
import java.util.Date;
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

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.util.JsonUtil;
import com.bluemobi.apito.ConsigneeTO;
import com.bluemobi.apito.cas.AddConsigneeRequestTO;
import com.bluemobi.apito.cas.AddConsigneeResponseTO;
import com.bluemobi.apito.cas.GetDefaultConsigneeRequestTO;
import com.bluemobi.apito.cas.GetDefaultConsigneeResponseTO;
import com.bluemobi.apito.cas.GetConsigneesRequestTO;
import com.bluemobi.apito.cas.GetConsigneesResponseTO;
import com.bluemobi.apito.cas.SetDefaultConsigneeRequestTO;
import com.bluemobi.apito.cas.SetDefaultConsigneeResponseTO;
import com.bluemobi.apito.cas.ModifyConsigneeRequestTO;
import com.bluemobi.apito.cas.ModifyConsigneeResponseTO;
import com.bluemobi.apito.cas.DeleteConsigneeRequestTO;
import com.bluemobi.apito.cas.DeleteConsigneeResponseTO;
import com.bluemobi.constant.RegionConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasConsignee;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.cas.CasConsigneeService;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.JedisUtil;

/**
 * 【收货地址】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 17:02:45
 * 
 */
@Controller(value = "casUserConsigneeAPIController")
@RequestMapping("api/casUserConsignee")
public class CasUserConsigneeAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserConsigneeAPIController.class);

    @Autowired
    private CasConsigneeService casConsigneeService;
    @Autowired
    private JedisPoolManager jedisPoolManager;

	/**
	 * 添加收货地址
     * @param request
     * @param addConsigneeRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "addConsignee")
    @ResponseBody
    public ResultTO addConsignee(HttpServletRequest request,  String json) {
        
        AddConsigneeRequestTO addConsigneeRequestTO = JsonUtil.getObject(json, AddConsigneeRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), addConsigneeRequestTO.toString() });
        
        Jedis jedis = null;
        
        try{
            jedis = jedisPoolManager.getResource();
            
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            reqMap.put("isDefault", (byte)1);
            List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(reqMap);
            CasConsignee consignee = new CasConsignee();
            //数据转换
            if (consigneeList.size() == 0) {
                consignee.setIsDefault((byte)1);
            }else {
                consignee.setIsDefault((byte)0);
            }
            //处理业务
           
            consignee.setAddress(addConsigneeRequestTO.getAddress());
            consignee.setMobile(addConsigneeRequestTO.getMobilePhone());
            consignee.setName(addConsigneeRequestTO.getName());
            
            consignee.setRegionId(addConsigneeRequestTO.getRegionId());
            //从缓存中获得数据
            String regionsName = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, addConsigneeRequestTO.getRegionId() + "", String.class);
            
            consignee.setRegionName(regionsName);
            //设置默认值
            consignee.setUserid(getUserid());
            consignee.setCtime(new Date());
            consignee.setMtime(new Date());
            consignee.setStatus((byte) 1);
            
            //持久化数据
            casConsigneeService.insert(consignee);
            
            AddConsigneeResponseTO addConsigneeResponseTO = new AddConsigneeResponseTO();
            return ResultTO.newSuccessResultTO("添加收货地址成功！", addConsigneeResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("添加收货地址出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), addConsigneeRequestTO.toString() });
            return ResultTO.newFailResultTO("添加收货地址失败！", null);
        }finally{
            jedisPoolManager.returnResourceObject(jedis);
        }
        
    }

	/**
	 * 获取默认收货地址
     * @param request
     * @param getDefaultConsigneeRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "getDefaultConsignee")
    @ResponseBody
    public ResultTO getDefaultConsignee(HttpServletRequest request, String json ) {
        GetDefaultConsigneeRequestTO getDefaultConsigneeRequestTO = JsonUtil.getObject(json, GetDefaultConsigneeRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getDefaultConsigneeRequestTO.toString() });
        
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            //根据用户ID获得-
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            reqMap.put("isDefault", (byte)1);
            List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(reqMap);
            CasConsignee consignee = null;
            if (consigneeList.size() > 0) {
                consignee = consigneeList.get(0);
            }else {
                return ResultTO.newFailResultTO("获取默认收货地址失败！无默认地址", null);
            }
            
            //返回数据转换
            GetDefaultConsigneeResponseTO getDefaultConsigneeResponseTO = new GetDefaultConsigneeResponseTO();
            ConsigneeTO consigneeTO = new ConsigneeTO();
            consigneeTO.setAddress(consignee.getAddress());
            consigneeTO.setConsigneeId(consignee.getConsigneeId());
            consigneeTO.setIsDefault(consignee.getIsDefault().intValue());
            consigneeTO.setMobilePhone(consignee.getMobile());
            consigneeTO.setName(consignee.getName());
            consigneeTO.setRegionId3(consignee.getRegionId());
            //从缓存中获得1，2级的regionId
            String parentStr3 = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_ALONE_MAP, consignee.getRegionId() + "", String.class);
            TrendRegion parent3 = JsonUtil.getObject(parentStr3, TrendRegion.class);
            consigneeTO.setRegionId2(parent3.getPid());
            String parentStr2 = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_ALONE_MAP, parent3.getPid() + "", String.class);
            TrendRegion parent2 = JsonUtil.getObject(parentStr2, TrendRegion.class);
            consigneeTO.setRegionId1(parent2.getPid());
            //从缓存中获得数据
            String regionsName = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, consignee.getRegionId() + "", String.class);
            
            consigneeTO.setRegionName(regionsName);
            
            getDefaultConsigneeResponseTO.setConsignee(consigneeTO);
            
            return ResultTO.newSuccessResultTO("获取默认收货地址成功！", getDefaultConsigneeResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取默认收货地址出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getDefaultConsigneeRequestTO.toString() });
            return ResultTO.newFailResultTO("获取默认收货地址失败！", null);
        }finally{
            jedisPoolManager.returnResourceObject(jedis);
        }
        
    }

	/**
	 * 获取收货地址列表
     * @param request
     * @param getConsigneesRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "getConsignees")
    @ResponseBody
    public ResultTO getConsignees(HttpServletRequest request, String json) {

        GetConsigneesRequestTO getConsigneesRequestTO = JsonUtil.getObject(json, GetConsigneesRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getConsigneesRequestTO.toString() });
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            //根据用户ID获得地区列表
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(reqMap);
            
            //数据组合
            GetConsigneesResponseTO getConsigneesResponseTO = new GetConsigneesResponseTO();
            List<ConsigneeTO> consignees = new ArrayList<ConsigneeTO>();
            for (CasConsignee data:consigneeList) {
                ConsigneeTO consigneeTO = new ConsigneeTO();
                consigneeTO.setAddress(data.getAddress());
                consigneeTO.setConsigneeId(data.getConsigneeId());
                consigneeTO.setIsDefault(data.getIsDefault().intValue());
                consigneeTO.setMobilePhone(data.getMobile());
                consigneeTO.setName(data.getName());
                consigneeTO.setRegionId3(data.getRegionId());
                //从缓存中获得1，2级的regionId
                String parentStr3 = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_ALONE_MAP, data.getRegionId() + "", String.class);
                TrendRegion parent3 = JsonUtil.getObject(parentStr3, TrendRegion.class);
                consigneeTO.setRegionId2(parent3.getPid());
                String parentStr2 = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_ALONE_MAP, parent3.getPid() + "", String.class);
                TrendRegion parent2 = JsonUtil.getObject(parentStr2, TrendRegion.class);
                consigneeTO.setRegionId1(parent2.getPid());
                //从缓存中获得数据
                String regionsName = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, data.getRegionId() + "", String.class);
                
                consigneeTO.setRegionName(regionsName);
                
                consignees.add(consigneeTO);
            }
            getConsigneesResponseTO.setConsignees(consignees);
            
            return ResultTO.newSuccessResultTO("获取收货地址列表成功！", getConsigneesResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取收货地址列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getConsigneesRequestTO.toString() });
            return ResultTO.newFailResultTO("获取收货地址列表失败！", null);
        }finally{
            jedisPoolManager.returnResourceObject(jedis);
        }
        
    }

	/**
	 * 设置默认收货地址
     * @param request
     * @param setDefaultConsigneeRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "setDefaultConsignee")
    @ResponseBody
    public ResultTO setDefaultConsignee(HttpServletRequest request, String json) {
        
        SetDefaultConsigneeRequestTO setDefaultConsigneeRequestTO = JsonUtil.getObject(json, SetDefaultConsigneeRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), setDefaultConsigneeRequestTO.toString() });
        
        try{
            //根据用户ID获得老的默认地址
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            reqMap.put("isDefault", (byte)1);
            List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(reqMap);
            if (!consigneeList.isEmpty()) {
                CasConsignee consigneeOld = consigneeList.get(0);
                //将老的默认地址设置为非默认地址
                consigneeOld.setIsDefault((byte)0);
                casConsigneeService.update(consigneeOld);
            }
            
            //根据地址ID查询地区对象
            Map<String, Object> reqConsigneeMap = new HashMap<String, Object>();
            reqConsigneeMap.put("consigneeId", setDefaultConsigneeRequestTO.getConsigneeId());
            CasConsignee consigneeNew = casConsigneeService.selectObject(reqConsigneeMap);
            //设置新的默认地址
            consigneeNew.setIsDefault((byte)1);
            casConsigneeService.update(consigneeNew);
            
            SetDefaultConsigneeResponseTO setDefaultConsigneeResponseTO = new SetDefaultConsigneeResponseTO();
            return ResultTO.newSuccessResultTO("设置默认收货地址成功！", setDefaultConsigneeResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("设置默认收货地址出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), setDefaultConsigneeRequestTO.toString() });
            return ResultTO.newFailResultTO("设置默认收货地址失败！", null);
        }
        
    }

	/**
	 * 修改收货地址
     * @param request
     * @param modifyConsigneeRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "modifyConsignee")
    @ResponseBody
    public ResultTO modifyConsignee(HttpServletRequest request, String json ) {
        
        ModifyConsigneeRequestTO modifyConsigneeRequestTO = JsonUtil.getObject(json, ModifyConsigneeRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), modifyConsigneeRequestTO.toString() });
        
        Jedis jedis = null;
        
        try{
            jedis = jedisPoolManager.getResource();
            //修改用户地区信息
            Map<String, Object> reqConsigneeMap = new HashMap<String, Object>();
            reqConsigneeMap.put("consigneeId", modifyConsigneeRequestTO.getConsigneeId());
            CasConsignee consignee = casConsigneeService.selectObject(reqConsigneeMap);
            //数据转换
            consignee.setAddress(modifyConsigneeRequestTO.getAddress());
            consignee.setMobile(modifyConsigneeRequestTO.getMobilePhone());
            consignee.setName(modifyConsigneeRequestTO.getName());
            consignee.setRegionId(modifyConsigneeRequestTO.getRegionId());
            
            //从缓存中获得数据
            String regionsName = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, modifyConsigneeRequestTO.getRegionId() + "", String.class);
            
            consignee.setRegionName(regionsName);
            
            consignee.setMtime(new Date());
            
            //修改数据
            casConsigneeService.update(consignee);
            
            ModifyConsigneeResponseTO modifyConsigneeResponseTO = new ModifyConsigneeResponseTO();
            return ResultTO.newSuccessResultTO("修改收货地址成功！", modifyConsigneeResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("修改收货地址出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), modifyConsigneeRequestTO.toString() });
            return ResultTO.newFailResultTO("修改收货地址失败！", null);
        }finally{
            jedisPoolManager.returnResourceObject(jedis);
        }
        
    }

	/**
	 * 删除收货地址
     * @param request
     * @param deleteConsigneeRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "deleteConsignee")
    @ResponseBody
    public ResultTO deleteConsignee(HttpServletRequest request, String json ) {
        
        DeleteConsigneeRequestTO deleteConsigneeRequestTO = JsonUtil.getObject(json, DeleteConsigneeRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), deleteConsigneeRequestTO.toString() });
        
        try{
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("consigneeId", deleteConsigneeRequestTO.getConsigneeId());
            CasConsignee consignee = casConsigneeService.selectObject(reqMap);
            if (consignee == null) {
                return ResultTO.newFailResultTO("没有该地址", null);
            }
            //删除地址
            casConsigneeService.delete(deleteConsigneeRequestTO.getConsigneeId());
            //判断是否是删除的默认地址，如果是就默认设置一个默认地址
            if (consignee.getIsDefault() == 1) {
                Map<String, Object> allMap = new HashMap<String, Object>();
                List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(allMap);
                if (!consigneeList.isEmpty()) {
                    CasConsignee consigneeNew = consigneeList.get(0);
                    consigneeNew.setIsDefault((byte)1);
                    casConsigneeService.update(consigneeNew);
                }
            }
            
            DeleteConsigneeResponseTO deleteConsigneeResponseTO = new DeleteConsigneeResponseTO();
            return ResultTO.newSuccessResultTO("删除收货地址成功！", deleteConsigneeResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("删除收货地址出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), deleteConsigneeRequestTO.toString() });
            return ResultTO.newFailResultTO("删除收货地址失败！", null);
        }
        
    }




}
