<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-1 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="Refresh"><i class="fa fa-refresh"></i></button>
                        </div>
                    </div>
                    
	                <form class="form-inline" action="${BASE_URL}/back/casuser/page" id="searchForm">
                    <div class="col-sm-8 m-b-xs text-right">
		                    <div class="form-group">
		                        <select id="userLvId" name="userLvId" class="form-control input-sm">
		                            <option value="0">用户等级</option>
		                           <c:forEach items="${lvList}" var="v">
		                            <option value="${v.userLvId}">${v.name}</option>
		                            </c:forEach>
		                        </select>
		                    </div>
		            </div>
		            
                    <div class="col-sm-3 m-b-xs pull-right">
                        <div class="input-group">
                            <input type="text" name="key" class="input-sm form-control" placeholder="用户名/真实姓名/手机号码/email" />
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

<!-- modal - 修改密码 -->
<div class="modal fade" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modal_title">修改会员密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_form" action="/casuser/resetPassword" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名</label>
                        <div class="col-sm-6">
                            <p class="form-control-static username"></p>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">输入新密码</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" name="password" id="password" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="repassword" class="col-sm-3 control-label">确认新密码</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" name="repassword" id="repassword" />
                        </div>
                    </div>
                    <input type="hidden" name="userid" value="" />
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary input-submit" id="submit_save_back">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- / modal - 修改密码 -->
            
<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/cas/js/casUser.index.js" type="text/javascript"></script>