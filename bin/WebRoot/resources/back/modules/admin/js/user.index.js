$(document).ready(function() {
	loadDataGridContent(columnsDefined(),  'formatData');
	
    /**
	 * 刷新或搜索
	 */
	$('body').delegate('.action-refresh', 'click', function(){
		$('#user_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 搜索对回车的支持
	 */
	$("input[name=key]").on('keypress', function (event) {
	    if (event.which == '13' && $(this).val()) {
	    	$('#user_listing').datagrid('reload');
	    	return false;
	    }
	});
	
	/**
	 * 删除用户
	 */
    $("#user_listing").on('click', '.operate-delete', function () {
		var obj_tr = $(this).parent().parent();
		var userid = $(this).attr('userid');
		
		var str_confirm = '确定要删除该用户吗？';
		if (! confirm(str_confirm)) {
			return false;
		}
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/back/adminUser/delete',
	        data:'userid=' + userid,
	        dataType:'json',
	        timeout:60000,
	        success:function(data){
	    		if (data.status == 0) {
	    			obj_tr.remove();
	    			alert('删除成功');
	    		} else {
	    			alert('删除失败，请稍后重试');
	    		}
	    		return false;
	    	},
	        error:ajaxError
	    });
	});
});

function columnsDefined() {
	return [
            {
            	property: 'a',
            	label: ''
            },
            {
                property: 'userid',
                label: 'ID',
                sortable: false
            },
            {
                property: 'username',
                label: '用户名',
                sortable: false
            },
            {
                property: 'fullname',
                label: '用户全称',
                sortable: false
            },
            {
                property: 'groups',
                label: '所属组',
                sortable: true
            },
            {
            	property: 'ctime',
            	label: '注册时间',
            	sortable: false
            },
            {
            	property: 'status',
            	label: '状态',
            	sortable: false
            },
            {
            	property: 'isOnline',
            	label: '是否在线',
            	sortable: false
            },
            {
            	property: 'action',
            	label: '操作',
            	sortable: false
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
    	item.a = '<a href="' + BASE_URL + '/back/adminUser/detail?userid=' + item.userid + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
        item.status = item.status == 1 ? '<i class="fa fa-check text-success" title="已激活"></i>' : '<i class="fa fa-ban text-danger" title="未激活"></i>';
        item.isOnline = item.isOnline == 1 ? '<i class="fa fa-check text-success" title="在线"></i>' : '<i class="fa fa-ban text-danger" title="不在线"></i>';
        var str_action = '<a href="' + BASE_URL + '/back/adminUser/edit?userid=' + item.userid + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>';
        if (item.username != 'admin') {
        	str_action += '&nbsp;&nbsp;<a href="javascript:;" class="operate-delete" id="userid_' + item.userid + '" userid="' + item.userid + '" title="删除"><i class="fa fa-times"></i></a>';
        }
        item.action = str_action;
        item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
    });
}