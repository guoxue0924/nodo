$(document).ready(function() {
	/**
	 * 预处理日期时间选择控件
	 */
	$('#startTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
		var startTime = $('#startTime').val();
		$('#endTime').datetimepicker('setStartDate', startTime);
		$('#startTime').datetimepicker('hide');
	});
	
	$('#endTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii'
	}).on('changeDate', function(ev){
		var endTime = $('#endTime').val();
		$('#startTime').datetimepicker('setEndDate', endTime);
		$('#endTime').datetimepicker('hide');
	});
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_type = $(this).attr("data_submit_type");
		switch (submit_type) {
			case 'submit_cancel' : form_cancel(); break;
			case 'submit_save_back' : back_listing = true; form_submit(); break;
			case 'submit_save_continue' : back_listing = false; form_submit(); break;
		}
	});
	
	/**
	 * 搜索
	 */
	$('#action_search').on('click', function(){
		$('.datagrid').datagrid('reload');
		return false;
	});
	
	/**
	 * 关键字搜索
	 */
	$('input[name=key]').on('keypress', function (event) {
		if (event.which == '13') {
			$('.datagrid').datagrid("reload");
			return false;
		}
	});
	
	loadDataGridContent(columnsDefined(), 'formatData');
	
	/**
	 * 选择一个关联属性
	 */
	$(".datagrid").delegate('.select-single', 'change', function(){
		if ($(this).is(':checked')) {
			select_property($(this));
		}
	});
	
});

/**
 * 选择一个关联属性
 */
function select_property(obj) {
	
	var skuId = obj.parent().next().text();
	var name = obj.parent().next().next().text();
	
	$('#skuId').val(skuId);
	$('#good_select > font').text(name);
}

/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL+"/back/grouponBulk/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	var bulkId = $("#bulkId").val();
	
	if (! $("#skuId").val()) {
		notice('edit_notice', img_delete + ' 请选择关联商品', true, 5000);
		return false;
	}
	
	if (! $("#startTime").val()) {
		notice('edit_notice', img_delete + ' 请选择活动开始时间', true, 5000);
		return false;
	}
	
	if (! $("#endTime").val()) {
		notice('edit_notice', img_delete + ' 请选择活动结束时间', true, 5000);
		return false;
	}
	
	if (! $("#inventorySum").val()) {
		notice('edit_notice', img_delete + ' 请输入库存', true, 5000);
		return false;
	}
	
	if (! $("#price").val()) {
		notice('edit_notice', img_delete + ' 请输入购价格', true, 5000);
		return false;
	}
	
	if (! $("#buyNumber").val() || $("#buyNumber").val() < 0) {
		$("#buyNumber").val(0);
	}
	
	$(".input-submit").attr('disabled', true);
	
	var saveCallBack;
	if (bulkId == '' || bulkId == 0) {
		saveCallBack = form_save_added;
	} else {
		saveCallBack = form_save_edited;
	}
	
	var options = {
            dataType:'json',
            timeout:60000,
            success:saveCallBack,
            error:ajaxError
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
    if(data.status === 0) {
        notice('edit_notice', img_done + ' 添加成功!', true, 5000);
        
        // 判断是否返回列表管理
        if (back_listing == true) {
        	form_cancel();
        }
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
    if (data.status === 0) {
        notice('edit_notice', img_done + ' 编辑成功!', true, 5000);
        form_cancel();
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}


function columnsDefined() {
	return [
            {
            	property: 'a',
            	label: ''
            },
            {
                property: 'skuId',
                label: 'ID',
            },
            {
            	property: 'name',
            	label: '商品名称',
            },    
            {
                property: 'price',
                label: '销售价格',
            },
            {
                property: 'stock',
                label: '库存',
            },
            {
                property: 'brandName',
                label: '品牌',
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
        item.a = '<input type="radio" name="selectSku" class="select-single"';
        if($("#skuId").val() !='' && $("#skuId").val() == item.skuId) {
        	item.a += ' checked ';
        }
        item.a += '>';
    });
}