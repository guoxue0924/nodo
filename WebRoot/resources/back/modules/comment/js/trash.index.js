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
			}
		];
}

function formatData(items) {
	$.each(items, function (index, item) {
    	item.a = '<a href="' + BASE_URL + '/back/commentadmin/index/detail/?id=' + item.id + '"  class="modal-detail"><i class="fa fa-search-plus"></i></a>';
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}