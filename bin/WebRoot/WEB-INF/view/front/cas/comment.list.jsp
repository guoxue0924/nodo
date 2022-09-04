<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<input type="hidden" value="${page.count}">
<c:forEach items="${page.data}" var="item">
<div class="ncm-evaluation-timeline">
    <div class="date">
    	<fmt:formatDate value="${item.ctime}" pattern="yyyy-MM-dd hh:mm:ss"/>
    </div>
    <div class="goods-thumb">
    	<a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${item.skuId}">
    		<img src="${IMG_URL}${item.goodsImage}">
    	</a>
    </div>
    <dl class="detail">
        <dt>
        	<a target="_blank" href=""></a>
		</dt>
        <dd>商品评分：
            <div class="raty" style="display: inline-block; width: 100px;" data-score="${item.rankBase}"></div>
        </dd>
        <dd>我的评价：${item.content}</dd>
        <dd>
        	<c:if test="${fn:length(item.filePaths) > 0}">
        	晒图图片：
        	<ul class="photos-thumb">
        		<c:forEach items="${item.filePaths}" var="filePath">
            	<li>
                	<a nctype="nyroModal" href="${IMG_URL}${filePath}"> <img src="${IMG_URL}${filePath}"></a>
            	</li>
            	</c:forEach>
        	</ul>
        	</c:if>
        	<c:if test="${fn:length(item.filePaths) == 0}">
            	<a href="${BASE_URL}/front/cas/comment/show?id=${item.id}" class="ncm-btn ncm-btn-orange">我要晒单</a>
            </c:if>
        </dd>
    </dl>
</div>
</c:forEach>