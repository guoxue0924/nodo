<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${STATIC_URL}/back/modules/navigation/css/navigation.css" rel="stylesheet" type="text/css" />

<!-- modal - 编辑导航 -->
<div class="modal-dialog" id="modal_edit">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<p class="h4">
				<c:choose>
					<c:when test="${permission != null}">编辑权限</c:when>
					<c:otherwise>添加权限</c:otherwise>
				</c:choose>
			</p>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" id="edit_form"
				action="${BASE_URL}/back/adminPermission/add" method="post">
				<div class="form-group">
					<label class="col-sm-3 control-label"><font class="red">*</font>所属菜单模块:</label>
					<div class="col-sm-3">
						<select class="form-control col-sm-6" 
							name="sysTwo_navigationId">
							<option value="">请选择父级菜单</option>
							<c:if test="${sysTwo != null}">
								<c:forEach items="${sysTwo}" var="v">
									<option value="${v.navigationId}"
										<c:if test="${ v.navigationId == parentId}">selected</c:if>>${v.title}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="col-sm-3">
						<select class="form-control col-sm-6"
							name="navigationId" id = "navigationIdSelect">
							<option value="">请选择子级菜单</option>
							<c:if test="${sysThree != null}">
								<c:forEach items="${sysThree}" var="v">
									<option data-link="${v.link}" value="${v.navigationId}"
										<c:if test="${ v.navigationId == permission.navigationId}">selected</c:if>>${v.title}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="permission_name" class="col-sm-3 control-label"><font
						class="red">*</font>权限名</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="permissionName"
							name="permissionName" value="${permission.permissionName}"
							placeholder="请输入权限名" />
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>

				<div class="form-group">
					<label for="permission_group" class="col-sm-3 control-label"><font
						class="red">*</font>URL地址</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="url" name="url"
							value="${permission.url}" placeholder="" />
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>

				<div class="form-group">
					<label for="description" class="col-sm-3 control-label">描述</label>
					<div class="col-sm-4">
						<textarea class="form-control" id="description" name="description"
							placeholder="请输入描述">${permission.description}</textarea>
					</div>
				</div>

				<input type="hidden" name="permission_id_now" id="permission_id_now"
					value="${permission.permissionId}" /> <input type="hidden"
					name="permissionId" id="permissionId"
					value="${permission.permissionId}" />
			</form>
		</div>
		<div class="modal-footer">
			<div class="m-t-sm">
			<button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存并返回列表管理</button>
				<c:if test="${permission != null}">
					<button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button>
				</c:if>
				<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">取消</button>
				<span id="edit_notice"></span>
			</div>
		</div>
	</div>
</div>
<script src="${STATIC_URL}/back/modules/admin/js/permission.edit.js" type="text/javascript"></script>