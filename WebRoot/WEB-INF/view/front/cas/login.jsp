<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="wrapper.prefix.jsp"/>

<div class="header-wrap">
  <header class="public-head-layout wrapper">
    <h1 class="site-logo"><a href="${BASE_URL}/front/index/index"><img src="${STATIC_URL}/front/images/front/logo.png" class="pngFix"></a></h1>
    </header>
</div>
<div class="nc-login-layout">
  <div class="left-pic"><img src="${STATIC_URL}/front/images/index/1.jpg" border="0"></div>
  <div class="nc-login">
    <div class="nc-login-title">
      <h3>用户登录</h3>
    </div>
    <div class="nc-login-content" id="demo-form-site">
      <form id="login_form" method="post" action="${BASE_URL}/front/cas/login" class="bg">
        <dl>
          <dt>用户名</dt>
          <dd style="min-height:54px;">
            <input type="text" class="text" autocomplete="off" name="username" id="username" autofocus="">
            <label></label>
          </dd>
        </dl>
        <dl>
          <dt>密&nbsp;&nbsp;&nbsp;码 </dt>
          <dd style="min-height:54px;">
            <input type="password" class="text" name="password" autocomplete="off" id="password">
            <label></label>
          </dd>
        </dl>
                <dl>
          <dt>验证码</dt>
          <dd style="min-height:54px;">
            <input type="text" name="captcha" autocomplete="off" class="text w50 fl" id="captcha" maxlength="4" size="10">
            <img src="${BASE_URL}/front/cas/code" alt="点击换一张" id="captchaimg" title="看不清楚，换一张" style="cursor:pointer;"  border="0" class="fl ml5"  onclick="this.src='${BASE_URL}/front/cas/code?t='+Math.random();">
            <label></label>
          </dd>
        </dl>
                <dl>
          <dt>&nbsp;</dt>
          <dd>
            <input type="button" id="sign_submit" class="submit" value="登&nbsp;&nbsp;&nbsp;录">
           
          </dd>
        </dl>
      </form>
      <dl class="mt10 mb10">
        <dt>&nbsp;</dt>
         <dd>忘记密码啦？请&nbsp;<a title="" href="${BASE_URL}/front/cas/forgotPassword" class="forgotpassword">找回密码</a></dd>
        <dd>还不是本站会员？立即<a title="" href="${BASE_URL}/front/cas/register" class="register">注册</a></dd>
      </dl>
          </div>
    <div class="nc-login-bottom"></div>
  </div>
</div>


<jsp:include page="wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/jquery-qtip/jquery.qtip.min.js"></script>
<link href="${STATIC_URL}/plugins/jquery-qtip/jquery.qtip.min.css" rel="stylesheet" type="text/css">
<script src="${STATIC_URL}/front/modules/cas/js/login.js"></script>