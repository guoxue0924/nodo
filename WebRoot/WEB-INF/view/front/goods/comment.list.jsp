<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<input type="hidden" id="count" value="${page.count}">
<c:forEach items="${page.data}" var="c">
<div class="ncs-commend-floor">
	<div class="user-avatar">
		<a href="javascript:void(0)" target="_blank" data-param="" nctype="mcard" data-hasqtip="6" aria-describedby="qtip-6">
			<img src="${IMG_URL}${c.avatar}">
        </a>
    </div>
    <dl class="detail">
    	<dt>
    		<span class="user-name">
    			<a href="javascript:void(0)" target="_blank" data-param="" nctype="mcard" data-hasqtip="7">
    				${c.nickname}
   				</a>
   			</span>
			<time pubdate="pubdate">[<fmt:formatDate value="${c.ctime}" pattern="yyyy-MM-dd hh:mm:ss"/>]</time>
        </dt>
        <dd>用户评分：
            <span class="raty" data-score="${c.rankBase}" title="" style="width: 100px;">
            <input type="hidden" name="score" value="${c.rankBase}" readonly="readonly">
            </span>
        </dd>
        <dd class="content">评价详情：<span>${c.content}</span></dd>
        <dd>
        	<c:if test="${fn:length(c.filePaths) > 0}">
        	晒图图片：
        	<ul class="photos-thumb">
        		<c:forEach items="${c.filePaths}" var="filePath">
            	<li>
                	<a nctype="nyroModal" href="${IMG_URL}${filePath}"> <img src="${IMG_URL}${filePath}"></a>
            	</li>
            	</c:forEach>
        	</ul>
        	</c:if>
        </dd>
    </dl>
</div>
</c:forEach>                   