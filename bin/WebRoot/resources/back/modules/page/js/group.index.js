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
	 * 删除 - 单条
	 */
    $("#content_listing").delegate('.operate-delete', 'click', function(){
		var groupId = $(this).attr("groupId");
		doDeleteGroup(groupId);
	});
    
});

/**
 * 删除
 */
function doDeleteGroup(groupId) {
	var del = confirm('确定要删除所选分组吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/pageGroup/delete',
        data:'groupId=' + groupId,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			window.location.href = BASE_URL + "/back/pageGroup/index";
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
                property: 'groupId',
                label: 'ID',
            },
            {
                property: 'groupName',
                label: '分组名称',
                sortable:true
            },
            {
                property: 'folder',
                label: '分组网址',
            },
            {
            	property: 'action',
            	label: '操作',
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
        item.action = '<a href="'+BASE_URL+'/back/pageGroup/edit?groupId=' + item.groupId + '" data-toggle="ajaxModal" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
        (item.moveable == 1 ? '<a href="javascript:;" class="operate-delete" id="groupId_' + item.groupId + '" groupId="' + item.groupId + '" title="删除"><i class="fa fa-times"></i></a>' : '');
    });
}