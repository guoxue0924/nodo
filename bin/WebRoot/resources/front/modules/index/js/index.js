var PRICE_FORMAT = '&yen;%s';
$(function(){

    /*清除时间*/
//    $('body').on('click', '.add-on', function(){
//        $(this).parent('td').find('input').val('');
//    });

    //首页左侧分类菜单
    $(".category ul.menu").find("li").each(
        function() {
            $(this).hover(
                function() {
                    var cat_id = $(this).attr("cat_id");
                    var menu = $(this).find("div[cat_menu_id='"+cat_id+"']");
                    menu.show();
                    $(this).addClass("hover");
                    var menu_height = menu.height();
                    if (menu_height < 60) menu.height(80);
                    menu_height = menu.height();
                    var li_top = $(this).position().top;
                    $(menu).css("top",-li_top + 38);
                },
                function() {
                    $(this).removeClass("hover");
                    var cat_id = $(this).attr("cat_id");
                    $(this).find("div[cat_menu_id='"+cat_id+"']").hide();
                }
            );
        }
    );
    $(".head-user-menu dl").hover(function() {
            $(this).addClass("hover");
        },
        function() {
            $(this).removeClass("hover");
        });
    $('.head-user-menu .my-mall').mouseover(function(){// 最近浏览的商品
//        load_history_information();
        $(this).unbind('mouseover');
    });
    $('.head-user-menu .my-cart').mouseover(function(){// 运行加载购物车
        load_cart_information();
        $(this).unbind('mouseover');
    });
    $('#button').click(function(){
        if ($('#keyword').val() == '') {
            return false;
        }
    });
});

$(function(){
    //search
    var act = "index";
    if (act == "store_list"){
        $("#search").children('ul').children('li:eq(1)').addClass("current");
        $("#search").children('ul').children('li:eq(0)').removeClass("current");
    }
    $("#search").children('ul').children('li').click(function(){
        $(this).parent().children('li').removeClass("current");
        $(this).addClass("current");
        $('#search_act').attr("value",$(this).attr("act"));
        $('#keyword').attr("placeholder",$(this).attr("title"));
    });
    $("#keyword").blur();

});

$(function() {
	load_cart_information();
}); 

backTop=function (btnId){
    var btn=document.getElementById(btnId);
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    window.onscroll=set;
    btn.onclick=function (){
        btn.style.opacity="0.5";
        window.onscroll=null;
        this.timer=setInterval(function(){
            scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
            scrollTop-=Math.ceil(scrollTop*0.1);
            if(scrollTop==0) clearInterval(btn.timer,window.onscroll=set);
            if (document.documentElement.scrollTop > 0) document.documentElement.scrollTop=scrollTop;
            if (document.body.scrollTop > 0) document.body.scrollTop=scrollTop;
        },10);
    };
    function set(){
        scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        btn.style.opacity=scrollTop?'1':"0.5";
    }
};
//backTop('gotop');
//动画显示边条内容区域
$(function() {
    ncToolbar();
    $(window).resize(function() {
        ncToolbar();
    });
    function ncToolbar() {
        if ($(window).width() >= 1240) {
            $('#appBarTabs >.variation').show();
        } else {
            $('#appBarTabs >.variation').hide();
        }
    }
    $('#appBarTabs').hover(
        function() {
            $('#appBarTabs >.variation').show();
        },
        function() {
            ncToolbar();
        }
    );
    $("#compare").click(function(){
        if ($("#content-compare").css('right') == '-210px') {
            loadCompare(false);
            $('#content-cart').animate({'right': '-210px'});
            $("#content-compare").animate({right:'35px'});
        } else {
            $(".close").click();
            $(".chat-list").css("display",'none');
        }
    });
    $("#rtoolbar_cart").click(function(){
        if ($("#content-cart").css('right') == '-210px') {
            $('#content-compare').animate({'right': '-210px'});
            $("#content-cart").animate({right:'35px'});
            if (!$("#rtoolbar_cartlist").html()) {
                $("#rtoolbar_cartlist").load('index.php?act=cart&op=ajax_load&type=html');
            }
        } else {
            $(".close").click();
            $(".chat-list").css("display",'none');
        }
    });
    $(".close").click(function(){
        $(".content-box").animate({right:'-210px'});
    });

    $(".quick-menu dl").hover(function() {
            $(this).addClass("hover");
        },
        function() {
            $(this).removeClass("hover");
        });

    // 右侧bar用户信息
    $('div[nctype="a-barUserInfo"]').click(function(){
        $('div[nctype="barUserInfo"]').toggle();
    });
    // 右侧bar登录
    $('div[nctype="a-barLoginBox"]').click(function(){
        $('div[nctype="barLoginBox"]').toggle();
        document.getElementById('codeimage').src='http://10.58.137.126/shop/index.php?act=seccode&op=makecode&nchash=c93636e5&t=' + Math.random();
    });
    $('a[nctype="close-barLoginBox"]').click(function(){
        $('div[nctype="barLoginBox"]').toggle();
    });

});

function takeCount() {
    setTimeout("takeCount()", 1000);
    $(".time-remain").each(function(){
        var obj = $(this);
        var tms = obj.attr("count_down");
        if (tms>0) {
            tms = parseInt(tms)-1;
            var days = Math.floor(tms / (1 * 60 * 60 * 24));
            var hours = Math.floor(tms / (1 * 60 * 60)) % 24;
            var minutes = Math.floor(tms / (1 * 60)) % 60;
            var seconds = Math.floor(tms / 1) % 60;

            if (days < 0) days = 0;
            if (hours < 0) hours = 0;
            if (minutes < 0) minutes = 0;
            if (seconds < 0) seconds = 0;
            obj.find("[time_id='d']").html(days);
            obj.find("[time_id='h']").html(hours);
            obj.find("[time_id='m']").html(minutes);
            obj.find("[time_id='s']").html(seconds);
            obj.attr("count_down",tms);
        }
    });
}
/*function update_screen_focus(){
    var ap_ids = '';//广告位编号
    $(".full-screen-slides li[ap_id]").each(function(){
        var ap_id = $(this).attr("ap_id");
        ap_ids += '&ap_ids[]='+ap_id;
    });
    $(".jfocus-trigeminy a[ap_id]").each(function(){
        var ap_id = $(this).attr("ap_id");
        ap_ids += '&ap_ids[]='+ap_id;
    });
    if (ap_ids != '') {
        $.ajax({
            type: "GET",
            url: SHOP_SITE_URL+'/index.php?act=adv&op=get_adv_list'+ap_ids,
            dataType:"jsonp",
            async: true,
            success: function(adv_list){
                $(".full-screen-slides li[ap_id]").each(function(){
                    var obj = $(this);
                    var ap_id = obj.attr("ap_id");
                    var color = obj.attr("color");
                    if (typeof adv_list[ap_id] !== "undefined") {
                        var adv = adv_list[ap_id];
                        obj.css("background",color+' url('+adv['adv_img']+') no-repeat center top');
                        obj.find("a").attr("title",adv['adv_title']);
                        obj.find("a").attr("href",adv['adv_url']);
                    }
                });
                $(".jfocus-trigeminy a[ap_id]").each(function(){
                    var obj = $(this);
                    var ap_id = obj.attr("ap_id");
                    if (typeof adv_list[ap_id] !== "undefined") {
                        var adv = adv_list[ap_id];
                        obj.attr("title",adv['adv_title']);
                        obj.attr("href",adv['adv_url']);
                        obj.find("img").attr("alt",adv['adv_title']);
                        obj.find("img").attr("src",adv['adv_img']);
                    }
                });
            }
        });
    }
}*/
