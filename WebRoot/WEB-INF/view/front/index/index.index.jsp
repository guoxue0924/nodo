<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="wrapper.prefix_front.jsp"/>
<link href="${STATIC_URL}/front/modules/index/css/index.css" rel="stylesheet"/>
<link href="${STATIC_URL}/front/modules/index/css/index.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="http://10.58.135.8/shop/resource/js/home_index.js" charset="utf-8"></script>-->

<div class="nch-breadcrumb-layout">
</div>


<!--[if IE 6]>
<script type="text/javascript" src="http://10.58.135.8/data/resource/js/ie6.js" charset="utf-8"></script>
<![endif]-->

<style type="text/css">
    .category {
        display: block !important;
    }
</style>
<div class="clear"></div>

<!-- HomeFocusLayout Begin-->
<div class="home-focus-layout">
    <ul id="fullScreenSlides" class="full-screen-slides">
        <c:forEach items="${topBigAdvertList}" var="t" begin="0" end="4" step="1" varStatus="status">
            <li style="background: url('${IMG_URL}${t.attachmentFilePath}') no-repeat center top">
                <a href="${t.bindSource}" target="_blank" title="${t.content}">&nbsp;</a >
            </li>
        </c:forEach>
    </ul>
    <div class="jfocus-trigeminy">
        <ul style="left: -776px; width: 2328px;">
            <li>
                <c:forEach items="${topGoods}" var="g" begin="0" end="2" step="1" varStatus="status">
                    <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" target="_blank" title="${g.name}" style="opacity: 1;">
                        <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                    </a>
                </c:forEach>
            </li>
            <li>
                <c:forEach items="${topGoods}" var="g" begin="3" end="5" step="1" varStatus="status">
                     <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" target="_blank" title="${g.name}" style="opacity: 1;">
                        <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                    </a> 
                </c:forEach>
            </li>
            <li>
                <c:forEach items="${topGoods}" var="g" begin="4" end="6" step="1" varStatus="status">
                     <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" target="_blank" title="${g.name}" style="opacity: 1;">
                        <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                    </a> 
                </c:forEach>
            </li>
        </ul>
        <div class="pagination"><span style="opacity: 0.4;"></span><span style="opacity: 1;"></span><span
                style="opacity: 0.4;"></span></div>
        <div class="arrow pre" style="opacity: 0;"></div>
        <div class="arrow next" style="opacity: 0;"></div>
    </div>
    <script type="text/javascript">
//         update_screen_focus();
    </script>
    <div class="right-sidebar">
        <div class="policy">
            <ul>
                <li class="b1">七天包退</li>
                <li class="b2">正品保障</li>
                <li class="b3">闪电发货</li>
            </ul>
        </div>
        <div class="groupbuy">
            <div class="title"><i>抢</i>近期抢购</div>
            <ul style="left: -210px; width: 420px;">
                <c:forEach items="${nearPanicBuying}" var="g">
                <li>
                    <dl style="background-image: url(${IMG_URL}${g.attachment})">
                        <dt>${g.name}</dt>
                        <dd class="price">
                        	<span class="groupbuy-price">¥<fmt:formatNumber value="${g.price}" pattern="#.##" /></span>
                        	<span class="buy-button">
                        		<a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">立即抢</a>
                        	</span>
                        </dd>
                        <dd class="time"><span class="sell">已售<em>${g.salesVolume}</em></span>
							<span class="time-remain" time_id="d">
								<em>15</em>天
								<em time_id="h">12</em>小时 
								<em time_id="m">57</em>分 
								<em time_id="s">18</em>秒 
							</span>
                        </dd>
                    </dl>
                </li>
                </c:forEach> 
            </ul>
            <div class="pagination"><span style="opacity: 0.4;"></span><span style="opacity: 1;"></span></div>
            <div class="arrow pre" style="opacity: 0;"></div>
            <div class="arrow next" style="opacity: 0;"></div>
        </div>
        <div class="proclamation" style="margin-left:-0.5px;">
            <ul class="tabs-nav">
                <li class="tabs-selected">
                    <h3>商城公告</h3>
                </li> 
                
            </ul>
            <div class="tabs-panel">
