$(function(){
    $('#pagination').twbsPagination({
        totalPages: 1,
        visiblePages: 5,
        initiateStartPageClick:false,
        startPage: 1,
        onPageClick: function (event, page) {
            $("#page").val(page);
            _rendRefundList();
        }
    });
    _rendRefundList();

});

function initPagination(totalPages,page,initiateStartPageClick) {
    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        initiateStartPageClick:false,
        startPage: page,
        onPageClick: function (event, page) {
            $("#page").val(page);
            _rendRefundList();
        }
    });
}

function _rendRefundList() {
    $.getJSON('/cas/message/index', {
        page: $("#page").val(),
        type: $('type').val()
    }, function (response) {
        var html = "";
        if (response.data.message.length < 1) {
            html += template("noData");
        } else {
            $.each(response.data.message, function (index, data) {
                html += template("messageTpl", data);
            });
        }
        var nav = template('navTpl', response.data.nav);
        $('#navTab').html(nav);
        $("#messageList tbody").html(html);
        $('#pagination').twbsPagination("destroy");
        initPagination(response.data.totalPage,response.data.page);
    });
}
