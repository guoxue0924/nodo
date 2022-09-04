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
                    <p style="height:48px;"></p>
                    <div id="question-cont">
                        <form id="aqs_form" action="?" method="post">
                        <input type="hidden" name="captchaId" value="{$captchaId}">
                            <ul>
                                <li>
                                    <div class="label-que f13 c666 soft-black"><span>*</span>通行证账号：</div>
                                    <div class="myinput-mib f13 c666 soft-black"><input type="text" name="username" maxlength="20" class="inp-bd" /><br /></div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <div class="label-que f13 c666 soft-black">输入验证码：</div>
                                    <div class="myinput-mib2 f13 c666 soft-black"><input type="text" name="captcha" autocomplete="off" maxlength="6" class="inp-bd" /></div>
                                    <div class="mi-yzm"><img class="vcodeimg" src="/captcha/image?id={$captchaId}&" alt="/captcha/image?id={$captchaId}&" id="vcodeimg" title="看不清楚，换一张" style="cursor:pointer;" onclick="this.src=this.alt+'t='+Math.random();" />
                                    <a href="#" onclick="$('.vcodeimg').attr('src', $('.vcodeimg').attr('alt')+'t='+Math.random()); return false;" class="deep-blue">换一张</a></div>
                                </li>
                                <br class="clearfloat" />
                                <li>
                                    <p class="next-que">
                                    <input type="button" name="mode_button" id="mode_email" class="post-mail" value="发送确认邮件" />
                                    <input type="button" name="mode_button" id="mode_qa" class="ans-que" value="回答安全问题" />
                                    <input type="hidden" name="mode" value="email" /></p>
                                </li>                 
                            </ul>
                        </form>
                    </div>                    
                    
                </div><!--/Security_Center-->
            </div><!--/m_right-->


{include file="inc/_main_end.html"}

{include file="inc/_footer.html"}

<!-- {literal} -->
<script type="text/javascript">
$("input[name=mode_button]").click(function(){
    var id = $(this).attr("id").substr(5);
    $("input[name=mode]:last").val(id);
    $("#aqs_form").submit();
});
</script>
<!-- {/literal} -->

</body>
</html>