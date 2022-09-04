$(function () {
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
        $.post('/cas/comment', {
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
	return false;
	
    var liHtml = '';
    $.each(data.data.comment, function (index, data) {
        var commentHtml = '<div class="f_l">';
        goodsHtml += '<a href="/goods/index/detail?content_id=' + data.content_id + '">';
        goodsHtml += '<img class="goodsimg" alt="' + data.name + '" src="/upload/' + data.image_default + '"></a><br>';
        goodsHtml += '<p><a title="' + data.name + '" href="/goods/index/detail?content_id=' + data.content_id + '">' + data.name + '</a></p>';
        goodsHtml += '市场价：<font class="market_s">' + data.price_market + '元</font><br>';
        goodsHtml += '促销价：<font class="shop_s">' + data.price + '元</font><br></div>';
        liHtml += goodsHtml;
        
        /*
        var commentHtml = '<div class="f_l">' + 
	        '<b>' + {$c.category_title}: </b><a href="{$c.relate_content.href}"><font class="f4">{$c.relate_content.name}</font></a>&nbsp;&nbsp;({$c.ctime})' + 
	        '</div>' + 
	        '<div class="f_r">' + 
	        '<a class="f6 deleteComment" data-id="{$c.id}" title="删除" href="javascript:;">删除</a>' + 
	        '</div>' + 
	        '<div class="msgBottomBorder">' + 
	        '{$c.content}<br>' + 
	        '</div>' + 
	        '<div class="attachment">' + 
	        '{foreach $c.attachment as $ca}' + 
	        '<img src="/upload{$ca.filepath}"/>' + 
	        '{/foreach}' + 
	        '</div>';
        */
    });
//    console.log(liHtml);
    $("#comment_list").html(liHtml);
    $("#pager").val(data.data.pagination.page);
}