$(document).ready(function() {
	// 自动改变窗口大小
	changeHeight();

	loadDataGridContent(columnsDefined(), 'formatData');

	/**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function() {
		$('#content_listing').datagrid('reload');
		return false;
	});

	/**
	 * 关键字搜索 - 支持回车
	 */
	$("input[name=key]").on('keypress', function(event) {
		if (event.which == '13') {
			$('#content_listing').datagrid('reload');
			return false;
		}
	});

	/**
	 * 上架
	 */
	$('#content_listing').delegate('.switch-sm', 'click', function() {
		var content_id = $(this).attr('content_id');
		var status = $('#publish_' + content_id).is(':checked') ? 1 : 0;
		publishContent(content_id, status);
		return false;
	});

	/**
	 * 扔进回收站 - 单条
	 */
	$("#content_listing").delegate('.operate-trash', 'click', function() {
		var content_id = $(this).attr("content_id");
		doTrashContent(content_id);
	});

	/**
	 * 扔进回收站 - 批量
	 */
	$('#action_delete').on('click', function() {
		var id_arr = new Array();
		var i = 0;
		$('#content_listing').find('.select-single').each(function() {
			if ($(this).is(':checked')) {
				id_arr[i] = $(this).val();
				i++;
			}
		});
		var id = id_arr.join(',');

		if (!id) {
			return false;
		}

		doTrashContent(id);
	});

});

/**
 * 浏览器大小改变时调用
 */
$(window).resize(function() {
	changeHeight();
});

/**
 * 根据搜索条件栏高度调整列表的高度
 */
function changeHeight() {
	var header = $("#viewHeader");
	var body = $("#viewBody");
	var height = header.outerHeight(true);
	body.attr("style", "top:" + height + "px;");
}

/**
 * 上架功能
 * 
 * @param id
 * @param status
 */
function publishContent(id, status) {
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/goodsContent/publish',
		data : 'contentId=' + id + '&status=' + status,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if ($('#publish_' + id).is(':checked')) {
					$('#publish_' + id).prop('checked', false);
				} else {
					$('#publish_' + id).prop('checked', true);
				}
			} else {
				alert(data.msg);
			}
			return false;
		}
	});
}

/**
 * 删除 - 扔进回收站
 */
function doTrashContent(contentId) {
	var del = confirm('确定要将所选商品扔进回收站吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/goodsContent/trash',
		data : 'contentIds=' + contentId,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if (parseInt(contentId) == contentId) {
					$("#content_" + contentId).parent().parent().remove();
				} else {
					$('#content_listing').find('.select-single:checked').parent().parent().remove();
				}
			} else {
				alert(data.msg);
			}
			return false;
		}
	});
}

function columnsDefined() {
	return [ {
		property : 'checkbox',
		label : '<input type="checkbox" />'
	}, {
		property : 'contentId',
		label : 'ID',
		sortable : false
	}, {
		property : 'name',
		label : '商品名称',
		sortable : false
	}, {
		property : 'categoryName',
		label : '所属分类',
		sortable : false
	}, {
		property : 'price',
		label : '销售价',
		sortable : false
	}, {
		property : 'priceMarket',
		label : '市场价',
		sortable : false
	}, {
		property : 'priceIntegral',
		label : '积分价',
		sortable : false
	}, {
		property : 'stock',
		label : '库存',
		sortable : false
	}, {
		property : 'goodsWeight',
		label : '重量(g)',
		sortable : false
	}, {
		property : 'brandName',
		label : '品牌',
		sortable : false
	}, {
		property : 'ctime',
		label : '发布时间',
		sortable : false
	}, {
		property : 'isShelf',
		label : '上架',
		sortable : false
	}, {
		property : 'action',
		label : '操作',
		sortable : false
	} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.contentId + '">';

		item.price = '￥' + parseInt(item.price).toFixed(2);
		item.priceMarket = '￥' + parseInt(item.priceMarket).toFixed(2);
		item.goodsWeight = item.weight;
		item.brandName = item.brandName ? item.brandName : '';
		item.storeName = item.storeName ? item.storeName : '';

		item.isVerify = item.isVerify == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';

		var isShelf = item.isShelf == 1 ? 'checked="checked"' : '';
		item.isShelf = '<label class="switch-sm" content_id="' + item.contentId + '">' + '<input type="checkbox" id="publish_' + item.contentId + '" ' + isShelf + ' />'
				+ '<span></span></label>';

		item.action = '<a href="' + BASE_URL + '/back/goodsContent/edit?contentId=' + item.contentId
				+ '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + '<a href="javascript:;" class="operate-trash" id="content_' + item.contentId
				+ '" content_id="' + item.contentId + '" title="删除(扔进回收站)"><i class="fa fa-trash-o"></i></a>';
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}