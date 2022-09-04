package com.bluemobi;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.trend.TrendPropertyService;
import com.bluemobi.service.trend.TrendRegionService;

/**
 * 初始化缓存数据等
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-12-15 下午3:42:16
 * 
 */
@Component(value = "serverInit")
public class ServerInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerInit.class);

    @Autowired
    private TrendPropertyService trendPropertyService;
    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private TrendRegionService trendRegionService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    public boolean init() {

        // beanutils 初始化设置
        ConvertUtils.register(new org.apache.commons.beanutils.converters.DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), java.math.BigDecimal.class);

        // 1、初始化商品属性和属性值
        LOGGER.info("开始初始化【商品属性和属性值】...");
        long b = System.currentTimeMillis();
        trendPropertyService.initTrendProperty();
        long e = System.currentTimeMillis();
        LOGGER.info("初始化结束【商品属性和属性值】成功...耗时【{}】秒", new Object[] { (e - b) / 1000d });

        // 2、初始化商品和sku信息
        LOGGER.info("开始初始化【商品和sku】...");
        b = System.currentTimeMillis();
        goodsContentService.initGoodsContent();
        e = System.currentTimeMillis();
        LOGGER.info("初始化结束【商品和sku】成功...耗时【{}】秒", new Object[] { (e - b) / 1000d });

        // 3、初始化商品分类
        LOGGER.info("开始初始化【商品分类】...");
        b = System.currentTimeMillis();
        goodsCategoryService.initCategories();
        e = System.currentTimeMillis();
        LOGGER.info("初始化结束【商品分类】成功...耗时【{}】秒", new Object[] { (e - b) / 1000d });
        
        // 4、初始化地区？
        LOGGER.info("开始初始化【地区】...");
        b = System.currentTimeMillis();
        trendRegionService.initRegion();
        e = System.currentTimeMillis();
        LOGGER.info("初始化结束【地区】成功...耗时【{}】秒", new Object[] { (e - b) / 1000d });

        return true;
    }

}
