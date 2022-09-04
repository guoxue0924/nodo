$(function(){
    $('#btn_submit').on('click', function() {
        form_submit();
    });
});

/**
 * 表单提交处理
 */
function form_submit() {
	var flag = true;
	$('#evalform .raty').each(function(){
		if(!$(this).find('input').val()) {
			alert('请对商品评分');
			flag = false;
			return false;
		}
		if(!$(this).parent().parent().next().find('textarea').val()) {
			alert('请填写评价');
			flag = false;
			return false;
		}
	});
	if(!flag) {
		return false;
	}
	
	if(!$('#evalform input[name="rankLogistics"]').val()) {
		alert('请对物流满意度评分');
		return false;
	}
	if(!$('#evalform input[name="rankSpeed"]').val()) {
		alert('请对发货速度满意度评分');
		return false;
	}
	
    var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
        type:'post'
    };
    $("#evalform").ajaxSubmit(options);
    return false;
}


function form_save_added(data){
    if (data.status == 0){
        location.href = BASE_URL+"/front/cas/comment";
    } else {
        alert(data.msg);
    }
}