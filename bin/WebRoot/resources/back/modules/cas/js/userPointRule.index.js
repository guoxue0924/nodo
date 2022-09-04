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
	 * 单个删除
	 */
	$('body').delegate('.operate-delete', 'click', function() {
		var del = confirm('确定要删除吗？');
		if (!del) {
			return false;
		}
		
				var pointRoleId = $(this).attr("pointRoleId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/back/casUserPointRule/delete',
			data : 'pointRoleId=' + pointRoleId,
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				if (data.status == 0) {
					$('#content_listing').datagrid('reload');
				} else {
					alert(data.msg);
				}
				return false;
			}
		});
	});
});

function columnsDefined() {
	return [
				{
					property: '_query',
					label: ''
				},
				{
					property : 'pointRoleId',
					label : '',
					sortable : false
				},
				{
					property : 'pointType',
					label : '积分类型',
					sortable : false
				},
				{
					property : 'pointName',
					label : '积分名称',
					sortable : false
				},
				{
					property : 'point',
					label : '积分',
					sortable : false
				},
				{
					property : 'pointCoefficient',
					label : '积分系数',
					sortable : false
				},
				{
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/back/casUserPointRule/detail?pointRoleId=' + item.pointRoleId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/back/casUserPointRule/edit?pointRoleId=' + item.pointRoleId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="pointRoleId_' + item.pointRoleId + '" pointRoleId="' + item.pointRoleId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strPointType = '未知';
		if (item.pointType == 1) {
			strPointType = '注册奖励';
		} else if (item.pointType == 2) {
			strPointType = '每日登录';
		} else if (item.pointType == 3) {
			strPointType = '每日签到）';
		}
        item.pointType = strPointType;
	});
}