package com.bluemobi.constant;

public class CouponConstant {

	// ============================= 优惠券模块 ================================
	/** 优惠券状态--未启用 */
	public static final byte UNUSED_STATUS = 0;
	/** 优惠券状态--启用 */
	public static final byte USED_STATUS = 1;
	/** 优惠券状态--锁定 */
	public static final byte LOCKED_STATUS = 2;
	/** 优惠券类型--全场 */
	public static final byte ALL_SKU_TYPE = 0;
	/** 优惠券类型--满减 */
	public static final byte FULL_MINUS_TYPE = 1;
	/** 优惠券审核状态--未审核 */
	public static final byte WAIT_VERIFY_STATUS = 0;
	/** 优惠券审核状态--审核通过 */
	public static final byte VERIFIED_ACCEPT_STATUS = 1;
	/** 优惠券审核状态--审核拒绝 */
	public static final byte VERIFIED_DENIED_STATUS = -1;
	/** 优惠券失效状态--未失效 */
	public static final byte ENABLED_STATUS = 0;
	/** 优惠券失效状态--失效 */
	public static final byte DISABLED_STATUS = 1;
	/** 优惠券是否允许积分兑换--不允许 */
	public static final byte UNALLOWD_EXCHANGE = 0;
	/** 优惠券是否允许积分兑换--允许 */
	public static final byte ALLOWED_EXCHANGE = 1;
	/** 优惠券是否标记删除--未删除 */
	public static final byte UNDELETE_MARK = 0;
	/** 优惠券是否标记删除--已删除 */
	public static final byte HAS_DELETED_MARK = 1;
	/** 优惠券满减类型--全场满减 */
	public static final byte ALL_SKU_FULL_MINUS_TYPE = 0;
	/** 优惠券满减类型--商品分类满减 */
	public static final byte SPEC_CATEGORY_FULL_MINUS_TYPE = 1;
	/** 优惠券满减类型--商户满减 */
	public static final byte SPEC_STORE_FULL_MINUS_TYPE = 2;
	
	public static final String COUPON_IMAGE = "/coupon/";

}
