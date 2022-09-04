$(function go_order_pay(orderNumber){
	$('#selectAll').on('click',function(){
		$.post(BASE_URL + '/front/bts/order/pay/payOrder', {orderNumber:orderNumber}, function(result){
			  if(result.status == 0){
				  alert('支付.....成功');
			  }else{
				  alert('支付.....失败');
			  }
		})
	})
	
})
