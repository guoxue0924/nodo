<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>冰动通行证</title>
<script language="javascript" type="text/javascript" src="{$_STATIC_URL}/lightning/script/index.js"></script>
</head>
<body>
{include file="inc/header.html"}
            
            <div class="m_right">
                <div class="AllTitle f18 c304 soft-black">重置通行证密码</div><!--/AllTitle-->
                <div id="Security_Center">
                    {if $error && count($error) > 0}
                    <div class="allerror">
                        <ul>
                            <li>{$error.msg}</li>
                        </ul>
                    </div>
                    {/if}
  

                    <div id="question-cont">
                        <form id="change_password_form" action="/cas/forgotpassword/resetpassword" method="POST">
                        <input type="hidden" name="code" value="{$code}">
                        <input type="hidden" name="rp_submit" value="1">
                            <p class="soft-black f13 c666 suo-info">设置新密码</p>
                            <ul>
                                <li>
                                    <div class="label-que f13 c666 soft-black">新密码：</div>
                                    <div class="myinput-que f13 c666 soft-black"><input id="password" type="password" name="password" maxlength="20" class="inp-bd" /></div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="label-que f13 c666 soft-black">确认新密码：</div>
                                    <div class="myinput-que f13 c666 soft-black"><input id="password_confirm" type="password" name="password_confirm" maxlength="20" class="inp-bd" /><br /><pre class="tips-que">密码长度为6~14位，可以使用以下字符：<br />字母a-z和A-Z、数字0-9和下划线_</pre></div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="btn-que"><input type="submit" name="submit" value="提交"></div>
                                </li>                 
                            </ul>
                        </form>
                    </div>                    

                </div><!--/Security_Center-->
            </div><!--/m_right-->

<!-- {literal} -->
<script>
$(document).ready(function(){
    $("#change_password_form").submit(function(){
        var password = $('#change_password_form :input[name=password]').val();
        var password_confirm = $('#change_password_form :input[name=password_confirm]').val();
        
        if (password == '') {
            alert('请填写新密码');
            $('#change_password_form :input[name=password]').focus();
            return false;
        }
        if (password_confirm == '') {
            alert('请确认新密码');
            $('#change_password_form :input[name=password_confirm]').focus();
            return false;
        }
        
        if (password != password_confirm) {
            alert('密码不一致，请重新填写');
            $('#change_password_form :input[name=password]').focus();
            return false;
        }
        
        return true;
    });
});
</script>
<!-- {/literal} -->
</body>
</html>