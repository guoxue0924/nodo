//注册表单提示
$('.tip').poshytip({
    className: 'tip-yellowsimple',
    showOn: 'focus',
    alignTo: 'target',
    alignX: 'center',
    alignY: 'top',
    offsetX: 0,
    offsetY: 5,
    allowTipHover: false
});

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
        errorPlacement: function(error, element){
            var error_td = element.parent('dd');
            error_td.find('label').hide();
            error_td.append(error);
        },
        onkeyup: false,
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
//             email : {
//                 required : true,
//                 email    : true
//             }, 
             phone : {
                 required : true,
                 minlength: 11,
                 maxlength: 11
             }, 
             messageText : {
                 required : true,
                 minlength: 6,
                 maxlength: 6
             },
             captcha : {
                 required : true,
                 minlength: 4,
                 maxlength: 4
             },
             agree : {
                 required : true
             }
            
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
            password_confirm : {
                required : '请再次输入您的密码',
                equalTo  : '两次输入的密码不一致'
            },
//            email : {
//                required : '电子邮箱不能为空',
//                email    : '这不是一个有效的电子邮箱'
//            },
            phone : {
                required : '手机号码不能为空',
                minlength: '手机号码应为11个字符',
                maxlength: '手机号码应为11个字符'
            },
            messageText : {
                required : '手机验证码不能为空',
                minlength: '手机验证码应为6个字符',
                maxlength: '手机验证码应为6个字符'
            },
            captcha : {
                required : '请输入验证码'
            },
            agree : {
                required : '请阅读并同意该协议'
            }
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
    	if(data.data =="userIsExist"){
    		alert("该用户已经存在，请登陆！");
    	}else{
    		$('#' + data.data + '-error').html(data.msg).show();
        	$('#captchaimg').click();
    	}
    	
    }
}

/**
 * 发送手机验证码
 * @param cart_id
 */
function send_message_text(){
    var successMSG = "短信发送成功";
    var failMSG = "短信发送失败";
    var phone= document.getElementById("phone").value;
//    window.location=BASE_URL + '/front/cas/sendShortMessage?phone='+phone;
    $.ajax({
//        type:"POST",  //请求方式
        url:BASE_URL+'/front/cas/sendShortMessage?phone='+phone,  //请求路径：页面/方法名字
//        data: phone,     //参数
//        dataType:"text",  
        timeout:60000,
        success:function(msg){  //成功
//        	alert(successMSG);
        },
        error:function(obj, msg, e){   //异常
//            alert(failMSG);
        }            
    });
    
}
