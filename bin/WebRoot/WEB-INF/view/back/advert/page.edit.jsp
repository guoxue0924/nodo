<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${page != null}">编辑广告页</c:when><c:otherwise>添加广告页</c:otherwise></c:choose></h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" action="${BASE_URL}/back/advertPage/add" method="post" id="edit_form">
                <div class="form-group">
                    <label for="title" class="col-sm-3 control-label"><font class="red">* </font>页面标题</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="title" name="title" value="${page.title}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入页面标题" />
                    </div>
                </div>
                
                <input type="hidden" name="pageId" value="${page.pageId}" />
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
<%--             <c:if test="${page == null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if> --%>
            <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
        </div>
    </div>
</div>
<script src="${STATIC_URL}/back/modules/advert/js/page.edit.js"></script>
<!-- / modal - 编辑-->