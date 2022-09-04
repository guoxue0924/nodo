$(function(){
    
	$('#sign_submit').click(function(){
        if (validata()){
            form_submit();
        }
    });
	
	$('#username').blur(function(){
        if ($(this).val() != ''){
            hideError($(this));
        }
    });
	
	$('#password').blur(function(){
        if ($(this).val() != ''){
            hideError($(this));
        }
    });

    $('#captcha').blur(function(){
        if ($(this).val() != ''){
            hideError($(this));
        }
    });
	
});

/**
 * 表单提交处理
 */
function form_submit() {
    var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
    };
    $("#login_form").ajaxSubmit(options);
    return false;
}

function form_save_added(data){
    if (data.status == 0){
    	window.location = BASE_URL + data.data;
    } else {
    	showError($('#' + data.data), data.msg);
    	$('#captchaimg').click();
    }
}

function validata(){
    $username = $('#username');
    $password = $('#password');
    $captcha = $('#captcha');

    if (! $username.val()){
        showError($username, '用户名不能为空');
        return false
    }

    if (! $password.val()){
        showError($password, '密码不能为空');
        return false
    }

    if (! $captcha.val()){
        showError($captcha, '验证码不能为空');
        return false
    }

    return true;
}

function showError(el, error){
    var error_td = el.parent('dd');
    var lable = error_td.find('label');
    if (lable.html() == ''){
        lable.addClass('error').append(error);
    } else {
        lable.html(error);
    }
}

function hideError(el){
    var error_td = el.parent('dd');
    error_td.find('label').html('');
}