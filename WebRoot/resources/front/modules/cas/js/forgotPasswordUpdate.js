$(document).ready(function() {
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
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
	history.go(1);
}

//注册表单验证
$(function(){
    jQuery.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[^:%,'\*\"\s\<\>\&]+$/i.test(value);
    }, "Letters only please");
    jQuery.validator.addMethod("lettersmin", function(value, element) {
        return this.optional(element) || ($.trim(value.replace(/[^\u0000-\u00ff]/g,"aa")).length>=3);
    }, "Letters min please");
    jQuery.validator.addMethod("lettersmax", function(value, element) {
        return this.optional(element) || ($.trim(value.replace(/[^\u0000-\u00ff]/g,"aa")).length<=15);
    }, "Letters max please");


    $("#register_form").validate({
        rules : {
        	 username : {
                 required : true,
                 minlength: 3,
                 maxlength: 15
             },
             password : {
                 required : true,
                 minlength: 6,
                 maxlength: 20
             },
             repassword : {
                 required : true,
                 equalTo  : '#password'
             },
            
        },
        messages : {
        	username : {
                required : '用户名不能为空',
                minlength : '用户名必须在3-15个字符之间',
                maxlength : '用户名必须在3-15个字符之间',
            },
            password  : {
                required : '密码不能为空',
                minlength: '密码长度应在6-20个字符之间',
                maxlength: '密码长度应在6-20个字符之间'
            },
            repassword : {
                required : '请再次输入您的密码',
                equalTo  : '两次输入的密码不一致'
            },
        },
        submitHandler:function(){
        	form_submit();
        }
    });
});

function form_submit(){
	$('#register_form label').hide();
	var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
    };
    $("#register_form").ajaxSubmit(options);
    return false;
}

function form_save_added(data, textStatus){
	if(data.status === 0) {
    	window.location = BASE_URL + data.data;
    } else {
    	$('#' + data.data + '-error').html(data.msg).show();
    	$('#captchaimg').click();
    }
}


