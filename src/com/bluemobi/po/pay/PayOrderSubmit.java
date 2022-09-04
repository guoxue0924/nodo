package com.bluemobi.po.pay;

import com.appcore.model.AbstractObject;
import com.bluemobi.po.bts.BtsOrder;

/**
 * 
 * 供页面订单提交接口使用
 * 
 * @author Administrator
 *
 */
public class PayOrderSubmit extends BtsOrder{

	public static final long serialVersionUID = 1L;
	
	/**
	 * server路径
	 */
	private String serverUrl;
	/**
	 * 密钥
	 */
	private String key;
	/**
	 * 报文版本号
	 */
	private String version;
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 编码
	 */
	private String inputCharset;
	/**
	 * 商户号
	 */
	private String merchantId;
	/**
	 * 取货地址
	 */
	private String pickupUrl;
	/**
	 * 商户系统通知地址
	 */
	private String receiveUrl;
	/**
	 * 支付方式
	 */
	private String paytype;
	/**
	 * 签名方式
	 */
	private String signType;
	/**
	 * 商户系统订单号
	 */
	private String orderNo;
	/**
	 * 订单金额(单位分)
	 */
	private String orderAmount;
	/**
	 * 商户的订单创建时间
	 */
	private String orderDatetime;
	/**
	 * 金额币种
	 */
	private String orderCurrency;
	/**
	 * 订单过期时间
	 */
	private String orderExpireDatetime;
	/**
	 * 付款人电话
	 */
	private String payerTelephone;
	/**
	 * 付款人联系email
	 */
	private String payerEmail;
	/**
	 * 付款人名称
	 */
	private String payerName;
	/**
	 * 付款人证件号
	 */
	private String payerIDCard;
	/**
	 * 合作伙伴的商户号
	 */
	private String pid;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品标识
	 */
	private String productId;
	/**
	 * 商品数量
	 */
	private String productNum;
	/**
	 * 商品单价
	 */
	private String productPrice;
	/**
	 * 商品描述
	 */
	private String productDesc;
	/**
	 * 附加参数1
	 */
	private String ext1;
	/**
	 * 附加参数2
	 */
	private String ext2;
	/**
	 * 发卡行机构号
	 */
	private String issuerId;
	/**
	 * 付款人支付卡号
	 */
	private String pan;
	/**
	 * 贸易类型
	 */
	private String tradeNature;
	/**
	 * 报文签名后内容
	 */
	private String strSignMsg;
	
	
	public PayOrderSubmit(String string, String stringFromJson) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取 server路径
	 */
	public String getServerUrl() {
		return serverUrl;
	}
	
	/**
	 * 设置 server路径
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	/**
	 * 获取 密钥
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 设置 密钥
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 获取 报文版本号
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * 设置 报文版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 获取 语言
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * 设置 语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * 获取 编码
	 */
	public String getInputCharset() {
		return inputCharset;
	}
	
	/**
	 * 设置 编码
	 */
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	
	/**
	 * 获取 商户号
	 */
	public String getMerchantId() {
		return merchantId;
	}
	
	/**
	 * 设置 商户号
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	/**
	 * 获取 取货地址
	 */
	public String getPickupUrl() {
		return pickupUrl;
	}
	
	/**
	 * 设置 取货地址
	 */
	public void setPickupUrl(String pickupUrl) {
		this.pickupUrl = pickupUrl;
	}
	
	/**
	 * 获取 商户系统通知地址
	 */
	public String getReceiveUrl() {
		return receiveUrl;
	}
	
