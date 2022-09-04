<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/inc/header_front.jsp"/>
<jsp:include page="../index/inc/main.top.jsp"/>
<link href="${STATIC_URL}/front/modules/bts/css/home_cart.css" rel="stylesheet" type="text/css">

<header class="ncc-head-layout">
    <div class="site-logo"><a href="${BASE_URL}/front/index/index"><img src="${STATIC_URL}/front/images/front/logo.png" class="pngFix"></a></div>
    <ul class="ncc-flow">
        <li class=""><i class="step1"></i>
            <p>我的购物车</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
        <li class="current"><i class="step2"></i>
            <p>填写核对购物信息</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
        <li class=""><i class="step3"></i>
            <p>支付提交</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
        <li class=""><i class="step4"></i>
            <p>订单完成</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
    </ul>
</header>


<div class="ncc-wrapper">
    <form method="post" id="order_form" name="order_form" action="${BASE_URL}/front/bts/order/create">
        <!-- S fcode -->
        <div class="ncc-main">
            <div class="ncc-title">
                <h3>填写核对购物信息</h3>
                <h5>请仔细核对填写收货、发票等信息，以确保物流快递及时准确投递。</h5>
            </div>

            <!--收货人信息begin-->
            <div class="ncc-receipt-info">
                <div class="ncc-receipt-info-title">
                    <h3>收货人信息</h3>
                    <a href="javascript:void(0)" nc_type="buy_edit" id="edit_reciver">[修改]</a></div>
                <div id="addr_list" class="ncc-candidate-items">
                    <ul>
                        <li><span class="true-name">${consignee.name}</span><span class="address">${consignee.regionName}${consignee.address}</span><span class="phone"><i class="icon-mobile-phone"></i>${consignee.mobile}</span></li>
                    </ul>
                </div>
            </div>
            <!--收货人信息end-->
            <!--支付方式begin-->
            <div class="ncc-receipt-info" id="paymentCon">
                <div class="ncc-receipt-info-title">
                    <h3>支付方式</h3>
                </div>
                <div class="ncc-candidate-items">
                    <ul>
                        <li>在线支付</li>
                    </ul>
                    <input value="1" id="payType" name="payType" type="hidden">
                </div>
            </div>
            <!--支付方式end-->
            <!--发票信息begin-->
            <div class="ncc-receipt-info"><div class="ncc-receipt-info-title">
                <h3>发票信息</h3>
                <a href="javascript:void(0)" nc_type="buy_edit" id="edit_invoice">[修改]</a></div>
                <div id="invoice_list" class="ncc-candidate-items">
                    <ul>
                        <li>不需要发票</li>
                    </ul>
                </div>
            </div>
            <!--发票信息end-->
            <!--商品列表begin-->
            <div class="ncc-receipt-info">
                <div class="ncc-receipt-info-title">
                    <h3>商品清单</h3>
                    <c:if test="${buytype eq 0}"><a href="${BASE_URL}/front/bts/cart">返回购物车</a></c:if>
                </div>
                <table class="ncc-table-style">
                    <thead>
                    <tr>
                        <th class="w20"></th>
                        <th></th>
                        <th>商品</th>
                        <th class="w120">单价(元)</th>
                        <th class="w120">数量</th>
                        <th class="w120">小计(元)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${carts}" var="c">
                    <tr id="cart_item_${c.skuId}" class="shop-list ">
                        <td><input type="hidden" value="${c.cartId}" name="cartIds"></td>
                        <td class="w60">
                            <a href="${BASE_URL}/front/goodsContent/detail?skuId=${c.skuId}" target="_blank" class="ncc-goods-thumb">
                                <img src="${IMG_URL}${c.DefaultImage}" alt="${c.name}">
                            </a>
                        </td>
                        <td class="tl">
                            <dl class="ncc-goods-info">
                                <dt>
                                    <a href="${BASE_URL}/front/goodsContent/detail?skuId=${c.skuId}" target="_blank">${c.name}</a>
                                </dt>
                            </dl>
                        </td>
                        <td class="w120"><em><fmt:formatNumber value="${c.price}" pattern="￥#,#00.00#"/></em></td>
                        <td class="w60">${c.quantity}</td>
                        <td class="w120"><em id="item${c.cartId}_subtotal" nc_type="eachGoodsTotal">${c.subprice}</em>
                        </td>
                        <td></td>
                    </tr>
                        <!--立即购买-->
                        <input value="${c.skuId}" name="skuId" id="skuId" type="hidden">
                        <!--立即购买数量-->
                        <input value="${c.quantity}" name="buyNum" id="buyNum" type="hidden">
                    </c:forEach>
                    <tr>
                        <td class="w10"></td>
                        <td class="tl" colspan="2">买家留言：
                            <textarea name="remark" class="ncc-msg-textarea" placeholder="选填：对本次交易的说明（建议填写已经和商家达成一致的说明）" title="选填：对本次交易的说明（建议填写已经和商家达成一致的说明）" maxlength="150"></textarea></td>
                        <td class="tl" colspan="10"><div class="ncc-form-default"> </div></td>
                    </tr>
                    <tr>
                        <td class="tr" colspan="20">
                            <div class="ncc-store-account">
                                <dl class="freight">
                                    <dt>运费：</dt>
                                    <dd><em id="eachStoreFreight">0.00</em>元</dd>
                                </dl>
                                <dl>
                                    <dt>商品金额：</dt>
                                    <dd><em id="eachStoreGoodsTotal"><fmt:formatNumber value="${totalprice}" pattern="￥#,#00.00#"/></em>元</dd>
                                </dl>
                                <c:if test="${coupon != null}">
                                <dl class="voucher">
                                    <dt>
                                        <select nctype="voucher" name="coupon">
                                            <option value="0">选择代金券</option>
                                            <c:forEach items="${coupon}" var="c">
                                            <option value="${c.cpnsId}|${c.faceValue}">${c.couponName}</option>
                                            </c:forEach>
                                        </select>：
                                    </dt>
                                    <dd><em id="eachStoreVoucher">-0.00</em></dd>
                                </dl>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="20"><div class="ncc-all-account">订单总金额：<em id="orderTotal">${totalprice}</em>元</div></td>
                    </tr>
                    </tfoot>
                </table>
            </div>
            <!--商品列表end-->

            <div class="ncc-bottom"> <a href="javascript:void(0)" id="submitOrder" class="ncc-btn ncc-btn-acidblue fr">提交订单</a> </div>
            <!-- 来源于购物车标志 -->
            <input value="1" type="hidden" name="ifcart">

            <!-- 收货地址ID -->
            <input value="${consignee.consigneeId}" name="consigneeId" id="consigneeId" type="hidden">
            <input value="1" name="paymentId" id="paymentId" type="hidden">
            <input value="0" name="orderType" id="orderType" type="hidden">

            <!-- 城市ID(运费) -->
            <input value="36" name="buy_city_id" id="buy_city_id" type="hidden">

            <!-- 默认使用的发票 -->
            <input value="0" name="isInvoice" id="isInvoice" type="hidden">
            <input value="" name="invoice_belong" id="invoice_belong" type="hidden">

            <!--立即购买收货地址id-->
            <input value="${consignee.consigneeId}" name="consigneeId" id="consigneeId" type="hidden">
        </div>
    </form>
    
</div>

<jsp:include page="../index/inc/footer.jsp"/>
<script src="${STATIC_URL}/front/modules/bts/js/order.check.js"></script>