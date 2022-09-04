package com.bluemobi.controller.front.cas;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.page.Page;
import com.bluemobi.constant.RegionConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasConsignee;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.cas.CasConsigneeService;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.JedisUtil;



/**
 * 【用户收货地址】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
@Controller
@RequestMapping("front/cas/consignee")
public class CasConsigneeFrontController extends AbstractAPIController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CasConsigneeFrontController.class);
    
    @Autowired
    private CasConsigneeService casConsigneeService;
    @Autowired
    private TrendRegionService trendRegionService;
    @Autowired
    private JedisPoolManager jedisPoolManager;

    
    /**
     * 初始化收货地址页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String consignee(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        
        return "front/cas/consignee.index";
    }
    
    /**
     * 在订单核对时初始化地区列表
     * @author HeWW
     * 2016-2-1
     * @param model
     * @return
     */
    @RequestMapping(value = "changeAddress", method = RequestMethod.GET)
    public String changeAddAddress(Model model,String price) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getCasUser().getUserid());
        List<TrendRegion> consignee = casConsigneeService.selectObjectList(map);
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("consignee", consignee);
        //modify by guoxue 2016-06-24 begin
        model.addAttribute("price", price);
        //modify by guoxue 2016-06-24 end
        return "front/bts/consignee.change.address";
    }
    /**
     * 在订单核对时添加地区
     * @author HeWW
     * 2016-2-1
     * @param model
     * @return
     */
    @RequestMapping(value = "addAddress", method = RequestMethod.GET)
    public String addAddress(Model model) {
      //初始化省市数据
        Map<String,Object> regionMap = new HashMap<String, Object>();
        regionMap.put("pid", 3743);//中国id为：2743
        List<TrendRegion> region = trendRegionService.selectObjectList(regionMap);
        
        model.addAttribute("region",region);
        model.addAttribute("loggedInUser", getCasUser());
        
        return "front/bts/consignee.add.address";
    }
    
    /**
     * 订单核对时修改发票信息
     * @author HeWW
     * 2016-2-1
     * @param model
     * @return
     */
    @RequestMapping(value = "editInvoice", method = RequestMethod.GET)
    public String editInvoice(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        
        return "front/bts/consignee.edit.invoice";
    }
    
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageConsignee(int pageSize, int pageIndex) {
      //1,根据用户名模糊分页查询用户列表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getCasUser().getUserid());
        Page<Map<String, Object>> pages = casConsigneeService.page(map, pageIndex, pageSize);
        
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", pages.getData());
        mapResult.put("count", pages.getCount());
        return mapResult;
    }
    
    /**
     * 初始化 添加收货地址页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String consigneeAdd(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        //初始化省市数据
        Map<String,Object> regionMap = new HashMap<String, Object>();
        regionMap.put("pid", 3743);//中国id为：2743
        List<TrendRegion> region = trendRegionService.selectObjectList(regionMap);
        
        model.addAttribute("region",region);
        return "front/cas/consignee.edit";
    }
    
    /**
     * 保存用户收货地址
     * @author HeWW
     * 2016-1-19
     * @param consignee
     * @param result
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveConsignee(CasConsignee consignee) {
        Jedis jedis = null;
        try {
            jedis = jedisPoolManager.getResource();
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            reqMap.put("isDefault", (byte)1);
            List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(reqMap);
            //设置默认收货地址
            if (consigneeList.size() == 0) {
                consignee.setIsDefault((byte)1);
            }else {
                consignee.setIsDefault((byte)0);
            }
            //从缓存中获得数据
            String regionsName = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, consignee.getRegionId() + "", String.class);
            
            consignee.setRegionName(regionsName);
            //设置默认值
            consignee.setUserid(getCasUser().getUserid());
            consignee.setCtime(new Date());
            consignee.setMtime(new Date());
            consignee.setStatus((byte) 1);
            
            //持久化数据
            casConsigneeService.insert(consignee);
            //modify by guoxue 2016-06-23 begin
            return ResultTO.newSuccessResultTO(" 添加用户收货地址 - 成功", consignee);
          //modify by guoxue 2016-06-23 end
        } catch (Exception e) {
            return ResultTO.newFailResultTO("添加收货地址失败！", null);
        }finally{
            jedisPoolManager.returnResourceObject(jedis);
        }
        
    }
    
    /**
     * 初始化 修改收货地址页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editConsignee(Integer consigneeId , Model model) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("consigneeId", consigneeId);
        CasConsignee consignee = casConsigneeService.selectObject(reqMap);
        
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("consignee", consignee);
        
        return "front/cas/consignee.edit";
    }
    
    /**
     * 修改保存用户收货地址
     * @author HeWW
     * 2016-1-19
     * @param consignee
     * @param result
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveEditConsignee(CasConsignee consignee) {
        //设置默认值
        consignee.setUserid(getCasUser().getUserid());
        consignee.setMtime(new Date());
        
        //持久化数据
        casConsigneeService.update(consignee);
        return ResultTO.newSuccessResultTO(" 修改用户收货地址 - 成功", null);
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteConsignee(Integer consigneeId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("consigneeId", consigneeId);
        //删除用户收货地址
        casConsigneeService.delete(reqMap);
        
        return ResultTO.newSuccessResultTO(" 修改用户收货地址 - 成功", null);
    }
    
    //add by guoxue 2016-6-23 begin
    
    /**
     * 通过region_id 获取运费
     * @author guoxue
     * 2016-6-22
     * @param model
     * @param regionId
     * @return
     */
    @RequestMapping(value = "checkExpressFee", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO checkExpressFee(Model model,String regionId,String price) {
        TrendRegion trendRegion = trendRegionService.selectObject(regionId);
        String expressFee = "";
        double totlePrice = Double.parseDouble(price);
        Integer ef = 0;
        if(trendRegion == null){
        	 expressFee = "0.00";
        }else{
        	 if(trendRegion.getExpressFee() == 0){
           	  expressFee = "0.00";
           }else{
           	expressFee =trendRegion.getExpressFee().toString() + ".00";
           	ef = trendRegion.getExpressFee();
           }
        }
        double allTotalPrice = totlePrice + ef;
        String AllTotalPrice = allTotalPrice + "";
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("expressFee", expressFee);
        reqMap.put("allTotalPrice", AllTotalPrice);
        return ResultTO.newSuccessResultTO(reqMap);
    }
    //add by guoxue 2016-6-23 end
}
