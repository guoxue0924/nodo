package com.bluemobi.controller.back.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.context.AppContext;
import com.appcore.util.CookieUtil;
import com.appcore.util.UUIDService;
import com.bluemobi.ServerInit;
import com.bluemobi.constant.GoodsConstant;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.test.Test;
import com.bluemobi.po.test.Ticket;
import com.bluemobi.service.cas.CasUserService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.test.TestService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.JedisUtil;

/**
 * 测试
 * 
 * @author haojian
 * @date 2015-6-9 上午10:52:37
 * 
 */
@Controller
@RequestMapping("front/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    //实际使用时，请不要把 JedisPoolManager 注入到控制器中，应该注入到service中
    @Autowired 
    private JedisPoolManager jedisPoolManager;
    
    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private CasUserService casUserService;
    

    
    /**
     * 登录
     * @author haojian
     * @date 2016-1-21 上午10:08:43 
     * @param model
     * @param userid
     * @return
     * @return String
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO login() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("username", "zhaolijie"); 
        //map.put("userGroupId", "1"); 
        //map.put("password", password); 
        CasUser casUser = casUserService.selectObject(map);
        System.out.println("casUser============"+casUser);
        
        ResultTO resultTO = ResultTO.newSuccessResultTO("success",casUser);
        
        return resultTO;
    }
    
    
    
    @RequestMapping(value = "buyTicket", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO buyTicket() {

        String msg = testService.buyTicket(1);

        LOGGER.info(msg);

        ResultTO resultTO = ResultTO.newSuccessResultTO("success",msg);
        
        return resultTO;
    }

    @RequestMapping(value = "getTicketInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO getTicketInfo() {

        Ticket ticket = testService.getTicketInfo();

        ResultTO resultTO = ResultTO.newSuccessResultTO("success", ticket);
       
        return resultTO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object test(HttpServletRequest request, HttpServletResponse response, String cmd) {

        LOGGER.info("测试命令：【" + cmd + "】");
        
        Object obj = null;
        
        if ("writeCookie".equalsIgnoreCase(cmd)) {

            String path = request.getContextPath();
            System.out.println("path===="+path);
            CookieUtil.writeCookie(response, "bm_token1", UUIDService.getUUID(), 24 * 3600 * 3, "/");
            CookieUtil.writeCookie(response, "bm_token2", UUIDService.getUUID(), 24 * 3600 * 3, "/nodo");
            CookieUtil.writeCookie(response, "bm_token3", UUIDService.getUUID(), 24 * 3600 * 3, "/nodo/");
            CookieUtil.writeCookie(response, "bm_token4", UUIDService.getUUID(), 24 * 3600 * 3, "/nodo/back");
            CookieUtil.writeCookie(response, "bm_token5", UUIDService.getUUID(), 24 * 3600 * 3, "/nodo/back/");

        } else if ("printCookie".equalsIgnoreCase(cmd)) {
            Cookie[] cc = request.getCookies();
            for (Cookie c : cc) {
                LOGGER.info(c.getDomain() + " , " + c.getComment() + " , " + c.getPath() + " , " + c.getName() + " , " + c.getValue());
            }

        } else if ("removeCookie".equalsIgnoreCase(cmd)) {

            CookieUtil.removeCookie("bm_token", request, response);

        } else if ("testRedis".equalsIgnoreCase(cmd)) {
            
            Jedis jedis = null;
            try {
                jedis = jedisPoolManager.getResource();
                String contentId = request.getParameter("contentId");
                
                
                long b = System.currentTimeMillis();
                for(int i=0;i<1000;i++){
                    
                    //JedisUtil.putMainIdAndDetailIds(jedis, GoodsConstant.CACHE_GOODS_CONTENT_ID, goods.getContentId(), goods.getSkuIdList());
                    List<String> skuIdList = JedisUtil.getDetailIdsByMainId(jedis, GoodsConstant.CACHE_GOODS_CONTENT_ID, contentId); 
                    //JedisUtil.putObjectsToMap(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, skuMap);
                    List<GoodsContentSku> skuList = JedisUtil.getObjectsFromMapByIds(jedis, GoodsConstant.CACHE_GOODS_CONTENT_SKU_MAP, skuIdList, GoodsContentSku.class);
                    obj = skuList;
                }
                
                long e = System.currentTimeMillis();
                System.out.println("耗时："+(e-b)/1000d+"秒");
                
               
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("初始化商品sku表信息失败。Exception:【{}】", new Object[] { e });
            } finally {
                jedisPoolManager.returnResourceObject(jedis);
            }
            

        }  else if ("testRedis2".equalsIgnoreCase(cmd)) {   
            
            String contentId = request.getParameter("contentId");
            List<GoodsContentSku> skuList = null;
            try {
                long b = System.currentTimeMillis();
                for(int i=0;i<1;i++){
                    skuList = goodsContentSkuService.selectGoodsContentSkuListByContentId( Integer.parseInt(contentId) ); 
                }
                long e = System.currentTimeMillis();
                System.out.println("耗时："+(e-b)/1000d+"秒");
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            obj = skuList;

        } else if ("testNoCacheFromDB".equalsIgnoreCase(cmd)) {
            
            String contentId = request.getParameter("contentId");
            obj = goodsContentService.selectGoodsContent(Integer.valueOf(contentId));
            
        } else if ("init".equalsIgnoreCase(cmd)) {
            
            ServerInit serverInit = AppContext.getBean("serverInit");
            serverInit.init();
            
        }
        
        ResultTO resultTO = ResultTO.newSuccessResultTO("命令：【" + cmd + "】, 测试成功！", obj);
        
        return resultTO;
        
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    @ResponseBody
    public Object insert() {
        LOGGER.info("-----insert-----");
        for (int i = 10; i < 300; i++) {
            Test t = new Test();
            t.setName("hao-" + i);
            t.setAge(i % 30);
            t.setAddress("上海杨浦-" + i);
            testService.insert(t);
            if (i % 10000 == 0) {
                LOGGER.info(new Date() + "[" + i + "]");
            }
        }
        return "-----insert success-----";
    }
    
    @RequestMapping(value = "testTransaction", method = RequestMethod.GET)
    @ResponseBody
    public Object testTransaction(HttpServletRequest request) {
        
        boolean hasException = Boolean.valueOf(request.getParameter("hasException"));
        
        LOGGER.info("-----testTransaction-----");
        Test t = new Test();
        t.setId(1001);
        t.setAge(28);
        t.setAddress("shanghai");
        t.setName("hao");
        testService.testTransaction(t, hasException);
        
        return "-----insert success-----";
        
    }
    
    

    @RequestMapping(value = "update", method = RequestMethod.GET)
    @ResponseBody
    public Object update(HttpServletRequest request) {
        LOGGER.info("-----update-----");
        
        long sleepTime = Integer.valueOf(request.getParameter("sleepTime"))*1000L;
        
        int age = Integer.valueOf(request.getParameter("age"));
        
        Map<String, Integer> pMap = new HashMap<String, Integer>();
        pMap.put("id", 1);
        Test t = testService.selectObject(pMap);
        t.setName("hao-change");
        t.setAge(age);
        t.setAddress(Thread.currentThread().toString());
        testService.testUpdate(t, sleepTime);
        t = testService.selectObject(pMap);
        return t;
    }

    @RequestMapping(value = "selectObject", method = RequestMethod.GET)
    @ResponseBody
    public Object selectObject(HttpServletResponse response) {
        LOGGER.info("-----selectObject-----");
        Map<String, Integer> pMap = new HashMap<String, Integer>();
        pMap.put("id", 100);
        Test test = testService.selectObject(pMap);
        return test;
    }

    @RequestMapping(value = "selectObjectList", method = RequestMethod.GET)
    @ResponseBody
    public Object selectObjectList(HttpServletRequest request) {
        LOGGER.info("-----selectObjectList-----");
        Map<String, String> map = new HashMap<String, String>();
        map.put("nameNotNull", "hao-200015");
        List<Test> list = testService.selectObjectList(map);
        return list;
    }

    @RequestMapping(value = "selectMap", method = RequestMethod.GET)
    @ResponseBody
    public Object selectMap() {
        LOGGER.info("-----selectMap-----");
        Map<String, Integer> pMap = new HashMap<String, Integer>();
        pMap.put("id", 100);
        Map<String, Object> map = testService.selectMap(pMap);
        return map;
    }

    @RequestMapping(value = "selectMapList", method = RequestMethod.GET)
    @ResponseBody
    public Object selectMapList() {
        LOGGER.info("-----selectMapList-----");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", 1005);
        List<Map<String, Object>> list = testService.selectMapList(map);
        return list;
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public Object page(int pageIndex, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        return testService.page(map, pageIndex, pageSize);
    }
    
  
    
    
    

}
