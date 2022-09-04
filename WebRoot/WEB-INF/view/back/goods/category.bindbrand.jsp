<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <p class="h4 col-sm-2">绑定品牌</p>
                    <div class="col-sm-6 m-b-xs pull-right">
                        <div class="input-group">
                        	<form action="${BASE_URL}/back/goodsBrand/page" id="searchForm">
                            	<input type="text" name="key" class="input-sm form-control" placeholder="品牌名称" />
                            </form>
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                            </span>
                        </div>
                    </div>
                </div>
            </header>
            
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/goodsCategory/bindBrand" method="post">
                    <div class="form-group">
                        <label for="label_name" class="col-sm-2 control-label">分类名称</label>
                        <div class="col-sm-4">
                            <p class="form-control-static">${category.categoryName}</p>
                        </div>
                    </div>
                    
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <section class="panel panel-default">
                        <table class="table table-striped m-b-none datagrid" id="brand_listing">
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
                    <input type="hidden" name="categoryId" value="${category.categoryId}" />
                    <input type="hidden" name="brandIds" value="${brandIds}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
    
    <aside class="bg-light lter aside-md" id="selected_brand">
        <section class="vbox">
            <header class="b-b header"><p class="h4">已选<font class="text-sm">（可拖拽排序）</font></p></header>
            <section class="scrollable w-f">
                <ul class="nav nav-stacked list-group gutter list-group-lg list-group-sp sortable">
                <c:forEach items="${brandsBinded}" var="v">
                    <li class="b-b m-t-none-reset" id="li_brand_${v.brandId}" brand_id="${v.brandId}" draggable="true">
                        <a href="javascript:;">
                            <i title="移除该品牌" class="fa fa-times pull-right m-t-xs fa-remove-brand"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i><font class="brand-name">${v.brandName}</font>
                        </a>
                    </li>
                   </c:forEach>
                </ul>
            </section>
        </section>
    </aside>
</section>

<!-- modal - 绑定品牌 -->
<div class="modal-dialog modal-lg hide">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title">绑定品牌</h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" action="goodsCategory/bindBrand" method="post" id="edit_form">
                <div class="form-group padder">
                    <c:forEach items="${brands}" var="v">
                    <label class="col-sm-3">
                        <input type="checkbox" class="brand-single" name="brand" value="${v.brandId}" checked="checked">&nbsp;${v.brandName}
                    </label>
                    </c:forEach>
                </div>
                
                <input type="hidden" name="categoryId" value="${category.categoryId}" />
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
            <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
            <span id="edit_notice"></span>
        </div>
    </div>
</div>
<!-- / modal - 绑定品牌 -->

<jsp:include page="../wrapper.suffix.jsp"/>

<script src="${STATIC_URL}/plugins/sortable/jquery.sortable.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/goods/js/category.bindbrand.js"></script>