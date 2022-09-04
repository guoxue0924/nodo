$(function() {
	
	$('input[type=text]').on('blur', function(){
		to_save($(this).attr('name'), $(this).val());
	});
	
	$('input[type=radio]').on('click', function(){
		to_save($(this).attr('name'), $(this).val());
	});
	
});

function to_save(name, value) {
	if(value == '') { 
		return false;
	}
	$.ajax({
    	type: 'post',
        url: BASE_URL+'/back/systemParam/save',
        data: {paramName:name, paramValue:value},
        dataType: 'json',
        timeout: 60000,
        success: function(data){
    		if (data.status == 0) {
    			notice('edit_notice', img_done + ' 设置成功', true, 5000);
    		} else {
    			notice('edit_notice', img_delete + ' 设置失败，请刷新后重试', true, 5000);
    		}
    		return false;
    	}
    });
}