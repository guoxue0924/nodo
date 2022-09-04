<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"><c:choose><c:when test="${lv!=null}">编辑分组</c:when><c:otherwise>添加分组</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/casuserLevel/add" method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">等级名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${lv.name}" placeholder="请输入等级名称" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否为默认等级</label>
                        <div class="col-sm-4">
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" name="isDefault" value="1" <c:if test="${lv.isDefault == 1}">checked="" </c:if>/>&nbsp;是
                            </label>
		                    <label class="checkbox-inline"><!-- lv.is_default !=null ||  -->
		                        <input type="radio" name="isDefault" value="0" <c:if test="${lv.isDefault == 0}"> checked=""</c:if> />&nbsp;否
		                    </label>
		                    <p class="form-control-static">如果选择“是”，添加等级成功时，所属用户组的初始等级为当前等级</p>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属用户组</label>
                        <div class="col-sm-4">
		                    <select id="userGroupId" name="userGroupId" class="input-sm form-control inline">
		                    	<c:forEach var="v" items="${userGroupList}">
                                <option value="${v.userGroupId}" <c:if test="${v.userGroupId == lv.userGroupId}">selected</c:if> >${v.groupName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <%-- {if false}
                    <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-4">
                            <label for="default_lv_y" class="checkbox-inline p-left-0">
                                <input type="radio" name="status"  value="1" <c:if test="${lv.status == 1}">checked=""</c:if> />&nbsp;启用
                            </label>
		                    <label for="default_lv_n" class="checkbox-inline">
		                        <input type="radio" name="status"  value="0" <c:if test="${lv.status == 1}">checked=""</c:if> />&nbsp;禁用
		                    </label>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    {/if} --%>

                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label">等级描述</label>
                        <div class="col-sm-4">
                            <textarea name="description" id="description" rows="3" class="form-control" placeholder="请输入等级描述">${lv.description}</textarea>
                        </div>
                    </div>
                    
                    <input type="hidden" id="userLvId" name="userLvId" value="${lv.userLvId}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${lv ==null }"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/cas/js/lv.edit.js" type="text/javascript"></script>
