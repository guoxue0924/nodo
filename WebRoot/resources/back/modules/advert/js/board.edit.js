$(document).ready(function() {
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		var submit_type = $(this).attr("data_submit_type");
		switch (submit_type) {
		case 'submit_cancel':
			form_cancel();
			break;
		case 'submit_save_back':
			back_listing = true;
			form_submit();
			break;
		case 'submit_save_continue':
			back_listing = false;
			form_submit();
			break;
		}
	});
});

/**
 * 取消处理
 */
function form_cancel() {
	$('#ajaxModal').modal('hide');
}

/**
 * 表单提交处理
 */
function form_submit() {
	if (!$("select[name=pageId]").val()) {
		alert('请选择所属页面');
		return false;
	}
	if (!$("select[name=type]").val()) {
		alert('请选择广告类型');
		return false;
	}
	if (!$("input[name=name]").val()) {
		alert('广告位名称不能为空');
		return false;
	}

	var board_id = $("input[name=boardId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (board_id == '' || board_id == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL + "/back/advertBoard/edit");
		saveCallBack = form_save_edited;
	}

	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack
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
		if (back_listing == true) {
			$('#content_listing').datagrid('reload');
			$('#ajaxModal').modal('hide');
		}
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
	if (data.status === 0) {
		alert('编辑成功!');

		$('#content_listing').datagrid('reload');
		$('#ajaxModal').modal('hide');
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}