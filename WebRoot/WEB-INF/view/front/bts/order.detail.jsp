<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
         <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>实物交易订单</span>
    </div>
</div>

<jsp:include page="../cas/user.nav.jsp"/>
<link href="${STATIC_URL}/plugins/dialog/dialog.css" rel="stylesheet" type="text/css">
<div class="right-layout">
<div class="ncm-oredr-show">
<div class="ncm-order-info">
    <div class="ncm-order-details">
        <div class="title">订单信息</div>
        <div class="content">
            <dl>
                <dt>收货地址：</dt>
                <dd><span>${order.consigneeRegionName}</span><span>${order.consigneeAddress}</span></dd>
            </dl>
            <dl>
                <dt>发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;票：</dt>
                <dd>
                    <c:if test="${order.isInvoice == 1}">
	                    <span title="类型">普通发票 </span>
    	                <span title="抬头">${order.invoiceBelong}</span>
        	            <span title="内容">明细</span>
                    </c:if>
                </dd>
            </dl>
            <dl>
                <dt>买家留言：</dt>
                <dd>${order.remark}</dd>
            </dl>
            <dl class="line">
                <dt>订单编号：</dt>
                <dd>${order.orderNumber}<a href="javascript:void(0);">更多<i class="icon-angle-down"></i>
                    <div class="more"><span class="arrow"></span>
                        <ul>
                            <li>支付方式：<span>在线付款 </span></li>
                            <li>下单时间：<span><time><fmt:formatDate value="${order.ctime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></time></span></li>
                        </ul>
                    </div>
                </a></dd>
            </dl>
        </div>
    </div>
    <div class="ncm-order-condition">

    </div>
<!--     <div class="mall-msg">有疑问可咨询<a href="javascript:void(0);" onclick=""><i class="icon-comments-alt"></i>平台客服</a></div>
 --></div>
<c:if test="${order.status != -1}">
	<div id="order-step" class="ncm-order-step">
	    <dl class="step-first current">
	        <dt>生成订单</dt>
	        <dd class="bg"></dd>
	        <dd class="date" title="下单时间">
	        	<time><fmt:formatDate value="${order.ctime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></time>
	        </dd>
	    </dl>
	    <dl id="step0" class='<c:if test="${order.payTime != null}">current</c:if>'>
	        <dt>完成付款</dt>
	        <dd class="bg"> </dd>
	        <dd class="date" title="付款时间">
	        	<time><fmt:formatDate value="${order.payTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></time>
	        </dd>
	    </dl>
	    <dl class='<c:if test="${order.deliveryTime != null}">current</c:if>'>
	        <dt>商家发货</dt>
	        <dd class="bg"> </dd>
	        <dd class="date" title="发货时间">
	        	<time><fmt:formatDate value="${order.deliveryTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></time>
	        </dd>
	    </dl>
	    <dl class="<c:if test="${order.finishTime != null}">current</c:if>">
	        <dt>确认收货</dt>
	        <dd class="bg"> </dd>
	        <dd class="date" title="完成时间">
	        	<time><fmt:formatDate value="${order.finishTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></time>
	        </dd>
	    </dl>
	    <dl class="<c:if test="${order.commentTime != null}">current</c:if>">
	        <dt>评价</dt>
	        <dd class="bg"></dd>
	        <dd class="date" title="评价时间">
	        	<time><fmt:formatDate value="${order.commentTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></time>
	        </dd>
	    </dl>
	</div>
</c:if>

<c:if test="${kuaidi != 'null'}">
	<strong>物流信息：</strong>
	<div id="kuaidiDIV" align="left" >
		<iframe id="kuaidi"  src="${kuaidi}" width="600" height="300" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>
	</div>
</c:if>

<div id="divEx" style="display:none;height:300px;width:520px;overflow-y: auto;">  
                                          
</div> 
<div class="ncm-order-contnet">
    <table class="ncm-default-table order">
        <thead>
        <tr>
            <th class="w10"></th>
            <th colspan="2">商品名称</th>
            <th class="w20"></th>
            <th class="w120 tl">单价（元）</th>
            <th class="w60">数量</th>
