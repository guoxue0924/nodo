$(document).ready(function() {
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		form_submit();
	});
	
         
});  


/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	var orderId = $("#orderId").val();
	
	if (! $("#edit_form #status").val()) {
		notice('edit_notice', img_delete + ' 请选择订单状态', true, 5000);
		return false;
	}
	
	if(! $("#edit_form #paymentStatus").val()) {
		notice('edit_notice', img_delete + ' 请选择支付状态', true, 5000);
		return false;
	}
	
	$(".input-submit").attr('disabled', true);
	
	var saveCallBack = form_save_edited;
	
	var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
        error:ajaxError
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
    if (data.status === 0) {
        notice('edit_notice', img_done + ' 编辑成功!', true, 5000);
        $('#content_listing').datagrid('reload');
        $('.modal-footer .input-submit').next().click();
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}

