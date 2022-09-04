$(function(){
	
	
	$('#startDate').datepicker({dateFormat: 'yy-mm-dd'});
    $('#endDate').datepicker({dateFormat: 'yy-mm-dd'});

	
	
	loadData();
	
    $('.tabmenu li').each(function(index){
    	var $this = $(this);
    	$(this).on('click',function(){
    		$this.addClass('active').removeClass('normal');
    		if(index == 0) {
    			$this.next().addClass('normal').removeClass('active');
    		} else {
    			$this.prev().addClass('normal').removeClass('active');
    		}
    		$('#isUserDel').val(index);
    		loadData();
    	})
    })
    
    $('body').on('click', '.delete', function(){
        var orderId = $(this).attr('data-param');
        if (confirm('确定要删除吗？')){
            $.post(BASE_URL + '/front/bts/order/delete', {orderId:orderId, isDelete:1}, function(response){
                if(response.status == 0){
                	loadData();
                }
            }, 'json');
        }
    });

    $('body').on('click', '.return', function(){
        var orderId = $(this).attr('data-param');
        if (confirm('确定要还原吗？')){
            $.post(BASE_URL + '/front/bts/order/delete', {orderId:orderId}, function(response){
                if(response.status == 0){
                	loadData();
                }
            }, 'json');
        }
    });
    
    $('body').on('click', '.delete-forever', function(){
        var order_id = $(this).attr('data-param');
        if (confirm('确定要永久删除吗？')){
            $.post(BASE_URL + '/front/bts/order/delete', {orderId:orderId, isDelete:2}, function(response){
                if(response.status == 0){
                	loadData();
                }
            }, 'json');
        }
    })
    
    $('.submit').on('click', loadData);
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
				url:BASE_URL+'/front/bts/order/page',
				data:paramJson,
				dataType:'html',
				timeout:60000,
				success:function(data){
					$('.order tbody').remove();
					var count = 0;
					$(data).each(function(){
						var type = this.nodeType;
						if(this.nodeType != 3) {
							if(this.nodeName == 'INPUT') {
								count = $(this).val();
							}
							$('.order').append($(this).html());
						} 
					})
					$('#paginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#paginationBar li:eq(' +index+')').data('visiblePages',pageSize);
					if(count == 0) {
						$('.order .ncs-norecord').show();
						$('#paginationBar').hide();
					} else {
						$('.order .ncs-norecord').hide();
						$('#paginationBar').show();
						
					}
				}
			});
		}
	});
}