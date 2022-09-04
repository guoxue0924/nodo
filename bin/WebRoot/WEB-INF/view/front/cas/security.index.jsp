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
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active"><a href="">账户安全</a></li>
            </ul>
        </div>
        <div class="ncm-security-user">
            <h3>您的账户信息</h3>

            <div class="user-avatar"><span><img src="${IMG_URL}${loggedInUser.avatar}"></span></div>
            <div class="user-intro">
                <dl>
                    <dt>登录账号：</dt>
                    <dd>${loggedInUser.username}</dd>
                </dl>
                <dl>
                    <dt>绑定邮箱：</dt>
                    <dd>${loggedInUser.email}</dd>
                </dl>
                <dl>
                    <dt>手机号码：</dt>
                    <dd>${loggedInUser.phone}</dd>
                </dl>
                <dl>
                    <dt>上次登录：</dt>
                    <dd>${loggedInUser.lastLoginTime}　|　IP地址:${loggedInUser.lastLoginIp}&nbsp;<span>（不是您登录的？请立即<a href="">“更改密码”</a>）。</span>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="ncm-security-container">
            <div class="title">您的安全服务</div>
           <%--  <div class="current ${level}">当前安全等级：<strong>${security.label}</strong><span>(${security.tips})</span></div> --%>
            <dl id="password" class="yes">
                <dt><span class="icon"
                        ><i></i>
                </span>
                <span class="item">
                    <h4>登录密码</h4>
                    <h6>已设置</h6>
                </span></dt>
                <dd>
                    <span class="explain">安全性高的密码可以使账号更安全。建议您定期更换密码，且设置一个包含数字和字母，并长度超过6位以上的密码，为保证您的账户安全，只有在您绑定邮箱或手机后才可以修改密码。</span>
                    <span class="handle"><a href="${BASE_URL}/front/cas/security/edit?type=password" class="ncm-btn  ncm-btn-orange">修改密码</a>
                    </span>
                </dd>
            </dl>
            <dl id="email" class="${loggedInUser.verifiedEmail}">
                <dt>
                    <span class="icon"><i></i></span><span class="item">
                    <h4>邮箱绑定</h4>
                    <h6><c:choose><c:when test="${loggedInUser.verifiedEmail == 0}">未绑定</c:when><c:otherwise>已设置</c:otherwise></c:choose></h6>
                    </span>
                </dt>
                <dd>
                    <span class="explain">进行邮箱验证后，可用于接收敏感操作的身份验证信息，以及订阅更优惠商品的促销邮件。</span><span class="handle">
                    <c:choose><c:when test="${loggedInUser.verifiedPhone == 0}">
                        <a href="${BASE_URL}/front/cas/security/edit?type=email" class="ncm-btn ncm-btn-acidblue bd">绑定邮箱</a>
                    </c:when><c:otherwise>
                        <a href="${BASE_URL}/front/cas/security/edit?type=email" class="ncm-btn ncm-btn-orange jc">修改邮箱</a>
                    </c:otherwise></c:choose>
                </span>
                </dd>
            </dl>
            <dl id="mobile" class="">
                <dt>
                    <span class="icon"><i></i></span><span class="item">
                        <h4>手机绑定</h4>
                        <h6><c:choose><c:when test="${loggedInUser.verifiedPhone == 0}">未绑定</c:when><c:otherwise>已设置</c:otherwise></c:choose></h6>
                    </span>
                </dt>
                <dd>
                    <span class="explain">进行手机验证后，可用于接收敏感操作的身份验证信息，以及进行积分消费的验证确认，非常有助于保护您的账号和账户财产安全。</span>
                    <span class="handle">
                        <c:choose><c:when test="${loggedInUser.verifiedPhone == 0}">
                        <a href="${BASE_URL}/front/cas/security/edit?type=phone" class="ncm-btn ncm-btn-acidblue bd">绑定手机</a>
                        </c:when><c:otherwise>
                        <a href="${BASE_URL}/front/cas/security/edit?type=phone" class="ncm-btn ncm-btn-orange jc">修改手机</a>
                        </c:otherwise></c:choose>
                    </span>
                </dd>
            </dl>
        </div>
    </div>
</div>
<div class="clear"></div>
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>