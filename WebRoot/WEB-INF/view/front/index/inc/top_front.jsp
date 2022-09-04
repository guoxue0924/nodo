<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- .header -->
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<jsp:include page="main.top.jsp"/>
<!-- PublicHeadLayout Begin -->
<!-- 顶部广告展开效果-->
<!-- 顶部广告展开效果-->
<div class="header-wrap">
    <header class="public-head-layout">
        <h1 class="site-logo img">
        	<a href="${BASE_URL}/front/index/index">
        		<img src="${STATIC_URL}/front/images/front/logo.png" style="text-align: center" />
        	</a>
        </h1>

        <div class="head-app">
        		<img src="${STATIC_URL}/front/images/index/top_bg1.png" style="text-align: center" />
        	<!-- <span class="pic"></span> -->
            <%-- <div class="download-app">
                <div class="qrcode">
                	<img src="${STATIC_URL}/front/images/index/my_weixin.jpg">
                </div>
                <div class="hint">
                	<h4>扫描二维码</h4>
                	下载手机客户端
                </div>
                <div class="addurl">
                	<a href="http://v3.33hao.com/v3.apk" target="_blank"><i class="icon-android"></i>Android</a>
                    <a href="http://v3.33hao.com/v3.api" target="_blank"><i class="icon-apple"></i>iPhone</a>
                </div>
            </div> --%>
        </div>

        <div id="search" class="head-search-bar">
            <!--商品和店铺-->
            <ul class="tab">
                <li title="请输入您要搜索的商品关键字" act="search" class="current">商品</li>
            </ul>
            <form class="search-form" method="get" action="${BASE_URL}/front/goodsContent/search">
                <input type="hidden" value="search" id="search_act" name="act">
                <input placeholder="请输入您要搜索的商品关键字" name="keyword" id="keyword" type="text" class="input-text" value="${keyword}"
                       maxlength="60" x-webkit-speech="" lang="zh-CN" onwebkitspeechchange="foo()"
                       x-webkit-grammar="builtin:search">
                <input type="submit" id="button" value="搜索" class="input-submit">
            </form>
        </div>
        
        <div class="head-user-menu">
            <dl class="my-mall">
                <dt>
                	<span class="ico"></span>我的商城<i class="arrow"></i>
                </dt>
                <dd>
                    <div class="sub-title">
                        <h4>
                        	${loggedInUser.username}
<!--                             <div class="nc-grade-mini" style="cursor:pointer;" -->
<%--                                  onclick="javascript:go('${BASE_URL}/front/cas/index');"> --%>
<!--                                V0  -->
<!--                             </div> -->
                        </h4>
                        
                        <a href="${BASE_URL}/front/cas/index" class="arrow">我的用户中心<i></i></a>
                    </div>
                    <div class="user-centent-menu">
                        <ul>
                            <li>
                                <a href="${BASE_URL}/front/cas/message">站内消息(<span>0</span>)</a>
                            </li>
                            <li><a href="${BASE_URL}/front/bts/order" class="arrow">我的订单<i></i></a>
                            </li>
                            <li><a href="${BASE_URL}/front/cas/feedback">咨询回复(<span id="member_consult">0</span>)</a></li>
                            <li><a href="${BASE_URL}/front/cas/favorite" class="arrow">我的收藏<i></i></a></li>
                            <li><a href="${BASE_URL}/front/cas/coupon">代金券(<span id="member_voucher">0</span>)</a></li>
                            <li><a href="${BASE_URL}/front/casUserPoint/index" class="arrow">我的积分<i></i></a></li>
                        </ul>
                    </div>
<!--                     <div class="browse-history"> -->
<!--                         <div class="part-title"> -->
<!--                             <h4>最近浏览的商品</h4> -->
<!--                             <span style="float:right;"><a -->
<!--                                     href="">全部浏览历史</a></span> -->
<!--                         </div> -->
<!--                         <ul> -->
<%--                             <li class="no-goods"><img class="loading" src="${STATIC_URL}/front/images/index/loading.gif"></li> --%>
<!--                         </ul> -->
<!--                     </div> -->
                </dd>
            </dl>
            <dl class="my-cart">
                <dt><span class="ico"></span>购物车结算<i class="arrow"></i></dt>
                <dd>
                    <div class="sub-title">
                        <h4>最新加入的商品</h4>
                    </div>
                    <div class="incart-goods-box">
                        <div class="incart-goods"><img class="loading" src="${STATIC_URL}/front/images/index/loading.gif"></div>
                    </div>
                    <div class="checkout">
                        <span class="total-price">共<i>0</i>种商品</span><a href="${BASE_URL}/front/cart" class="btn-cart">结算购物车中的商品</a>
                    </div>
                </dd>
            </dl>
        </div>
    </header>
</div>
<!-- publicNavLayout Begin -->
<jsp:include page="main.side.jsp"/>
<!-- /.header -->