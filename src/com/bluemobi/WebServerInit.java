package com.bluemobi;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appcore.context.AppContext;
import com.bluemobi.conf.Config;
import com.bluemobi.po.goods.GoodsBrand;
import com.bluemobi.po.goods.GoodsCategory;
import com.bluemobi.service.goods.GoodsBrandService;
import com.bluemobi.service.goods.GoodsCategoryService;

/**
 * 初始化缓存数据等
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-12-15 下午3:42:16
 * 
 */
@Component(value = "webServerInit")
public class WebServerInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServerInit.class);

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsBrandService goodsBrandService;

    public boolean init(ServletContext context) {

        // beanutils 初始化设置
        ConvertUtils.register(new org.apache.commons.beanutils.converters.DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), java.math.BigDecimal.class);

        //1、设置全局参数
        LOGGER.info("开始初始化【商品属性和属性值】...");
        long b = System.currentTimeMillis();
        context.setAttribute("BASE_URL",  Config.BASE_URL);
        context.setAttribute("STATIC_URL", Config.STATIC_URL);
        context.setAttribute("IMG_URL", Config.IMG_URL);
        context.setAttribute("SITE_NAME", Config.SITE_NAME);
        context.setAttribute("TEMP_IMG_URL", Config.TEMP_IMG_URL);
        long e = System.currentTimeMillis();
        LOGGER.info("初始化结束【商品属性和属性值】成功...耗时【{}】秒", new Object[] { (e - b) / 1000d });
        
        //2、初始化商品分类
        List<GoodsCategory> categories = goodsCategoryService.loadCategories();
        context.setAttribute("LOAD_CATEGORIES", categories);
        
        //3、初始化商品品牌
        List<GoodsBrand> brands = goodsBrandService.loadGoodsBrands();
        context.setAttribute("LOAD_BRANDS", brands);
        
        //获取目录对应的品牌
        List<Map<String, Object>> BrandsbyCategory = goodsBrandService.loadGoodsBrandsbyCategory();
        context.setAttribute("BrandsbyCategory", BrandsbyCategory);
        
        //4、初始化数据字典
        DataDictionaryInit dataDictionaryInit = AppContext.getBean("dataDictionaryInit");
        dataDictionaryInit.init();
        context.setAttribute("haoCoupon_isVerify", dataDictionaryInit.haoCoupon_isVerify);

        return true;
    }

}
