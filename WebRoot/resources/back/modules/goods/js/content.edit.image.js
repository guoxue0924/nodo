$(document).ready(function() {
	/**
	 * 上传附件 使用延时加载的方式来解决js文件加载混乱而导致无法执行上传动作的问题
	 */
	setTimeout('uploadFiles(this)', 1500);

	/**
	 * 附件中图片的 pretty 处理
	 */
	editPretty();

	/**
	 * 删除附件 - 单个
	 */
	$('#edit_fileupload').delegate('.delete-single', 'click', function() {
		var obj_tr = $(this).parent().parent();
//		var attachment_ids_old = $('input[name=attachment_ids]:last').val();
//		var attachment_id_del = 'attachmentid_' + obj_tr.attr('attachmentid');
//		var attachment_ids_new = attachment_ids_old.replace(attachment_id_del + ',', '');
//		attachment_ids_new = attachment_ids_new.replace(',' + attachment_id_del, '');
//		attachment_ids_new = attachment_ids_new.replace(attachment_id_del, '');
//		$('input[name=attachment_ids]:last').val(attachment_ids_new);
		obj_tr.remove();
	});

	/**
	 * 删除附件 - 批量
	 */
	$('#btn_delete_multiple').click(function() {
//		var attachment_ids = $('input[name=attachment_ids]:last').val();
		$('#table_striped>tbody').find('.select-single').each(function() {
			if ($(this).is(':checked')) {
//				var attachment_id_del = 'attachmentid_' + $(this).val();
//				attachment_ids = attachment_ids.replace(attachment_id_del + ',', '');
//				attachment_ids = attachment_ids.replace(',' + attachment_id_del, '');
//				attachment_ids = attachment_ids.replace(attachment_id_del, '');
				$(this).parent().parent().remove();
				$('#table_striped thead [type="checkbox"]').prop('checked', false);
			}
		});
//		$('input[name=attachment_ids]:last').val(attachment_ids);
	});

	/**
	 * 设置默认图片
	 */
	$('#table_striped').delegate('.image-default', 'change', function() {
		setDefaultImage($(this));
	});
	
});

/**
 * 删除sku图片
 */
function deleteSkuImage(propertyValueId) {
	var v = "del_" + propertyValueId;
	$("#"+v).parent().remove();
}

/**
 * 上传附件
 */
function uploadFiles(obj) {
	(function() {
		$('#btn_fileupload').fileupload({
			dataType : 'json',
			url : BASE_URL + '/back/goodsContent/uploadFile',
			submit : function(e, data) {
				/* 上传按钮样式处理 */
				$('.fileinput-button').attr('disabled', true);
				$('.fileinput-button').find('.text').hide();
				$('.fileinput-button').find('.text-active').removeClass('text-active');

				/* 等待状态处理 */
				$('#spin').addClass('show inline');
			},
			done : function(e, data) {
				/* 等待状态处理 */
				$("#spin").removeClass('show inline');

				/* 上传按钮样式处理 */
				$('.fileinput-button').removeAttr('disabled');
				$('.fileinput-button').find('.text').show();
				$('.fileinput-button').find('span').eq(1).addClass('text-active');

				/* 返回数据显示处理 */
				if (data.result.status == 0) {
					showFile(data.result.data);
				}

				/* 附件中图片的 pretty 处理 */
				editPretty();
			}
		});
	})();
}

function uploadImage(obj) {
	var uploadImageId = obj.id;
	var uploadImageName = obj.name;
	$(".btn_fileupload").fileupload({
		dataType : 'json',
		formData:{
			inputId : uploadImageId,
			inputName : uploadImageName
		},
		url : BASE_URL + '/back/goodsContent/uploadSkuImage',
		submit : function(e, data) {
			
		},
		done : function(e, data) {
			/* 返回数据显示处理 */
			if (data.result.status == 0) {
				showImage(data.result.data);
			}
			/* 附件中图片的 pretty 处理 */
			editPretty();
		}
	});
}

/**
 * 将上传的附件以列表形式显示出来
 */
