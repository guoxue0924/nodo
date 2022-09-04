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
	 * 关键字搜索
	 */
	$('input[name=key]').on('keypress', function (event) {
	    if (event.which == '13') {
	        $('#content_listing').datagrid("reload");
	        return false;
	    }
	});
	
	
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
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
		var endTime = $('#endTime').val();
		$('#startTime').datetimepicker('setEndDate', endTime);
		$('#endTime').datetimepicker('hide');
	});
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		var submit_type = $(this).attr("data_submit_type");
		switch (submit_type) {
		case 'submit_cancel':
			form_cancel();
			break;
		case 'submit_save_back': 
			back_listing = true;
			form_submit();
			break;
		case 'submit_save_continue':
			back_listing = false;
			form_submit();
			break;
		}
	});
	
	$("#categoryId").change(function() {
		var categoryId = $(this).val();
		if(categoryId > 0) {
			$('#skuSection').show();
			$.ajax({
		    	type:'get',
		        url:BASE_URL + '/back/promotionContent/getTemplate',
		        data:'templateName=' + $(this).children('option:selected').attr('id'),
		        dataType:'html',
		        timeout:60000,
		        success:function(data){
		        	var rules = $('#rules').val();
		        	var html = $(data);
		        	if(rules) {
		        		var json = $.parseJSON(rules);
		        		html.find('#x').val(json.x);
		        		html.find('#y').val(json.y);
		        	}
		    		$('#ruleDiv').html(html);
		    		$('#rules').val('');
		    		return false;
		    	}
		    });
		} else {
			$('#ruleDiv').html('暂无规则');
			$('#skuSection').hide();
		}
	});
	
	/**
	 * 重载已选关联商品列表的拖拽事件
	 */
	$('#selectedSkus').find('ul').sortable('refresh');

	
	/**
	 * 选择一个商品关联活动
	 */
	$("#content_listing > tbody").delegate('.select-single', 'click', function(event){
		if ($(this).is(':checked')) {
			
			var obj = $(this);
			var skuId = obj.parent().next().text();
			var startTime = $('#startTime').val();
			if(!startTime) {
				alert('请选择开始时间');
				$(this).removeAttr('checked');
				return;
			}
			var endTime = $('#endTime').val();
			if(!endTime) {
				alert('请选择结束时间');
				$(this).removeAttr('checked');
				return;
			}
			var promotionId = $('#promotionId').val();
			
			//判断活动类型是否重叠
		    $.ajax({
		    	url: BASE_URL + '/back/promotionContent/checkActive',
		    	data: {skuId:skuId,startTime:startTime,endTime:endTime,promotionId:promotionId},
		    	timeout: 2000,
		    	async : false,
		    	dataType : "json",	
		    	method: 'post',
		    	success:function(data){
		    		if (data.status == 0) {		    			
		    			relatedSku(obj.parent().next().text(), obj.parent().next().next().next().text());	
		    		}else if (data.status == 1) {
		    			alert(data.msg);
		    			event.preventDefault();
		    		}
		    	},
		    	error:function(){
		    		alert("网络异常");
		    	}
		    })
			
		} else {
			removeRelatedSku(skuId);
		}
		
	});

	/**
	 * 移除一个已选择的关联商品
	 */
	$("#selectedSkus").delegate('.fa-remove-related-goods', 'click', function(){
		removeRelatedSku($(this).parent().parent().attr('id').split("_")[1]);
	});
	
	if($("#promotionId").val() == '' || $("#categoryId").val() == 0) {
		$('#skuSection').hide();
	}
	
	if($("#categoryId").val() > 0) {
		$("#categoryId").change();
	}
});

/**
 * 表单提交处理
 */
function form_submit() {
	var promotionId = $("#promotionId").val();
	
	var categoryId = $("#categoryId").val();
	if(categoryId == 0) {
		notice('edit_notice', img_delete + ' 请选择活动类型', true, 5000);
		return;
	}
	
	var title = $('#title').val();
	if(!title) {
		notice('edit_notice', img_delete + ' 请输入活动名称', true, 5000);
		return;
	}
	
	var startTime = $('#startTime').val();
	if(!startTime) {
		notice('edit_notice', img_delete + ' 请选择开始时间', true, 5000);
		return;
	}
	
	var endTime = $('#endTime').val();
	if(!endTime) {
		notice('edit_notice', img_delete + ' 请选择结束时间', true, 5000);
		return;
	}
	
	
	var x = $('#x').val();
	if(!x) {
		notice('edit_notice', img_delete + ' 请输入' + $('#x').parent().prev().text().substr(1) + 'x的金额', true, 5000);
		return;
	}
	var y = $('#y').val();
	if(!y) {
		notice('edit_notice', img_delete + ' 请输入' + $('#y').parent().prev().text().substr(1) + 'y的金额', true, 5000);
		return;
	}
	
	if( y > x) {
		notice('edit_notice', img_delete + ' ' + $('#y').parent().prev().text().substr(1) + 'y的金额不能大于' + $('#x').parent().prev().text().substr(1) + 'x的金额', true, 5000);
		return;
	}
	var rules = {x:x,y:y};
	$('#rules').val(JSON.stringify(rules));
	
	var skuSize = $('#selectedSkus').find('ul').children('li').length;
	if(skuSize == 0) {
		notice('edit_notice', img_delete + ' 请选择商品', true, 5000);
		return;
	}
	
	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (promotionId == '' || promotionId == 0) {
		saveCallBack = form_save_added;
	} else {
		saveCallBack = form_save_edited;
	}
	
	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack
	};
	$("#edit_form").ajaxSubmit(options);
	return false;
}

/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL+"/back/promotionContent/index";
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
	if (data.status === 0) {
		alert('添加成功!');

		// 判断是否返回列表管理
		if (back_listing == true) {
			form_cancel();
		} else {
			window.location.href = BASE_URL+"/promotionContent/add";
		}
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
	if (data.status === 0) {
		alert('编辑成功!');
		form_cancel();
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}

function columnsDefined() {
	return [
				{
					property : 'a',
					label : '',
				},
				{
					property : 'skuId',
					label : 'ID',
				},
				{
					property : 'categoryName',
					label : '所属分类',
				},
				
				{
					property : 'name',
					label : '商品名称',
				},
				{
					property : 'brandName',
					label : '所属品牌',
				},
				{
					property : 'price',
					label : '价格',
				},
				{
					property : 'property',
					label : '规格',
				}
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		var flag = false;
		$('#selectedSkus').find('li').each(function(){
			if($(this).attr('id') == ('li_' + item.skuId)) {
				flag = true;
			}
		});
		item.a = '<input type="checkbox" id="checkbox'+ item.skuId +'" class="select-single"' + (flag ? 'checked' : '') +' >';
	});
}


/**
* 选择一个关联商品
*/
function relatedSku(skuId, name) {
	var template = $('#sku_template').clone();
	template.find('li').attr('id', 'li_' + skuId);
	template.find('font').text(name);
	template.find('input').remove();
	/* 将新选择属性加入到已选列表，并重载该列表的拖拽事件 */
	$('#selectedSkus').find('ul').append(template.html()).sortable('refresh');
	var checkboxTemplate = $('#sku_template').find('input').clone();
	checkboxTemplate.attr('id', 'cb_' + skuId).val(skuId);
	$('#rules').after(checkboxTemplate);
}

/**
* 移除一个已选择的关联商品
*/
function removeRelatedSku(skuId) {
	$('#li_' + skuId).remove();
	$('#cb_' + skuId).remove();
	$('#checkbox' + skuId).removeAttr('checked');
}