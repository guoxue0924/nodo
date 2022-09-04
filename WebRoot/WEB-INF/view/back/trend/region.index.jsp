<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="vbox">
    <header class="header bg-white b-b clearfix">
        <div class="row m-t-sm">
            <div class="col-sm-8 m-b-xs">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-default action-refresh" title="Refresh"><i class="fa fa-refresh"></i></button>
                </div>
<!--                 <a href="${BASE_URL}/back/trend/region/add?pid=0" data-toggle="ajaxModal" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 添加国家</a> -->
            </div>
        </div>
    </header>
        
    <section class="scrollable wrapper">
        <section class="panel panel-default">
            <div class="table-responsive">
                <table class="table table-striped b-t b-light" id="content_listing">
	                <thead>
						<th class="col-sm-9">地区名称</th>
	                    <th class="col-sm-1">排序</th>
	                    <th class="col-sm-2">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${regions}" var="v">
						<tr id="tr_region_${v.regionId}">
						    <td>
						        <a href="javascript:;" class="region-extend" regionId="${v.regionId}" parentId="${v.pid}">
						            <i class="fa fa-plus-square-o"></i> ${v.regionName}
						        </a>
						    </td>
						    <td>${v.sortOrder}</td>
						    <td>
						    	<a href="${BASE_URL}/back/trend/region/add?pid=${v.regionId}" data-toggle="ajaxModal" class="operate-edit">添加子地区</a> | 
		                    	<a href="${BASE_URL}/back/trend/region/update?regionId=${v.regionId}" data-toggle="ajaxModal" class="operate-edit">编辑</a> | 
		                    	<a href="javascript:;" class="operate-delete" regionId="${v.regionId}">删除</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
                </table>
            </div>
        </section>
    </section>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/trend/js/region.index.js"></script>