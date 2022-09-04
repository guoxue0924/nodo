$(document).ready(function() {
	/**
	 * 提交按钮处理
	 */
	$("input.input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : form_cancel(); break;
			case 'submit_save_back' : back_listing = true; form_submit(); break;
			case 'submit_save_continue' : back_listing = false; form_submit(); break;
		}
	});
});


/**
 * 表单提交处理
 */
function form_submit() {
	if (! $("input[name=regionName]").val()) {
		alert('地区名称不能为空');
		return false;
	}
	
	var category_id = $("input[name=regionId]").val();
	var saveCallBack;
	if (category_id == '' || category_id == 0) {
		saveCallBack = form_save_added;
	} else {
		
		$("#edit_form").attr("action", BASE_URL + "/back/trend/region/update");
		saveCallBack = form_save_edited;
	}
	var options = {
            dataType:'json',
            timeout:60000,
            success:saveCallBack
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
    if (data.status === 0) {
        alert('添加成功!');
        
        // 判断是否返回列表管理
        
        	$('#modal_brand').modal('hide');
        	window.location.href = BASE_URL + "/back/trend/region"; 
            $('.close').trigger('click');
        
    } else {
    	alert(data.msg);
    }
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
    if (data.status === 0) {
        alert('编辑成功!');
//        $('#category_listing').datagrid('reload');
        $('#modal_brand').modal('hide');
        window.location.href = BASE_URL + "/back/trend/region"; 
        $('.close').trigger('click');
        
    } else {
    	alert(data.msg);
    }
}