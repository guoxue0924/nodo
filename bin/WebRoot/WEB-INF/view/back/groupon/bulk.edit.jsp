<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${bulk!=null}">编辑抢购</c:when><c:otherwise>添加抢购</c:otherwise></c:choose></p>
                <input type="hidden" name="username_edit" id="username_edit" value="${user.username}" />
                
            </header>
                
            <section class="scrollable wrapper w-f">
                	<div class="panel-body">
		                <form class="form-horizontal h-100" id="edit_form" action=
		                <c:choose> <c:when test="${bulk!=null}">"${BASE_URL}/back/grouponBulk/edit"</c:when><c:otherwise>"${BASE_URL}/back/grouponBulk/add"</c:otherwise></c:choose> 
		                method="post">
                		<div class="form-group m-b-xs">
							<label for="sku" class="col-sm-2 control-label"><font class="red">* </font>关联商品</label>
							<div class="col-sm-6">
								<div id="good_select">
									<font class="property-name">${bulk.sku.name}</font>
								</div>
							</div>
						</div>
						<div class="line line-dashed line-sm pull-in"></div>
						<div class="form-group m-b-xs">
							<label class="col-sm-2 control-label"><font class="red">*
							</font>限购数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control input-s inline" id="buyNumber" name="buyNumber" value="${bulk.buyNumber}" placeholder="0为不限购">
									
								&nbsp;&nbsp;<font class="red">* </font>库存 &nbsp;&nbsp;&nbsp;
								<input type="text" class="form-control input-s inline" id="inventorySum" name="inventorySum" value="${bulk.inventorySum}" placeholder="请输入库存">
							</div>
						</div>
						<div class="line line-dashed line-sm pull-in"></div>
						<div class="form-group m-b-xs">
							<label for="price" class="col-sm-2 control-label"><font class="red">* </font>抢购价格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="price" name="price" value="${bulk.price}" placeholder="请输入抢购价格">
							</div>
						</div>
						<div class="line line-dashed line-sm pull-in"></div>
						<div class="form-group m-b-xs">
							<label for="start_time" class="col-sm-2 control-label"><font class="red">* </font>活动时间</label>
							<div class="col-sm-6">
								<input type="text" class="input-s datetimepicker form-control  inline  " id="startTime" name="startTime" 
								value="<fmt:formatDate value="${bulk.startTime}" pattern="yyyy-MM-dd hh:mm"/>" readonly="readonly">
							  -  
								<input type="text" class="input-s datetimepicker form-control  inline " id="endTime" name="endTime" 
								value="<fmt:formatDate value="${bulk.endTime}" pattern="yyyy-MM-dd hh:mm"/>" readonly="readonly">
							</div>
						</div>
						<div class="line line-dashed line-sm pull-in"></div>
						<div class="form-group m-b-xs">
							<label for="integral" class="col-sm-2 control-label">积分</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="integral" name="integral" value="${bulk.integral}" placeholder="请输入积分,0代表不使用，数字代表最高使用积分数">
							</div>
						</div>
						<div class="line line-dashed line-sm pull-in"></div>
						<div class="form-group m-b-xs">
							<label class="col-sm-2 control-label"><font class="red">*
							</font>分类</label>
							<div class="col-sm-6">
								<c:forEach items="${categorys}" var="category">
	                                <label class="checkbox-inline p-left-1">
		                                <input type="checkbox" name="categoryIds" id="categoryId" value="${category.categoryId}" 
		                                <c:forEach items="${bulk.categorys}" var="choosed">
		                                   <c:if test="${category.categoryId == choosed.categoryId}"> checked</c:if>
		                                </c:forEach>/>${category.title}
	                                </label>
	                            </c:forEach>
							</div>
						</div>
						<div class="line line-dashed line-sm pull-in"></div>
						<input type="hidden" id="bulkId"  name="bulkId" value="${bulk.bulkId}" />
						<input type="hidden" id="skuId"  name="skuId" value="${bulk.sku.skuId}" />
						</form>
                	</div>
                	<div class="form-group">
                		<section class="panel panel-default">
                			<header class="panel-heading">
                				选择关联商品
                				<div class="col-sm-4 m-t-n-xs pull-right" id="search">
                					<form class="form-inline" action="${BASE_URL}/back/goodsContent/pageByGrouponBulk" id="searchForm">
	                                    <div class="input-group">
						                    <input type="text" name="key" class="input-sm form-control" placeholder="商品名称">
						                    <span class="input-group-btn">
						                        <a class="btn btn-sm btn-default" id="action_search" type="button">搜索</a>
						                    </span>
						                </div>
					                </form>
				                </div>
                			</header>
					        <table class="table table-striped m-b-none datagrid">
					            <thead>
					            </thead>
					            <tfoot>
					                <tr>
					                    <th class="row">
					                        <div class="datagrid-footer-left col-sm-6 text-center-xs m-l-n">
					                            <div class="grid-controls m-t-sm">
					                                    <span>
					                                        <span class="grid-start"></span> -
					                                        <span class="grid-end"></span> （共
					                                        <span class="grid-count"></span>）
					                                    </span>
					
					                                <div class="select grid-pagesize" data-resize="auto">
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
					
					                        <div class="datagrid-footer-right col-sm-6 text-right text-center-xs">
					                            <div class="grid-pager m-r-n">
					                                <button type="button" class="btn btn-sm btn-default grid-prevpage"><i
					                                        class="fa fa-chevron-left"></i></button>
				                                    <div class="inline">
				                                        <div class="input-group dropdown combobox">
				                                            <input class="input-sm form-control" type="text">
				
				                                            <div class="input-group-btn dropdown">
				                                                <button class="btn btn-sm btn-default" data-toggle="dropdown"><i
				                                                        class="caret"></i></button>
				                                                <ul class="dropdown-menu pull-right"></ul>
				                                            </div>
				                                        </div>
				                                    </div>
				                                    <span>/ <span class="grid-pages"></span></span>
				                                    <button type="button" class="btn btn-sm btn-default grid-nextpage"><i
				                                            class="fa fa-chevron-right"></i></button>
				                                </div>
				                            </div>
				                        </th>
				                    </tr>
				                </tfoot>
				            </table>
					    </section>
                    </div>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${bulk ==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${STATIC_URL}/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/back/modules/groupon/js/bulk.edit.js"></script>