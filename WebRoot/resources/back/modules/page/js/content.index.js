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
		var contentId = $(this).attr("contentId");
		doDeleteContent(contentId);
	});
    
});

/**
 * 删除
 */
function doDeleteContent(contentId) {
	var del = confirm('确定要删除所选分组吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/pageContent/delete',
        data:'contentId=' + contentId,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			window.location.href = BASE_URL + "/back/pageContent/index";
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
                property: 'id',
                label: 'ID',
            },
            {
                property: 'groupTitle',
                label: '分组名称',
                sortable:true
            },
            {
                property: 'title',
                label: '网页标题',
            },
            {
                property: 'pageUrl',
                label: '网址',
            },
            {
                property: 'ctime',
                label: '创建时间',
            },
            {
            	property: 'action',
            	label: '操作',
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
        item.action = '<a href="'+BASE_URL+'/back/pageContent/edit?contentId=' + item.id + '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
        '<a href="javascript:;" class="operate-delete" id="contentId_' + item.id + '" contentId="' + item.id + '" title="删除"><i class="fa fa-times"></i></a>';
        item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
    });
}