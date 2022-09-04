<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r" id="subNav">
        <section class="vbox">
            <header class="b-b header"><p class="h4"><c:choose><c:when test="${store != null}">编辑商户</c:when><c:otherwise>添加商户</c:otherwise></c:choose></p></header>
            <section class="scrollable w-f">
                <ul class="nav nav-pills nav-stacked no-radius">
                    <li class="b-b m-t-none-reset nav-map active" id="nav_base">
                        <a href="javascript:;">
                            <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i>
                            基本信息
                        </a>
                    </li>
                    <li class="b-b m-t-none-reset nav-map" id="nav_account">
                        <a href="javascript:;">
                            <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i>
                            账户信息
                        </a>
                    </li>
                    <li class="b-b m-t-none-reset nav-map" id="nav_setting">
                        <a href="javascript:;">
                            <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i>
                            店铺设置
                        </a>
                    </li>
                    <li class="b-b m-t-none-reset nav-map hide" id="nav_crm">
                        <a href="javascript:;">
                            <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i>
                            CRM 跟进记录
                        </a>
                    </li>
                </ul>
            </section>
        </section>
    </aside>
    
    <aside class="bg-white">
        <section class="vbox">
            <header class="header b-b b-t bg-white-only">
                <div class="m-t-sm">
                    <a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0">
                        <i class="fa fa-caret-right text fa-lg"></i>
                        <i class="fa fa-caret-left text-active fa-lg"></i>
                    </a>
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${store != null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                </div>
            </header>
            
            <section class="scrollable w-f">
                <form class="form-horizontal" id="editForm" action="${BASE_URL}/back/storeContent/add" method="post" enctype="multipart/form-data">	                		
	                
	                <!-- 基本信息 @start -->
	                <jsp:include page="content.edit.base.jsp"/>
                    <!-- 基本信息 @end -->
	                
	                <!-- 账户信息 @start -->
	                <jsp:include page="content.edit.account.jsp"/>
                    <!-- 账户信息 @end -->
	                
	                <!-- 店铺设置 @start -->
	                <jsp:include page="content.edit.setting.jsp"/>
                    <!-- 店铺设置 @end -->
	                
	                <!-- CRM 跟进记录 @start -->
	                <jsp:include page="content.edit.crm.jsp"/>
                    <!-- CRM 跟进记录 @end -->
                    
                    <input type="hidden" name="storeId" id="storeId" value="${store.storeId}" />
                    <input type="hidden" name="source" id="source" value="local" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="1">
                        <i class="fa fa-caret-right text fa-lg"></i>
                        <i class="fa fa-caret-left text-active fa-lg"></i>
                    </a>
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${store == null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="editNotice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>


<jsp:include page="../wrapper.suffix.jsp"/>

<script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>
<script src="${STATIC_URL}/back/modules/cas/js/casvalidator.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/store/js/content.edit.js" type="text/javascript"></script>