<!--             	<a href="http://10.58.135.8/shop/index.php?act=show_joinin&amp;op=index" title="申请商家入驻；已提交申请，可查看当前审核状态。" class="store-join-btn" target="_blank">&nbsp;</a> -->
<!--                 <a href="http://10.58.135.8/shop/index.php?act=seller_login&amp;op=show_login" target="_blank" -->
<!--                    class="store-join-help"><i class="icon-cog"></i>登录商家管理中心</a> -->
			<ul>
 				<c:forEach items="${articleContentList}" var="t" begin="0" end="4" step="1" varStatus="status">
 				<li>
 					<b>●</b>
 					<a href="${BASE_URL}/front/articleContent/helpCenterDetail?contentId=${t.contentId}&categoryId=${t.categoryId }" target="_blank" title="${t.title}">${t.title}</a >
 					</li>
  				</c:forEach>
  			</ul>
  			
            </div>
<!--             <div class="tabs-panel tabs-hide"> -->
<!--                 <ul class="mall-news"> -->
<!--                     <li><i></i><a target="_blank" href="http://www.33hao.com/" title="火爆销售中">火爆销售中 </a> -->
<!--                         <time>(2014-01-16)</time> -->
<!--                     </li> -->
<!--                     <li><i></i><a target="_blank" -->
<!--                                   href="http://10.58.135.8/shop/index.php?act=article&amp;op=show&amp;article_id=37" -->
<!--                                   title="如何扩充水印字体库">如何扩充水印字体库 </a> -->
<!--                         <time>(2014-01-16)</time> -->
<!--                     </li> -->
<!--                     <li><i></i><a target="_blank" -->
<!--                                   href="http://10.58.135.8/shop/index.php?act=article&amp;op=show&amp;article_id=41" -->
<!--                                   title="功能使用说明">功能使用说明 </a> -->
<!--                         <time>(2014-01-16)</time> -->
<!--                     </li> -->
<!--                     <li><i></i><a target="_blank" -->
<!--                                   href="http://10.58.135.8/shop/index.php?act=article&amp;op=show&amp;article_id=36" -->
<!--                                   title="管理功能说明">管理功能说明 </a> -->
<!--                         <time>(2014-01-16)</time> -->
<!--                     </li> -->
<!--                     <li><i></i><a target="_blank" -->
<!--                                   href="http://10.58.135.8/shop/index.php?act=article&amp;op=show&amp;article_id=38" -->
<!--                                   title="提示信息">提示信息 </a> -->
<!--                         <time>(2014-01-16)</time> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </div> -->
        </div>
    </div>
</div>
<!--HomeFocusLayout End-->

