/**
 * 定义一些常量
 */
var property_specs; // 待选规格项
var property_specs_added = new Array(); // 已添加的规格信息
var is_category_changed = 0; // 添加/编辑商品时，是否改变了分类。1：是；0：否；
var checkboxName = new Array();
var imageInfo = $("#imageInfo").val();

$(document).ready(function() {
	content_id = $('#contentId').val();
	/**
	 * 初始化品牌、扩展属性、规格等信息
	 */
	load_brand();
	
	if ($('#isSpec').is(':checked')) {
		load_spec_option();
	}
	$('input[name=categoryIdLast]:last').val($('#categoryId').val());
	
	/**
	 * 动态加载分类相关联的品牌、扩展属性和规格信息
	 */
	$('#categoryId').change(function(){
		is_category_changed = 1;
		
		/* 加载品牌 */
		load_brand();
		
		/* 判断开启规格的开关是否打开，若已打开，则需重新加载规格 */
		$('#spec_list').empty();
		property_specs_added = [];
		load_spec_option();
		
		/* 初始化编辑规格栏目的样式 */
		$('#btn_spec_reselect').trigger('click');
		$('#spec_option_list').find('input[type=checkbox]').prop('checked', false);
		
		/* 记录分类的最终状态 */
		$('input[name=categoryIdLast]:last').val($(this).val());
	});
	
	/**
	 * 开启/关闭规格
	 */
	$('#isSpec').change(function(){
		/* 加载规格项 */
		load_spec_option();
	});
});

/**
 * 动态加载分类相关联的品牌信息
 */
function load_brand() {
	var category_id = $('#categoryId').val();
	var brand_id_now = $("select[name=brandId]").attr('brandId');
	var str_option = '';
	
	if (category_id == 0 || category_id == '') {
		return false;
	}

	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/goodsContent/getBrandByCategoryId',
        data:'categoryId=' + category_id,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			/* 清空现有品牌信息 */
    			$("select[name=brandId]").find("option:gt(0)").remove();
    			
    			/* 加载新的品牌信息 */
    			var d = data.brands;
    			if (d != null && d.length > 0) {
	    			for (var i = 0; i < d.length; i++) {
	    				var selected = d[i]['brand_id'] == brand_id_now ? 'selected' : '';
	    				str_option += '<option value="' + d[i]['brandId'] + '" ' + selected + '>' + d[i]['brandName'] + '</option>';
	    			}
	    			$("select[name=brandId]").append(str_option);
    			}
    			var createTable = "<thead>"
    				+ "	<tr>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">库存 <input type=\"text\" data-filed=\"stock\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">销售价 <input type=\"text\" data-filed=\"price\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">市场价 <input type=\"text\" data-filed=\"priceMarket\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">成本价 <input type=\"text\" data-filed=\"priceCost\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">重量 <input type=\"text\" data-filed=\"weight\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">长 <input type=\"text\" data-filed=\"length\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">宽 <input type=\"text\" data-filed=\"wide\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "		<th align=\"center\" style=\"width: 10%;text-align: center;\">高 <input type=\"text\" data-filed=\"height\" style=\"width: 40px;\" value=\"\" /><input type=\"button\" title=\"批量设置\" value=\"设置\" onclick=\"addValues(this);\" /></th>"
    				+ "	</tr>" + "</thead>" + "<tbody>" + "</tbody>";
    			$("#createTable").html(createTable);
    		}
    		return false;
    	}
    });
}

/**
 * 加载规格项
 */
