/**
 * Created by George on 15/12/18.
 */
var defaultSmallGoodsImage = 'shop/common/default_goods_image_240.gif';
var defaultTinyGoodsImage = 'shop/common/default_goods_image_60.gif';

$(function(){
    $('#files').tree({
        expanded: 'li:lt(2)'
    });

    //浮动导航  waypoints.js
    $('#main-nav-holder').waypoint(function(event, direction) {
        $(this).parent().toggleClass('sticky', direction === "down");
        event.stopPropagation();
    });
    // 单行显示更多
    $('span[nc_type="show"]').click(function(){
        s = $(this).parents('dd').prev().find('li[nc_type="none"]');
        if(s.css('display') == 'none'){
            s.show();
            $(this).html('<i class="icon-angle-up"></i>收起');
        }else{
            s.hide();
            $(this).html('<i class="icon-angle-down"></i>更多');
        }
    });


    //浏览历史处滚条
    $('#nchSidebarViewed').perfectScrollbar();

    //猜你喜欢
    $('#guesslike_div').load('http://10.58.137.126/shop/index.php?act=search&op=get_guesslike', function(){
        $(this).show();
    });
});