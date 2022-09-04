<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="edit-map hbox stretch default-hidden" id="edit_related">
	<aside class="bg-white b-r">
		<section class="vbox">
			<section class="panel panel-default scrollable no-border m-b-none">
				<header class="panel-heading">选择相关商品</header>

				<div class="row text-sm wrapper-sm padder-b-xs">
					<div class="col-sm-8 m-b-xs">
						<button title="Refresh" class="btn btn-sm btn-default action-refresh" type="button">
							<i class="fa fa-refresh"></i>
						</button>
						<select name="relatedCategoryId" id="relatedCategoryId" class="input-sm form-control input-s-sm inline">
							<option value="0">选择分类</option>
							<c:forEach items="${categories}" var="v">
								<option value="${v.categoryId}" <c:if test="${v.categoryId == category.categoryId}">selected</c:if>>${v.strPadding}${v.categoryName}</option>
							</c:forEach>
						</select> <select name="relatedBrandId" id="relatedBrandId" class="input-sm form-control input-s-sm inline">
							<option value="0">选择品牌</option>
						</select>
					</div>
					<div class="col-sm-4 pull-right">
						<div class="input-group">
							<input type="text" class="input-sm form-control" name="relatedKey" placeholder="Search" /> <span class="input-group-btn">
								<button type="button" class="btn btn-sm btn-default action-refresh">搜索</button> </span>
						</div>
					</div>
				</div>

				<div class="table-responsive">
					<table class="table table-striped m-b-none datagrid" id="content_listing">
						<thead>
						</thead>
						<tfoot>
							<tr>
								<th class="row">
									<div class="datagrid-footer-left col-sm-6 text-center-xs m-l-n" style="display:none;">
										<div class="grid-controls m-t-sm">
											<span> <span class="grid-start"></span> - <span class="grid-end"></span> （共 <span class="grid-count"></span>） </span>

											<div class="select grid-pagesize dropup" data-resize="auto">
												<button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle">
													<span class="dropdown-label"></span> <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li data-value="5"><a href="#">5</a></li>
													<li data-value="15" data-selected="true"><a href="#">15</a></li>
													<li data-value="20"><a href="#">20</a></li>
													<li data-value="50"><a href="#">50</a></li>
													<li data-value="100"><a href="#">100</a></li>
												</ul>
											</div>
											<span>/页</span>
										</div>
									</div>

									<div class="datagrid-footer-right col-sm-6 text-right text-center-xs" style="display:none;">
										<div class="grid-pager m-r-n">
											<button type="button" class="btn btn-sm btn-default grid-prevpage">
												<i class="fa fa-chevron-left"></i>
											</button>
											<!--<span>页</span>-->

											<div class="inline">
												<div class="input-group dropdown combobox">
													<input class="input-sm form-control" type="text">

													<div class="input-group-btn dropup">
														<button class="btn btn-sm btn-default" data-toggle="dropdown">
															<i class="caret"></i>
														</button>
														<ul class="dropdown-menu pull-right"></ul>
													</div>
												</div>
											</div>
											<span>/ <span class="grid-pages"></span> </span>
											<button type="button" class="btn btn-sm btn-default grid-nextpage">
												<i class="fa fa-chevron-right"></i>
											</button>
										</div>
									</div>
								</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</section>
		</section>
	</aside>

	<aside class="bg-light lter aside-md" id="selected_related_goods">
		<section class="vbox">
			<header class="b-b header">
				<p class="h4">
					已选商品<font class="text-sm">（可拖拽排序）</font>
				</p>
			</header>
			<section class="scrollable">
				<ul class="nav nav-stacked list-group gutter list-group-lg list-group-sp sortable">
					<c:forEach items="${contentRelated}" var="v">
						<li class="b-b m-t-none-reset" id="li_related_goods_${v.contentId}" content_id="${v.contentId}" draggable="true"><a href="javascript:;"> <i title="移除该商品" class="fa fa-times pull-right m-t-xs fa-remove-related-goods"></i> <i class="fa fa-fw fa-ellipsis-v"></i><font class="related-goods-name">${v.name}</font> </a>
						</li>
					</c:forEach>
				</ul>
				<input type="hidden" name="relatedContentId" value="${content.related}" />
			</section>
		</section>
	</aside>

</section>