function load_spec_option() {
	var category_id = $('#categoryId').val();
	var contentId = $("#contentId").val();
	var valueId = $("#valueId").val();
	var valueArray = valueId.split(",");
	
	if (category_id == 0 || category_id == '') {
		return false;
	}
	
	/* 显示或隐藏规格编辑区 */
	if ($('#isSpec').is(':checked')) {
		$('#edit_base_spec').show();
		$('#property_base').hide();
	} else {
		$('#edit_base_spec').hide();
		$('#property_base').show();
		return false;
	}
	
	/* 若已加载，则无需重复加载 */
	if ($('#spec_list').html()) {
		return false;
	}
	
	/* 编辑状态时的基本元素属性处理 */
	if (content_id && is_category_changed == 0) {
		$('#btn_spec_select_ok').attr('disabled', true);
		$('#edit_base_spec_option').find(':checkbox').attr('disabled', true);
		$('#btn_spec_add').removeAttr('disabled');
		$('#btn_spec_reselect').show();
	}
	
	/* 移除旧的扩展属性元素 */
	$('#edit_base_property>.panel-body:first').empty();
	/* 定义扩展属性模板 */
	var template_property = '<div class="form-group m-b-xs">' + 
        	'<label class="col-sm-2 control-label">{#label_name#}</label>' + 
        	'<div class="col-sm-6">' + 
            '<select name="property" class="form-control">' + 
            '{#options#}' + 
            '</select>' + 
            '</div>' + 
            '</div>' + 
            '<div class="line line-dashed line-sm pull-in"></div>';
	
	/* 动态获取规格项数据 */
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/goodsContent/getPropertyByCategoryId',
        data:'categoryId=' + category_id + '&isSpec=1',
        dataType:'json',
        timeout:60000,
        success:function(data){
        	/* 若无规格项，则进行提示 */
    		if (data.status != 0 || data.data == null) {
    			$('#edit_base_spec_option').hide();
    			$('#edit_base_spec>.panel-body:last').show();
				return false;
    		}
    		var priceProperty = data.data.priceProperty;
    		var otherProperty = data.data.otherProperty;
    		$('#edit_base_spec_option').show();
    		$('#edit_base_spec>.panel-body:last').hide();
			
    		/* 编辑状态时，处理已选商品规格 */
    		var spec_property_id = $('input[name=specPropertyId]:last').val();
    		var spec_property_ids = new Array();
    		if (spec_property_id) {
    			spec_property_ids = spec_property_id.split(',');
    		}
			/* 加载规格项元素 */
    		var tbody_format = '';
    		checkboxName = new Array();
			$(priceProperty).each(function(){
				var isImage = this.isImage;
				// 把价格属性保存到全局变量中
				if (checkboxName.indexOf("checkboxName_" + this.propertyId) < 0) {
					checkboxName.push("checkboxName_" + this.propertyId);
				}
				
				// 判断是否选中
				var spec_checked = '';
				if ($.inArray(this.propertyId, spec_property_ids) > -1 && is_category_changed == 0) {
					spec_checked = 'checked';
				}
				// 格式化模板
				tbody_format += '<tr>' + 
//						'<td><input type="checkbox" class="spec-option select-single" value="' + this.propertyId + '" ' + spec_checked + ' /></td>' + 
						'<td>' + this.labelName + '</td>' + 
						'<td>';
				var v = this.propertyValues;
				var labelName = this.labelName;
				var option_arr = new Array();
				$(v).each(function(k){
					var checked = valueArray.indexOf(this.propertyValueId + "") > -1 ? 'checked="checked"' : '';
					option_arr[k] = "<input type='checkbox' " +
							""+checked+"" +
							"name='checkboxName_"+this.propertyId+"' " +
							"data-property-id='"+this.propertyId+"' " +
							"data-property='"+labelName+"' " +
							"data-property-value-id='"+this.propertyValueId+"' " +
							"data-property-value='"+this.propertyValue+"' " +
							"data-is-image='"+isImage+"' " +
							"value='"+this.propertyValueId+"' " +
							"onclick='getCheckedValue(this);' />&nbsp;"+this.propertyValue+"";
				});
				var str_option = option_arr.join(' &nbsp;&nbsp;&nbsp;&nbsp;');
				tbody_format += str_option + '</td></tr>';
			});
			$('#spec_option_list>tbody').html(tbody_format);
			
			/* 编辑状态时的基本元素属性处理 */
			
			/* 动态获取扩展属性数据 */
    		$('#edit_base_property').show();
    		/* 加载新的扩展属性元素 */
			$(otherProperty).each(function(){
				// 格式化模板
				var template_property_format = template_property.replace(/{#label_name#}/g, this.labelName);
				var v = this.propertyValues;
				var str_option = '';
				$(v).each(function(){
					var str_property = this.propertyId + '_' + this.propertyValueId;
					var str_property_temp = ',' + str_property + ',';
					var selected = valueArray.indexOf(this.propertyValueId + "") > -1 ? 'selected="selected"' : '';
					str_option += '<option value="' + str_property + '" ' + selected + '>' + this.propertyValue + '</option>';
				});
				template_property_format = template_property_format.replace(/{#options#}/g, str_option);
				// 加载格式化后的模板
				$('#edit_base_property>.panel-body:first').append(template_property_format);
			});
			/* 处理最后一条分割线 */
			$('#edit_base_property').find('.line-dashed:last').remove();
			
			/* 根据后台的属性生成table的列 */
			createTableCol(priceProperty);
			
			if (valueId != null && contentId != null) {
				editAddTable();
			}
			
    		return false;
    	}
    });
}

/**
 * 根据后台传来的属性动态添加table的列
 * 
 * @author zhangzheng
 * @date 2015-11-13 下午2:50:23
 */
function createTableCol(priceProperty) {
	// 因为使用每次都在table最前面新增列，所以从后向前读取数组
	for (var i = priceProperty.length - 1; i >= 0; i--) {
		$th = $("<th align=\"center\" style=\"width: 10%;text-align: center;\">" + priceProperty[i].labelName + "</th>");
		$("#createTable>thead>tr").prepend($th);
	}
}

/**
 * 点击属性的checkbox时动态生成表格的行
 * 
 * @author zhangzheng
 * @date 2015-11-13 下午3:52:45
 * @param obj
 *            点击的checkbox对象
 */
function getCheckedValue(obj) {

	// 如果是点击取消checkbox，移除这个属性所在的行
	if (!obj.checked) {
		if(confirm('如果取消则会删除表格中对应的行数据\n                 是否确定删除？')){
			removeValue(obj);
			return false;
		} else {
			obj.checked = true;
		}
	}

	var addCol = new Array();

	var checkedValue = new Array();
	var checkedName = new Array();
	
	var propertyArray = null;
	var propertyIdArray = null;
	var propertyValueArray = null;
	var propertyValueIdArray = null;
	var propertyIsImage = null;
	var propertyArray2 = null;
	var propertyIdArray2 = null;
	var propertyValueArray2 = null;
	var propertyValueIdArray2 = null;
	var propertyIsImage2 = null;
	var propertyArray3 = null;
	var propertyIdArray3 = null;
	var propertyValueArray3 = null;
	var propertyValueIdArray3 = null;
	var propertyIsImage3 = null;

	if (checkboxName.length > 0) {
		propertyArray = new Array();
		propertyIdArray = new Array();
		propertyValueArray = new Array();
		propertyValueIdArray = new Array();
		propertyIsImage = 0;
		if (checkboxName.length > 1) {
			propertyArray2 = new Array();
			propertyIdArray2 = new Array();
			propertyValueArray2 = new Array();
			propertyValueIdArray2 = new Array();
			propertyIsImage2 = 0;
			if (checkboxName.length > 2) {
				propertyArray3 = new Array();
				propertyIdArray3 = new Array();
				propertyValueArray3 = new Array();
				propertyValueIdArray3 = new Array();
				propertyIsImage3 = 0;
			}
		}
	}
	
	// 将选中的checkbox的值封装成数组
	for (var i = 0; i < checkboxName.length; i++) {
		var input = document.getElementsByName(checkboxName[i]);
		for (var index = 0; index < input.length; index++) {
			if (input[index].type == "checkbox" && input[index].checked) {
				checkedValue.push(input[index].value); // 原来的property value
				checkedName.push(input[index].name); // 原来的property
				
				if (i == 0) {
					propertyArray.push(input[index].getAttribute("data-property"));
					propertyIdArray.push(input[index].getAttribute("data-property-id"));
					propertyValueArray.push(input[index].getAttribute("data-property-value"));
					propertyValueIdArray.push(input[index].getAttribute("data-property-value-id"));
					propertyIsImage = input[index].getAttribute("data-is-image");
				} else if (i == 1) {
					propertyArray2.push(input[index].getAttribute("data-property"));
					propertyIdArray2.push(input[index].getAttribute("data-property-id"));
					propertyValueArray2.push(input[index].getAttribute("data-property-value"));
					propertyValueIdArray2.push(input[index].getAttribute("data-property-value-id"));
					propertyIsImage2 = input[index].getAttribute("data-is-image");
				} else if (i == 2) {
					propertyArray3.push(input[index].getAttribute("data-property"));
					propertyIdArray3.push(input[index].getAttribute("data-property-id"));
					propertyValueArray3.push(input[index].getAttribute("data-property-value"));
					propertyValueIdArray3.push(input[index].getAttribute("data-property-value-id"));
					propertyIsImage3 = input[index].getAttribute("data-is-image");
				}
			}
		}
	}
	
	if (propertyIsImage != null && propertyIsImage == 1) {
		for (var i = 0; i < propertyArray.length; i++) {
			var flag = checkImage(propertyValueIdArray[i]);
			if (!flag) {
				var imageHTML = createBaseImageTD(propertyIdArray[i], propertyValueIdArray[i], propertyValueArray[i]);
				$("#skuImages").append(imageHTML); // 在图片table最后面添加一行
			}
		}
	} else if (propertyIsImage2 != null && propertyIsImage2 == 1) {
		for (var i = 0; i < propertyArray2.length; i++) {
			var flag = checkImage(propertyValueIdArray2[i]);
			if (!flag) {
				var imageHTML = createBaseImageTD(propertyIdArray2[i], propertyValueIdArray2[i], propertyValueArray2[i]);
				$("#skuImages").append(imageHTML); // 在图片table最后面添加一行
			}
		}
	} else if (propertyIsImage3 != null && propertyIsImage3 == 1) {
		for (var i = 0; i < propertyArray3.length; i++) {
			var flag = checkImage(propertyValueIdArray3[i]);
			if (!flag) {
				var imageHTML = createBaseImageTD(propertyIdArray3[i], propertyValueIdArray3[i], propertyValueArray3[i]);
				$("#skuImages").append(imageHTML); // 在图片table最后面添加一行
			}
		}
	}
	
	// checkdName中是所有选中的name，有重复，addCol中是要新增的列
	for (var v = 0; v < checkedName.length; v++) {
		if (addCol.indexOf(checkedName[v]) < 0
				&& $("#th_" + checkedName[v]).val() == null) {
			addCol.push(checkedName[v]);
		}
	}

	// 只有一个属性
	if (propertyArray.length > 0 && propertyArray2 == null && propertyArray3 == null) {
		for (var i = 0; i < propertyArray.length; i++) {
			flag = checkRow(propertyValueIdArray[i], null, null);
			if (!flag) {
				var trHTML = "<tr>";
				trHTML += "<td><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+propertyValueIdArray[i]+"' value='"+propertyValueArray[i]+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+propertyValueIdArray[i]+"' /><input type='hidden' name='propertyId' value='"+propertyIdArray[i]+"' /></td>";
				trHTML += createBaseTD();
				trHTML += "<td><input type='hidden' id='row_"+propertyValueIdArray[i]+"' value='check' /><input type='hidden' name='skuId' value='0' /></td>";
				trHTML += "</tr>";
				$("#createTable").append(trHTML); // 在table最后面添加一行
			}
		}
	} else if (propertyArray.length > 0 && propertyArray2.length > 0 && propertyArray3 == null) { // 有两个属性
		for (var i = 0; i < propertyArray.length; i++) {
    		for (var j = 0; j < propertyArray2.length; j++) {
		  
// $("#tab1>thead>tr").append($("#temp").clone());
		  // 用clone代替每次在这里新建字符串
    			flag = checkRow(propertyValueIdArray[i], propertyValueIdArray2[j], null);

    			if (!flag) {
    				var trHTML = "<tr>";
    				trHTML += "<td><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+propertyValueIdArray[i]+"' value='"+propertyValueArray[i]+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+propertyValueIdArray[i]+"' /><input type='hidden' name='propertyId' value='"+propertyIdArray[i]+"' /></td>";
    				trHTML += "<td><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+propertyValueIdArray2[j]+"' value='"+propertyValueArray2[j]+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+propertyValueIdArray2[j]+"' /><input type='hidden' name='propertyId2' value='"+propertyIdArray2[j]+"' /></td>";
    				trHTML += createBaseTD();
    				trHTML += "<td><input type='hidden' id='row_"+propertyValueIdArray[i]+"_"+propertyValueIdArray2[j]+"' value='check' /><input type='hidden' name='skuId' value='0' /></td>";
    				trHTML += "</tr>";
    				$("#createTable").append(trHTML);// 在table最后面添加一行
    			}
    		}
    	}
	} else if (propertyArray.length > 0 && propertyArray2.length > 0 && propertyArray3.length > 0) { // 有三个属性
		for (var i = 0; i < propertyArray.length; i++) {
    		for (var j = 0; j < propertyArray2.length; j++) {
    			for (var k = 0; k < propertyArray3.length; k++) {
    				flag = checkRow(propertyValueIdArray[i], propertyValueIdArray2[j], propertyValueIdArray3[k]);
        			if (!flag) {
        				var trHTML = "<tr>";
        				trHTML += "<td><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+propertyValueIdArray[i]+"' value='"+propertyValueArray[i]+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+propertyValueIdArray[i]+"' /><input type='hidden' name='propertyId' value='"+propertyIdArray[i]+"' /></td>";
        				trHTML += "<td><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+propertyValueIdArray2[j]+"' value='"+propertyValueArray2[j]+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+propertyValueIdArray2[j]+"' /><input type='hidden' name='propertyId2' value='"+propertyIdArray2[j]+"' /></td>";
        				trHTML += "<td><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+propertyValueIdArray3[k]+"' value='"+propertyValueArray3[k]+"' disabled='true' /><input type='hidden' name='propertyValueId3' value='"+propertyValueIdArray3[k]+"' /><input type='hidden' name='propertyId3' value='"+propertyIdArray3[k]+"' /></td>";
        				trHTML += createBaseTD();
        				trHTML += "<td><input type='hidden' id='row_"+propertyValueIdArray[i]+"_"+propertyValueIdArray2[j]+"_"+propertyValueIdArray3[k]+"' value='check' /><input type='hidden' name='skuId' value='0' /></td>";
        				trHTML += "</tr>";
        				$("#createTable").append(trHTML);// 在table最后面添加一行
        			}
    			}
    		}
    	}
	}
}

/**
 * 判断行是否已存在
 * 
 * @author zhangzheng
 * @date 2015-11-16
 * @param valueId1
 * @param valueId2
 * @param valueId3
 * @returns true:存在;false:不存在;
 */
function checkRow(valueId1, valueId2, valueId3) {
	var v;
	if (valueId1 != null && valueId2 == null && valueId3 == null) {
		v = $("#row_"+valueId1).val();
	} else if (valueId1 != null && valueId2 != null && valueId3 == null) {
		v = $("#row_"+valueId1+"_"+valueId2).val();
	} else {
		v = $("#row_"+valueId1+"_"+valueId2+"_"+valueId3).val();
	}
	
	if (v != null) {
		return true;
	} else {
		return false;
	}
}

function checkImage(valueId) {
	var v;
	if (valueId != null) {
		v = $("#imgrow_"+valueId).val();
	}
	if (v != null) {
		return true;
	} else {
		return false;
	}
}

/**
 * 获取所有要移除的行
 * 
 * @author zhangzheng
 * @date 2015-11-16
 * @param obj
 */
function removeValue(obj) {
	if (!obj.checked) {
		var removeImageArray = $("#uploadImage_"+obj.getAttribute("data-property-value-id"));
		removeRow(removeImageArray);
		
		var checkboxArray  =  $("#createTable").find('input[name="check_'+obj.getAttribute("data-property-value-id")+'"]');
		removeRow(checkboxArray);
	}
}

/**
 * 移除行
 * 
 * @author zhangzheng
 * @date 2015-11-16
 * @param obj
 */
function removeRow(obj){
	$.each(obj,function(index, data){
		$(data).parents('tr').remove();
	});
}

/**
 * 为同一列属性赋值
 * 
 * @author zhangzheng
 * @date 2015-11-16
 * @param obj
 */
function addValues(obj) {
// var input = $(obj).parents('td').find('input');
	var input = $(obj).parents('th').find('input');
	var inputs = document.getElementsByName(input[0].getAttribute("data-filed"));
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].value = input[0].value;
	}
}

