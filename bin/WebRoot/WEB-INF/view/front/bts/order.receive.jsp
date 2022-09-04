<%@ page contentType="text/html;charset=UTF-8" %>
<div class="eject_con">
    <div id="warning"></div>
    <form action="${BASE_URL}/front/bts/orderReceive" method="post" id="order_confirm_form" >
        <h3 class="orange">您是否确已经收到以下订单的货品?</h3>
        <dl>
            <dt>订单编号：</dt>
            <dd>${orderNumber}        <p class="hint">请注意： 如果你尚未收到货品请不要点击“确认”。大部分被骗案件都是由于提前确认付款被骗的，请谨慎操作！ </p>
            </dd>
        </dl>
        <div class="bottom">
            <label class="submit-border">
                <input type="button" class="submit" id="confirm_yes" value="确定">
            </label>
            <a class="ncm-btn ml5" href="javascript:DialogManager.close('buyer_order_confirm_order');">取消</a>
        </div>
        <input type="hidden" name="orderId" value="${orderId}"/>
    </form>
</div>
<script src="${STATIC_URL}/front/modules/bts/js/order.receive.js"></script>