<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="wrapper">
    <section class="panel panel-default">
        <header class="panel-heading">基本资料</header>
        <table class="table table-striped m-b-none text-sm">
            <tbody>
                <tr>
                    <td class="col-sm-2 text-right">用户名：</td>
                    <td class="col-sm-4">${user.username}</td>
                    <td class="col-sm-2 text-right">用户状态：</td>
                    <td class="col-sm-4">
                        <%-- <c:if test="${user.is_del == 1}">已删除</c:if> --%>
                        <%-- <c:if test="${user.is_verify == 0}">待审核</c:if> --%>
                        <c:choose><c:when test="${user.status == 1}">已激活</c:when><c:otherwise>未激活</c:otherwise></c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="col-sm-2 text-right">昵称：</td>
                    <td class="col-sm-4">${user.nickname}</td>
                    <td class="col-sm-2 text-right">真实姓名：</td>
                    <td class="col-sm-4">${user.realname}</td>
                </tr>
                <tr>
                    <td class="col-sm-2 text-right">性别：</td>
                    <td class="col-sm-4"><c:choose><c:when test="${user.gender == 1}">男</c:when><c:otherwise><c:if test="${user.gender == 2}">女</c:if>保密</c:otherwise></c:choose></td>
                    <td class="col-sm-2 text-right">用户等级：</td>
                    <td class="col-sm-4">${lvInfo.name}</td>
                </tr>
                <tr>
                    <td class="col-sm-2 text-right">手机号码：</td>
                    <td class="col-sm-4">${user.phone}</td>
                    <td class="col-sm-2 text-right">电子邮箱：</td> 
                    <td class="col-sm-4">${user.email}</td>
                </tr>
                <tr>
                    <td class="col-sm-2 text-right">身份证号码：</td>
                    <td class="col-sm-4">${user.idcard}</td>
                    <%-- <td class="col-sm-2 text-right">生日：</td>
                    <td class="col-sm-4">${user.birthday|truncate:10:true}</td> --%>
                </tr>
                <tr>
                    <td class="col-sm-2 text-right">用户来源：</td>
                    <td class="col-sm-4">
                        <%-- <c:if test="${user.source == 'qq'}">QQ</c:if>
                        <c:if test="${user.source == 'wechat'}">微信</c:if>
                        <c:if test="${user.source == 'alipay'}">支付宝</c:if>
                        <c:if test="${user.source == 'weibo'}">微博</c:if>
                        <c:if test="${user.source == 'baidu'}">百度</c:if>
                        <c:if test="${user.source == 'renren'}">人人网</c:if>
                        <c:if test="${user.source == ''}">本地注册</c:if> --%>                        
                    </td>
                    <td class="col-sm-2 text-right">职业：</td>
                    <%-- <td class="col-sm-4">${user.job}</td> --%>
                </tr>
                <tr>
                    <td class="col-sm-2 text-right">注册时间：</td>
                    <td class="col-sm-4"><fmt:formatDate value="${user.ctime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                    <td class="col-sm-2 text-right">最后登录时间：</td>
                    <td class="col-sm-4"><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                </tr>
            </tbody>
        </table>
    </section>
    
    <section class="panel panel-default">
        <header class="panel-heading">收货地址</header>
        <table class="table table-striped m-b-none text-sm">
            <thead>
                <tr>
                    <th>收货人</th>
                    <th>所在地区</th>
                    <th>详细地址</th>
                    <th>手机或固话</th>
                    <th>邮箱</th>
                    <!-- <th>别名</th> -->
                    <th>默认</th>
                </tr>
            </thead>
            <tbody>
            <c:if test="${userConsignees != null}">
                <c:forEach items="${userConsignees}" var="consignees" >
                <tr>
                    <td>${consignees.name}</td>
                    <td>${consignees.regionName}</td>
                    <td>${consignees.address}</td>
                    <td>${consignees.mobile}</td>
                    <td>${consignees.email}</td>
                    <%-- <td>${consignees.title}</td> --%>
                    <td><c:choose><c:when test="${consignees.isDefault == 1}"><i class="fa fa-check text-success"></i></c:when><c:otherwise><i class="fa fa-ban text-danger"></i></c:otherwise></c:choose></td>
                </tr>
                </c:forEach>
             </c:if>
            </tbody>
        </table>
    </section>
</section>