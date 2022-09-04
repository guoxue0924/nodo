<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/inc/header_front.jsp"/>
<jsp:include page="../index/inc/main.top.jsp"/>
<link href="${STATIC_URL}/front/modules/bts/css/home_cart.css" rel="stylesheet" type="text/css">

<div class="AllWrapInside">
  <div class="cart_index"> 
    <div class="cart_none_nav">
      <div class="cart_none_left"><img src="/static/index/img/cartimg06.jpg" height="103" width="145"></div>
      <div class="cart_none_right">
        <h4>您的购物车还是空的，赶紧行动吧！ </h4>
        <p>马上去<a href="${_STORE_URL}">随便逛逛</a></p>
      </div>
    </div>
    <div style="clear: both;"></div>
    <div class="cart_cdlike" style="display: block;">
      <div class="cart_cdlike_title"> <span  class="meybelike">校妆特惠</span> <span class="countprouduct meybelike1">我的收藏</span> </div>
      <div class="cart_cdlike_title" style="margin-left: 880px; width: auto;"> <a class="xz_bfd_logo" href="http://www.baifendian.com/" target="_top"> </a> </div>
      <div style="display: none;" class="cart_ylike" id="cartlike_show">
        <c:forEach items="${activity}" var="v">
        <!-- {foreach from=$activity key=id item=v} -->
        <dl>
          <dt><a href="${_STORE_URL}/goods/index/detailed?item_id=${v.goods_id}" title="${v.name}" target="_blank"><img src="${v.attach_pic}" alt="${v.name}" height="160" width="160"></a></dt>
          <dd class="cart_ylikename"><a href="${_STORE_URL}/goods/index/detailed?item_id=${v.goods_id}" title="${v.name}" target="_blank">${v.name}</a></dd>
          <dd><em class="cartlike_price2">￥${v.price}</em><em class="cartlike_price1">￥${v.price}</em></dd>
          <dd><a class="cart_tub0 cart_tub6 addGoodsToCart" jump="false" data-product-id="${v.goods_id}" href="javascript:;"></a></dd>
        </dl>
        </c:forEach>
      </div>
      <div class="cart_ylike" id="cartcd_show" style="display: block;">
      <c:choose><c:when test="${loggedInUser.username != ''}">
            <c:forEach items="${favorite}" var="v">
            <!--{foreach from=$ key=id item=v} -->
            <dl>
              <dt><a href="${BASE_URL}/front/goods/index/detailed?item_id=${v.goodsId}" title="${v.name}" target="_blank"><img src="${v.attach_pic}" alt="${v.name}" height="160" width="160"></a></dt>
              <dd class="cart_ylikename"><a href="${_STORE_URL}/goods/index/detailed?item_id=${v.goodsId}" title="${v.name}" target="_blank">${v.name}</a></dd>
              <dd><em class="cartlike_price2">￥${v.price}</em><em class="cartlike_price1">￥${v.price}</em></dd>
              <dd><a class="cart_tub0 cart_tub6 addGoodsToCart" jump="false" data-product-id="${v.goodsId}" href="javascript:;"></a></dd>
            </dl>
            </c:forEach>
        </c:when><c:otherwise>
            <div class="cart_none_tishi">请点击此处<a href="${BASE_URL}/front/cas/login">登录</a>以方便查看你收藏的商品。</div>
        </c:otherwise></c:choose>
      </div>
      <div class="cart_mkshop" style="display: none;">
        <div class="cart_mktitle"><span>提示</span><em class="cart_close" ></em></div>
        <dl>
          <dt></dt>
          <dd>商品已成功加入购物车！</dd>
          <dd><a href="${_STORE_URL}/?cart.html">查看购物车</a></dd>
        </dl>
        <p><span id="secondID">5</span>秒后自动关闭</p>
        <div class="clr"></div>
      </div>
    </div>
 <div  title="" class="backToTop"></div>
    </div>
  <div class="cartv2_login" style="z-index: 99999; display: none;" id="cartLoginID" draggable="true">
    <h3 class="cartv2_ltitle">您尚未登录！</h3>
    <a href="javascript:;" class="cartv2_lclose"></a>
    <div class="cv2_titile"> <span class="cv2_ltitle0 login_title cv2_ltitle01">登录</span> <span class="cv2_ltitle0 reg_title cv2_ltitle02">注册</span> </div>
    <div class="cv2_login" style="display: block;" id="cartlogin_show">
      <dl>
        <dt>会员名：</dt>
        <dd>
          <input onBlur="if (this.value=='') this.value='请输入会员卡号或用户名'" onFocus="if (this.value == '请输入会员卡号或用户名') this.value=''" value="请输入会员卡号或用户名" style="width: 180px; height: 20px;" name="login" id="cartLoginUname" type="text">
        </dd>
        <dd></dd>
      </dl>
      <dl>
        <dt>密码：</dt>
        <dd>
          <input value="" style="width: 180px; height: 20px;" id="cartLoginPass" type="password">
        </dd>
        <dd><a href="${_STORE_URL}/?passport-lost.html" style="color: rgb(255, 51, 102); text-decoration: underline;">忘记密码？</a></dd>
      </dl>
      <dl>
        <dt>&nbsp;</dt>
        <dd>
          <input style="cursor: pointer;" value="立即登录" onClick="Cart.cartLoginSub()" class="btn-loginv2" id="cartLoginButtonID" type="button">
        </dd>
        <dd></dd>
      </dl>
      <p class="cv2_npwrong" id="errorMsg" style="display: none;">您的用户名或密码不匹配，请重新输入</p>
    </div>
    <div class="cv2_login" style="display: none;" id="cartzc_show">
      <dl>
        <dt>会员名：</dt>
        <dd>
          <input value="" style="width: 160px; height: 20px;" name="uname" id="reg_user" onBlur="nameCheck(this)" type="text">
        </dd>
        <dd class="cv2_sig" id="usernameTEXT">用户名为4-20个字符</dd>
      </dl>
      <dl>
        <dt>密码：</dt>
        <dd>
          <input name="passwd" value="" style="width: 160px; height: 20px;" id="reg_passwd" onBlur="passwordCheck(this)" type="password">
        </dd>
        <dd class="cv2_sig" id="passwordTEXT">密码为6-16个字符</dd>
      </dl>
      <dl>
        <dt>确认密码：</dt>
        <dd>
          <input name="passwd_r" value="" style="width: 160px; height: 20px;" id="reg_passwd_r" onBlur="password_rCheck(this)" type="password">
        </dd>
        <dd class="cv2_sig" id="password_rTEXT"></dd>
      </dl>
      <dl>
        <dt>电子邮箱：</dt>
        <dd>
          <input value="" name="email" style="width: 160px; height: 20px;" id="reg_email" onBlur="emailCheck(this)" type="text">
        </dd>
        <dd class="cv2_sig" id="emailTEXT"></dd>
      </dl>
      <dl>
        <dt>验证码：</dt>
        <dd>
          <input value="" vtype="digits" id="iptsingup" name="signupverifycode" required size="8" style="float: left; height: 23px; width: 80px;" autocomplete="off" type="text">
          <span style="display: block; float: left; margin-left: 10px;" class="verifyCode"> <img src="untitled" style="height: 23px; float: left;" id="verifyCodeID" border="1"> <a style="color: rgb(153, 153, 153); display: block; float: left;" onClick="Cart.changeVerifyCode();return false;" href="javascript:;">&nbsp;看不清楚?换个图片</a> </span></dd>
        <dd></dd>
      </dl>
      <dl>
        <dt>&nbsp;</dt>
        <dd>
          <label style="width: auto; text-align: left; font-weight: normal;" class="nof">
            <input style="display: block; float: left; margin-right: 5px;" checked="checked" value="agree" id="reg_license" type="checkbox">
            我已阅读并同意 <a style="color: rgb(0, 102, 204); text-decoration: none;" target="_top" class="lnk" id="terms_error" href="${_STORE_URL}/?page-%E4%BC%9A%E5%91%98%E6%B3%A8%E5%86%8C%E5%8D%8F%E8%AE%AE.html"><span id="terms_error_sym" class="FormText">会员注册协议</span></a>和<a style="color: rgb(0, 102, 204); text-decoration: none;" target="_top" class="lnk" id="privacy_error" href="${_STORE_URL}/?page-%E9%9A%90%E7%A7%81%E5%A3%B0%E6%98%8E.html"><span id="privacy_error_sym" class="FormText">隐私保护政策</span></a>。 </label>
        </dd>
        <dd></dd>
      </dl>
      <dl>
        <dt>&nbsp;</dt>
        <dd>
          <input value="立即注册并登录" class="btn-registerv2" id="cartRegisterButtonID" onClick="Cart.toRegister()" type="button">
        </dd>
        <dd></dd>
      </dl>
      <p class="cv2_npwrong" id="errorMsg_2" style="display: none;"></p>
    </div>
    <div class="dengluo">
      <div class="til">合作网站账号登录：<br>
        <script type="text/javascript">function toQzoneLogin(){ window.open("/index.php?action_tencent_nocenter-login.html","QQLogin","width=450,height=320,menubar=0,scrollbars=1, resizable=1,status=1,titlebar=0,toolbar=0,location=1");                }</script><a title="使用QQ账号直接登陆" onClick="toQzoneLogin()" href="#"><img style="" alt="QQ账号直接登陆" src="qq_logo_1.png"><span style="color: rgb(0, 90, 160); font-size: 12px; position: relative; top: 6px;">QQ</span></a> <a title="使用人人账号直接登陆" target="_top" href="https://graph.renren.com/oauth/authorize?client_id=180644&amp;response_type=code&amp;scope=publish_feed,photo_upload&amp;state=a%3d1%26b%3d2&amp;redirect_uri=${_STORE_URL}/unionlg/renren/accesstoken.php"><img style="margin-top: 2px;" alt="人人账号登陆" src="rr_login.png"><span style="color: rgb(0, 90, 160); font-size: 12px; position: relative; top: 6px;">人人网</span></a> <a href="${_STORE_URL}/alipay/auth_authorize.php"> <img style="margin-top: 6px;" src="zhifubao.png"><span style="color: rgb(0, 90, 160); font-size: 12px; position: relative; top: 6px;">支付宝</span></a> <!-- &nbsp;&nbsp; <a href="?passport-sina.html" style=""> <img src="unionlg/touxiang/xinaLOGO_16x16.png"><span style="color:#005aa0; font-size:12px;position:relative; top:-5px;">新浪微博</span></a> --> </div>
      <p></p>
    </div>
  </div>
 </div>
</body>
</html>
