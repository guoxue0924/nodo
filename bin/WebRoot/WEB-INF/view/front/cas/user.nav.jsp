<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${STATIC_URL}/front/modules/cas/css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/common.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/ToolTip.js"></script>
<script id="noData" type="text/html">
    <tr>
        <td colspan="20" class="norecord"><div class="warning-option"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></div></td>
    </tr>
</script>
<div class="ncm-container">
<div class="left-layout">
<div class="ncm-l-top">
    <h2><a href="${BASE_URL}/front/cas/index" title="我的商城">我的商城</a></h2>
</div>
<div class="ncm-user-info">
    <div class="avatar"><img src="${IMG_URL}${loggedInUser.avatar}">
        <div class="frame"></div>
        <!--<a href="index.php?act=member_message&amp;op=message" class="new-message" title="新消息"></a>-->
    </div>
    <div class="handle">
        <a href="${BASE_URL}/front/cas/account/avatar" title="修改头像">
            <i class="icon-camera"></i>修改头像
        </a>
        <a href="${BASE_URL}/front/cas/account" title="修改资料">
            <i class="icon-pencil"></i>修改资料
        </a>
        <a href="${BASE_URL}/front/cas/logout" title="安全退出"><i class="icon-off"></i>安全退出</a></div>
    <div class="name">${loggedInUser.username}&nbsp;
        <div class="nc-grade-mini" style="cursor:pointer;" onclick="javascript:go('#');">V0</div>
    </div>
</div>
<ul class="ncm-sidebar ncm-quick-menu">
    <li class="side-menu-quick" nctype="commonOperations" style="display: none;"> <a href="javascript:void(0)">
        <h3>常用操作</h3>
    </a>
        <ul>
        </ul>
    </li>
</ul>
<ul id="sidebarMenu" class="ncm-sidebar">
    <li class="side-menu"><a href="javascript:void(0)" key="trade">
        <h3>交易管理</h3>
    </a>
        <ul>
            <li><a href="${BASE_URL}/front/bts/order">实物交易订单</a></li>
            <li><a href="${BASE_URL}/front/cas/favorite">我的收藏</a></li>
            <li><a href="${BASE_URL}/front/cas/comment">交易评价/晒单</a></li>
            <li><a href="${BASE_URL}/front/cas/coupon">我的优惠券</a></li>
        </ul>
    </li>
    <li class="side-menu"><a href="javascript:void(0)" key="serv">
        <h3>客户服务</h3>
    </a>
        <ul>
            <li><a href="${BASE_URL}/front/bts/refund">退款及退货</a></li>
            <li><a href="${BASE_URL}/front/cas/feedback">平台客服</a></li>
        </ul>
    </li>
    <li class="side-menu"><a href="javascript:void(0)" key="info">
        <h3>资料管理</h3>
    </a>
        <ul>
            <li><a href="${BASE_URL}/front/cas/account">账户信息</a></li>
            <li><a href="${BASE_URL}/front/cas/security">账户安全</a></li>
            <li><a href="${BASE_URL}/front/cas/consignee">收货地址</a></li>
            <li><a href="${BASE_URL}/front/cas/message">我的消息</a></li>
        </ul>
    </li>
</ul>
</div>
