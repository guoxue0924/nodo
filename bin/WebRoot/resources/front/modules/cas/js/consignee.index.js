$(document).ready(function() {
    $('#pagination').twbsPagination({
        totalPages: 1,
        visiblePages: 5,
        initiateStartPageClick:false,
        startPage: 1,
        onPageClick: function (event, pageIndex,pageSize) {
            $("#pageIndex").val(pageIndex);
            $("#pageSize").val(pageSize);
            loadData();
        }
    });
    loadData();

    $('body').on('click', '.delete', function(){
        if (confirm('确认删除吗')){
            var consigneeId = $(this).attr('data-id');
            $.post(BASE_URL + '/front/cas/consignee/delete', {consigneeId:consigneeId}, function(response){
                if (response.status == 0){
                	loadData();
                } else {
                    alert(response.msg);
                }
            }, 'json');
        }
    });
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
				url:BASE_URL+'/front/cas/consignee/page',
				data:paramJson,
				dataType:'json',
				timeout:60000,
				success:function(data){
					var count = 0;
					var html = "";
					$.each(data.data, function (index, data) {
		                html += template("consigneeTpl", data);
		            });
					$("#consigneeList tbody").html(html);
					count = data.count;
					
					$('#paginationBar li:eq(' +index+')').data('totalPages',(Math.ceil(count / pageSize) < 1) ? 1 : Math.ceil(count / pageSize));
					$('#paginationBar li:eq(' +index+')').data('visiblePages',pageSize);
				}
			});
		}
	});
}