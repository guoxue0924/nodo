$(function(){
	loadData();
	
	$('#searchBtn').on('click', loadData);
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
			$.ajax({
				async: false,
				type:'post',
				url:BASE_URL+'/front/cas/coupon/page',
				data:{pageSize : pageSize, pageIndex: page, status: $('#status').val()},
				dataType:'json',
				timeout:60000,
				success:function(data){
					$('#couponList tbody').empty();
					var count = data.count;
					$(data.data).each(function(){
						 var tr = $('<tr></tr>');
						 tr.append($('<td>' + this.couponName + '</td>'));
						 tr.append($('<td>' + this.faceValue + '</td>'));
						 tr.append($('<td>' + this.validStime + ' - ' + this.validEtime + '</td>'));
						 tr.append($('<td>' + this.cpnsStatus + '</td>'));
						 tr.append($('<td>查看</td>'));
						 $('#couponList tbody').append(tr);
					})
					$('#paginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#paginationBar li:eq(' +index+')').data('visiblePages',pageSize);
					if(count == 0) {
						$('#couponList .ncs-norecord').show();
						$('#paginationBar').hide();
					} else {
						$('#couponList .ncs-norecord').hide();
						$('#paginationBar').show();
						
					}
				}
			});
		}
	});
}