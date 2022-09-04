<%@ page contentType="text/html;charset=UTF-8" %>
<!-- .header -->
<header class="bg-light dk header navbar navbar-fixed-top-xs b-b">
    <div class="col-sm-3">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
            <i class="fa fa-bars"></i>
        </a>
        <a href="javascript:;" class="navbar-brand" data-toggle="fullscreen">
            <img src="${STATIC_URL}/front/images/front/logo.png" class="m-r-sm">${SITE_NAME} - 会员中心
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user">
            <i class="fa fa-cog"></i>
        </a>
    </div>
    
    <div class="col-sm-6">
        <ul class="nav navbar-nav">
            <li class="active">
                <a href="${STATIC_URL}">首页</a>
            </li>
            <li class="active">
                <a href="${STATIC_URL}/goods">商品部分</a>
            </li>
            <li class="active">
                <a href="${STATIC_URL}/bts">订单部分</a>
            </li>
            <li class="active">
                <a href="${STATIC_URL}/cas/panel">会员部分</a>
            </li>
            <li class="active">
                <a href="${STATIC_URL}/store">商户部分</a>
            </li>
        </ul>
    </div>
    
    {if $loggedInUser}
    <ul class="nav navbar-nav navbar-right hidden-xs nav-user">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="thumb-sm avatar pull-left">
                    <img src="${STATIC_URL}/panel/img/avatar.jpg">
                </span>
                ${loggedInUser.realname} <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
                <li>
                    <a href="/cas/sign/out">注销</a>
                </li>
            </ul>
        </li>
    </ul>
    {else}
    <a type="button" href="${BASE_URL}/front/cas/login?continue=${continue}" class="btn btn-default btn-sm pull-right m-sm">登录</a>
    {/if}
</header>
<!-- /.header -->