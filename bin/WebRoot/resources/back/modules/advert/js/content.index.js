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
	 * 动态加载广告位
	 */
	$("#pageId").change(function() {
		get_board($(this).val());
	});

	/**
	 * 发布
	 */
	$('#content_listing').delegate('.switch-sm', 'click', function() {
		var contentId = $(this).attr('content_id');
		var status = $(this).attr('status');
		publishAdvert(contentId, status);
		return false;
	});

	/**
	 * 单个删除
	 */
//	$('body').delegate('.operate-delete', 'click', function() {
//		var del = confirm('确定要删除该广告吗？');
//		if (!del) {
//			return false;
//		}
//
//		var contentId = $(this).attr("content_id");
//		/* 执行 */
//		$.ajax({
//			type : 'post',
//			url : BASE_URL + '/back/advertContent/delete',
//			data : 'contentId=' + contentId,
//			dataType : 'json',
//			timeout : 10000,
//			success : function(data) {
//				if (data.status == 0) {
//					$("#content_" + contentId).parent().parent().remove();
//				} else {
//					alert(data.error);
//				}
//				return false;
//			}
//		});
//	});
});

function del(contentId){
	var del = confirm('确定要删除该广告吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertContent/delete',
		data : 'contentId=' + contentId,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$("#content_" + contentId).parent().parent().remove();
			} else {
				alert(data.error);
			}
			return false;
		}
	});
}

/**
 * 动态加载广告位
 */
function get_board(pageId) {
	$("select[name=boardId]:first").find("option:gt(0)").remove();
	if (pageId == null || pageId == 0) {
		return false;
	}
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertBoard/getBoardByPageId',
		data : 'pageId=' + pageId,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				$(data.data.boards).each(function() {
					$("select[name=boardId]:first").append('<option value="' + this.boardId + '">' + this.name + '</option>');
				});
			}
			return false;
		}
	});
	return false;
}

/**
 * 审核
 */
function publishAdvert(contentId, status) {
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/advertContent/publish',
		data : 'contentId=' + contentId + '&status=' + status,
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				if ($('#publish_' + contentId).prop('checked')) {
					$('#publish_' + contentId).prop('checked', false);
					$("#publish_" + contentId).parent().attr("status", 0);
				} else {
					$('#publish_' + contentId).prop('checked', true);
					$("#publish_" + contentId).parent().attr("status", 1);
				}
			} else {
				alert(data.error);
			}
			return false;
		}
	});
}

function columnsDefined() {
	return [ {
		property : 'a',
		label : ''
	}, {
		property : 'contentId',
		label : 'ID',
		sortable : false
	}, {
		property : 'title',
		label : '广告标题',
		sortable : false
	}, {
		property : 'pageTitle',
		label : '所属页面',
		sortable : false
	}, {
		property : 'boardName',
		label : '所属广告位',
		sortable : false
	}, {
		property : 'type',
		label : '广告类型',
		sortable : false
	}, {
		property : 'bindSource',
		label : '绑定资源',
		sortable : false
	}, {
		property : 'count',
		label : '点击次数',
		sortable : false
	}, {
		property : 'startTime',
		label : '生效时间',
		sortable : false
	}, {
		property : 'endTime',
		label : '结束时间',
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
		item.a = '<a href="' + BASE_URL + '/back/advertContent/detail/?contentId=' + item.contentId + '"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		/* 处理广告类型 */
		var str_type = '';
		if (item.type == 1) {
			str_type = '图片';
		} else if (item.type == 2) {
			str_type = '文字';
		} else if (item.type == 3) {
			str_type = 'Flash';
		} else if (item.type == 4) {
			str_type = '视频';
		} else if (item.type == 5) {
			str_type = '轮播';
		}
		item.type = str_type;

		/* 处理绑定资源 */
		var bind_source_name = '';
		if (item.bind_type == 1) {
			bind_source_name = '[链接] ';
		} else if (item.bind_type == 2) {
			bind_source_name = '[商品] ';
		} else if (item.bind_type == 3) {
			bind_source_name = '[文章] ';
		} else if (item.bind_type == 4) {
			bind_source_name = '[自定义] ';
		}
		bind_source_name += item.bind_source_name;
		item.bind_source = bind_source_name;

		var is_publish = item.status == 1 ? 'checked="checked"' : '';

		if (is_publish) {
			item.status = '<label class="switch-sm" title="关闭显示" content_id="' + item.contentId + '" status="' + item.status + '">' + '<input type="checkbox" id="publish_'
					+ item.contentId + '" ' + is_publish + ' />' + '<span></span></label>';
		} else {
			item.status = '<label class="switch-sm" title="开启显示" content_id="' + item.contentId + '" status="' + item.status + '">' + '<input type="checkbox" id="publish_'
					+ item.contentId + '" ' + is_publish + ' />' + '<span></span></label>';
		}

		item.action = '<a href="' + BASE_URL + '/back/advertContent/edit?contentId=' + item.contentId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="content_' + item.contentId + '" content_id="' + item.contentId
				+ '" title="删除" onclick="del('+item.contentId+')"><i class="fa fa-trash-o"></i></a>';

		item.startTime = dateConverter(item.startTime, PATTERN_ENUM.date);
		item.endTime = dateConverter(item.endTime, PATTERN_ENUM.date);
	});
}