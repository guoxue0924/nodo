<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-6 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="Refresh"><i class="fa fa-refresh"></i></button>
                        </div>
                    </div>
                    <form action="${BASE_URL}/back/feedbackContent/page" id="searchForm">
                    <div class="col-sm-2 m-b-xs">
                    	<select id="hasName" class="form-control m-b-xs input-sm" name="hasName">
                       		<option value="0">所有用户</option>
                       		<option value="1">匿名用户</option>
                       		<option value="2">非匿名用户</option>
                       	</select>
                    </div>
                    <div class="col-sm-4 m-b-xs">
                        <div class="input-group">
                            <input type="text" name="key" class="input-sm form-control" placeholder="请输入反馈内容" />
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                            </span>
                        </div>
                    </div>
                    </form>
                </div>
            </header>
                
            <jsp:include page="../datagrid.jsp"/>
        </section>
    </aside>
</section>
 
<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/feedback/js/content.index.js" type="text/javascript"></script>