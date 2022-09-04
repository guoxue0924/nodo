<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>我的代金券</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active"><a href="${BASE_URL}/front/cas/coupon">我的代金券</a></li>
            </ul>
        </div>
        	<form method="get" action="${BASE_URL}/front/cas/coupon" id='searchForm'>
            <table class="ncm-search-table">
                <tbody>
                <tr>
                    <td>&nbsp;</td>
                    <td class="w100 tr">
	                    <select id="status" name="status">
	                        <option value="">状态</option>
	                        <option value="0"> 未使用</option>
	                        <option value="1"> 已使用</option>
	                    </select>
                    </td>
                    <td class="w70 tc"><label class="submit-border">
                        <input type="button" value="搜索" id="searchBtn" class="submit">
                    </label></td>
                </tr>
                </tbody>
            </table>
            </form>
        <table id="couponList" class="ncm-default-table">
            <thead>
	            <tr>
	                <th class="w10"></th>
	                <th class="w70"></th>
	                <th class="tl">代金券名称</th>
	                <th class="w80">面额（元）</th>
	                <th class="w200">有效期</th>
	                <th class="w100">状态</th>
	                <th class="w70">操作</th>
	            </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="20">
                    <div >
                    	<div class="ncs-norecord hide">暂无符合条件的数据记录</div>
                        <ul id="paginationBar" class="pagination pagination-sm"></ul>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<div class="clear"></div>
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script src="${STATIC_URL}/front/modules/cas/js/coupon.index.js" type="text/javascript"></script>