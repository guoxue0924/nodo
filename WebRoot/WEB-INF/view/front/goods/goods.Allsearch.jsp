<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/front/modules/index/css/layout.css" rel="stylesheet"/>
<link href="${STATIC_URL}/front/modules/goods/css/goods.search.css" rel="stylesheet"/>
<script src="${STATIC_URL}/front/modules/goods/js/goods.search.js"></script>

<div class="nch-container wrapper">
    <div class="left">
        <div class="nch-module nch-module-style02">
            <div class="content">
                <ul id="files" class="tree" role="tree">
                    <c:forEach items="${LOAD_CATEGORIES}" var="c">
	                    <li role="treeitem">
	                        <i class="tree-parent tree-parent-collapsed" tabindex="-1"></i>
	                        <a class="" href="${BASE_URL}/front/goodsContent/search?categoryId=${c.categoryId}">${c.categoryName}</a>
	                        <ul role="group" class="">
	                            <c:forEach items="${c.subList}" var="second">
	                            <li role="treeitem" aria-expanded="${c1.aria-expanded}">
	                                <i class="tree-parent tree-parent-collapsed" tabindex="-1"></i>
	                                <a class="" href="${BASE_URL}/front/goodsContent/search?categoryId=${second.categoryId}">${second.categoryName}</a>
	                                <ul role="group" class="">
	                                    <c:forEach items="${second.subList}" var="third">
		                                    <li class="tree-parent tree-parent-collapsed" role="treeitem">
		                                        <i tabindex="-1"></i>
		                                        <a class="" href="${BASE_URL}/front/goodsContent/search?categoryId=${third.categoryId}">${third.categoryName}</a>
		                                    </li>
	                                    </c:forEach>
	                                </ul>
	                            </li>
	                            </c:forEach>
	                        </ul>
	                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="${STATIC_URL}/plugins/waypoints.js"></script>
<script src="${STATIC_URL}/front/modules/goods/js/search_category_menu.js"></script>
