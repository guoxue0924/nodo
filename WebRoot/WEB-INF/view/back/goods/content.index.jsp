<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link rel="stylesheet" href="${STATIC_URL}/plugins/jquery-ztree/3.5.15/css/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${STATIC_URL}/back/modules/goods/css/content.index.css" type="text/css" />

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header id="viewHeader" class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-2 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>
                            <button type="button" class="btn btn-sm btn-default" id="action_delete" title="批量删除(扔进回收站)"><i class="fa fa-trash-o"></i></button>
                            <button type="button" class="btn btn-sm btn-default" data-toggle="dropdown" title="批量操作"><i class="fa fa-filter"></i> <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:;">将商品上架</a></li>
                                <li><a href="javascript:;">将商品下架</a></li>
                                <li class="divider"></li>
                                <li><a href="javascript:;">统一调价</a></li>
                                <li><a href="javascript:;">分别调价</a></li>
                                <li><a href="javascript:;">统一调库存</a></li>
                                <li><a href="javascript:;">分别调库存</a></li>
                                <li><a href="javascript:;">统一调积分</a></li>
                                <li><a href="javascript:;">分别调积分</a></li>
                                <li class="divider"></li>
                                <li><a href="javascript:;">统一调整分类</a></li>
                                <li><a href="javascript:;">分别调整分类</a></li>
                                <li><a href="javascript:;">统一调整品牌</a></li>
                                <li><a href="javascript:;">分别调整品牌</a></li>
                                <li><a href="javascript:;">统一调整序号</a></li>
                                <li><a href="javascript:;">分别调整序号</a></li>
                            </ul>
                        </div>
                        <a href="${BASE_URL}/back/goodsContent/add" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i> 添加</a>
                    </div>

					<form class="form-inline" action="${BASE_URL}/back/goodsContent/page" id="searchForm">                    
                    <div class="col-sm-7 m-b-xs text-right p-right-0">
		                    <div class="form-group">
                                <select name="categoryId" class="form-control input-sm">
                                    <option value="0">请选择分类</option>
                                    <c:forEach items="${categories}" var="v">
                                   	 	<option value="${v.categoryId}">
                                            <c:if test="${v.grade == 1}">${v.categoryName}</c:if>
                                            <c:if test="${v.grade == 2}">&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                                            <c:if test="${v.grade == 3}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                                   	 	</option>
									</c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <select name="brandId" class="form-control input-sm">
                                    <option value="0">请选择品牌</option>
                                    <c:forEach items="${brands}" var="v">
                                    	<option value="${v.brandId}">${v.brandName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <select name="isShelf" class="form-control input-sm">
                                    <option value="-1">上架状态</option>
                                    <option value="1">已上架</option>
                                    <option value="0">未上架</option>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" value="${priceMin}" size="10" class="input-sm input-s form-control"
                                        name="priceMin" placeholder="最低销售价" /> - 
                            </div>
                            
                            <div class="form-group">
                                <input type="text" value="${priceMax}" size="10" class="input-sm input-s form-control"
                                        name="priceMax" placeholder="最高销售价" />
                            </div>
		            </div>
		            
                    <div class="col-sm-3 m-b-xs">
                        <div class="input-group">
                            <input type="text" name="key" class="input-sm form-control" placeholder="商品名称" />
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                            </span>
                        </div>
                    </div>
	                </form>
                </div>
            </header>
                
            <section id="viewBody" class="scrollable wrapper">
                <section class="panel panel-default">
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
                                                <button type="button" class="btn btn-sm btn-default grid-nextpage"><i
                                                        class="fa fa-chevron-right"></i></button>
                                            </div>
                                        </div>
                                    </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </section>
            </section>
        </section>
    </aside>
</section>
            
<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/jquery-ztree/3.5.15/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${STATIC_URL}/back/modules/goods/js/content.index.js"></script>