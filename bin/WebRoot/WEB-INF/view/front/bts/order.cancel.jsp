<%@ page contentType="text/html;charset=UTF-8" %>
<div class="dialog_content" style="margin: 0px; padding: 0px;">
	<div class="eject_con">
	    <div id="warning"></div>
	    <form method="post" action="${BASE_URL}/front/bts/order/cancel" id="order_cancel_form">
	        <input type="hidden" name="orderId" value="${orderId}">
	        <dl>
	            <dt>订单号：</dt>
	            <dd><span class="num">${orderNumber}</span></dd>
	        </dl>
	        <dl>
	            <dt>取消原因：</dt>
	            <dd>
	                <ul class="eject_con-list">
	                    <li>
	                        <input type="radio" class="radio" checked="" name="state_info" id="d1" value="改买其他商品">
	                        <label for="d1">改买其他商品</label>
	                    </li>
	                    <li>
	                        <input type="radio" class="radio" name="state_info" id="d2" value="改用其他配送方式">
	                        <label for="d2">改用其他配送方式</label>
	                    </li>
	                    <li>
	                        <input type="radio" class="radio" name="state_info" id="d3" value="从其他店铺购买">
	                        <label for="d3">从其他店铺购买</label>
	                    </li>
	                    <li>
	                        <input type="radio" class="radio" name="state_info" flag="other_reason" id="d4" value="">
	                        <label for="d4">其他原因</label>
	                    </li>
	                    <li id="other_reason" style="display:none;">
	                        <textarea name="state_info1" class="textarea w300 h50" rows="2" id="other_reason_input"></textarea>
	                    </li>
	                </ul>
	            </dd>
	        </dl>
	        <div class="bottom">
	            <label class="submit-border"><input type="button" id="confirm_button" class="submit" value="确定提交"></label>
	            <a class="ncm-btn ml5" href="javascript:DialogManager.close('buyer_order_cancel_order');">取消</a>
	        </div>
	    </form>
	</div>
</div>
<script src="${STATIC_URL}/front/modules/bts/js/order.cancel.js"></script>