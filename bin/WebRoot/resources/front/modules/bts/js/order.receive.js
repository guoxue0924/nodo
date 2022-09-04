$(function(){
    $('.submit').click(function(){
        form_submit();
    })
})
/**
 * 表单提交处理
 */
function form_submit() {
    var saveCallBack;
    var orderId = $('#orderId').val();
    saveCallBack = form_save;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
        type:'post'
    };
    $("#order_confirm_form").ajaxSubmit(options);
    return false;
}

function form_save(data){
    if (data.status == 0){
        location.href= BASE_URL+'/front/bts/order';
    } else {
        alert(data.msg);
    }
}