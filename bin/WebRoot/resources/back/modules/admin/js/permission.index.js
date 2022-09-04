$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
	/**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('#permission_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 权限模块处理
	 */
	$("select[name=navigationId]").change(function(){
		$('#permission_listing').datagrid('reload');
    	return false;
	});
	
	/**
	 * 删除权限数据
	 */
	$("#permission_listing").on('click','.operate-delete', function(){
		var obj_tr = $(this).parent().parent();
		var permissionId = $(this).attr('permissionId');

		var strConfirm = '确定要删除该权限数据吗？';
		if (! confirm(strConfirm)) {
			return false;
		}
		
		$.ajax({
	    	type:'post',
	    	url:BASE_URL + '/back/adminPermission/delete',
	        data:'permissionId=' + permissionId,
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
                property: 'permissionId',
                label: '权限 ID',
                sortable: true
            },
            {
                property: 'permissionName',
                label: '权限名',
                sortable: false
            },
            
            {
                property: 'url',
                label: 'URL地址',
                sortable: true
            },
            {
            	property: 'title',
            	label: '权限所属菜单组',
            	sortable: false
            },
            {
                property: 'description',
                label: '描述',
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
    	item.action = '<a href="' + BASE_URL + '/back/adminPermission/edit?permissionId=' + item.permissionId + '"data-toggle="ajaxModal" class="operate-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
//    	item.action = '<a href="' + BASE_URL + '/back/adminPermission/edit?permissionId=' + item.permissionId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + 
            	'<a href="javascript:;" class="operate-delete" id="permission_' + item.permissionId + '" permissionId="' + item.permissionId + '" title="删除"><i class="fa fa-times"></i></a>';
    });
}