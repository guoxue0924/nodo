<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<script src="${STATIC_URL}/front/modules/front/js/page.js" type="text/javascript"></script>
<script src="${STATIC_URL}/front/modules/front/js/cas.js"></script>
<div style="clear:both"></div>


<div class="block box">
    <div class="blank"></div>
    <div id="ur_here">
        当前位置: <a href=".">首页</a> <code>&gt;</code> 用户中心
    </div>
</div>
<div class="blank"></div>
<div class="block clearfix">
<jsp:include page="user.nav.jsp"/>
    <div class="AreaR">
        <div class="box">
            <div class="box_1">
                <div style="_height:1%;" class="userCenterBox boxCenterList clearfix">
                    <h5><span>收货人信息</span></h5>
                    <div class="blank"></div>
                    <c:forEach items="${address}" var="a">
                    <form class="editAddressForm" name="theForm" method="post" action="/cas/address/updateAddress">
                        <input type="hidden" name="consignee_id" value="${a.consignee_id}"/>
                        <table width="100%" cellspacing="1" cellpadding="5" border="0" bgcolor="#dddddd">
                            <tbody><tr>
                                <td bgcolor="#ffffff" align="right">配送区域：</td>
                                <td bgcolor="#ffffff" align="left" colspan="3">
                                    <c:forEach items="${a.region}">
                                   <%--  {foreach from=$ item=ar key=key}
                                    <select value="${a.hid.$key}" style="border:1px solid #ccc;" id="selCountries_0" name="region[]" class="regionSelect">
                                        <option value="0">请选择</option>
                                        {foreach from=$ar item=arar key=arkey}
                                        <option {if $arar.region_id == $a.hid.$key}selected="selected"{/if} value="${arar.region_id}">${arar.region_name}</option>
                                        {/foreach}
                                    </select> --%>
                                   </c:forEach>
                                    &nbsp;&nbsp;
                                <span style="color:red;"><c:if test="${a.is_default eq 1}">默认地址</c:if></span>
                                </td>   
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">收货人姓名：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="${a.name}" id="consignee_0" class="inputBg" name="name">
                                    (必填) </td>
                                <td bgcolor="#ffffff" align="right">电子邮件地址：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="${a.email}" id="email_0" class="inputBg" name="email">
                                    (必填)</td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">详细地址：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="${a.address}" id="address_0" class="inputBg" name="address">
                                    (必填)</td>
                                <td bgcolor="#ffffff" align="right">邮政编码：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="${a.zipcode}" id="zipcode_0" class="inputBg" name="zipcode"></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">电话：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="${a.tel}" id="tel_0" class="inputBg" name="tel">
                                    (必填)</td>
                                <td bgcolor="#ffffff" align="right">手机：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="${a.mobile}" id="mobile_0" class="inputBg" name="mobile"></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">&nbsp;</td>
                                <td bgcolor="#ffffff" align="center" colspan="3">
                                    <input data-consignee_id="${a.consignee_id}" class="defaultAddressSubmit" type="button" value="设为默认地址" class="bnt_blue" name="button">
                                    <input class="updateAddressSubmit" type="button" value="确认修改" class="bnt_blue_1" name="submit">
                                    <input data-consignee_id="${a.consignee_id}" class="deleteAddressSubmit" type="button" value="删除" class="bnt_blue" name="button">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                    <br/>
                    </c:forEach>
                    <form id="addAddressForm" method="post" action="/cas/address/addAddress">
                        <table width="100%" cellspacing="1" cellpadding="5" border="0" bgcolor="#dddddd">
                            <tbody>
                            <tr>
                                <td bgcolor="#ffffff" align="right">配送区域：</td>
                                <td bgcolor="#ffffff" align="left" colspan="3">
                                    <select  class="regionSelect" name="region[]">
                                        <option value="0">请选择省</option>
                                        <c:forEach items="${region }" var="r">
                                        <option value="${r.region_id}">${r.region_name}</option>
                                        </c:forEach>
                                    </select>
                                    (必填) </td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">收货人姓名：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="" id="consignee_1" class="inputBg" name="name">
                                    (必填) </td>
                                <td bgcolor="#ffffff" align="right">电子邮件地址：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="" id="email_1" class="inputBg" name="email">
                                    (必填)</td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">详细地址：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="" id="address_1" class="inputBg" name="address">
                                    (必填)</td>
                                <td bgcolor="#ffffff" align="right">邮政编码：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="" id="zipcode_1" class="inputBg" name="zipcode"></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">电话：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="" id="tel_1" class="inputBg" name="tel">
                                    (必填)</td>
                                <td bgcolor="#ffffff" align="right">手机：</td>
                                <td bgcolor="#ffffff" align="left"><input type="text" value="" id="mobile_1" class="inputBg" name="mobile"></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="right">&nbsp;</td>
                                <td bgcolor="#ffffff" align="center" colspan="3">                    <input id="addAddressSubmit" type="button" value="新增收货地址" class="bnt_blue_2" name="submit">
                                    <input type="hidden" value="act_edit_address" name="act">
                                    <input type="hidden" value="" name="address_id">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                                            <div class="pagebar" id="pager">
                            <span style="margin-right: 10px;" class="f_l ">
                                总计
                                <b>${count}</b>
                                个记录
                            </span>
                            <input id="page" type="hidden" value="${page}" />
                            <input id="page_count" type="hidden" value="${pages}" />
                            <a id="firstPageCategory" href="${_STORE_URL}/cas/address/index">第一页</a>
                            
                            {if $page == 1}
                            <a id="prevPageCategory" data-page="${page-1}" href="javascript:;">上一页</a>
                            {else}
                            <a id="prevPageCategory" data-page="${page-1}" href="/cas/address{if $page > 2}?page=${page - 1}{/if}">上一页</a>
                            {/if}
                            
                            {if $page == $pages}
                            <a id="nextPageCategory" data-page="${page+1}" href="javascript:;">下一页</a>
                            {else}
                            <a id="nextPageCategory" data-page="${page+1}" href="/cas/address?page=${page + 1}">下一页</a>
                            {/if}
                            <a id="lastPageCategory" href="/cas/address?page=${pages}">最末页</a>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../index/wrapper.suffix.jsp"/>