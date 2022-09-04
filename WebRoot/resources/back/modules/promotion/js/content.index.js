$(document).ready(function() {
	
	loadDataGridContent(columnsDefined(), 'formatData');

	/**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('#content_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 关键字搜索
	 */
	$('input[name=key]').on('keypress', function (event) {
	    if (event.which == '13') {
	        $('#content_listing').datagrid("reload");
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
		
		var promotionId = $(this).attr("promotionId");
		
		deletePromotion(promotionId);
		return false;
	});
	
	/**
     * 切换状态
     */
    $('#content_listing').delegate('.switch-sm', 'click', function(){
    	var promotionId = $(this).attr('id');
    	updatePromotionStatus(promotionId);
    	return false;
    });
});

function deletePromotion(promotionId) {
	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/promotionContent/delete',
		data : 'promotionId=' + promotionId,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$('#content_listing').datagrid("reload");
		        return false;
			} else {
				alert(data.msg);
			}
			return false;
		}
	});
}

function updatePromotionStatus(promotionId) {
	/* 执行 */
	$.ajax({
		type : 'post',
		url : BASE_URL + '/back/promotionContent/updateStatus',
		data : 'promotionId=' + promotionId,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$('#promotion_' + promotionId).prop('checked', !$('#promotion_' + promotionId).prop('checked'));
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
					property : 'promotionId',
					label : 'ID',
				},
				{
					property : 'title',
					label : '活动名称',
				},
				{
					property : 'categoryTitle',
					label : '所属类型',
				},
				
				{
					property : 'startTime',
					label : '活动开始时间',
				},
				{
					property : 'endTime',
					label : '活动结束时间',
				},
				{
					property : 'ctime',
					label : '发布时间',
					sortable : false
				},
				{
					property : 'status',
					label : '状态',
				},
				{
					property : 'action',
					label : '操作',
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item.action = '<a href="' + BASE_URL + '/back/promotionContent/edit?promotionId=' + item.promotionId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="promotionId_' + item.promotionId + '" promotionId="' + item.promotionId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


        var ischecked = item.status == 1 ? 'checked="checked"' : '';
        item.status = '<label class="switch-sm" id="' + item.promotionId + '">' + 
        		'<input type="checkbox" id="promotion_' + item.promotionId + '" ' + ischecked + ' />' + 
        		'<span></span></label>';

		item.startTime = dateConverter(item.startTime, PATTERN_ENUM.datetime);
		item.endTime = dateConverter(item.endTime, PATTERN_ENUM.datetime);
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}