<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"><c:choose><c:when test="${group != null}">编辑分组</c:when><c:otherwise>添加分组</c:otherwise></c:choose></p>
            </header>
            
            <section class="scrollable wrapper panel w-f">
	            <form class="form-horizontal" action="${BASE_URL}/back/trendPropertyGroup/add" method="post" id="edit_form" enctype="multipart/form-data">
	                <div class="form-group">
	                    <label for="propertyGroupName" class="col-sm-2 control-label"><font class="red">* </font>分组名称</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="propertyGroupName" name="propertyGroupName" value="${group.propertyGroupName}" placeholder="请输入分组名称" />
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label for="sort_order" class="col-sm-2 control-label">序号</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${group.sortOrder}" />
	                    </div>
	                </div>
	                <input type="hidden" name="propertyGroupId" value="${group.propertyGroupId}" />
	                <input type="hidden" name="propertyIds" value="${propertyIds}" />
	            </form>
	                <div class="line line-dashed line-lg pull-in"></div>
	                
	                <div class="form-group">
                        <label for="sort_order" class="col-sm-2 control-label hide">关联属性</label>
                        <div class="col-sm-12">
                            <section class="panel panel-default">
                                <header class="panel-heading">
                                    选择关联属性
                                    <a id="btn_add_property_value" class="btn btn-xs btn-default pull-right hide">
                                        <i class="fa fa-plus text"></i>
                                        <span class="text">添加</span>
                                    </a>
                                    
                                    <form action="${BASE_URL}/back/trendProperty/page" id="searchForm">
                                    <div class="col-sm-4 m-t-n-xs pull-right" id="search">
                                        <div class="input-group">
						                    <input type="text" name="key" class="input-sm form-control" placeholder="Search" />
						                    <span class="input-group-btn">
						                        <a class="btn btn-sm btn-default" id="action_search" type="button">搜索</a>
						                    </span>
						                </div>
					                </div>
					                </form>
                                </header>
                                <table class="table table-striped m-b-none datagrid" id="content_listing">
				                    <thead>
				                    </thead>
				                    <tfoot>
				                        <tr>
				                            <th class="row">
				                                <div class="datagrid-footer-left col-sm-6 text-center-xs m-l-n" style="display:none;">
				                                    <div class="grid-controls m-t-sm">
			                                            <span>
			                                                <span class="grid-start"></span> -
			                                                <span class="grid-end"></span> （共
			                                                <span class="grid-count"></span>）
			                                            </span>
				
				                                        <div class="select grid-pagesize dropup" data-resize="auto">
				                                            <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle">
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
				                                        <button type="button" class="btn btn-sm btn-default grid-prevpage"><i class="fa fa-chevron-left"></i></button>
				                                        <!--<span>页</span>-->
				                                        <div class="inline">
				                                            <div class="input-group dropdown combobox">
				                                                <input class="input-sm form-control" type="text">
				                                                <div class="input-group-btn dropup">
				                                                    <button class="btn btn-sm btn-default" data-toggle="dropdown"><i class="caret"></i></button>
				                                                    <ul class="dropdown-menu pull-right"></ul>
				                                                </div>
				                                            </div>
				                                        </div>
				                                        <span>/ <span class="grid-pages"></span></span>
				                                        <button type="button" class="btn btn-sm btn-default grid-nextpage"><i class="fa fa-chevron-right"></i></button>
				                                    </div>
				                                </div>
				                            </th>
				                        </tr>
				                    </tfoot>
				                </table>
                            </section>
                        </div>
                    </div>
	        </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                     <c:if test="${group == null}"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
    
    <aside class="bg-light lter aside-md" id="selected_property">
        <section class="vbox">
            <header class="b-b header"><p class="h4">已选属性<font class="text-sm">（可拖拽排序）</font></p></header>
            <section class="scrollable w-f">
                <ul class="nav nav-stacked list-group gutter list-group-lg list-group-sp sortable">
                	<c:forEach items="${properties}" var="v">
                    <li class="b-b m-t-none-reset" id="li_property_${v.propertyId}" propertyId="${v.propertyId}" draggable="true">
                        <a href="javascript:;">
                            <i title="移除该属性" class="fa fa-times pull-right m-t-xs fa-remove-property"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i><font class="property-name">${v.labelName}</font>
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/sortable/jquery.sortable.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/trend/js/propertyGroup.edit.js"></script>