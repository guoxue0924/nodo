$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
	/**
     * 刷新搜索
     */
    $(".action-refresh,#action_search").on('click',function(){
    	$('#content_listing').datagrid('reload');
    });
    
	/**
	 * 关键字搜索 - 支持回车
	 */
	$('input[name=key]').on('keypress', function (event) {
	    if (event.which == '13') {
	        $('#content_listing').datagrid("reload");
	        return false;
	    }
	});
	
	/**
	 * 重载已选属性列表的拖拽事件
	 */
	$('#selected_property').find('ul').sortable('refresh');
	
	/**
	 * 选择一个关联属性
	 */
	$("#content_listing>tbody").delegate('.select-single', 'change', function(){
		if ($(this).is(':checked')) {
			select_property($(this).parent().parent());
		} else {
			remove_property($('li#li_property_' + $(this).val()));
		}
	});
	
	/**
	 * 移除一个已选择的关联属性
	 */
	$("#selected_property").delegate('.fa-remove-property', 'click', function(){
		remove_property($(this).parent().parent());
	});
	
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_cancel' : form_cancel(); break;
			case 'submit_save_back' : back_listing = true; form_submit(); break;
			case 'submit_save_continue' : back_listing = false; form_submit(); break;
		}
	});
});


/**
 * 选择一个关联属性
 */
function select_property(obj) {
	var template_selected_property = '<li class="b-b m-t-none-reset" id="li_property_{#propertyId#}" propertyId="{#propertyId#}" draggable="true">' + 
        	'<a href="javascript:;">' + 
        	'<i title="移除该属性" class="fa fa-times pull-right m-t-xs fa-remove-property"></i>' + 
        	'<i class="fa fa-fw fa-ellipsis-v"></i><font class="property-name">{#property_name#}</font>' + 
        	'</a></li>';
	
	var propertyId = obj.find('td').eq(1).text();
	var property_name = obj.find('td').eq(2).text();
	
	var tsp = template_selected_property.replace(/{#propertyId#}/g, propertyId)
			.replace('{#property_name#}', property_name);
	
	/* 将新选择属性加入到已选列表，并重载该列表的拖拽事件 */
	$('#selected_property').find('ul').append(tsp).sortable('refresh');
}

/**
 * 移除一个已选择的关联属性
 */
function remove_property(obj) {
	$('#select_single_' + obj.attr('propertyId')).removeAttr('checked');
	obj.remove();
}

/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL + "/back/trendPropertyGroup/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	if (! $("input[name=propertyGroupName]").val()) {
		notice('edit_notice', img_delete + ' 分组名称不能为空', true, 5000);
		return false;
	}
	
	/* 处理属性 */
	var property_ids = '';
	var sp = new Array();
	$('#selected_property').find('ul>li').each(function(k){
		var propertyId = $(this).attr('propertyId');
		sp[k] = propertyId;
	});
	if (sp.length > 0) {
		propertyIds = sp.join(',');
	}
	$('input[name=propertyIds]').val(propertyIds);
	
	$(".input-submit").attr('disabled', true);
	
	var propertyGroupId = $("input[name=propertyGroupId]").val();
	
	var saveCallBack;
	if (propertyGroupId == '' || propertyGroupId == 0) {
		saveCallBack = form_save_added;
	} else {
		$("#edit_form").attr("action", BASE_URL + "/back/trendPropertyGroup/edit");
		saveCallBack = form_save_edited;
	}
	
	var options = {
            dataType:'json',
            timeout:60000,
            success:saveCallBack
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
    if (data.status === 0) {
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
                property: 'checkbox',
                label: ''
            },
            {
                property: 'propertyId',
                label: 'ID',
                sortable: true
            },
            {
            	property: 'labelName',
            	label: '属性名称',
            	sortable: false
            },
            {
            	property: 'propertyValues',
            	label: '属性值',
            	sortable: false
            },
            {
            	property: 'note',
            	label: '备注',
            	sortable: false
            },
            {
            	property: 'sortOrder',
            	label: '序号',
            	sortable: false
            },
            {
            	property: 'isSpec',
            	label: '开启规格',
            	sortable: false
            }
        ];
}

function formatData(items) {
	// 将已选属性添加到array中，循环时做判断是否选中
	var propertyArray = new Array();
	$('#selected_property').find('ul>li').each(function(){
		propertyArray.push($(this).attr('propertyId'));
	});
	$.each(items, function (index, item) {
		if (propertyArray.indexOf(item.propertyId + "") > -1) {
			item.checkbox = '<input type="checkbox" checked="checked" name="post[]" id="select_single_' + item.propertyId + '" class="select-single" value="' + item.propertyId + '">';
		} else {
			item.checkbox = '<input type="checkbox" name="post[]" id="select_single_' + item.propertyId + '" class="select-single" value="' + item.propertyId + '">';
		}
    	item.is_spec = item.is_spec == 1 ? '<i class="fa fa-check text-success"></i>' : '<i class="fa fa-ban text-danger"></i>';
    });
}