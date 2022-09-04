<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${STATIC_URL}/front/modules/cas/css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/common.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/ToolTip.js"></script>
<script id="noData" type="text/html">
    <tr>
        <td colspan="20" class="norecord"><div class="warning-option"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></div></td>
    </tr>
</script>
<div class="ncm-container">
<div class="left-layout">

<div class="left">
    <div class="nch-module nch-module-style01">
      <div class="title">
        <h3>文章分类</h3>
      </div>
      <div class="content">
        <ul class="nch-sidebar-article-class">
        <c:forEach items="${category}" var="category">
                    <li><a href="${BASE_URL}/front/articleContent/helpCenter?categoryId=${category.categoryId}">${category.title}</a></li>
                    
                  </c:forEach>
                  </ul>
      </div>
    </div>
    <div class="nch-module nch-module-style03">
      <div class="title">
        <h3>最新文章</h3>
      </div>
      <div class="content">
        <ul class="nch-sidebar-article-list">
         <c:forEach items="${contentNew}" var="contentNew" begin="0" end="4" step="1" >
                   <li><i></i><a href="${BASE_URL}/front/articleContent/helpCenterDetail?contentId=${contentNew.contentId}&categoryId=${categoryId }">${contentNew.title}</a></li>
         </c:forEach>
        </ul>
      </div>
    </div>
  </div>
</div>

</div>