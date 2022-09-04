<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>

            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <form class="form-inline" action="${BASE_URL}/back/order/pageByuserId" id="searchForm" method="post">
                        <div class="col-sm-7 m-b-xs text-right">
                            <div class="form-group">
                                <input type="text" value="" class="input-sm input-s form-control" name="startDate" id="startDate" placeholder="下单时间" readonly="readonly"> - 
                                <input type="text" value="" class="input-sm input-s form-control" name="endDate" id="endDate" placeholder="下单时间" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <select id="orderStatus" name="orderStatus" class="form-control input-sm">
                                    <option value="" selected>订单状态</option>
                                    <c:forEach items="${orderStatus}" var="entry">
                                        <option value="${entry.key}" id="orderStatus_${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <select id="paymentStatus" name="paymentStatus" class="form-control input-sm">
                                    <option value="" selected>支付状态</option>
                                    <c:forEach items="${paymentStatus}" var="entry">
                                        <option value="${entry.key}" id="paymentStatus_${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <select id="orderType" name="orderType" class="form-control input-sm">
                                    <option value="" selected>订单类型</option>
                                    <c:forEach items="${orderType}" var="entry">
                                        <option value="${entry.key}" id="orderType_${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <select id="payType" name="payType" class="hidden">
                                    <c:forEach items="${payType}" var="entry">
                                        <option value="${entry.key}" id="payType_${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                        </div>
                        <div class="col-sm-3 m-b-xs pull-right">
                            <div class="input-group">
                                <input type="text" name="key" class="input-sm form-control" placeholder="请输入订单号" />
                                <span class="input-group-btn">
                                    <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                                </span>
                            </div>
                        </div>
                        
                        <input type="hidden" name="userid" value="${userid}"/>
                        
                    </form>
                </div>
            </header>
                
            <section class="scrollable wrapper customer">
                <section class="panel panel-default">
                    <div class="table-responsive">
                        <table class="table table-striped m-b-sm datagrid" id="content_listing_order">
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
            
<!-- Bootstrap -->
<script src="${STATIC_URL}/plugins/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<!-- bootbox -->
<script type="text/javascript" src="${STATIC_URL}/plugins/bootbox/bootbox.js"></script>

<script src="${STATIC_URL}/back/js/utils.js"></script>
<script src="${STATIC_URL}/back/js/common.js"></script>

<script src="${STATIC_URL}/back/modules/cas/js/casUser.detail.order.js" type="text/javascript"></script>
<!-- order -->
<script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
