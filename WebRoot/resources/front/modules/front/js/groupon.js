/**
 * 
 */

$(function(){
	$('#category_tree .dt').hover(function () {
        var index = $(this).index();
        $(this).find(".DisSub2").show();
        $(this).find(".HandleLI2").addClass('current');
    }, function () {
        $(this).find(".DisSub2").hide();
        $(this).find(".HandleLI2").removeClass('current');
    });

    $("#carouselAdvert").owlCarousel({"items": 1});
        
    var category_id = $("#category_id").val();
    var categoryArr = category_id.split(',');
    $.each(categoryArr, function (index, value) {
        $('.categoryChange[data-category_id="' + value + '"]').addClass("current").siblings('.categoryChange').removeClass('current');
    })
    
    /* 属性切换 */
    $(".categoryChange").click(function () {
        $(this).addClass('current');
        $(this).siblings().removeClass('current');
        var category = [];
        $.each($(".categoryChange.current"), function (index, el) {
            if ($(el).data('category_id') != 0) {
            	category.push($(el).data('category_id'));
            }
        });
        var categoryStr = category.join(',');
        $('category_value').val(categoryStr);
        var status = $("#pageStatus").attr('status');
        location.href = '/groupon/index?status=' + status + '&category_id=' + categoryStr;
    });
});

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