/**
 * 用于点击修改页面时动态生成table里的行
 */
function editAddTable() {
	var contentSkuTos = $("#contentSkuTos").val();
	var skuArray = eval(contentSkuTos);
	
	var addCol = new Array();

	var checkedValue = new Array();
	var checkedName = new Array();
	
	var propertyArray = null;
	var propertyIdArray = null;
	var propertyValueArray = null;
	var propertyValueIdArray = null;
	var propertyIsImage = null;
	var propertyArray2 = null;
	var propertyIdArray2 = null;
	var propertyValueArray2 = null;
	var propertyValueIdArray2 = null;
	var propertyIsImage2 = null;
	var propertyArray3 = null;
	var propertyIdArray3 = null;
	var propertyValueArray3 = null;
	var propertyValueIdArray3 = null;
	var propertyIsImage3 = null;
	

	if (checkboxName.length > 0) {
		propertyArray = new Array();
		propertyIdArray = new Array();
		propertyValueArray = new Array();
		propertyValueIdArray = new Array();
		propertyIsImage = 0;
		if (checkboxName.length > 1) {
			propertyArray2 = new Array();
			propertyIdArray2 = new Array();
			propertyValueArray2 = new Array();
			propertyValueIdArray2 = new Array();
			propertyIsImage2 = 0;
			if (checkboxName.length > 2) {
				propertyArray3 = new Array();
				propertyIdArray3 = new Array();
				propertyValueArray3 = new Array();
				propertyValueIdArray3 = new Array();
				propertyIsImage3 = 0;
			}
		}
	}
	
	// 将选中的checkbox的值封装成数组
	for (var i = 0; i < checkboxName.length; i++) {
		var input = document.getElementsByName(checkboxName[i]);
		for (var index = 0; index < input.length; index++) {
			if (input[index].type == "checkbox" && input[index].checked) {
				checkedValue.push(input[index].value); // 原来的property value
				checkedName.push(input[index].name); // 原来的property
				
				if (i == 0) {
					propertyArray.push(input[index].getAttribute("data-property"));
					propertyIdArray.push(input[index].getAttribute("data-property-id"));
					propertyValueArray.push(input[index].getAttribute("data-property-value"));
					propertyValueIdArray.push(input[index].getAttribute("data-property-value-id"));
					propertyIsImage = input[index].getAttribute("data-is-image");
				} else if (i == 1) {
					propertyArray2.push(input[index].getAttribute("data-property"));
					propertyIdArray2.push(input[index].getAttribute("data-property-id"));
					propertyValueArray2.push(input[index].getAttribute("data-property-value"));
					propertyValueIdArray2.push(input[index].getAttribute("data-property-value-id"));
					propertyIsImage2 = input[index].getAttribute("data-is-image");
				} else if (i == 2) {
					propertyArray3.push(input[index].getAttribute("data-property"));
					propertyIdArray3.push(input[index].getAttribute("data-property-id"));
					propertyValueArray3.push(input[index].getAttribute("data-property-value"));
					propertyValueIdArray3.push(input[index].getAttribute("data-property-value-id"));
					propertyIsImage3 = input[index].getAttribute("data-is-image");
				}
			}
		}
	}
	
	if (propertyIsImage != null && propertyIsImage == 1) {
		for (var i = 0; i < propertyArray.length; i++) {
			var flag = checkImage(propertyValueIdArray[i]);
			if (!flag) {
				var imageHTML = createEditImageTD(propertyIdArray[i], propertyValueIdArray[i], propertyValueArray[i]);
				$("#skuImages").append(imageHTML); // 在图片table最后面添加一行
			}
		}
	} else if (propertyIsImage2 != null && propertyIsImage2 == 1) {
		for (var i = 0; i < propertyArray2.length; i++) {
			var flag = checkImage(propertyValueIdArray2[i]);
			if (!flag) {
				var imageHTML = createEditImageTD(propertyIdArray2[i], propertyValueIdArray2[i], propertyValueArray2[i]);
				$("#skuImages").append(imageHTML); // 在图片table最后面添加一行
			}
		}
	} else if (propertyIsImage3 != null && propertyIsImage3 == 1) {
		for (var i = 0; i < propertyArray3.length; i++) {
			var flag = checkImage(propertyValueIdArray3[i]);
			if (!flag) {
				var imageHTML = createEditImageTD(propertyIdArray3[i], propertyValueIdArray3[i], propertyValueArray3[i]);
				$("#skuImages").append(imageHTML); // 在图片table最后面添加一行
			}
		}
	}
	
	// checkdName中是所有选中的name，有重复，addCol中是要新增的列
	for (var v = 0; v < checkedName.length; v++) {
		if (addCol.indexOf(checkedName[v]) < 0
				&& $("#th_" + checkedName[v]).val() == null) {
			addCol.push(checkedName[v]);
		}
	}
	
	// 只有一个属性
	if (propertyArray.length > 0 && propertyArray2 == null && propertyArray3 == null) {
		for (var i = 0; i < propertyArray.length; i++) {
			flag = checkRow(propertyValueIdArray[i], null, null);
			if (!flag) {
				var trHTML = "<tr>";
				trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[i].propertyValueTos[0].propertyValueId+"' value='"+skuArray[i].propertyValueTos[0].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+skuArray[i].propertyValueTos[0].propertyValueId+"' /><input type='hidden' name='propertyId' value='"+skuArray[i].propertyValueTos[0].propertyId+"' /></td>";
				trHTML += createEditTD(skuArray[i].stock, skuArray[i].price, skuArray[i].priceMarket, skuArray[i].priceCost, skuArray[i].weight, skuArray[i].length, skuArray[i].wide, skuArray[i].height);
				trHTML += "<td style='width:10%;'><input type='hidden' id='row_"+propertyValueIdArray[i]+"' value='check' /><input type='hidden' name='skuId' value='"+skuArray[i].skuId+"' /></td>";
				trHTML += "</tr>";
				$("#createTable").append(trHTML);// 在table最后面添加一行
			}
		}
	} else if (propertyArray.length > 0 && propertyArray2.length > 0 && propertyArray3 == null) { // 有两个属性
		var status = 0;
		for (var i = 0; i < propertyArray.length; i++) {
    		for (var j = 0; j < propertyArray2.length; j++) {
    			flag = checkRow(propertyValueIdArray[i], propertyValueIdArray2[j], null);
    			if (!flag) {
    				for (var k = i; k <= i; k++) {
    					var trHTML = "<tr>";
    					if (i >= j) {
    						trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[0].propertyValueId+"' value='"+skuArray[status].propertyValueTos[0].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+skuArray[status].propertyValueTos[0].propertyValueId+"' /><input type='hidden' name='propertyId' value='"+skuArray[status].propertyValueTos[0].propertyId+"' /></td>";
    						trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[1].propertyValueId+"' value='"+skuArray[status].propertyValueTos[1].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+skuArray[status].propertyValueTos[1].propertyValueId+"' /><input type='hidden' name='propertyId2' value='"+skuArray[status].propertyValueTos[1].propertyId+"' /></td>";
    						trHTML += createEditTD(skuArray[status].stock, skuArray[status].price, skuArray[status].priceMarket, skuArray[status].priceCost, skuArray[status].weight, skuArray[status].length, skuArray[status].wide, skuArray[status].height);
    					} else {
    						trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[0].propertyValueId+"' value='"+skuArray[status].propertyValueTos[0].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+skuArray[status].propertyValueTos[0].propertyValueId+"' /><input type='hidden' name='propertyId' value='"+skuArray[status].propertyValueTos[0].propertyId+"' /></td>";
    						trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[1].propertyValueId+"' value='"+skuArray[status].propertyValueTos[1].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+skuArray[status].propertyValueTos[1].propertyValueId+"' /><input type='hidden' name='propertyId2' value='"+skuArray[status].propertyValueTos[1].propertyId+"' /></td>";
    						trHTML += createEditTD(skuArray[status].stock, skuArray[status].price, skuArray[status].priceMarket, skuArray[status].priceCost, skuArray[status].weight, skuArray[status].length, skuArray[status].wide, skuArray[status].height);
    					}
    					trHTML += "<td style='width:10%;'><input type='hidden' id='row_"+propertyValueIdArray[i]+"_"+propertyValueIdArray2[j]+"' value='check' /><input type='hidden' name='skuId' value='"+skuArray[status].skuId+"' /></td>";
    					trHTML += "</tr>";
    					$("#createTable").append(trHTML);// 在table最后面添加一行
    					status++;
    				}
    			}
    		}
    	}
	} else if (propertyArray.length > 0 && propertyArray2.length > 0 && propertyArray3.length > 0) { // 有三个属性
		var maxLength = Math.max.apply(Math,[propertyArray.length,propertyArray2.length,propertyArray3.length]);
		var status = 0;
		for (var i = 0; i < propertyArray.length; i++) {
    		for (var j = 0; j < propertyArray2.length; j++) {
    			for (var k = 0; k < propertyArray3.length; k++) {
    				flag = checkRow(propertyValueIdArray[i], propertyValueIdArray2[j], propertyValueIdArray3[k]);
        			if (!flag) {
        				var trHTML = "<tr>";
        				if (i >= j && i >= k) {
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[0].propertyValueId+"' value='"+skuArray[status].propertyValueTos[0].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+skuArray[status].propertyValueTos[0].propertyValueId+"' /><input type='hidden' name='propertyId' value='"+skuArray[status].propertyValueTos[0].propertyId+"' /></td>";
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[1].propertyValueId+"' value='"+skuArray[status].propertyValueTos[1].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+skuArray[status].propertyValueTos[1].propertyValueId+"' /><input type='hidden' name='propertyId2' value='"+skuArray[status].propertyValueTos[1].propertyId+"' /></td>";
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[2].propertyValueId+"' value='"+skuArray[status].propertyValueTos[2].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId3' value='"+skuArray[status].propertyValueTos[2].propertyValueId+"' /><input type='hidden' name='propertyId3' value='"+skuArray[status].propertyValueTos[2].propertyId+"' /></td>";
        					trHTML += createEditTD(skuArray[status].stock, skuArray[status].price, skuArray[status].priceMarket, skuArray[status].priceCost, skuArray[status].weight, skuArray[status].length, skuArray[status].wide, skuArray[status].height);
        				} else if (j >= k) {
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[0].propertyValueId+"' value='"+skuArray[status].propertyValueTos[0].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+skuArray[status].propertyValueTos[0].propertyValueId+"' /><input type='hidden' name='propertyId' value='"+skuArray[status].propertyValueTos[0].propertyId+"' /></td>";
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[1].propertyValueId+"' value='"+skuArray[status].propertyValueTos[1].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+skuArray[status].propertyValueTos[1].propertyValueId+"' /><input type='hidden' name='propertyId2' value='"+skuArray[status].propertyValueTos[1].propertyId+"' /></td>";
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[2].propertyValueId+"' value='"+skuArray[status].propertyValueTos[2].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId3' value='"+skuArray[status].propertyValueTos[2].propertyValueId+"' /><input type='hidden' name='propertyId3' value='"+skuArray[status].propertyValueTos[2].propertyId+"' /></td>";
        					trHTML += createEditTD(skuArray[status].stock, skuArray[status].price, skuArray[status].priceMarket, skuArray[status].priceCost, skuArray[status].weight, skuArray[status].length, skuArray[status].wide, skuArray[status].height);
        				} else {
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[0].propertyValueId+"' value='"+skuArray[status].propertyValueTos[0].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId' value='"+skuArray[status].propertyValueTos[0].propertyValueId+"' /><input type='hidden' name='propertyId' value='"+skuArray[status].propertyValueTos[0].propertyId+"' /></td>";
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[1].propertyValueId+"' value='"+skuArray[status].propertyValueTos[1].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId2' value='"+skuArray[status].propertyValueTos[1].propertyValueId+"' /><input type='hidden' name='propertyId2' value='"+skuArray[status].propertyValueTos[1].propertyId+"' /></td>";
        					trHTML += "<td style='width:10%;'><input type='text' style='width: 95%;text-align: center;' id='' name='check_"+skuArray[status].propertyValueTos[2].propertyValueId+"' value='"+skuArray[status].propertyValueTos[2].propertyValue+"' disabled='true' /><input type='hidden' name='propertyValueId3' value='"+skuArray[status].propertyValueTos[2].propertyValueId+"' /><input type='hidden' name='propertyId3' value='"+skuArray[status].propertyValueTos[2].propertyId+"' /></td>";
        					trHTML += createEditTD(skuArray[status].stock, skuArray[status].price, skuArray[status].priceMarket, skuArray[status].priceCost, skuArray[status].weight, skuArray[status].length, skuArray[status].wide, skuArray[status].height);
        				}
        				trHTML += "<td style='width:10%;'><input type='hidden' id='row_"+propertyValueIdArray[i]+"_"+propertyValueIdArray2[j]+"_"+propertyValueIdArray3[k]+"' value='check' /><input type='hidden' name='skuId' value='"+skuArray[status].skuId+"' /></td>";
        				trHTML += "</tr>";
        				$("#createTable").append(trHTML);// 在table最后面添加一行
        				status++;
        			}
    			}
    		}
    	}
	}
}

