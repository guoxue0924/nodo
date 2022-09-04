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
	 * 搜索对回车的支持
	 */
	$("input[name=key]").on('keypress', function (event) {
		if (event.which == '13' && $(this).val()) {
			$('#content_listing').datagrid('reload');
			return false;
		}
	});
	
	/**
     * 审核
     */
    $('#content_listing').delegate('.setting-verify', 'click', function(){
    	var storeId = $(this).attr('storeId');
    	
    	var strConfirm = "确定要通过该商户的审核请求吗？";
    	if ($("#verify_" + storeId).is(':checked')) {
    		strConfirm = "确定要解除该商户的审核状态吗？";
    	}
    	if (! confirm(strConfirm)) {return false;}
    	
    	verifyStore(storeId);
		return false;
    });
    
    
    /**
	 * 删除 - 单条
	 */
    $("#content_listing").delegate('.operate-delete', 'click', function(){
		var storeId = $(this).attr("storeId");
		doDeleteStore(storeId);
		return false;
	});
});


/**
 * 审核
 */
function verifyStore(storeId) {
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/storeContent/verify',
        data:'storeId=' + storeId,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			var d = data.data;
    			if (d.is_verify == 1) {
    				$('#verify_' + storeId).prop('checked', true);
    			} else {
    				$('#verify_' + storeId).prop('checked', false);
    			}
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}


/**
 * 删除
 */
function doDeleteStore(storeId) {
	var del = confirm('确定要删除该店铺吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/storeContent/delete',
        data:'storeId=' + storeId,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
				$("#store_" + storeId).parent().parent().remove();
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
                property: 'storeId',
                label: 'ID',
                sortable: false
            },
            {
            	property: 'username',
            	label: '商户用户名',
            	sortable: false
            },
            {
            	property: 'storeName',
            	label: '店铺名称',
            	sortable: false
            },
            {
                property: 'companyName',
                label: '公司名称',
                sortable: false
            },
            {
            	property: 'region',
            	label: '所在地区',
            	sortable: false
            },
            {
            	property: 'legalpName',
            	label: '法人姓名',
            	sortable: false
            },	            
            {
            	property: 'tel',
            	label: '联系电话',
            	sortable: false
            },	            
            {
            	property: 'isVerify',
            	label: '审核状态',
            	sortable: false
            },
            {
            	property: 'status',
            	label: '启用状态',
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
		/* 拼装所在地区 */
		item.region = item.provinceName + " " + item.cityName + " " + item.areaName;
		
		item.username = item.username == null ? "" : item.username;
		item.storeName = item.storeName == null ? "" : item.storeName;
		item.companyName = item.companyName == null ? "" : item.companyName;
		item.legalpName = item.legalpName == null ? "" : item.legalpName;
		item.tel = item.tel == null ? "" : item.tel;
		
    	/* 查看详情 */
    	item.a = '<a href="' + BASE_URL + '/back/storeContent/detail/?storeId=' + item.storeId + '"><i class="fa fa-search-plus" title="查看详情"></i></a>';
    	
    	/* 审核状态 */
    	item.isVerify = item.isVerify == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';
    	
    	/* 启用状态 */
    	item.status = item.status == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';
        
        /* 操作 */
    	item.action = '<a href="' + BASE_URL + '/back/storeContent/edit?storeId=' + item.storeId + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;';
    });
}