<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />
<link href="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/css/jquery.fileupload-ui.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
	<aside class="aside-md bg-white b-r" id="subNav">
		<section class="vbox">
			<header class="b-b header">
				<p class="h4">
					<c:choose>
						<c:when test="${content != null }">编辑文章</c:when>
						<c:otherwise>添加文章</c:otherwise>
					</c:choose>
				</p>
			</header>
			<section class="scrollable w-f">
				<ul class="nav nav-pills nav-stacked no-radius">
					<li class="b-b m-t-none-reset nav-map active" id="nav_base"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 基本信息 </a></li>
					<li class="b-b m-t-none-reset nav-map" id="nav_content"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 详细介绍 </a></li>
					<li class="b-b m-t-none-reset nav-map" id="nav_meta"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 搜索引擎优化 </a></li>
				</ul>
			</section>
		</section>
	</aside>

	<aside class="bg-white">
		<section class="vbox">
			<header class="header b-b b-t bg-white-only">
				<div class="m-t-sm">
					<a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0"> <i class="fa fa-caret-right text fa-lg"></i> <i class="fa fa-caret-left text-active fa-lg"></i> </a>
				</div>
			</header>

			<section class="scrollable w-f">
				<form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/articleContent/add" method="post" enctype="multipart/form-data">

					<!-- 基本信息 @start -->
					<jsp:include page="content.edit.base.jsp" />
					<!-- 基本信息 @end -->

					<!-- 详细介绍 @start -->
					<jsp:include page="content.edit.content.jsp" />
					<!-- 详细介绍 @end -->

					<!-- 搜索引擎优化 @start -->
					<jsp:include page="content.edit.meta.jsp" />
					<!-- 搜索引擎优化 @end -->

					<input type="hidden" name="" id="content_id" value="${contentId}" />
				</form>
			</section>

			<footer class="footer b-t bg-white-only">
				<div class="m-t-sm">
					<a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="1"> <i class="fa fa-caret-right text fa-lg"></i> <i class="fa fa-caret-left text-active fa-lg"></i> </a>
					<button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
					<button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
					<span id="edit_notice"></span>
				</div>
			</footer>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>
<script src="${STATIC_URL}/plugins/ckeditor/4.4.1/ckeditor.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/sortable/jquery.sortable.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/article/js/content.edit.js" type="text/javascript"></script>
