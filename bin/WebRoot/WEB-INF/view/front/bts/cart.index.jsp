<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/inc/header_front.jsp"/>
<jsp:include page="../index/inc/main.top.jsp"/>
<link href="${STATIC_URL}/front/modules/bts/css/home_cart.css" rel="stylesheet" type="text/css">

<header class="ncc-head-layout">
    <div class="site-logo"><a href="${BASE_URL}/front/index/index"><img src="${STATIC_URL}/front/images/front/logo.png" class="pngFix"></a></div>
    <ul class="ncc-flow">
        <li class="current"><i class="step1"></i>
            <p>我的购物车</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
        <li class=""><i class="step2"></i>
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
    <c:choose><c:when test="${carts ne '[]'}">
    <style>
        .ncc-table-style tbody tr.item_disabled td {
            background: none repeat scroll 0 0 #F9F9F9;
            height: 30px;
            padding: 10px 0;
            text-align: center;
        }
    </style>
    <div class="ncc-main">
        <div class="ncc-title">
            <h3>我的购物车</h3>
            <h5>查看购物车商品清单，增加减少商品数量，并勾选想要的商品进入下一步操作。</h5>
        </div>
        <form action="${BASE_URL}/front/bts/order/check" method="POST" id="form_buy" name="form_buy">
            <input type="hidden" value="1" name="ifcart">
            <table class="ncc-table-style" nc_type="table_cart">
                <thead>
                <tr>
                    <th class="w50"><label>
                        <input type="checkbox" checked="" value="1" id="selectAll">
                        全选</label></th>
                    <th></th>
                    <th>商品</th>
                    <th class="w120">单价(元)</th>
                    <th class="w120">数量</th>
                    <th class="w120">小计(元)</th>
                    <th class="w80">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${carts}" var="c">
                
                <tr id="cart_item_${c.cartId}" nc_group="${c.cartId}" class="shop-list ">
                    <td>
                        <input type="checkbox" checked="" nc_type="eachGoodsCheckBox" value="${c.cartId}" id="cartId${c.cartId}" name="cartIds">
                    </td>
                    <td class="w60">
                        <a href="${BASE_URL}/front/goodsContent/detail?skuId=${c.skuId}" target="_blank" class="ncc-goods-thumb"><img src="${IMG_URL}${c.DefaultImage}" alt="${c.name}"></a>
                    </td>
                    <td class="tl">
                        <dl class="ncc-goods-info">
                        <dt>
                            <a href="${BASE_URL}/front/goodsContent/detail?skuId=${c.skuId}" target="_blank">${c.name}</a>
                        </dt>
                        </dl>
                    </td>
                    <td class="w120"><em id="item${c.cartId}_price"><fmt:formatNumber value="${c.price}" pattern="￥#,#00.00#"/></em></td>
                    <td class="w120 ws0">
                        <a href="JavaScript:void(0);" onclick="decrease_quantity(${c.cartId});" title="减少商品件数" class="add-substract-key tip">-</a>
                        <input id="input_item_${c.cartId}" value="${c.quantity}" orig="1" changed="1" onkeyup="change_quantity(${c.cartId}, this,${c.skuId});" type="text" class="text w20">
                        <a href="JavaScript:void(0);" onclick="add_quantity(${c.cartId});" title="增加商品件数" class="add-substract-key tip">+</a>
                    </td>
                    <td class="w120">
                        <em id="item${c.cartId}_subtotal" nc_type="eachGoodsTotal"><fmt:formatNumber value="${c.subPrice}" pattern="￥#,#00.00#"/></em>
                    </td>
                    <td class="w80">
                        <%-- <a href="javascript:void(0)" onclick="collect_goods(${c.contentId});">收藏</a><br> --%>
                        <a href="javascript:void(0)" onclick="drop_cart_item(${c.cartId});">删除</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="20"><div class="ncc-all-account">商品总价（不含运费）<em id="cartTotal"><fmt:formatNumber value="${price}" pattern="￥#,#00.00#"/></em>元</div></td>
                </tr>
                </tfoot>
            </table>
        </form>
        <div class="ncc-bottom">
            <a id="next_submit" href="javascript:void(0)" class="ncc-btn ncc-btn-acidblue fr"><i class="icon-pencil"></i>下一步，填写核对购物信息
            </a>
        </div>
    </div>
    </c:when><c:otherwise>
    <div class="ncc-null-shopping"><i class="ico"></i>
        <h4>您的购物车还没有商品</h4>
        <p><a href="${BASE_URL}/front/index/index" class="ncc-btn-mini mr10"><i class="icon-reply-all"></i>马上去购物</a> <a href="${BASE_URL}/front/bts/order" class="ncc-btn-mini"><i class="icon-file-text"></i>查看自己的订单</a></p>
    </div>
    </c:otherwise></c:choose>
</div>


<jsp:include page="../index/inc/footer.jsp"/>
<script src="${STATIC_URL}/front/modules/bts/js/cart.index.js"></script>
