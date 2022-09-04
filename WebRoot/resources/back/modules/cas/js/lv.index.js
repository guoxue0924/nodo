$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
    /**
	 * 刷新
	 */
	$('.action-refresh').on('click', function(){
		$('#content_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 删除会员等级 (若该等级下已有会员，则不允许删除，并提示其去做会员等级调整)
	 */
	$("#content_listing").delegate('.operate-delete', 'click', function(){
		var obj_tr = $(this).parent().parent();
		var userLvId = $(this).attr('userLvId');
		
		var strConfirm = '确定要删除该等级吗？';
		if (! confirm(strConfirm)) {
			return false;
		}
		
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/back/casuserLevel/delete',
	        data:'userLvId=' + userLvId,
	        dataType:'json',
	        timeout:60000,
	        success:function(data){
	    		if (data.status == 0) {
	    			obj_tr.remove();
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
                property: 'userLvId',
                label: 'ID',
                sortable: false
            },
            {
            	property: 'name',
            	label: '等级名称',
            	sortable: false
            },
            {
                property: 'groupName',
                label: '等级类型',
                sortable: false
            },
            {
            	property: 'description',
            	label: '描述',
            	sortable: false
            },
            {
                property: 'isDefault',
                label: '是否默认',
                sortable: true
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
    	item.isDefault = item.isDefault == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';
    	
    	item.action = '<a href="' + BASE_URL + '/back/casuserLevel/edit?userLvId=' + item.userLvId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + 
    			'<a href="javascript:;" class="operate-delete" userLvId="' + item.userLvId + '" title="删除"><i class="fa fa-times"></i></a>';
    });
}