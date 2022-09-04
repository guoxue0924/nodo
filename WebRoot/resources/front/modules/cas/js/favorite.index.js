/**
 * Created by George on 15/12/25.
 */
$(function () {

    $('#pagination').twbsPagination({
        totalPages: 1,
        visiblePages: 5,
        initiateStartPageClick: false,
        startPage: 1,
        onPageClick: function (event, pageIndex,pageSize) {
            $("#pageIndex").val(pageIndex);
            $("#pageSize").val(pageSize);
            _rendRefundList();
        }
    });

    _rendFavoriteList();
    $("#favoriteList").on('mouseover', '.favorite-pic-list', function () {
        $(this).find('div.favorite-goods-info').animate({
                "top": "-40px"
            },
            400, "swing");
    });
    $("#favoriteList").on('mouseleave', '.favorite-pic-list', function () {
        $(this).find('div.favorite-goods-info').stop(true, false).animate({
                "top": "0"
            },
            400, "swing");
    });

    $("#favoriteList").on('click', '.deleteBtn', function () {
        $.ajax(BASE_URL + '/front/cas/deleteFavorite', {
            type: 'POST',
            dataType: 'JSON',
            data: {favoriteId: $(this).attr('data-favoriteId')}
            , success: function (response) {
                if (response.status == 0) {
                    $('.deleteBtn[data-favoriteId="' + response.data + '"]').parents('li').remove();
                } else {
                    alert(response.msg);
                }
            }
        });
    });

    $("body").on('click', '.deleteMultipleBtn', function () {
        var checkArr = new Array();
        if($('.checkitem:checked').length<1){
            alert('请选择要删除的商品');
            return false;
        }
        $.each($('.checkitem:checked'), function (index, data) {
            checkArr.push($(data).val());
        });
        var favoriteIds = checkArr.join(',');
        $.ajax(BASE_URL + '/front/cas/deleteMultipleFavorite', {
            type: 'POST',
            dataType: 'JSON',
            data: {favoriteIds: favoriteIds}
            , success: function (response) {
                if (response.status == 0) {
                	//modify by guoxue 2016-07-05 begin
//                    $(response.data.favoriteIds).each(function (index,data) {
//                        $('.deleteBtn[data-favoriteId="' + data + '"]').parents('li').remove();
                    	
                        _rendFavoriteList();
                        $("[name = checkall]:checkbox").attr("checked", false);
//                    });
                      //modify by guoxue 2016-07-05 end
                } else {
                    alert(response.msg);
                }
            }
        });
    });

    ////猜你喜欢
    //$('#guesslike_div').load('http://10.58.137.126/shop/index.php?act=search&op=get_guesslike', function () {
    //    $(this).show();
    //});
    //$('.checkall').change(function(){
    //    alert(1);
    //});
});

function initPagination(totalPages, pageIndex,pageSize, initiateStartPageClick) {
    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        initiateStartPageClick: false,
        startPage: pageIndex,
        onPageClick: function (event, pageIndex,pageSize) {
        	$("#pageIndex").val(pageIndex);
            $("#pageSize").val(pageSize);
            _rendFavoriteList();
        }
    });
}

function _rendFavoriteList() {
	 $.post(BASE_URL + '/front/cas/favorite', {
		 pageSize: $("#pageSize").val(),
	     pageIndex:$("#pageIndex").val()
    }, function (response) {
        var html = "";
        if (response.data.favorite.length < 1) {
            html += template("noData");
            $("#favoriteList").parents('tbody').html(html);
        } else {
            $(response.data.favorite).each(function (index, data) {
                html += template("favoriteTpl", data);
            });
            $("#favoriteList").html(html);
        }

        $('#pagination').twbsPagination("destroy");

        initPagination(response.count,response.data);
    },"json");
}