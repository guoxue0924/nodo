<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<script type="text/javascript" src="${STATIC_URL}/plugins/jcarousel/jquery.jcarousel.min.js"></script>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <<span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span>我的商城</span>
    </div>
</div>
<jsp:include page="user.nav.jsp"/>
<div class="right-layout">
    <div id="member_center_box" class="ncm-index-container">

        <div id="account" class="double">
            <div class="outline">
                <div class="user-account">
                    <ul>
                        <li id="pre-deposit"><a href="" title="查看我的余额">
                            <h5>账户余额</h5>
                            <span class="icon"></span> <span class="value">￥<em>0.00</em></span></a> </li>
                        <li id="voucher"><a href="" title="查看我的代金券">
                            <h5>代金券</h5>
                            <span class="icon"></span> <span class="value"><em>0</em>张</span></a> </li>
                        <li id="points"><a href="" title="查看我的积分">
                            <h5>可用积分</h5>
                            <span class="icon"></span> <span class="value"><em>50</em>分</span></a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="security" class="normal">
            <div class="outline">
                <div class="SAM">
                    <h5>账户安全</h5>
                    <div id="low" class="SAM-info"><strong>低</strong><span><em></em></span>
                        <a href="" title="安全设置">提升&gt;</a>
                    </div>
                    <div class="SAM-handle"><span><i class="mobile"></i>手机：
                <a href="" title="绑定手机">未绑定</a>
        </span>
        <span><i class="mail"></i>邮箱：
                <a href="" title="绑定邮箱">未绑定</a>
        </span>
                    </div>
                </div>
            </div>
        </div>
        <div id="transaction" class="double">
            <div class="outline">
                <div class="title">
                    <h3>交易提醒</h3>
                    <ul>
                        <li>
                            <c:choose><c:when test="${WAIT_PAY > 0}">
                            <a href="/btscas/order?status=2">待付款<em>${WAIT_PAY}</em></a>
                            </c:when><c:otherwise>
                            待付款<em>0</em>
                            </c:otherwise></c:choose>
                        </li>
                        <li>
                        <c:choose><c:when test="${WAIT_DELIVERY > 0}">
                            <a href="/btscas/order?status=5">待发货<em>${WAIT_DELIVERY}</em></a>
                            </c:when><c:otherwise>
                            待收货<em>0</em>
                           </c:otherwise></c:choose>
                        </li>
                        <li>
                        <c:choose><c:when test="${order_status.uncomment > 0}">
                            <a href="/btscas/order?status=1">待评价<em>${order_status.uncomment}</em></a>
                            </c:when><c:otherwise>
                            待评价<em>${order_status.uncomment}</em>
                            </c:otherwise></c:choose>

                        </li>
                    </ul>
                </div>
                <div class="order-list">
                 <c:choose><c:when test="${orders != null}">
                    <ul>
                        <c:forEach items="${orders}" var="o" >
                            
                        <li>
                            <div class="ncm-goods-thumb"><a target="_blank" href="${BASE_URL}/front/bts/order/detail?orderId=${o.orderId}"><img src="${IMG_URL}${o.items[0].goodsImage}"></a><em><c:if test="${o.count>1}">${o.count}</c:if></em></div>
                            <dl class="ncm-goods-info">
                                <dt><a href="${BASE_URL}/front/goodsContent/detail?skuId=${o.items[0].skuId}" target="_blank">${o.items[0].goodsName}</a>
                                </dt>
                                <dd><span class="order-date">下单时间：<fmt:formatDate value="${o.ctime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></span><span class="ncm-order-price">订单金额：<em><fmt:formatNumber value="${o.totalAmount}" pattern="￥#,#00.00#"/></em></span></dd>
                                <dd><span class="order-state">订单状态：<c:forEach items="${orderStatus}" var="entry"><c:if test="${entry.key == o.status}">${entry.value}</c:if></c:forEach> </span> </dd>
                            </dl>
                            <c:choose>
                            	<c:when test="${o.status eq 2}">
                            		<a href="${BASE_URL}/front/bts/order/pay?orderId=${o.orderId}" target="_blank" class="ncm-btn">订单支付</a>
                            	</c:when>
                           		<c:when test="${o.status eq 1 }">
                           			<a href="${BASE_URL}/front/cas/comment?orderId=${o.orderId}" target="_blank" class="ncm-btn">我要评论</a>
                           		</c:when>
                            	<c:otherwise>
                            		<a href="${BASE_URL}/front/bts/order/detail?orderId=${o.orderId}" target="_blank" class="ncm-btn">查看订单</a>
                            	</c:otherwise>
                           	</c:choose>
                            
                        </li>
                        </c:forEach>
                    </ul>
                    </c:when>
                    <c:otherwise>
                 <dl class="null-tip">
                    <dt></dt>
                    <dd>
                        <h4>您好久没在商城购物了</h4>
                        <h5>交易提醒可帮助您了解订单状态和物流情况</h5>
                    </dd>
                 </dl>
                 </c:otherwise></c:choose>
                </div>
                
            </div>
        </div>
        <div id="shopping" class="normal">
            <div class="outline">
                <div class="title">
                    <h3>购物车</h3>
                </div>
                <c:choose><c:when test="${carts != null}">
                <div class="cart-list">
                    <ul>
                    <c:forEach items="${carts}" var="c">
                        <li>
                            <div class="ncm-goods-thumb">
                                <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${c.skuId}">
                                    <img src="${IMG_URL}${c.DefaultImage}">
                                </a>
                            </div>
                            <dl class="ncm-goods-info">
                                <dt>
                                    <a href="${BASE_URL}/front/goodsContent/detail?skuId=${c.skuId}" target="_blank">${c.name}</a>
                                </dt>
                                <dd>
                                    <span class="ncm-order-price">商城价：<em><fmt:formatNumber value="${c.price}" pattern="￥#,#00.00#"/></em></span>
                                </dd>
                            </dl>
                        </li>
                        </c:forEach>
                    </ul>
                    <div class="more"><a href="${BASE_URL}/front/cart">查看购物车所有商品</a></div>
                </div>
                </c:when>
                <c:otherwise>
                <dl class="null-tip">
                    <dt></dt>
                    <dd>
                        <h4>您的购物车还是空的</h4>
                        <h5>将想买的商品放进购物车，一起结算更轻松</h5>
                    </dd>
                </dl>
                </c:otherwise></c:choose>
            </div>
        </div>

        <div id="favoritesGoods" class="double">
            <div class="outline">
                <div class="title">
                    <h3>商品收藏</h3>
                </div>
                <c:choose><c:when test="${favorites != null}">
                <div class="ncm-favorites-goods">
                    <ul id="favoritesGoodsList" class="jcarousel-skin-tango">
                        <c:forEach items="${favorites}" var="f">
                        <li>
                            <div class="ncm-goods-thumb-120">
                                <a href="${BASE_URL}/front/goodsContent/detail?skuId=${f.serialize.skuId}" target="_blank"><img alt="${f.serialize.name}" src="${IMG_URL}${f.DefaultImage}"></a>
                                <div class="ncm-goods-price"><em><fmt:formatNumber value="${f.serialize.price}" pattern="￥#,#00.00#"/></em></div>
                            </div>
                            <div class="ncm-goods-name"><a href="${BASE_URL}/front/goodsContent/detail?skuId=${f.serialize.skuId}" title="${f.serialize.name}" target="_blank"></a></div>
                        </li>
                        </c:forEach>
                    </ul>
                    <div class="more"><a target="_blank" href="${BASE_URL}/front/cas/favorite">查看收藏的所有商品</a></div>
                </div>
                </c:when><c:otherwise>
                <dl class="null-tip">
                    <dt></dt>
                    <dd>
                        <h4>您还没有收藏商品</h4>
                        <h5>收藏的商品将显示最新的促销活动和降价情况</h5>
                    </dd>
                </dl>
                </c:otherwise></c:choose>
            </div>
        </div>
        <div id="browseMark" class="normal">
            <!-- <div class="outline">
                <div class="title">
                    <h3>我的足迹</h3>
                </div>
                <dl class="null-tip">
                    <dt></dt>
                    <dd>
                        <h4>您的商品浏览记录为空</h4>
                        <h5>赶紧去商城看看促销活动吧</h5>
                        <p><a target="_blank" href="http://10.58.137.126/shop" class="ncm-btn-mini">浏览商品</a></p>
                    </dd>
                </dl>
            </div> -->
        </div>
    </div>
</div>
<div class="clear"></div>
</div>

<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/user.index.js"></script>

<jsp:include page="wrapper.suffix.jsp"/>