package com.bluemobi.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 广告模块基础常量
 * 
 * @author zhangzheng
 * @date 2015-11-25
 * 
 */
public class AdvertConstant {
    /** 绑定属性类型1：链接地址 */
    public static final Integer BIND_TYPE_LINK_ADDRESS = 1;
    /** 绑定属性类型2：商品 */
    public static final Integer BIND_TYPE_GOODS = 2;
    /** 绑定属性类型3：文章 */
    public static final Integer BIND_TYPE_WORD = 3;
    /** 绑定属性类型4：自定义 */
    public static final Integer BIND_TYPE_CUSTOM = 4;

    /** 文件路径--广告 */
    public static final String ADVERT_CONTENT_FILE = "/advert/content/";

    public static Map<Integer, Object> bindTypeMap;

    static {
        bindTypeMap = new LinkedHashMap<Integer, Object>();
        bindTypeMap.put(BIND_TYPE_LINK_ADDRESS, "链接地址");
        bindTypeMap.put(BIND_TYPE_GOODS, "商品");
        bindTypeMap.put(BIND_TYPE_CUSTOM, "自定义");
    }

}
