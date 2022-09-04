$(function() {
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : form_cancel(); break;
			case 'submit_save_back' : back_listing = true; form_submit(); break;
		}
	});
    
});

/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL + "/back/pageContent/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	if (! $("#id").val()) {
		if (! $("#pageUrl").val() || ! $("#title").val()) {
			notice('edit_notice', img_delete + ' 网页名称及网页标题不能为空', true, 5000);
			return false;
		}
	} else {
		if (! $("#title").val()) {
			notice('edit_notice', img_delete + ' 网页标题不能为空', true, 5000);
			return false;
		}
	}
	
	$(".input-submit").attr('disabled', true);
	
	/* 更新编辑器状态 */
	CKupdate();
	
	var contentId = $("#id").val();
	
	var saveCallBack;
	if (contentId == '' || contentId == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL +"/back/pageContent/edit");
		saveCallBack = form_save_edited;
	}
	
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
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
    if(data.status === 0) {
        notice('edit_notice', img_done + ' 添加成功!', true, 5000);
        
        // 判断是否返回列表管理
        if (back_listing == true) {
        	form_cancel();
        }
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
    if (data.status === 0) {
        notice('edit_notice', img_done + ' 编辑成功!', true, 5000);
        form_cancel();
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}