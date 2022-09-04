$(function(){
    $('.raty').raty({
        path: STATIC_URL + "/plugins/jquery-raty/img",
        readOnly: true,
        score: function() {
            return $(this).attr('data-score');
        }
    });

    //图片上传
    $(".input-file").fileupload({
        dataType: 'json',
        url: BASE_URL + "/front/cas/comment/upload",
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

        $('#btn_submit').on('click', function() {
            form_submit();
        });
})

function form_submit(){
    var saveCallBack;
    saveCallBack = form_save_edited;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
        type:'post'
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

function form_save_edited(data){
    if (data.status == 0){
        location.href = BASE_URL +'/front/cas/comment';
    } else {
        alert(data.msg);
    }
}