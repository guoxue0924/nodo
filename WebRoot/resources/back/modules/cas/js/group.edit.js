$(document).ready(function() {
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : group_cancel(); break;
			case 'submit_save_back' : back_listing = true; group_submit(); break;
			case 'submit_save_continue' : back_listing = false; group_submit(); break;
		}
	});
});


/**
 * 取消处理
 */
function group_cancel() {
//	history.go(-1);
	window.location.href = BASE_URL + "/back/casuserGroup/index";
}

/**
 * 角色表单提交处理
 */
function group_submit() {
	notice('editNotice', img_loading_small, false);
	
	if (! $("input[name=groupName]").val()) {
		notice('editNotice', img_delete + ' 分组名称不能为空', true, 5000);
		return false;
	}
	
	var userGroupId = $("input[name=userGroupId]:last").val();
	
	$(".input-submit").attr('disabled', true);
	
	var saveCallBack;
	if (userGroupId == '' || userGroupId == 0) {
		saveCallBack = group_save_added;
	} else {
		$("#editForm").attr("action", BASE_URL + "/back/casuserGroup/edit");
		saveCallBack = group_save_edited;
	}
	
	
	var options = {
            dataType:'json',
            timeout:60000,
            success:saveCallBack,
            error:ajaxError
    };
    $("#editForm").ajaxSubmit(options);
    return false;
}

/**
 * 添加用户组成功，返回处理
 */
function group_save_added(data, textStatus) {
    if (data.status === 0) {
        notice('editNotice', img_done + ' 添加用户组成功!', true, 5000);
        
        // 判断是否返回用户组列表管理
        if (back_listing == true) {
        	group_cancel();
        } else {
        	window.location.href = BASE_URL + "/back/casuserGroup/add";
        }
    } else {
    	notice('editNotice', img_delete + " " + data.error, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}

/**
 * 编辑用户组成功，返回处理
 */
function group_save_edited(data, textStatus) {
    if (data.status === 0) {
        notice('editNotice', img_done + ' 编辑成功!', true, 5000);
        if (back_listing == true) {
        	group_cancel();
        } else {
        	window.location.href = BASE_URL + "/back/casuserGroup/add";
        }
    } else {
    	notice('editNotice', img_delete + " " + data.error, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}