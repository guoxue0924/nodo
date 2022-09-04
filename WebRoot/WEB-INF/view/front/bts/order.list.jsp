<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<input type="hidden" id="count" value="${page.count}">
<c:forEach items="${page.data}" var="order">
<table>
<tbody order_id="${order.orderId}" <c:if test="${order.payStatus == waitPayStatus}">class="pay"</c:if>>
	<tr>
		<td colspan="19" class="sep-row"></td>
	</tr>
	<c:if test="${order.payStatus == waitPayStatus}">
		<tr>
			<td colspan="19" class="pay-td">
				<span class="ml15">在线支付金额：<em><fmt:formatNumber value="${order.totalAmount}" pattern="￥#,#00.00#"/></em></span>
				<a class="ncm-btn ncm-btn-orange fr mr15" href="${BASE_URL}/front/bts/order/pay?orderId=${order.orderId}">
<%-- 				<a class="ncm-btn ncm-btn-orange fr mr15" href="${BASE_URL}/front/bts/order/pay?orderId=${order.orderNumber}"> --%>
					<i class="icon-shield"></i>订单支付
				</a>
			</td>
		</tr>
	</c:if>
	<tr>
		<th colspan="19">
			<span class="ml10">订单号：${order.orderNumber}</span>
			<span>下单时间：<fmt:formatDate value="${order.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			<c:choose>
				<c:when test="${order.isDelUser == userDelStatus}">
		            <a href="javascript:void(0)" class="order-trash return" data-param='${order.orderId}'><i class="icon-refresh"></i>还原</a>
				</c:when>
				<c:otherwise>
					<c:if test="${ order.isDelUser != userDelStatus && (order.status == 1 || order.status == -1)}">
	            		<a href="javascript:void(0)" class="order-trash delete" data-param='${order.orderId}'><i class="icon-trash"></i>删除</a>
					</c:if>		
				</c:otherwise>
			</c:choose>
		</th>
	</tr>
	<!-- S 商品列表 -->
	<c:forEach items="${order.items}" var="item" varStatus="status">
	<tr>
		<td class="bdl"></td>
		<td class="w70">
       		<div class="ncm-goods-thumb">
       			<a href="${BASE_URL}/front/goodsContent/detail?skuId=${item.skuId}" target="_blank">
       				<img src="${IMG_URL}${item.goodsImage}" 
       					onmouseover="toolTip('<img src=${IMG_URL}${item.goodsImage}>')" 
       					onmouseout="toolTip()">
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
       	<td><fmt:formatNumber value="${item.buyPrice}" pattern="￥#,#00.00#"/></td>
       	<td>${item.buyNum}</td>
	          	
       	<td><!-- 退款 -->
       		<c:if test="${order.isRefund == 0 && order.status == 4}">
       			<p><a href="${BASE_URL}/front/bts/refund/add?itemId=${item.itemId}">退货退款</a></p>
       		</c:if>
   		</td>
	       		
        <!-- S 合并TD -->
        <c:set value="${fn:length(order.items)}" var="size" />
        <c:if test="${status.first}">
       	<td class="bdl" rowspan="${size}">
       		<p class=""><strong><fmt:formatNumber value="${item.buyPrice * item.buyNum}" pattern="￥#,#00.00#"/></strong></p>
       		<p class="goods-freight">（免运费）</p>
       		<p title="支付方式：在线付款">在线付款</p>
       	</td>
       	
       	<td class="bdl" rowspan="${size}">
       		<p><span style="color:#36C"><c:forEach items="${orderStatus}" var="entry"><c:if test="${entry.key == order.status}">${entry.value}</c:if></c:forEach> </span> </p>
           	<!-- 订单查看 -->
           	<p><a href="${BASE_URL}/front/bts/order/detail?orderId=${order.orderId}" target="_blank">订单详情</a></p>
           	<!-- 物流跟踪 -->
       	</td>
       	<td class="bdl bdr" rowspan="${size}"><!-- 永久删除 -->
       		<!-- 取消订单 -->
       		<c:if test="${order.status == 2}">
        		<p>
        			<a href="javascript:void(0)" class="ncm-btn ncm-btn-red" nc_type="dialog" dialog_width="480" 
                		dialog_title="取消订单" dialog_id="buyer_order_cancel_order" 
                		uri="${BASE_URL}/front/bts/order/cancel?orderId=${order.orderId}&orderNumber=${order.orderNumber}" 
                		id="order${order.orderId}_action_cancel">
                		<i class="icon-ban-circle"></i>取消订单
            		</a>
         		</p>
        		</c:if>
           	<!--订单退款 -->
           	<!-- 收货 -->
           	<c:if test="${order.status == 5}">
           		<p>
           			<a href="javascript:void(0)" class="ncm-btn" nc_type="dialog" dialog_id="buyer_order_confirm_order" 
           				dialog_width="480" dialog_title="确认收货" 
           				uri="${BASE_URL}/front/bts/order/receive?orderId=${order.orderId}&orderNumber=${order.orderNumber}" 
           				id="order${order.orderId}_action_confirm">确认收货</a>
           		</p>
           	</c:if>
           	<!-- 评价 -->
           	<c:if test="${order.status == 1 && item.isComment == 0}">
           		<p>
           			<a class="ncm-btn ncm-btn-acidblue" href="${BASE_URL}/front/cas/comment/add?orderId=${order.orderId}">
           				<i class="icon-thumbs-up-alt"></i>我要评价
           			</a>
           		</p>
           	</c:if>
           	<!-- 已经评价 -->
           	<c:if test="${order.status == 1 && item.isComment == 1}">
           		<p>
           			<a href="javascript:void(0);" data-param='${order.orderId}' class="ncm-btn ncm-btn-red mt5 delete-forever">
           				<i class="icon-trash"></i>永久删除
           			</a>
           		</p>
           	</c:if>
       	</td>
       	</c:if>
       	<!-- E 合并TD -->
   	</tr>
   	</c:forEach>
</tbody>
</table>
</c:forEach>
