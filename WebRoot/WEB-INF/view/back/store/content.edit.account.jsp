<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="edit-map wrapper default-hidden" id="edit_account">

    <div class="form-group m-b-xs">
        <label for="username" class="col-sm-2 control-label"><c:if test="${store != null}"><font class="red">* </font></c:if>管理员账号</label>
        <div class="col-sm-4">
        	<c:choose>
        	<c:when test="${store != null}">
            <p class="form-control-static">${store.username}</p>
            <input type="hidden" name="username" id="username" value="${store.username}" />
            <input type="hidden" name="userid" id="userid" value="${store.userid}" />
            </c:when>
            <c:otherwise>
            <input type="text" class="form-control" id="username" name="username" value="" placeholder="请输入管理员账号" maxlength="14" />
            <p class="help-block m-b-none">账号由半角字符的字母、数字和下划线“_”组成。以字母开头，长度为6～14位</p>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>

    <div class="form-group m-b-xs">
        <label for="password" class="col-sm-2 control-label"><c:if test="${store != null}"><font class="red">* </font></c:if><c:choose><c:when test="${store != null}">重置</c:when><c:otherwise>设置</c:otherwise></c:choose>密码</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="password" name="password" value="" placeholder="请输入<c:choose><c:when test="${store != null}">重置</c:when><c:otherwise>管理员</c:otherwise></c:choose>密码" maxlength="20" />
            <p class="help-block m-b-none">密码由半角字符的字母、数字和下划线“_”组成<c:if test="${store != null}">，不重置请不要填写</c:if></p>
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
    <c:if test="${store == null}">
    <div class="form-group m-b-xs">
        <label for="repassword" class="col-sm-2 control-label"><font class="red">* </font>确认密码</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="repassword" name="repassword" value="" placeholder="请再次输入管理员密码" maxlength="20" />
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    </c:if>
    
</section>