<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <a href="javascript:;" class="btn btn-sm btn-default" id="buttonCancel"><i class="fa fa-reply"></i> 返回</a>
                    </div>
                </div>
            </header>
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">订单信息</header>
		            <section class="scrollable wrapper panel w-f">
	                    <div class="scrollable">
	                    	<section class="col-sm-12 panel-default form-horizontal">
	                    		<div class="form-group">
	                    			<label class="col-sm-2 control-label">订单号：</label>
	                    			<lable class="col-sm-1 control-label">${order.orderNumber}</lable>
                    			</div>
	                            <div class="line line-dashed line-lg pull-in"></div>
	                           	<div class="form-group">
	                           		<label class="col-sm-2 control-label">收货人信息：</label>
	                           		<div class="col-sm-6">
	                           			收货人：${order.consigneeName}<br>
	                           			详细地址：${order.consigneeAddress}<br>
	                           			收货人邮编：${order.consigneeZipcode}<br>
	                           			收货人邮箱：${order.consigneeEmail}<br>
	                           			收货人电话：${order.consigneeMobile}<br>
                           			</div>
                      			</div>
                      			<div class="line line-dashed line-lg pull-in"></div>
                      				<div class="form-group">
                      					<label class="col-sm-2 control-label">支付及配送方式：</label>
                      					<div class="col-sm-6">
                      						支付方式：${payWay}<br>
                      						订单状态：${orderStatus}<br>
                      						付款状态：${payStatus}<br>
                      						支付类型：${payType} <br>
                      					</div>
                      				</div>
                      			<div class="line line-dashed line-lg pull-in"></div>
                   			</section>
               			</div>
          			</section>
      			</section>
      			<section class="panel panel-default portlet-item">
      				<header class="panel-heading">商品清单</header>
   					<table class="table">
   						<thead>
   							<tr>
   								<th width="120">商品图片</th>
   								<th>商品名称 </th>
   								<th width="140">购买价格</th>
   								<th width="120">购买数量</th>
   								<th width="120">是否已经评价</th>
   								<th width="120">是否延时发货</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${order.items}" var="item">
								<tr>
									<td><img src="${IMG_URL}${item.goodsImage}"></td>
									<td>${item.goodsName}</td>
									<td><fmt:formatNumber value="${item.buyPrice}" pattern="#,#00.00#"/></td>
									<td>${item.buyNum}</td>
									<td>${item.isComment == 1 ? '是 ' : '否'}</td>
									<td></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" class="text-right no-border"><strong>运费</strong></td>
								<td><fmt:formatNumber value="${order.freight}" pattern="#,#0.00#"/></td>
							</tr>
							<tr>
								<td colspan="5" class="text-right no-border"><strong>应付总额</strong></td>
								<td><strong><fmt:formatNumber value="${order.totalAmount}" pattern="#,##0.00#"/></strong></td>
							</tr>
						</tbody>
					</table> 
				</section>
			</section>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/bts/js/order.detail.js" type="text/javascript"></script>