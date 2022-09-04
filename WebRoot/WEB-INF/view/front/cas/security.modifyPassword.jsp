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
<div class="wrap" style="margin-left:280px;">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="normal"><a href="index.php?act=member_security&amp;op=index">账户安全</a></li><li class="active"><a href="index.php?act=member_security&amp;op=auth&amp;type=modify_mobile">修改密码</a></li></ul>
        </div>
        <div class="alert alert-success">
            <h4>操作提示：</h4>
            <ul>
                <li>1. 通过绑定的手机号码获得验证码。</li>
                <li>2. 重新设置密码。</li>
                <li>3. 收到安全验证码后，请在60秒内完成验证。</li>
            </ul>
        </div>
        <div class="ncm-default-form">
            <form method="post" id="mobile_form" action="index.php?act=member_security&amp;op=modify_mobile">
                <input type="hidden" name="form_submit" value="ok">
                <dl>
                    <dt><i class="required">*</i>绑定手机号码：</dt>
                    <dd>
                        <p>
                            <input type="text" class="text" maxlength="11" value="${loggedInUser.username}" name="mobile" id="mobile">
                            <label for="email" generated="true" class="error"></label>
                            <a href="javascript:void(0);" id="send_auth_code" class="ncm-btn ml5">
                           <!--  <span id="sending" style="display:none">正在</span><span class="send_success_tips" style="display: none;">
                            <strong id="show_times" class="red mr5"></strong>秒后再次</span> -->
                                                                   获取短信验证码</a></p>
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

 				<dl>
                    <dt><i class="required">*</i>输入新的密码：</dt>
                    <dd>
                        <input type="text" class="text" maxlength="6" value="" name="vcode" id="vcode">
                        <label for="email" generated="true" class="error"></label>
                    </dd>
                </dl>
                <dl class="bottom">
                    <dt>&nbsp;</dt>
                    <dd><label class="submit-border">
                        <input type="submit" class="submit" value="保存修改"></label>
                    </dd>
                </dl>
            </form>
        </div>
    </div>


</div>
<div class="clear"></div>


    

<jsp:include page="../index/wrapper.suffix_front.jsp"/>