function showFile(d) {
	/* 若已存在，则不再堆叠显示 */
	var attachment_ids_old = $('input[name=attachmentIds]:last').val();
	var attachment_id_new = d.attachmentid;

	if (attachment_ids_old != null) {
		if (attachment_ids_old.indexOf(attachment_id_new) != -1) {
			return false;
		}
	}
	/* 列表显示处理 */
	var tr_str = '<tr attachmentid="' + d.attachmentid + '">' + '<td class="v-middle"><input type="checkbox" class="select-single" value="' + d.attachmentid + '" /></td>'
			+ '<td class="v-middle">';
	tr_str += '<input type="hidden" name="attachmentid" value="'+d.attachmentid+'" />';

	if (d.mediatype == 'image') {
		tr_str += '<a href="' + IMG_URL + d.filepath + '" rel="prettyPhoto[gallery]" title="' + d.title + '">' + '<img style="height: 80px; width: 60;" src="' + IMG_URL
				+ d.filepath + '" border="0" />' + '</a>';
	} else {
		tr_str += '<img src="' + IMG_URL + d.filepath + '" border="0" />';
	}

	tr_str += '</td>' + '<td class="v-middle">' + d.title + '</td>' + '<td class="v-middle">' + d.filesize + ' Bytes</td>' + '<td class="v-middle">'
			+ '<label class="switch-sm switch-image-default">' + '<input type="checkbox" class="image-default" />' + '<span></span></label>' + '</td>' + '<td class="v-middle">'
			+ '<button type="button" class="btn btn-danger btn-sm delete-single">' + '<i class="fa fa-trash-o"></i>' + '<span> 删除</span>' + '</button>' + '</td>' + '</tr>';
	$("#table_striped>tbody").append(tr_str);

	/* 上传附件 ID 处理 */
	if (!attachment_ids_old) {
		$('input[name=attachmentIds]:last').val(attachment_id_new);
	} else if (attachment_ids_old.indexOf(attachment_id_new) < 0) {
		$('input[name=attachmentIds]:last').val(attachment_ids_old + ',' + attachment_id_new);
	}
}

function showImage(d) {
	/* 若已存在，则不再堆叠显示 */
//	var attachment_ids_old = $('input[name=attachmentIds]:last').val();
//	var attachment_id_new = d.attachmentid;
//
//	if (attachment_ids_old != null) {
//		if (attachment_ids_old.indexOf(attachment_id_new) != -1) {
//			return false;
//		}
//	}
	/* 列表显示处理 */
	var image_str = "<div class='thumbnail uploadDiv addImg'>";
	image_str += "<div onclick='deleteSkuImage(this);'>";
	image_str += "<a class='glyphicon glyphicon-remove-circle gallery-delete delImg' data-attachmentid='" + d.attachmentid + "' href='javascript:;' ></a> <img width='90px' src='" + IMG_URL + d.filepath + "'>";
	image_str += "<input id='' type='hidden' value='"+d.propertyValueId+"' name='skuImagePropertyValueIds' />";
	image_str += "<input id='' type='hidden' value='"+d.attachmentid+"' name='skuImageAttachmentids' />";
	image_str += "</div>";
	image_str += "<div class='defaultImg'>";
	image_str += "<a class='btn btn-success btn-xs advert-roll-set-time defaultImage' type='button' data-url='" + IMG_URL + d.filepath + "' href='javascript:;'>封面图</a>";
	image_str += "</div>";
	image_str += "</div>";
	
	var inputId = d.inputId;
	var origTD = $("#"+inputId).parents('td').html();
	$("#"+inputId).parents('td').html(image_str + origTD);
//	alert($("#"+inputId).parents('td').html());
//	$("#"+inputId).parents('div[name="uploadImage"]').prepend(image_str);
//	var checkboxArray  =  $("#createTable").find('input[name="check_'+obj.getAttribute("data-property-value-id")+'"]');
//	var removeImageArray = $("#uploadImage_"+obj.getAttribute("data-property-value-id"));
//	$(data).parents('tr').remove();
}

/**
 * 附件中的图片 pretty 处理
 */
function editPretty() {
	$(".gallery-edit:first a[rel^='prettyPhoto']").prettyPhoto({
		animation_speed : 'normal',
		theme : 'light_square',
		slideshow : 3000,
		autoplay_slideshow : false
	});
}

/**
 * 设置默认图片
 */
function setDefaultImage(obj) {
	$('.image-default').prop('checked', false);
	obj.prop('checked', true);

	var obj_tr = obj.parent().parent().parent();
	var image_default_href = obj_tr.find('td').eq(1).find('a:first').attr('href');
	$("input[name=imageDefault]:last").val(image_default_href);
}
