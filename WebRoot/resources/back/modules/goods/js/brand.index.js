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
	 * 单个删除品牌
	 */
//	$("#content_listing").delegate('.operate-delete', 'click', function() {
//		var brandId = $(this).attr("brandId");
//		doDelete(brandId);
//	});

	/**
	 * 批量删除品牌
	 */
	$('#action_delete').on('click', function() {
		var idArr = new Array();
		var i = 0;
		$('#content_listing').find('.select-single').each(function() {
			if ($(this).is(':checked')) {
				idArr[i] = $(this).val();
				i++;
			}
		});
		var id = idArr.join(',');

		if (!id) {
			return false;
		}

		doDelete(id);
	});
});

function del(brandId) {
	doDelete(brandId);
}

/**
 * 删除
 */
function doDelete(id) {
	var del = confirm('确定要删除所选品牌吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/goodsBrand/delete',
		data : 'brandIds=' + id,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if (parseInt(id) == id) {
					$("#brand_" + id).parent().parent().remove();
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
				label : ''
			}, {
				property : 'brandId',
				label : 'ID',
				sortable : true
			}, {
				property : 'brandLogo',
				label : '标志',
				sortable : true
			}, {
				property : 'brandName',
				label : '品牌名称',
				sortable : false
			}, {
				property : 'brandUrl',
				label : '链接网址',
				sortable : false
			}, {
				property : 'ctime',
				label : '创建时间',
				sortable : true
			}, {
				property : 'operate',
				label : '操作'
			} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.brandId + '">';

		if (item.brandLogo) {
			item.brandLogo = '<a class="thumb m-l" href="javascript:;">' + '<img src="' + IMG_URL + item.brandLogo + '">' + '</a>';
		} else {
			item.brandLogo = '';
		}

		item.operate = '<a href="' + BASE_URL + '/back/goodsBrand/edit?brandId=' + item.brandId
				+ '" data-toggle="ajaxModal" class="operate-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="brand_' + item.brandId + '" brandId="' + item.brandId
				+ '" title="删除" onclick="del('+item.brandId+')"><i class="fa fa-times"></i></a>';
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}