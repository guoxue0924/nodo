package com.bluemobi.constant;

/**
 * 商户模块基础常量
 * 
 * @author zhangzheng
 * @date 2015-11-25
 * 
 */
public class GoodsConstant {

    /** 图片路径--商品品牌LOGO */
    public static final String BRAND_LOGO_IMAGE = "/goods/brand/";
    /** 图片路径--商品属性 */
    public static final String GOODS_PROPERTY_IMAGE = "/goods/property/";
    /** 图片路径--商品图片 */
    public static final String GOODS_CONTENT_IMAGE = "/goods/content/";

    /** 缓存--商品主表id */
    public static final String CACHE_GOODS_CONTENT_ID = "goods_content_id_";
    /** 缓存--商品主表map */
    public static final String CACHE_GOODS_CONTENT_MAP = "goods_content_map";
    /** 缓存--商品分类 */
    public static final String CACHE_GOODS_CATEGORY = "goods_category_";
    /** 缓存--商品品牌 */
    public static final String CACHE_GOODS_BRAND = "goods_brand_";

    /** 缓存--商品sku */
    public static final String CACHE_GOODS_CONTENT_SKU = "goods_content_sku_";
    /** 缓存--商品sku map */
    public static final String CACHE_GOODS_CONTENT_SKU_MAP = "goods_content_sku_map";
    /** 缓存--商品分类 */
    public static final String CACHE_GOODS_CONTENT_SKU_PRICE = "goods_content_sku_price_";
    /** 缓存--商品品牌 */
    public static final String CACHE_GOODS_CONTENT_SKU_CONTENT_ID = "goods_content_sku_content_id_";
    
    /** 缓存--属性map */
    public static final String CACHE_TREND_PROPERTY_MAP = "trend_property_map";
    /** 缓存--属性id */
    public static final String CACHE_TREND_PROPERTY_ID = "trend_property_id_";
    /** 缓存--属性值map */
    public static final String CACHE_TREND_PROPERTY_VALUE_MAP = "trend_property_value_map";
    /** 缓存--属性值id */
    public static final String CACHE_TREND_PROPERTY_VALUE_ID = "trend_property_value_id_";
    
    /** 缓存--商品 属性字符串 对应的skuid map，用于通过 属性字符串，查找skuid */
    public static final String CACHE_PROPERTY_ID_AND_VALUE_MAP = "property_id_and_value_map";
    
    /** 缓存--商品列表map，缓存商品简单信息，便于搜索商品列表的时候使用。 后期使用solr、Lucene 等搜索引擎的时候，可以去除这部分内容 */
    public static final String CACHE_GOODS_LIST_map = "goods_list_map";
    
    
    
    
    /** 商品分类删除成功 */
    public static final Integer CATEGORY_DELETE_SUCCESS = 0;
    /** 商品分类删除失败 */
    public static final Integer CATEGORY_DELETE_FAIL = 1;
    /** 商品分类删除失败，存在子类 */
    public static final Integer CATEGORY_DELETE_FAIL_EXIS = 2;
}
