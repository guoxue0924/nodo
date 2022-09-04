/**
 * 快速重置密码
 */
function quickresetpwd() {
	/* 获取参数 */
	var old_password = $('#resetpwd_old_password').val();
	var password = $('#resetpwd_password').val();
	var repassword = $('#resetpwd_repassword').val();
	
	/* 校验密码 */
	if (! CASValidator.password(password)) {
		return '为保证您的账户安全，请输入由半角字符的字母、数字和下划线“_”组成，且长度最少 6 位，最多 14 位的密码';
	}
	
	/* 校验重复密码 */
	if (repassword != password) {
		return '两次输入密码不一致，请检查后重新输入';
	}
	if (! CASValidator.password(repassword)) {
		return '为保证您的账户安全，请输入由半角字符的字母、数字和下划线“_”组成，且长度最少 6 位，最多 14 位的确认密码';
	}
	
	var saveCallBack = form_quickresetpwded;
	var options = {
            dataType:'json',
            timeout:60000,
            success:saveCallBack
    };
    $("#form_quickresetpwd").ajaxSubmit(options);
    return true;
}

/**
 * 重置密码成功，返回处理
 */
function form_quickresetpwded(data, textStatus) {
    if (data.status === 0) {
    	createSuccess(1);
    } else {
		alert(data.error);
    }
}