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
		var bulkId = $(this).attr("bulkId");
		doDeleteBulk(bulkId);
	});
    
});


/**
 * 删除
 */
function doDeleteBulk(bulkId) {
	var del = confirm('确定要删除所选活动吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/grouponBulk/delete',
        data:'bulkId=' + bulkId,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
				$("#bulkId_" + bulkId).parent().parent().remove();
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
                property: 'bulkId',
                label: 'ID',
            },
            {
                property: 'sku.skuId',
                label: '商品ID',
            },
            {
            	property: 'sku.name',
            	label: '商品名称',
            },    
            {
                property: 'price',
                label: '抢购价格',
            },
            {
                property: 'inventorySum',
                label: '总库存',
            },
            {
                property: 'inventory',
                label: '剩余库存',
            },
            {
                property: 'startTime',
                label: '开始时间',
            },
            {
                property: 'endTime',
                label: '结束时间',
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
        item.action = '<a href="'+BASE_URL+'/back/grouponBulk/edit?bulkId=' + item.bulkId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
        '<a href="javascript:;" class="operate-delete" id="bulkId_' + item.bulkId + '" bulkId="' + item.bulkId + '" title="删除"><i class="fa fa-times"></i></a>';
        item.startTime = dateConverter(item.startTime, PATTERN_ENUM.datetime);
        item.endTime = dateConverter(item.endTime, PATTERN_ENUM.datetime);
        $(item).attr('sku.skuId', item.sku.skuId);
        $(item).attr('sku.name', item.sku.name);
    });
}