<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/inc/header_front.jsp"/>
<jsp:include page="../index/inc/main.top.jsp"/>
<link href="${STATIC_URL}/front/modules/bts/css/home_cart.css" rel="stylesheet" type="text/css">
<header class="ncc-head-layout">
    <div class="site-logo"><a href="${BASE_URL}/front/index"><img src="${STATIC_URL}/front/images/front/logo.png" class="pngFix"></a></div>
    <ul class="ncc-flow">
        <li class=""><i class="step1"></i>
            <p>我的购物车</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
        <li class=""><i class="step2"></i>
            <p>填写核对购物信息</p>
            <sub></sub>
            <div class="hr"></div>
        </li>
        <li class="current"><i class="step3"></i>
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
<body>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orderNumber = request.getParameter("orderNumber");//获取订单号
String totalAmount = request.getParameter("totalAmount");//获取支付总金额

%>
</body>
<div class="ncc-wrapper">
    <div class="ncc-main">
        <div class="ncc-title">
            <h3>支付提交</h3>
            <h5>订单详情内容可通过查看<a href="${BASE_URL}/front/bts/order" target="_blank">我的订单</a>进行核对处理。</h5>
        </div>
        <form action="" method="POST" id="buy_form">
            <input type="hidden" name="pay_sn" value="">
            <input type="hidden" id="payment_code" name="payment_code" value="">
            <div class="ncc-receipt-info">
                <div class="ncc-receipt-info-title">
                    <h3>请您及时付款，以便订单尽快处理！                    在线支付金额：
                      <strong>
                    	<fmt:formatNumber value="<%=totalAmount%>" pattern="￥#,#00.00#"/>
                      </strong>
                    </h3>
                </div>
                <table class="ncc-table-style">
                    <thead>
                    <tr>
                        <th class="w50"></th>
                        <th class="w200 tl">订单号</th>
                        <th class="tl w150">支付方式</th>
                        <th class="tl">金额</th>
                        <th class="w150">物流</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td></td>
                        <td class="tl" id="ordernubmer">
                        <%=orderNumber%>
                        </td>
                        <td class="tl">在线支付</td>
                        <td class="tl"><fmt:formatNumber value="<%=totalAmount%>" pattern="￥#,#00.00#"/></td>
                        <td>快递</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="ncc-receipt-info">
                <div class="ncc-receipt-info-title">
                    <h3>支付选择</h3>
                </div>
                <ul class="ncc-payment-list">
                    <li payment_code="alipay" class="using">
                        <label for="pay_alipay">
                            <i></i>
                            <div class="logo" for="pay_2">
                           	  <img src="${STATIC_URL}/front/images/bts/alipay_logo.gif"  id="alipay1"/> 
                           	</div>
                        </label>
                    </li> 
                </ul>
            </div>
<!--             <div class="ncc-bottom tc mb50"><a href="javascript:void(0)" onclick="orderPay();" class="ncc-btn ncc-btn-green"><i class="icon-shield"></i>确认提交支付</a></div> -->
			<div class="ncc-bottom tc mb50">
				<a href="${BASE_URL}/front/bts/order/pay/useAliPay?orderNumber=<%=orderNumber%>"
					 class="ncc-btn ncc-btn-green">
					<i class="icon-shield"></i>确认提交支付</a>
			</div>
		</form>
    </div>   
</div>
<script src="${STATIC_URL}/front/modules/bts/js/order.gopay.js"></script>