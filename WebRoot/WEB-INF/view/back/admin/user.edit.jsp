<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"><c:choose><c:when test="${user != null }">编辑用户</c:when><c:otherwise>添加用户</c:otherwise></c:choose></p>
            </header>
            
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/adminUser/add" method="post">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label"><font class="red">*</font><c:if test="${ user.userid != null}"></c:if>用户名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control <c:if test='${ user.userid != null}'>disabled</c:if>" id="username" name="username" value="${ user.username}" placeholder="请输入用户名" <c:if test='${ user.userid != null}'>readonly="readonly"</c:if> />
                        </div>
                        <div class="col-sm-6">
                            <p class="form-control-static"><c:if test="${ user.userid != null}">编辑状态下，用户名不可以更改</c:if></p>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                        
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label"><font class="red">*</font><c:if test="${ user.userid != null}"></c:if>密码</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="password" name="password" value="" />
                        </div>
                        <div class="col-sm-6">
                            <p class="form-control-static"><c:if test="${ user.userid != null}">不修改请不要填写</c:if></p>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                        
                    <div class="form-group">
                        <label for="fullname" class="col-sm-2 control-label"><font class="red">*</font>全名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="fullname" name="fullname" value="${user.fullname}" placeholder="请输入全名" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                        
                    <div class="form-group">
                        <label for="nickname" class="col-sm-2 control-label"><font class="red">*</font>昵称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" placeholder="请输入昵称" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><font class="red">*</font>性别</label>
                        <div class="col-sm-4">
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" name="gender" value="1" <c:if test="${ user.gender == 1}">checked="checked"</c:if> />&nbsp;男
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="gender" value="0" <c:if test="${ user.gender == 0}">checked="checked"</c:if> />&nbsp;女
                            </label>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                        
                    <div class="form-group">
                        <label for="idcard" class="col-sm-2 control-label">身份证</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="idcard" name="idcard" value="${ user.idcard}" placeholder="请输入身份证" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                        
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">E-mail</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="请输入电子邮箱" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-4">
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" name="status" value="0" <c:if test="${ user.status == 0}">checked="checked"</c:if> />&nbsp;未激活
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" value="1" <c:if test="${ user.status == 1}">checked="checked"</c:if> />&nbsp;已激活
                            </label>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户组</label>
                        <div class="col-sm-3">
                            <select name="groups" class="form-control" multiple>
                                <c:forEach items="${groups}" var="v">
		                        <option value="${v.groupId}" <c:forEach items="${user_groupids}" var="g"><c:if test='${ v.groupId == g}'>selected="selected"</c:if></c:forEach>>${v.groupName}</option>
		                        </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="userid" value="${user.userid}" />
                    <input type="hidden" name="salt" value="${user.salt}" />
                    <input type="hidden" name="domain" value="${user.domain}" />
                    <input type="hidden" name="ctime" value="${user.ctime}" />
                    <input type="hidden" name="mtime" value="${user.mtime}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${ user != null}"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/admin/js/user.edit.js" type="text/javascript"></script>