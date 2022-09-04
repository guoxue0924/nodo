<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
    <c:if test="${consignee != null}">
    <c:forEach items="${consignee}" var="c">
    <li class="receive_add address_item ncc-selected-item ">
        <input name="addr" nc_type="addr" type="radio" class="radio" city_id="${c.regionName}" true_name="${c.name}" address="${c.address}" phone="${c.mobile}" value="${c.consigneeId}" <c:if test="${c.isDefault == 1}">checked</c:if>/>
        <label for="">
            <span class="true-name">${c.name}</span>
            <span class="address">${c.regionName}${c.address}</span>
            <span class="phone"><i class="icon-mobile-phone"></i>${c.mobile}</span>
        </label>
    </li>
    </c:forEach>
    </c:if>
    <li class="receive_add addr_item">
        <input value="0" nc_type="addr" id="add_addr" type="radio" name="addr">
        <label for="add_addr">使用新地址</label>
    </li>
    <div id="add_addr_box"><!-- 存放新增地址表单 --></div>
</ul>
<div class="hr16">
    <a id="hide_addr_list" class="ncc-btn ncc-btn-red" href="javascript:void(0);">保存收货人信息</a>
</div>
<script type="text/javascript">
    $(function(){
        function addAddr() {
            $('#add_addr_box').load(BASE_URL + '/front/cas/consignee/addAddress');
        }
        $('input[nc_type="addr"]').on('click',function(){
            if ($(this).val() == '0') {
                $('.address_item').removeClass('ncc-selected-item');
                $('#add_addr_box').load(BASE_URL + '/front/cas/consignee/addAddress');
            } else {
                $('.address_item').removeClass('ncc-selected-item');
                $(this).parent().addClass('ncc-selected-item');
                $('#add_addr_box').html('');
            }
        });
        $('#hide_addr_list').on('click',function(){
            if ($('input[nc_type="addr"]:checked').val() == '0'){
                submitAddAddr();
            } else {
                if ($('input[nc_type="addr"]:checked').size() == 0) {
                    return false;
                }
                var city_id = $('input[name="addr"]:checked').attr('city_id');
                var area_id = $('input[name="addr"]:checked').attr('area_id');
                var addr_id = $('input[name="addr"]:checked').val();
                var true_name = $('input[name="addr"]:checked').attr('true_name');
                var address = $('input[name="addr"]:checked').attr('address');
                var phone = $('input[name="addr"]:checked').attr('phone');
                hideAddrList(addr_id,true_name,address,phone);
                $('#consigneeId').val(addr_id);
            }
        });
        if ($('input[nc_type="addr"]').size() == 1){
            $('#add_addr').attr('checked',true);
            addAddr();
        }
    });
</script>
