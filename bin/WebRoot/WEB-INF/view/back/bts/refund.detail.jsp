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
		            <section class="scrollable wrapper panel w-f ">
	                    <div class="scrollable">
	                    	<section class="col-sm-12 panel-default form-horizontal">
	                    		<div class="form-group">
	                    			<label class="col-sm-2 control-label">退单号：</label>
                    				<lable class="col-sm-1 control-label">${refund.refundSn}</lable>
                    				<label class="col-sm-2 control-label">订单号：</label>
                   					<lable class="col-sm-1 control-label">${refund.orderNumber}</lable>
                            </div>
                            <div class="line line-dashed line-lg pull-in"></div>
                            <div class="form-group">
                            	<label class="col-sm-2 control-label">退货信息：</label>
                            	<div class="col-sm-6">
                            		退单状态：${status}<br>
                            		退货人：${refund.username }<br>
                            		退货物品：${refund.goodsName}<br>
                            		退货原因：${refund.reason}<br>
                           		</div>
                           </div>
                           <div class="line line-dashed line-lg pull-in"></div>
                            <div class="form-group">
                               <label class="col-sm-2 control-label">退货图片：</label>
                               <div class="col-sm-6">
                               	<c:forEach items="${refund.picList}" var="pic">
	                               	<a href="${IMG_URL}${pic}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${pic}"></a>
                               	</c:forEach>
								</div>
                           </div>
                        </section>
               			</div>
          			</section>
      			</section>
			</section>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/bts/js/refund.detail.js" type="text/javascript"></script>