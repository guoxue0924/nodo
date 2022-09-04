$(document).ready(function() {
	$('#startDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	$('#endDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});

	loadDataGridContent(columnsDefined(), 'formatData');
	
	
    /**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('#content_listing_order').datagrid('reload');
		return false;
	});
	
	/**
	 * 关键字搜索
	 */
	$('input[name=key]').on('keypress', function (event) {
	    if (event.which == '13') {
	        $('#content_listing_order').datagrid("reload");
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
                property: 'orderNumber',
                label: '订单号',
            },
            {
            	property: 'orderType',
            	label: '订单类型',
            	sortable: false
            },
            {
                property: 'logisticNumber',
                label: '物流单号',
            },
            {
                property: 'payTime',
                label: '支付时间',
            },
            {
                property: 'ctime',
                label: '下单时间',
            },
            {
                property: 'totalAmount',
                label: '订单总额',
            },
            {
                property: 'status',
                label: '订单状态',
            },
            {
                property: 'payStatus',
                label: '付款状态',
            },
            {
                property: 'payType',
                label: '支付类型',
            },
            {
            	property: 'username',
            	label: '买家',
            }
            /*,
            {
            	property: 'action',
            	label: '操作',
            	sortable: false
            }*/
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
        item.a = '<a href="' + BASE_URL + '/back/order/detail?orderId=' + item.orderId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
        
        /*item.action = '<a href="' + BASE_URL + '/back/order/edit?orderId=' + item.orderId + '" data-toggle="ajaxModal" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;';*/
        
		item.status = $('#orderStatus_' + item.status).text();
        
		item.payStatus = $('#paymentStatus_' + item.payStatus).text();
		
		item.orderType = $('#orderType_' + item.orderType).text();
		
		item.payType = $('#payType_' + item.payType).text();
        
		if(!item.logisticNumber) {
			$(item).attr('logisticNumber', '');
		}
		
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.date);
		if(!item.payTime) {
			$(item).attr('payTime', '');
		} else {
			item.payTime = dateConverter(item.payTime, PATTERN_ENUM.date);
		}
	});
}