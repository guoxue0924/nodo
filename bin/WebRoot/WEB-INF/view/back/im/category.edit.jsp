<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
    	<div class="modal-header">
	    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${category !=null}">编辑分类</c:when><c:otherwise>添加分类</c:otherwise></c:choose></h4>
    	</div>
	    <div class="modal-body">
	    	<form class="form-horizontal" id="edit_form" 
	    		action=	<c:choose> <c:when test="${category!=null}">"${BASE_URL}/back/imCategory/edit"</c:when><c:otherwise>"${BASE_URL}/back/imCategory/add"</c:otherwise></c:choose>
				method="post">
				
				<div class="form-group">
			        <label for="title" class="col-sm-3 control-label"><font class="red">* </font>分类名称</label>
			        <div class="col-sm-7">
			            <input type="text" class="form-control" id="categoryName" name="categoryName" value="${category.categoryName}" placeholder="请输入分类名称" maxlength="20" />
		            </div>
		        </div>
					
				<div class="form-group">
	                <label for="sortOrder" class="col-sm-3 control-label">序号</label>
	                <div class="col-sm-7">
	                    <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${category.sortOrder}" placeholder="请输入排序号" maxlength="20" />
				    </div>
				</div>
				
				<div class="form-group">
	                <label for="sortOrder" class="col-sm-3 control-label">描述</label>
	                <div class="col-sm-6">
                        <textarea rows="2" class="form-control" id="description" name="description" placeholder="您可以在这里对该分类进行简要描述">${category.description}</textarea>
                    </div>
				</div>
				
				<input type="hidden" id="categoryId"  name="categoryId" value="${category.categoryId}" />
			</form>
	    </div>
	    <div class="modal-footer">
	        <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
	        <button type="button" data-dismiss="modal" class="btn btn-danger btn-sm input-submit">取消</button>
	    </div>
    </div>
</div>

<script src="${STATIC_URL}/back/modules/im/js/category.edit.js" type="text/javascript"></script>