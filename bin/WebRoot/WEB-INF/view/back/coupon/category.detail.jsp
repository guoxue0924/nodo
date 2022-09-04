<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="bg-white">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="m-t-sm">
                    <a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0">
                        <i class="fa fa-caret-right text fa-lg"></i>
                        <i class="fa fa-caret-left text-active fa-lg"></i>
                    </a>
                        <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                </div>
            </header>
              
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">优惠券详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                            <tr>                    
                                <td class="col-sm-2">优惠券名称:</td>
                                <td class="col-sm-4">${couponTO.coupon.couponName}</td>
                                <td class="col-sm-2">用户券生成数量:</td>
                                <td class="col-sm-4">${couponTO.coupon.total}</td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">有效开始时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${couponTO.coupon.validStime}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td class="col-sm-2">有效结束时间:</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${couponTO.coupon.validEtime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">发放开始时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${couponTO.coupon.grantStime}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td class="col-sm-2">发放结束时间:</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${couponTO.coupon.grantEtime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">优惠券类型:</td>
                                <td class="col-sm-4">
                                    <c:if test="${couponTO.coupon.couponType == 0}">全场</c:if>
                                    <c:if test="${couponTO.coupon.couponType == 1}">满减</c:if>
                                </td>
                                <td class="col-sm-2">优惠金额:</td>
                                <td class="col-sm-4">${couponTO.coupon.faceValue}</td>
                            </tr>
                            <c:if test="${couponTO.coupon.couponType == 1}">
                            <tr>                    
                                <td class="col-sm-2">商品分类:</td>
                                <td class="col-sm-4">${category.categoryName}</td>
                                <td class="col-sm-2">基本要求金额:</td>
                                <td class="col-sm-4">${couponTO.relation.basicPrice}</td>
                            </tr>
                            </c:if>
                            <tr>                    
                                <td class="col-sm-2">是否允许积分兑换:</td>
                                <td class="col-sm-4">
                                    <c:if test="${couponTO.coupon.isExchange == 0}">允许</c:if>
                                    <c:if test="${couponTO.coupon.isExchange == 1}">不允许</c:if>
                                </td>
                                <td class="col-sm-2"><c:if test="${couponTO.coupon.isExchange == 0}">兑换所需积分:</c:if></td>
                                <td class="col-sm-4"><c:if test="${couponTO.coupon.isExchange == 0}">${couponTO.coupon.couponPoint}</c:if></td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">使用规则:</td>
                                <td class="col-sm-4">${couponTO.coupon.rule}</td>
                                <td class="col-sm-2">使用详情:</td>
                                <td class="col-sm-4">${couponTO.coupon.body}</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/coupon/js/category.detail.js" type="text/javascript"></script>