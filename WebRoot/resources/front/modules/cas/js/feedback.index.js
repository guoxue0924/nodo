/**
 * Created by George on 15/12/23.
 */
$(function () {
    $("#add_time_from").datepicker({dateFormat: "yy-mm-dd"});
    $("#add_time_to").datepicker({dateFormat: "yy-mm-dd"});
    $('#pagination').twbsPagination({
        totalPages: 1,
        visiblePages: 5,
        initiateStartPageClick: false,
        startPage: 1,
        onPageClick: function (event, page) {
            $("#page").val(page);
            _rendRefundList();
        }
    });
    _rendRefundList();
    $("#searchBtn").click(function () {
        $("#page").val(1);
        _rendRefundList();
    });

    /* 删除反馈 */
    $("#feedbackList").on('click', '.deleteBtn', function () {
        var content_id = $(this).data('content_id');
        $.ajax('/cas/feedback/delete', {
            dataType: 'JSON',
            type:'POST',
            data: {'content_id': content_id},
            success: function (response) {
                if (response.status == 0) {
                    $('.deleteBtn[data-content_id="' + content_id + '"]').parents('tr.bd-line').remove();
                } else {
                    alert(response.error);
                }
            }
        });
    });
});

function initPagination(totalPages, page, initiateStartPageClick) {
    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        initiateStartPageClick: false,
        startPage: page,
        onPageClick: function (event, page) {
            $("#page").val(page);
            _rendRefundList();
        }
    });
}

function _rendRefundList() {
    $.getJSON('/cas/feedback/index', {
        page: $("#page").val()
    }, function (response) {
        var html = "";
        if (response.data.feedback.length < 1) {
            html += template("feedbackNoneTpl");
        } else {
            $.each(response.data.feedback, function (index, data) {
                html += template("feedbackTpl", data);
            });
        }
        $("#feedbackList tbody").html(html);
        $('#pagination').twbsPagination("destroy");
        initPagination(response.data.totalPage, response.data.page);
    });
}