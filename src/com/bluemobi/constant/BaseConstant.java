package com.bluemobi.constant;

import java.io.File;

import com.bluemobi.conf.Config;

/**
 * 基础的常量
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-9-21 下午5:19:28
 * 
 */
public class BaseConstant {

    /** 通用状态 -- 启用 */
    public static final int STATUS_ENABLED = 1;
    /** 通用状态 -- 禁用 */
    public static final int STATUS_DISABELD = 0;

    /** 返回状态--成功 */
    public static final int STATUS_SUCCESS = 0;
    /** 返回状态--失败 */
    public static final int STATUS_FAILURE = 1;
    /** 返回状态--session过期 */
    public static final int STATUS_SESSION_INVALID = 2;

    /** 图片上传根路径 */
    public static final String BASE_IMAGE_ADDRESS = new File(BaseConstant.class.getResource("/").getPath()) + "/../../../img";
    /** 图片服务器路径 */
    public static final String IMG_SERVER_URL = Config.IMG_URL;
    /** 图片路径--会员头像 */
    public static final String USER_AVATAR_IMAGE = "/user/avatar/";
    /** 图片路径--前台页面上会员临时头像，用于裁剪的原图 */
    public static final String TEMP_IMAGE = "/temp/";

    // ============================= 上传附件关联表类型 ================================
    /** 附件类型--1：商品附件 */
    public static final String GOODS_CONTENT_SKU = "1";
    /** 附件类型--2：订单附件 */
    public static final String BTS_ORDER_ITEM = "2";
    /** 附件类型--3：评论附件 */
    public static final String COMMENT_CONTENT = "3";

    private BaseConstant() {
    }

}
