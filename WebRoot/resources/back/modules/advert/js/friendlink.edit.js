$(document).ready(function() {
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : form_cancel(); break;
			case 'submit_save_back' : back_listing = true; form_submit(); break;
		}
	});
	
	$("#imageFile").fileupload({
        url: BASE_URL + "/back/friendlink/uploadFile",  
        sequentialUploads: true  
    }).bind('fileuploaddone', function (e, data) {  
    	var status = $.parseJSON(data.result).status;
    	if(status == '0') {
    		$('#imageUrl').val($.parseJSON(data.result).url);
    		$('#image').attr('src', IMG_URL + $.parseJSON(data.result).url);
    		$('#image').show();
    	} else {
    		alert('上传失败,请重新操作!');
    	}
    	
    });  
});

/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL+"/back/friendlink/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	/* 校验必填项 */
	if ($("#linkName").val() < 1) {
		notice('edit_notice', img_delete + ' 友情链接名称不能为空', true, 5000);
		return false;
	}
	
	$(".input-submit").attr('disabled', true);
	
	
	var linkId = $("#linkId").val();
	
	var saveCallBack;
	if (linkId == '' || linkId == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL+"/back/friendlink/edit");
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
    if (data.status === 0) {
        notice('edit_notice', img_done + ' 添加成功!', true, 5000);
        
        // 判断是否返回列表管理
        if (back_listing == true) {
        	form_cancel();
        }
    } else {
    	notice('edit_notice', img_delete + " " + data.error, true, 5000);
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
    	notice('edit_notice', img_delete + " " + data.error, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}