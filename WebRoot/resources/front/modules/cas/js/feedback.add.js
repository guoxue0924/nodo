/**
 * Created by George on 15/12/23.
 */
$(function () {
    $('.submit').click(function () {
        var options = {
            dataType: 'json',
            timeout: 60000,
            success: saveCallBack
        };
        $("#mallconsultForm").ajaxSubmit(options);
    });
});

function saveCallBack(response) {
    if (response.status == 0) {
        history.back(-1);
    } else {
        alert(response.error)
    }
}