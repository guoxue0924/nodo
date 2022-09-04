<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
    	<div class="modal-header">
	    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${group !=null}">编辑分组</c:when><c:otherwise>添加分组</c:otherwise></c:choose></h4>
    	</div>
	    <div class="modal-body">
	    	<form class="form-horizontal" id="edit_form" 
	    		action=	<c:choose> <c:when test="${group!=null}">"${BASE_URL}/back/pageGroup/edit"</c:when><c:otherwise>"${BASE_URL}/back/pageGroup/add"</c:otherwise></c:choose>
				method="post">
				
				<div class="form-group">
                    <label for="group_name" class="col-sm-3 control-label"><font class="red">* </font>分组名称</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="groupName" name="groupName" value="${group.groupName}" placeholder="请输入分组名称" />
                    </div>
                </div>
				
		        <div class="form-group">
                    <label for="folder" class="col-sm-3 control-label"><c:if test="${group ==null}"><font class="red">* </font></c:if>分组文件名</label>
                    <div class="col-sm-6">
                        <c:choose>
                        <c:when test="${group ==null}">
                        <input type="text" class="form-control" id="folder" name="folder" value="${group.folder}" placeholder="请输入分组文件名" />
                        <p class="form-control-static">必须是字母，且首字母大写。多个单词，则保持驼峰式。比如：ShoppingGuide</p>
                        </c:when>
                        <c:otherwise>
                        <p class="form-control-static">${group.folder}</p>
                        <input type="hidden" id="folder"  name="folder" value="${group.folder}" />
                        </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="moveable" class="col-sm-3 control-label">是否允许被删除</label>
                    <div class="col-sm-6">
                        <label class="switch" >
                        <input type="checkbox" value="1" id="moveable" name="moveable" <c:if test="${group.moveable == 1}">checked=checked</c:if>/> 
                        <span></span>
                        </label>
                    </div>
                </div>
				
				<input type="hidden" id="groupId"  name="groupId" value="${group.groupId}" />
			</form>
	    </div>
	    <div class="modal-footer">
	        <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
	        <button type="button" data-dismiss="modal" class="btn btn-danger btn-sm input-submit">取消</button>
	    </div>
    </div>
</div>

<script src="${STATIC_URL}/back/modules/page/js/group.edit.js" type="text/javascript"></script>