$(document).ready(function() {
	
	
	$("#vcode").blur(function (){ 
			var vcode = $("#vcode").val();
			//验证验证码是否正确
			v_code(vcode);
		});
	
	 $('.submit').click(function(){
	        form_submit();
	    });
});

//注册表单验证
/*$(function(){

    $("#register_form").validate({
        rules : {
        	phone : {
                 required : true,
                 minlength: 11,
                 maxlength: 11
             },
             messageText : {
                 required : true,
                 minlength: 3,
                 maxlength: 6
             },
            
        },
        messages : {
        	phone : {
                required : '手机号不能为空',
                minlength : '手机号必须为11个字符',
                maxlength : '手机号必须为为11个字符',
            },
            messageText  : {
                required : '验证码不能为空',
                minlength: '验证码长度应在3-6个字符之间',
                maxlength: '验证码长度应在3-6个字符之间'
            },
        },
        submitHandler:function(){
        	form_submit();
        }
    });
});
*/
/**
 * 取消处理
 */
function form_cancel() {
	history.go(1);
}

function form_submit(){
	
	var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
    };
    $("#mobile_form").ajaxSubmit(options);
    return false;
}


function form_save_added(data){
    if (data.status == 0){
        location.reload(true);
        alert("修改成功");
    } else {
        alert(data.error);
    }
}

/**
 * 发送手机验证码
 * @param cart_id
 */
function send_message_text(){
	
    var successMSG = "短信发送成功";
    var failMSG = "短信发送失败";
    var phone= document.getElementById("mobile").value;
    // window.location=BASE_URL + '/front/cas/sendShortMessage?phone='+phone;
    $.ajax({
    	//  type:"POST",  //请求方式
        url:BASE_URL+'/front/cas/sendShortMessage?phone='+phone,  //请求路径：页面/方法名字
        //  data: phone,     //参数
        //  dataType:"text",  
        timeout:60000,
        success:function(){  //成功
        	alert(successMSG);
        },
        error:function(obj, msg, e){   //异常
            alert(failMSG);
        }         
    });
    
}


/**
 * 修改密码
 * @param cart_id
 */
function update_password(){
    
    //手机号
	var phone= document.getElementById("mobile").value;
	//验证码
    var messageText= document.getElementById("vcode").value;
    
    var newPassword = $('#newPassword').val();
    
    //  window.location=BASE_URL + '/front/cas/sendShortMessage?phone='+phone;
    $.ajax({
        url:BASE_URL+'/front/cas/setNewPassword?messageText='+messageText+"&phone="+phone+"&newPassword="+newPassword,  //请求路径：页面/方法名字
        timeout:60000,
        success:function(){  //成功
        },
    });
}

function v_code(vcode){
	var data = {vcode:vcode};
	   $.ajax({
	        url: BASE_URL + '/front/cas/verifyCode',
	        data: data,
	        dataType: 'json',
	        type: 'post',
	        success:function(response){
	            if (response.data == "success"){
	               $("#vSuccess").show();
	               $("#vFail").hide();
	               $("#newPassword").removeAttr("disabled");
	            }else{
	               $("#vFail").show();
	               $("#vSuccess").hide();
	               $("#newPassword").attr("disabled",true);;
	            }
	        }
	    })
}
