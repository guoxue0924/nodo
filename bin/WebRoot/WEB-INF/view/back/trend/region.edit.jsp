<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑地区 -->
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${region != null}">编辑地区</c:when><c:otherwise>添加地区</c:otherwise></c:choose></h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" action="${BASE_URL}/back/trend/region/add" method="post" id="edit_form">
                <div class="form-group">
                    <label for="region_name" class="col-sm-3 control-label"><font class="red">* </font>地区名</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="regionName" name="regionName" value="${region.regionName}" placeholder="请输入分类名称" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="sort_order" class="col-sm-3 control-label">排序号</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="<c:choose><c:when test="${region.sortOrder != null}">${region.sortOrder}</c:when><c:otherwise>255</c:otherwise></c:choose>" />
                    </div>
                </div>
                
                <input type="hidden" name="regionId" value="${region.regionId}" />
                <input type="hidden" name="pid" value="${pid}" />
                <input type="hidden" name="hid" value="${trendRegion.hid}" />
            </form>
        </div>
        <div class="modal-footer">
            <input type="button" class="btn btn-default btn-primary input-submit" id="submit_save_back" value="保存并返回列表管理" />
            <c:if test="${region != null}"><input type="button" class="btn btn-default btn-primary input-submit" id="submit_save_continue" value="保存并继续添加" /></c:if>
            <input type="button" class="btn btn-default btn-danger" value="取&nbsp;&nbsp;消" data-dismiss="modal" />
        </div>
    </div>
</div>
<!-- / modal - 编辑地区 -->
<script src="${STATIC_URL}/back/modules/trend/js/region.edit.js"></script>