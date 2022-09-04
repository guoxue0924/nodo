$(document).ready(function() {
	
	/**
	 * 权限模块切换
	 */
	$(".permission-navigationId").click(function(event){
		event.preventDefault();
        event.stopPropagation();
        
		var page = $("input[name=page]:last").val();
		var href = $(this).attr("href") + "&page=" + page;
		
		$.history.load(href);
		return false;
	});
	
	/**
	 * 所属模块切换
	 */
	$('form').on('change','#navigationIdSelect', function(){
		$('input[name="url"]').val($(this).find('option:checked').data('link'));
	});
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : permission_cancel(); break;
			case 'submit_save_back' : back_listing = true; permission_submit(); break;
			case 'submit_save_continue' : back_listing = false; permission_submit(); break;
		}
	});
	
	/**
	 * 通过父级菜单获得子级列表
	 */
	$('select[name="sysTwo_navigationId"]').change(function(){
		var navigationId = $(this).val();
		$.ajax({
			url:BASE_URL + '/back/navigation/getNavigationById',
			dataType:'JSON',
			data:{navigationId:navigationId},
			type:'POST',
			complete:function(response){
				var data = response.responseJSON.data;
				var optionHtml = "<option value=0>请选择</option>";
				$.each(data,function(index, data){
					optionHtml +='<option data-link="'+data.link+'" value="'+data.navigationId+'">'+data.title+'</option>';
				});
				$('#navigationIdSelect').html(optionHtml);
			}
		});
	});
});


/**
 * 取消处理
 */
function permission_cancel() {
	history.go(-1);
}

/**
 * 权限表单提交处理
 */
function permission_submit() {
	notice('edit_notice', img_loading_small, false);
	
	if ( ! $("input[name=permissionName]").val() || 
		! $("input[name=url]").val() || ! $("#navigationIdSelect").val()) {
		notice('edit_notice', img_delete + ' 请填写完所有带红色星号的内容', true, 5000);
		return false;
	}
	
	var permission_id = $("#permission_id_now").val();
	
	var saveCallBack;
	if (permission_id == '' || permission_id == 0) {
		saveCallBack = permission_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL + "/back/adminPermission/edit");
		saveCallBack = permission_save_edited;
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
function permission_save_added(data, textStatus) {
    if (data.status === 0) {
        alert('添加成功!');
        
        // 判断是否返回列表管理
        if (back_listing == true) {
        	$('#permission_listing').datagrid('reload');
        	$('#ajaxModal').modal('hide');
        }
    } else {
    	alert(data.msg);
    }
}

/**
 * 编辑成功，返回处理
 */
function permission_save_edited(data, textStatus) {
    if (data.status === 0) {
        alert('编辑成功!');
        $('#permission_listing').datagrid('reload');
        $('#ajaxModal').modal('hide');
    } else {
    	alert(data.msg);
    }
}