<!--             <th class="w100">售后维权</th> -->
            <th class="w100">交易状态</th>
            <th class="w100 bdr">交易操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- S 商品列表 -->
        <c:forEach items="${order.items}" var="item" varStatus="status">
        <tr class="bd-line">
            <td></td>
            <td class="w70">
                <div class="ncm-goods-thumb">
                    <a href="${BASE_URL}/front/goodsContent/detail?skuId=${item.skuId}" target="_blank">
                        <img src="${IMG_URL}${item.goodsImage}" onmouseover="toolTip('<img src=${IMG_URL}${item.goodsImage}>')" onmouseout="toolTip()">
                    </a>
                </div>
            </td>
            <td class="tl">
                <dl class="goods-name">
                    <dt>
                        <a href="${BASE_URL}/front/goodsContent/detail?skuId=${item.skuId}" target="_blank" title="${item.goodsName}">${item.goodsName}</a>
                    </dt>
                </dl> 
            </td>
            <td></td>
<%--             <td class="tl">${good.buyPrice}</td> --%>
            <td class="tl">${(item.buyPrice).doubleValue()}</td>
            <td>${item.buyNum}</td>
<!--             <td> -->
            	<!-- 退款 -->
<!--             </td> -->
            <c:set value="${fn:length(order.items)}" var="size" />
        	<c:if test="${status.first}">
            <!-- S 合并TD -->
<%--             <td class="bdl" rowspan="${size}"><p><span><c:forEach items="${orderStatus}" var="entry"><c:if test="${entry.key == order.status}">${entry.value}</c:if></c:forEach></span> </p> --%>
            <td class="bdl" rowspan="${size}">
              <p>
                 <span>
	            	<c:if test="${order.status==0}">待处理 </c:if>
	            	<c:if test="${order.status==1}">已签收 </c:if>
	            	<c:if test="${order.status==2}">待付款 </c:if>
	            	<c:if test="${order.status==3}">付款成功</c:if>
	            	<c:if test="${order.status==4}">待发货</c:if>
	            	<c:if test="${order.status==5}">已发货</c:if>
				 </span> 
		      </p> 
                <!-- 物流跟踪 -->
            </td>
            <td class="bdl bdr" rowspan="${size}"><!-- 永久删除 -->

                <!--订单退款 -->
                <c:choose>
                	<c:when test="${order.status == 4}">
                		<p><a id="#order-step" href="" class="ncm-btn"><i class="icon-legal"></i>订单退款</a></p>
                	</c:when>
                	<c:when test="${order.status == 2}">
		                <p>
		                    <a href="javascript:void(0)" nc_type="dialog" dialog_width="480" dialog_title="取消订单" 
		                    	dialog_id="buyer_order_cancel_order" uri="${BASE_URL}/front/bts/order/cancel?orderId=${order.orderId}">
		                    	<i class="icon-ban-circle"></i>取消订单
		                    </a>
		                </p>
                	</c:when>
                	<c:when test="${order.status == 5}">
                		<p>
                			<a id="#order-step" href="javascript:void(0)" class="ncm-btn" nc_type="dialog" 
                				dialog_id="buyer_order_confirm_order" dialog_width="480" dialog_title="确认收货" 
                				uri="${BASE_URL}/front/bts/order/receive?orderId=${order.orderId}&orderNumber=${order.orderNumber}" 
                				id="order${order.orderId}_action_confirm">确认收货</a>
                		</p>
                	</c:when>

                	<c:when test="${order.status == 1 && order.commentTime == null}">
                		<p>
                			<a id="#order-step" class="ncm-btn ncm-btn-acidblue" 
                				href="${BASE_URL}/front/cas/comment?orderId=${order.orderId}">
                				<i class="icon-thumbs-up-alt"></i>我要评价
                			</a>
                		</p>
                	</c:when>

                	<c:when test="${order.commentTime != null}">
                		<p>
                			<a href="javascript:void(0);" onclick="" class="ncm-btn ncm-btn-red mt5 delete-forever">
                				<i class="icon-trash"></i>永久删除
                			</a>
                		</p>
                	</c:when>
                	<c:otherwise></c:otherwise>
                </c:choose>
            </td>
            <!-- E 合并TD -->
            </c:if>
        </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="20">
                <dl class="freight">
                    <dd>（免运费）</dd>
                </dl>
                <dl class="sum">
                    <dt>订单应付金额：</dt>
                    <dd><em><fmt:formatNumber value="${order.totalAmount}" pattern="#,#00.00#"/></em>元</dd>
                </dl>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
