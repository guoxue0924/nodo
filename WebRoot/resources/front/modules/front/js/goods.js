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
	
    $("#com_b h2").click(function () {
        $(this).removeClass('h2bg');
        $(this).siblings().addClass('h2bg');
        $("#com_h blockquote").hide();
        $("#com_h blockquote").eq($(this).index()).show();
    });

    /* 商品图片缩略图 */
    var jqzoom = $(".jqzoom");
    jqzoom.imagezoom();

    /* 图片缩略图切换 */
    $("#imglist a").click(function () {
        var src = $(this).children("img").attr('src');
        jqzoom.attr('src', src);
        jqzoom.attr('rel', src);
        $(this).parents("li").addClass("select").siblings().removeClass("selected");
    });

    /* 导航栏伸缩 */
    $(".sliderBtn").click(function () {
        $(this).next("ul").toggle();
    });

    /* 商品规格选择 */
    $(".spec_select").change(function () {
        _getPriceTotal();
    });

    /* 数量选择 */
    $(".count").blur(function () {
        _getPriceTotal();
    });

    /* 添加购物车 */
    $(".addCartBtn").click(function () {
        var propertyArr = new Array();
        $.each($(".spec_select"), function (index, data) {
            var property = new Array();
            property.push($(data).data("id"));
            property.push($(data).val());
            var propertyStr = property.join(":");
            propertyArr.push(propertyStr);
        });
        var propertySpecs = propertyArr.join(",");
        var content_id = $("#content_id").val();
        $.post(BASE_URL + '/front/cart/addToCart',
            {
                "propertySpecs": propertySpecs,
                "content_id": content_id
            },
            function (data) {

            });
    });

    /* 添加评论 */
    $("#addCommentSubmit").click(function(){
    	var email = $("#email").val();
    	var content = $("#content").val();
    	if(email == '') {
    		alert('请填写你的邮箱');
    		return false;
    	}
    	if(content == '') {
    		alert('评论内容不能为空！');
    		return false;
    	}
    	var options = {
                dataType:'json',
                timeout:60000,
                success:function(data){
                	if(data.status == 1) {
                		alert(data.error);
                	}else {
                		alert('评论成功');
                		location.reload();
                	}
                }
        };
        $("#addCommentForm").ajaxSubmit(options);
    });
    /* 添加购物车 */
    $('#cartBtn').click(function(){
        $('#cartSubmit').trigger('click')
    });

    /* 分页事件 第一页 */
    $("#firstPageComment").click(function(){
        var content_id = $('#content_id').val();
        $.post('/cas/comment/getGoodsComment',{'content_id':content_id,'page':1},pageSuccess,'json');
    });

    /* 分页事件 上一页 */
    $("#prevPageComment").click(function(){
        var content_id = $('#content_id').val();
        if($("#page").val()==1){
            alert('已经是第一页');
            return false;
        }
        console.log($("#page").val());
        var page = parseInt($("#page").val())-1;
        $.post('/cas/comment/getGoodsComment',{'content_id':content_id,'page':page},pageSuccess,'json');
    });

    /* 分页事件 下一页 */
    $("#nextPageComment").click(function(){
        var content_id = $('#content_id').val();
        if($("#page").val()==$("#page_count").val()){
            alert('已经是最后一页');
            return false;
        }
        var page = parseInt($("#page").val())+1;
        $.post('/cas/comment/getGoodsComment',{'content_id':content_id,'page':page},pageSuccess,'json');
    });

    /* 分页事件 页末 */
    $("#lastPageComment").click(function(){
    	var last_page = $(this).attr('data-page');
    	if($("#page").val() == last_page) {
    		alert('已经是最后一页');
            return false;
    	}
        var content_id = $('#content_id').val();
        $.post('/cas/comment/getGoodsComment',{'content_id':content_id,'page':$("#page_count").val()},pageSuccess,'json')
    });

    /* 收藏商品 */
    $("#addFavorite").click(function(){
        var content_id = $('#content_id').val();
        $.post('/cas/favorite/addFavorite',{'content_id':content_id,'type':'1'},function(data){
            if(data.status==1){
                alert(data.msg);
            }else{
                alert(data.msg);
                $("#emfavoriteId").html(data.data);
            }
        });
    });

});

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
	
	function pageSuccess(data){
	    var liHtml = '';
	    $.each(data.comment,function(index,data){
	        var commentHtml = '<li class="word">';
	        commentHtml += '<img width="40" height="40" src="'+data.avatar+'"/>';
	        commentHtml += '<font class="f2">'+data.nickname+'</font> ';
	        commentHtml += '<font class="f3">( '+data.ctime+' )</font><br>';
	        commentHtml += '<img class="rank" alt="" src="/static/front/img/stars'+data.rank_base+'.gif"/>';
	        commentHtml += '<p>'+data.content+'</p>';
	        commentHtml += '</li>';
	        liHtml+=commentHtml;
	    });
	    $(".comments").html(liHtml);
	    $("#page").val(data.page);
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
