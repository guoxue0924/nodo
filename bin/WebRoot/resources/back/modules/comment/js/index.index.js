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
	 * 搜索对回车的支持
	 */
	$("input[name=key]").on('keypress', function (event) {
	    if (event.which == '13' && $(this).val()) {
	    	$('#content_listing').datagrid('reload');
	    	return false;
	    }
	});
	
	/**
	 * 导出excel表格
	 */
	$("#action_excel").on('click', function () {
		var key = $('input[name="key"]').val();
		location.href = "" + BASE_URL + "/back/commentadmin/exportCommentExcel?key="+key+"";
	});
	
	/**
     * 审核
     */
    $('#content_listing').delegate('.switch-sm', 'click', function(){
    	var content_id = $(this).attr('content_id');
    	publishComment(content_id);
    	return false;
    });
	
    /**
	 * 删除 - 单条
	 */
    $("#content_listing").delegate('.operate-delete', 'click', function(){
		var content_id = $(this).attr("content_id");
		doDeleteContent(content_id);
	});
    
    /**
     * 删除 - 批量
     */
    $('#action_delete').on('click', function(){
		var id_arr = new Array();
		var i = 0;
		$('#content_listing').find('.select-single').each(function(){
			if ($(this).is(':checked')) {
				id_arr[i] = $(this).val();
				i++;
			}
		});
		var id = id_arr.join(',');
		if (! id) {
			return false;
		}
		
		doDeleteContent(id);
	});
});


/**
 * 审核
 */
function publishComment(id) {
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/commentadmin/index/publish',
        data:'id=' + id,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			if ($('#publish_' + id).prop('checked')) {
    				$('#publish_' + id).prop('checked', false);
    			} else {
    				$('#publish_' + id).prop('checked', true);
    			}
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

/**
 * 删除
 */
function doDeleteContent(id) {
	var del = confirm('确定要删除所选评论吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/commentadmin/index/delete',
        data:'id=' + id,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			if (parseInt(id) == id) {
    				$("#content_" + id).parent().parent().remove();
    			} else {
    				$('#content_listing').find('.select-single:checked').parent().parent().remove();
    			}
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
                label: ''
            },
            {
            	property: 'a',
            	label: ''
            },
            {
                property: 'username',
                label: '评论人',
                sortable: false
            },
            {
                property: 'to_name',
                label: '评论对象(商品)',
                sortable: false
            },
            {
            	property: 'rankBase',
            	label: '商品满意度评分',
            	sortable: false
            },
            {
            	property: 'rankLogistics',
            	label: '物流评分',
            	sortable: false
            },
            {
            	property: 'rankSpeed',
            	label: '发货速度评分',
            	sortable: false
            },
            {
                property: 'ctime',
                label: '评论时间',
                sortable: false
            },
            {
            	property: 'status',
            	label: '前台显示',
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
    	item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.id + '">';
    	
    	item.a = '<a href="' + BASE_URL + '/back/commentadmin/index/detail/?id=' + item.id + '"  class="modal-detail"><i class="fa fa-search-plus"></i></a>';
        
    	var is_publish = item.status == 1 ? 'checked="checked"' : '';
        item.status = '<label class="switch-sm" content_id="' + item.id + '">' + 
        		'<input type="checkbox" id="publish_' + item.id + '" ' + is_publish + ' />' + 
        		'<span></span></label>';
        
        item.action ='<a href="javascript:;" class="operate-delete" id="content_' + item.id + '" content_id="' + item.id + '" title="删除"><i class="fa fa-times"></i></a>';
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}