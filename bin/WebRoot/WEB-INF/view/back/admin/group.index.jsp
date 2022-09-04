<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="Refresh"><i class="fa fa-refresh"></i></button>
                        </div>
                        <a href="${BASE_URL}/back/adminUserGroup/add" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i> 添加</a>
                    </div>
                </div>
            </header>
            <form class="form-inline" action="${BASE_URL}/back/adminUserGroup/page" id="searchForm"></form>
            <section class="scrollable wrapper">
                <section class="panel panel-default">
                    <div class="table-responsive">
                        <table class="table table-striped m-b-sm datagrid" id="group_listing">
                            <thead>
                            </thead>
                        </table>
                    </div>
                </section>
            </section>
        </section>
    </aside>
</section>
            
<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/admin/js/group.index.js" type="text/javascript"></script>