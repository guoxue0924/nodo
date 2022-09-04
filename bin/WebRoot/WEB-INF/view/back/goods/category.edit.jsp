<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑分类 -->
<div class="modal-dialog" id="modal_content">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title">
            <c:choose>
            	<c:when test="${category!=null}">编辑分类</c:when>
            	<c:otherwise>添加分类</c:otherwise>
            </c:choose>
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" action="${BASE_URL}/back/goodsCategory/add" method="post" id="edit_form" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-3 control-label">父级分类</label>
                    <div class="col-sm-6">
	                    <select name="parentId" id="parentId" class="form-control">
                            <option value="0">
                            	<c:choose>
                            		<c:when test="${category!=null && category.parentName!=null}">${category.parentName}[ID:${category.parentId}]</c:when>
                            		<c:otherwise>Root Category [ID:0]</c:otherwise>
                            	</c:choose>
                            </option>
				                <c:forEach items="${categories}" var="v">
				                    <c:if test="${v.grade == 1}">${v.categoryName}</c:if>
				                    <c:if test="${v.grade == 2}">&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
				                    <c:if test="${v.grade == 3}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                                	<option value="${v.categoryId}" <c:if test='${v.categoryId==category.parentId}'>selected</c:if>>
	                                	<c:if test="${v.grade == 1}">${v.categoryName}</c:if>
	                                    <c:if test="${v.grade == 2}">&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
	                                    <c:if test="${v.grade == 3}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                                	</option>
                                </c:forEach>
                        </select>
	                </div>
	                <div class="col-sm-3">
	                    <button type="button" name="resetParentCategory" id="resetParentCategory" class="btn btn-sm input-submit">设置为根分类</button>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="title" class="col-sm-3 control-label"><font class="red">* </font>分类名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="categoryName" name="categoryName" value="${category.categoryName}" placeholder="请输入分类名称" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="sort_order" class="col-sm-3 control-label">序号</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${category.sortOrder}" placeholder="请输入序号" />
                    </div>
                </div>
                
                <div class="form-group file-reset">
                    <label class="col-sm-3 control-label">默认图片</label>
                    <div class="col-sm-9">
                        <input type="file" name="image" id="image" class="filestyle" data-icon="false" data-classbutton="btn btn-default" data-classinput="form-control inline input-s" style="display: none;" />
                        <c:if test="${category.image != null && category.image != ''}">
                            <a href="${IMG_URL}${category.image}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${category.image}" /></a>
                        </c:if>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="description" class="col-sm-3 control-label">描述</label>
                    <div class="col-sm-6">
                        <textarea rows="2" class="form-control" id="description" name="description" placeholder="您可以在这里对该分类进行简要描述">${category.description}</textarea>
                    </div>
                </div>
                
                <input type="hidden" name="categoryId" value="${category.categoryId}" />
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存并返回列表管理</button>
            <c:if test="${category==null}"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
            <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
            <span id="edit_notice"></span>
        </div>
    </div>
</div>
<!-- / modal - 编辑分类 -->

<!-- {literal} -->
<script type="text/javascript">
    /* 重置父分类 */
    $("#resetParentCategory").click(function() {
        $('#parentCategoryId').val("0");
        $('#parentId').get(0).selectedIndex = 0;
        
        // 若是修改，点击选择后，判断其是否转移分类，并标记
        if ($("#categoryId").val() > 0 && 0 != $("#oldParentCategoryId").val()) {
            $("#updateChangeCat").val("1");
        } else {
            $("#updateChangeCat").val("0");
        }
    });
</script>
<!-- {/literal} -->

<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>    
<script src="${STATIC_URL}/back/modules/goods/js/category.edit.js"></script>
