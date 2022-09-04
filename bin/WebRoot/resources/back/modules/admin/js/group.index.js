$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
    /**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('#group_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 删除用户组
	 */
	$("#group_listing").delegate('.operate-delete', 'click', function(){
		var obj_tr = $(this).parent().parent();
		var groupId = $(this).attr('groupId');
//		var del_user = $(this).attr('del_user'); // 暂时不启用
		var del_user = 0;
		
		var str_confirm = '确定要删除该用户组吗？';
		if (del_user == 1) {
			str_confirm = '确定要删除该用户组，并同时删除该用户组下的所有用户吗？';
		}
		if (! confirm(str_confirm)) {
			return false;
		}
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/back/adminUserGroup/delete',
	        data:'groupId=' + groupId + '&delUser=' + del_user,
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
                property: 'groupId',
                label: 'ID',
                sortable: true
            },
            {
                property: 'groupName',
                label: '用户组名',
                sortable: false
            },
            {
                property: 'description',
                label: '描述',
                sortable: false
            },
            {
            	property: 'setting_auth',
            	label: '权限',
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
        item.setting_auth = '<a href="' + BASE_URL + '/back/adminPermission/assignGroup?groupId=' + item.groupId + '" class="load-content" title="设置权限"><i class="fa fa-gear"></i></a>'; 
        item.action = '<a href="' + BASE_URL + '/back/adminUserGroup/edit?groupId=' + item.groupId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + 
				'<a href="javascript:;" class="operate-delete" id="groupId_' + item.groupId + '" groupId="' + item.groupId + '" title="删除"><i class="fa fa-times"></i></a>';
    });
}