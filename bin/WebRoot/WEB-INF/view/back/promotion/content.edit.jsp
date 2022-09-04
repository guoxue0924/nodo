<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>

<section class="hbox stretch">
	<aside class="aside-md bg-white b-r">
		<section class="vbox">
			<header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${content!=null}">编辑促销活动</c:when><c:otherwise>添加促销活动</c:otherwise></c:choose></p>
            </header>
            <section class="scrollable w-f">
            	<section class="edit-map wrapper" id="edit_base">
					<form class="form-horizontal" id="edit_form" action=
						<c:choose> <c:when test="${content!=null}">"${BASE_URL}/back/promotionContent/edit"</c:when><c:otherwise>"${BASE_URL}/back/promotionContent/add"</c:otherwise></c:choose> 
						method="post">
						<input type="hidden" name="promotionId" id="promotionId" value="${content.promotionId}">
						<input type="hidden" name="rules" id="rules" value='${content.rules}'>
						<c:forEach items="${content.skuList}" var="sku" >
							<input class="hidden" type="checkbox" checked name="skuIdList" value="${sku.skuId}">
						</c:forEach>
						
						<section class="panel panel-default">
							<header class="panel-heading font-bold">基本信息</header>
							<div class="panel-body">
								<div class="form-group">
					                <label for="categoryId" class="col-sm-2 control-label"><font class="red">* </font>活动类型</label>
					                <div class="col-sm-4">
					                    <select id="categoryId" name="categoryId" class="form-control">
					                        <option value="0">请选择</option>
					                        <c:forEach items="${categorys}" var="category">
					                            <option id="${category.templateName}" value="${category.categoryId}" <c:if test="${content.categoryId == category.categoryId}">selected</c:if>>${category.title}</option>
					                        </c:forEach>
					                    </select>
					                </div>
					            </div>
					            <div class="line line-dashed line pull-in"></div>
					            <div class="form-group">
					                <label for="title" class="col-sm-2 control-label"><font class="red">* </font>活动名称</label>
					                <div class="col-sm-4">
					                    <input type="text" class="form-control" id="title" name="title" value="${content.title}" placeholder="请输入活动名称" />
					                </div>
					            </div>
					            <div class="line line-dashed line pull-in"></div>
					            <div class="form-group">
					            	<label for="startTime" class="col-sm-2 control-label">开始时间</label>
					                <div class="col-sm-4">
					                	<input id="startTime" name="startTime" class="input-sm input-s datetimepicker-input form-control" type="text" 
					                        value="<fmt:formatDate value="${content.startTime}" pattern="yyyy-MM-dd hh:mm"/>" readonly="readonly" placeholder="点击选择开始时间" />
					                </div>
					            </div>
					            <div class="line line-dashed line pull-in"></div>
					            <div class="form-group">
					                <label class="col-sm-2 control-label">结束时间</label>
					                <div class="col-sm-4">
					                    <input id="endTime" name="endTime" class="input-sm input-s datetimepicker-input form-control" type="text" 
					                            value="<fmt:formatDate value="${content.endTime}" pattern="yyyy-MM-dd hh:mm"/>" readonly="readonly" placeholder="点击选择结束时间" />
					                </div>
					            </div>
					            <div class="line line-dashed line pull-in" ></div>
					            <div class="form-group">
					                <label class="col-sm-2 control-label">状态</label>
					                <div class="col-sm-4">
					                    <label class="checkbox-inline p-left-0">
					                        <input type="radio" <c:if test="${content.status == 1}">checked</c:if> name="status" value="1" /> 启用
					                    </label>
					                    <label class="checkbox-inline p-left-0">
					                        <input type="radio" <c:if test="${content == null || content.status == 0}">checked</c:if> name="status" value="0" /> 关闭
					                    </label>
					                </div>
					            </div>
					            <div class="line line-dashed line pull-in" ></div>
							</div>
						</section>
						
						<section class="panel panel-default">
							<header class="panel-heading font-bold">活动规则</header>
							<div class="panel-body" id="ruleDiv">暂无规则</div>
						</section>
					</form>
					
					<section class="hbox stretch" id="skuSection">
						<aside class="bg-white b-r">
							<section class="panel panel-default">
								<header class="panel-heading font-bold">选择商品</header>
								<div class="panel-body">
				                	<form class="form-inline" action="${BASE_URL}/back/goodsContent/pageByActivity" id="searchForm">
						            	<div class="row text-sm wrapper-sm padder-b-xs">
						                	<div class="col-sm-8 m-b-xs">
						                   		<button title="Refresh" class="btn btn-sm btn-default action-refresh" type="button"><i class="fa fa-refresh"></i></button>
						                    	<select name="categoryId" id="related_category_id" class="input-sm form-control input-s-sm inline">
						                        	<option value="0">选择分类</option>
						                        	<c:forEach items="${goodsCategorys}" var="category">
						                        		<option value="${category.categoryId}" >${category.categoryName}</option>
						                        	</c:forEach>
						                    	</select>
							                    <select name="brandId" id="related_brand_id" class="input-sm form-control input-s-sm inline">
							                        <option value="0">选择品牌 </option>
							                        	<c:forEach items="${brands}" var="brand">
							                        		<option value="${brand.brandId}" >${brand.brandName}</option>
							                        	</c:forEach>
							                    </select>
						                 	</div>
						                	<div class="col-sm-4 pull-right">
							                    <div class="input-group">
							                        <input type="text" class="input-sm form-control" name="key" placeholder="请输入商品名称" />
							                        <span class="input-group-btn">
							                            <button type="button" class="btn btn-sm btn-default action-refresh">搜索</button>
							                        </span>
							                    </div>
						                	</div>
						            	</div>
					            	</form>
		
					                <div class="table-responsive">
						                <table class="table table-striped m-b-none datagrid" id="content_listing">
						                    <thead>
						                    </thead>
						                    <tfoot>
						                        <tr>
						                            <th class="row">
						                                <div class="datagrid-footer-left col-sm-6 text-center-xs m-l-n"
						                                     style="display:none;">
						                                    <div class="grid-controls m-t-sm">
					                                            <span>
					                                                <span class="grid-start"></span> -
					                                                <span class="grid-end"></span> （共
					                                                <span class="grid-count"></span>）
					                                            </span>
		
					                                        <div class="select grid-pagesize dropup" data-resize="auto">
					                                            <button data-toggle="dropdown"
					                                                    class="btn btn-sm btn-default dropdown-toggle">
					                                                <span class="dropdown-label"></span>
					                                                <span class="caret"></span>
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
		
					                                <div class="datagrid-footer-right col-sm-6 text-right text-center-xs"
					                                     style="display:none;">
					                                    <div class="grid-pager m-r-n">
					                                        <button type="button" class="btn btn-sm btn-default grid-prevpage"><i
					                                                class="fa fa-chevron-left"></i></button>
					                                        <!--<span>页</span>-->
		
					                                        <div class="inline">
					                                            <div class="input-group dropdown combobox">
					                                                <input class="input-sm form-control" type="text">
		
					                                                <div class="input-group-btn dropup">
					                                                    <button class="btn btn-sm btn-default" data-toggle="dropdown"><i
					                                                            class="caret"></i></button>
					                                                    <ul class="dropdown-menu pull-right"></ul>
					                                                </div>
					                                            </div>
					                                        </div>
					                                        <span>/ <span class="grid-pages"></span></span>
						                                        <button type="button" class="btn btn-sm btn-default grid-nextpage"><i class="fa fa-chevron-right"></i>
						                                        </button>
						                                    </div>
						                                </div>
						                            </th>
						                        </tr>
						                    </tfoot>
						                </table>
					                </div>
			                    	<input type="hidden" name="related_goods" id="related_goods" value=""/>	
								</div>
							</section>
						</aside>
						<aside class="bg-light lter aside-md" id="selectedSkus">
					        <section class="vbox">
					            <header class="b-b header"><p class="h4">已选<font class="text-sm">（可拖拽排序）</font></p></header>
					            <section class="scrollable w-f">
					                <ul class="nav nav-stacked list-group gutter list-group-lg list-group-sp sortable">
					                	<c:forEach items="${content.skuList}" var="sku" >
					                		<li class="b-b m-t-none-reset" id="li_${sku.skuId}" draggable="true">
						                        <a href="javascript:void(0);">
						                            <i title="移除该商品" class="fa fa-times pull-right m-t-xs fa-remove-related-goods"></i>
						                            <i class="fa fa-fw fa-ellipsis-v"></i><font class="related-goods-name">${sku.name}</font>
						                        </a>
					                    	</li>
					                	</c:forEach>
					                </ul>
					            </section>
					        </section>
					    </aside>
					</section>
				</section>
            </section>
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${content==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
		</section>
	</aside>
</section>

<div class="hidden" id="sku_template">
	<li class="b-b m-t-none-reset" id="" draggable="true">
		<a href="javascript:;">
			<i title="移除该商品" class="fa fa-times pull-right m-t-xs fa-remove-related-goods"></i>
			<i class="fa fa-fw fa-ellipsis-v"></i>
			<font class="related-goods-name"></font>
		</a>
	</li>
	<input class="hidden" type="checkbox" checked name="skuIdList">
</div>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${STATIC_URL}/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/plugins/sortable/jquery.sortable.js"></script>
<script src="${STATIC_URL}/back/modules/promotion/js/content.edit.js" type="text/javascript"></script>
