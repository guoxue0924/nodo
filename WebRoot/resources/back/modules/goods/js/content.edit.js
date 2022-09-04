/**
 * 定义一些常量
 */
var contentId = $('#contentId').val();

$(document).ready(function() {
	/**
	 * 表单初始化
	 */
	if (!content_id) {
		$('input[name=attachment_ids]:last').val('');
	}

	/**
	 * 切换模板编辑位置
	 */
	$('.nav-map').click(function() {
		/* 模板导航的样式处理 */
		$('.nav-map').removeClass('active');
		$(this).addClass('active');

		/* 切换编辑区域 */
		$('.edit-map').hide();
		var nav_id = $(this).attr('id');
		var edit_id = nav_id.replace('nav', 'edit');
		$('#' + edit_id).show();

		if ($('#' + edit_id).attr('id') == 'edit_related') {
			$('#' + edit_id).css({
				'display' : 'table'
			});
		}
	});

	/**
	 * 处理编辑商品快捷导航显示/隐藏事件
	 */
	$('.btn-nav-goods').click(function() {
		var i = $(this).attr('btn_nav_goods_index');
		if (i == 1) {
			switch_btn_nav_goods($('.btn-nav-goods:first'));
		} else {
			switch_btn_nav_goods($('.btn-nav-goods:last'));
		}
	});

	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		var submit_id = $(this).attr("id");
		switch (submit_id) {
		case 'submit_cancel':
			form_cancel();
			break;
		case 'submit_save_back':
			back_listing = true;
			form_submit();
			break;
		case 'submit_save_continue':
			back_listing = false;
			form_submit();
			break;
		}
	});

	/**
	 * 处理编辑商品快捷导航显示/隐藏事件
	 */
	function switch_btn_nav_goods(obj) {
		if (obj.hasClass('active')) {
			obj.removeClass('active');
		} else {
			obj.addClass('active');
		}
	}

	/**
	 * 上传图片
	 */
	$("#img_upload").change(function() {
		action = BASE_URL + '/back/goodsContent/uploadFile?time=' + (new Date()).getTime();
		if ($("#g_upload").length == 0)
			$("#img_upload").wrap("<form id='g_upload' action='" + action + "' method='post' enctype='multipart/form-data'></form>");
		$("#g_upload").ajaxSubmit({
			dataType : 'json',
			timeout : 10000,
			beforeSend : function() {
				$('#img_upload').attr('disabled', true);
			},
			success : function(data) {
				if (data.url) {
					data.url = BASE_URL + '/back/upload' + data.url;
					data.thumbUrl = data.thumbUrl ? data.thumbUrl : data.url;
					$(".goods_img_box .img_box_s").html('<img src="' + data.url + '"/>');
					$(".goods_img_box .gpic_box span").removeClass('current');
					html = '<div class="gpic_box"><span class="current"><img src="' + data.thumbUrl + '"></span>';
					html += '<div class="spic_box_btn"><a href="javascript:;" class="i_arrow"><i class="fa fa-arrow-left"></i></a>';
					html += '<a href="javascript:;" class="i_arrow">&nbsp;<i class="fa fa-arrow-right"></i></a>';
					html += '<a href="javascript:;" class="i_remove">&nbsp;<i class="fa fa-times"></i></a></div>';
					html += '<input type="hidden" name="default_images[defaultUrl][]" value="' + data.url + '" class="default_url">';
					html += '<input type="hidden" name="default_images[thumb][]" value="' + data.thumbUrl + '" class="thumb_url">';
					$(".goods_img_box .img_box_list").append(html);
				} else {
					alert('上传失败');
				}
			},
			complete : function() {
				$('#img_upload').val('').attr('disabled', false);
			},
			error : function(data) {
				alert('上传失败');
			}
		});
	});

	/**
	 * 删除商品图片
	 */
	$('body').delegate('.goods_img_box .i_remove', 'click', function() {
		var box = $(this).closest('.gpic_box');
		var len = $('.img_box_list').find('.gpic_box').length;
		box.remove();
		if (box.find('span:first').hasClass('current')) {
			$('.img_box_list .gpic_box span').removeClass('current');
			cur = $('.img_box_list .gpic_box:first');
			cur.find('span:first').addClass('current');
			cur_val = cur.find('.default_url').val();
			$(".goods_img_box .img_box_s").html('<img src="' + cur_val + '"/>');

		}
		if (len == 1)
			$(".goods_img_box .img_box_s").html('您还未上传商品图片.');
	});

	/**
	 * 商品图片左右移 事件
	 */
	$('body').delegate('.goods_img_box .i_arrow', 'click', function() {
		var isL = $(this).find('i').hasClass('fa-arrow-left');
		var box = $(this).closest('.gpic_box');
		var n = $('.img_box_list .gpic_box').index(box);
		var cur_val = box.find('.default_url').val();

		if (isL) {
			if (n > 0) {
				$('.img_box_list .gpic_box span').removeClass('current');
				pre = $('.img_box_list .gpic_box').eq(n - 1);
				pre_val = pre.find('.default_url').val();
				$(".goods_img_box .img_box_s").html('<img src="' + cur_val + '"/>');
				pre.find('span:first').addClass('current');
				pre.find('.default_url').val(cur_val);
				pre.find('span:first img').attr('src', cur_val);
				pre.find('.thumb_url').val(cur_val);
				box.find('.default_url').val(pre_val);
				box.find('.thumb_url').val(pre_val);
				box.find('span:first img').attr('src', pre_val);
			}
		} else {
			len = $('.img_box_list .gpic_box').length;
			if (len - 1 > n) {
				$('.img_box_list .gpic_box span').removeClass('current');
				next = $('.img_box_list .gpic_box').eq(n + 1);
				// last = $('.img_box_list
				// .gpic_box').eq(n).find('.default_url').val();

				next_val = next.find('.default_url').val();
				// alert(last+'\n'+next_val);
				$(".goods_img_box .img_box_s").html('<img src="' + cur_val + '"/>');
				next.find('span:first').addClass('current');
				next.find('.default_url').val(cur_val);
				next.find('.thumb_url').val(cur_val);
				next.find('span:first img').attr('src', cur_val);
				box.find('.default_url').val(next_val);
				box.find('.thumb_url').val(next_val);
				box.find('span:first img').attr('src', next_val);
			}
		}
	});

});

