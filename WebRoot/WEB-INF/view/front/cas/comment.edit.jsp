<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>交易评价/晒单</span>
    </div>
</div>
<jsp:include page="user.nav.jsp"/>
<div class="right-layout">
<div class="ncm-flow-layout">
    <div class="ncm-flow-container">
        <div class="title"><a href="javascript:history.go(-1);" class="ncm-btn-mini fr"><i class="icon-reply"></i>返&nbsp;回</a>
            <h3>对商品进行评价</h3>
        </div>
        <form id="evalform" method="get" action="${BASE_URL}/front/cas/comment/add">
            <div class="alert alert-block">
                <h4>操作提示：</h4>
                <ul>
                    <li>评价信息最多填写250字，请您根据本次交易，给予真实、客观地评价；您的评价将是其他会员的参考。</li>
                    <li>评价完成将获得 “10经验值” “50积分”。<span class="orange"> 一旦提交后不能修改。</span></li>
                </ul>
            </div>
            <div class="tabmenu">
                <ul class="tab">
                    <li class="active"><a href="javascript:void(0);">对购买过的商品评价</a></li>
                </ul>
            </div>
            <table class="ncm-default-table deliver mb30">
                <thead>
                <tr>
                    <th colspan="2">订单详情</th>
                    <th>商品评分</th>
                    <th>评价详情</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                	<c:forEach items="${order.items}" var="item" varStatus="status">
                    <tr class="bd-line">
                        <td valign="top" class="w40">
                            <div class="pic-thumb">
                                <a href="#" target="_blank"><img src="${IMG_URL}${item.goodsImage}"/></a>
                            </div>
                        </td>
                        <td valign="top" class="tl w200">
                            <dl class="goods-name">
                            <dt style="width: 190px;">
                                <a href="#" target="_blank">${item.goodsName}</a>
                            </dt>
                            <dd><span class="rmb-price"><fmt:formatNumber value="${item.buyPrice}" pattern="#,#00.00#"/></span>&nbsp;*&nbsp;${item.buyNum}&nbsp;件</dd>
                            </dl>
                        </td>
                        <td valign="top" class="w100">
                            <div class="ncgeval mb10">
                                <div class="raty"><input nctype="score" name="itemComments[${status.index}].rankBase" type="hidden"></div>
                            </div>
                        </td>
                        <td valign="top" class="tr">
                            <textarea name="itemComments[${status.index}].content" cols="150" style="width: 280px;"></textarea>
	                    	<input type="hidden" name="itemComments[${status.index}].itemId" value="${item.itemId}">
	                    	<input type="hidden" name="itemComments[${status.index}].skuId" value="${item.skuId}">
                        </td>
                    </tr>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
            <div class="tabmenu">
                <ul class="tab">
                    <li class="active"><a href="javascript:void(0);">对此次购物体验的评分</a></li>
                </ul>
            </div>
            <div class="ncm-default-form">
                <dl>
                    <dt>物流满意度：</dt>
                    <dd>
                        <div class="raty-x2">
                            <input nctype="score" name="rankLogistics" type="hidden">
                        </div>
                    </dd>
                </dl>
                <dl>
                    <dt>发货速度满意度：</dt>
                    <dd>
                        <div class="raty-x2">
                            <input nctype="score" name="rankSpeed" type="hidden">
                        </div>
                    </dd>
                </dl>
                <div class="bottom">
                    <label class="submit-border">
                        <input id="btn_submit" type="button" class="submit" value="提交"/>
                    </label>
                </div>
            </div>
        </form>
    </div>
</div>
 <script src="${STATIC_URL}/front/modules/cas/js/comment.edit.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-raty/jquery.raty.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.raty').raty({
            path: "${STATIC_URL}/plugins/jquery-raty/img",
            click: function(score) {
                $(this).find('[nctype="score"]').val(score);
            }
        });

        $('.raty-x2').raty({
            path: "${STATIC_URL}/plugins/jquery-raty/img",
            starOff: 'star-off-x2.png',
            starOn: 'star-on-x2.png',
            width: 150,
            click: function(score) {
                $(this).find('[nctype="score"]').val(score);
            }
        });
    });
</script>
</div>
<div class="clear"></div>
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>