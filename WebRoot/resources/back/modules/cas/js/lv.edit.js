$(document).ready(function() {
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_type = $(this).attr("data_submit_type");
		switch (submit_type) {
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
	window.location.href = BASE_URL + "/back/casuserLevel/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	var userLvId = $("input[name=userLvId]").val();
	
	if (! $("input[name=name]").val()) {
		notice('edit_notice', img_delete + ' 等级名称不能为空', true, 5000);
		return false;
	}
	
	$(".input-submit").attr('disabled', true);
	
	var saveCallBack;
	if (userLvId == '' || userLvId == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL + "/back/casuserLevel/edit");
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
        } else {
        	window.location.href = BASE_URL + "/back/casuserLevel/add";
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
        if (back_listing == true) {
        	form_cancel();
        } else {
        	window.location.href = BASE_URL + "/back/casuserLevel/add";
        }
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}
