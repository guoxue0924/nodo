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
        <h1 class="site-logo"><a href="${BASE_URL}/front/index/index"><img src="${STATIC_URL}/front/images/front/logo.png" class="pngFix"></a></h1>
    </header>
</div>
<div class="nc-login-layout">
    <div class="nc-login" id="aui_iwrapper">
        <div class="nc-login-title">
            <h3>找回密码</h3>
        </div>
        <div class="nc-login-content">
            <form id="register_form" method="post" action="${BASE_URL}/front/cas/forgotPasswordUpdate" novalidate="novalidate">
            <dl>
                <dt>手&nbsp;机&nbsp;号&nbsp;码</dt>
                <dd style="min-height:54px;">
                    <input type="text" id="phone" name="phone" class="text tip error" title="请输入常用的手机号，将用来找回密码、接受订单通知等">
                    <label id="phone-error" style="display: none;"></label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="send_message_text();">发送手机验证码</a>
<%--                 	 <a href="${BASE_URL}/front/cas/sendShortMessage?phone=${phone}">发送手机验证码</a> --%>
                </dd>    
            </dl>
            <dl>
                <dt>手机验证码</dt>
                <dd style="min-height:54px;">
                    <input type="text" id="messageText" name="messageText" class="text tip error" title="请输入正确的手机验证码">
                    <label id="messageText-error" style="display: none;"></label>
                </dd>    
            </dl>
            <dl>
                <dt>&nbsp;</dt>
                <dd>
<!--                      <a href="#" onclick="update_password();">提交</a> -->
                     <input type="submit" onclick="update_password();" value="提交手机验证" class="submit" title="提交手机验证">
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--                      <a href="#" onclick="form_cancel();">取消</a> -->
					 <input type="submit" id="sign_cancel" value="进行取消" class="cancel" title="取消">
                </dd>    
            </dl>
            </form>
            <div class="clear"></div>
        </div>
        <div class="nc-login-bottom"></div>
    </div>
    <div class="nc-login-left">
        <h3>找回密码之后您可以</h3>
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
        <div class="nc-login-now mt10"><span class="ml20">我已回忆起密码，立即<a href="${BASE_URL}/front/cas/login" title="" class="register">登录</a></span><span></span></div>
    </div>
</div>

<jsp:include page="wrapper.suffix.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery.poshytip.min.js" charset="utf-8"></script>
<script src="${STATIC_URL}/plugins/jquery-qtip/jquery.qtip.min.js"></script>
<link href="${STATIC_URL}/plugins/jquery-qtip/jquery.qtip.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/forgotPassword.js" charset="utf-8"></script>