$(document).ready(function() {

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
	 * 彻底删除 - 单条
	 */
	$("#content_listing").delegate('.operate-delete', 'click', function() {
		var contentId = $(this).attr("content_id");
		doDeleteContent(contentId);
	});

	/**
	 * 彻底删除 - 批量
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

		doDeleteContent(id);
	});
});

/**
 * 彻底删除
 */
function doDeleteContent(id) {
	var del = confirm('确定要将所选商品彻底删除吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/goodsTrash/delete',
		data : 'contentIds=' + id,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if (parseInt(id) == id) {
					$("#content_" + id).parent().parent().remove();
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

/**
 * 还原商品
 * 
 * @param contentId
 * @returns {Boolean}
 */
function returnGoods(contentId) {
	var del = confirm('确定要将所选商品还原吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/goodsTrash/returnGoods',
		data : 'contentId=' + contentId,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if (parseInt(contentId) == contentId) {
					$("#return_content_" + contentId).parent().parent().remove();
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
		property : 'a',
		label : ''
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
		property : 'action',
		label : '操作',
		sortable : false
	} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.contentId + '">';

		item.a = '<a href="goods/preview?content_id=' + item.contentId + '" target="_blank"><i class="fa fa-search-plus"></i></a>';

		item.price = '￥' + parseInt(item.price).toFixed(2);
		item.priceMarket = '￥' + parseInt(item.priceMarket).toFixed(2);
		item.goodsWeight = item.weight;
		item.brandName = item.brandName ? item.brandName : '';

		item.action = '<a href="javascript:;" class="load-content" id="return_content_' + item.contentId + '" onclick="returnGoods(' + item.contentId + ');" content_id="'
				+ item.contentId + '" title="还原商品"><i class="fa fa-bitcoin"></i></a>&nbsp;&nbsp;<a href="javascript:;" class="operate-delete" id="content_' + item.contentId
				+ '" content_id="' + item.contentId + '" title="彻底删除"><i class="fa fa-trash-o"></i></a>';

	});
}

function refreshListing() {
	/* fuelux datagrid */
	var DataGridDataSource = function(options) {
		this._formatter = options.formatter;
		this._columns = options.columns;
		this._delay = options.delay;
	};

	DataGridDataSource.prototype = {
		columns : function() {
			return this._columns;
		},
		data : function(options, callback) {
			var url = BASE_URL + '/back/goodsTrash/page';
			var self = this;

			setTimeout(function() {

				var data = $.extend(true, [], self._data);

				$.ajax(url, {
					data : {
						rstype : "json",
						pageIndex : options.pageIndex + 1,
						pageSize : options.pageSize,
						categoryId : $('select[name=categoryId]').val(),
						brandId : $('select[name=brandId]').val(),
						isVerify : $('select[name=isVerify]').val(),
						isShelf : $('select[name=isShelf]').val(),
						priceMin : $('input[name=priceMin]').val(),
						priceMax : $('input[name=priceMax]').val(),
						key : $('input[name=key]').val()
					},
					dataType : 'json',
					async : true,
					type : 'GET'
				}).done(function(response) {
					var data = response.data;
					if (!data) {
						return false;
					}

					var count = response.count;// 设置data.total
					// PAGING
					var startIndex = options.pageIndex * options.pageSize;
					var endIndex = startIndex + options.pageSize;
					var end = (endIndex > count) ? count : endIndex;
					var pages = Math.ceil(count / options.pageSize);
					var page = options.pageIndex + 1;
					var start = startIndex + 1;

					if (self._formatter)
						self._formatter(data);

					callback({
						data : data,
						start : start,
						end : end,
						count : count,
						pages : pages,
						page : page
					});
				}).fail(function(e) {

				});
			}, self._delay);
		}
	};

	$('#content_listing')
			.datagrid(
					{
						dataSource : new DataGridDataSource(
								{
									// Column definitions for Datagrid
									columns : [ {
										property : 'checkbox',
										label : '<input type="checkbox" />'
									}, {
										property : 'a',
										label : ''
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
										property : 'action',
										label : '操作',
										sortable : false
									} ],
									formatter : function(items) {
										$
												.each(
														items,
														function(index, item) {
															item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.contentId + '">';

															item.a = '<a href="goods/preview?content_id=' + item.contentId
																	+ '" target="_blank"><i class="fa fa-search-plus"></i></a>';

															item.price = '￥' + parseInt(item.price).toFixed(2);
															item.priceMarket = '￥' + parseInt(item.priceMarket).toFixed(2);
															item.goodsWeight = item.weight;
															item.brandName = item.brandName ? item.brandName : '';

															item.action = '<a href="javascript:;" onclick="returnGoods('
																	+ item.contentId
																	+ ');" class="load-content" title="还原商品"><i class="fa fa-bitcoin"></i></a><a href="javascript:;" class="operate-delete" title="彻底删除"><i class="fa fa-bitcoin"></i></a><a href="javascript:;" class="operate-delete" id="content_'
																	+ item.contentId + '" content_id="' + item.contentId + '" title="彻底删除"><i class="fa fa-trash-o"></i></a>';
														});
									}
								}),
						loadingHTML : '<span><img src="' + STATIC_URL + '/back/images/loading.gif"><i class="fa fa-info-sign text-muted" "></i>正在加载……</span>',
						itemsText : '项',
						itemText : '项',
						dataOptions : {
							pageIndex : 0,
							pageSize : 15
						}
					});
}