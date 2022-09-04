<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>
<link href="${STATIC_URL}/back/modules/coupon/css/listing.index.css" rel="stylesheet" type="text/css"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-2 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="Refresh"><i class="fa fa-refresh"></i></button>
                        </div>
                    </div>
                    <form class="form-inline" action="${BASE_URL}/back/couponListing/page" id="searchForm">
                    <div class="col-sm-10 m-b-xs text-right p-right">
                            <div class="form-group">
                                <select id="type" name="type" class="form-control input-sm">
                                    <option value="">请选择优惠券类型</option>
                                    <option value="0">全场</option>
                                    <option value="1">满减</option>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <select id="status" name="status" class="form-control input-sm">
                                    <option value="">请选择优惠券状态</option>
                                    <option value="0">未启用</option>
                                    <option value="1">启用</option>
                                    <option value="2">锁定</option>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" value="" size="10" class="input-sm input-s form-control" name="createStartDate" id="createStartDate" placeholder="请选择创建开始时间" readonly="readonly"> - 
                                <input type="text" value="" size="10" class="input-sm input-s form-control" name="createEndDate" id="createEndDate" placeholder="请选择创建结束时间" readonly="readonly">
                            </div>
                            
                            <div class="form-group">
                                <input type="text" value="" size="10" class="input-sm input-s form-control" name="modifyStartDate" id="modifyStartDate" placeholder="请选择修改开始时间" readonly="readonly"> - 
                                <input type="text" value="" size="10" class="input-sm input-s form-control" name="modifyEndDate" id="modifyEndDate" placeholder="请选择修改结束时间" readonly="readonly">
                            </div>
                    </div>
                    
                    <div class="col-sm-3 m-b-xs pull-right">
                        <div class="input-group">
                            <input type="text" name="key" class="input-sm form-control" placeholder="优惠券名称/用户名"/>
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                                <button class="btn btn-sm btn-default action-reset" type="button">重置</button>
                            </span>
                        </div>
                    </div>
                    </form>
                </div>
            </header>
                
            <section class="scrollable wrapper customer">
                <section class="panel panel-default">
                    <div class="table-responsive">
                        <table class="table table-striped m-b-sm datagrid" id="content_listing">
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
<script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/back/modules/coupon/js/listing.index.js" type="text/javascript"></script>