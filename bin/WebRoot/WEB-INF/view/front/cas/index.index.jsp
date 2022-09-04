<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix.jsp"/>

<div style="clear:both"></div>
<div class="block box">
    <div class="blank"></div>
    <div id="ur_here">
        当前位置: <a href=".">首页</a> <code>&gt;</code> 用户中心
    </div>
</div>
<div class="blank"></div><div class="block clearfix">
    ${nav}
    
    <div class="AreaR">
        <div class="box">
            <div class="box_1">
                <div style="_height:1%;" class="userCenterBox boxCenterList clearfix">

                    <font class="f5"><b class="f4">${user_info.nickname}</b> 欢迎您回到 ECSHOP！</font><br>
                    <div class="blank"></div>
                    您的上一次登录时间: ${user_info.last_login_time}<br>
                    <div class="blank5"></div>
                    您的等级是 ${lvInfo.name}  <br>
                    <div class="blank5"></div><c:choose><c:when test="${user_info.verifiedEmail eq 0}"></c:when>
                    您还没有通过邮件认证 <a style="color:#006bd0;" href="javascript:sendHashMail()">点此发送认证邮件</a> <c:otherwise><c:if test="${user_info.verifiedEmail eq 1">您已通过邮件认证</c:if></c:otherwise></c:choose><br>
                    <div style="margin:5px 0; border:1px solid #f7dd98;padding:10px 20px; background-color:#fffad5;">
                        <img alt="" src="themes/default/images/note.gif">&nbsp;  </div>
                    <br><br>
                    <!--  
                    <div style="width:350px;" class="f_l">
                        <h5><span>您的账户</span></h5>
                        <div class="blank"></div>
                        余额:<a style="color:#006bd0;" href="user.php?act=account_log">￥0.00元</a><br>
                        红包:<a style="color:#006bd0;" href="http://www.ecshop.com">Powered&nbsp;by&nbsp;<strong><span style="color: #3366FF">ECShop</span></strong></a><br>
                        积分:0积分<br>
                    </div>
                    <div style="width:350px;" class="f_r">
                        <h5><span>用户提醒</span></h5>
                        <div class="blank"></div>
                        您最近30天内提交了0个订单<br>
                    </div>
                    <div class="blank5"></div> -->
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>

