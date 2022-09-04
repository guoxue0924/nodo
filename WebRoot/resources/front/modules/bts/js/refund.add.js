$(function(){
	
    $('.submit').click(function(){
        if(form_validate()){
            form_submit();
        }
    });
    
    //图片上传
	$(".input-file").fileupload({
	    dataType: 'json',
	    url: BASE_URL + "/front/bts/refund/upload",
	    add: function(e, data) {
	            data.submit();
	        },
	    done: function (e, data) {
	        if(data.result.status == 0) {
	            $item = $(this).parents('li');
	            $item.find('img').attr('src', IMG_URL + data.result.url);
	            $item.find('[nctype="input_image"]').val(data.result.attachmentId);
	            $item.find('[nctype="input_image"]').attr("checked",'true');
	            $item.find('[nctype="del"]').attr('data-file-id', data.result.attachmentId);
	            $item.find('[nctype="image_item"]').show();
	        } else {
	            alert('上传失败!');
	        }
	    }
	});

        $('[nctype="del"]').on('click', function() {
            $item = $(this).parent();
            $item.find('[nctype="input_image"]').val('');
            $item.find('[nctype="input_image"]').removeAttr("checked");
            $item.hide();
        });

});

function form_submit() {
    var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
        type:'post'
    };
    $("#refundForm").ajaxSubmit(options);
    return false;
}

function form_save_added(data){
    if (data.status == 0){
        location.href = BASE_URL + '/front/bts/refund';
    } else {
        alert(data.error);
    }
}

function form_validate(){
    var label = '<label for="reason_id" class="error"><i class="icon-exclamation-sign"></i>%s</label>';
    if ($('select[name=reasonType]').val() <= 0){
        $('select[name=reasonType]').parent('dd').find('.error').html(sprintf(label, '请选择退货原因'));
        return false;
    }

    if ($('input[name=goodsNum]').val() > $('input[name=goods_num]').attr('data-max')){
        $('input[name=goodsNum]').parent('dd').find('.error').html(sprintf(label, '退货数量超过最大数量'));
        return false;
    }

    if ($('textarea[name=reason]').val() == ''){
        $('textarea[name=reason]').parent('dd').find('.error').html(sprintf(label, '请填写退货原因'));
        return false;
    }

    return true;
}

function sprintf(string, replace){
    return string.replace('%s', replace);
}