<div class="home-sale-layout wrapper">
    <div class="left-layout">
        <ul class="tabs-nav">
            <li class=""><i class="arrow"></i><h3>热卖商品</h3></li>
            <li class="tabs-selected"><i class="arrow"></i><h3>疯狂抢购</h3></li>
            <li class=""><i class="arrow"></i><h3>猜您喜欢</h3></li>
            <li class=""><i class="arrow"></i><h3>热评商品</h3></li>
            <li class=""><i class="arrow"></i><h3>新品上架</h3></li>
        </ul>
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul>
                <c:forEach items="${hotSales}" var="g">
                <li>
                    <dl>
                        <dt class="goods-name"><a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" title="${g.name}">
                            ${g.name}</a></dt>
                        <dd class="goods-thumb">
                            <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">
                                <img src="${IMG_URL}${g.image}" alt="${g.name}">
                            </a></dd>
                        <dd class="goods-price">商城价：<em><fmt:formatNumber value="${g.price}" pattern="￥#,#00.00#"/></em></dd>
                    </dl>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="tabs-panel sale-goods-list">
            <ul>
                <c:forEach items="${panicBuying}" var="g">
                <li>
                    <dl>
                        <dt class="goods-name"><a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" title="${g.name}">
                            ${g.name}</a></dt>
                        <dd class="goods-thumb">
                            <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">
                                <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                            </a></dd>
                        <dd class="goods-price">商城价：<em><fmt:formatNumber value="${g.price}" pattern="￥#,#00.00#"/></em></dd>
                    </dl>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul>
                <c:forEach items="${guessYouWouldLike}" var="g">
                <li>
                    <dl>
                        <dt class="goods-name"><a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" title="${g.name}">
                            ${g.name}</a></dt>
                        <dd class="goods-thumb">
                            <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">
                                <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                            </a></dd>
                        <dd class="goods-price">商城价：<em><fmt:formatNumber value="${g.price}" pattern="￥#,#00.00#"/></em></dd>
                    </dl>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul>
                <c:forEach items="${hotReview}" var="g">
                <li>
                    <dl>
                        <dt class="goods-name"><a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" title="${g.name}">
                            ${g.name}</a></dt>
                        <dd class="goods-thumb">
                            <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">
                                <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                            </a></dd>
                        <dd class="goods-price">商城价：<em><fmt:formatNumber value="${g.price}" pattern="￥#,#00.00#"/></em></dd>
                    </dl>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul>
                <c:forEach items="${newGoods}" var="g">
                <li>
                    <dl>
                        <dt class="goods-name"><a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" title="${g.name}">
                            ${g.name}</a></dt>
                        <dd class="goods-thumb">
                            <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">
                                <img src="${IMG_URL}${g.attachment}" alt="${g.name}">
                            </a></dd>
                        <dd class="goods-price">商城价：<em><fmt:formatNumber value="${g.price}" pattern="￥#,#00.00#"/></em></dd>
                    </dl>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="right-sidebar">
        <div class="title">
            <h3>限时折扣</h3>
        </div>
        <div id="saleDiscount" class="sale-discount">
            <ul style="left: -420px; width: 630px;">
                 <c:forEach items="${specialDiscount}" var="g">
                <li>
                    <dl>
                        <dt class="goods-name">${g.name}</dt>
                        <dd class="goods-thumb"><a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}"> <img src="${IMG_URL}${g.attachment}"></a></dd>
                        <dd class="goods-price">¥<fmt:formatNumber value="${g.price}" pattern="#.##" /> <span class="original">¥<fmt:formatNumber value="${g.price/7.8}" pattern="#.##" /></span></dd>
                        <dd class="goods-price-discount"><em>7.8折</em></dd>
                        <dd class="time-remain" count_down="1296474"><i></i><em time_id="d">5</em>天<em time_id="h">12</em>小时 <em time_id="m">57</em>分<em time_id="s">18</em>秒 </dd>
                        <dd class="goods-buy-btn"></dd>
                    </dl>
                </li>
                </c:forEach> 
            </ul>
            <div class="pagination"><span style="opacity: 0.4;"></span><span style="opacity: 0.4;"></span><span style="opacity: 1;"></span></div><div class="arrow pre" style="opacity: 0;"></div><div class="arrow next" style="opacity: 0;"></div></div>
    </div>
</div>
<div class="wrapper">
    <div class="mt10">
        <div class="mt10"><a href="" title="首页中上部banner"><img style="width:1200px;height:100px" border="0" src="${IMG_URL}${bannerAdvertList[0].attachmentFilePath}" alt=""></a></div>
    </div>
</div>

<!--StandardLayout Begin-->

