<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>账户安全</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">
</div>
<div class="clear"></div>
</div>

<script type="text/html" id="addphone">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="normal"><a href="index.php?act=member_security&amp;op=index">账户安全</a></li><li class="active"><a href="index.php?act=member_security&amp;op=auth&amp;type=modify_mobile">手机验证</a></li></ul>
        </div>
        <div class="alert alert-success">
            <h4>操作提示：</h4>
            <ul>
                <li>1. 绑定手机后可直接通过短信接受安全验证等重要操作。</li>
                <li>2. 更改手机时，请通过安全验证后重新输入新手机号码绑定。</li>
                <li>3. 收到安全验证码后，请在30分钟内完成验证。</li>
            </ul>
        </div>
        <div class="ncm-default-form">
            <form method="post" id="mobile_form" action="index.php?act=member_security&amp;op=modify_mobile">
                <input type="hidden" name="form_submit" value="ok">
                <dl>
                    <dt><i class="required">*</i>绑定手机号码：</dt>
                    <dd>
                        <p>
                            <input type="text" class="text" maxlength="11" value="15221823228" name="mobile" id="mobile">
                            <label for="email" generated="true" class="error"></label>
                            <a href="javascript:void(0);" id="send_auth_code" class="ncm-btn ml5"><span id="sending" style="display:none">正在</span><span class="send_success_tips" style="display: none;"><strong id="show_times" class="red mr5"></strong>秒后再次</span>获取短信验证码</a></p>
                        <p class="send_success_tips hint mt10" style="display: none;">“安全验证码”已发出，请注意查收，请在<strong>“30分种”</strong>内完成验证。</p>
                        <!-- <input type="button" value="获取短信验证码" id="send_mobile"><span id="send_success_tips" style="display: none">校验码已发出，请注意查收短信，如果没有收到，您可以在<i style="color: red" id="show_times">10</i>秒要求系统重新发送，收到后，请在30分种内完成验证。</span></p>
                      <p class="hint">请您输入正确的手机号码以便接收绑定短信验证码，一个手机号码只能绑定一个账号</p>-->
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>输入短信校验码：</dt>
                    <dd>
                        <input type="text" class="text" maxlength="6" value="" name="vcode" id="vcode">
                        <label for="email" generated="true" class="error"></label>
                    </dd>
                </dl>
                <dl class="bottom">
                    <dt>&nbsp;</dt>
                    <dd><label class="submit-border">
                        <input type="submit" class="submit" value="立即绑定"></label>
                    </dd>
                </dl>
            </form>
        </div>
    </div>
</script>

<script type="text/html" id="addemail">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="normal"><a href="index.php?act=member_security&amp;op=index">账户安全</a></li><li class="active"><a href="index.php?act=member_security&amp;op=auth&amp;type=modify_email">邮箱验证</a></li></ul>
        </div>
        <div class="alert alert-success">
            <h4>操作提示：</h4>
            <ul>
                <li>1. 此邮箱将接收密码找回，订单通知等敏感性安全服务及提醒使用，请务必填写正确地址。</li>
                <li>2. 设置提交后，系统将自动发送验证邮件到您绑定的信箱，您需在24小时内登录邮箱并完成验证。</li>
                <li>3. 更改邮箱时，请通过安全验证后重新输入新邮箱地址绑定。</li>
            </ul>
        </div>
        <div class="ncm-default-form">
            <form method="post" id="email_form" action="index.php?act=member_security&amp;op=send_bind_email">
                <input type="hidden" name="form_submit" value="ok">
                <dl>
                    <dt><i class="required">*</i>绑定邮箱地址：</dt>
                    <dd>
                        <input type="text" class="text w180" maxlength="40" value="{{email}}" name="email" id="email">
                        <label for="email" generated="true" class="error"></label>
                    </dd>
                </dl>
                <dl class="bottom">
                    <dt>&nbsp;</dt>
                    <dd><label class="submit-border">
                        <input type="submit" class="submit" value="发送验证邮件"></label>
                    </dd>
                </dl>
            </form>
        </div>
    </div>
</script>
<script type="text/html" id="edit">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="normal"><a href="index.php?act=member_security&amp;op=index">账户安全</a></li><li class="active"><a href="index.php?act=member_security&amp;op=auth&amp;type=modify_email">{{}}</a></li></ul>
        </div>
        <div class="alert alert-success">
            <h4>操作提示：</h4>
            <ul>
                <li>1. 请选择“绑定邮箱”或“绑定手机”方式其一作为安全校验码的获取方式并正确输入。</li>
                <li>2. 如果您的邮箱已失效，可以 <a href="index.php?act=member_security&amp;op=auth&amp;type=modify_mobile">绑定手机</a> 后通过接收手机短信完成验证。</li>
                <li>3. 如果您的手机已失效，可以 <a href="index.php?act=member_security&amp;op=auth&amp;type=modify_email">绑定邮箱</a> 后通过接收邮件完成验证。</li>
                <li>4. 请正确输入下方图形验证码，如看不清可点击图片进行更换，输入完成后进行下一步操作。</li>
                <li>5. 收到安全验证码后，请在30分钟内完成验证。</li>
            </ul>
        </div>
        <div class="ncm-default-form">
            <form method="post" id="auth_form" action="index.php?act=member_security&amp;op=auth">
                <input type="hidden" name="form_submit" value="ok">
                <input type="hidden" name="type" value="modify_email">
                <input name="nchash" type="hidden" value="fbf69a8f">
                <dl>
                    <dt><i class="required">*</i>选择身份认证方式：</dt>
                    <dd><p>
                        <select name="auth_type" id="auth_type">
                            <option value="mobile">手机 [135****5555]</option>
                            <option value="email">邮箱 [tes****il.com]</option>
                        </select>
                        <a href="javascript:void(0);" id="send_auth_code" class="ncm-btn ml5"><span id="sending" style="display:none">正在</span><span class="send_success_tips" style="display: none;"><strong id="show_times" class="red mr5"></strong>秒后再次</span>获取安全验证码</a></p>
                        <p class="send_success_tips hint mt10" style="display: none;">“安全验证码”已发出，请注意查收，请在<strong>“30分种”</strong>内完成验证。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>请输入安全验证码：</dt>
                    <dd>
                        <input type="text" class="text" maxlength="6" value="" name="auth_code" size="10" id="auth_code" autocomplete="off">
                        <label for="email" generated="true" class="error"></label>
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>图形验证码：</dt>
                    <dd>
                        <input type="text" name="captcha" class="text" id="captcha" maxlength="4" size="10" autocomplete="off">
                        <img src="http://10.58.137.126/shop/index.php?act=seccode&amp;op=makecode&amp;nchash=fbf69a8f" name="codeimage" border="0" id="codeimage" class="ml5 vm"><a href="javascript:void(0)" class="ml5 blue" onclick="javascript:document.getElementById('codeimage').src='http://10.58.137.126/shop/index.php?act=seccode&amp;op=makecode&amp;nchash=fbf69a8f&amp;t=' + Math.random();">看不清？换张图</a>
                        <label for="captcha" generated="true" class="error"></label>
                    </dd>
                </dl>
                <dl class="bottom">
                    <dt>&nbsp;</dt>
                    <dd>
                        <label class="submit-border">
                            <input type="button" class="submit" value="确认，进入下一步">
                        </label>
                    </dd>
                </dl>
            </form>
        </div>
    </div>
</script>

<script type="text/javascript">
    var html = template("${type}");
    $('.right-layout').append(html);
</script>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>