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
	 * 还原
	 */
    $("#content_listing").delegate('.operating-restore', 'click', function(){
		var contentId = $(this).attr("contentId");
		doRestoreContent(contentId);
	});
	
	/**
	 * 删除 - 单条
	 */
    $("#content_listing").delegate('.operate-delete', 'click', function(){
		var content_id = $(this).attr("contentId");
		var id_arr = new Array();
		id_arr.push(content_id)
		doTrashContent(id_arr);
	});
	
	/**
	 * 删除 - 批量
	 */
	$('#action_trash').on('click', function(){
		var id_arr = new Array();
		var i = 0;
		$('#content_listing').find('.select-single').each(function(){
			if ($(this).is(':checked')) {
				id_arr.push($(this).val());
				i++;
			}
		});
		if (i == 0) {
			return false;
		}
		doTrashContent(id_arr);
	});
    
});

/**
 * 删除
 */
function doTrashContent(contentIds) {
	var del = confirm('确定要删除所选文章吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/articleRecycle/delete',
        data:{contentIds : contentIds},
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			$('#content_listing').datagrid("reload");
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

/**
 * 还原
 */
function doRestoreContent(contentId) {
	var del = confirm('确定要还原所选文章吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/articleRecycle/restore',
        data:{contentId : contentId},
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			$('#content_listing').datagrid("reload");
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
				property: 'checkbox',
				label:'<input type="checkbox" />',
			},
            {
                property: 'contentId',
                label: 'ID',
            },
            {
                property: 'categoryTitle',
                label: '分类',
            },
            {
                property: 'title',
                label: '标题',
            },
            {
                property: 'sortOrder',
                label: '权重',
            },
            {
                property: 'mtime',
                label: '修改时间',
            },
            {
            	property: 'action',
            	label: '操作',
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
		item.checkbox = '<input type="checkbox" class="select-single" value="' + item.contentId + '">';
        item.action = '<a href="javascript:;" class="operating-restore" contentId="' + item.contentId +'" title="还原" ><i class="fa fa-reply"></i></a>&nbsp;&nbsp;' +
        '<a href="javascript:;" class="operate-delete" id="contentId_' + item.contentId + '" contentId="' + item.contentId + '" title="删除"><i class="fa fa-times"></i></a>';
        
        item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
    });
}