$(document).ready(function() {
	$('#startDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	$('#endDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});

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
	
    
});


function columnsDefined() {
	return [
            {
            	property: 'a',
            	label: ''
            },
            {
                property: 'refundSn',
                label: '退货单号',
            },
            {
            	property: 'goodsName',
            	label: '商品名称',
            	sortable: false
            },
            {
                property: 'number',
                label: '商品数量',
            },
            {
                property: 'orderNumber',
                label: '订单号',
            },
            {
                property: 'reason',
                label: '退货原因',
            },
            {
                property: 'ctime',
                label: '退货时间',
            },
            {
                property: 'status',
                label: '退单状态',
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
        item.a = '<a href="' + BASE_URL + '/back/refund/detail?refundId=' + item.refundId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
        
        item.action = '<a href="' + BASE_URL + '/back/refund/edit?refundId=' + item.refundId + '" data-toggle="ajaxModal" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;';
        
		item.status = $('#refundStatus_' + item.status).text();
		
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.date);
		if(!item.payTime) {
			$(item).attr('payTime', '');
		} else {
			item.payTime = dateConverter(item.payTime, PATTERN_ENUM.date);
		}
	});
}