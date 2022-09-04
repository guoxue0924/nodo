<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/plugins/datepicker/css/datepicker2.css" rel="stylesheet" type="text/css">

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>实物交易订单</span>
    </div>
</div>

<jsp:include page="../cas/user.nav.jsp"/>

<div class="right-layout">

    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="normal"><a href="${BASE_URL}/front/bts/refund">退货申请</a></li>
            </ul>
        </div>
       	<form id="searchForm" action="" method="post">
        <table class="ncm-search-table">
            <tbody>
	            <tr>
	                <td>&nbsp;</td>
	                <th>申请时间</th>
	                <td class="w240">
	                    <input name="startDate" id="startDate" type="text" class="text w70" 
	                    	readonly="readonly">
	                    <label class="add-on"><i class="icon-calendar"></i></label>&nbsp;–&nbsp;<input name="endDate" id="endDate" type="text" class="text w70" 
	                    	readonly="readonly">
	                   	<label class="add-on"><i class="icon-calendar"></i></label></td>
	                <th>
	                	<select id="type" name="searchType">
	                    	<option value="1">订单编号</option>
	                    	<option value="2">退款编号</option>
	                    	<option value="3">商品名称</option>
	                	</select>
	                </th>
	                <td class="w160">
	                	<input type="text" id="key" class="text w150" name="key" value="">
	                </td>
	                <td class="w70 tc">
	                	<label class="submit-border">
	                    	<input type="button" class="submit filterBtn" id="searchBtn" value="搜索">
	                	</label>
	                </td>
	            </tr>
            </tbody>
        </table>
        </form>
        <table id="refundList" class="ncm-default-table order ">
            <thead>
            <tr>
                <th class="w10"></th>
                <th colspan="2">商品</th>
                <th class="w100">退款金额（元）</th>
                <th class="w100">退货数量（件）</th>
                <th class="w100">审核状态</th>
                <th class="w100">操作</th>
            </tr>
            </thead>
            <tbody>
            	<tr>
			        <td class="sep-row" colspan="20"></td>
			    </tr>
			    <tr>
			        <th colspan="20">
			            <span class="fl ml10">退货编号：{{refund_sn}}</span>
			            <span>申请时间：{{ctime}}</span>
			            <span member_id="1"></span>
			        </th>
			    </tr>
			    <tr>
			        <td class="bdl"></td>
			        <td class="w50">
			            <div class="pic-thumb">
			            	<a target="_blank" href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=49">
			            		<img src="/upload/{{goods_image}}">
			            	</a>
			            </div>
			        </td>
			        <td class="tl">
			            <dl class="goods-name">
			                <dt>
			                	<a target="_blank" href="/goods/goods/detail">{{goods_name}}</a>
			                </dt>
			                <dd>
			                	订单编号：<a target="_blank" href="index.php?act=member_order&amp;op=show_order&amp;order_id=17">
			                				{{order_number}}
			                			</a>
			                </dd>
			            </dl>
			        </td>
			        <td>{{buy_price}}</td>
			        <td>{{buy_num}}</td>
			        <td>{{if status==1}}已成功退货{{else if status==2}}等待审核{{else if status==3}}已审核/待退货{{else if status=4}}用户已退货{{/if}}
			        </td>
			        <td class="bdr">
			        	<a class="ncm-btn" href="/cas/refund/detail?refund_id=3">查看</a>
			        </td>
			    </tr>
            </tbody>
            <tfoot>
            <td colspan="20">
                <ul id="paginationBar" class="pagination pagination-sm"></ul>
            </td>
            </tfoot>
        </table>

    </div>
</div>
<div class="clear"></div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN-2.js"></script>
<script src="${STATIC_URL}/front/js/utils.js"></script>
<script src="${STATIC_URL}/front/modules/bts/js/refund.index.js" type="text/javascript"></script>