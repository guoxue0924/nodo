$(document).ready(function() {
	/**
	 * 预动态加载广告位
	 */
	get_board($('#pageId').val(), $('#type').val());

	/**
	 * 预处理日期选择控件
	 */
	$('.datepicker-input').datepicker();

	/**
	 * 编辑状态下，预加载绑定资源
	 */
	if ($('input[name=contentId]:last').val()) {
		var bindType = $('#bindType').val();
		if (bindType == 2) {
			refreshGoods();
		} else if (bindType == 3) {
			refreshArticle();
		}
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
	});

	/**
	 * 处理编辑快捷导航显示/隐藏事件
	 */
	$('.btn-nav-quick').click(function() {
		var i = $(this).attr('btn_nav_quick_index');
		if (i == 1) {
			switch_btn_nav_quick($('.btn-nav-quick:first'));
		} else {
			switch_btn_nav_quick($('.btn-nav-quick:last'));
		}
	});

	/**
	 * 切换资源类型编辑区
	 */
	$('#bindType').on('change', function() {
		var type_id = $(this).val();

		/* 切换资源类型编辑区 */
		$('.bind-type-edit').hide();
		$('#bind_type_' + type_id).show();

		/* 切换资源编辑区 */
		$('.bind-source-edit').hide();
		$('#bind_source_listing_' + type_id).show();

		/* 加载可供选择的资源 */
		if (type_id == 2) {
			if ($('#content_source_' + type_id).find('tbody').size() < 1) {
				refreshGoods();
			} else {
				reset_source_listing();
			}
		} else if (type_id == 3) {
			if ($('#content_source_' + type_id).find('tbody').size() < 1) {
				refreshArticle();
			} else {
				reset_source_listing();
			}
		}
	});

	/**
	 * 1、是否显示上传附件组件 2、动态加载广告位 - 广告类型变动时
	 */
	$('#type').on('change', function() {
		/* 是否显示上传附件组件 */
		$(this).val() == 2 ? $('#for_type').hide() : $('#for_type').show();

		/* 动态加载广告位 */
		get_board($('#pageId').val(), $('#type').val());
	});

	/**
	 * 动态加载广告位 - 所属页面变动时
	 */
	$('#pageId').on('change', function() {
		get_board($('#pageId').val(), $('#type').val());
	});

	/**
	 * 资源的刷新或搜索
	 */
	$('#action_search_goods').on('click', function() {
		$('#content_source_2').datagrid('reload');
		return false;
	});
	$('#action_search_article').on('click', function() {
		$('#content_source_3').datagrid('reload');
		return false;
	});

	/**
	 * 选择一个关联资源
	 */
	$(".bind-source-edit").delegate('.select-single', 'change', function() {
		select_source($(this).val());
	});

	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		var submit_type = $(this).attr("data_submit_type");
		switch (submit_type) {
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
});

/**
 * 处理编辑快捷导航显示/隐藏事件
 */
function switch_btn_nav_quick(obj) {
	if (obj.hasClass('active')) {
		obj.removeClass('active');
	} else {
		obj.addClass('active');
	}
}

/**
 * 动态加载广告位
 */
function get_board(pageId, type) {
	$('#boardId').find("option:gt(0)").remove();

	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertBoard/getBoardByPageId',
		data : 'pageId=' + pageId + '&type=' + type,
		timeout : 60000,
		success : function(data) {
			data = JSON.parse(data);
			if (data.status == 0) {
				var boardIdEdit = $('#boardIdEdit').val();
				$(data.data.boards).each(function() {
					if (this.boardId == boardIdEdit) {
						$('#boardId').append('<option value="' + this.boardId + '" selected>' + this.name + '</option>');
					} else {
						$('#boardId').append('<option value="' + this.boardId + '">' + this.name + '</option>');
					}
				});
			}
			return false;
		}
	});

	return false;
}

/**
 * 取消处理
 */
function form_cancel() {
	history.go(-1);
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);

	if (!$("select[name=pageId]").val() || $("select[name=pageId]").val() == 0) {
		notice('edit_notice', img_delete + " 请选择所属页面", true, 5000);
		return false;
	}
	if (!$("select[name=type]").val()) {
		notice('edit_notice', img_delete + " 请选择广告类型", true, 5000);
		return false;
	}
	if (!$("select[name=boardId]").val() || $("select[name=boardId]").val() == 0) {
		notice('edit_notice', img_delete + " 请选择所属广告位", true, 5000);
		return false;
	}
	if (!$("input[name=title]").val()) {
		notice('edit_notice', img_delete + " 广告标题不能为空", true, 5000);
		return false;
	}

	var contentId = $("input[name=contentId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (contentId == '' || contentId == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL + "/back/advertContent/edit");
		saveCallBack = form_save_edited;
	}

	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack
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

