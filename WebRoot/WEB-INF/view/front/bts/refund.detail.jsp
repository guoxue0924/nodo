<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/plugins/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet"  type="text/css" id="cssfile2">

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="/index.php">首页</a></span><span class="arrow">&gt;</span>
        <span>我的商城</span><span class="arrow">&gt;</span>
        <span>实物交易订单</span>
    </div>
</div>

<jsp:include page="../cas/user.nav.jsp"/>

<div class="right-layout">

    <div class="ncm-flow-layout">
        <div class="ncm-flow-container">
            <div class="title">
                <h3>退款服务</h3>
            </div>
            <div class="alert">
                <h4>提示：</h4>
                <ul>
                    <li>1. 若提出申请后，商家拒绝退款或退货，可再次提交申请或选择<em>“商品投诉”</em>，请求商城客服人员介入。</li>
                    <li>2. 成功完成退款/退货；经过商城审核后，会将退款金额以<em>“预存款”</em>的形式返还到您的余额账户中（充值卡部分只能退回到充值卡余额）。</li>
                </ul>
            </div>
            <div id="saleRefund" show_id="1">
                <div class="ncm-flow-step">
                    <dl class='step-first <c:if test="${refund.status == 2}">current</c:if>'>
                        <dt>买家申请退款</dt>
                        <dd class="bg"></dd>
                    </dl>
                    <dl class='<c:if test="${refund.status == 3}">current</c:if>'>
                        <dt>商家处理退款申请</dt>
                        <dd class="bg"></dd>
                    </dl>
                    <dl class='<c:if test="${refund.status == 4}">current</c:if>'>
                        <dt>平台审核，退款完成</dt>
                        <dd class="bg"></dd>
                    </dl>
                </div>
                <div class=" ncm-default-form">
                    <h3>我的退款申请</h3>
                    <dl>
                        <dt>退款编号：</dt>
                        <dd>${refund.refundSn}</dd>
                    </dl>
                    <dl>
                        <dt>退款原因：</dt>
                        <dd>${refund.reason}</dd>
                    </dl>
                    <dl>
                        <dt>退款金额：</dt>
                        <dd><fmt:formatNumber value="${refund.number*refund.buyPrice}" pattern="#,#00.00#"/></dd>
                    </dl>
                    <dl>
                        <dt>数量：</dt>
                        <dd>${refund.number}</dd>
                    </dl>
                    <dl>
                        <dt>凭证上传：</dt>
                        <dd>
                        	<ul class="photos-thumb">
			        		<c:forEach items="${refund.picList}" var="pic">
				            	<li>
				                	<a nctype="nyroModal" href="${IMG_URL}${pic}"> <img src="${IMG_URL}${pic}" style="max-width: 100px;max-height: 120px;"></a>
				            	</li>
			            	</c:forEach>
			        	</ul>
                        </dd>111
                    </dl>
                    <h3>商家退款处理</h3>
                    <dl>
                        <dt>审核状态：
                        <c:choose>
                        	<c:when test="${refund.status == 1}">已成功退货</c:when>
                        	<c:when test="${refund.status == 2}">等待审核</c:when>
                        	<c:when test="${refund.status == 3}">已审核/待退货</c:when>
                        </c:choose></dt>
                        <dd></dd>
                    </dl>
                    <div class="bottom"><a href="javascript:history.go(-1);" class="ncm-btn"><i
                            class="icon-reply"></i>返回列表</a></div>
                </div>
            </div>
        </div>

        <div class="ncm-flow-item">
            <div class="title">相关商品交易信息</div>
            <div class="item-goods">
                <dl>
                    <dt>
                    <div class="ncm-goods-thumb-mini">
                    	<a target="_blank" href="${BASE_URL}/front/goods/detail?skuId=${refund.skuId}">
                        <img src="${IMG_URL}${refund.goodsImage}"
                             onmouseover="toolTip('<img src=${IMG_URL}${refund.goodsImage}>')"
                             onmouseout="toolTip()"></a></div>
                    </dt>
                    <dd><a target="_blank" href="${BASE_URL}/front/goods/detail?skuId=${refund.skuId}">${refund.goodsName}</a>
                        <fmt:formatNumber value="${refund.buyPrice}" pattern="￥#,#00.00#" /> * ${refund.number}
                        <font color="#AAA">(数量)</font>
                        <span></span>
                    </dd>
                </dl>
            </div>
            <div class="item-order">
                <dl>
                    <dt>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</dt>
                    <dd>（免运费）</dd>
                </dl>
                <dl>
                    <dt>订单总额：</dt>
                    <dd><strong><fmt:formatNumber value="${refund.totalAmount}" pattern="￥#,#00.00#"/></strong></dd>
                </dl>
                <dl class="line">
                    <dt>订单编号：</dt>
                    <dd><a target="_blank" href="${BASE_URL}/front/bts/order/detail?orderId=${order.orderId}">${refund.orderNumber}</a>
                        <a href="javascript:void(0);" class="a">更多<i class="icon-angle-down"></i>
                            <div class="more"><span class="arrow"></span>
                                <ul>
<!--                                     <li>付款单号：<span>440504106597778002</span></li> -->
                                    <li>支付方式：<span>在线付款</span></li>
                            		<li>下单时间：<span><fmt:formatDate value="${refund.ctime}" pattern="yyyy-MM-dd hh:mm:ss"/></span></li>
                                </ul>
                            </div>
                        </a></dd>
                </dl>
            </div>
        </div>
    </div>
    
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script>
	$(function(){
		$('a[nctype="nyroModal"]').nyroModal();
	});
</script>