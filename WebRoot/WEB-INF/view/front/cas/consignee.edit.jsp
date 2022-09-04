 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    <div class="eject_con">
        <div class="adds">
            <div id="warning"></div>
            <form method="post" action="${BASE_URL}/front/cas/consignee/add" id="edit_form" target="_parent">
                <input type="hidden" name="consigneeId" id="consigneeId" value="${consignee.consigneeId}">
                <dl>
                    <dt><i class="required">*</i>收货人：</dt>
                    <dd>
                        <input type="text" class="text w100" name="name" value="${consignee.name}">
                        <p class="hint">请填写您的真实姓名</p>
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>所在地区：</dt>
                    <dd>
                        <input type="hidden" value="${consignee.regionName}" name="regionName" id="regionName" class="area_names">
                        <span id="region" class="w400">
                        <%-- <input type="hidden" value="${consignee.regionId}" name="regionId" class="area_names"> --%>
                        <c:choose><c:when test="${consignee.regionName !=null}">
                            ${consignee.regionName}
                                <input id="edit_area" type="button" value="编辑" style="width: 60px; height: 32px; border: 1px solid rgb(231, 231, 231); cursor: pointer; background-color: rgb(245, 245, 245);" class="edit_region">
                            </c:when><c:otherwise>
                                <select name="province" id="province">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${region}" var="r">
                                    <option value="${r.regionId}">${r.regionName}</option>
                                 </c:forEach>
                                </select>
                            </c:otherwise></c:choose>
                        </span>
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>街道地址：</dt>
                    <dd>
                        <input class="text w300" type="text" name="address" value="${consignee.address}">
                        <p class="hint">不必重复填写地区</p>
                    </dd>
                </dl>
                <dl>
                    <dt>电话号码：</dt>
                    <dd>
                        <input type="text" class="text w200" name="tel" value="${consignee.tel}">
                        <p class="hint">区号 - 电话号码 - 分机</p>
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>手机号码：</dt>
                    <dd>
                        <input type="text" class="text w200" name="mobile" value="${consignee.mobile}">
                    </dd>
                </dl>
                <dl>
                    <dt><em class="pngFix"></em>设为默认地址：</dt>
                    <dd>
                        <input type="checkbox" class="checkbox vm mr5" name="isDefault" id="isDefault" value="1" <c:if test="${consignee.isDefault eq 1}">checked</c:if>>
                        <label for="is_default">设置为默认收货地址</label>
                    </dd>
                </dl>
                <div class="bottom">
                    <label class="submit-border">
                        <input type="button" class="submit" value="保存地址">
                    </label>
                    <a class="ncm-btn ml5" href="javascript:DialogManager.close('my_address_edit');">取消</a> </div>
            </form>
        </div>
    </div>
</div>

<script src="${STATIC_URL}/front/modules/cas/js/consignee.edit.js"></script>