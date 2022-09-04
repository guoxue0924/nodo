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
	 * 前台显示与否
	 */
	$('#content_listing').delegate('.switch-sm', 'click', function() {
		var linkId = $(this).attr('linkId');
		publishFriendLink(linkId);
		return false;
	});

	/**
	 * 单个删除
	 */
//	$('body').delegate('.operate-delete', 'click', function() {
//		var del = confirm('确定要删除该友情链接吗？');
//		if (!del) {
//			return false;
//		}
//
//		var linkId = $(this).attr("linkId");
//		/* 执行 */
//		$.ajax({
//			type : 'post',
//			url : BASE_URL + '/back/friendlink/delete',
//			data : 'linkId=' + linkId,
//			dataType : 'json',
//			timeout : 10000,
//			success : function(data) {
//				if (data.status == 0) {
//					$('#content_listing').datagrid("reload");
//				} else {
//					alert(data.error);
//				}
//				return false;
//			}
//		});
//	});
});

function del(linkId) {
	var del = confirm('确定要删除该友情链接吗？');
	if (!del) {
		return false;
	}

	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/friendlink/delete',
		data : 'linkId=' + linkId,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$('#content_listing').datagrid("reload");
			} else {
				alert(data.error);
			}
			return false;
		}
	});
}

/**
 * 审核
 */
function publishFriendLink(linkId) {
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/friendlink/publish',
		data : {linkId:linkId, isPulished: $('#publish_' + linkId).prop('checked')},
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			if (data.status == 0) {
				$('#publish_' + linkId).prop('checked', !$('#publish_' + linkId).prop('checked'));
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
				property: 'checkbox',
				label:'<input type="checkbox" />'
			},
			{
				property : 'linkName',
				label : '友情链接名称'
			}, 
			{
				property : 'href',
				label : '友情链接地址'
			}, 
			{
				property : 'imageUrl',
				label : '图标'
			}, 
			{
				property : 'sortOrder',
				label : '序号'
			}, 
			{
				property : 'status',
				label : '前台显示'
			}, 
			{
				property : 'action',
				label : '操作'
			} ];
}

function formatData(items) {
	$.each(items, function(index, item) {
		
		item.checkbox = '<input type="checkbox" class="select-single" value="' + item.linkId + '">';
		var is_publish = item.status == 1 ? 'checked="checked"' : '';

		if (is_publish) {
			item.status = '<label class="switch-sm" title="关闭显示" linkId="' + item.linkId + '" status="' + item.status + '">' + '<input type="checkbox" id="publish_'
					+ item.linkId + '" ' + is_publish + ' />' + '<span></span></label>';
		} else {
			item.status = '<label class="switch-sm" title="开启显示" linkId="' + item.linkId + '" status="' + item.status + '">' + '<input type="checkbox" id="publish_'
					+ item.linkId + '" ' + is_publish + ' />' + '<span></span></label>';
		}

		item.action = '<a href="' + BASE_URL + '/back/friendlink/edit?linkId=' + item.linkId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="link_' + item.linkId + '" linkId="' + item.linkId
				+ '" title="删除" onclick="del('+item.linkId+')"><i class="fa fa-trash-o"></i></a>';

		item.imageUrl = '<a href="' + IMG_URL + item.imageUrl + '" class="thumb-sm" target="_blank"><img src="' + IMG_URL + item.imageUrl + '"></a>';
		
		
	});
}