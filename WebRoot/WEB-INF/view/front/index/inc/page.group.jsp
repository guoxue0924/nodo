<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${page_groups != null}">
<div style="position:absolute;bottom:20px;text-align:center;">
    <c:forEach items="${page_groups}" var="v">
    <div  style="float:left;text-align:left;">      
      <h2> ${v.group_name} </h2>
      <ul class="submenu" style="display:block;">
      <c:if test="${v.page_list != null}">
        <c:forEach items="${v.page_list}" var="page">
        <li><a href="/page/${v.folder}/${v.page_folder}" class="myCatLink">${page.title}</a></li>
        </c:forEach>
        </c:if>
      </ul>
    </div>
    <div  style="float:left;width:35px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </c:forEach>
</div> 
</c:if>