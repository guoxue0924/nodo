<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>实物交易订单</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">

    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active"><a href="index.php?act=member_favorites&amp;op=fglist">收藏商品</a></li>
            </ul>
        </div>
        <table class="ncm-default-table">
            <thead>
            <tr nc_type="table_header">
                <td><input type="checkbox" id="all" class="checkall">
                    <label for="all">全选</label>
                    <a href="javascript:void(0);" class="ncm-btn-mini deleteMultipleBtn"
                       uri="index.php?act=member_favorites&amp;op=delfavorites&amp;type=goods" name="fav_id"
                       nc_type="batchbutton"><i class="icon-trash"></i>删除</a>

                    <!--<div class="model-switch-btn">显示方式：<a-->
                            <!--href="index.php?act=member_favorites&amp;op=fglist&amp;show=list" title="列表模式"><i-->
                            <!--class="icon-list"></i></a><a-->
                            <!--href="index.php?act=member_favorites&amp;op=fglist&amp;show=pic" class="current"-->
                            <!--title="大图模式"><i class="icon-picture"></i></a><a-->
                            <!--href="index.php?act=member_favorites&amp;op=fglist&amp;show=store" title="店铺模式"><i-->
                            <!--class="icon-home"></i></a>-->
                    <!--</div>-->
                </td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="2" class="pic-model">
                    <ul id="favoriteList">
                        <!--<li class="favorite-pic-list">-->
                        <!--<div class="favorite-goods-thumb">-->
                        <!--<a href="index.php?act=goods&amp;goods_id=232" target="_blank">-->
                        <!--<img src="http://10.58.137.126/data/upload/shop/store/goods/1/1_04423411880302392_240.png">-->
                        <!--</a>-->
                        <!--</div>-->
                        <!--<div class="favorite-goods-info" style="top: 0px;">-->
                        <!--<dl>-->
                        <!--<dt>-->
                        <!--<input type="checkbox" class="checkitem" value="232">-->
                        <!--<a href="index.php?act=goods&amp;goods_id=232" target="_blank">中华老字号-->
                        <!--东阿阿胶桃花姬阿胶糕300g</a></dt>-->
                        <!--<dd><span><strong>150.00</strong>元</span><a href="javascript:void(0)"-->
                        <!--nc_type="sharegoods"-->
                        <!--class="sns-share" title="分享"><i-->
                        <!--class="icon-share"></i>分享</a></dd>-->
                        <!--<dd><span>售出：<em>2</em>件</span><span>(<em>0</em>条评论)</span><span>收藏人气：1</span><a-->
                        <!--href="javascript:void(0)"-->
                        <!--class="ncm-btn-mini" title="删除">删除</a></dd>-->
                        <!--</dl>-->
                        <!--</div>-->
                        <!--</li>-->
                    </ul>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="20">
                    <div class="pagination">
                        <ul id="pagination" class="pagination-sm"></ul>
                        <input id="pageIndex" type="hidden" value="1"/>
                        <input id="pageSize" type="hidden" value="5"/>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>

        <!--&lt;!&ndash; 猜你喜欢 &ndash;&gt;-->
        <!--<div id="guesslike_div" style="width:980px;">-->
            <!--<div class="goods-guess-like">-->
                <!--<div class="title">猜您喜欢的宝贝</div>-->
                <!--<div class="content">-->
                    <!--<div class="noguess">暂无商品向您推荐</div>-->
                <!--</div>-->
            <!--</div>-->
        <!--</div>-->
    </div>
</div>
<div class="clear"></div>
</div>

<script src="${STATIC_URL}/front/modules/cas/js/favorite.index.js" type="text/javascript"></script>

<script id="favoriteTpl" type="text/html">
    <li class="favorite-pic-list">
        <div class="favorite-goods-thumb">
            <a href="${BASE_URL}/front/goodsContent/detail?contentId={{specificationid}}" target="_blank">
                <img src="${IMG_URL}{{DefaultImage}}">
            </a>
        </div>
        <div class="favorite-goods-info" style="top: 0px;">
            <dl>
                <dt>
                    <input type="checkbox" class="checkitem" value="{{favoriteId}}">
                    <a href="${BASE_URL}/front/goodsContent/detail?skuId={{specificationid}}" target="_blank">{{relate_content['name']}}</a></dt>
                <dd><span><strong>{{relate_content['price']}}</strong>元</span>
                    <a href="javascript:void(0)" nc_type="sharegoods" class="sns-share" title="分享"><i
                            class="icon-share"></i>分享</a></dd>
                <dd><span>售出：<em>{{relate_content['salesAmount']}}</em>件</span><span>(<em>{{relate_content['salesAmount']}}</em>条评论)</span><span>收藏人气：{{favoriteCount}}</span>
                    <a href="javascript:void(0)" class="ncm-btn-mini deleteBtn" data-favoriteId="{{favoriteId}}" title="删除">删除</a></dd>
            </dl>
        </div>
    </li>
</script>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
