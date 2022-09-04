<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ncc-form-default">
    <form method="POST" id="addr_form" action="index.php">
        <input type="hidden" value="1" name="is_default">
        <dl>
            <dt><i class="required">*</i>收货人姓名：</dt>
            <dd>
                <input type="text" class="text w100" name="name" maxlength="20" id="name" value=""/>
                <p class="hint"></p>
                <label></label>
            </dd>
        </dl>
        <dl>
            <dt><i class="required">*</i>所在地区：</dt>
            <dd>
                <div id="region">
                	
                    <select id="province" name="province" class="w110">
                    <option value="-1">请选择</option>
                    <c:forEach items="${region}" var="r">
                        <option value="${r.regionId}">${r.regionName}</option>
                    </c:forEach>
                    </select>
                    
                    
                </div>
                <p class="hint"></p>
                <label></label>
                
            </dd>
        </dl>
        <dl>
            <dt><i class="required">*</i>详细地址：</dt>
            <dd>
                <input type="text" class="text w500" name="address" id="address" maxlength="80" value=""/>
                <p class="hint"></p>
                <label></label>
            </dd>
        </dl>
        <dl>
            <dt> <i class="required">*</i>手机号码：</dt>
            <dd>
                <input type="text" class="text w200" name="mobile" id="mobile" maxlength="15" value=""/>
                &nbsp;&nbsp;(或)&nbsp;固定电话：
                <input type="text" class="text w200" id="tel" name="tel" maxlength="20" value=""/>
                <p class="hint"></p>
                <label></label>
            </dd>
        </dl>
        <input name="regionName" id="regionName" type="hidden"/>
    </form>
</div>
<script type="text/javascript">
$(document).ready(function(){
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
        /*add by guoxue 2016-07-04 begin  */
        $('#city').blur(function(){
            if ($(this).val() != -1){
                hideError2($(this));
            }
        });
    });
    $('body').on('change', '#regionId', function(){
        $('#regionId').blur(function(){
            if ($(this).val() != -1){
                hideError2($(this));
            }
        });
    });
    $('#name').blur(function(){
        if ($(this).val() != ''){
            hideError($(this));
        }
    });
    $('#province').blur(function(){
        if ($(this).val() != -1){
            hideError2($(this));
        }
    });
    $('#address').blur(function(){
        if ($(this).val() != ''){
            hideError($(this));
        }
    });
    $('#mobile').blur(function(){
        if ($(this).val() != ''){
            hideError($(this));
        }
    });
});

function hideError(el){
    var error_td = el.parent('dd');
    error_td.find('label').html('');
}
function hideError2(el){
    var error_td = el.parent().parent('dd');
    error_td.find('label').html('');
}
/*add by guoxue 2016-07-04 end  */
function checkPhone(){
    return ($('input[name="mob_phone"]').val() == '' && $('input[name="tel_phone"]').val() == '');
}
/*modify by guoxue 2016-06-24 begin  */
function submitAddAddr(price){
    var datas=$('#addr_form').serialize();
    saveRegion();
    $.post(BASE_URL + '/front/cas/consignee/add',datas,function(data){
        if (data.status == 0){
            var true_name = $.trim($("#name").val());
            var tel_phone = $.trim($("#tel").val());
            var mob_phone = $.trim($("#mobile").val());
            var area_info = $.trim($("#regionName").val());
            var address = $.trim($("#address").val());
            var regionId = data.data.regionId;
            var regionName = data.data.regionName;
            hideAddrList(data.addr_id,true_name,address,(mob_phone != '' ? mob_phone : tel_phone),regionId,price,regionName);
        }else{
            alert(data.error);
        }
    },'json');
}
/*modify by guoxue 2016-06-24 end  */
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
                if (! $('#' + select + '').val()){
                    $('#' + select + '').remove();
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

function saveRegion()
{
    if ($('select').length > 0){
        var regionName = $('#province').find('option:selected').text() + ' ' +  $('#city').find('option:selected').text() + ' ' +  $('#regionId').find('option:selected').text();
        $('#regionName').val(regionName);
    }
}
</script>
