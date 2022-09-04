$(function(){
	load_cart_information();
	
    $('#birthday').datepicker({format:'yy=mm-dd'});

    $('body').on('change', '#province', function(){
        input = new Array();
        if (this.value == -1){
            input = ['city', 'regionId'];
            resetSelect(input)
        } else {
            input = ['regionId'];
            resetSelect(input)
            showArea(this.value, 'city');
        }
    });

    $('body').on('change', '#city', function(){
        input = new Array();
        if (this.value == -1){
            input = ['regionId'];
            resetSelect(input)
        } else {
            showArea(this.value, 'regionId');
        }
    });

    $('.submit').click(function(){
        saveRegion();
        form_submit();
    });

    $('#edit_area').click(function(){
        $('#region').html('');
        showArea(3743, 'province');
    });
});

function saveRegion()
{
    if ($('select').length > 0){
        var regionName = $('#province').find('option:selected').text() + ' ' +  $('#city').find('option:selected').text() + ' ' +  $('#regionId').find('option:selected').text();
        $('#regionName').val(regionName);
       
    }
}

function showArea(parent_id, select){
    var data = '';
    if (parent_id){
        data = {parentId:parent_id};
    }

    var region = '';
    $.ajax({
        url: BASE_URL + '/front/cas/getArea',
        data: data,
        dataType: 'json',
        type: 'post',
        success:function(response){
            if (response.status == 0){
                var option = '<option value="-1">请选择</option>'
                $.each(response.data, function(i,v){
                    option += '<option value="' + v.regionId +'">' + v.regionName + '</option>';
                });
                var html = '';
                if ($('#' + select + '').length < 1){
                    html += '<select name="' + select +'" id="' + select + '">' + option + '</select>';
                    $('#region').append(html);
                } else {
                    $('#' + select + '').html(option).show();

                }
            }
        }
    })
}

function resetSelect(input){
    $.each(input, function(i, v){
        $('#' + v +'').hide();
    })
}

/**
 * 表单提交处理
 */
function form_submit() {
    var saveCallBack;
    saveCallBack = form_save_added;
    var options = {
        dataType:'json',
        timeout:60000,
        success:saveCallBack,
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

function form_save_added(data){
    if (data.status == 0){
        location.reload(true);
    } else {
        alert(data.error);
    }
}
