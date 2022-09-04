<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
    <c:if test="${consignee != null}">
    <c:forEach items="${consignee}" var="c">
    <li class="receive_add address_item ncc-selected-item ">
    <!-- modify by guoxue 2016-06-23 begin  -->
        <input name="addr" nc_type="addr" type="radio" class="radio" region_id="${c.regionId}" city_id="${c.regionName}" true_name="${c.name}" address="${c.address}" phone="${c.mobile}" value="${c.consigneeId}" <c:if test="${c.isDefault == 1}">checked</c:if>/>
    <!-- modify by guoxue 2016-06-23 end  --> 
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
<!-- add by guoxue 2016-06-24 begin  -->
 <input value="${price }" type="hidden" name="price" id="price">
 <!-- add by guoxue 2016-06-24 end  -->
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
        /*modify by guoxue 2016-06-23 begin  */
        $('#hide_addr_list').on('click',function(){
            if ($('input[nc_type="addr"]:checked').val() == '0'){
            	/*add by guoxue 2016-07-04 begin  */
            	 $name = $('#name');
            	 $province = $('#province');
            	 $city = $('#city');
            	 $regionId = $('#regionId');
            	 $address = $('#address');
            	 $mobile = $('#mobile');
            		 if (! $name.val()){
            		        showError($name, '收货人不能为空');
            		        return false
            		    }
            		 if ( $province.val() == -1){
         		        showError2($province, '所在省不能为空');
         		        return false
         		    }else if($city.val() == -1){
         		    	showError2($city, '所在市不能为空');
         		        return false
         		    }else if($regionId.val() == -1){
         		    	showError2($regionId, '所在地区不能为空');
         		        return false
         		    }
            		 
            		 if (! $address.val()){
         		        showError($address, '详细地址不能为空');
         		        return false
         		    }
            		 if (! $mobile.val()){
         		        showError($mobile, '手机号码不能为空');
         		        return false
         		    }
            	 /*add by guoxue 2016-07-04 end  */
            	var price = $('#price').val();
                submitAddAddr(price);
            } else {
                if ($('input[nc_type="addr"]:checked').size() == 0) {
                    return false;
                }
                var city_id = $('input[name="addr"]:checked').attr('city_id');
                var area_id = $('input[name="addr"]:checked').attr('area_id');
                var addr_id = $('input[name="addr"]:checked').val();
                var region_id = $('input[name="addr"]:checked').attr('region_id');
                var true_name = $('input[name="addr"]:checked').attr('true_name');
                var address = $('input[name="addr"]:checked').attr('address');
                var phone = $('input[name="addr"]:checked').attr('phone');
                var price = $('#price').val();
                hideAddrList(addr_id,true_name,address,phone,region_id,price,city_id);
                
                $('#consigneeId').val(addr_id);
            }
        });
        /*modify by guoxue 2016-06-23 end  */
        if ($('input[nc_type="addr"]').size() == 1){
            $('#add_addr').attr('checked',true);
            addAddr();
        }
        
        /*add by guoxue 2016-07-04 begin*/
        function showError(el, error){
            var error_td = el.parent('dd');
            var lable = error_td.find('label');
            if (lable.html() == ''){
                lable.addClass('error').append(error);
            } else {
                lable.html(error);
            }
        }
        function showError2(el, error){
            var error_td = el.parent().parent('dd');
            var lable = error_td.find('label');
            if (lable.html() == ''){
                lable.addClass('error').append(error);
            } else {
                lable.html(error);
            }
        }
        /*add by guoxue 2016-07-04 end  */
    });
</script>
