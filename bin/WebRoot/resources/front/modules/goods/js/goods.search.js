/**
 * Created by George on 15/12/18.
 */
var defaultSmallGoodsImage = 'shop/common/default_goods_image_240.gif';
var defaultTinyGoodsImage = 'shop/common/default_goods_image_60.gif';

$(function() {
	$('#files').tree({
		expanded : 'li:lt(0)'
	});

	// 浮动导航 waypoints.js
	$('#main-nav-holder').waypoint(function(event, direction) {
		$(this).parent().toggleClass('sticky', direction === "down");
		event.stopPropagation();
	});
	// 单行显示更多
	$('span[nc_type="show"]').click(function() {
		s = $(this).parents('dd').prev().find('li[nc_type="none"]');
		if (s.css('display') == 'none') {
			s.show();
			$(this).html('<i class="icon-angle-up"></i>收起');
		} else {
			s.hide();
			$(this).html('<i class="icon-angle-down"></i>更多');
		}
	});

	// 浏览历史处滚条
	$('#nchSidebarViewed').perfectScrollbar();

	// 猜你喜欢
	$('#guesslike_div').load('http://10.58.137.126/shop/index.php?act=search&op=get_guesslike', function() {
		$(this).show();
	});

	// 鼠标经过弹出图片信息
	$(".item").hover(function() {
		$(this).find(".goods-info").animate({
			"top" : "180px"
		}, 400, "swing");
	}, function() {
		$(this).find(".goods-info").stop(true, false).animate({
			"top" : "230px"
		}, 400, "swing");
	});

	// 加入购物车
	$('a[nctype="add_cart"]').click(function() {
		var _parent = $(this).parent(), thisTop = _parent.offset().top, thisLeft = _parent.offset().left;
		animatenTop(thisTop, thisLeft), !1;
		addcart($(this).attr('data-param'), 1, '');
	});

	// 图片切换效果
	$('.goods-pic-scroll-show').find('a').mouseover(function() {
		$(this).parents('li:first').addClass('selected').siblings().removeClass('selected');
		var _src = $(this).find('img').attr('src');
		_src = _src.replace('_60.', '_240.');
		$(this).parents('.goods-content').find('.goods-pic').find('img').attr('src', _src);
		// 切换图片的同时切换此sku的价格、链接等信息
		var skuId = $(this).parents('li:first').find('input[name="skuId"]').val();
		var _price = $(this).parents('li:first').find('input[name="skuPrice"]').val();
		var _title = "商城价：¥" + _price;
		var _url = BASE_URL + "/front/goodsContent/detail?skuId=" + skuId;
		$(this).parents('.goods-content').find('.goods-price').find('.sale-price').attr('title', _title);
		$(this).parents('.goods-content').find('.goods-price').find('.sale-price').html("¥" + _price);
		$(this).parents('.goods-content').find('.skuUrl').attr("href", _url);
		$(this).parents('.goods-content').find('.store').find('a:first').attr("href", _url);
	});
});

function animatenTop(thisTop, thisLeft) {
	var CopyDiv = '<div id="box" style="top:' + thisTop + "px;left:" + thisLeft + 'px" ></div>', topLength = $(".my-cart").offset().top, leftLength = $(".my-cart").offset().left;
	$("body").append(CopyDiv), $("body").children("#box").animate({
		"width" : "0",
		"height" : "0",
		"margin-top" : "0",
		"top" : topLength,
		"left" : leftLength,
		"opacity" : 0
	}, 1000, function() {
		$(this).remove();
	});
}

function go(pageIndex) {
	var keyword = $("#keyword").val();
	var categoryId = $("#categoryId").val();
	var sort = $("#sort").val();
	var url = BASE_URL + "/front/goodsContent/search?pageIndex=" + pageIndex;
	if (keyword != null && keyword != '') {
		url += "&keyword=" + keyword;
	}
	if (categoryId != null && categoryId != '') {
		url += "&categoryId=" + categoryId;
	}
	if (sort != null && sort != '') {
		url += "&sort=" + sort;
	}
	location.href = url;
}