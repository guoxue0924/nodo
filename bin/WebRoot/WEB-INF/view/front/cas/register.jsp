<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="wrapper.prefix.jsp"/>

<style id="poshytip-css-tip-yellowsimple" type="text/css">
    div.tip-yellowsimple{visibility:hidden;position:absolute;top:0;left:0;}
    div.tip-yellowsimple table, div.tip-yellowsimple td{margin:0;font-family:inherit;font-size:inherit;font-weight:inherit;font-style:inherit;font-variant:inherit;}
    div.tip-yellowsimple td.tip-bg-image span{display:block;font:1px/1px sans-serif;height:10px;width:10px;overflow:hidden;}
    div.tip-yellowsimple td.tip-right{background-position:100% 0;}
    div.tip-yellowsimple td.tip-bottom{background-position:100% 100%;}
    div.tip-yellowsimple td.tip-left{background-position:0 100%;}
    div.tip-yellowsimple div.tip-inner{background-position:-10px -10px;}
    div.tip-yellowsimple div.tip-arrow{visibility:hidden;position:absolute;overflow:hidden;font:1px/1px sans-serif;}
</style>

<div class="header-wrap">
    <header class="public-head-layout wrapper">
        <h1 class="site-logo"><a href=""><img src="${STATIC_URL}/front/images/front/logo.png" class="pngFix"></a></h1>
    </header>
</div>
<div class="nc-login-layout">
    <div class="nc-login" id="aui_iwrapper">
        <div class="nc-login-title">
            <h3>用户注册</h3>
        </div>
        <div class="nc-login-content">
            <form id="register_form" method="post" action="${BASE_URL}/front/cas/register" novalidate="novalidate">
            <dl>
                <dt>用户名</dt>
                <dd style="min-height:54px;">
                    <input type="text" id="username" name="username" class="text tip error" title="3-15位字符，可由中文、英文、数字及“_”、“-”组成" autofocus="">
                    <label id="username-error" style="display: none;"></label>
                </dd>
            </dl>
            <dl>
                <dt>设置密码</dt>
                <dd style="min-height:54px;">
                    <input type="password" id="password" name="password" class="text tip valid" title="6-20位字符，可由英文、数字及标点符号组成">
                    <label id="password-error" style="display: none;"></label>
                </dd>
            </dl>
            <dl>
                <dt>确认密码</dt>
                <dd style="min-height:54px;">
                    <input type="password" id="repassword" name="repassword" class="text tip valid" title="请再次输入您的密码">
                    <label id="repassword-error" style="display: none;"></label>
                </dd>
            </dl>
            <dl>
                <dt>邮箱</dt>
                <dd style="min-height:54px;">
                    <input type="text" id="email" name="email" class="text tip error" title="请输入常用的邮箱，将用来找回密码、接受订单通知等">
                    <label id="email-error" style="display: none;"></label>
            </dl>
            <dl>
                <dt>验证码</dt>
                <dd style="min-height:54px;">
                    <input type="text" id="captcha" name="captcha" class="text w50 fl tip error" maxlength="4" size="10" title="请输入验证码，不区分大小写">
                    <img src="${BASE_URL}/front/cas/code" alt="点击换一张" id="captchaimg" title="看不清楚，换一张" style="cursor:pointer;"  border="0" class="fl ml5"  onclick="this.src='${BASE_URL}/front/cas/code?t='+Math.random();">
                    <label id="captcha-error" style="display: none;"></label>
            </dl>
            <dl>
                <dt>&nbsp;</dt>
                <dd>
                    <input type="submit" id="sign_submit" value="立即注册" class="submit" title="立即注册">
                    <input name="agree" type="checkbox" class="vm ml10 valid" id="clause" value="1" checked="checked">
                    <span for="clause" class="ml5">阅读并同意<a href="http://10.58.137.126/shop/index.php?act=document&amp;op=index&amp;code=agreement" target="_blank" class="agreement" title="阅读并同意">服务协议</a></span>
                    <label style="display: none;"></label>
            </dl>
            </form>
            <div class="clear"></div>
        </div>
        <div class="nc-login-bottom"></div>
    </div>
    <div class="nc-login-left">
        <h3>注册之后您可以</h3>
        <ol>
            <li class="ico05"><i></i>购买商品支付订单</li>
            <li class="ico01"><i></i>方便快捷优惠多多</li>
            <li class="ico03"><i></i>参加活动赢取积分</li>
            <li class="ico02"><i></i>收藏商品关注店铺</li>
            <li class="ico06"><i></i>商品咨询服务评价</li>
            <li class="ico04"><i></i>安全交易诚信无忧</li>
            <div class="clear"></div>
        </ol>
        <h3 class="mt20">如果您是本站用户</h3>
        <div class="nc-login-now mt10"><span class="ml20">我已经注册过账号，立即<a href="${BASE_URL}/front/cas/login" title="" class="register">登录</a></span><span></span></div>
    </div>
</div>

<jsp:include page="wrapper.suffix.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery.poshytip.min.js" charset="utf-8"></script>
<script src="${STATIC_URL}/plugins/jquery-qtip/jquery.qtip.min.js"></script>
<link href="${STATIC_URL}/plugins/jquery-qtip/jquery.qtip.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/register.js" charset="utf-8"></script>