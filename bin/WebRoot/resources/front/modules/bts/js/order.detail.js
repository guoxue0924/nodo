$(function(){
    $('.delete').click(function(){
        var orderId = $(this).attr('data-param');
        if (confirm('确定要删除吗？')){
            $.post(BASE_URL + '/front/bts/order/delete', {orderId:orderId, isDelete:1}, function(response){
                if(response.status == 0){
                	loadData();
                }
            }, 'json');
        }
    });

    $('.return').click(function(){
        var order_id = $(this).attr('data-param');
        if (confirm('确定要还原吗？')){
            $.post(BASE_URL + '/front/bts/order/delete', {orderId:orderId}, function(response){
                if(response.status == 0){
                	loadData();
                }
            }, 'json');
        }
    });

    $('.delete-forever').click(function(){
        var order_id = $(this).attr('data-param');
        if (confirm('确定要永久删除吗？')){
            $.post(BASE_URL + '/front/bts/order/delete', {orderId:orderId, isDelete:2}, function(response){
                if(response.status == 0){
                	loadData();
                }
            }, 'json');
        }
    })
})