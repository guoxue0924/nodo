$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');

	/**
	 * 刷新搜索
	 */
	$(".action-refresh,#action_search").on('click', function() {
		$('#content_listing').datagrid('reload');
		return false;
	});

	/**
	 * 关键字搜索 - 支持回车
	 */
	$('input[name=key]').on('keypress', function(event) {
		if (event.which == '13') {
			$('#content_listing').datagrid("reload");
			return false;
		}
	});

	/**
	 * 单个删除分类
	 */
	$('body').delegate('#content_listing .operate-delete', 'click', function() {
		var tr = $(this).closest('tr'), cid = tr.find(".select-single").val();
		if (!cid || !confirm('确定要删除此分类吗？'))
			return false;
		$.ajax({
			type : 'post',
			url : BASE_URL + '/back/goodsCategory/delete',
			data : 'categoryIds=' + cid,
			dataType : 'json',
			timeout : 10000,
			beforeSend : function() {
				$(tr).addClass('remove');
			},
			success : function(data) {
				if (data.status == 0) {
					$(tr).fadeIn().remove();
				} else {
					alert(data.msg);
				}
				return false;
			},
			complete : function() {
				$(tr).removeClass('remove');
			}

		});
	});

	/**
	 * 批量删除分类
	 */
	$('body').delegate('#action_delete', 'click', function() {
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

		var del = confirm('确定要删除所选分类吗？');
		if (!del) {
			return false;
		}

		/* 执行删除 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/back/goodsCategory/delete',
			data : 'categoryIds=' + id,
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				if (data.status == 0) {
					$('#content_listing').find('.select-single:checked').parent().parent().remove();
				} else {
					alert(data.msg);
				}
				return false;
			}
		});
	});
});

function columnsDefined() {
	return [ {
		property : 'checkbox',
		label : ''
	}, {
		property : 'categoryId',
		label : 'ID',
		sortable : true
	}, {
		property : 'categoryName',
		label : '分类名称',
		sortable : false
	}, {
		property : 'parentName',
		label : '父级分类名称',
		sortable : false
	}, {
		property : 'sortOrder',
		label : '序号',
		sortable : false
	}, {
		property : 'operate',
		label : '操作'
	} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.categoryId + '">';

		item.operate = '<a href="' + BASE_URL + '/back/goodsCategory/bindBrand/?categoryId=' + item.categoryId
				+ '" class="load-content" title="绑定品牌"><i class="fa fa-bitcoin"></i></a>&nbsp;&nbsp;' + '<a href="' + BASE_URL + '/back/goodsCategory/bindProperty/?categoryId='
				+ item.categoryId + '" class="load-content" title="绑定属性"><i class="fa fa-gear"></i></a>&nbsp;&nbsp;' + '<a href="' + BASE_URL
				+ '/back/goodsCategory/edit/?categoryId=' + item.categoryId
				+ '" data-toggle="ajaxModal" class="operate-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" title="删除"><i class="fa fa-times"></i></a>';

	});
}