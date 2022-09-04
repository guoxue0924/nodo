$(function () {
    $('select[nctype="voucher"]').on('change',function(){
        if ($(this).val() == -1) {
            $('#eachStoreVoucher').html('-0.00');
        } else {
            var items = $(this).val().split('|');
            $('#eachStoreVoucher').html('-'+number_format(items[1],2));
        }
        calcOrder();
    });

    //加载收货地址列表
    $('#edit_reciver').on('click',function(){
        $(this).hide();
        disableOtherEdit('如需修改，请先保存收货人信息 ');
        $(this).parent().parent().addClass('current_box');
        $('#addr_list').load(BASE_URL + '/front/cas/consignee/changeAddress');
    });

    //加载发票列表
    $('#edit_invoice').on('click',function(){
        $(this).hide();
        disableOtherEdit('如需修改，请先保存发票信息');
        $(this).parent().parent().addClass('current_box');
        $('#invoice_list').load(BASE_URL + '/front/cas/consignee/editInvoice');
    });

    ableSubmitOrder();
});

function calcOrder() {
    var allTotal = 0;

        var eachTotal = 0;
        if ($('#eachStoreFreight').length > 0) {
            eachTotal += parseFloat($('#eachStoreFreight').html());
        }
        if ($('#eachStoreGoodsTotal').length > 0) {
            eachTotal += parseFloat($('#eachStoreGoodsTotal').html());
        }
        if ($('#eachStoreManSong').length > 0) {
            eachTotal += parseFloat($('#eachStoreManSong').html());
        }
        if ($('#eachStoreVoucher').length > 0) {
            eachTotal += parseFloat($('#eachStoreVoucher').html());
        }

        allTotal += eachTotal;

    $('#orderTotal').html(number_format(allTotal,2));
}

//隐藏发票列表
function hideInvList(content) {
    $('#edit_invoice').show();
    $("#invoice_list").html('<ul><li>'+content+'</li></ul>');
    $('.current_box').removeClass('current_box');
    ableOtherEdit();
    //重新定位到顶部
    $("html, body").animate({ scrollTop: 0 }, 0);
}

function hideAddrList(consigneeId,true_name,address,mobile) {
    $('#edit_reciver').show();
    $("#consigneeRegionId").val(consigneeId);
    $("#addr_list").html('<ul><li><span class="true-name">'+true_name+'</span><span class="address">'+address+'</span><span class="mobile"><i class="icon-mobile-phone"></i>'+mobile+'</span></li></ul>');
    $('.current_box').removeClass('current_box');
    ableOtherEdit();
    $('#edit_payment').click();
}

function disableOtherEdit(showText){
    $('a[nc_type="buy_edit"]').each(function(){
        if ($(this).css('display') != 'none'){
            $(this).after('<font color="#B0B0B0">' + showText + '</font>');
            $(this).hide();
        }
    });
    disableSubmitOrder();
}
function ableOtherEdit(){
    $('a[nc_type="buy_edit"]').show().next('font').remove();
    ableSubmitOrder();

}
function ableSubmitOrder(){
    $('#submitOrder').on('click',function(){
    	if (validata()){
    		form_submit();
        }
    	}).css('cursor','').addClass('ncc-btn-acidblue');
}
function disableSubmitOrder(){
    $('#submitOrder').unbind('click').css('cursor','not-allowed').removeClass('ncc-btn-acidblue');
}

function form_submit(){
	
    var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
        type:'post'
    };
    $("#order_form").ajaxSubmit(options);
    return false;
}

function form_save_added(data){
    if (data.status == 0){
        location.href = BASE_URL +"/front/bts/order/gopay?orderNumber="+data.data;
    } else {
        alert(data.msg);
    }
}

function validata(){
	
    $consigneeId = $('#consigneeId');
    if (! $consigneeId.val()){
        showError($consigneeId, '收货地址不能为空！');
        return false;
    }

    return true;
}