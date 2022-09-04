<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/front/modules/index/css/layout.css" rel="stylesheet"/>
<link href="${STATIC_URL}/front/modules/goods/css/goods.search.css" rel="stylesheet"/>
<script src="${STATIC_URL}/front/modules/goods/js/goods.search.js"></script>
<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span>搜索结果</span>
    </div>
</div>

<div class="nch-container wrapper">
    <div class="left">
        <div class="nch-module nch-module-style02">
            <div class="title">
                <h3>分类筛选</h3>
            </div>
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
        <!-- S 推荐展位 -->
        <div nctype="booth_goods" class="nch-module" style="display:none;"></div>
        <!-- E 推荐展位 -->
        <div class="nch-module">
            <a href="${BASE_URL}/front/index/index" title="商品列表页左侧广告位">
                <c:if test="${adverts != null && adverts != ''}">
                    <img style="width:206px;height:300px" border="0" src="${IMG_URL}${adverts[0].attachmentFilePath}" alt="">
                </c:if>
                <c:if test="${adverts == null || adverts == ''}">
                    <img style="width:206px;height:300px" border="0" src="${STATIC_URL}/front/images/goods/nopic.jpg" alt="">
                </c:if>
            </a>
        </div>
    </div>
    <div class="right">
        <div class="shop_con_list" id="main-nav-holder">
            <nav class="sort-bar" id="main-nav">
                <div class="pagination">
                    <ul>
                        <li><span>上一页</span></li>
                        <li><span>下一页</span></li>
                    </ul>
                </div>
                <div class="nch-sortbar-array"> 排序方式：
                    <ul>
                        <li class='${empty sort ? "selected" : ""}'>
                            <a href="${BASE_URL}/front/goodsContent/search" title="默认排序">默认</a>
                        </li>
                        <li class='${sort eq "salesVolume" ? "selected" : ""}'>
                            <a href="${BASE_URL}/front/goodsContent/search?sort=salesVolume" title="点击按销量从高到低排序">销量<i></i></a>
                        </li>
                        <li class='${sort eq "price" ? "selected" : ""}'>
                            <a href="${BASE_URL}/front/goodsContent/search?sort=price" title="点击按价格从高到低排序">价格<i></i></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- 商品列表循环  -->
            <div>
                <div class="squares" nc_type="current_display_mode">
                    <c:if test="${not empty goods}">
	                    <ul class="list_pic">
	                        <c:forEach items="${goods}" var="g">
	                        <li class="item">
	                            <div class="goods-content" nctype_goods="${g.contentId}">
	                                <div class="goods-pic">
	                                    <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skus[0].skuId}" class="skuUrl" target="_blank" title="${g.name}">
	                                        <img src="${IMG_URL}${g.skus[0].filepath}" title="${g.name}">
	                                    </a>
	                                </div>
	                                <%--<c:if test="${g.promotion_type eq 3}"><div class="goods-promotion"><span>抢购商品</span></div></c:if> --%>
	                                <div class="goods-info" style="top: 230px;">
	                                    <div class="goods-pic-scroll-show">
	                                        <ul>
	                                           <c:if test="${not empty g.skus}">
	                                                <c:forEach items="${g.skus}" var="s" varStatus="status">
	                                                    <c:if test="${status.index < 5}">
			                                                <li class='${status.index == 0 ? "selected" : ""}'><a href="javascript:void(0);">
			                                                    <img src="${IMG_URL}${s.filepath}"></a>
			                                                    <input type="hidden" name="skuId" value="${s.skuId}" />
			                                                    <input type="hidden" name="skuPrice" value="<fmt:formatNumber value="${s.price}" pattern="#.##" />" />
			                                                </li>
		                                                </c:if>
	                                                </c:forEach>
	                                            </c:if>
	                                        </ul>
	                                    </div>
	                                    <div class="goods-name">
	                                        <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skus[0].skuId}" class="skuUrl" target="_blank" title="全国唯一支持开瓶试饮7天无理由退换货">${g.name}</a></div>
	                                    <div class="goods-price"><em class="sale-price" title="商城价：¥${g.skus[0].price}">¥<fmt:formatNumber value="${g.skus[0].price}" pattern="#.##" /></em>
	                                        <%--<em class="market-price" title="市场价：¥${g.priceMarket}">¥<fmt:formatNumber value="${g.priceMarket}" pattern="#.##" /></em> --%>
	                                        <span class="raty" data-score="0" title="" style="width: 80px;">
	                                        <input type="hidden" name="score" value="0" readonly="readonly"></span>
	                                    </div>
	                                    <div class="goods-sub">
	                                        <span class="goods-compare" nc_type="compare_100008" data-param=""></span> </div>
	                                    <div class="sell-stat">
	                                        <ul>
	                                            <li>
	                                                <a href="" target="_blank" class="status">0</a>
	                                                <p>商品销量</p>
	                                            </li>
	                                            <li>
	                                                <a href="javascript:"  target="_blank">0</a>
	                                                <p>用户评论</p>
	                                            </li>
	                                            <li><em member_id="1">&nbsp;</em></li>
	                                        </ul>
	                                    </div>
	                                    <div class="store"><a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skus[0].skuId}" title="查看详情" class="name">查看详情</a></div>
	                                    <div class="add-cart">
	                                        <a href="javascript:void(0);" nctype="add_cart" data-param="${g.contentId}">
	                                            <i class="icon-shopping-cart"></i>加入购物车
	                                        </a>
	                                    </div>
	                                </div>
	                            </div>
	                        </li>
	                        </c:forEach>
	                        <div class="clear"></div>
	                    </ul>
                    </c:if>
                    <c:if test="${empty goods}">
                        <div id="no_results" class="no-results"><i></i>没有找到符合条件的商品</div>
                    </c:if>
                </div>
                <%--<form id="buynow_form" method="post" action="http://10.58.135.8/shop/index.php" target="_blank">
                    <input id="act" name="act" type="hidden" value="buy">
                    <input id="op" name="op" type="hidden" value="buy_step1">
                    <input id="goods_id" name="cart_id[]" type="hidden">
                </form> --%>
                <input type="hidden" id="categoryId" value="${categoryId}" />
                <input type="hidden" id="sort" value="${sort}" />
                <input type="hidden" id="count" value="${count}" />
                <input type="hidden" id="pageIndex" value="${pageIndex}" />
                <input type="hidden" id="pageSize" value="${pageSize}" />
                <input type="hidden" id="pageCount" value="${pageCount}" />
                <script type="text/javascript"
                        src="${STATIC_URL}/plugins/jquery-raty/jquery.raty.min.js"></script>
            </div>
            <div class="tc mt20 mb20">
                <div class="pagination">
					<div class="tc mt20 mb20">
						<div class="pagination">
							<ul>
							    <c:if test="${count <= pageSize}">
								    <li><span>首页</span></li>
	                                <li><span>上一页</span></li>
	                                <li><span class="currentpage">1</span></li>
	                                <li><span>下一页</span></li>
	                                <li><span>尾页</span></li>
							    </c:if>
                                <c:if test="${count > pageSize}">
									<li><a class="demo" href="javascript:void(0);" onclick="go(1);"> <span>首页</span> </a></li>
									<c:choose>
                                        <c:when test="${(pageIndex - 1) > 0}">
                                            <li><a class="demo" href="javascript:void(0);" onclick="go(${pageIndex - 1});"> <span>上一页</span> </a></li>
                                        </c:when>
								        <c:otherwise>
								            <li><span>上一页</span></li>
								        </c:otherwise>
									</c:choose>
									<c:if test="${(pageIndex - 1) > 1}">
									   <li><span>...</span></li>
									</c:if>
									<c:if test="${pageIndex > 1}">
										<li><a class="demo" href="javascript:void(0);" onclick="go(${pageIndex - 1});"> <span>${pageIndex - 1}</span> </a></li>
									</c:if>
									<li><span class="currentpage">${pageIndex}</span></li>
									<c:if test="${pageIndex < pageCount}">
										<li><a class="demo" href="javascript:void(0);" onclick="go(${pageIndex + 1});"> <span>${pageIndex + 1}</span> </a></li>
									</c:if>
									<c:if test="${(pageIndex + 1) < pageCount}">
                                       <li><span>...</span></li>
                                    </c:if>
									<c:choose>
                                        <c:when test="${(pageIndex + 1) <= pageCount}">
                                            <li><a class="demo" href="javascript:void(0);" onclick="go(${pageIndex + 1});"> <span>下一页</span> </a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><span>下一页</span></li>
                                        </c:otherwise>
                                    </c:choose>
									<li><a class="demo" href="javascript:void(0);" onclick="go(${pageCount});"> <span>尾页</span> </a></li>
                                </c:if>
							</ul>
						</div>
					</div>
				</div>
            </div>
        </div>
    </div>
</div>
<script src="${STATIC_URL}/plugins/waypoints.js"></script>
<script src="${STATIC_URL}/front/modules/goods/js/search_category_menu.js"></script>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>