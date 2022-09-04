<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${board !=null}">编辑广告位</c:when><c:otherwise>添加广告位</c:otherwise></c:choose></h4>
        </div>
        <div class="modal-body"><%-- <c:choose><c:when test="${board}">edit</c:when><c:otherwise>add</c:otherwise></c:choose> --%>
            <form class="form-horizontal" action="${BASE_URL}/back/advertBoard/add" method="post" id="edit_form">
                <div class="form-group">
                    <label class="col-sm-3 control-label"><font class="red">* </font>所属页面</label>
                    <div class="col-sm-7">
                    	<select name="pageId" class="form-control">
							<option value="0" >请选择</option>
							<c:forEach items="${pages}" var="v">
							<!-- {foreach from=$ key=k item=v} -->
							<option value="${v.pageId}" <c:if test="${board.pageId==v.pageId}">selected</c:if>>${v.title}</option>
							</c:forEach>
						</select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label"><font class="red">* </font>广告类型</label>
                    <div class="col-sm-7">
                    	<select name="type" class="form-control">
							<option value="0" <c:if test="${board.type == 0}">selected</c:if>>请选择</option>
							<option value="1" <c:if test="${board.type == 1}">selected</c:if>>图片</option>
							<option value="2" <c:if test="${board.type == 2}">selected</c:if>>文字</option>
							<option value="3" <c:if test="${board.type == 3}">selected</c:if>>flash</option>
							<option value="4" <c:if test="${board.type == 4}">selected</c:if>>视频</option>
							<option value="5" <c:if test="${board.type == 5}">selected</c:if>>轮播</option>
						</select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label"><font class="red">* </font>广告位名称</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="name" name="name" value="${board.name}" placeholder="请输入广告位名称" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="memo" class="col-sm-3 control-label">广告位描述</label>
                    <div class="col-sm-7">
                        <textarea id="memo" name="memo" class="form-control">${board.memo}</textarea>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="width" class="col-sm-3 control-label">广告位宽度</label>
                    <div class="col-sm-7">
                        <div class="input-group">
                            <input type="text" class="form-control" id="width" name="width" value="${board.width}" placeholder="单位：像素" />
                            <span class="input-group-addon">px</span>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="height" class="col-sm-3 control-label">广告位高度</label>
                    <div class="col-sm-7">
                        <div class="input-group">
	                        <input type="text" class="form-control" id="height" name="height" value="${board.height}" placeholder="单位：像素" />
                            <span class="input-group-addon">px</span>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="sortOrder" class="col-sm-3 control-label">序号</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="<c:choose><c:when test="${board.sortOrder != null}">${board.sortOrder}</c:when><c:otherwise>0</c:otherwise></c:choose>" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">状态</label>
                    <div class="col-sm-7">
	                    <label class="checkbox-inline p-left-0"><input type="radio" <c:if test="${board.status == 1}">checked</c:if> name="status" value="1" /> 启用</label>
                    	<label class="checkbox-inline p-left-0"><input type="radio" <c:if test="${board.status == 0}">checked</c:if> name="status" value="0" /> 关闭</label>
                    </div>
                </div>
                
                <input type="hidden" name="boardId" value="${board.boardId}" />
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
<%--             <c:if test="${board !=null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if> --%>
            <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
        </div>
    </div>
</div>
<script src="${STATIC_URL}/back/modules/advert/js/board.edit.js"></script>
<!-- / modal - 编辑-->