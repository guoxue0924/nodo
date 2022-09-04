$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');

	/**
	 * 刷新或搜索
	 */
	$('body').delegate('.action-refresh, #action_search', 'click', function() {
		$('#content_listing').datagrid('reload');
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
	 * 审核
	 */
	$('#content_listing').delegate('.switch-sm', 'click', function() {
		var board_id = $(this).attr('board_id');
		var status = $(this).attr('status');
		publishBoard(board_id, status);
		return false;
	});

	/**
	 * 单个删除
	 */
//	$('body').delegate('.operate-delete', 'click', function() {
//		var del = confirm('确定要删除该广告位吗？');
//		if (!del) {
//			return false;
//		}
//
//		var board_id = $(this).attr("board_id");
//
//		/* 执行 */
//		$.ajax({
//			type : 'post',
//			url : BASE_URL + '/back/advertBoard/delete',
//			data : 'boardId=' + board_id,
//			dataType : 'json',
//			timeout : 10000,
//			success : function(data) {
//				if (data.status == 0) {
//					$("#board_" + board_id).parent().parent().remove();
//				} else {
//					alert(data.msg);
//				}
//				return false;
//			}
//		});
//	});
});

function del(boardId){
	var del = confirm('确定要删除该广告位吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertBoard/delete',
		data : 'boardId=' + boardId,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$("#board_" + boardId).parent().parent().remove();
			} else {
				alert(data.msg);
			}
			return false;
		}
	});
}

/**
 * 审核
 */
function publishBoard(boardId, status) {
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertBoard/publish',
		data : 'boardId=' + boardId + '&status=' + status,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if ($('#publish_' + boardId).prop('checked')) {
					$('#publish_' + boardId).prop('checked', false);
					$("#publish_"+boardId).parent().attr("status", 0);
				} else {
					$('#publish_' + boardId).prop('checked', true);
					$("#publish_"+boardId).parent().attr("status", 1);
				}
			} else {
				alert(data.error);
			}
			return false;
		}
	});
}

function columnsDefined() {
	return [
			{
				property : 'boardId',
				label : 'ID',
				sortable : false
			}, {
				property : 'name',
				label : '广告位名称',
				sortable : false
			}, {
				property : 'width',
				label : '广告位宽度',
				sortable : false
			}, {
				property : 'height',
				label : '广告位高度',
				sortable : false
			}, {
				property : 'sortOrder',
				label : '序号',
				sortable : false
			}, {
				property : 'ctime',
				label : '创建时间',
				sortable : false
			}, {
				property : 'status',
				label : '状态',
				sortable : false
			}, {
				property : 'action',
				label : '操作',
				sortable : false
			} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		var is_publish = item.status == 1 ? 'checked="checked"' : '';
		if (is_publish) {
			item.status = '<label class="switch-sm" title="关闭" board_id="' + item.boardId + '" status="' + item.status + '"">'
					+ '<input type="checkbox" id="publish_' + item.boardId + '" ' + is_publish + ' />' + '<span></span></label>';
		} else {
			item.status = '<label class="switch-sm" title="启用" board_id="' + item.boardId + '" status="' + item.status + '"">'
					+ '<input type="checkbox" id="publish_' + item.boardId + '" ' + is_publish + ' />' + '<span></span></label>';
		}
		item.action = '<a href="' + BASE_URL + '/back/advertBoard/edit?boardId=' + item.boardId
				+ '" data-toggle="ajaxModal" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="board_' + item.boardId + '" board_id="' + item.boardId
				+ '" title="删除" onclick="del('+item.boardId+')"><i class="fa fa-trash-o"></i></a>';
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}