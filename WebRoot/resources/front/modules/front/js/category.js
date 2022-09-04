/**
 * Created by George on 15/4/16.
 */

$(function () {
	$('#category_tree .dt').hover(function () {
        var index = $(this).index();
        $(this).find(".DisSub2").show();
        $(this).find(".HandleLI2").addClass('current');
    }, function () {
        $(this).find(".DisSub2").hide();
        $(this).find(".HandleLI2").removeClass('current');
    });
	
	
    var property_value_id = $("#property_value_id").val();
    var propertyArr = property_value_id.split(',');
    $.each(propertyArr, function (index, value) {
        $('.propertyChange[data-property_value_id="' + value + '"]').addClass("current").siblings('.propertyChange').removeClass('current');
    })

    $(".sliderBtn").click(function () {
        $(this).next("ul").toggle();
    });

    /* 属性切换 */
    $(".propertyChange").click(function () {
        $(this).addClass('current');
        $(this).siblings().removeClass('current');
        var property = [];
        $.each($(".propertyChange.current"), function (index, el) {
            if ($(el).data('property_value_id') != 0) {
                property.push($(el).data('property_value_id'));
            }
        });
        var propertyStr = property.join(',');
        $('property_value').val(propertyStr);
        var category_id = $("#category_id").val();
        location.href = '/goods/index/category?category_id=' + category_id + '&property_value_id=' + propertyStr;
    });

    /* 分页事件 第一页 */
    $("#firstPageCategory").click(function () {
        var category_id = $("#category_id").val();
        var property_value_id = $("#property_value_id").val();
        $.post('/goods/index/getGoodsCategory', {
            'category_id': category_id,
            'property_value_id': property_value_id,
            'page': 1
        }, pageSuccess);
    });

    /* 分页事件 上一页 */
    $("#prevPageCategory").click(function () {
        var category_id = $("#category_id").val();
        var property_value_id = $("#property_value_id").val();
        var content_id = $('#content_id').val();
        if ($("#page").val() == 1) {
            alert('已经是第一页');
            return false;
        }
        console.log($("#page").val());
        var page = parseInt($("#page").val()) - 1;
        $.post('/goods/index/getGoodsCategory', {
            'category_id': category_id,
            'property_value_id': property_value_id,
            'page': page
        }, pageSuccess);
    });

    /* 分页事件 下一页 */
    $("#nextPageCategory").click(function () {
        var category_id = $("#category_id").val();
        var property_value_id = $("#property_value_id").val();
        if ($("#page").val() == $("#page_count").val()) {
            alert('已经是最后一页');
            return false;
        }
        var page = parseInt($("#page").val()) + 1;
        var category_id = $("#category_id").val();
        $.post('/goods/index/getGoodsCategory', {
            'category_id': category_id,
            'property_value_id': property_value_id,
            'page': page
        }, pageSuccess);
    });

    /* 分页事件 页末 */
    $("#lastPageCategory").click(function () {
        var category_id = $("#category_id").val();
        var property_value_id = $("#property_value_id").val();
        var content_id = $('#content_id').val();
        $.post('/goods/index/getGoodsCategory', {
            'category_id': category_id,
            'property_value_id': property_value_id,
            'page': $("#page_count").val()
        }, pageSuccess)
    });
});

/* 分页成功回调函数 */
function pageSuccess(data) {
    var liHtml = '';
    $.each(data.data.goods, function (index, data) {
        console.log(data);
        var goodsHtml = '<div class="goodsItem">';
        goodsHtml += '<a href="/goods/index/detail?content_id=' + data.content_id + '">';
        goodsHtml += '<img class="goodsimg" alt="' + data.name + '" src="/upload/' + data.image_default + '"></a><br>';
        goodsHtml += '<p><a title="' + data.name + '" href="/goods/index/detail?content_id=' + data.content_id + '">' + data.name + '</a></p>';
        goodsHtml += '市场价：<font class="market_s">' + data.price_market + '元</font><br>';
        goodsHtml += '促销价：<font class="shop_s">' + data.price + '元</font><br></div>';
        liHtml += goodsHtml;
    });
//    console.log(liHtml);
    $("#goodsList").html(liHtml);
    $("#page").val(data.data.pagination.page);
}

//清除浏览过的商品
function clearHistory() {
	$.post('/goods/index/clearHistory',{'clear':'ok'},function(data){
		if(data.status == 0) {
			alert(data.error);
			$(".history_list").remove();
		} else {
			alert(data.error);
		}
	},'json');
}