/**
 * Created by George on 15/12/25.
 */
$(function () {
    $("#add_time_from").datepicker({dateFormat: "yy-mm-dd"});
    $("#add_time_to").datepicker({dateFormat: "yy-mm-dd"});
    $('#pagination').twbsPagination({
        totalPages: 1,
        visiblePages: 5,
        initiateStartPageClick: false,
        startPage: 1
    });

    _rendIntegralList();
    $("#searchBtn").click(function () {
        $("#page").val(1);
        _rendIntegralList();
    });

    ////猜你喜欢
    //$('#guesslike_div').load('http://10.58.137.126/shop/index.php?act=search&op=get_guesslike', function () {
    //    $(this).show();
    //});
    //$('.checkall').change(function(){
    //    alert(1);
    //});
});

function initPagination(totalPages, page, initiateStartPageClick) {
    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        initiateStartPageClick: false,
        startPage: page,
        onPageClick: function (event, page) {
            $("#page").val(page);
            _rendIntegralList();
        }
    });
}

function _rendIntegralList() {
    $.getJSON('/cas/asset/integral', {
        page: $("#page").val(),
        add_time_from: $("#add_time_from").val(),
        add_time_to: $("#add_time_to").val(),
        source: $("#source").val(),
        remark: $("#remark").val()
    }, function (response) {
        var html = "";
        if (response.data.integral_log.length < 1) {
            html += template("noData");
            $("#integralList").find('tbody').html(html);
        } else {
            $.each(response.data.integral_log, function (index, data) {
                html += template("integralTpl", data);
            });
            $("#integralList").find('tbody').html(html);
        }

        $('#pagination').twbsPagination("destroy");

        initPagination(response.data.totalPage, response.data.page);
    });
}