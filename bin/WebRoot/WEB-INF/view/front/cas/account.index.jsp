<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>账户信息</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active"><a href="${BASE_URL}/front/cas/account">基本信息</a></li>
                <li class="normal"><a href="${BASE_URL}/front/cas/account/avatar">更换头像</a></li>
            </ul>
        </div>
        <div class="ncm-user-profile">
            <div class="user-avatar"><span><img src="${IMG_URL}${loggedInUser.avatar}"></span></div>
            <div class="ncm-default-form fr">
                <form method="post" id="edit_form" action="${BASE_URL}/front/cas/account">
                    <dl>
                        <dt>用户名称：</dt>
                        <dd>
                            <span class="w400">${loggedInUser.username}&nbsp;&nbsp;
                            <div class="nc-grade-mini" style="cursor:pointer;"
                                 onclick="javascript:go('');">
                                V0
                            </div>
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>邮箱：</dt>
                        <dd>
                            <span class="w400">${loggedInUser.email}&nbsp;&nbsp;
                                <a href="index.php?act=member_security&amp;op=auth&amp;type=modify_email">绑定邮箱</a>
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>真实姓名：</dt>
                        <dd>
                            <span class="w400">
                                <input type="text" class="text" maxlength="20" name="realname" value="${loggedInUser.realname}">
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>昵称：</dt>
                        <dd>
                            <span class="w400">
                                <input type="text" class="text" maxlength="20" name="nickname" value="${loggedInUser.nickname}">
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>性别：</dt>
                        <dd>
                            <span class="w400">
                                <label><input type="radio" name="gender" value="0">保密</label>&nbsp;&nbsp;
                                <label><input type="radio" name="gender" value="1">男</label>&nbsp;&nbsp;
                                <label><input type="radio" name="gender" value="2">女</label>
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>生日：</dt>
                        <dd>
                            <span class="w400">
                            <input type="text" class="text" name="birthday" maxlength="10" id="birthday" value="${userDetail.birthday}"
                   readonly="readonly">
                            <%-- <input id="birthday" name="birthday" class="input-sm input-s datepicker-input form-control" type="text" 
                    data-date-format="yyyy-mm-dd" value="${userDetail.birthday}" readonly="readonly"/> --%>
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>所在地区：</dt>
                        <dd>
                            <input type="hidden" value="${userDetail.regionName}" name="regionName" id="regionName" class="area_names">
                            <span id="region" class="w400">
                            <input type="hidden" value="${consignee.regionId}" name="regionId" id="regionId" class="area_names">
                            <c:choose><c:when test="${userDetail.regionName !=null}">
                            ${userDetail.regionName}
                                <input id="edit_area" type="button" value="编辑" style="width: 60px; height: 32px; border: 1px solid rgb(231, 231, 231); cursor: pointer; background-color: rgb(245, 245, 245);" class="edit_region">
                            </c:when><c:otherwise>
                                <select name="province" id="province">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${region}" var="r">
                                    <option value="${r.regionId}">${r.regionName}</option>
                                 </c:forEach>
                                </select>
                            </c:otherwise></c:choose>
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>邮政编码：</dt>
                        <dd>
                            <span class="w400">
                                <input type="text" class="text" maxlength="30" name="zipcode" value="${userDetail.zipcode}">
                            </span>
                        </dd>
                    </dl>
                    <dl class="bottom">
                        <dt></dt>
                        <dd>
                            <label class="submit-border">
                                <input type="button" class="submit" value="保存修改">
                            </label>
                        </dd>
                    </dl>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="clear"></div>
</div>

<%-- <script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script> --%>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>

<link rel="stylesheet" href="${STATIC_URL}/plugins/datepicker/css/datepicker2.css"/>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN-2.js"></script>
<script src="${STATIC_URL}/plugins/jquery-validate/1.9/jquery.validation.min.js"></script>
<script src="${STATIC_URL}/front/modules/cas/js/common_select.js"></script>

<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/account.index.js"></script>

<script type="text/javascript">
    var gender = ${loggedInUser.gender};
    $(function(){
        $('input[name=gender]').each(function(){
            if (this.value == gender){
                this.checked = true;
            }
        });
    });
</script>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>