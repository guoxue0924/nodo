<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/plugins/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css" id="cssfile2">

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>交易评价/晒单</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul id="listpj" class="tab">
                <li class="active">
                    <a href="${BASE_URL}/front/cas/comment">交易评价/晒单</a>
                </li>
            </ul>
        </div>
        <div class="ncm-evaluation-list">
            <div  id="commentList">
            </div>
            <div class="tc mt20 mb20">
            	<div class="ncs-norecord hide">暂无符合条件的数据记录</div>
	            <ul id="paginationBar" class="pagination-sm pagination"></ul>
            </div> 
        </div>
</div>
<div class="clear"></div>
</div>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-raty/jquery.raty.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery.nyroModal/custom.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery.poshytip.min.js" charset="utf-8"></script>
<script src="${STATIC_URL}/front/js/utils.js"></script>
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/comment.index.js" charset="utf-8"></script>