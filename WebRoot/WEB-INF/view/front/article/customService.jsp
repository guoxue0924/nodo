<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>


<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
            <span><a href="http://b2c2.icooper.biz/shop">首页</a></span><span class="arrow">&gt;</span>
                <span>${categoryTitle }</span>
          </div>
  </div>
  <jsp:include page="../article/article.nav.jsp"/>
<link href="http://b2c2.icooper.biz/shop/templates/default/css/layout.css" rel="stylesheet" type="text/css">
<div class="nch-container wrapper">
 
  <div class="right">
    <div class="nch-article-con" style=" margin-top:-30px;">
      <div class="title-bar">
      
        <h3>${categoryTitle }</h3>
      </div>
            <ul class="nch-article-list">
             <c:forEach items="${content}" var="content">
            
                <li><i></i><a href="${BASE_URL}/front/articleContent/helpCenterDetail?contentId=${content.contentId}&categoryId=${categoryId }">${content.title}</a><time><fmt:formatDate value="${content.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/></time></li>
               
              </c:forEach>
              </ul>
           
    </div> 
    <div class="tc mb20">  <div class="pagination"> <ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><span>下一页</span></li><li><span>末页</span></li></ul> </div></div>
  
  </div>
</div>
<input type="hidden" value="${categoryId } id="categoryId" />
<div class="clear"></div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>