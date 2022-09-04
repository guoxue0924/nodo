<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../index/wrapper.prefix_front.jsp" />
<link href="${STATIC_URL}/plugins/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css" id="cssfile2">
<link href="${STATIC_URL}/front/modules/goods/css/goods.detail.css" rel="stylesheet" type="text/css">
<link href="${STATIC_URL}/front/modules/goods/css/home_goods.css" rel="stylesheet" type="text/css">
<style type="text/css">
    .ncs-goods-picture .levelB, .ncs-goods-picture .levelC {
        cursor: url(${STATIC_URL}/front/images/goods/zoom.cur), pointer;
    }

    .ncs-goods-picture .levelD {
        cursor: url(${STATIC_URL}/front/images/goods/hand.png), move \9;
    }
</style>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span>首页 > ${categoryUrl}</span>
    </div>
</div>

<div id="content" class="wrapper pr">
    <div class="ncs-detail">
        <!-- S 商品图片 -->
        <div id="ncs-goods-picture" class="ncs-goods-picture image_zoom">
            <div class="tb-booth tb-pic tb-s310">
                <a href="${IMG_URL}${attachments[0].filepath}"><img src="${IMG_URL}${attachments[0].filepath}" alt="" rel="${IMG_URL}${attachments[0].filepath}" class="jqzoom" /></a>
            </div>
            <ul class="tb-thumb" id="thumblist">
                <c:forEach items="${attachments}" var="a" begin="0" end="5" step="1" varStatus="status">
                	<li <c:if test="${status.index == 0}">class="tb-selected"</c:if>><div class="tb-pic tb-s40"><a href="#"><img width="40" height="40" src="${IMG_URL}${a.filepath}" mid="${IMG_URL}${a.filepath}" big="${IMG_URL}${a.filepath}"></a></div></li>
                </c:forEach>
            </ul>
        </div>
        
        <!-- S 商品基本信息 -->
        <div class="ncs-goods-summary">
            <div class="name">
                <h1>${goods.name}</h1>
            </div>
            <div class="ncs-meta">
                <!-- S 描述相符评分 -->
                <div class="rate">
                    <a href="#ncGoodsRate">商品评分</a>
                    <div class="raty" data-score="${rankAverage}" title="很满意" style="width: 100px;">
                        <input type="hidden" name="score" value="${rankAverage}" readonly="readonly">
                    </div>
                </div>
                <!-- E 描述相符评分 -->
                
                <!-- S 商品参考价格 -->
                <dl>
                    <dt>市&nbsp;场&nbsp;价：</dt>
                    <dd class="cost-price"><strong id="skuPriceMarket"><fmt:formatNumber value="${skuDetail.priceMarket}" pattern="￥#,#00.00#"/></strong></dd>
                </dl>
                <!-- E 商品参考价格 -->
                
                <!-- S 商品发布价格 -->
                <dl>
                    <dt>商&nbsp;城&nbsp;价：</dt>
                    <dd class="price">
