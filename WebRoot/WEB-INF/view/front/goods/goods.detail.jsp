<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../index/wrapper.prefix_front.jsp" />
<link href="${STATIC_URL}/plugins/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css" id="cssfile2">

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper">
        <i class="icon-home"></i> <span><a href="/index">首页</a> </span><span class="arrow">&gt;</span> <span><a href="/goods/index/search?category_id=1">服饰鞋帽</a> </span><span class="arrow">&gt;</span> <span><a href="/goods/index/search?category_id=1">女装</a> </span><span class="arrow">&gt;</span> <span><a href="/goods/index/search?category_id=1">针织衫</a> </span><span class="arrow">&gt;</span> <span>新款 女款 拼接 不规则摆 长袖针织衫开衫 杏雨 白色</span>
    </div>
</div>
<link href="${STATIC_URL}/front/modules/goods/css/home_goods.css" rel="stylesheet" type="text/css">
<style type="text/css">
.ncs-goods-picture .levelB,.ncs-goods-picture .levelC {
    cursor: url(${STATIC_URL}/front/images/goods/zoom.cur), pointer;
}

.ncs-goods-picture .levelD {
    cursor: url(${STATIC_URL}/goods/img/hand.png), move \9;
}
</style>

<div id="content" class="wrapper pr">
    <div class="ncs-detail">
        <!-- S 商品图片 -->
        <div id="ncs-goods-picture" class="ncs-goods-picture image_zoom">
            <div class="gallery_wrap" style="height: 360px; width: 360px; position: relative; overflow: hidden;">
                <c:forEach items="${attachments}" var="attachment" varStatus="status">
                    <c:if test="${status.index == 0}">
                        <div class="gallery levelB" style="position: absolute; overflow: hidden; opacity: 1; height: 320px; width: 320px; left: 20px; top: 20px;">
                            <img src="${IMG_URL}${attachment.filepath}" alt="${status.index}" style="height: 320px; width: 320px;">
                        </div>
                    </c:if>
                    <c:if test="${status.index != 0}">
                        <div class="gallery gallery_mask" style="position: absolute; overflow: hidden; opacity: 1; height: 320px; width: 320px; left: 20px; top: 20px; display: none;">
                            <img src="${IMG_URL}${attachment.filepath}" alt="${status.index}" style="height: 320px; width: 320px;">
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="controller_wrap">
                <div class="controller">
                    <ul>
                        <c:forEach items="${attachments}" var="attachment" begin="0" end="5" step="1" varStatus="status">
                            <li><c:if test="${status.index == 0}">
                                    <a href="javascript:;" data-index="${status.index}" class="current"> <img src="${IMG_URL}${attachment.filepath}" height="60" width="60" alt=""> </a>
                                </c:if> <c:if test="${status.index != 0}">
                                    <a href="javascript:;" data-index="${status.index}"> <img src="${IMG_URL}${attachment.filepath}" height="60" width="60" alt=""> </a>
                                </c:if></li>
                        </c:forEach>
                    </ul>
                </div>
                <a href="javascript:;" class="prev"><span>«</span> </a><a href="javascript:;" class="next"><span>»</span> </a>
            </div>
            <div class="close_wrap">
                <a href="javascript:;" class="close" style="display: none;">×</a>
            </div>
        </div>
        <!-- S 商品基本信息 -->
        <div class="ncs-goods-summary">
            <div class="name">
                <h1>${goods.name}</h1>
                <strong>2014新款，特惠促销</strong>
            </div>
            <div class="ncs-meta">
                <div class="rate">
                    <!-- S 描述相符评分 -->
                    <a href="#ncGoodsRate">商品评分</a>

                    <div class="raty" data-score="5" title="很满意" style="width: 100px;">
                        <img src="" alt="1" title="很满意">&nbsp;<img src="" alt="2" title="很满意">&nbsp;<img src="http://10.58.137.126/data/resource/js/jquery.raty/img/star-on.png" alt="3" title="很满意">&nbsp;<img src="http://10.58.137.126/data/resource/js/jquery.raty/img/star-on.png" alt="4" title="很满意">&nbsp;<img src="http://10.58.137.126/data/resource/js/jquery.raty/img/star-on.png" alt="5" title="很满意"><input type="hidden" name="score" value="5" readonly="readonly">
                    </div>
                    <!-- E 描述相符评分 -->
                </div>

                <!-- S 商品参考价格 -->
                <dl>
                    <dt>市&nbsp;场&nbsp;价：</dt>
                    <dd class="cost-price">
                        <strong>¥${skus[0].priceMarket}</strong>
                    </dd>
                </dl>
                <!-- E 商品参考价格 -->
                <!-- S 商品发布价格 -->
                <dl>
                    <dt>商&nbsp;城&nbsp;价：</dt>
                    <dd class="price">
                        <span class="tag">月末折扣</span> <strong>¥${skus[0].price}</strong><em>(原售价：¥${skus[0].price})</em>
                    </dd>
                </dl>
                <!-- E 商品发布价格 -->
                <!-- S 促销 -->
                <dl>
                    <dt>促销信息：</dt>
                    <dd class="promotion-info">
                        <!-- S 限时折扣 -->
                        直降：¥41.00 <em>最低2件起</em> <span>挥泪大甩卖</span><br>
                        <!-- E 限时折扣  -->
                        <!-- S 抢购-->
                        <!-- E 抢购 -->
                        <!-- S 赠品 -->
                        <!-- E 赠品 -->
                    </dd>
                </dl>
                <!-- E 促销 -->
            </div>
            <div class="ncs-plus">
                <!-- S 物流运费  预售商品不显示物流 -->
                <dl class="ncs-freight">
                   <!--  <dt>
                        如果买家承担运费
                        如果使用了运费模板
                        至 全国：
                    </dt>
                    <dd id="transport_price">
                        <span id="nc_kd">卖家承担运费</span>
                    </dd> -->
                    <dd style="color:red;display:none" id="loading_price">loading.....</dd>
                </dl>
                <!-- E 物流运费 --->

                <!--送达时间 begin  -->
                <!--送达时间 end  -->

                <!-- S 赠品 -->
                <!-- S 赠品 -->
            </div>
            <div class="ncs-key">
                <!-- S 商品规格值-->
                <c:forEach items="${properties}" var="property">
                    <dl nctype="nc-spec" id="changeProperty">
                        <dt>${property.labelName}</dt>
                        <dd>
                            <ul nctyle="ul_sign">
                                <c:forEach items="${property.propertyValues}" var="propertyValue" varStatus="status">
                                    <c:if test="${status.index == 0}">
                                        <li class="sp-img">
                                            <a href="javascript:void(0);" class="hovered" data-param="" title="${propertyValue.propertyValue}">
                                                <c:if test="${propertyValue.propertyImage != null && propertyValue.propertyImage != ''}">
                                                    <img src="${IMG_URL}${propertyValue.propertyImage}">
                                                </c:if>
                                                ${propertyValue.propertyValue}<i></i> 
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${status.index != 0}">
                                        <li class="sp-img">
                                            <a href="javascript:void(0);" class="" data-param="" title="${propertyValue.propertyValue}">
                                                <c:if test="${propertyValue.propertyImage != null && propertyValue.propertyImage != ''}">
                                                    <img src="${IMG_URL}${propertyValue.propertyImage}">
                                                </c:if>
                                                ${propertyValue.propertyValue}<i></i>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </dd>
                    </dl>
                </c:forEach>
                <!-- E 商品规格值-->
                <!-- S 购买数量及库存 -->
                <dl>
                    <dt>购买数量：</dt>
                    <dd class="ncs-figure-input">

                        <input type="text" name="" id="quantity" value="1" size="3" maxlength="6" class="text w30"> <a href="javascript:void(0)" class="increase">+</a><a href="javascript:void(0)" class="decrease">-</a> <span>(当前库存<em nctype="goods_stock">${skus[0].stock}</em>件 <!-- 虚拟商品限购数 --> )</span>
                    </dd>
                </dl>
                <!-- E 购买数量及库存 -->
            </div>
            <!-- S 购买按钮 -->
            <div class="ncs-btn">
                <!-- S 提示已选规格及库存不足无法购买 -->
                <div nctype="goods_prompt" class="ncs-point">
                    <span class="yes">已选择 <strong>js获取</strong> </span>
                </div>
                <!-- E 提示已选规格及库存不足无法购买 -->
                <!-- S到货通知 -->
                <!-- E到货通知 -->
                <div class="clear"></div>

                <!-- 预约 -->


                <!-- v3-b11 限制购买-->
                <!-- 立即购买-->
                <a href="javascript:void(0);" nctype="buynow_submit" class="buynow " title="立即购买">立即购买</a>
                <!-- 加入购物车-->
                <a href="javascript:void(0);" nctype="addcart_submit" class="addcart " title="添加购物车"><i class="icon-shopping-cart"></i>添加购物车</a>

                <!-- v3-b11 end-->

                <!-- S 加入购物车弹出提示框 -->
                <div class="ncs-cart-popup">
                    <dl>
                        <dt>
                            成功添加到购物车<a title="关闭" onclick="">X</a>
                        </dt>
                        <dd class="btns">
                            <a href="javascript:void(0);" class="ncs-btn-mini ncs-btn-green" onclick="location.href='BASE_URL + /front/cart'">查看购物车</a> <a href="javascript:void(0);" class="ncs-btn-mini" value="" onclick="">继续购物</a>
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
            <!-- S 分享 -->
            <a href="javascript:void(0);" class="share" nc_type="sharegoods" data-param=""><i></i>分享<span>(<em nc_type="sharecount_52">0)</em> </span> </a>
            <!-- S 收藏 -->
            <a href="javascript:collect_goods('52','count','goods_collect');" class="favorite"><i></i>收藏商品<span>(<em nctype="goods_collect">0</em>)</span> </a>
            <!-- S 对比 -->
            <a href="javascript:void(0);" class="compare" nc_type="compare_52" data-param=""><i></i>加入对比</a>
            <!-- S 举报 -->
            <a href="index.php?act=member_inform&amp;op=inform_submit&amp;goods_id=52" title="举&nbsp;报" class="inform"><i></i>举&nbsp;报</a>
            <!-- End -->
        </div>
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
                        <li class="current"><a id="tabGoodsIntro" href="#content">商品详情</a>
                        </li>
                        <li><a id="tabGoodsRate" href="#content">商品评价<em>(0)</em> </a>
                        </li>
                        <li><a id="tabGoodsTraded" href="#content">销售记录<em>(2)</em> </a>
                        </li>
                        <li><a id="tabGuestbook" href="#content">购买咨询</a>
                        </li>
                    </ul>
                    <div class="switch-bar">
                        <a href="javascript:void(0)" id="fold">&nbsp;</a>
                    </div>
                </div>
            </div>
            <div class="ncs-intro">
                <div class="content bd" id="ncGoodsIntro">

                    <!--S 满就送 -->
                    <!--E 满就送 -->
                    <ul class="nc-goods-sort">
                        <li>商家货号：</li>
                        <li>品牌：挪巍</li>
                        <li>款式：蝙蝠衫</li>
                        <li>材质：棉</li>
                        <li>价格：0-99</li>
                        <li>袖长：长袖</li>
                        <li>风格：百搭</li>
                    </ul>
                    <div class="ncs-goods-info-content">
                        <div class="default">
                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" title="温馨提示.jpg" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2HRYjXfhXXXXXXXXX_%21%211124754276.jpg"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2jUjiXn0aXXXXXXXX_%21%211124754276.jpg?4caa155c10bcc783150746582de00275">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" id="ids-tag-m-5" class="ke_anchor" src="http://a.tbcdn.cn/kissy/1.0.0/build/imglazyload/spaceball.gif"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2CEZIXelXXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td style="height: 30px; width: 26px;">？</td>
                                        <td align="left" valign="top" style="width: 20px;"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2HRYjXfhXXXXXXXXX_%21%211124754276.jpg"></td>
                                        <td style="font-size:14px;font-weight:bold;">51130454 杏雨 清新色系花边拼接长袖针织衫</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="height: 30px; width: 46px;">？</td>
                                        <td style="font-size:12px;">设计点：精巧圆领（两侧开小口） /插肩袖 / 网布绣花花边拼接 / 单排扣门襟 / 甜美糖果色纽扣 / 百搭长袖 / 罗纹袖口 / 高腰分割效果 / 褶皱装饰 / 不规则下摆 /轻薄针织衫</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2uht5XlBdXXXXXXXX_%21%211124754276.jpg"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2CVgIXdlaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2OncIXgtXXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2iV7IXn8XXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2YVAIXdNaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2I7kGXhlaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2e9EHXclaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2ytgHXjxaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2qJsIXapaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2VaMJXbFXXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2TvZIXihXXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2Su3IXkBXXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T28_.HXfdaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2t9gHXd0aXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2aHkJXb4XXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" id="ids-tag-m-4" class="ke_anchor" src="http://a.tbcdn.cn/kissy/1.0.0/build/imglazyload/spaceball.gif"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2.kjjXjXXXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2TDm1XctbXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table style="color:#292929;font-size:12px;font-weight:bold;" class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0" width="685">
                                <tbody>
                                    <tr>
                                        <td align="right" style="width: 72px;">产品款号：</td>
                                        <td style="width: 322px;">51130454</td>
                                        <td align="right" style="width: 58px;">柔软：</td>
                                        <td valign="center" style="width: 233px;">
                                            <ul class="list-paddingleft-2">
                                                <li>
                                                    <p>
                                                        <span style="background-color:#ffffff;color:#000000;">柔软</span>
                                                    </p></li>
                                                <li>
                                                    <p>
                                                        <span style="background-color:#636363;color:#ffffff;">适中</span>
                                                    </p></li>
                                                <li>
                                                    <p>偏硬</p></li>
                                            </ul></td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="width: 72px;">产品昵称：</td>
                                        <td style="width: 322px;">杏雨</td>
                                        <td align="right" style="width: 58px;">厚度：</td>
                                        <td style="width: 233px;">
                                            <ul class="list-paddingleft-2">
                                                <li>
                                                    <p>
                                                        <span style="background-color:#ffffff;color:#000000;">加厚</span>
                                                    </p></li>
                                                <li>
                                                    <p>厚</p></li>
                                                <li>
                                                    <p>
                                                        <span style="background-color:#636363;color:#ffffff;">适中</span>
                                                    </p></li>
                                                <li>
                                                    <p>薄款</p></li>
                                            </ul></td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="width: 72px;">产品颜色：</td>
                                        <td style="width: 322px;">粉色 紫色 白色</td>
                                        <td align="right" style="width: 58px;">弹力：</td>
                                        <td style="width: 233px;">
                                            <ul class="list-paddingleft-2">
                                                <li>
                                                    <p>
                                                        <span style="background-color:#ffffff;color:#000000;">超弹</span>
                                                    </p></li>
                                                <li>
                                                    <p>
                                                        <span style="background-color:#626262;color:#ffffff;">弹力</span>
                                                    </p></li>
                                                <li>
                                                    <p>微弹</p></li>
                                                <li>
                                                    <p>无弹力</p></li>
                                            </ul></td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="width: 72px;">产品成份：</td>
                                        <td style="width: 322px;">55%聚丙烯腈纤维45%棉</td>
                                        <td align="right" style="width: 58px;">版型：</td>
                                        <td style="width: 233px;">
                                            <ul class="list-paddingleft-2">
                                                <li>
                                                    <p>
                                                        <span style="background-color:#ffffff;color:#000000;">修身</span>
                                                    </p></li>
                                                <li>
                                                    <p>直筒</p></li>
                                                <li>
                                                    <p>
                                                        <span style="background-color:#6b6b6b;color:#ffffff;">宽松</span>
                                                    </p></li>
                                                <li>
                                                    <p>适中</p></li>
                                            </ul></td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="width: 72px;">克 重：</td>
                                        <td style="width: 322px;">0.156KG</td>
                                        <td align="right" style="width: 58px;">？</td>
                                        <td style="width: 233px;">？</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/228784630/T25.VBXdpOXXXXXXXX_%21%21228784630.jpg" style="text-align: absMiddle;"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2DLAHXcXaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T20hcIXe0XXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2DQjiXhFaXXXXXXXX_%21%211124754276.jpg?82c0a15cd9b4e1656ddf9534acc87ac8"></td>
                                        <td>
                                            <p>① 精致开口圆领</p></td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>小巧精致的圆领，两侧插肩接缝处添加小小开口设计，趣味感十足</p>
                            <table class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <p>② 雅致花边拼接</p></td>
                                        <td><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2LO16XeXbXXXXXXXX_%21%211124754276.jpg?02e8eefb6edde3e53093aac4a2b6f462"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>肩袖处拼接白色羽毛状绣花网眼布，在清新色彩上更显雅致</p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2BQL6XeRbXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2ZqMHXmRaXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2DQjiXhFaXXXXXXXX_%21%211124754276.jpg?82c0a15cd9b4e1656ddf9534acc87ac8"></td>
                                        <td>
                                            <p>③ 糖果色纽扣</p></td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>单排扣门襟，纽扣与衣服同一色系，色彩更加鲜亮活泼，非常俏皮可爱</p>
                            <table class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <p>④ 不规则下摆</p></td>
                                        <td><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2LO16XeXbXXXXXXXX_%21%211124754276.jpg?02e8eefb6edde3e53093aac4a2b6f462"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>不要中规中矩，优雅独特的气质跳跃而出</p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2JakIXo4XXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2fIgGXjhaXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2B59pXeVcXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table style="font-family:微软雅黑;color:#000000;font-size:12px;" class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td rowspan="2" style="width: 25px;">？</td>
                                        <td style="height: 50px; width: 20px;"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2HRYjXfhXXXXXXXXX_%21%211124754276.jpg" height="13" width="14"></td>
                                        <td style="width: 323px;">
                                            <p>素雅绣花网眼布拼接</p></td>
                                        <td rowspan="2" style="width: 25px;">？</td>
                                        <td style="width: 20px;"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2HRYjXfhXXXXXXXXX_%21%211124754276.jpg" height="13" width="14"></td>
                                        <td style="width: 324px;">
                                            <p>柔和亲肤腈棉材质</p></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" valign="top" style="width: 343px;">
                                            <p>肩袖处拼接绣花网眼布，使用单色线绣法，用色淡雅，纹样精美，做工精致。由于绣在网布之上，使网布绣花看起来接近蕾丝的质地，古典婉约，并且兼具蕾丝的甜美与性感。</p></td>
                                        <td colspan="2" valign="top" style="width: 344px;">
                                            <p>腈棉是聚丙烯腈纤维和棉的混纺物。拥有聚丙烯腈纤维弹性好、蓬松卷曲而柔软的特点，柔软蓬松，易染色、色泽鲜艳、耐光、抗菌，不怕虫蛀等。同时结合棉吸湿性好，手感柔软、卫生舒适、光泽柔和、有自然美感等贴点，穿着更加柔和亲肤。</p></td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://a.tbcdn.cn/kissy/1.0.0/build/imglazyload/spaceball.gif">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2XXwgXe0aXXXXXXXX_%21%211124754276.jpg"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2.fJ8XktcXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img04.taobaocdn.com/imgextra/i4/1124754276/T2I5cgXaRaXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table style="font-size:12px;" class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0" width="460">
                                <tbody>
                                    <tr>
                                        <td style="height: 27px;"><strong>尺码</strong></td>
                                        <td><strong>身长</strong></td>
                                        <td><strong>胸围</strong></td>
                                        <td><strong>摆围</strong></td>
                                        <td><strong>袖长</strong></td>
                                        <td><strong>袖肥</strong></td>
                                        <td><strong>袖口</strong></td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px;">S</td>
                                        <td>58</td>
                                        <td>84</td>
                                        <td>138</td>
                                        <td>69</td>
                                        <td>26</td>
                                        <td>17</td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px;">M</td>
                                        <td>60</td>
                                        <td>88</td>
                                        <td>142</td>
                                        <td>70.5</td>
                                        <td>27</td>
                                        <td>18</td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px;">
                                            <p>L</p></td>
                                        <td>62</td>
                                        <td>92</td>
                                        <td>146</td>
                                        <td>72</td>
                                        <td>28</td>
                                        <td>19</td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px;">XL</td>
                                        <td>64</td>
                                        <td>96</td>
                                        <td>150</td>
                                        <td>73.5</td>
                                        <td>29</td>
                                        <td>20</td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px;">XXL</td>
                                        <td>66</td>
                                        <td>100</td>
                                        <td>154</td>
                                        <td>75</td>
                                        <td>30</td>
                                        <td>21</td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px;">-</td>
                                        <td>-</td>
                                        <td>-</td>
                                        <td>-</td>
                                        <td>-</td>
                                        <td>-</td>
                                        <td>-</td>
                                        <td>？</td>
                                        <td>？</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>由于尺码是纯手工测量所以难免存在1CM-3CM，请精灵们谅解。</p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" id="ids-tag-m-2" class="ke_anchor" src="http://a.tbcdn.cn/kissy/1.0.0/build/imglazyload/spaceball.gif"><img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img03.taobaocdn.com/imgextra/i3/1124754276/T2id2jXctaXXXXXXXX_%21%211124754276.jpg">
                            </p>
                            <table style="font-size:12px;" class="ke-zeroborder" border="0" cellpadding="0" cellspacing="0" width="690">
                                <tbody>
                                    <tr>
                                        <td style="height: 27px; width: 80px;">模特橙子</td>
                                        <td style="width: 70px;">身高168CM</td>
                                        <td style="width: 70px;">体重45KG</td>
                                        <td style="width: 120px;">三围： 81/57/82/</td>
                                        <td style="width: 350px;">肩宽 37CM 穿S码（裤子穿25码）</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px; width: 80px;">模特田心</td>
                                        <td style="width: 70px;">身高165CM</td>
                                        <td style="width: 70px;">体重45KG</td>
                                        <td style="width: 120px;">三围： 80/58.5/83/</td>
                                        <td style="width: 350px;">肩宽 37CM 穿S码（裤子穿25码）</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px; width: 80px;">试衣模特</td>
                                        <td style="width: 70px;">身高**CM</td>
                                        <td style="width: 70px;">体重**KG</td>
                                        <td style="width: 120px;">三围： **/**/**/</td>
                                        <td style="width: 350px;">平时穿M码/裤子26 码 此款***码 合适</td>
                                    </tr>
                                    <tr>
                                        <td style="height: 27px; width: 80px;">试衣模特</td>
                                        <td style="width: 70px;">身高**CM</td>
                                        <td style="width: 70px;">体重**KG</td>
                                        <td style="width: 120px;">三围： **/**/**/</td>
                                        <td style="width: 350px;">平时穿L码/裤子29 码 此款***码 合适</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img01.taobaocdn.com/imgextra/i1/1124754276/T2DIsIXoXXXXXXXXXX_%21%211124754276.jpg">
                            </p>

                            <p>
                                <img alt="裂帛 裂帛新款 女 拼接 不规则摆 长袖针织衫开衫51130454杏雨" src="http://img02.taobaocdn.com/imgextra/i2/1124754276/T2YEDiXmVaXXXXXXXX_%21%211124754276.jpg?2bb287f60d61e3f3159f1bbaa3b7e3c6">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ncs-comment">
                <div class="ncs-goods-title-bar hd">
                    <h4>
                        <a href="javascript:void(0);">商品评价</a>
                    </h4>
                </div>
                <div class="ncs-goods-info-content bd" id="ncGoodsRate">
                    <div class="top">
                        <div class="rate">
                            <p>
                                <strong>100</strong><sub>%</sub>好评
                            </p>
                            <span>共有0人参与评分</span>
                        </div>
                        <div class="percent">
                            <dl>
                                <dt>
                                    好评<em>(100%)</em>
                                </dt>
                                <dd>
                                    <i style="width: 100%"></i>
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    中评<em>(0%)</em>
                                </dt>
                                <dd>
                                    <i style="width: 0%"></i>
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    差评<em>(0%)</em>
                                </dt>
                                <dd>
                                    <i style="width: 0%"></i>
                                </dd>
                            </dl>
                        </div>
                        <div class="btns">
                            <span>您可对已购商品进行评价</span>

                            <p>
                                <a href="http://10.58.137.126/shop/index.php?act=member_order&amp;op=index" class="ncs-btn ncs-btn-red" target="_blank"><i class="icon-comment-alt"></i>评价商品</a>
                            </p>
                        </div>
                    </div>
                    <div class="ncs-goods-title-nav">
                        <ul id="comment_tab">
                            <li data-type="all" class="current"><a href="javascript:void(0);">商品评价(0)</a>
                            </li>
                            <li data-type="1"><a href="javascript:void(0);">好评(0)</a>
                            </li>
                            <li data-type="2"><a href="javascript:void(0);">中评(0)</a>
                            </li>
                            <li data-type="3"><a href="javascript:void(0);">差评(0)</a>
                            </li>
                        </ul>
                    </div>
                    <!-- 商品评价内容部分 -->
                    <div id="goodseval" class="ncs-commend-main">
                        <div class="ncs-norecord">暂无符合条件的数据记录</div>

                    </div>
                </div>
            </div>
            <div class="ncg-salelog">
                <div class="ncs-goods-title-bar hd">
                    <h4>
                        <a href="javascript:void(0);">销售记录</a>
                    </h4>
                </div>
                <div class="ncs-goods-info-content bd" id="ncGoodsTraded">
                    <div class="top">
                        <div class="price">
                            商&nbsp;城&nbsp;价<strong>99.00</strong>元<span>购买的价格不同可能是由于店铺往期促销活动引起的，详情可以咨询卖家</span>
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
                                <tr>
                                    <td colspan="10" class="ncs-norecord">暂无符合条件的数据记录</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="ncs-consult">
                <div class="ncs-goods-title-bar hd">
                    <h4>
                        <a href="javascript:void(0);">购买咨询</a>
                    </h4>
                </div>
                <div class="ncs-goods-info-content bd" id="ncGuestbook">
                    <!-- 咨询留言内容部分 -->
                    <div id="consulting_demo" class="ncs-loading">

                        <div class="top" style="overflow: hidden;">
                            <div class="ncs-cosult-tips">
                                <i></i>

                                <p>因厂家更改商品包装、场地、附配件等不做提前通知，且每位咨询者购买、提问时间等不同。为此，客服给到的回复仅对提问者3天内有效，其他网友仅供参考！给您带来的不变还请谅解，谢谢！</p>
                            </div>
                            <div class="ncs-cosult-askbtn">
                                <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=consulting_list&amp;goods_id=52#askQuestion" target="_blank" class="ncs-btn ncs-btn-red">我要提问</a>
                            </div>
                        </div>
                        <div class="ncs-goods-title-nav">
                            <ul id="consulting_tab">
                                <li class="current"><a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=consulting&amp;goods_id=52&amp;store_id=1">全部</a></li>
                                <li class=""><a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=consulting&amp;goods_id=52&amp;ctid=1&amp;store_id=1">商品咨询</a></li>
                                <li class=""><a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=consulting&amp;goods_id=52&amp;ctid=2&amp;store_id=1">支付问题</a></li>
                                <li class=""><a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=consulting&amp;goods_id=52&amp;ctid=3&amp;store_id=1">发票及保修</a></li>
                                <li class=""><a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=consulting&amp;goods_id=52&amp;ctid=4&amp;store_id=1">促销及赠品</a></li>
                            </ul>
                        </div>
                        <div class="ncs-cosult-main">
                            <div class="ncs-norecord">还没有咨询内容</div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="ncs-recommend">
                <div class="title">
                    <h4>推荐商品</h4>
                </div>
                <div class="content">
                    <ul>
                        <li>
                            <dl>
                                <dt class="goods-name">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=42" target="_blank" title="">2014春款打底毛衫拼色毛衣 长袖套头针织衫 莺 蓝色<em></em> </a>
                                </dt>
                                <dd class="goods-pic">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=42" target="_blank" title=""><img src="" alt="2014春款打底毛衫拼色毛衣 长袖套头针织衫 莺 蓝色"> </a>
                                </dd>
                                <dd class="goods-price">¥179.00</dd>
                            </dl></li>
                        <li>
                            <dl>
                                <dt class="goods-name">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=38" target="_blank" title="新款特惠">正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 蓝色<em>新款特惠</em> </a>
                                </dt>
                                <dd class="goods-pic">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=38" target="_blank" title="新款特惠"><img src="" alt="正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 蓝色"> </a>
                                </dd>
                                <dd class="goods-price">¥189.00</dd>
                            </dl></li>
                        <li>
                            <dl>
                                <dt class="goods-name">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=46" target="_blank" title="">春装 披肩式 超短款 针织 衫开衫 女装 青鸟 绿色<em></em> </a>
                                </dt>
                                <dd class="goods-pic">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=46" target="_blank" title=""><img src="" alt="春装 披肩式 超短款 针织 衫开衫 女装 青鸟 绿色"> </a>
                                </dd>
                                <dd class="goods-price">¥129.00</dd>
                            </dl></li>
                        <li>
                            <dl>
                                <dt class="goods-name">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=232" target="_blank" title="">中华老字号 东阿阿胶桃花姬阿胶糕300g<em></em> </a>
                                </dt>
                                <dd class="goods-pic">
                                    <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=232" target="_blank" title=""><img src="" alt="中华老字号 东阿阿胶桃花姬阿胶糕300g"> </a>
                                </dd>
                                <dd class="goods-price">¥150.00</dd>
                            </dl></li>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <div class="ncs-sidebar">
            <div class="ncs-sidebar-container">
                <div class="title">
                    <h4>商品二维码</h4>
                </div>
                <div class="content">
                    <div class="ncs-goods-code">
                        <p>
                            <img src="" title="商品原始地址：http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=52">
                        </p>
                        <span class="ncs-goods-code-note"><i></i>扫描二维码，手机查看分享</span>
                    </div>
                </div>
            </div>
            <div class="ncs-sidebar-container ncs-class-bar">
                <div class="title">
                    <h4>商品分类</h4>
                </div>
                <div class="content">
                    <p>
                        <span><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1&amp;key=1&amp;order=2">按新品</a> </span><span><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1&amp;key=2&amp;order=2">按价格</a> </span><span><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1&amp;key=3&amp;order=2">按销量</a> </span><span><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1&amp;key=5&amp;order=2">按人气</a> </span>
                    </p>

                    <div class="ncs-search">
                        <form id="" name="searchShop" method="get" action="index.php">
                            <input type="hidden" name="act" value="show_store"> <input type="hidden" name="op" value="goods_all"> <input type="hidden" name="store_id" value="1"> <input type="text" class="text w120" name="inkeyword" value="" placeholder="搜索店内商品"> <a href="javascript:document.searchShop.submit();" class="ncs-btn">搜索</a>
                        </form>
                    </div>
                    <ul class="ncs-submenu">
                        <li><span class="ico-none"><em>-</em> </span><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1">全部商品</a></li>
                    </ul>

                </div>
            </div>
            <div class="ncs-sidebar-container ncs-top-bar">
                <div class="title">
                    <h4>商品排行</h4>
                </div>
                <div class="content">
                    <ul class="ncs-top-tab pngFix">
                        <li id="hot_sales_tab" class="current"><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1&amp;key=3&amp;order=2">热销商品排行</a></li>
                        <li id="hot_collect_tab"><a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1&amp;key=4&amp;order=2">热门收藏排行</a></li>
                    </ul>
                    <div id="hot_sales_list" class="ncs-top-panel">
                        <ol>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=231">至尊金奖 法国原瓶进口AOC红酒 任选一箱 红沙城堡红葡萄酒 原装进口</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=231"><span class="thumb size40"><i></i><img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="至尊金奖 法国原瓶进口AOC红酒 任选一箱 红沙城堡红葡萄酒 原装进口" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">228.00</dd>
                                    <dd class="selled pngFix">
                                        售出：<strong>12</strong>笔
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=232">中华老字号 东阿阿胶桃花姬阿胶糕300g</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=232"><span class="thumb size40"><i></i><img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="中华老字号 东阿阿胶桃花姬阿胶糕300g" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">95.00</dd>
                                    <dd class="selled pngFix">
                                        售出：<strong>2</strong>笔
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=48">春装 披肩式 超短款 针织 衫开衫 女装 青鸟 蓝色</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=48"><span class="thumb size40"><i></i><img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="春装 披肩式 超短款 针织 衫开衫 女装 青鸟 蓝色" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">129.00</dd>
                                    <dd class="selled pngFix">
                                        售出：<strong>1</strong>笔
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=49">春装 披肩式 超短款 针织 衫开衫 女装 青鸟 黑色</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=49"><span class="thumb size40"><i></i><img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="春装 披肩式 超短款 针织 衫开衫 女装 青鸟 黑色" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">100.00</dd>
                                    <dd class="selled pngFix">
                                        售出：<strong>1</strong>笔
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=52">新款 女款 拼接 不规则摆 长袖针织衫开衫 杏雨 白色</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=52"><span class="thumb size40"><i></i><img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="新款 女款 拼接 不规则摆 长袖针织衫开衫 杏雨 白色" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">58.00</dd>
                                    <dd class="selled pngFix">
                                        售出：<strong>1</strong>笔
                                    </dd>
                                </dl></li>
                        </ol>
                    </div>
                    <div id="hot_collect_list" class="ncs-top-panel hide">
                        <ol>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=232">中华老字号 东阿阿胶桃花姬阿胶糕300g</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=232" title=""><span class="thumb size40"><i></i> <img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="中华老字号 东阿阿胶桃花姬阿胶糕300g" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">95.00</dd>
                                    <dd class="collection pngFix">
                                        收藏人气：<strong>1</strong>
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=38">正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 蓝色</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=38" title=""><span class="thumb size40"><i></i> <img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 蓝色" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">158.00</dd>
                                    <dd class="collection pngFix">
                                        收藏人气：<strong>0</strong>
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=39">正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 绿色</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=39" title=""><span class="thumb size40"><i></i> <img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 绿色" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">189.00</dd>
                                    <dd class="collection pngFix">
                                        收藏人气：<strong>0</strong>
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=40">正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 梅红</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=40" title=""><span class="thumb size40"><i></i> <img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 梅红" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">189.00</dd>
                                    <dd class="collection pngFix">
                                        收藏人气：<strong>0</strong>
                                    </dd>
                                </dl></li>
                            <li>
                                <dl>
                                    <dt>
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=41">正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 黑色</a>
                                    </dt>
                                    <dd class="goods-pic">
                                        <a href="http://10.58.137.126/shop/index.php?act=goods&amp;op=index&amp;goods_id=41" title=""><span class="thumb size40"><i></i> <img src="" onload="javascript:DrawImage(this,40,40);" width="40" height="40"> </span> </a>

                                        <p>
                                            <span class="thumb size100"><i></i><img src="" onload="javascript:DrawImage(this,100,100);" title="正品 2014春装新款 女 绣花针织衫 开衫外套浮桑初 黑色" width="100" height="100"><big></big><small></small> </span>
                                        </p>
                                    </dd>
                                    <dd class="price pngFix">189.00</dd>
                                    <dd class="collection pngFix">
                                        收藏人气：<strong>0</strong>
                                    </dd>
                                </dl></li>
                        </ol>
                    </div>
                    <p>
                        <a href="http://10.58.137.126/shop/index.php?act=show_store&amp;op=goods_all&amp;store_id=1">查看本店其他商品</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="buynow_form" method="post" action="http://10.58.137.126/shop/index.php">
    <input id="act" name="act" type="hidden" value="buy"> <input id="op" name="op" type="hidden" value="buy_step1"> <input id="cart_id" name="cart_id[]" type="hidden">
</form>
<script type="text/javascript" src="${STATIC_URL}/front/modules/goods/js/goods.detail.js"></script>
<jsp:include page="../index/wrapper.suffix_front.jsp" />
