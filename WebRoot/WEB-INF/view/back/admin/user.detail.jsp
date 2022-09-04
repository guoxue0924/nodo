<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r" id="subNav">
        <section class="vbox">
            <header class="b-b header"><p class="h4">平台角色详情</p></header>
            <section class="scrollable w-f">
                <ul class="nav nav-pills nav-stacked no-radius">
                    <li class="b-b m-t-none-reset nav-map active" id="nav_base">
                        <a href="javascript:;">
                            <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i>
                            基本信息
                        </a>
                    </li>
                </ul>
            </section>
        </section>
    </aside>
    
    <aside class="bg-white">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="m-t-sm">
                    <a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0">
                        <i class="fa fa-caret-right text fa-lg"></i>
                        <i class="fa fa-caret-left text-active fa-lg"></i>
                    </a>
                        <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                </div>
            </header>
              
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">平台角色详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                            <tr>                    
                                <td class="col-sm-2">名称:</td>
                                <td class="col-sm-4">${user.username}</td>
                                <td class="col-sm-2">昵称:</td>
                                <td class="col-sm-4">${user.nickname}</td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">全称:</td>
                                <td class="col-sm-4">${user.fullname}</td>
                                <td class="col-sm-2">性别:</td>
                                <td class="col-sm-4"><c:if test="${user.gender == 1}">男</c:if><c:if test="${user.gender == 2}">女</c:if></td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">电子邮箱：</td>
                                <td class="col-sm-4">${user.email}</td>
                                <td class="col-sm-2">身份证:</td>
                                <td class="col-sm-4">${user.idcard}</td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">创建时间:</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${user.ctime}"  type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
                                </td>
                                <td class="col-sm-2">最后修改时间:</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${user.mtime}"  type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
                                </td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">用户状态:</td>
                                <td class="col-sm-4"><c:choose><c:when test="${user.status==1}">已激活</c:when><c:otherwise>未激活</c:otherwise></c:choose></td>
                                <td class="col-sm-2"></td>
                                <td class="col-sm-4"></td>
                            </tr>
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/admin/js/user.detail.js" type="text/javascript"></script>