/**
 * Created by George on 15/4/16.
 */

$(function () {
    initPrice();

    /* 商品数量修改 */
    $(".quantity").change(function () {
        initPrice();
        var quantity = $(this).val();
        var cart_id = $(this).parents('tr.cartList').data('id');
        $.post('/bts/cart/updateQuantity',{
            "cart_id":cart_id,
            "quantity":quantity
        },json);
    });

    /* 删除购物车商品 */
    $(".deleteBtn").click(function () {
        if (confirm("是否从购物车删除")) {
            var cart_id = $(this).data('id');
            $.post("http://adv.yumshop.lo.trac.cn/bts/cart/deleteGoodsFromCart",
                {"cart_id": cart_id},
                function (data) {
                    if(data.status==0){
                        $("#cartTable tr[data-id='"+cart_id+"']").remove();
                        alert("删除成功！");
                        initPrice();
                    }else{
                        alert(data.error);
                    }
                },json);
        }
    });

    /* 清除购物车商品 */
    $("#clearCart").click(function(){
        if (confirm("是否清空购物车")) {
            $.post("http://adv.yumshop.lo.trac.cn/bts/cart/clearCart",
                function (status) {
                    if(status==0){
                        $("#cartTable .cartList").remove();
                        //alert("清除成功！");
                        initPrice();
                    }else{
                        alert('失败：清空购物车');
                    }
                },json);
        }
    });
});

/* 计算购物车价格 */
function initPrice() {
    var quantityEl = $(".quantity");
    var priceMarketTotal = 0.00;
    var priceTotal = 0.00;
    $.each(quantityEl, function (index, data) {
        var quantity = $(data).val();
        var tr = $(data).parents('tr');
        var price = tr.find('.price').data('price');
        var priceMark = tr.find('.price_market').data('price');
        var priceTotalS = price * quantity;
        var priceMarkTotalS = priceMark * quantity;
        priceTotal += priceTotalS;
        priceMarketTotal += priceMarkTotalS;
        tr.find(".price_total_s").text("￥" + (priceTotalS) + "元");
        tr.find(".price_total_s").data('priceTotalS', priceTotalS);
    });
    $("#priceDifference").text(priceMarketTotal - priceTotal);
    $("#differencePercent").text(Math.ceil((priceMarketTotal - priceTotal) / priceMarketTotal * 100));
    $("#priceTotal").text(priceTotal);
    $("#priceMarketTotal").text(priceMarketTotal);
}


function _getPriceTotal() {
    var propertyArr = new Array();
    $.each($(".spec_select"), function (index, data) {
        var property = new Array();
        property.push($(data).data("id"));
        property.push($(data).val());
        var proertyStr = property.join(":");
        propertyArr.push(proertyStr);
    });
    var propertySpecs = propertyArr.join(",");
    var content_id = $("#content_id").val();
    var count = $(".count").val();
    $.post('http://adv.yumshop.lo.trac.cn/goods/index/getPriceTotal', {
            "spec": propertySpecs, "content_id": content_id, "count": count
        },
        function (data) {
            if (data.status == 0) {
                $(".price_total").text(data.data.price_total + '元');

            }
        },json);
}