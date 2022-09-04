package com.bluemobi.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单相关静态常量
 * ClassName: OrderConstant
 * Date: 2015年12月28日下午2:38:25    

 * @author kevin
 * @version 
 * @since JDK 7
 */
public class OrderConstant {
	
	/** 支付类型--在线支付 */
	public static final byte ONLINE_PAYMENT_TYPE = 1;
	/** 支付类型--线下付款 */
	public static final byte OFFLINE_PAYMENT_TYPE = 2;
	/** 支付方式--支付宝 */
	public static final byte ALIPAY_PAYMENT = 1;
	/** 支付方式--微信 */
	public static final byte WECHAT_PAYMENT = 2;
	/** 支付方式--银联 */
	public static final byte UNIONPAY_PAYMENT = 3;
	/** 支付方式--货到付款 */
	public static final byte COD_PAYMENT = 4;
	/** 支付方式-支付类型关系映射 */
	public static Map<Byte, Byte> paymentMap;
	
	static {
	    paymentMap = new HashMap<Byte, Byte>();
	    paymentMap.put(OrderConstant.ALIPAY_PAYMENT, OrderConstant.ONLINE_PAYMENT_TYPE);
	    paymentMap.put(OrderConstant.WECHAT_PAYMENT, OrderConstant.ONLINE_PAYMENT_TYPE);
	    paymentMap.put(OrderConstant.UNIONPAY_PAYMENT, OrderConstant.ONLINE_PAYMENT_TYPE);
	    paymentMap.put(OrderConstant.COD_PAYMENT, OrderConstant.OFFLINE_PAYMENT_TYPE);
	}
	
	// 支付类型
	public enum PayType {
		ONLINE(0, "在线支付"),
		OFFLINE(1, "线下付款");

		Integer	code;
		String	desc;

		PayType(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	// 支付方式
	public enum PayWay {
		ALIPAY(1, "支付宝"),
		WECHAT(2, "微信"),
		UNIONPAY(3, "银联"),
		COD(4, "货到付款");

		Integer	code;
		String	desc;

		PayWay(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	
	// 物流
	public enum Logistics {
		LOGISTICS01("huitongkuaidi","百世汇通"),
		LOGISTICS02("bangsongwuliu","邦送物流"),
		LOGISTICS03("debangwuliu","德邦物流"),
		LOGISTICS04("ems","EMS"),
		LOGISTICS05("feikuaida","飞快达"),
		LOGISTICS06("rufengda","凡客如风达"),
		LOGISTICS07("fengxingtianxia","风行天下"),
		LOGISTICS08("feibaokuaidi","飞豹快递"),
		LOGISTICS09("guotongkuaidi","国通快递"),
		LOGISTICS10("huitongkuaidi","汇通快运"),
		LOGISTICS11("huiqiangkuaidi","汇强快递"),
		LOGISTICS12("shentong","申通"),
		LOGISTICS13("shunfeng","顺丰速递"),
		LOGISTICS14("tiantian","天天快递"),
		LOGISTICS15("yuantong","圆通速递"),
		LOGISTICS16("yunda","韵达快运"),
		LOGISTICS17("yuntongkuaidi","运通快递"),
		LOGISTICS18("youzhengguonei","邮政小包（国内）"),
		LOGISTICS19("youshuwuliu","优速物流"),
		LOGISTICS20("zhongtong","中通速递"),
		LOGISTICS21("zhaijisong","宅急送"),
		LOGISTICS22("zhongyouwuliu","中邮物流");


		String	code;
		String	desc;

		Logistics(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	// 订单类型
	public enum OrderType {
		COMMON(0, "常规订单"),
		GROUPONBULK(1, "团购订单"),
		GROUPONGRAB(2, "抢购订单");

		Integer	code;
		String	desc;

		OrderType(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	// 支付状态
	public enum PaymentStatus {
		WAIT(0, "未支付"),
		FINISHED(1, "已支付");

		Integer	code;
		String	desc;

		PaymentStatus(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	// 订单状态
	public enum OrderStatus {
		CANCEL(-1, "已取消"),
		WAIT_PROCESS(0, "待处理"),
		SIGNED(1, "已签收"),
		WAIT_PAY(2, "待付款"),
		PAYED(3, "已付款"),
		WAIT_DELIVERY(4, "待发货"),
		DELIVERIED(5, "已发货");

		Integer	code;
		String	desc;

		OrderStatus(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	// 支付方式
	public enum RefundStatus {
		SUCCESS(1, "成功退货"),
		WAIT_AUDIT(2, "等待审核"),
		AUDITED(3, "已审核/待退货"),
		REFUNDED(4, "用户已退货");

		Integer	code;
		String	desc;

		RefundStatus(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
}
