$(document).ready(function() {
    /**
	 * 返回
	 */
    $("#button_cancel").click(function(){
//    	history.go(-1);
    	window.location.href = BASE_URL + "/back/casuser/index";
	});
    
    var userid = $('#userid').val();
    /**
     * 订单记录
     */
    $('#userinfo_order').load(BASE_URL + '/back/order/indexOrderDetail?userid='+userid);
    /**
     * 优惠券记录
     */
    $('#userinfo_coupon').load(BASE_URL + '/back/couponListing/indexUserDetail?userid='+userid);
});