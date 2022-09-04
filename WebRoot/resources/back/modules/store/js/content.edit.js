/**
 * 定义一些常量
 */
var accountAccess = true; // 管理员帐号是否通过验证。不填也视为通过

$(document).ready(function() {
	/**
	 * 切换模板编辑位置
	 */
	$('.nav-map').click(function() {
		/* 模板导航的样式处理 */
		$('.nav-map').removeClass('active');
		$(this).addClass('active');

		/* 切换编辑区域 */
		$('.edit-map').hide();
		var navId = $(this).attr('id');
		var editId = navId.replace('nav', 'edit');
		$('#' + editId).show();
	});

	/**
	 * 预处理日期选择控件
	 */
	$('.datepicker-input').datepicker();

	/**
	 * 初始化地区选单
	 */
	$("select.region").each(function(k) {
		var pid = $(this).attr('data-init');
		if (k == 0) {
			pid = pid ? pid : 3743;
		}
		if (pid > 0) {
			getRegionByPidredis(pid, $(this));
		}
	});
	
	/**
	 * 根据父级 ID 获取子级地区(redis)
	 */
	function getRegionByPidredis(pid, $obj) {
		$.ajax({
			type:'post',
			url:BASE_URL + '/back/storeContent/getRegionByPid',
			data:'pid=' + pid,
			dataType:'json',
			timeout:60000,
			success:function(data){
//				if (data.status == 0) {
					/* 若已加载，则无需重复加载 */
					if ($obj.find('option').size() > 1) {
						return false;
					}
					
					/* 加载地区列表 */
					var selected_id = parseInt($obj.attr('data-selected'));
					$(data.data).each(function() {
						if (selected_id > 0 && selected_id == this.regionId) {
							$obj.append('<option value="' + this.regionId + '" selected="selected">' + this.regionName + '</option>');
						} else {
							$obj.append('<option value="' + this.regionId + '">' + this.regionName + '</option>');
						}
					});
//				}
			}
		});
	}
	
	/**
	 * 选择地区，动态加载子级地区
	 */
	$('body').delegate("select.region", 'change', function(){
		var $obj = $(this);
		var i = $obj.index();
		var pid = $(this).val();
		
		// 到最后一级时，不再动态加载子级地区 
		if (i == $obj.parent().find('select.region').size() - 1) {
			return false;
		}
		
		// 清空后面地区选单元素的选项 
		$obj.parent().find('select:gt(' + i + ')').find("option:gt(0)").remove();
		
		// 动态加载子级地区 
		getRegionByPidredis(pid, $obj.next('select.region'));
	});

	/**
	 * 是否审核
	 */
	$('#isVerify').val() == -1 ? $('#rejectionReason').show() : $('#rejectionReason').hide();
	$('#isVerify').change(function() {
		$(this).val() == -1 ? $('#rejectionReason').show() : $('#rejectionReason').hide();
	});

	/**
	 * 实时校验账户信息
	 */
	$('#username').on('blur', function() {
		var username = $(this).val();
		if (!username || $.trim(username) == '') {
			return false;
		}

		if (!CASValidator.username(username)) {
			accountAccess = false;
			alert('账号不符合规则');
			return false;
		}

		$.ajax({
			type : 'get',
			url : BASE_URL + '/back/casuser/sign/isUsernameAvailable',
			data : 'username=' + username,
			timeout : 60000,
			success : function(data) {
				if (!data) {
					accountAccess = false;
					alert('账号已被注册，请重新填写');
				}
				accountAccess = true;
				return false;
			}
		});
	});

	/**
	 * 实时校验公司名称
	 */
	$('#companyName').on('blur', function() {
		var companyName = $(this).val();
		if (!companyName || $.trim(companyName) == '') {
			return false;
		}

		/* 编辑状态下，没有改动时，不校验 */
		if ($('#storeId').val() && $('#companyName').val() == $('#companyNameEdit').val()) {
			return false;
		}

		$.ajax({
			type : 'post',
			url : BASE_URL + '/back/storeContent/checkCompanyName',
			data : 'companyName=' + companyName,
			dataType : 'json',
			timeout : 60000,
			success : function(data) {
				if (data.status != 0) {
					alert('该公司名称已被注册，请重新填写');
				}
				return false;
			}
		});
	});

	/**
	 * 实时校验店铺名称
	 */
	$('#storeName').on('blur', function() {
		var storeName = $(this).val();
		if (!storeName || $.trim(storeName) == '') {
			return false;
		}

		/* 编辑状态下，没有改动时，不校验 */
		if ($('#storeId').val() && $('#storeName').val() == $('#storeNameEdit').val()) {
			return false;
		}

		$.ajax({
			type : 'post',
			url : BASE_URL + '/back/storeContent/checkStoreName',
			data : 'storeName=' + storeName,
			dataType : 'json',
			timeout : 60000,
			success : function(data) {
				if (data.status != 0) {
					alert('该店铺名称已被注册，请重新填写');
				}
				return false;
			}
		});
	});

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
	history.go(-1);
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('editNotice', img_loading_small, false);

	var storeId = $("#storeId").val();

	/* 校验必填项 */
	if (!$("#signingTimeStart").val() || $.trim($("#signingTimeStart").val()) == '') {
		notice('editNotice', img_delete + ' 签约开始时间不能为空', true, 5000);
		return false;
	}
	if (!$("#signingTimeEnd").val() || $.trim($("#signingTimeEnd").val()) == '') {
		notice('editNotice', img_delete + ' 签约结束时间不能为空', true, 5000);
		return false;
	}
	if ($("#signingTimeEnd").val() < $("#signingTimeStart").val()) {
		notice('editNotice', img_delete + ' 签约结束时间不能在开始时间之前', true, 5000);
		return false;
	}
	if (!$("#companyName").val() || $.trim($("#companyName").val()) == '') {
		notice('editNotice', img_delete + ' 请输入公司名称', true, 5000);
		return false;
	}

	/* 校验账户信息 */
	if (!storeId) {
		if (!$('#username').val() || $.trim($('#username').val()) == '') {
			notice('editNotice', img_delete + ' 请输入管理员帐号', true, 5000);
			return false;
		}
		if (!$('#password').val() || !$('#repassword').val()) {
			notice('editNotice', img_delete + ' 请设置管理员密码', true, 5000);
			return false;
		}
		if ($('#password').val() == $('#username').val()) {
			notice('editNotice', img_delete + ' 密码不能和账号相同', true, 5000);
			return false;
		}
		if ($('#password').val().length < 6 || $('#password').val().length > 14) {
			notice('editNotice', img_delete + ' 密码长度至少6位，最多不超过14位', true, 5000);
			return false;
		}
		if (!CASValidator.password($('#password').val())) {
			notice('editNotice', img_delete + ' 密码中仅能使用以下字符：a-z、A-Z、0-9 以及下划线“_”', true, 5000);
			return false;
		}
		if ($('#repassword').val() != $('#password').val()) {
			notice('editNotice', img_delete + ' 两次输入密码不一致', true, 5000);
			return false;
		}
	}

	/* 编辑时，校验重置密码 */
	if (storeId && $('#password').val()) {
		if ($('#password').val() == $('#usernameEdit').val()) {
			notice('editNotice', img_delete + ' 密码不能和账号相同', true, 5000);
			return false;
		}
		if ($('#password').val().length < 6 || $('#password').val().length > 14) {
			notice('editNotice', img_delete + ' 密码长度至少6位，最多不超过14位', true, 5000);
			return false;
		}
		if (!CASValidator.password($('#password').val())) {
			notice('editNotice', img_delete + ' 密码中仅能使用以下字符：a-z、A-Z、0-9 以及下划线“_”', true, 5000);
			return false;
		}
	}

	/* 编辑时，若填写了重置密码，则需提示是否确认要重置密码 */
	if (storeId && $('#password').val() && !confirm('本次更新将连同密码一并更新，确定要继续吗？')) {
		notice('editNotice', '', false);
		return false;
	}

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (storeId == '' || storeId == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#editForm").attr("action", BASE_URL + "/back/storeContent/edit");
		saveCallBack = form_save_edited;
	}

	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack,
		error : ajaxError
	};
	$("#editForm").ajaxSubmit(options);
	return false;
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
	if (data.status === 0) {
		notice('editNotice', img_done + ' 添加成功!', true, 5000);

		// 判断是否返回列表管理
		if (back_listing == true) {
			// history.back(-1);
			window.location.href = BASE_URL + "/back/storeContent/index";
		}
	} else {
		notice('editNotice', img_delete + " " + data.error, true, 5000);
	}
	$(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
	if (data.status === 0) {
		notice('editNotice', img_done + ' 编辑成功!', true, 5000);
		// history.back(-1);
		window.location.href = BASE_URL + "/back/storeContent/index";
	} else {
		notice('editNotice', img_delete + " " + data.error, true, 5000);
	}
	$(".input-submit").removeAttr('disabled');
}