/**
 * Created by George on 15/4/16.
 */



/* 订单提交 */
$("#orderSubmit").click(function () {
//    e.preventDefault();
//    e.stopPropagation();
    alert('11');
    $("#theForm").ajaxSubmit({
    	dataType:'json',
        timeout:60000,
    	success: function (data) {
            if (data.status == 1) {
            	alert(data.error);
            } else if (data.status == 0) {
                alert("购买成功！");
                location.href = data.data;
            }
        }
    });
});

$(function () {
    initPrice();

    /* 选择是否需要发票 */
    $("input[name='is_invoice']").change(function(){
        if($(this).prop('checked')){
            $(this).parents('tr').next('tr').show();
        }else{
            $(this).parents('tr').next('tr').hide();
        }
    });
});


function initPrice() {
    var quantityEl = $(".quantity");
    var priceMarketTotal = 0.00;
    var priceTotal = 0.00;
    $.each(quantityEl, function (index, data) {
        var quantity = $(data).data('quantity');
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
    $("#goodsTotal").text('￥'+priceTotal+'元');
    $("#orderTotal").text('￥'+priceTotal+'元');
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
    $.post('http://yummall.lo.trac.cn/goods/index/getPriceTotal', {
            "spec": propertySpecs, "content_id": content_id, "count": count
        },
        function (data) {
            if (data.status == 0) {
                $(".price_total").text(data.data.price_total + '元');

            }
        });
}

