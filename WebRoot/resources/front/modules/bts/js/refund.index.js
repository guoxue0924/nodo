/**
 * Created by George on 15/12/23.
 */
$(function () {

    $("#startDate").datepicker({dateFormat: "yy-mm-dd"});
    $("#endDate").datepicker({dateFormat: "yy-mm-dd"});
    
    loadData();
    
    $("#searchBtn").on('click', loadData);
});

var pageSize = 5;

function loadData() {
	if($('#paginationBar li').length > 0) {
		$('#paginationBar').twbsPagination('destroy');
	}
	$('#paginationBar').twbsPagination({
		totalPages: 1,
		startPage: 1,
		visiblePages: 1,
		first: '首页',
		prev: '上一页',
		next: '下一页',
		last: '尾页',
		onPageClick: function(event, page, index) {
			var paramInArray = $('#searchForm').serializeArray();
			var paramJson = {};
			for(var i in paramInArray) {
				if (typeof (paramJson[paramInArray[i].name]) == 'undefined') 
					paramJson[paramInArray[i].name] = paramInArray[i].value; 
				else 
					paramJson[paramInArray[i].name] += "," + paramInArray[i].value; 
			}
			paramJson = $.extend({}, paramJson, {pageSize : pageSize, pageIndex: page} );
			$.ajax({
				async: false,
				type:'post',
				url:BASE_URL+'/front/bts/refund/page',
				data:paramJson,
				dataType:'json',
				timeout:60000,
				success:function(data){
					var tr1_temp = $('#refundList tbody').find('tr:first').clone();
					var tr2_temp = $('#refundList tbody').find('tr:first').next().clone();
					var tr3_temp = $('#refundList tbody').find('tr:first').next().next().clone();
					$('#refundList tbody').empty();
					var count = data.count;
					$(data.data).each(function(){
						$('#refundList tbody').append(tr1_temp.clone());
						var tr2 = tr2_temp.clone();
						tr2.find('span:first').text('退货编号：' + this.refundSn);
						tr2.find('span:first').next().text('申请时间： ' + dateConverter(this.ctime, PATTERN_ENUM.datetime));
						$('#refundList tbody').append(tr2);
						var tr3 = tr3_temp.clone();
						tr3.find('.pic-thumb a').attr('href', BASE_URL + '/front/goods/detail?skuId=' + this.skuId);
						tr3.find('.pic-thumb img').attr('src', IMG_URL + this.goodsImage);
						tr3.find('.goods-name dt a').attr('href', BASE_URL + '/front/goods/detail?skuId=' + this.skuId);
						tr3.find('.goods-name dt a').text(this.goodsName);
						tr3.find('.goods-name dd a').attr('href', BASE_URL + '/front/bts/order/detail?orderId=' + this.orderId);
						tr3.find('.goods-name dd a').text(this.orderNumber);
						tr3.find('.tl').next().text(this.buyPrice*this.buyNum);
						tr3.find('.tl').next().next().text(this.buyNum);
						var status = this.status;
						switch (status) {
							case 1:  status = '已成功退货';break;
							case 2:  status = '等待审核';break;
							case 3:  status = '已审核';break;
							default: status = '未知'; break;
						} 
						tr3.find('.tl').next().next().next().text(status);
						tr3.find('.ncm-btn').attr('href', BASE_URL + '/front/bts/refund/detail?refundId=' + this.refundId);
						$('#refundList tbody').append(tr3);
					})
					$('#paginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#paginationBar li:eq(' +index+')').data('visiblePages',pageSize);
					
				}
			});
		}
	});
	
}