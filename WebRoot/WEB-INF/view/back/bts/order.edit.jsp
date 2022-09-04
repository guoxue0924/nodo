<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
    	<div class="modal-header">
	    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="modal_title">处理订单</h4>
    	</div>
	    <div class="modal-body">
	    	<form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/order/save"	method="post">
				
				<div class="form-group">
                    <label class="col-sm-3 control-label"><font class="red">* </font>订单状态</label>
                    <div class="col-sm-6">	
                        <select name="status" id="status" class="form-control">
                            <c:forEach items="${orderStatus}" var="entry">
                           		<option value="${entry.key}" <c:if test="${entry.key == order.status}">selected</c:if>>${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label"><font class="red">* </font>支付状态</label>
                    <div class="col-sm-6">
                        <select name="payStatus" id="paymentStatus" class="form-control">
                            <c:forEach items="${paymentStatus}" var="entry">
                           		<option value="${entry.key}" <c:if test="${entry.key == order.payStatus}">selected</c:if>>${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label"><font class="red">* </font>支付类型</label>
                    <div class="col-sm-6">
              			在线支付
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">物流公司</label>
                    <div class="col-sm-6">
                        <select name="logistics" id="logisticsId" class="form-control">
                        	<c:forEach items="${logistics}" var="entry">
							<%--  <option value="${entry.key}">${entry.value}</option> --%>
                           		<option value="${entry.key}" <c:if test="${entry.key == order.logisticsCom}">selected</c:if>>${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">运单号</label>
                    <div class="col-sm-6">
                       <input type="text" class="form-control" id="logisticsNumber" name="logisticsNumber" value="${order.logisticsNumber}" placeholder="请输入运单号码">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="fromdecrement" class="col-sm-3 control-label">订单备注</label>
                    <div class="col-sm-6">
                         <textarea name="remark" id="remark" rows="2" class="form-control">${order.remark}</textarea>
                    </div>
                </div>
				
				<input type="hidden" id="orderId"  name="orderId" value="${order.orderId}" />
			</form>
	    </div>
	    <div class="modal-footer">
	    	<span id="edit_notice"></span>
	        <button type="button" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
	        <button type="button" data-dismiss="modal" class="btn btn-danger btn-sm">取消</button>
	    </div>
    </div>
</div>

<script src="${STATIC_URL}/back/modules/bts/js/order.edit.js"></script>