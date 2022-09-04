$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'requestParam', 'formatData');
	
    /**
     * 刷新
     */
    $(".action-refresh").on('click',function(){
    	$('#tag_listing').datagrid('reload');
    	return false;
    });
    
    /**
     * 单个删除标签
     */
    $('body').delegate('#tag_listing .operate-delete', 'click', function(){
    	var tr=$(this).closest('tr'),bid = tr.find(".select-single").val();
    	if(!bid || !confirm('确定要删除此标签吗？')) return false;
    	$.ajax({
	    	type:'post',
	        url:'/trendadmin/tag/delete',
	        data:'id=' + bid,
	        dataType:'json',
	        timeout:10000,
	        beforeSend:function(){$(tr).addClass('remove');},
	        success:function(data){
	    		if (data.status == 0) {
	    			$(tr).fadeIn().remove();
	    		} else {
	    			alert(data.msg);
	    		}
	    		return false;
	    	},complete:function(){$(tr).removeClass('remove');}
	    	
	    });
	});
    
    /**
     * 批量删除标签
     */
    $('body').delegate('#action_delete', 'click', function(){
		var id_arr = new Array();
		var i = 0;
		$('#tag_listing').find('.select-single').each(function(){
			if ($(this).is(':checked')) {
				id_arr[i] = $(this).val();
				i++;
			}
		});
		var id = id_arr.join(',');
		
		if (! id) {
			return false;
		}
		
		var del = confirm('确定要删除所选标签吗？');
		if (! del) {return false;}
		
		/* 执行删除 */
		$.ajax({
	    	type:'post',
	        url:'/trendadmin/tag/delete',
	        data:'id=' + id,
	        dataType:'json',
	        timeout:10000,
	        success:function(data){
	    		if (data.status == 0) {
	    			$('#tag_listing').find('.select-single:checked').parent().parent().remove();
	    		} else {
	    			alert(data.msg);
	    		}
	    		return false;
	    	}
	    });
	 });
});

function columnsDefined() {
	return [
            {
                property: 'checkbox',
                label: ''
            },
            {
                property: 'tag_id',
                label: 'ID',
                sortable: true
            },
            {
            	property: 'tag_name',
            	label: '标签名称',
            	sortable: true
            },
            {
                property: 'tag_type',
                label: '标签类型',
                sortable: false
            },
            {
                property: 'rel_count',
                label: '关联数',
                sortable: false
            },
            {
            	property: 'operate',
                label: '操作'
            }
        ];
}

function requestParam() {
	var url = '/trendadmin/tag';
	return {url: url};
}

function formatData(items) {
	$.each(items, function (index, item) {
    	item.checkbox = '<input type="checkbox" name="post[]" class="select-single" value="' + item.tag_id + '">';
    	item.operate = '<a href="/trendadmin/tag/edit/?tag_id='+item.tag_id+'" data-toggle="ajaxModal" class="operate-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + 
		'<a href="javascript:;" class="operate-delete" title="删除"><i class="fa fa-times"></i></a>';
    	item.tag_type = item.tag_type=='goods'?'商品标签':'文章标签';
    });
}