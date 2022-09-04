<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
    	<div class="modal-header">
	    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="modal_title">处理退货单</h4>
    	</div>
	    <div class="modal-body">
	    	<form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/refund/save"	method="post">
				
				<div class="form-group">
                    <label class="col-sm-3 control-label"><font class="red">* </font>订单状态</label>
                    <div class="col-sm-6">	
                        <select name="status" id="status" class="form-control">
                            <c:forEach items="${refundStatus}" var="entry">
                           		<option value="${entry.key}" <c:if test="${entry.key == refund.status}">selected</c:if>>${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="fromdecrement" class="col-sm-3 control-label">订单备注</label>
                    <div class="col-sm-6">
                         <textarea name="reason" id="reason" rows="2" class="form-control">${refund.reason}</textarea>
                    </div>
                </div>
				
				<input type="hidden" id="refundId"  name="refundId" value="${refund.refundId}" />
			</form>
	    </div>
	    <div class="modal-footer">
	    	<span id="edit_notice"></span>
	        <button type="button" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
	        <button type="button" data-dismiss="modal" class="btn btn-danger btn-sm">取消</button>
	    </div>
    </div>
</div>

<script src="${STATIC_URL}/back/modules/bts/js/refund.edit.js"></script>