/**
 * 选择一个资源
 */
function select_source(content_id) {
	if (!content_id) {
		return false;
	}

	var type_id = $('#bindType').val();
	var title = $('#select_single_' + content_id).parent().next().next().text();
	$('#bind_source_' + type_id).val(content_id);
	$('#bind_source_view_' + type_id).val('[' + content_id + '] ' + title);
}

/**
 * 将商品/文章资源中已被选中的商品置为已选中状态
 */
function reset_source_listing() {
	var type_id = $('#bindType').val();
	var bind_source = $('#bind_source_' + type_id).val();
	$('#select_single_' + bind_source).prop('checked', true);

	select_source(bind_source);
}

/**
 * 加载商品列表
 */
function refreshGoods() {
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
			var url = BASE_URL + '/back/goodsContent/page';
			var self = this;

			setTimeout(function() {

				var data = $.extend(true, [], self._data);

				$.ajax(url, {
					data : {
						rstype : "json",
						pageIndex : options.pageIndex + 1,
						pageSize : options.pageSize,
						key : $('input[name=keyGoods]').val()
					},
					dataType : 'json',
					async : true,
					type : 'POST'
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

					// 将商品资源中已被选中的商品置为已选中状态
					reset_source_listing();
				}).fail(function(e) {

				});
			}, self._delay);
		}
	};

	$('#content_source_2').datagrid({
		dataSource : new DataGridDataSource({
			// Column definitions for Datagrid
			columns : [ {
				property : 'radio',
				label : ''
			}, {
				property : 'contentId',
				label : 'ID',
				sortable : false
			}, {
				property : 'name',
				label : '商品名称', // 显示商品别名
				sortable : false
			}, {
				property : 'categoryName',
				label : '所属分类',
				sortable : false
			}, {
				property : 'sku',
				label : 'SKU',
				sortable : false
			}, {
				property : 'price',
				label : '价格',
				sortable : false
			}, {
				property : 'stock',
				label : '库存',
				sortable : false
			}, {
				property : 'isShelf',
				label : '上架',
				sortable : false
			}, {
				property : 'ctime',
				label : '发布时间',
				sortable : false
			} ],
			formatter : function(items) {
				$.each(items, function(index, item) {
					item.radio = '<input type="radio" name="post[]" id="select_single_' + item.contentId + '" class="select-single" value="' + item.contentId + '">';
					item.price = '￥' + parseInt(item.price).toFixed(2);
					item.isShelf = item.isShelf == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';
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

/**
 * 加载文章列表
 */
function refreshArticle() {
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
			var url = BASE_URL + '/back/articleadmin/page';
			var self = this;

			setTimeout(function() {

				var data = $.extend(true, [], self._data);

				$.ajax(url, {
					data : {
						rstype : "json",
						pageIndex : options.pageIndex + 1,
						pageSize : options.pageSize,
						key : $('input[name=keyArticle]').val()
					},
					dataType : 'json',
					async : true,
					type : 'GET'
				}).done(function(response) {
					var data = response.data.data;
					if (!data) {
						return false;
					}

					var count = response.data.count;// 设置data.total
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

					// 将文章资源中已被选中的文章置为已选中状态
					reset_source_listing();
				}).fail(function(e) {

				});
			}, self._delay);
		}
	};

	$('#content_source_3').datagrid({
		dataSource : new DataGridDataSource({
			// Column definitions for Datagrid
			columns : [ {
				property : 'radio',
				label : ''
			}, {
				property : 'contentId',
				label : 'ID',
				sortable : true
			}, {
				property : 'title',
				label : '标题',
				sortable : false
			}, {
				property : 'categoryName',
				label : '所属分类',
				sortable : false
			}, {
				property : 'pinned',
				label : '权重',
				sortable : true
			}, {
				property : 'publish',
				label : '发布',
				sortable : false
			}, {
				property : 'ctime',
				label : '发布时间',
				sortable : true
			}, ],
			formatter : function(items) {
				$.each(items, function(index, item) {
					item.radio = '<input type="radio" name="post[]" id="select_single_' + item.contentId + '" class="select-single" value="' + item.contentId + '">';
					item.publish = item.status == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';
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