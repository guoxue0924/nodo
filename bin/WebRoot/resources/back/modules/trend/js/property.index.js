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
	 * 单个删除
	 */
	$("#content_listing").delegate('.operate-delete', 'click', function() {
		var propertyId = $(this).attr("propertyId");
		doDeleteProperty(propertyId);
	});

	/**
	 * 批量删除分类
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

		doDeleteProperty(id);
	});
});

/**
 * 删除
 */
function doDeleteProperty(id) {
	var del = confirm('确定要删除所选属性吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/trendProperty/delete',
		data : 'ids=' + id,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if (parseInt(id) == id) {
					$("#property_" + id).parent().parent().remove();
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
 * 开启规格
 */
$('#content_listing').delegate('.switch-spec', 'click', function() {
	var propertyId = $(this).attr('property_id');
	var isSpec = $(this).attr('is_spec');
	publishSpec(propertyId, isSpec);
	return false;
});

/**
 * 开启规格
 * 
 * @param propertyId
 *            属性ID
 * @param isSpec
 *            是否设为定价属性（设为定价属性后将和价格、库存等相关联）。1：是；0：否；
 */
function publishSpec(propertyId, isSpec) {
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/trendProperty/changeSpec',
		data : 'propertyId=' + propertyId + '&isSpec=' + isSpec,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if ($('#spec_' + propertyId).prop('checked')) {
					$('#spec_' + propertyId).prop('checked', false);
					$("#spec_" + propertyId).parent().attr("is_spec", 0);
				} else {
					$('#spec_' + propertyId).prop('checked', true);
					$("#spec_" + propertyId).parent().attr("is_spec", 1);
				}
			} else {
				alert(data.msg);
			}
			return false;
		}
	});
}

/**
 * 修改状态
 */
$('#content_listing').delegate('.switch-status', 'click', function() {
	var propertyId = $(this).attr('property_id');
	var status = $(this).attr('status');
	publishCategory(propertyId, status);
	return false;
});

/**
 * 修改状态
 * 
 * @param propertyId
 *            属性ID
 * @param status
 *            状态。1：启用；0：不启用；
 */
function publishCategory(propertyId, status) {
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/trendProperty/publish',
		data : 'propertyId=' + propertyId + '&status=' + status,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if ($('#publish_' + propertyId).prop('checked')) {
					$('#publish_' + propertyId).prop('checked', false);
					$("#publish_" + propertyId).parent().attr("status", 0);
				} else {
					$('#publish_' + propertyId).prop('checked', true);
					$("#publish_" + propertyId).parent().attr("status", 1);
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
		property : 'propertyId',
		label : 'ID',
		sortable : true
	}, {
		property : 'labelName',
		label : '属性名称',
		sortable : false
	}, {
		property : 'propertyValues',
		label : '属性值',
		sortable : false
	}, {
		property : 'note',
		label : '备注',
		sortable : false
	}, {
		property : 'sortOrder',
		label : '序号',
		sortable : false
	}, {
		property : 'isSpec',
		label : '开启规格',
		sortable : false
	}, {
		property : 'status',
		label : '状态',
		sortable : false
	}, {
		property : 'operate',
		label : '操作'
	} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.propertyId + '" />';

		if (item.isSpec == 1) {
			item.isSpec = '<label class="switch-sm switch-spec" title="开启" property_id="' + item.propertyId + '" is_spec="' + item.isSpec + '"">' + '<input type="checkbox" id="spec_'
					+ item.propertyId + '" id="spec_' + item.isSpec + '" checked="checked" />' + '<span></span></label>';
		} else {
			item.isSpec = '<label class="switch-sm switch-spec" title="不开启" property_id="' + item.propertyId + '" is_spec="' + item.isSpec + '"">' + '<input type="checkbox" id="spec_'
					+ item.propertyId + '" id="spec_' + item.isSpec + '" />' + '<span></span></label>';
		}
		if (item.status == 1) {
			item.status = '<label class="switch-sm switch-status" title="启用" property_id="' + item.propertyId + '" status="' + item.status + '"">' + '<input type="checkbox" id="publish_'
					+ item.propertyId + '" id="status_' + item.status + '" checked="checked" />' + '<span></span></label>';
		} else {
			item.status = '<label class="switch-sm switch-status" title="不启用" property_id="' + item.propertyId + '" status="' + item.status + '"">' + '<input type="checkbox" id="publish_'
					+ item.propertyId + '" id="status_' + item.status + '" />' + '<span></span></label>';
		}

		item.operate = '<a href="' + BASE_URL + '/back/trendProperty/edit/?propertyId=' + item.propertyId
				+ '" class="operate-edit load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + '<a href="javascript:;" class="operate-delete" id="property_'
				+ item.propertyId + '" propertyId="' + item.propertyId + '" title="删除"><i class="fa fa-times"></i></a>';
	});
}