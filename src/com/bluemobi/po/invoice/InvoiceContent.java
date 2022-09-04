package com.bluemobi.po.invoice;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【发票表】持久化对象 数据库表：invoice_content
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-11 15:44:05
 * 
 */
public class InvoiceContent extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 用户id
    private Long userid;
    // 订单id
    private Long orderId;
    // 发票代码
    private String invoiceCode;
    // 发票号码
    private String invoiceNumber;
    // 机打号
    private String machineNumber;
    // 机器编号
    private String identificationNumber;
    // 收款单位
    private String payee;
    // 税务登记号
    private String taxRegisterNumber;
    // 开票日期
    private Date invoiceDate;
    // 收款员
    private String receiver;
    // 付款单位（个人）
    private String payer;
    // 项目
    private Long goodsContentSkuId;
    // 单价
    private BigDecimal price;
    // 数量
    private Integer amount;
    // 金额
    private BigDecimal money;
    // 合计
    private BigDecimal lumpSum;
    // 税控码
    private String taxCode;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 用户id 属性 */
    public Long getUserid() {
        return userid;
    }

    /** 设置 用户id 属性 */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /** 获取 订单id 属性 */
    public Long getOrderId() {
        return orderId;
    }

    /** 设置 订单id 属性 */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /** 获取 发票代码 属性 */
    public String getInvoiceCode() {
        return invoiceCode;
    }

    /** 设置 发票代码 属性 */
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    /** 获取 发票号码 属性 */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /** 设置 发票号码 属性 */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /** 获取 机打号 属性 */
    public String getMachineNumber() {
        return machineNumber;
    }

    /** 设置 机打号 属性 */
    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    /** 获取 机器编号 属性 */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /** 设置 机器编号 属性 */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /** 获取 收款单位 属性 */
    public String getPayee() {
        return payee;
    }

    /** 设置 收款单位 属性 */
    public void setPayee(String payee) {
        this.payee = payee;
    }

    /** 获取 税务登记号 属性 */
    public String getTaxRegisterNumber() {
        return taxRegisterNumber;
    }

    /** 设置 税务登记号 属性 */
    public void setTaxRegisterNumber(String taxRegisterNumber) {
        this.taxRegisterNumber = taxRegisterNumber;
    }

    /** 获取 开票日期 属性 */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /** 设置 开票日期 属性 */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /** 获取 收款员 属性 */
    public String getReceiver() {
        return receiver;
    }

    /** 设置 收款员 属性 */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /** 获取 付款单位（个人） 属性 */
    public String getPayer() {
        return payer;
    }

    /** 设置 付款单位（个人） 属性 */
    public void setPayer(String payer) {
        this.payer = payer;
    }

    /** 获取 项目 属性 */
    public Long getGoodsContentSkuId() {
        return goodsContentSkuId;
    }

    /** 设置 项目 属性 */
    public void setGoodsContentSkuId(Long goodsContentSkuId) {
        this.goodsContentSkuId = goodsContentSkuId;
    }

    /** 获取 单价 属性 */
    public BigDecimal getPrice() {
        return price;
    }

    /** 设置 单价 属性 */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /** 获取 数量 属性 */
    public Integer getAmount() {
        return amount;
    }

    /** 设置 数量 属性 */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /** 获取 金额 属性 */
    public BigDecimal getMoney() {
        return money;
    }

    /** 设置 金额 属性 */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /** 获取 合计 属性 */
    public BigDecimal getLumpSum() {
        return lumpSum;
    }

    /** 设置 合计 属性 */
    public void setLumpSum(BigDecimal lumpSum) {
        this.lumpSum = lumpSum;
    }

    /** 获取 税控码 属性 */
    public String getTaxCode() {
        return taxCode;
    }

    /** 设置 税控码 属性 */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("InvoiceContent");
        sb.append("{id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", orderId=").append(orderId);
        sb.append(", invoiceCode=").append(invoiceCode);
        sb.append(", invoiceNumber=").append(invoiceNumber);
        sb.append(", machineNumber=").append(machineNumber);
        sb.append(", identificationNumber=").append(identificationNumber);
        sb.append(", payee=").append(payee);
        sb.append(", taxRegisterNumber=").append(taxRegisterNumber);
        sb.append(", invoiceDate=").append(invoiceDate);
        sb.append(", receiver=").append(receiver);
        sb.append(", payer=").append(payer);
        sb.append(", goodsContentSkuId=").append(goodsContentSkuId);
        sb.append(", price=").append(price);
        sb.append(", amount=").append(amount);
        sb.append(", money=").append(money);
        sb.append(", lumpSum=").append(lumpSum);
        sb.append(", taxCode=").append(taxCode);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InvoiceContent) {
            InvoiceContent invoiceContent = (InvoiceContent) obj;
            if (this.getId().equals(invoiceContent.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}