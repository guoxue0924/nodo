<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="public-top-layout w">
    <div class="topbar wrapper">
        <div class="user-entry">
            您好 <span>
      <a href="${BASE_URL}/front/cas/index">${loggedInUser.username}</a>
<%--             <div class="nc-grade-mini" style="cursor:pointer;" onclick="javascript:go('${BASE_URL}/front/cas/index');">V0  </div> --%>
            </span> ，欢迎来到 <a href="${BASE_URL}/front/index/index" title="首页" alt="首页"><span> 国货频道 v1.0</span></a> <c:choose><c:when test="${loggedInUser == null}"><span>[<a href="${BASE_URL}/front/cas/login">登录</a>]&nbsp;[<a href="${BASE_URL}/front/cas/register">注册</a>]</span></c:when><c:otherwise><span>[<a
                href="${BASE_URL}/front/cas/logout">退出</a>] </span></c:otherwise></c:choose>
        </div>
        <div class="quick-menu">
            <dl>
<!--                 <dt><a href="/wap">手机触屏版</a></dt> -->
                <dd>
                    <ul>
                        <li><a href="" title="招商入驻">招商入驻</a></li>
                        <li><a href="" target="_blank" title="登录商家管理中心">商家登录</a></li>
                    </ul>
                </dd>
            </dl>
            <dl class="">
                <dt><a href="${BASE_URL}/front/bts/order">我的订单</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${BASE_URL}/front/bts/order?status=2">待付款订单</a>
                        </li>
                        <li><a href="${BASE_URL}/front/bts/order?status=5">待确认收货</a>
                        </li>
                        <li><a href="${BASE_URL}/front/bts/order?status=1">待评价交易</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="${BASE_URL}/front/cas/favorite">我的收藏</a><i></i>
                </dt>
                <dd>
                    <ul>
                        <li><a href="${BASE_URL}/front/cas/favorite">商品收藏</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt>客户服务<i></i></dt>
                <dd>
                    <ul>
                        <li>
                            <a href="${BASE_URL}/front/articleContent/helpCenter?categoryId=1">帮助中心</a>
                        </li>
                        <li>
                            <a href="${BASE_URL}/front/articleContent/helpCenter?categoryId=2">售后服务</a>
                        </li>
                        <li>
                            <a href="${BASE_URL}/front/articleContent/helpCenter?categoryId=3">客服中心</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl class="weixin">
                <%-- <dt>关注我们<i></i></dt>
                <dd>
                    <h4>扫描二维码<br>
                                                关注商城微信号</h4>
                    <img src="${STATIC_URL}/front/images/index/my_weixin.jpg"></dd> --%>
            </dl>
        </div>
    </div>
</div>