function submitEditForm() {
	var url = BASE_URL + "/back/goodsContent/edit";
	
	var saveCallBack = form_save_edited;
	$("#edit_form").attr("action", url);
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
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
    if (data.status === 0) {
        notice('edit_notice', img_done + ' 编辑成功!', true, 5000);
        history.back(-1);
    } else {
    	notice('edit_notice', img_delete + " " + data.msg, true, 5000);
    }
    $(".input-submit").removeAttr('disabled');
}

function createBaseTD () {
	var createTD = "";
	createTD += "<td style='width:10%;'><input type='text' name='stock' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='price' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='priceMarket' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='priceCost' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='weight' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='length' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='wide' style='width: 95%;text-align: center;' /></td>";
	createTD += "<td style='width:10%;'><input type='text' name='height' style='width: 95%;text-align: center;' /></td>";
	return createTD;
}

function createEditTD (stock, price, priceMarket, priceCost, weight, length, wide, height) {
	var createEditTD = "";
	createEditTD += "<td style='width:10%;'><input type='text' name='stock' style='width: 95%;text-align: center;' value='" + stock + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='price' style='width: 95%;text-align: center;' value='" + price + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='priceMarket' style='width: 95%;text-align: center;' value='" + priceMarket + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='priceCost' style='width: 95%;text-align: center;' value='" + priceCost + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='weight' style='width: 95%;text-align: center;' value='" + weight + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='length' style='width: 95%;text-align: center;' value='" + length + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='wide' style='width: 95%;text-align: center;' value='" + wide + "' /></td>";
	createEditTD += "<td style='width:10%;'><input type='text' name='height' style='width: 95%;text-align: center;' value='" + height + "' /></td>";
	return createEditTD;
}

