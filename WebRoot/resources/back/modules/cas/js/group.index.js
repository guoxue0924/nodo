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
	 * 删除用户组
	 */
	$("#content_listing").delegate('.operate-delete', 'click', function(){
		var obj_tr = $(this).parent().parent();
		var userGroupId = $(this).attr('userGroupId');

		var strConfirm = '确定要删除该用户组吗？';
		if (! confirm(strConfirm)) {
			return false;
		}
		
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/back/casuserGroup/delete',
	        data:'userGroupId=' + userGroupId ,
	        dataType:'json',
	        timeout:60000,
	        success:function(data){
	    		if (data.status == 0) {
	    			obj_tr.remove();
	    			alert('删除成功');
	    		} else {
	    			alert(data.msg);
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
                property: 'userGroupId',
                label: 'ID',
                sortable: true
            },
            {
                property: 'groupName',
                label: '用户组名',
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
         item.action = '<a href="' + BASE_URL + '/back/casuserGroup/edit?userGroupId=' + item.userGroupId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + 
					'<a href="javascript:;" class="operate-delete" id="user_group_' + item.userGroupId + '" userGroupId="' + item.userGroupId + '" title="删除"><i class="fa fa-times"></i></a>';
     });

}