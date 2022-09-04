$(document).ready(function() {
	if($("input[name='couponTypeVal']").val() == 0) {
		$('.full-minus').hide();
	}
	if($("input[name='isExchangeVal']").val() == 1) {
		$('.need-point').hide();
	}
	
	/**
	 * 预处理日期时间选择控件
	 */
	$('#validStime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
		var startTime = $('#validStime').val();
		$('#validEtime').datetimepicker('setStartDate', startTime);
		$('#grantStime').datetimepicker('setEndDate', startTime);
		$('#validStime').datetimepicker('hide');
	});
	
	$('#validEtime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
		var endTime = $('#validEtime').val();
		$('#validStime').datetimepicker('setEndDate', endTime);
		$('#grantEtime').datetimepicker('setEndDate', endTime);
		$('#validEtime').datetimepicker('hide');
	});
	
	$('#grantStime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
		var startTime = $('#grantStime').val();
		$('#grantEtime').datetimepicker('setStartDate', startTime);
		$('#validStime').datetimepicker('setStartDate', startTime);
		$('#grantStime').datetimepicker('hide');
	});
	
	$('#grantEtime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
		var endTime = $('#grantEtime').val();
		$('#grantStime').datetimepicker('setEndDate', endTime);
		$('#validEtime').datetimepicker('setStartDate', endTime);
		$('#grantEtime').datetimepicker('hide');
	});
	
	
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
	
	$("#imageFile").fileupload({
        url: BASE_URL + "/back/couponCategory/uploadFile",  
        sequentialUploads: true  
    }).bind('fileuploaddone', function (e, data) {  
    	var status = $.parseJSON(data.result).status;
    	if(status == '0') {
    		$('#imageUrl').val($.parseJSON(data.result).url);
    		$('#image').attr('src', IMG_URL + $.parseJSON(data.result).url);
    	} else {
    		alert('上传失败,请重新操作!');
    	}
    	
    });  
         
});  


/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL + "/back/couponCategory/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	var couponId = $("#couponId").val();
	
	if (! $("#couponName").val()) {
		notice('edit_notice', img_delete + ' 请输入优惠券名称', true, 5000);
		return false;
	}
	
	if(! $("#faceValue").val()) {
		notice('edit_notice', img_delete + ' 请输入面额', true, 5000);
		return false;
	}
	
	if (! $("#validStime").val()) {
		notice('edit_notice', img_delete + ' 请选择有效开始时间', true, 5000);
		return false;
	}
	
	if (! $("#validEtime").val()) {
		notice('edit_notice', img_delete + ' 请选择有效结束时间', true, 5000);
		return false;
	}
	
	if (! $("#grantStime").val()) {
		notice('edit_notice', img_delete + ' 请选择发放开始时间', true, 5000);
		return false;
	}
	
	if (! $("#grantEtime").val()) {
		notice('edit_notice', img_delete + ' 请选择发放结束时间', true, 5000);
		return false;
	}
	
	if (! $("#total").val()) {
		notice('edit_notice', img_delete + ' 请输入发放量', true, 5000);
		return false;
	}
	
	$(".input-submit").attr('disabled', true);
	
	var saveCallBack;
	if (couponId == '' || couponId == 0) {
		saveCallBack = form_save_added;
	} else {
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
