package com.bluemobi.controller.front.bts;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.service.bts.BtsCartService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.to.ResultTO;


/**
 * Web端购物车
 * @author heweiwen
 * 2016-1-26 下午5:02:46
 */
@Controller
@RequestMapping("front/cart")
public class BtsFrontCartController extends AbstractAPIController{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BtsFrontCartController.class);
    
    @Autowired
    private BtsCartService btsCartService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    /**
     * 进入购物车主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-01-21 16:56:24
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        int userid = this.getUserid();
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid", userid);
        List<Map<String, Object>> list = btsCartService.selectMapList(map);
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        double countPrice = 0.00;
        for (Map<String, Object> data:list) {
        	if(data.get("skuId") !=null){
        		int skuId = Integer.parseInt(String.valueOf(data.get("skuId")));
        		
        		Map<String, Object> cartMap = new HashMap<String, Object>();
        		cartMap.putAll(data);
        		GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId);
        		//将购物车商品数量转换成BigDecimal 进行小计价格技术
        		if(goodsSku !=null){
        			BigDecimal quantity = new BigDecimal((Short)data.get("quantity"));
        			double subPrice = goodsSku.getPrice().multiply(quantity).doubleValue();
        			countPrice += subPrice;
        			
        			cartMap.put("contentId", goodsSku.getContentId());
        			cartMap.put("price", goodsSku.getPrice());
        			cartMap.put("subPrice",subPrice);
        			cartMap.put("name",goodsSku.getName());
        			cartMap.put("DefaultImage", goodsSku.getImages().size() > 0 ? goodsSku.getImages().get(0) : "");
        			resultList.add(cartMap);
        		}
        	}
        }
        
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("carts",resultList);
        model.addAttribute("price", countPrice);
        
        return "front/bts/cart.index";
    }
    
    /**
     * 购物车添加商品
     * @author haojian
     * @date 2015-10-14 上午11:30:29 
     * @param request
     * @param skuId 商品skuid
     * @return
     * @return ResultTO
     */
    @RequestMapping(value = "addToCart" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addToCart(Long skuId,int quantity) {
        
        int userid = this.getUserid();
        
        BtsCart btsCart = btsCartService.addGoodsToCart(userid, skuId);
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid ", btsCart.getUserid());
        map.put("skuId ", btsCart.getSkuId());
        map.put("quantity ", btsCart.getQuantity());
        
        LOGGER.info("用户【{}】添加商品【{}】到购物车，购物车信息【{}】", new Object[]{userid, skuId, btsCart.toString()} );
        
        return ResultTO.newSuccessResultTO("添加购物车成功", map);
        
    }
    
    /**
     * 获取购物车信息
     * @author HeWW
     * 2016-1-26
     * @return
     */
    @RequestMapping(value = "getFrontCart" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getFrontCart() {
        
        int userid = this.getUserid();
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid", userid);
        List<Map<String, Object>> list = btsCartService.selectMapList(map);
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        double countPrice = 0.00;
        int count = 0;
        for (Map<String, Object> data:list) {
        	//modify by fxz 2016-07-11 begin
        	if(String.valueOf(data.get("skuId")) !=null && String.valueOf(data.get("skuId")) != ""){
        		int skuId = Integer.parseInt(String.valueOf(data.get("skuId")));
        		
        		Map<String, Object> cartMap = new HashMap<String, Object>();
        		cartMap.putAll(data);
        		GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId);
        		//将购物车商品数量转换成BigDecimal 进行小计价格技术
        		if(goodsSku != null){
        			BigDecimal quantity = new BigDecimal((Short)data.get("quantity"));
        			double subPrice = goodsSku.getPrice().multiply(quantity).doubleValue();
        			countPrice += subPrice;
        			
        			cartMap.put("contentId", goodsSku.getContentId());
        			cartMap.put("price", goodsSku.getPrice());
        			cartMap.put("subPrice",subPrice);
        			cartMap.put("name",goodsSku.getName());
        			cartMap.put("DefaultImage", goodsSku.getImages().size() > 0 ?goodsSku.getImages().get(0):"");
        			resultList.add(cartMap);
        			count ++;
        		}
        	}
        	//modify by fxz 2016-07-11 end
        }
        LOGGER.info("用户【{}】的【{}】", new Object[]{userid, list} );
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("carts", resultList);
        resultMap.put("count", count);
        resultMap.put("totalprice", countPrice);
        
        return ResultTO.newSuccessResultTO("获取购物车信息成功", resultMap);
        
    }
   
    
    /**
     * 删除购物车商品
     * @author haojian
     * @date 2015-10-14 下午5:26:02 
     * @param request
     * @param cartIds
     * @return
     * @return ResultTO
     */
    @RequestMapping(value = "deleteBtsCartByCartId" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteBtsCartByCartId(Integer cartId) {
        
        int userid = this.getUserid();
        //1，根据ID，删除购物信息
        btsCartService.delete(cartId);
        
        //2，查询购物车是否为空
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid", userid);
        List<Map<String, Object>> list = btsCartService.selectMapList(map);
        int quantity = 0;
        if (list.size()>0) {
            quantity = 1;
        }
       
        LOGGER.info("用户【{}】的购物车信息【{}】删除成功", new Object[]{userid, cartId} );
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("quantity", quantity);
        
        return ResultTO.newSuccessResultTO("删除购物车信息成功", resultMap);
    }
    
    /**
     * 删除购物车中的商品
     * @param cartId
     * @param skuId
     * @return
     * @return ResultTO
     */
    @RequestMapping(value = "deleteGoodsFromCart" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteGoodsFromCart(Integer cartId,Integer skuId) {
        int userid = this.getUserid();
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("userid",getUserid());
        
        //删除购物车信息
        btsCartService.delete(cartId);
        //查询购物车信息
        List<BtsCart> btsCartList = btsCartService.selectObjectList(reqMap);
        int count = 0;
        double subPrice = 0.0;
        for (BtsCart data:btsCartList) {
            Map<String, Object> reqContentMap = new HashMap<String, Object>();
            reqContentMap.put("skuId", data.getSkuId());
            //查询商品信息
            GoodsContentSku goodsContentSku = goodsContentSkuService.selectObject(reqContentMap);
            //计算价格
            BigDecimal quantity = new BigDecimal((Short)data.getQuantity());
            subPrice = goodsContentSku.getPrice().multiply(quantity).doubleValue();
            count++;
        }
        
        LOGGER.info("用户【{}】的购物车信息【{}】删除成功", new Object[]{userid, cartId} );
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("count", count);
        resultMap.put("totalprice", subPrice);
        
        return ResultTO.newSuccessResultTO("删除购物车信息成功", resultMap);
        
    }
    
    /**
     * 修改购物车
     * @author haojian
     * @date 2015-10-14 下午5:34:18 
     * @param request
     * @param cartId
     * @param quantity
     * @return
     * @return ResultTO
     */
    @RequestMapping(value = "updateBtsCart" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateBtsCart(Integer cartId, Integer quantity,Integer skuId) {
        
        int userid = this.getUserid();
        //1，根据购物车ID,修改购物车信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid", userid);
        map.put("cartId", cartId);
        map.put("quantity", quantity);
        btsCartService.update(map);
        
        //2，通过ID获得购物车信息和商品详情信息
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("cartId", cartId);
        BtsCart resultCart = btsCartService.selectObject(map2);
        GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId);
   
        //3，计算商品数量的乘积价格
        BigDecimal cartQuantity = new BigDecimal((Short)resultCart.getQuantity());
        double subPrice = goodsSku.getPrice().multiply(cartQuantity).doubleValue();
        
        //4，返回数据组装
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("price", goodsSku.getPrice());
        resultMap.put("subprice", subPrice);
        
        LOGGER.info("用户【{}】的购物车信息【{}】修改成功，修改后为【{}】", new Object[]{userid, cartId, resultMap} );
        
        return ResultTO.newSuccessResultTO("修改购物车信息成功", resultMap);
        
    }
    


}
