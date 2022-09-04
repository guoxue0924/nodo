/**
 * @author George on 15/4/16.
 * @updated by zhangr01 on 15/6/26.
 */

$(function () {
    /* 登录提交 */
    $("#loginSubmit").click(function () {
    	$("#loginForm").ajaxSubmit({
    		dataType:'json',
            timeout:60000,
    		success: function (data) {
                if (data.status == 0) {
                    location.href = data.data;
                } else {
                	alert(data.error);
                }
            }
        });
    });

    /* 注册提交 */
    $("#signUpSubmit").click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $("#signUpForm").ajaxSubmit({
        	dataType:'json',
            timeout:60000,
        	success: function (data) {
                if (data.status == 1) {
                	alert(data.error);
                } else if (data.status == 0) {
                    alert("注册成功！");
                    location.href = data.data;
                }
            }
        });
    });

    /* 注销 */
    $("#signOutSubmit").click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $.post('/cas/sign/out',function(){

        });

    });


    /* 地区选择联动 */
    $("body").on('change', '.regionSelect', function () {
        var region_id = $(this).val();
        var select = $(this);
        $.post('/cas/address/getChildrenRegion', {"region_id": region_id}, function (data) {
            if (data.status == 1) {
                alert(data.error);
            } else {
                if (data.data == null) {
                    return false
                }
                var selectHtml = '<select name="region[]" class="regionSelect">'
                $.each(data.data, function (index, data) {
                	if(data.region_name != undefined) {
                		selectHtml += '<option value="' + data.region_id + '">' + data.region_name + '</option>';
                	}
                });
                selectHtml += '</select>'
            }
            select.nextAll('select').remove();
            select.after(selectHtml);
        },"json");
    });

    /* 新增地址提交 */
    $("#addAddressSubmit").click(function(data){
        $("#addAddressForm").ajaxSubmit({
            success:function(data){
                if (data.status == 1) {
                    alert(data.error);
                } else if (data.status == 0) {
                    alert("新增地址成功！");
                    location.reload();
                }
            }
        },"json");
    });

    /* 更新地址提交 */
    $(".updateAddressSubmit").click(function(data){
        $(this).parents('form').ajaxSubmit({
        	dataType:'json',
            timeout:60000,
        	success:function(data){
                if (data.status == 1) {
                    alert(data.error);
                } else if (data.status == 0) {
                    alert("编辑地址成功！");
                    location.reload();
                }
            }
        });
    });

    /* 删除地址提交 */
    $(".deleteAddressSubmit").click(function(data){
        var consignee_id = $(this).data('consignee_id');
        if(!confirm('是否删除地址')){
            return false;
        }
        $.post('/cas/address/deleteAddress',{"consignee_id":consignee_id},function(data){
            if (data.status == 1) {
                alert(data.error);
            } else if (data.status == 0) {
                alert("删除地址成功！");
                location.reload();
            }
        },"json");
    });
    
    /* 默认地址提交 */
    $(".defaultAddressSubmit").click(function(data){
        var consignee_id = $(this).data('consignee_id');
        $.post('/cas/address/defaultAddress',{"consignee_id":consignee_id},function(data){
            if (data.status == 1) {
                alert(data.error);
            } else if (data.status == 0) {
                alert("默认地址设定成功！");
                location.reload();
            }
        },"json");
    });

    /* 删除评论提交 */
    $(".deleteComment").click(function(){
        var id = $(this).data('id');
        if(!confirm('是否删除评论')){
            return false;
        }
        $.post('/cas/comment/deleteComment',{"id":id},function(data){
            if (data.status == 1) {
                alert(data.error);
            } else if (data.status == 0) {
                alert("删除评论成功！");
                location.reload();
            }
        },"json");
    });

    /* 删除收藏提交 */
    $(".deleteFavorite").click(function(){
        var id = $(this).data('id');
        if(!confirm('是否删除收藏')){
            return false;
        }
        $.post('/cas/favorite/deleteFavorite',{"id":id},function(data){
            if (data.status == 1) {
                alert(data.error);
            } else if (data.status == 0) {
                alert("删除收藏成功！");
                location.reload();
            }
        },"json");
    });

    /* 提交留言 */
    $("#messageSubmit").click(function(){
        $('#messageForm').ajaxSubmit({
        	dataType:'json',
            timeout:60000,
        	success:function(data){
                if (data.status == 1) {
                    alert(data.error);
                } else if (data.status == 0) {
                    alert("留言提交成功！");
                    location.reload();
                }
            }
        });
    });
    
    /* 取消订单 */
    $(".cancelOrderSubmit").click(function(){
        var id = $(this).data('order_id');
        if(!confirm('是否取消订单')){
            return false;
        }
        $.post('/cas/order/cancelOrder',{"id":id},function(data){
            if (data.status == 1) {
                alert(data.error);
            } else if (data.status == 0) {
                alert("取消订单成功！");
                location.reload();
            }
        },"json");
    });
    
    /* 删除订单 */
    $(".delOrderSubmit").click(function(){
        var id = $(this).data('order_id');
        if(!confirm('是否删除订单')){
            return false;
        }
        $.post('/cas/order/delOrder',{"id":id},function(data){
        	console.log(data.status);
            if (data.status == 1) {
                alert(data.error);
            } else if (data.status == 0) {
                alert("删除订单成功！");
                location.reload();
            }
        },"json");
    });

});