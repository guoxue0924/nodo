package com.bluemobi.controller.api.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.util.JsonUtil;
import com.bluemobi.apito.GoodsCategoryTO;
import com.bluemobi.apito.goods.GetGoodsCategoryRequestTO;
import com.bluemobi.apito.goods.GetGoodsCategoryResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 【商品分类】移动控制器
 * 
 * @author zhangzheng
 * @date 2016-2-29
 * 
 */
@Controller(value = "goodsCategoryAPIController")
@RequestMapping("api/goodsCategory")
public class GoodsCategoryAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryAPIController.class);

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 获取商品分类
     * 
     * @auther zhangzheng
     * @date 2016-2-29 上午11:08:50
     * @param request
     * @param getGoodsCategoryRequestTO
     * @return
     */
    @RequestMapping(value = "getGoodsCategory")
    @ResponseBody
    public ResultTO getGoodsCategory(HttpServletRequest request, String json) {
        GetGoodsCategoryRequestTO getGoodsCategoryRequestTO = JsonUtil.getObject(json, GetGoodsCategoryRequestTO.class);
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getGoodsCategoryRequestTO.toString() });
        List<GoodsCategoryTO> categories = null;
        GetGoodsCategoryResponseTO getGoodsCategoryResponseTO = new GetGoodsCategoryResponseTO();
        try {
            categories = goodsCategoryService.selectObjectListByRedis();
            getGoodsCategoryResponseTO.setGoodsCategory(categories);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取商品分类出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getGoodsCategoryRequestTO.toString() });
            return ResultTO.newFailResultTO("获取商品分类失败！", null);
        }
        return ResultTO.newSuccessResultTO("获取商品分类成功！", getGoodsCategoryResponseTO);
    }

}
