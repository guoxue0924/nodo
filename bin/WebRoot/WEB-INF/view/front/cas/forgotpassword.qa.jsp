<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
{include file="inc/_meta.html"}
<title>冰动通行证</title>
<script language="javascript" type="text/javascript" src="{$_STATIC_URL}/lightning/script/index.js"></script>
</head>
<body>
{include file="inc/_header.html"}

{include file="inc/_main_start.html"}

            <div class="m_left">
                {include file="inc/_panel_left.html"}
            </div><!--/m_left-->
            
            <div class="m_right">
                <p id="current" class="soft-black f13 c304"><b>当前位置：</b><cite>忘记密码</cite></p>
                <div class="AllTitle f18 c304 soft-black">忘记密码</div><!--/AllTitle-->
                <div id="Security_Center">
                    {if $error|@count > 0}
                    <div class="allerror">
                        <ul>
                    {foreach from=$error item=errorMessage}
                    <li>{$errorMessage}</li>
                    {/foreach}
                        </ul>
                    </div>
                    {/if}
                    <div id="question-cont">
                        <form id="forgot_password_form" action="/forgotPassword" method="POST">
                        <input name="qa_submit" type="hidden" value="1">
                        <input type="hidden" name="username" value="{$username}" />
                            <p class="soft-black f13 c666 suo-info">输入安全问题及机密答案</p>
                            <ul class="borc8">
                                <li>
                                    <div class="label-que f13 c666 soft-black">问题一：</div>
                                    <div class="myinput-tit f13 c666 soft-black">{$qa.question1}</div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="label-que f13 c666 soft-black">机密答案一：</div>
                                    <div class="myinput-que f13 c666 soft-black"><input type="text" name="qa_answer1" maxlength="30"  class="inp-bd" /></div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="label-que f13 c666 soft-black">问题二：</div>
                                    <div class="myinput-tit f13 c666 soft-black">{$qa.question2}</div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="label-que f13 c666 soft-black">机密答案二：</div>
                                    <div class="myinput-que f13 c666 soft-black"><input type="text" name="qa_answer2" maxlength="30" class="inp-bd" /></div>
                                </li>
                            </ul>
                            <p class="soft-black f13 c666 suo-info">设置新密码</p>
                            <ul>
                                <li>
                                    <div class="label-que f13 c666 soft-black">新密码：</div>
                                    <div class="myinput-que f13 c666 soft-black"><input type="password" name="password" maxlength="20" class="inp-bd" /></div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="label-que f13 c666 soft-black">确认新密码：</div>
                                    <div class="myinput-que f13 c666 soft-black"><input type="password" name="password_confirm" maxlength="20" class="inp-bd" /><br /><pre class="tips-que">密码长度为6~14位，可以使用以下字符：<br />字母a-z和A-Z、数字0-9和下划线_</pre></div>
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

{include file="inc/_main_end.html"}

{include file="inc/_footer.html"}

<!-- {literal} -->
<script>
$(document).ready(function(){
    $("#forgot_password_form").submit(function(){
        var password = $('#form_resetpw :input[name=password]').val();
        var password_confirm = $('#form_resetpw :input[name=password_confirm]').val();
        
        if (password == '') {
            alert('请填写新密码');
            $('#form_resetpw :input[name=password]').focus();
            return false;
        }
        if (password_confirm == '') {
            alert('请确认新密码');
            $('#form_resetpw :input[name=password_confirm]').focus();
            return false;
        }
        
        if (password != password_confirm) {
            alert('密码不一致，请重新填写');
            $('#form_resetpw :input[name=password]').focus();
            return false;
        }
        
        return true;
    });
});
</script>
<!-- {/literal} -->
</body>
</html>