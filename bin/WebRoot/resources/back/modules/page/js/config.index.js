var CODEMIRROR_LOADED_HEADER = false;
var CODEMIRROR_LOADED_FOOTER = false;
var editor_header, editor_footer;

$(function() {
	/**
	 * 载入在线编辑资源
	 */
	initCM();
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function(){
		var submit_id = $(this).attr("id");
		switch (submit_id) {
			case 'submit_save' : form_submit(); break;
		}
	});
});


/**
 * 延迟重载 codemirror
 */
function initCM() {
    if (CODEMIRROR_LOADED_HEADER == false || CODEMIRROR_LOADED_FOOTER == false) {
    	editor_header = CodeMirror_Helper('content_code_header', 'editor_header');
    	editor_footer = CodeMirror_Helper('content_code_footer', 'editor_footer');
    } else {
        setTimeout('initCM()', 500);
    }
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('edit_notice', img_loading_small, false);
	
	$(".input-submit").attr('disabled', true);
    
    var id_arr = new Array();
	id_arr.push($('#configId_0').val());
	id_arr.push($('#configId_1').val());
	
	var value_arr = new Array();
	value_arr.push(editor_header.getValue());
	value_arr.push(editor_footer.getValue());
    
    /* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/pageConfig/save',
        data:{configIds : id_arr, configValues : value_arr},
        dataType:'json',
        timeout:60000,
        success:function(data){
        	if (data.status === 0) {
                notice('edit_notice', img_done + ' 保存成功!', true, 5000);
            } else {
            	notice('edit_notice', img_delete + " " + data.error, true, 5000);
            }
            $(".input-submit").removeAttr('disabled');
    		return false;
    	}
    });
    return false;
}