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
		var categoryId = $(this).attr("categoryId");
		doDeleteCategory(categoryId);
	});
    
});

/**
 * 删除
 */
function doDeleteCategory(categoryId) {
	var del = confirm('确定要删除所选活动吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/grouponCategory/delete',
        data:'categoryId=' + categoryId,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
				$("#categoryId_" + categoryId).parent().parent().remove();
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
	        	property: 'a',
	        	label:'',
	        },
            {
                property: 'categoryId',
                label: 'ID',
            },
            {
                property: 'title',
                label: '标签名称',
                sortable:true
            },
            {
                property: 'parentTitle',
                label: '上级标签名称',
            },
            {
                property: 'sortOrder',
                label: '排序',
            },
            {
            	property: 'action',
            	label: '操作',
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
		item.a = '';
        item.action = '<a href="'+BASE_URL+'/back/grouponCategory/edit?categoryId=' + item.categoryId + '" data-toggle="ajaxModal" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
        '<a href="javascript:;" class="operate-delete" id="categoryId_' + item.categoryId + '" categoryId="' + item.categoryId + '" title="删除"><i class="fa fa-times"></i></a>';
        if(item.parentTitle == '') {
        	item.parentTitle = '顶级标签'
        }
    });
}