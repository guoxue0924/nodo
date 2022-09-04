$(document).ready(function() {
	loadDataGridContent(columnsDefined(),  'formatData');

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
	 * 单个删除
	 */
//	$('body').delegate('.operate-delete', 'click', function() {
//		var del = confirm('确定要删除该广告页吗？');
//		if (!del) {
//			return false;
//		}
//
//		var pageId = $(this).attr("page_id");
//
//		/* 执行 */
//		$.ajax({
//			type : 'post',
//			url : BASE_URL + '/back/advertPage/delete',
//			data : 'pageId=' + pageId,
//			dataType : 'json',
//			timeout : 10000,
//			success : function(data) {
//				if (data.status == 0) {
//					$("#page_" + pageId).parent().parent().remove();
//				} else {
//					alert(data.msg);
//				}
//				return false;
//			}
//		});
//	});
});

function del(pageId){
	var del = confirm('确定要删除该广告页吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertPage/delete',
		data : 'pageId=' + pageId,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$("#page_" + pageId).parent().parent().remove();
			} else {
				alert(data.msg);
			}
			return false;
		}
	});
}

function columnsDefined() {
	return [
			{
				property : 'pageId',
				label : 'ID',
				sortable : false
			}, {
				property : 'title',
				label : '页面标题',
				sortable : false
			}, {
				property : 'ctime',
				label : '创建时间',
				sortable : false
			}, {
				property : 'action',
				label : '操作',
				sortable : false
			} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.action = '<a href="' + BASE_URL + '/back/advertPage/edit?pageId=' + item.pageId
				+ '" data-toggle="ajaxModal" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="page_' + item.pageId + '" page_id="' + item.pageId
				+ '" title="删除" onclick="del('+item.pageId+')"><i class="fa fa-trash-o"></i></a>';
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}