/**
 * 取消处理
 */
function form_cancel() {
	history.go(-1);

}

function check(){
	if (!$("#categoryId").val()) {
		notice('edit_notice', img_delete + ' 请选择所属分类', true, 5000);
		return false;
	}
	if (!$("#brandId").val()) {
		notice('edit_notice', img_delete + ' 请选择所属品牌', true, 5000);
		return false;
	}
	if (!$("#name").val()) {
		notice('edit_notice', img_delete + ' 请输入商品名称', true, 5000);
		return false;
	}
	
//	var selectedData = [];
	var v = false;
	$(":checkbox:checked","#spec_option_list").each(function(){
		if ($(this).val() != null && $(this).val() != "") {
			v = true;
		}
//	  var tablerow = $(this).parent("tr");
//	  var code = tablerow.find("[name='p_code']").val();
//	  var name= tablerow.find("[name='p_name']").val();
//	  var price= tablerow.find("[name='p_price']").val();
//	  selectedData.push({Code:code,Name:name,Price:price});
	});
	if (!v) {
		notice('edit_notice', img_delete + ' 请选择sku信息', true, 5000);
		return false;
	}
	
	var stockArray = $("input[name='stock']");
	var priceArray = $("input[name='price']");
	var marketArray = $("input[name='priceMarket']");
	var costArray = $("input[name='priceCost']");
	var weightArray = $("input[name='weight']");
	var lengthArray = $("input[name='length']");
	var wideArray = $("input[name='wide']");
	var heightArray = $("input[name='height']");
	if (stockArray != null) {
		for (var i = 0; i < stockArray.length; i++) {
			if (!checkStock(stockArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的库存', true, 5000);
				return false;
			}
			if (!checkNum(priceArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的价格', true, 5000);
				return false;
			}
			if (!checkNum(marketArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的市场价', true, 5000);
				return false;
			}
			if (!checkNum(costArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的成本价', true, 5000);
				return false;
			}
			if (!checkStock(weightArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的重量', true, 5000);
				return false;
			}
			if (!checkStock(lengthArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的长', true, 5000);
				return false;
			}
			if (!checkStock(wideArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的宽', true, 5000);
				return false;
			}
			if (!checkStock(heightArray[i].value)){
				notice('edit_notice', img_delete + ' 请输入正确的高', true, 5000);
				return false;
			}
		}
	}
	return true;
}

function checkNum(num) {
    var exp =  /^([1-9]\d{0,10})(\.[0-9]{2})?$/;
    if(exp.test(num)){
    	return true;
    }else{
    	return false;
    }
}

function checkStock(num) {
	var exp =  /^[0-9]*$/;
    if(exp.test(num)){
    	return true;
    }else{
    	return false;
    }
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);

	var flag = check();
	if (!flag) {
		return false;
	}
	
	// if (! $("input[name=title]").val()) {
	// notice('edit_notice', img_delete + ' 文章标题不能为空', true, 50000);
	// return false;
	// }

	/* 规格校验 */
	// if ($('#isSpec').is(':checked')) {
	// // 若当前分类没有绑定规格，则进行提示，并关闭规格
	// if (! $('#edit_base_spec>.panel-body:last').is(':hidden')) {
	// var confirm_spec = '当前分类尚未绑定任何规格，系统将自动关闭规格项。继续提交，请确定；返回修改，请取消。';
	// if (! confirm(confirm_spec)) {
	// return false;
	// }
	//			
	// $('#isSpec').prop('checked', false);
	// } else {
	// // 若当前分类绑定了规格，但提交时没有填写规格项，则进行提示，并阻止提交
	// if (! $('#spec_list > tbody').html()) {
	// notice('edit_notice', img_delete + ' 请至少添加一组规格', true, 50000);
	// return false;
	// }
	// }
	// }
	/* 处理关联商品信息 */
	var related_content_ids = '';
	var related_content_ids_arr = new Array();
	// $('#selected_related_goods').find('ul>li').each(function(k){
	// var content_id_selected = $(this).attr('content_id');
	// related_content_ids_arr[k] = content_id_selected;
	// });
	// if (related_content_ids_arr.length > 0) {
	// related_content_ids = related_content_ids_arr.join(',');
	// }
	// $('input[name=relatedContentId]').val(related_content_ids);

	$(".input-submit").attr('disabled', true);

	/* 更新编辑器状态 */
	CKupdate();

	var content_id = $("#content_id").val();

	var saveCallBack;
	if (content_id == '' || content_id == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL + "/back/goodsContent/edit");
		saveCallBack = form_save_edited;
	}

	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack,
		error : ajaxError
	};
	$("#edit_form").ajaxSubmit(options);
	return false;
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
	if (data.status === 0) {
		notice('edit_notice', img_done + ' 添加成功!', true, 5000);

		// 判断是否返回列表管理
		if (back_listing == true) {
			history.back(-1);
		}
	} else {
		notice('edit_notice', img_delete + " " + data.msg, true, 5000);
	}
	$(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
	if (data.status === 0) {
		notice('edit_notice', img_done + ' 编辑成功!', true, 5000);
		history.back(-1);
	} else {
		notice('edit_notice', img_delete + " " + data.msg, true, 5000);
	}
	$(".input-submit").removeAttr('disabled');
}