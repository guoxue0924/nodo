<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/inc/header_front.jsp"/>
<jsp:include page="../index/inc/main.top.jsp"/>
<link href="${STATIC_URL}/front/modules/bts/css/home_cart.css" rel="stylesheet" type="text/css">
<header class="ncc-head-layout">
  <div class="site-logo" style="height: 80px;width: 500px">
  	<a href="${BASE_URL}/front/index">
  		<img src="${STATIC_URL}/front/images/bts/ALLINPAY-LOGO.png" class="pngFix" style="height: 50px;width: 250px">
  		&nbsp;&nbsp;&nbsp;
  		<font size="6" color="#4C87BB">|&nbsp;&nbsp;我的收银台</font>
  	</a>
  </div> 
</header>
<div class="ncc-wrapper">
    <div class="ncc-main" style="width: 800px">
        <div class="ncc-title">
            <h3>正在支付...</h3>
        </div>
        <font size="3" color="#000000">此订单需要支付599元</font>
		</br>     
		</br>     
        <div>
        	<tr>
				<font size="4" color="#2A97DB">商&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;号:</font>
<%-- 				<td> <%=inputCharset%></td> --%>
				<font size="4" color="#2A97DB">0001</font>
			</tr>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<tr>
				<font size="4" color="#2A97DB">付款人姓名:</font>
<%-- 				<td> <%=pickupUrl%></td> --%>
				<font size="4" color="#2A97DB">张三</font>
			</tr>
		</div>
		</br>
		<div>
			<tr>
				<font size="4" color="#2A97DB">商户订单号:</font>
<%-- 				<td> <%=receiveUrl%></td> --%>
				<font size="4" color="#2A97DB">117</font>
			</tr>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<tr>
				<font size="4" color="#2A97DB">商户订单金额:</font>
<%-- 				<td> <%=version%></td> --%>
				<font size="4" color="#2A97DB">599</font>
			</tr>
		</div>
		</br>
		<div>
			<tr>
				<font size="4" color="#2A97DB">订单金额币种类型:</font>
<%-- 				<td><%=language %></td> --%>
				<font size="4" color="#2A97DB">人民币</font>
			</tr>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<tr>
				<font size="4" color="#2A97DB">支付方式:</font>
<%-- 				<td><%=signType%></td> --%>
				<font size="4" color="#2A97DB">个人储蓄卡网银支付</font>
			</tr>
		</div>
		</br>
		<div>
			<!-- 买卖双方信息参数 -->
			<tr>
				<font size="4" color="#2A97DB">商户订单提交时间:</font>
<%-- 				<td> <%=merchantId%></td> --%>
				<font size="4" color="#2A97DB">00014</font>
			</tr>
        </div>
        </br>
        <div >
           	<table style="height: 60px;width: 530px">
           		<td>
           			<img alt="" src="${STATIC_URL}/front/images/bts/pay_bank/icbc.gif" style="height: 80px;width: 350px">
           		</td>
           		<td>
           			<h1>***5142&nbsp;&nbsp;&nbsp;&nbsp;储蓄卡&nbsp;&nbsp;|&nbsp;&nbsp;快捷</h1>
           		</td>
           	</table>
        </div>
        </br>
        <div class="ncc-title">
           <a href="#pay">
				<font size="4" color="#2A97DB">其他方式支付</font>
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#pay">
				<font size="4" color="#2A97DB">其他方式支付</font>
			</a>
        </div>
        
        <div class="ncc-title">
            <font color="red" size="2">请输入支付密码</font>
				<br />
				<br />
		
			<input style="height: 15px; width: 15px;"/>
			<input style="height: 15px; width: 15px;"/>
			<input style="height: 15px; width: 15px;"/>
			<input style="height: 15px; width: 15px;"/>
			<input style="height: 15px; width: 15px;"/>
			<input style="height: 15px; width: 15px;"/>
			&nbsp;&nbsp;&nbsp;
			<a href="#needPassword">忘记密码</a>
        </div>
        </br>
        <div class="ncc-title">
            <a href="${BASE_URL}/front/bts/order/pay/useTLGetPay?orderNumber=${order.orderNumber}">
					<font size="4" color="#CD3D61">确认支付</font>
			</a>
        </div>
        <div class="ncc-title">
            <h3>支付完成</h3>
        </div>
    </div>
</div>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script>
	function pay() {
		alert('支付功能暂未开通!');
	}
</script>