<!-- <div class="home-standard-layout wrapper style-pink"> -->
<!--     <div class="left-sidebar"> -->
<!--         <div class="title"> -->
<!--             <div class="txt-type"> -->
<!--                 <span>1</span>                        <h2 title="女装男装">女装男装</h2> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="left-ads"> -->
<!--             <a href="" title="出游服装五折起" target="_blank"> -->
<!--                 <img src="http://localhost/data/upload/shop/editor/web-2-23.png?543" alt="出游服装五折起"> -->
<!--             </a> -->
<!--         </div> -->
<!--         <div class="recommend-classes"> -->
<!--             <ul> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=108" title="正装鞋" target="_blank">正装鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=16" title="卫衣" target="_blank">卫衣</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=110" title="凉鞋／沙滩鞋" target="_blank">凉鞋／沙滩鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=19" title="半身裙" target="_blank">半身裙</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=24" title="西服" target="_blank">西服</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=26" title="风衣" target="_blank">风衣</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=27" title="大衣" target="_blank">大衣</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=29" title="棉服" target="_blank">棉服</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=31" title="孕妇装" target="_blank">孕妇装</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=32" title="大码装" target="_blank">大码装</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=33" title="中老年装" target="_blank">中老年装</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=107" title="商务休闲鞋" target="_blank">商务休闲鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=109" title="休闲鞋" target="_blank">休闲鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=111" title="男靴" target="_blank">男靴</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=112" title="功能鞋" target="_blank">功能鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=113" title="拖鞋" target="_blank">拖鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=114" title="传统布鞋" target="_blank">传统布鞋</a></li> -->
<!--                 <li><a href="http://localhost/shop/index.php?act=search&amp;op=index&amp;cate_id=116" title="帆布鞋" target="_blank">帆布鞋</a></li> -->
<!--             </ul> -->
<!--         </div> -->
<!--     </div> -->
<!--     <div class="middle-layout"> -->
<!--         <ul class="tabs-nav"> -->
<!--             <li class="tabs-selected"><i class="arrow"></i><h3>冬季内衣</h3></li> -->
<!--             <li class=""><i class="arrow"></i><h3>女装春款</h3></li> -->
<!--         </ul> -->
<!--         <div class="tabs-panel middle-banner-style01 fade-img "> -->
<%--             <c:forEach items="${goodsList}" var="g" begin="0" end="8" step="1" varStatus="status"> --%>
<%--                 <c:if test="${status.index == 4}"> --%>
<%--                     <a href="" title="${g.name}" class="a1" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 5}"> --%>
<%--                     <a href="" title="${g.name}" class="a2" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 3}"> --%>
<%--                     <a href="" title="${g.name}" class="b1" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 6}"> --%>
<%--                     <a href="" title="${g.name}" class="c1" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 1}"> --%>
<%--                     <a href="" title="${g.name}" class="c2" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 2}"> --%>
<%--                     <a href="" title="${g.name}" class="d1" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 0}"> --%>
<%--                     <a href="" title="${g.name}" class="d2" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 7}"> --%>
<%--                     <a href="" title="${g.name}" class="d3" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${status.index == 8}"> --%>
<%--                     <a href="" title="${g.name}" class="d4" target="_blank" style="opacity: 1;"> --%>
<%--                         <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                     </a> -->
<%--                 </c:if> --%>
<%--             </c:forEach> --%>
<!--         </div> -->
<!--         <div class="tabs-panel middle-goods-list tabs-hide"> -->
<!--             <ul> -->
<%--                 <c:forEach items="${goodsList}" var="g" begin="0" end="7" step="1" varStatus="status"> --%>
<!--                 <li style="opacity: 0.9;"> -->
<!--                     <dl> -->
<%--                         <dt class="goods-name"><a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}" title="${g.name}"> --%>
<%--                             ${g.name}</a></dt> --%>
<!--                         <dd class="goods-thumb"> -->
<%--                             <a target="_blank" href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}"> --%>
<%--                                 <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> --%>
<!--                             </a></dd> -->
<%--                         <dd class="goods-price"><em>¥<fmt:formatNumber value="${g.price}" pattern="#.##" /></em> --%>
<%--                             <span class="original">¥<fmt:formatNumber value="${g.price}" pattern="#.##" /></span></dd> --%>
<!--                     </dl> -->
<!--                 </li> -->
<%--                 </c:forEach> --%>
<!--             </ul> -->
<!--         </div> -->
<!--     </div> -->
<!--     <div class="right-sidebar"> -->
<!--         <div class="title"></div> -->
<!--         <div class="recommend-brand"> -->
<!--             <ul> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=104" title="esprit" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398090828687339_sm.jpg" alt="esprit"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=103" title="ELLE HOME" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398090611386532_sm.jpg" alt="ELLE HOME"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=102" title="她他/tata" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398090459092275_sm.jpg" alt="她他/tata"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=99" title="梦特娇" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398089942879365_sm.jpg" alt="梦特娇"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=96" title="佐丹奴" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398089412399747_sm.jpg" alt="佐丹奴"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=90" title="金利来" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04397473042647991_sm.jpg" alt="金利来"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=84" title="阿迪达斯" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04397471910652190_sm.jpg" alt="阿迪达斯"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=85" title="猫人" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04397472152849925_sm.jpg" alt="猫人"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=79" title="justyle" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04397468710494742_sm.jpg" alt="justyle"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=116" title="Dior" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398099738566948_sm.jpg" alt="Dior"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=100" title="宝姿" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398090061006740_sm.jpg" alt="宝姿"></a> -->
<!--                 </li> -->
<!--                 <li style="opacity: 1;"> -->
<!--                     <a href="http://localhost/shop/index.php?act=brand&amp;op=list&amp;brand=95" title="李宁" target="_blank"> -->
<!--                         <img src="http://localhost/data/upload/shop/brand/04398089270610035_sm.jpg" alt="李宁"></a> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </div> -->
<!--         <div class="right-side-focus"> -->
<!--             <ul style="left: -212px; width: 636px;"> -->
<!--                 <li><a href="/goods/index/detail?content_id=1" title="ThinkPad E430" target="_blank"> -->
<!--                     <img src="http://10.58.132.41:8000/nodo/resources/front/images/index/test/renren.jpg" alt=""></a> -->
<!--                 </li> -->
<!--                 <li><a href="/goods/index/detail?content_id=14" title="TEST电脑" target="_blank"> -->
<!--                     <img src="http://10.58.132.41:8000/nodo/resources/front/images/index/test/ren.jpg" alt=""></a> -->
<!--                 </li> -->
<!--                 <li><a href="/goods/index/detail?content_id=15" title="测试电脑联想" target="_blank"> -->
<!--                     <img src="http://10.58.132.41:8000/nodo/resources/front/images/index/test/hua.jpg" alt=""></a> -->
<!--                 </li> -->
<!--             </ul> -->
<!--             <ul style="left: -212px; width: 636px;"> -->
<!--                 <li><a href="" title="" target="_blank"> -->
<!--                     <img src="http://10.58.132.41:8000/nodo/resources/front/images/index/test/renren.jpg" alt=""></a> -->
<!--                 </li> -->
<!--                 <li><a href="" title="" target="_blank"> -->
<!--                     <img src="http://10.58.132.41:8000/nodo/resources/front/images/index/test/ren.jpg" alt=""></a> -->
<!--                 </li> -->
<!--                 <li><a href="" title="" target="_blank"> -->
<!--                     <img src="http://10.58.132.41:8000/nodo/resources/front/images/index/test/hua.jpg" alt=""></a> -->
<!--                 </li> -->
<!--                 <c:forEach items="${goodsList}" var="g" begin="4" end="6" step="1" varStatus="status"> -->
<!-- 	                <li> -->
<!--                         <a href="" title="" target="_blank"> -->
<!--                             <img src="${IMG_URL}${g.attachment}" alt="${g.name}"> -->
<!--                         </a> -->
<!-- 	                </li> -->
<!--                 </c:forEach> -->
<!--             </ul> -->
<!--             <div class="pagination"><span style="opacity: 0.4;"></span><span style="opacity: 1;"></span><span style="opacity: 0.4;"></span></div><div class="arrow pre" style="opacity: 0;"></div><div class="arrow next" style="opacity: 0;"></div></div> -->
<!--     </div> -->
<!-- </div> -->

<div class="wrapper">
<!--     <div class="mt10"><a href="http://www.33hao.com" target="_blank" title="首页通栏"><img style="width:1200px;height:90px" border="0" src="http://10.58.135.8/data/upload/shop/adv/04716538681718544.png" alt="首页通栏"></a></div> -->
</div>


<!--StandardLayout End-->
<jsp:include page="wrapper.suffix_front.jsp"/>
<script src="${STATIC_URL}/front/modules/index/js/index.index.js"></script>