<!--                         <span class="tag">限时抢购</span> -->
                        <strong id="skuPrice"><fmt:formatNumber value="${skuDetail.price}" pattern="￥#,#00.00#" /></strong><%-- <em id="skuPriceEm">(原售价：¥<fmt:formatNumber value="${skuDetail.price}" pattern="￥#,#00.00#" />)</em> --%>
                    </dd>
                </dl>
                <!-- E 商品发布价格 -->
            </div>
            <div class="ncs-plus">
                <!-- S 物流运费  预售商品不显示物流 -->
                <dl class="ncs-freight">
                    <dt>
                        <!-- 如果买家承担运费 -->
                        <!-- 如果使用了运费模板 -->
                        至　全国：
                    </dt>
                    <dd id="transport_price">
                        <span id="nc_kd">卖家承担运费</span>
                    </dd>
                    <dd style="color:red;display:none" id="loading_price">loading.....</dd>
                </dl>
                <!-- E 物流运费 --->
            </div>
            <div class="ncs-key">
                <!-- S 商品规格值-->
                <dl nctype="nc-spec">
                    <c:forEach items="${properties}" var="p" varStatus="status">
                        <dt>${p.labelName}</dt>
	                    <dd>
	                        <ul nctyle="ul_sign">
	                            <c:forEach items="${p.propertyValues}" var="v" varStatus="status">
	                            <li class="sp-img">
	                                <c:if test="${fn:contains(propertyValueIdList, v.propertyValueId)}">
		                                <a href="javascript:" data-param="${p.propertyId}-${v.propertyValueId}" class="hovered" data-param="" title="${v.propertyValue}">
		                                    <c:if test="${v.propertyImage != null && v.propertyImage != ''}">
                                                <img src="${IMG_URL}${v.propertyImage}">
                                            </c:if>
		                                    ${v.propertyValue}
		                                    <i></i>
		                                </a>
	                                </c:if>
	                                <c:if test="${!fn:contains(propertyValueIdList, v.propertyValueId)}">
                                        <a href="javascript:" data-param="${p.propertyId}-${v.propertyValueId}" data-param="" title="${v.propertyValue}">
                                            <c:if test="${v.propertyImage != null && v.propertyImage != ''}">
                                                <img src="${IMG_URL}${v.propertyImage}">
                                            </c:if>
                                            ${v.propertyValue}
                                            <i></i>
                                        </a>
                                    </c:if>
	                            </li>
	                            </c:forEach>
	                        </ul>
	                    </dd>
                    </c:forEach>
                </dl>
                <!-- E 商品规格值-->
                <!-- S 购买数量及库存 -->
                <dl>
                    <dt>购买数量：</dt>
                    <dd class="ncs-figure-input">
                        <input type="text" name="" id="quantity" value="1" size="3" maxlength="6" class="text w30">
                        <a href="javascript:void(0)" class="increase">+</a>
                        <a href="javascript:void(0)" class="decrease">-</a>
                        <span>(当前库存<em nctype="goods_stock" id="skuStockEm">${skuDetail.stock}</em>件)</span>
                    </dd>
                </dl>
                <!-- E 购买数量及库存 -->
            </div>
            <!-- S 购买按钮 -->
            <div class="ncs-btn"><!-- S 提示已选规格及库存不足无法购买 -->
                <div class="clear"></div>

                <!-- 立即购买-->
                <a href="javascript:void(0);" content-id="${skuId}" nctype="buynow_submit" class="buynow " title="立即购买">立即购买</a>
                <!-- 加入购物车-->
                <a href="javascript:void(0);" content-id="${skuId}" nctype="addcart_submit" class="addcart " title="添加购物车"><i class="icon-shopping-cart"></i>添加购物车</a>

                <!-- v3-b11 end-->

                <!-- S 加入购物车弹出提示框 -->
                <div class="ncs-cart-popup">
                    <dl>
                        <dt>成功添加到购物车<a title="关闭" onclick="$('.ncs-cart-popup').hide();">X</a></dt>
                        <dd class="btns">
                            <a href="javascript:void(0);" class="ncs-btn-mini ncs-btn-green" onclick="location.href='${BASE_URL}/front/cart'">查看购物车</a>
                        <a href="javascript:void(0);" class="ncs-btn-mini" value="" onclick="$('.ncs-cart-popup').hide();">继续购物</a>
                        </dd>
                    </dl>
                </div>
                <!-- E 加入购物车弹出提示框 -->

            </div>
            <!-- E 购买按钮 -->
            <!--E 商品信息 -->

        </div>
        <!-- E 商品图片及收藏分享 -->
        <div class="ncs-handle">
            <!-- S 收藏 -->
            <a href="javascript:collect_goods(${skuId}, '1');" id="collect" class="favorite"><i></i>收藏商品
                <span>(<em id="emfavoriteId" nctype="goods_collect">${favoriteCount}</em>)</span>
            </a>
            <!-- End --> </div>

        <div class="clear"></div>
    </div>
    <div class="ncs-goods-layout expanded">
        <div class="ncs-goods-main" id="main-nav-holder">
            <!-- S 优惠套装 -->
            <div class="ncs-promotion" id="nc-bundling" style="display:none;"></div>
            <!-- E 优惠套装 -->
            <div class="tabbar pngFix" id="main-nav">
                <div class="ncs-goods-title-nav">
                    <ul id="categorymenu">
                        <li class="current"><a id="tabGoodsIntro" href="#content">商品详情</a></li>
                        <li><a id="tabGoodsRate" href="#content">商品评价<em>()</em></a></li>
                        <li><a id="tabGoodsTraded" href="#content">销售记录<em>()</em></a></li>
                    </ul>
                    <div class="switch-bar"><a href="javascript:void(0)" id="fold">&nbsp;</a></div>
                </div>
            </div>
            <div class="ncs-intro">
                 <div class="content bd" id="ncGoodsIntro">
                     ${goods.body}
                 </div>
            </div>
            <!--商品评价begin-->
            <div class="ncs-comment">
                <div class="ncs-goods-title-bar hd">
                    <h4><a href="javascript:void(0);">商品评价</a></h4>
                </div>
                <div class="ncs-goods-info-content bd" id="ncGoodsRate">
                    <div class="top">
                        <div class="rate">
                            <p><strong>${goodCommentRank*100}</strong><sub>%</sub>好评</p>
                            <span>共有${totalUser}人参与评分</span></div>
                        <div class="percent">
                            <dl>
                                <dt>好评<em>(${goodComment})</em></dt>
                                <dd><i style="width: ${goodCommentRank*100}%"></i></dd>
                            </dl>
                            <dl>
                                <dt>中评<em>(${normalComment})</em></dt>
                                <dd><i style="width: ${normalCommentRank*100}%"></i></dd>
                            </dl>
                            <dl>
                                <dt>差评<em>(${badComment})</em></dt>
                                <dd><i style="width: ${badCommentRank*100}%"></i></dd>
                            </dl>
                        </div>
                        <div class="btns"><span>您可对已购商品进行评价</span>

                            <p><a href="${BASE_URL}/front/cas/comment" class="ncs-btn ncs-btn-red" target="_blank"><i class="icon-comment-alt"></i>评价商品</a>
                            </p>
                        </div>
                    </div>
                    <div class="ncs-goods-title-nav">
                        <ul id="comment_tab">
                            <li data-type="all" class="current"><a href="javascript:void(0);">商品评价(<c:if test="${comments_count == null}">0</c:if><c:if test="${comments_count != null}">${comments_count}</c:if>)</a></li>
                        </ul>
                    </div>
                    <!-- 商品评价内容部分 -->
                    <div id="goodseval" class="ncs-commend-main">
                        <div class="ncs-norecord hide">暂无符合条件的数据记录</div>
                        <center><div><ul id="commentPaginationBar" class="pagination-sm pagination"></ul></div></center>
                    </div>
                </div>
            </div>
            <!--商品评价end-->
            <!--销售记录begin-->
            <div class="ncg-salelog">
                <div class="ncs-goods-title-bar hd">
                    <h4><a href="javascript:void(0);">销售记录</a></h4>
                </div>
                <div class="ncs-goods-info-content bd" id="ncGoodsTraded">
                    <div class="top">
                        <div class="price">
                           	 商&nbsp;城&nbsp;价
                           	 <strong>
                           	 	<fmt:formatNumber value="${skuDetail.price}" pattern="￥#,#00.00#" />
                           	 </strong>元
                           	 <span>购买的价格不同可能是由于店铺往期促销活动引起的，详情可以咨询卖家</span>
                        </div>
                    </div> 
                    <!-- 成交记录内容部分 -->
                    <div id="salelog_demo" class="ncs-loading">

                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mt10">
                            <thead>
                            <tr>
                                <th class="w200">买家</th>
                                <th class="w100">购买价</th>
                                <th class="">购买数量</th>
                                <th class="w200">购买时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
	                            <tr>
	                                <td colspan="10" class="ncs-norecord hide">暂无符合条件的数据记录</td>
	                            </tr>
	                            <tr>
	                                <td colspan="10" class="ncs-norecord">
										<div><ul id="saleHistoryPaginationBar" class="pagination-sm pagination"></ul></div>
									</td>
	                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <!--销售记录end-->
        </div>
        <div class="ncs-sidebar">
            <div class="ncs-sidebar-container ncs-top-bar">
                <div class="title">
                    <h4>商品排行</h4>
                </div>
                <div class="content">
                    <ul class="ncs-top-tab pngFix">
                        <li id="hot_sales_tab" class="current"><a
                                href="">热销商品排行</a>
                        </li>
                        <li id="hot_collect_tab"><a
                                href="">热门个收藏商品</a>
                        </li>
                    </ul>
                    <div id="hot_sales_list" class="ncs-top-panel">
                        <ol>
                            <c:forEach items="${hotSalesList}" var="g">
                                <li>
                                    <dl>
                                        <dt>
                                            <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">${g.name}
                                            </a>
                                        </dt>
                                        <dd class="goods-pic">
                                            <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}"><span class="thumb size40">
                                            <i></i>
                                            <c:if test="${g.image != null && g.image != ''}">
                                               <img src="${IMG_URL}${g.image}" width="40"  height="40"></span></a>
                                            </c:if>
                                            <c:if test="${g.image == null || g.image == ''}">
                                               <img src="${STATIC_URL}/front/images/goods/nopic.jpg" width="40"  height="40"></span></a>
                                            </c:if>
                                            <p><span class="thumb size100">
                                                <i></i>
                                                <c:if test="${g.image != null && g.image != ''}">
                                                    <img src="${IMG_URL}${g.image}" title="${g.name}" width="100" height="100"><big></big>
                                                </c:if>
                                                <c:if test="${g.image == null || g.image == ''}">
                                                    <img src="${STATIC_URL}/front/images/goods/nopic.jpg" title="${g.name}" width="100" height="100"><big></big>
                                                </c:if>
                                                <small></small></span></p>
                                        </dd>
                                        <dd class="price pngFix"><fmt:formatNumber value="${g.price}" pattern="#.##" /></dd>
                                        <dd class="selled pngFix">售出：<strong><fmt:formatNumber value="${g.salesCount}" pattern="#" /></strong>笔</dd>
                                    </dl>
                                </li>
                            </c:forEach> 
                        </ol>
                    </div>
                    <div id="hot_collect_list" class="ncs-top-panel hide">
                        <ol>
                            <c:forEach items="${hotCollectList}" var="g">
                                <li>
                                    <dl>
                                        <dt>
                                            <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}">${g.name}</a>
                                        </dt>
                                        <dd class="goods-pic">
                                           <c:if test="${g.image != null && g.image != ''}">
                                            <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}"  title=""><span class="thumb size40"><i></i>
                                                <img src="${IMG_URL}${g.image}" width="40" height="40"></span></a>
                                            </c:if>
                                            <c:if test="${g.image == null || g.image == ''}">
                                               <a href="${BASE_URL}/front/goodsContent/detail?skuId=${g.skuId}"  title=""><span class="thumb size40"><i></i>
                                                <img src="${STATIC_URL}/front/images/goods/nopic.jpg" width="40" height="40"></span></a>
                                            </c:if>
                                            <p><span class="thumb size100"><i></i>
                                            <c:if test="${g.image != null && g.image != ''}">
                                                <img src="${IMG_URL}${g.image}" title="${g.name}" width="100" height="100"><big></big><small></small>
                                            </c:if>
                                            <c:if test="${g.image == null || g.image == ''}">
                                               <img src="${STATIC_URL}/front/images/goods/nopic.jpg" title="${g.name}" width="100" height="100"><big></big><small></small>
                                            </c:if>
                                            </span>
                                            </p>
                                        </dd>
                                        <dd class="price pngFix"><fmt:formatNumber value="${g.price}" pattern="#.##" /></dd>
                                        <dd class="collection pngFix">收藏人气：<strong><fmt:formatNumber value="${g.favoriteCount}" pattern="#" /></strong></dd>
                                    </dl>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="contentId" name="contentId" value="${goods.contentId}"/>
<input type="hidden" id="skuId" name="skuId" value="${skuId}"/>
<input type="hidden" id="skuValueId" name="skuValueId" value=""/>
<!-- <input type="hidden" id="contentSkus" name="contentSkus" value='${contentSkus}' /> -->
<input type="hidden" id="propertyIdList" name="propertyIdList" value='${propertyIdList}' />
<input type="hidden" id="propertyValueIdList" name="propertyValueIdList" value='${propertyValueIdList}' />
<form id="buynow_form" method="post" action="${BASE_URL}/front/bts/order/fastCheck">
    <input id="skuId" name="skuId" type="hidden">
    <input id="buytype" name="buytype" type="hidden" value="1"/>
</form>
<jsp:include page="../index/wrapper.suffix_front.jsp" />
<script src="${STATIC_URL}/plugins/jquery-raty/jquery.raty.min.js"></script>
<script src="${STATIC_URL}/plugins/jquery.nyroModal/custom.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-imagezoom/jquery.imagezoom.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-imagezoom/jquery.imagezoom.min.js"></script>
<script src="${STATIC_URL}/front/js/utils.js"></script>
<script src="${STATIC_URL}/front/modules/goods/js/goods.detail.js"></script>