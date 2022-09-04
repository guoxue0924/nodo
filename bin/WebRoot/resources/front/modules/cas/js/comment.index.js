$(function(){
	loadData()
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
				url:BASE_URL+'/front/cas/comment/page',
				data:{pageSize : pageSize, pageIndex: page},
				dataType:'html',
				timeout:60000,
				success:function(data){
					$('#commentList').empty();
					
					var count = 0;
					$(data).each(function(){
						var type = this.nodeType;
						if(this.nodeType != 3) {
							if(this.nodeName == 'INPUT') {
								count = $(this).val();
							}
							$('#commentList').append($(this));
						} 
					})
					
					$('#paginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#paginationBar li:eq(' +index+')').data('visiblePages',pageSize);
					$('.raty').raty({
			            path: STATIC_URL + "/plugins/jquery-raty/img",
			            readOnly: true,
			            score: function() {
			                return $(this).attr('data-score');
			            }
			        });
			        $('a[nctype="nyroModal"]').nyroModal();
			        if(count == 0) {
						$('.mb20 .ncs-norecord').show();
						$('#paginationBar').hide();
					} else {
						$('.mb20 .ncs-norecord').hide();
						$('#paginationBar').show();
						
					}
				}
			});
		}
	});
}