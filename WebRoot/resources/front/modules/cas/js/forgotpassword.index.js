$(document).ready(function() {
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : form_cancel(); break;
			case 'submit_save_back' : back_listing = true; form_submit(); break;
			case 'submit_save_continue' : back_listing = false; form_submit(); break;
		}
	});
	
});


/**
 * 取消处理
 */
function form_cancel() {
	history.go(-1);
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	if (! $("#username").val()) {
		notice('edit_notice', img_delete + ' 用户名不能为空', true, 5000);
		return false;
	}
	
	if (! $("#email").val()) {
		notice('edit_notice', img_delete + ' 邮箱地址不能为空', true, 5000);
		return false;
	}
	
	$(".input-submit").attr('disabled', true);

    $("#edit_form").submit();
    return false;
}
