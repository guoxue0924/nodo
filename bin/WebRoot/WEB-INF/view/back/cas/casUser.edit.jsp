<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<!-- Datepicker -->
<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${user!=null}">编辑会员</c:when><c:otherwise>添加会员</c:otherwise></c:choose></p>
                <input type="hidden" name="username_edit" id="username_edit" value="${user.username}" />
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/casuser/add" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-4">
                            <p class="form-control-static">${user.username}</p>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group m-b-xs">
				        <label for="password" class="col-sm-2 control-label">重置密码</label>
				        <div class="col-sm-4">
				            <input type="password" class="form-control" id="password" name="password" value="" placeholder="请输入重置密码" maxlength="20" />
				            <p class="help-block m-b-none">密码由半角字符的字母、数字和下划线“_”组成，不重置请不要填写</p>
				        </div>
				    </div>
				    <div class="line line-dashed line-lg pull-in"></div>
				    
                     <div class="form-group">
                        <label class="col-sm-2 control-label">审核</label>
                        <div class="col-sm-4">
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" name="isVerify" value="1" <c:if test="${user.isVerify == 1}">checked=""</c:if> />&nbsp;通过
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="isVerify" value="0" <c:if test="${user.isVerify == 0}">checked=""</c:if> />&nbsp;不通过
                            </label>
                        </div>
                    </div>
                    <div class="line line-dashed line-sm pull-in"></div>
                    
                    <div class="form-group m-b-xs">
				        <label class="col-sm-2 control-label">会员等级</label>
				        <div class="col-sm-4">
				            <select id="userLvId" name="userLvId" class="input-sm form-control inline">
				            	<c:forEach items="${lvList}" var="v">
                                <option value="${v.userLvId}" <c:if test="${v.userLvId == user.userLvId}">selected</c:if>>${v.name}</option>
                               	</c:forEach>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line-sm pull-in"></div>
                    
                    <div class="form-group m-b-xs">
				        <label for="realname" class="col-sm-2 control-label">真实姓名</label>
				        <div class="col-sm-4">
				            <input type="text" class="form-control" id="realname" name="realname" value="${user.realname}" placeholder="请输入真实姓名" />
				        </div>
				    </div>
				    <div class="line line-dashed line-sm pull-in"></div>
				    
				    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-4">
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" name="gender"  value="1" <c:if test="${user.gender == 1}">checked=""</c:if> />&nbsp;男
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="gender"  value="2" <c:if test="${user.gender == 2}">checked=""</c:if> />&nbsp;女
                            </label>
                        </div>
                    </div>
                    <div class="line line-dashed line-sm pull-in"></div>
                    
                    <div class="form-group m-b-xs">
				        <label for="phone" class="col-sm-2 control-label">手机号码</label>
				        <div class="col-sm-4">
				            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" placeholder="请输入手机号码" maxlength="14" />
				        </div>
				    </div>
				    <div class="line line-dashed line-sm pull-in"></div>
                    
                    <div class="form-group m-b-xs">
				        <label for="email" class="col-sm-2 control-label">E-mail</label>
				        <div class="col-sm-4">
				            <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="请输入电子邮箱" />
				        </div>
				    </div>
				    <div class="line line-dashed line-sm pull-in"></div>
                    
                    <%-- <div class="form-group m-b-xs">
				        <label class="col-sm-2 control-label">出生日期</label>
				        <div class="col-sm-4">
				            <input id="birthdayStr" name="birthdayStr" class="input-sm input-s datepicker-input form-control" type="text" 
                                    data-date-format="yyyy-mm-dd" value="${user.birthday}" />
				        </div>
				    </div> --%>
				    <div class="line line-dashed line-sm pull-in"></div>
				    
                    <%-- <div class="form-group m-b-xs">
				        <label class="col-sm-2 control-label">所在地区</label>
				        <div class="col-sm-8">
				            <select data-init="3743" data-selected="${regionHidArr[2]}" class="input-sm form-control inline col-def-3 region">
				                <option value="0">请选择</option>
				            </select>
				            <select data-init="${regionHidArr[2]}" data-selected="${regionHidArr[3]}" class="input-sm form-control inline col-def-3 region">
				                <option value="0">请选择</option>
				            </select>
				            <select name="regionId" data-init="${regionHidArr[3]}" data-selected="${regionHidArr[4]}" class="input-sm form-control inline col-def-3 region">
				                <option value="0">请选择</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line-sm pull-in"></div>
                     --%>
                    <%-- <div class="form-group m-b-xs">
				        <label for="address" class="col-sm-2 control-label">详细地址</label>
				        <div class="col-sm-4">
				            <input type="text" class="form-control" id="address" name="address" value="${user.address}" placeholder="请输入详细地址" />
				        </div>
				    </div> --%>
                    
                    <input type="hidden" id="userid"  name="userid" value="${user.userid}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${user ==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/back/modules/cas/js/casUser.edit.js" type="text/javascript"></script>