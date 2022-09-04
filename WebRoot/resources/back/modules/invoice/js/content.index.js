$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');

	/**
	 * 刷新或搜索
	 */
	$('body').delegate('.action-refresh, #action_search', 'click', function() {
		$('#content_listing').datagrid('reload');
	});

	/**
	 * 关键字搜索 - 支持回车
	 */
	$("input[name=key]").on('keypress', function(event) {
		if (event.which == '13') {
			$('#content_listing').datagrid('reload');
			return false;
		}
	});

	/**
	 * 单个删除
	 */
	$('body').delegate('.operate-delete', 'click', function() {
		var del = confirm('确定要删除吗？');
		if (!del) {
			return false;
		}
		
				var id = $(this).attr("id");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/back/invoiceContent/delete',
			data : 'id=' + id,
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				if (data.status == 0) {
					$("#id_" + id).parent().parent().remove();
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
					property: '_query',
					label: ''
				},
				{
					property : 'id',
					label : '',
					sortable : false
				},
				{
					property : 'userid',
					label : '用户id',
					sortable : false
				},
				{
					property : 'orderId',
					label : '订单id',
					sortable : false
				},
				{
					property : 'invoiceCode',
					label : '发票代码',
					sortable : false
				},
				{
					property : 'invoiceNumber',
					label : '发票号码',
					sortable : false
				},
				{
					property : 'machineNumber',
					label : '机打号',
					sortable : false
				},
				{
					property : 'identificationNumber',
					label : '机器编号',
					sortable : false
				},
				{
					property : 'payee',
					label : '收款单位',
					sortable : false
				},
				{
					property : 'taxRegisterNumber',
					label : '税务登记号',
					sortable : false
				},
				{
					property : 'invoiceDate',
					label : '开票日期',
					sortable : false
				},
				{
					property : 'receiver',
					label : '收款员',
					sortable : false
				},
				{
					property : 'payer',
					label : '付款单位',
					sortable : false
				},
				{
					property : 'goodsContentSkuId',
					label : '项目',
					sortable : false
				},
				{
					property : 'price',
					label : '单价',
					sortable : false
				},
				{
					property : 'amount',
					label : '数量',
					sortable : false
				},
				{
					property : 'money',
					label : '金额',
					sortable : false
				},
				{
					property : 'lumpSum',
					label : '合计',
					sortable : false
				},
				{
					property : 'taxCode',
					label : '税控码',
					sortable : false
				},
				{
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="' + BASE_URL + '/back/invoiceContent/detail?id=' + item.id + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/back/invoiceContent/edit?id=' + item.id
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="id_' + item.id + '" id="' + item.id
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

		item.invoiceDate = dateConverter(item.invoiceDate, PATTERN_ENUM.datetime);
	});
}