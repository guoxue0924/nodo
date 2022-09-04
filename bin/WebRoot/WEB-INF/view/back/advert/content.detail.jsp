<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp" />

<section class="hbox stretch">
	<aside class="aside-md bg-white b-r">
		<section class="vbox">
			<header class="header bg-white b-b clearfix">
				<div class="row m-t-sm">
					<div class="col-sm-8 m-b-xs">
						<a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a> <font class="h4 v-middle padder">广告详情 </font>
					</div>
				</div>
			</header>

			<section class="scrollable wrapper">
				<section class="panel panel-default portlet-item">
					<header class="panel-heading">基本信息</header>
					<table class="table table-striped2 m-b-none text-sm">
						<tbody>
							<tr>
								<td class="col-sm-2 text-right">所属页面</td>
								<td class="col-sm-4">${advert.pageName}</td>
								<td class="col-sm-2 text-right">所属广告位:</td>
								<td class="col-sm-4">${advert.boardName}</td>
							</tr>

							<tr>
								<td class="col-sm-2 text-right">广告类型</td>
								<td class="col-sm-4">
								    <c:if test="${advert.type == 1}">图片</c:if>
								    <c:if test="${advert.type == 2}">文字</c:if>
								    <c:if test="${advert.type == 3}">flash</c:if>
								    <c:if test="${advert.type == 4}">视频</c:if>
								    <c:if test="${advert.type == 5}">轮播</c:if>
								</td>
								<td class="col-sm-2 text-right">广告标题</td>
								<td class="col-sm-4">${advert.title}</td>
							</tr>

							<tr>
								<td class="col-sm-2 text-right">序号</td>
								<td class="col-sm-4">${advert.sortOrder}</td>
								<td class="col-sm-2 text-right">广告详情</td>
								<td class="col-sm-4">${advert.content}</td>
							</tr>

							<tr>
								<td class="col-sm-2 text-right">点击次数</td>
								<td class="col-sm-4">${advert.count}</td>
								<td class="col-sm-2 text-right">上传附件</td>
								<td class="col-sm-4">
								    <c:if test="${advert.attachmentid != null && advrt.attachmentid != ''}">
                                        <a href="${IMG_URL}${advert.attachmentFilePath}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${advert.attachmentFilePath}" /> </a>
                                    </c:if>
                                </td>
							</tr>

							<tr>
								<td class="col-sm-2 text-right">生效时间</td>
								<td class="col-sm-4">
								    <fmt:formatDate value="${advert.startTime}" type="date" dateStyle="long"/>
								</td>
								<td class="col-sm-2 text-right">结束时间</td>
								<td class="col-sm-4">
								    <fmt:formatDate value="${advert.endTime}" type="date" dateStyle="long"/>
								</td>
							</tr>

							<tr>
								<td class="ccol-sm-2 text-right">状态</td>
								<td class="col-sm-4">
								    <c:if test="${advert.status == 0}">不显示</c:if>
								    <c:if test="${advert.status == 1}">显示</c:if>
								    <c:if test="${advert.status == 2}">定时显示</c:if>
								</td>
								<td class="ccol-sm-2 text-right"></td>
								<td class="col-sm-4"></td>
							</tr>

						</tbody>
					</table>
				</section>

				<section class="panel panel-default portlet-item">
					<header class="panel-heading">绑定资源</header>
					<table class="table table-striped2 m-b-none text-sm">
						<tbody>
							<tr>
								<td class="ccol-sm-2 text-right"><c:if test="${advert.bindType == 1}">链接地址</c:if> <c:if test="${advert.bindType == 2}">商品</c:if> <c:if test="${advert.bindType == 4}">自定义</c:if></td>
								<td class="col-sm-10">${advert.bindSource}</td>
							</tr>
						</tbody>
					</table>
				</section>

			</section>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/back/modules/advert/js/content.detail.js" type="text/javascript"></script>