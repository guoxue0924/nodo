$(function(){
	
	loadCommentData();
	loadSaleHistoryData();
	
	// 商品图片切换
	$(".jqzoom").imagezoom();
    $("#thumblist li a").click(function(){
        //增加点击的li的class:tb-selected，去掉其他的tb-selecte
        $(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
        //赋值属性
        $(".jqzoom").attr('src',$(this).find("img").attr("mid"));
        $(".jqzoom").attr('rel',$(this).find("img").attr("big"));
    });

    // 商品内容介绍Tab样式切换控制
    $('#categorymenu').find("li").click(function(){
        $('#categorymenu').find("li").removeClass('current');
        $(this).addClass('current');
    });

    // 商品详情默认情况下显示全部
    $('#tabGoodsIntro').click(function(){
        $('.bd').css('display','');
        $('.hd').css('display','');
    });

    // 点击评价隐藏其他以及其标题栏
    $('#tabGoodsRate').click(function(){
        $('.bd').css('display','none');
        $('#ncGoodsRate').css('display','');
        $('.hd').css('display','none');
    });
    // 点击成交隐藏其他以及其标题
    $('#tabGoodsTraded').click(function(){
        $('.bd').css('display','none');
        $('#ncGoodsTraded').css('display','');
        $('.hd').css('display','none');
    });

    //赠品处滚条
//    $('#ncsGoodsGift').perfectScrollbar();
    // 加入购物车
    $('a[nctype="addcart_submit"]').click(function(){
        var content_id = $(this).attr('content-id');
        addcart(content_id, checkQuantity(),'addcart_callback');
    });
    // 立即购买
    $('a[nctype="buynow_submit"]').click(function(){
        var skuId = $(this).attr('content-id');
        buynow(skuId,checkQuantity());
    });
    
    // 到货通知
    //浮动导航  waypoints.js
//    $('#main-nav').waypoint(function(event, direction) {
//        $(this).parent().parent().parent().toggleClass('sticky', direction === "down");
//        event.stopPropagation();
//    });

    // 规格选择
    $('dl[nctype="nc-spec"]').find('a').each(function(){
        $(this).click(function(){
            if ($(this).hasClass('hovered')) {
                return false;
            }
            $(this).parents('ul:first').find('a').removeClass('hovered');
            $(this).addClass('hovered');
            checkSpec();
            changeSkuInfo();
        });
    });

    /* 商品购买数量增减js */
    // 增加
    $('.increase').click(function(){
        num = parseInt($('#quantity').val());
		max = parseInt($('[nctype="goods_stock"]').text());
		alert(max);
        if(num < max){
            $('#quantity').val(num+1);
        }
    });
    //减少
    $('.decrease').click(function(){
		num = parseInt($('#quantity').val());
		if(num > 1){
			$('#quantity').val(num-1);
		}
	});

    //评价列表
//    $('#comment_tab').on('click', 'li', function() {
//        $('#comment_tab li').removeClass('current');
//        $(this).addClass('current');
//        load_goodseval($(this).attr('data-type'));
//    });

    $('.raty').raty({
        path: STATIC_URL + "/plugins/jquery-raty/img",
        readOnly: true,
        score: function() {
            return $(this).attr('data-score');
        }
    });

    //热销排行切换
    $('#hot_sales_tab').on('mouseenter', function() {
        $(this).addClass('current');
        $('#hot_collect_tab').removeClass('current');
        $('#hot_sales_list').removeClass('hide');
        $('#hot_collect_list').addClass('hide');
    });
    $('#hot_collect_tab').on('mouseenter', function() {
        $(this).addClass('current');
        $('#hot_sales_tab').removeClass('current');
        $('#hot_sales_list').addClass('hide');
        $('#hot_collect_list').removeClass('hide');
    });

    //评价列表
    $('#comment_tab').on('click', 'li', function() {
        $('#comment_tab li').removeClass('current');
        $(this).addClass('current');
        load_goodseval($(this).attr('data-type'));
    });

    $('a[nctype="nyroModal"]').nyroModal();

    /***
     * 选择规格前验证
     */
//    $('.sp-txt').click(function(){
//        var str = '';
//        $('.sp-txt').each(function(){
//            if ($(this).find('a').hasClass('hovered')){
//                str += $(this).find('a').attr('data-param') + 'a';
//            }
//        })
//        str = str.substring(0, str.length - 1);
//        var content_id = $('input[name=content_id]').val();
//        if (str){
//            $.get('/goods/index/checkGoodsExists?content_id=' + content_id + '&property=' + str, function(response){
//                if (response.status == 0){
//                    location.href = '/goods/index/detail?content_id=' + response.data.goods.content_id;
//                } else {
//                    alert(response.error);
//                }
//            }, 'json');
//
//        }
//    });

    $.getScript(STATIC_URL + '/front/modules/index/js/ImageZoom.js', function(){
    	var image = "";
        var
            zoomController,
            zoomControllerUl,
            zoomControllerUlLeft = 0,
            shell = $('#ncs-goods-picture'),
            shellPanel = shell.parent(),
            heightNcsDetail = $('div[class="ncs-detail"]').height();
        	heightOffset = 60,
            minGallerySize = [360, 360],
            imageZoom = new ImageZoom({
                shell: shell,
                basePath: '',
                levelASize: [60, 60],
                levelBSize: [320, 320],
                gallerySize: minGallerySize,
                onBeforeZoom: function(index, level){
                    if(!zoomController){
                        zoomController = shell.find('div.controller');
                    }

                    var
                        self = this,
                        duration = 320,
                        width = minGallerySize[0],
                        height = minGallerySize[1],
                        zoomFx = function(){
                            self.ops.gallerySize = [width, height];
                            self.galleryPanel.stop().animate({width:width, height:height}, duration);
                            shellPanel.stop().animate({height:height + heightOffset}, duration).css('overflow', 'visible');
                            zoomController.animate({width:width-22}, duration);
                            shell.stop().animate({width:width}, duration);
                        };
                    if(level !== this.level && this.level !== 0){
                        if(this.level === 1 && level > 1){
                            height = Math.max(480, shellPanel.height());
                            width = shellPanel.width();
                            zoomFx();
                        }
                        else if(level === 1){
                            zoomFx();
                            shellPanel.stop().animate({height:heightNcsDetail}, duration);
                        }
                    }
                },
                onZoom: function(index, level, prevIndex){
                    shell.find('a.prev,a.next')[level<3 ? 'removeClass' : 'addClass']('hide');
                    shell.find('a.close').css('display', [level>1 ? 'block' : 'none']);
                },
                items: image
            });
        shell.data('imageZoom', imageZoom);
    });

});

function checkSpec() {
    var spec_param = '';
    var spec = new Array();
    $('ul[nctyle="ul_sign"]').find('.hovered').each(function(){
        var data_str = ''; eval('data_str =' + $(this).attr('data-param'));
        spec.push(data_str.valid);
    });
    spec1 = spec.sort(function(a,b){
        return a-b;
    });
    var spec_sign = spec1.join('|');
    $.each(spec_param, function(i, n){
        if (n.sign == spec_sign) {
            window.location.href = n.url;
        }
    });
}

function changeSkuInfo() {
	var property = "";
	$('dl[nctype="nc-spec"]').find('a').each(function(){
		if ($(this).hasClass('hovered')) {
	        var v = $(this).attr("data-param");
	        property  += v.split("-")[0] + "_" + v.split("-")[1] + ",";
	    }
	});
	
	if (property.length > 0) {
		property = property.substring(0, property.length - 1);
	}
	
	var contentId = $("#contentId").val();
	var skuId = $("#skuId").val();
	window.location.href = BASE_URL + "/front/goodsContent/getDetail?contentId=" + contentId + "&propertyFront=" + property;
//	var param = {
//			skuId : skuId,
//			propertyFront : property
//	};
	
//	$.ajax({
//    	type:'post',
//        url:BASE_URL + '/front/goodsContent/detail',
//        data:param,
//        dataType:'json',
//        timeout:60000,
//        success:function(data){
////    		if (data.status == 0) {
////    			var sku = data.data;
////    			$("#skuPriceMarket").html(sku.priceMarket);
////    			$("#skuPrice").html(sku.price);
////    			$("#skuPriceEm").html("(原售价：¥"+sku.price+")");
////    			$("#skuStockEm").html(sku.stock);
////    			$("#skuId").val(sku.skuId);
////    			$("#skuValueId").val(sku.skuValueId);
////    			var href = "javascript:collect_goods("+sku.skuId+", 1);";
////    			$("#collect").attr("href", href);
////    		}
//    		return false;
//    	}
//    });
//	var skus = eval($("#contentSkus").val());
//	for (var i = 0; i < idArray.length; i++) {
//		alert(idArray[i].propertyId);
//		alert(idArray[i].propertyValueId);
//	}
}

// 验证购买数量
function checkQuantity(){
    var quantity = parseInt($("#quantity").val());
    if (quantity < 1) {
        alert("请填写购买数量");
        $("#quantity").val('1');
        return false;
    }
    max = parseInt($('[nctype="goods_stock"]').text());
    if(quantity > max){
        alert("库存不足");
        return false;
    }
    return quantity;
}

// 立即购买js
function buynow(skuId,quantity){
    if (!quantity) {
        return false;
    }
    $("#fastSkuId").val(skuId);
    $("#fastQuantity").val(quantity);
    $("#buynow_form").submit();
}

function load_goodseval(type) {
    var url = '';
    url += '&type=' + type;
    $("#goodseval").load(url, function(){
        $(this).find('[nctype="mcard"]').membershipCard({type:'shop'});
    });
}


var pageSize = 5;

function loadCommentData() {
	if($('#commentPaginationBar li').length > 0) {
		$('#commentPaginationBar').twbsPagination('destroy');
	}
	$('#commentPaginationBar').twbsPagination({
		totalPages: 1,
		startPage: 1,
		visiblePages: 1,
		first: '首页',
		prev: '上一页',
		next: '下一页',
		last: '尾页',
		onPageClick: function(event, page, index) {
			var paramJson = {skuId: $('#skuId').val()};
			paramJson = $.extend({}, paramJson, {pageSize : pageSize, pageIndex: page} );
			$.ajax({
				async: false,
				type:'post',
				url:BASE_URL+'/front/goodsContent/commentPage',
				data:paramJson,
				dataType:'html',
				timeout:60000,
				success:function(data){
					$('#ncGoodsRate .ncs-commend-floor').remove();
					var count = 0;
					
					$(data).each(function(){
						var type = this.nodeType;
						if(this.nodeType != 3) {
							if(this.nodeName == 'INPUT') {
								count = $(this).val();
							}
							$('#ncGoodsRate .ncs-goods-title-nav').after($(this));
						} 
					})
					$('#commentPaginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#commentPaginationBar li:eq(' +index+')').data('visiblePages',pageSize);
					$('#tabGoodsRate').html('商品评价<em>('+count+')</em>');
					if(count == 0) {
						$('#ncGoodsRate .ncs-norecord').show();
						$('#commentPaginationBar').hide();
					} else {
						$('.raty').raty({
					        path: STATIC_URL + "/plugins/jquery-raty/img",
					        readOnly: true,
					        score: function() {
					            return $(this).attr('data-score');
					        }
					    });
						$('#ncGoodsRate .ncs-norecord').hide();
						$('#commentPaginationBar').show();
						
					}
				}
			});
		}
	});
}

function loadSaleHistoryData() {
	if($('#saleHistoryPaginationBar li').length > 0) {
		$('#saleHistoryPaginationBar').twbsPagination('destroy');
	}
	$('#saleHistoryPaginationBar').twbsPagination({
		totalPages: 1,
		startPage: 1,
		visiblePages: 1,
		first: '首页',
		prev: '上一页',
		next: '下一页',
		last: '尾页',
		onPageClick: function(event, page, index) {
			var paramJson = {skuId: $('#skuId').val()};
			paramJson = $.extend({}, paramJson, {pageSize : pageSize, pageIndex: page} );
			$.ajax({
				async: false,
				type:'post',
				url:BASE_URL+'/front/goodsContent/saleHistoryPage',
				data:paramJson,
				dataType:'json',
				timeout:60000,
				success:function(data){
					$('.mt10 tbody').empty();
					var count = data.count;
					$(data.data).each(function(index, d){
						var html = '<tr>'
						html += '<td><a href="javascript:" target="_blank" data-param="" nctype="mcard" data-hasqtip="2">'+d.username +'</a></td>'
						html += '<td><em class="price">' + price_format(d.buyPrice) +'</em> <i style="color:red;"></i></td>';
						html += '<td>'+d.buyNum + '</td>';
						html += '<td><time>' +dateConverter(d.ctime, PATTERN_ENUM.datetime) +'</time></td>';
						html += '</tr>';
						$('.mt10 tbody').append(html);
							
					})
					$('#saleHistoryPaginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#saleHistoryPaginationBar li:eq(' +index+')').data('visiblePages',pageSize);
					$('#tabGoodsTraded').html('销售记录<em>('+count+')</em>');
					if(count == 0) {
						$('.mt10 tfoot tr:eq(0)').show();
						$('#saleHistoryPaginationBar').hide();
					} else {
						$('.mt10 tfoot tr:eq(0)').hide();
						$('#saleHistoryPaginationBar').show();
					}
				}
			});
		}
	});
}