	/**
	 * 设置 商户系统通知地址
	 */
	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl;
	}
	
	/**
	 * 获取 支付方式
	 */
	public String getPaytype() {
		return paytype;
	}
	
	/**
	 * 设置 支付方式
	 */
	public void setPaytype(String payType) {
		this.paytype = paytype;
	}
	
	/**
	 * 获取 签名方式
	 */
	public String getSignType() {
		return signType;
	}
	
	/**
	 * 设置 签名方式
	 */
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	/**
	 * 获取 商户系统订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * 设置 商户系统订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 获取 订单金额(单位分)
	 */
	public String getOrderAmount() {
		return orderAmount;
	}
	
	/**
	 * 设置 订单金额(单位分)
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	/**
	 * 获取 商户的订单创建时间
	 */
	public String getOrderDatetime() {
		return orderDatetime;
	}
	
	/**
	 * 设置  商户的订单创建时间
	 */
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	
	/**
	 * 获取 金额币种
	 */
	public String getOrderCurrency() {
		return orderCurrency;
	}
	
	/**
	 * 设置 金额币种
	 */
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	
	/**
	 * 获取 订单过期时间
	 */
	public String getOrderExpireDatetime() {
		return orderExpireDatetime;
	}
	
	/**
	 * 设置 订单过期时间
	 */
	public void setOrderExpireDatetime(String orderExpireDatetime) {
		this.orderExpireDatetime = orderExpireDatetime;
	}
	
	/**
	 * 获取 付款人电话
	 */
	public String getPayerTelephone() {
		return payerTelephone;
	}
	
	/**
	 * 设置 付款人电话
	 */
	public void setPayerTelephone(String payerTelephone) {
		this.payerTelephone = payerTelephone;
	}
	
	/**
	 * 获取 付款人联系email
	 */
	public String getPayerEmail() {
		return payerEmail;
	}
	
	/**
	 * 设置 付款人联系email
	 */
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}
	
	/**
	 * 获取 付款人名称
	 */
	public String getPayerName() {
		return payerName;
	}
	
	/**
	 * 设置  付款人名称
	 */
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	
	/**
	 * 获取 付款人证件号
	 */
	public String getPayerIDCard() {
		return payerIDCard;
	}
	
	/**
	 * 设置 付款人证件号
	 */
	public void setPayerIDCard(String payerIDCard) {
		this.payerIDCard = payerIDCard;
	}
	
	/**
	 * 获取 合作伙伴的商户号
	 */
	public String getPid() {
		return pid;
	}
	
	/**
	 * 设置 合作伙伴的商户号
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	/**
	 * 获取 商品名称
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * 设置 商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 获取 商品标识
	 */
	public String getProductId() {
		return productId;
	}
	
	/**
	 * 设置  商品标识
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 获取 商品数量
	 */
	public String getProductNum() {
		return productNum;
	}
	
	/**
	 * 设置 商品数量
	 */
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 获取 商品单价
	 */
	public String getProductPrice() {
		return productPrice;
	}
	
	/**
	 * 设置 商品单价
	 */
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	/**
	 * 获取 商品描述
	 */
	public String getProductDesc() {
		return productDesc;
	}
	
	/**
	 * 设置 商品描述
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	/**
	 * 获取 附加参数1
	 */
	public String getExt1() {
		return ext1;
	}
	
	/**
	 * 设置 附加参数1
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	/**
	 * 获取 附加参数2
	 */
	public String getExt2() {
		return ext2;
	}
	
	/**
	 * 设置  附加参数2
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	/**
	 * 获取 发卡行机构号
	 */
	public String getIssuerId() {
		return issuerId;
	}
	
	/**
	 * 设置 发卡行机构号
	 */
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
	/**
	 * 获取 付款人支付卡号
	 */
	public String getPan() {
		return pan;
	}
	
	/**
	 * 设置 付款人支付卡号
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	/**
	 * 获取 贸易类型
	 */
	public String getTradeNature() {
		return tradeNature;
	}
	
	/**
	 * 设置 贸易类型
	 */
	public void setTradeNature(String tradeNature) {
		this.tradeNature = tradeNature;
	}
	
	/**
	 * 获取 报文签名后内容
	 */
	public String getStrSignMsg() {
		return strSignMsg;
	}
	
	/**
	 * 设置 报文签名后内容
	 */
	public void setStrSignMsg(String strSignMsg) {
		this.strSignMsg = strSignMsg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
