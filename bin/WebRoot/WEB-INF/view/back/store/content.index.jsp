<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-2 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>                           
                        </div>
                        <a href="${BASE_URL}/back/storeContent/add" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i> 添加</a>
                    </div>
                    <form class="form-inline" action="${BASE_URL}/back/storeContent/page" id="searchForm">
                    <div class="col-sm-7 m-b-xs text-right">
                            <div class="form-group">
                                <select name="isVerify" id="isVerify" class="form-control input-sm">
                                    <option value="-1">审核状态</option>                                  
                                    <option value="1">已审核</option>   
                                    <option value="0">未审核</option>                                 
                                </select>
                            </div>
                            <div class="form-group">
                                <select name="status" id="status" class="form-control input-sm">
                                    <option value="-1">启用状态</option> 
                                    <option value="1">已启用</option>   
                                    <option value="0">未启用</option>   
                                </select>
                            </div>
                    </div>
                    <div class="col-sm-3 m-b-xs">
                        <div class="input-group">
                            <input type="text" name="key" class="input-sm form-control" placeholder="公司名称/店铺名称/法人姓名/联系电话" />
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                            </span>
                        </div>
                    </div>
                    </form>
                </div>
            </header>
                
            <section class="scrollable wrapper">
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
<script src="${STATIC_URL}/back/modules/store/js/content.index.js"></script>