</div>
</div>
<!-- add by guoxue 2016-07-05 begin -->
<input type="hidden" id="orderNumber" value="${order.orderNumber }"/>
<input type="hidden" id="totalAmount" value="${order.totalAmount}"/>
<input type="hidden" id="kuaidInput" value="${kuaidi}"/>
<!-- add by guoxue 2016-07-05 end-->
<div class="clear"></div>
</div>
<script type="text/html" id="status1">
    <dl>
        <dt><i class="icon-ok-circle green"></i>订单状态：</dt>
        <dd>已经收货。</dd>
    </dl>
    <ul>
        <li>1. 如果收到货后出现问题，您可以联系商家协商解决。</li>
        <li>2. 如果商家没有履行应尽的承诺，您可以申请 <a href="#order-step" class="red">"投诉维权"</a>。</li>
        <li>3. 交易已完成，你可以对购买的商品及商家的服务进行<a href="#order-step" class="ncm-btn-mini ncm-btn-acidblue">评价</a>及晒单。</li>
    </ul>
</script>
<!-- modify by guoxue 2016-07-05 begin -->
<script type="text/html" id="status2">
    <dl>
        <dt><i class="icon-ok-circle green"></i>订单状态：</dt>
        <dd>订单已经提交，等待买家付款</dd>
    </dl>
    <ul>
        <li>1. 您尚未对该订单进行支付，请<a href="javascript:void(0);" class="ncm-btn-mini ncm-btn-orange"><i></i>支付订单</a>以确保商家及时发货。</li>
        <li>2. 如果您不想购买此订单的商品，请选择<a href="#order-step" class="ncm-btn-mini">取消订单</a>操作。</li>
       <!-- <li>3. 如果您未对该笔订单进行支付操作，系统将于
            <time>2016-01-01 09:24:32</time>
            自动关闭该订单。</li>-->
    </ul>
</script>

<!-- modify by guoxue 2016-07-05 end -->
<!-- modify by fxz  2016-07-06 end -->
<script type="text/html" id="status3">
    <dl>
        <dt><i class="icon-ok-circle green"></i>订单状态：</dt>
        <dd>订单已经支付完成，等待卖家发货</dd>
    </dl>
    <ul>
        <li>1.&nbsp;&nbsp;您的订单已经支付完成，您还可以<a href="${BASE_URL}/front/index/index;" class="ncm-btn-orange"><i></i>继续购物</a>！</li>
        <li>2.&nbsp;&nbsp;订单已提交商家进行备货发货准备！</li>
       <!-- <li>3. 如果您未对该笔订单进行支付操作，系统将于
            <time>2016-01-01 09:24:32</time>
            自动关闭该订单。</li>-->
    </ul>
</script>
<!-- modify by fxz 2016-07-06 end -->
<script type="text/html" id="status4">
    <dl>
        <dt><i class="icon-ok-circle green"></i>订单状态：</dt>
        <dd>
            已支付成功
        </dd>
    </dl>
    <ul>
        <li>1. 您已使用“在线付款”方式成功对订单进行支付，支付单号 “{/literal}{$order.out_order_number}{literal}”。</li>
        <li>2. 订单已提交商家进行备货发货准备。</li>
        <li>3. 如果您想取消购买，请与商家沟通后对订单进行<a class="ncm-btn-mini" href="#order-step">申请退款</a>操作。</li>
    </ul>
</script>
<script type="text/html" id="status5">
    <dl>
        <dt><i class="icon-ok-circle green"></i>订单状态：</dt>
        <dd>商家已发货
        </dd>
    </dl>
    <ul>
        <li>1. 商品已发出；
            无需要物流。
        </li>
        <li>2. 如果您已收到货，且对商品满意，您可以<a href="#order-step" class="ncm-btn-mini ncm-btn-green">确认收货</a>完成交易。</li>
        <li>3. 系统将于
            <time></time>
            自动完成“确认收货”，完成交易。</li>
    </ul>
</script>
<script type="text/html" id="status-1">
    <dl>
        <dt><i class="icon-off orange"></i>订单状态：</dt>
        <dd>交易关闭</dd>
    </dl>
    <ul>
        <li> 于 </li>
    </ul>
</script>
<script type="text/javascript">
    var status = 'status' + ${order.status};
    $('.ncm-order-condition').html(template(status));
</script>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script src="${STATIC_URL}/plugins/dialog/dialog.js"></script>
<script src="${STATIC_URL}/front/modules/bts/js/order.detail.js"></script>