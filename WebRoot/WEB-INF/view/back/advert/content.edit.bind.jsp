<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<section class="edit-map wrapper default-hidden" id="edit_bind">
    
    <div class="form-group">
        <label class="col-sm-2 control-label">资源类型</label>
        <div class="col-sm-4">
            <select id="bindType" name="bindType" class="form-control">
                <c:forEach items="${bindTypes}" var="bind">
                    <option value="${bind.key}" <c:if test="${content.bindType == bind.key}">selected="selected"</c:if>>${bind.value}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div id="bind_type_1" class='bind-type-edit <c:if test="${!(content == null || content.bindType == 1)}">default-hidden</c:if>'>
        <div class="form-group">
            <label for="bind_source_1" class="col-sm-2 control-label">链接地址</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="bind_source_1" name="bindSource" value='<c:if test="${content.bindType == 1}">${content.bindSource}</c:if>' placeholder="请输入链接地址" />
            </div>
        </div>
    </div>
    
    <div id="bind_type_2" class='bind-type-edit <c:if test="${content.bindType != 2}">default-hidden</c:if>'>
        <div class="form-group">
            <label class="col-sm-2 control-label">绑定商品</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="bind_source_view_2" value="" disabled="disabled" />
                <input type="hidden" id="bind_source_2" name="bindSource" value='<c:if test="${content.bindType == 2}">${content.bindSource}</c:if>' />
            </div>
            <div class="col-sm-6">
                <span class="help-block m-b-none">请在下列列表中选择需要绑定的商品</span>
            </div>
        </div>
        <div class="line line-dashed line pull-in"></div>
    </div>
    
    <div id="bind_type_3" class='bind-type-edit <c:if test="${content.bindType != 3}">default-hidden</c:if>'>
        <div class="form-group">
            <label class="col-sm-2 control-label">绑定文章</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="bind_source_view_3" value="" disabled="disabled" />
                <input type="hidden" id="bind_source_3" name="bindSource" value='<c:if test="${content.bindType == 3}">${content.bindSource}</c:if>' />
            </div>
            <div class="col-sm-6">
                <span class="help-block m-b-none">请在下列列表中选择需要绑定的文章</span>
            </div>
        </div>
        <div class="line line-dashed line pull-in"></div>
    </div>
    
    <div id="bind_type_4" class='bind-type-edit <c:if test="${content.bindType != 4}">default-hidden</c:if>'>
        <div class="form-group">
            <label for="bind_source_4" class="col-sm-2 control-label">自定义标签</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="bind_source_4" name="bindSource" value='<c:if test="${content.bindType == 4}">${content.bindSource}</c:if>' placeholder="请输入自定义标签" />
                <span class="help-block m-b-none">该标签需要和前端人员协商填入</span>
            </div>
        </div>
    </div>
    
    <div id="bind_source_listing_2" class='bind-source-edit <c:if test="${content.bindType != 2}">default-hidden</c:if>'>
        <div class="form-group">
            <div class="col-sm-12">
                <section class="panel panel-default">
                    <header class="panel-heading">
                        选择商品
                        <a id="btn_add_property_value" class="btn btn-xs btn-default pull-right hide">
                            <i class="fa fa-plus text"></i>
                            <span class="text">添加</span>
                        </a>
                        
                        <div class="col-sm-4 m-t-n-xs pull-right" id="search2">
                            <div class="input-group">
                                <input type="text" name="keyGoods" class="input-sm form-control" placeholder="Search" />
                                <span class="input-group-btn">
                                    <button class="btn btn-sm btn-default" id="action_search_goods" type="button">搜索</button>
                                </span>
                            </div>
                        </div>
                    </header>
                    <table class="table table-striped m-b-none datagrid" id="content_source_2">
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
    </div>
    
    <div id="bind_source_listing_3" class='bind-source-edit <c:if test="${content.bindType != 3}">default-hidden</c:if>'>
        <div class="form-group">
            <div class="col-sm-12">
                <section class="panel panel-default">
                    <header class="panel-heading">
                        选择文章
                        <a id="btn_add_property_value" class="btn btn-xs btn-default pull-right hide">
                            <i class="fa fa-plus text"></i>
                            <span class="text">添加</span>
                        </a>
                        
                        <div class="col-sm-4 m-t-n-xs pull-right" id="search2">
                            <div class="input-group">
                                <input type="text" name="keyArticle" class="input-sm form-control" placeholder="Search" />
                                <span class="input-group-btn">
                                    <button class="btn btn-sm btn-default" id="action_search_article" type="button">搜索</button>
                                </span>
                            </div>
                        </div>
                    </header>
                    <table class="table table-striped m-b-none datagrid" id="content_source_3">
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
    </div>
                    
</section>
