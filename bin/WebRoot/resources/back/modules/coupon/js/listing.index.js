$(document).ready(function() {
	$('#createStartDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	$('#createEndDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	$('#modifyStartDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	$('#modifyEndDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});

	loadDataGridContent(columnsDefined(), 'formatData');
	
	
    /**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('#content_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 重置搜索条件
	 */
	$('.action-reset').on('click', function(){
		$('#createStartDate').val('');
		$('#createEndDate').val('');
		$('#modifyStartDate').val('');
		$('#modifyEndDate').val('');
		$('#type').find("option:selected").prop("selected",false);
		$('#status').find("option:selected").prop("selected",false);
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
	 * 锁定，解锁 - 单条
	 */
    $("#content_listing").delegate('.operate-delete', 'click', function(){
		var cpnsId = $(this).attr("cpnsId");
		changeCouponListStatus(cpnsId);
		return false;
	});
	
	
});

/**
 * 启锁定，解锁
 */
function changeCouponListStatus(cpnsId) {
	var del = confirm('确定要'+$('#cpnsId_' + cpnsId).attr('title')+'所选优惠券吗？');
	if (! del) {return false;}
	/* 执行 */
	var status = $('#cpnsId_' + cpnsId).attr('status');
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/couponListing/updateStatus',
        data:
        	{
        		'cpnsId': cpnsId,
        		'status': status,
        	 },
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status > 0) {
    			alert(data.msg);
    		}
    		$('#content_listing').datagrid('reload');
    		return false;
    	}
    });
}

function columnsDefined() {
	return [
            {
                property: 'cpnsId',
                label: 'ID',
                sortable: false
            },
            {
                property: 'couponName',
                label: '优惠券名称',
                sortable: false
            },
            {
                property: 'username',
                label: '用户名称',
                sortable: false
            },
            {
                property: 'cpnsStatus',
                label: '优惠券状态',
                sortable: false
            },
            {
            	property: 'isDel',
            	label: '删除标记',
            	sortable: false
            },
            {
            	property: 'disabled',
            	label: '是否失效',
            	sortable: false
            },
            {
                property: 'ctime',
                label: '创建时间',
                sortable: false
            },
            {
                property: 'mtime',
                label: '修改时间',
                sortable: false
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
    	
    	item.action = '';
        if (item.status == 1) {
        	item.action += '<a href="javascript:;" class="operate-delete" id="cpnsId_' + item.cpnsId + '" cpnsId="' + item.cpnsId + '" title="锁定" status="'+item.status+'"><i class="fa fa-lock"></i></a>';
        } else if (item.status == 2) {
        	item.action += '<a href="javascript:;" class="operate-delete" id="cpnsId_' + item.cpnsId + '" cpnsId="' + item.cpnsId + '" title="解锁" status="'+item.status+'"><i class="fa fa-unlock"></i></a>';
        }
    	
        var strStatus = '未知';
        if (item.cpnsStatus == 0) {
        	strStatus = '未启用';
        } else if (item.cpnsStatus == 1) {
        	strStatus = '启用';
        } else if (item.cpnsStatus == 2) {
        	strStatus = '锁定';
        }
        item.cpnsStatus = strStatus;
        
        var isDel = '未知';
        if (item.isDel == 0) {
        	isDel = '未删除';
        } else if (item.isDel == 1) {
        	isDel = '已删除';
        }
        item.isDel = isDel;
        
        var disabled = '未知';
        if (item.disabled == 0) {
        	disabled = '未失效';
        } else if (item.disabled == 1) {
        	disabled = '已失效';
        } 
        item.disabled = disabled;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}