function createBaseImageTD (propertyId, propertyValueId, propertyValue) {
	var imageHTML = "<tr>";
	imageHTML += "<td width='5%' height='120px;' align='center' style='border:1px solid #E8E8E8'>";
	imageHTML += "<p>"+propertyValue+"</p>";
	imageHTML += "</td>";
	imageHTML += "<td width='95%' height='120px;' style='border:1px solid #E8E8E8'>";
	imageHTML += "<div class='thumbnail uploadDiv' name='uploadImage'>";
	imageHTML += "<div class='uploadImg'>";
	imageHTML += "<input class='uploadInput upload btn_fileupload' type='file' multiple='' id='uploadImage_"+propertyValueId+"' name='files' onclick='uploadImage(this);' style='width:85px;height:85px;' multiple>";
	imageHTML += "</div>";
	imageHTML += "</div>";
	imageHTML += "</td>";
	imageHTML += "<td><input type='hidden' id='imgrow_"+propertyValueId+"' value='check' /></td>";
	imageHTML += "</tr>";
	return imageHTML;
}

function createEditImageTD (propertyId, propertyValueId, propertyValue) {
	var dataObj=eval(imageInfo);
	
	var imageHTML = "<tr>";
	imageHTML += "<td width='5%' height='120px;' align='center' style='border:1px solid #E8E8E8'>";
	imageHTML += "<p>"+propertyValue+"</p>";
	imageHTML += "</td>";
	imageHTML += "<td width='95%' height='120px;' style='border:1px solid #E8E8E8'>";
	
	for (var v = 0; v < dataObj.length; v++) {
		if (dataObj[v].propertyValueId == propertyValueId) {
			imageHTML += "<div class='thumbnail uploadDiv addImg'>";
			imageHTML += "<div id='del_"+dataObj[v].propertyValueId+"' onclick='deleteSkuImage("+dataObj[v].propertyValueId+");'>";
			imageHTML += "<a class='glyphicon glyphicon-remove-circle gallery-delete delImg' data-attachmentid='" + dataObj[v].attachmentid + "' href='javascript:;' ></a> <img width='90px' src='" + IMG_URL + dataObj[v].filepath + "'>";
			imageHTML += "<input id='' type='hidden' value='"+dataObj[v].propertyValueId+"' name='skuImagePropertyValueIds' />";
			imageHTML += "<input id='' type='hidden' value='"+dataObj[v].attachmentid+"' name='skuImageAttachmentids' />";
			imageHTML += "</div>";
			imageHTML += "<div class='defaultImg'>";
			imageHTML += "<a class='btn btn-success btn-xs advert-roll-set-time defaultImage' type='button' data-url='" + IMG_URL + dataObj[v].filepath + "' href='javascript:;'>封面图</a>";
			imageHTML += "</div>";
			imageHTML += "</div>";
		}
	}
	
	imageHTML += "<div class='thumbnail uploadDiv' name='uploadImage'>";
	imageHTML += "<div class='uploadImg'>";
	imageHTML += "<input class='uploadInput upload btn_fileupload' type='file' multiple='' id='uploadImage_"+propertyValueId+"' name='files' onclick='uploadImage(this);' style='width:85px;height:85px;' multiple>";
	imageHTML += "</div>";
	imageHTML += "</div>";
	
	imageHTML += "</td>";
	imageHTML += "<td><input type='hidden' id='imgrow_"+propertyValueId+"' value='check' /></td>";
	imageHTML += "</tr>";
	return imageHTML;
}
