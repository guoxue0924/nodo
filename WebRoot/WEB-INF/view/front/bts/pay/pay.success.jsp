<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../../index/inc/header_front.jsp"/>
<jsp:include page="../../index/inc/main.top.jsp"/>
<link href="${STATIC_URL}/front/modules/bts/css/home_cart.css" rel="stylesheet" type="text/css">
<header class="ncc-head-layout">
  <div class="site-logo" style="height: 80px;width: 500px">
  	<a href="${BASE_URL}/front/index">
  		<img src="${STATIC_URL}/front/images/bts/ALLINPAY-LOGO.png" class="pngFix" style="height: 50px;width: 250px">
  	</a>
  </div> 
</header>
<div class="ncc-wrapper">
    <div class="ncc-main" style="width: 800px">
        <div class="ncc-title">
            <h3><font size="4" color="#2A97DB">恭喜你，此订单支付成功！</font></h3>
        </div>
        
        <div >
           	<img alt="" src="${STATIC_URL}/front/images/bts/pay_bank/icbc.gif" style="height: 80px;width: 350px">
        </div>
        <div class="ncc-title">
            <a href="${BASE_URL}/front/bts/order/pay/toOrder?orderNumber=${order.orderNumber}">
					<font size="4" color="#CD3D61">查看订单信息</font>
			</a>
        </div>
    </div>
</div>
<jsp:include page="../../index/wrapper.suffix_front.jsp"/>
