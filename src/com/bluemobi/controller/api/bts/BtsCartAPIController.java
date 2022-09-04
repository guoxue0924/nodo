package com.bluemobi.controller.api.bts;

import java.math.BigDecimal;
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

import com.appcore.util.JsonUtil;
import com.bluemobi.apito.CartTO;
import com.bluemobi.apito.bts.AddToCartRequestTO;
import com.bluemobi.apito.bts.AddToCartResponseTO;
import com.bluemobi.apito.bts.DeleteCartRequestTO;
import com.bluemobi.apito.bts.DeleteCartResponseTO;
import com.bluemobi.apito.bts.GetCartRequestTO;
import com.bluemobi.apito.bts.GetCartResponseTO;
import com.bluemobi.apito.bts.MarkCartRequestTO;
import com.bluemobi.apito.bts.MarkCartResponseTO;
import com.bluemobi.apito.bts.ModifyCartRequestTO;
import com.bluemobi.apito.bts.ModifyCartResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.bts.BtsCartMark;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.service.bts.BtsCartMarkService;
import com.bluemobi.service.bts.BtsCartService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.to.ResultTO;

/**
 * 【购物车】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-27 14:22:38
 * 
 */
@Controller(value = "btsCartAPIController")
@RequestMapping("api/btsCart")
public class BtsCartAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BtsCartAPIController.class);

    @Autowired
    private BtsCartService btsCartService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private BtsCartMarkService btsCartMarkService;
    
	/**
	 * 获取购物车信息
     * @param request
     * @param getCartRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-01-27 14:22:38
	 */
    @RequestMapping(value = "getCart")
    @ResponseBody
    public ResultTO getCart(HttpServletRequest request, String json ) {
        
        GetCartRequestTO getCartRequestTO = JsonUtil.getObject(json, GetCartRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getCartRequestTO.toString() });
        
        try{
            //处理业务
            int userid = this.getUserid();
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userid", userid);
            List<BtsCart> cartList  = btsCartService.selectObjectList(map);
            //数据转换
            GetCartResponseTO getCartResponseTO = new GetCartResponseTO();
            List<CartTO> carts = new ArrayList<CartTO>();
            double countPrice = 0.00;
            for (BtsCart data:cartList) {
                GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(data.getSkuId());
                CartTO cartTO = new CartTO();
                cartTO.setSkuId(data.getSkuId().intValue());
                cartTO.setCartId(data.getCartId());
                cartTO.setName(goodsSku.getName());
                cartTO.setPrice(goodsSku.getPrice().floatValue());
                cartTO.setQuantity(data.getQuantity().intValue());
                cartTO.setImage(goodsSku.getImages().size() > 0 ?goodsSku.getImages().get(0):"");
                
                //查询是否勾选
                map.put("cartId", data.getCartId());
                List<BtsCartMark> markCartList = btsCartMarkService.selectObjectList(map);
                if (!markCartList.isEmpty()) {
                    cartTO.setIsMark(1);//1,勾选；2，去除勾选
                    //计算价格
                    BigDecimal quantityData = new BigDecimal((Short)data.getQuantity());
                    double subPrice = goodsSku.getPrice().multiply(quantityData).doubleValue();
                    countPrice += subPrice;
                }else {
                    cartTO.setIsMark(0);
                }
                carts.add(cartTO);
            }
            getCartResponseTO.setCarts(carts);
            getCartResponseTO.setCountPrice(new BigDecimal(countPrice));
            
            return ResultTO.newSuccessResultTO("获取购物车信息成功！", getCartResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取购物车信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getCartRequestTO.toString() });
            return ResultTO.newFailResultTO("获取购物车信息失败！", null);
        }
		
    }

	/**
	 * 修改购物车信息
     * @param request
     * @param modifyCartRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-01-27 14:22:38
	 */
    @RequestMapping(value = "modifyCart")
    @ResponseBody
    public ResultTO modifyCart(HttpServletRequest request, String json ) {
        
        ModifyCartRequestTO modifyCartRequestTO = JsonUtil.getObject(json, ModifyCartRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), modifyCartRequestTO.toString() });
        
        try{
            //根据购物车ID查询数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("cartId", modifyCartRequestTO.getCartId());
            BtsCart cart  = btsCartService.selectObject(map);
            //修改数据
            int quantity = cart.getQuantity() + modifyCartRequestTO.getQuantity();
            cart.setQuantity((short)quantity);
            btsCartService.update(cart);
            
            ModifyCartResponseTO modifyCartResponseTO = new ModifyCartResponseTO();
            
            return ResultTO.newSuccessResultTO("修改购物车信息成功！", modifyCartResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("修改购物车信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), modifyCartRequestTO.toString() });
            return ResultTO.newFailResultTO("修改购物车信息失败！", null);
        }
		
    }

	/**
	 * 删除购物车信息
     * @param request
     * @param deleteCartRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-01-27 14:22:38
	 */
    @RequestMapping(value = "deleteCart")
    @ResponseBody
    public ResultTO deleteCart(HttpServletRequest request, String json) {
        
        DeleteCartRequestTO deleteCartRequestTO = JsonUtil.getObject(json, DeleteCartRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), deleteCartRequestTO.toString() });
        
        try{
            //删除购物车数据
            btsCartService.delete(deleteCartRequestTO.getCartId());
            
            DeleteCartResponseTO deleteCartResponseTO = new DeleteCartResponseTO();
            return ResultTO.newSuccessResultTO("删除购物车信息成功！", deleteCartResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("删除购物车信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), deleteCartRequestTO.toString() });
            return ResultTO.newFailResultTO("删除购物车信息失败！", null);
        }
		
    }


    /**
     * 购物车添加商品
     * @author HeWW
     * 2016-2-25
     * @param request
     * @param addToCartRequestTO
     * @return
     */
    @RequestMapping(value = "addToCart")
    @ResponseBody
    public ResultTO addToCart(HttpServletRequest request, String json) {
        
        AddToCartRequestTO addToCartRequestTO = JsonUtil.getObject(json, AddToCartRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), addToCartRequestTO.toString() });
        
        try{
            int userid = this.getUserid();
            
            BtsCart btsCart = btsCartService.addGoodsToCart(userid, addToCartRequestTO.getSkuId());
            
            AddToCartResponseTO addToCartResponseTO = new AddToCartResponseTO();
            
            return ResultTO.newSuccessResultTO("添加商品到购物车信息成功！", addToCartResponseTO);
        
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("添加商品到购物车信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), addToCartRequestTO.toString() });
            return ResultTO.newFailResultTO("添加商品到购物车信息失败！", null);
        }
        
    }
    
    /**
     * 勾选购物车商品
     * @author HeWW
     * 2016-3-28
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(value = "markCart")
    @ResponseBody
    public ResultTO markCart(HttpServletRequest request, String json) {
        
        MarkCartRequestTO markCartRequestTO = JsonUtil.getObject(json, MarkCartRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), markCartRequestTO.toString() });
        
        try{
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            reqMap.put("cartId", markCartRequestTO.getCartId());
            List<BtsCartMark> cartMarkList = btsCartMarkService.selectObjectList(reqMap);
            
            if (markCartRequestTO.getIsMark() == 0 && !cartMarkList.isEmpty()) {
                btsCartMarkService.delete(cartMarkList.get(0).getCartMarkId());
            }else if (markCartRequestTO.getIsMark() == 1) {
                BtsCartMark cartMark = new BtsCartMark();
                cartMark.setCartId(markCartRequestTO.getCartId());
                cartMark.setMtime(new Date());
                cartMark.setUserid(getUserid());
                btsCartMarkService.insert(cartMark);
            }else {
                return ResultTO.newFailResultTO(" 没有该购物车商品信息！", null);
            }
            //返回值对象
            MarkCartResponseTO markCartResponseTO = new MarkCartResponseTO();
            return ResultTO.newSuccessResultTO(" 勾选购物车商品成功！", markCartResponseTO);
        
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error(" 勾选购物车商品出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), markCartRequestTO.toString() });
            return ResultTO.newFailResultTO(" 勾选购物车商品失败！", null);
        }
        
    }
    
}
