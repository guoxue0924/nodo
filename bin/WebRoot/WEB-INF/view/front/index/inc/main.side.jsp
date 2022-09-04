<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="public-nav-layout">
	<div class="wrapper">
		<div class="all-category">

			<div class="title">
				<i></i>

				<h3>
					<a href="/goods/index/search">所有商品分类</a>
				</h3>
			</div>
			<div id="category" class="category">
				<ul class="menu">
					<c:forEach items="${LOAD_CATEGORIES}" var="c">
						<li cat_id="1" class="odd">
							<div class="class">
								<span class="ico"><img src="${STATIC_URL}/front/images/index/category-pic-1.jpg"> </span>
								<h4>
									<a href="${BASE_URL}/front/goodsContent/search?categoryId=${c.categoryId}">${c.categoryName}</a>
								</h4>
								<span class="arrow"></span>
							</div>
							<div class="sub-class" cat_menu_id="1" style="display: none; top: -1px;">
								<div class="sub-class-content">
									<div class="recommend-class">
									   <c:forEach items="${c.subList[0].subList}" var="v" begin="0" end="5" step="1" varStatus="status">
                                            <span><a href="#" title="${v.categoryName}">${v.categoryName}</a> </span>
									   </c:forEach>
									</div>
									<c:forEach items="${c.subList}" var="second">
										<dl>
											<dt>
												<h3>
													<a href="${BASE_URL}/front/goodsContent/search?categoryId=${second.categoryId}">${second.categoryName}</a>
												</h3>
											</dt>
											<dd class="goods-class">
												<c:forEach items="${second.subList}" var="third">
													<a href="${BASE_URL}/front/goodsContent/search?categoryId=${third.categoryId}">${third.categoryName}</a>
												</c:forEach>
											</dd>
										</dl>
									</c:forEach>
								</div>
								<div class="sub-class-right">
									<div class="brands-list">
										<ul>
                                            <c:forEach items="${LOAD_BRANDS}" var="brand" step="1" begin="0" end="13" varStatus="status">
    											<li><a title="${brand.brandName}" href="#"><img width="90" alt="${brand.brandName}" heiht="30" src="${IMG_URL}${brand.brandLogo}"><span>${brand.brandName}</span> </a></li>
                                            </c:forEach>
										</ul>
									</div>
									<div class="adv-promotions"></div>
								</div>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<ul class="site-menu">
			<li><a href="${BASE_URL}/front/index/index" class="current">首页</a>
			</li>
<!-- 			<li><a href="/groupon/purch/index"> 抢购</a> -->
<!-- 			</li> -->
<!-- 			<li><a href="/goods/brand/index"> 品牌</a> -->
<!-- 			</li> -->
<!-- 			<li><a href="/bts/asset/index"> 积分中心</a> -->
<!-- 			</li> -->
<!-- 			<li><a href="http://10.58.135.8/shop/index.php?act=special&amp;op=special_list"> 专题</a> -->
<!-- 			</li> -->
		</ul>
	</div>
</nav>
