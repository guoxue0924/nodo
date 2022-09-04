<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>
<link href="${STATIC_URL}/plugins/codemirror/4.3/lib/codemirror.css" rel="stylesheet" type="text/css" />
<link href="${STATIC_URL}/plugins/codemirror/4.3/addon/display/fullscreen.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <p class="h4">单页设置</p>
            </header>
            
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="editForm" action="${BASE_URL}/back/pageConfig/save" method="post">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">公共头部</label>
                        <div class="col-sm-10">
                            <section class="panel panel-default">
                                <header class="panel-heading text-right bg-light">
                                    <ul class="nav nav-tabs pull-left">
                                        <li class="active hide"><a data-toggle="tab" href="#edit_online"><i class="fa fa-edit text-default"></i> 在线编辑</a></li>
                                    </ul>
                                    <ul class="nav nav-tabs pull-right">
                                        <li class="dropdown">
                                            <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-cog text-default"></i> themes <b class="caret"></b></a>
                                            <ul class="dropdown-menu text-left">
                                                <li><a href="javascript:;">暂未开放</a></li>
                                                <li><a href="javascript:;">敬请期待</a></li>
                                                <li><a href="javascript:;">default</a></li>
                                                <li><a href="javascript:;">night</a></li>
                                                <li><a href="javascript:;">neat</a></li>
                                                <li><a href="javascript:;">elegant</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                    <span class="hidden-sm">&nbsp;</span>
                                </header>
                                <div>
                                    <div class="tab-content">              
                                        <div id="edit_online" class="tab-pane fade active in">
<%--                                             <textarea name="public_header" id="textarea_content_header" class="hide">${pageConfig[0].value}</textarea> --%>
                                            <textarea name="configValues" id="content_code_header" class="hide">${pageConfig[0].value}</textarea>
                                            <input type="hidden" id="configId_0" value="${pageConfig[0].id}">
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">公共尾部</label>
                        <div class="col-sm-10">
                            <section class="panel panel-default">
                                <header class="panel-heading text-right bg-light">
                                    <ul class="nav nav-tabs pull-left">
                                        <li class="active hide"><a data-toggle="tab" href="#edit_online"><i class="fa fa-edit text-default"></i> 在线编辑</a></li>
                                    </ul>
                                    <ul class="nav nav-tabs pull-right">
                                        <li class="dropdown">
                                            <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-cog text-default"></i> themes <b class="caret"></b></a>
                                            <ul class="dropdown-menu text-left">
                                                <li><a href="javascript:;">暂未开放</a></li>
                                                <li><a href="javascript:;">敬请期待</a></li>
                                                <li><a href="javascript:;">default</a></li>
                                                <li><a href="javascript:;">night</a></li>
                                                <li><a href="javascript:;">neat</a></li>
                                                <li><a href="javascript:;">elegant</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                    <span class="hidden-sm">&nbsp;</span>
                                </header>
                                <div>
                                    <div class="tab-content">              
                                        <div id="edit_online" class="tab-pane fade active in">
<%--                                             <textarea name="public_footer" id="textarea_content_footer" class="hide">${pageConfig[1].value}</textarea> --%>
                                            <textarea name="configValues" id="content_code_footer" class="hide">${pageConfig[1].value}</textarea>
                                            <input type="hidden" id="configId_1" value="${pageConfig[1].id}">
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <input type="hidden" name="page_id" id="page_id" value="{$page_id}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/codemirror/4.3/lib/codemirror.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/mode/javascript/javascript.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/mode/xml/xml.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/addon/selection/active-line.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/addon/edit/matchbrackets.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/addon/display/fullscreen.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/js/codemirror.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/page/js/config.index.js" type="text/javascript"></script>