<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/plugins/datepicker/css/datepicker2.css" rel="stylesheet" type="text/css">
<link href="${STATIC_URL}/plugins/dialog/dialog.css" rel="stylesheet" type="text/css">
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
                <li class="active"><a href="javascript:void(0);">订单列表</a></li>
                <li class="normal"><a href="javascript:void(0);">回收站</a></li>
            </ul>
        </div>
        <form method="get" action="${BASE_URL}/front/bts/order" id='searchForm'>
        	<input type="hidden" name="isUserDel" id="isUserDel" value="0">
            <table class="ncm-search-table">
                <tbody><tr>
                    <td>&nbsp;</td>
                    <th>订单状态</th>
                    <td class="w100">
                    	<select name="orderStatus">
	                        <option value="" <c:if test="${initStatus == null}">selected</c:if>>所有订单</option>
	                        <c:forEach items="${orderStatus}" var="entry">
		                        <option id="orderStatus_${entry.key}" value="${entry.key}" <c:if test="${initStatus == entry.key}">selected</c:if>>${entry.value}</option>
	                        </c:forEach>
	                    </select>
                    </td>
                    <th>下单时间</th>
                    <td class="w240">
                    	<input type="text" class="text w70" name="startDate" id="startDate" readonly="readonly"><label class="add-on"><i class="icon-calendar"></i></label>
                    		&nbsp;–&nbsp;
                    	<input type="text" class="text w70" name="endDate" id="endDate" readonly="readonly"><label class="add-on"><i class="icon-calendar"></i></label>
                   	</td>
                    <th>订单号</th>
                    <td class="w160"><input type="text" class="text w150" name="key" ></td>
                    <td class="w70 tc"><label class="submit-border">
                        <input type="button" class="submit" value="搜索">
                    </label></td>
                </tr>
                </tbody>
             </table>
        </form>
        <table class="ncm-default-table order">
            <thead>
            <tr>
                <th class="w10"></th>
                <th colspan="2">商品</th>
                <th class="w100">单价（元）</th>
                <th class="w40">数量</th>
                <th class="w100">售后</th>
                <th class="w120">订单金额</th>
                <th class="w100">交易状态</th>
                <th class="w150">交易操作</th>
            </tr>
            </thead>
            <tfoot>
            	<tr>
                    <td colspan="19" class="ncs-norecord hide">暂无符合条件的数据记录</td>
                </tr>
	            <tr>
	                <td colspan="19"><div><ul id="paginationBar" class="pagination-sm pagination"></ul> </div></td>
	            </tr>
            </tfoot>
        </table>
    </div>
</div>
<div class="clear"></div>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN-2.js"></script>
<script src="${STATIC_URL}/plugins/dialog/dialog.js"></script>
<script src="${STATIC_URL}/front/js/utils.js"></script>
<script src="${STATIC_URL}/front/modules/bts/js/order.list.js"></script>