<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="edit-map wrapper default-hidden" id="edit_setting">
    
    <div class="form-group m-b-xs">
        <label for="storeName" class="col-sm-2 control-label">店铺名称</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="storeName" name="storeName" value="${store.storeName}" placeholder="请输入店铺名称" />
            <input type="hidden" name="storeNameEdit" id="storeNameEdit" value="${store.storeName}" />
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
    <div class="form-group m-b-xs file-reset">
        <label class="col-sm-2 control-label">店铺 Logo</label>
        <div class="col-sm-7">
            <input type="file" name="logoImageFile" id="logoImageFile" class="filestyle" data-icon="false" data-buttonText="上传图片" data-classbutton="btn btn-default" data-classinput="form-control inline input-s" />
            <c:if test="${store.logo != null && store.logo != ''}">
            <a href="${IMG_URL}${store.logo}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${store.logo}" /></a>
            </c:if>
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
    <div class="form-group m-b-xs">
        <label class="col-sm-2 control-label">发货地区</label>
        <div class="col-sm-8">
            <select data-init="3743" data-selected="${regionHidShipArr[2]}" class="input-sm form-control inline col-def-3 region">
                <option value="0">请选择</option>
            </select>
            <select name="regionIdShip" data-init="${regionHidShipArr[2]}" data-selected="${regionHidShipArr[3]}" class="input-sm form-control inline col-def-3 region">
                <option value="0">请选择</option>
            </select>
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
    <div class="form-group m-b-xs">
        <label for="addressReturn" class="col-sm-2 control-label">退货地址</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="addressReturn" name="addressReturn" value="${store.addressReturn}" placeholder="请输入退货地址" />
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>

    <div class="form-group m-b-xs">
        <label for="returnRule" class="col-sm-2 control-label">退货条约</label>
        <div class="col-sm-8">
            <textarea name="returnRule" id="returnRule" rows="5" class="form-control" placeholder="请输入退货条约">${store.returnRule}</textarea>
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
    <div class="form-group m-b-xs">
        <label class="col-sm-2 control-label">发布商品是否需审核</label>
        <div class="col-sm-4">
            <label class="switch">
                <input type="checkbox" id="goodsVerify" name="goodsVerify" value="1" <c:if test="${store.goodsVerify == '' || store.goodsVerify == 1 }">checked=checked</c:if> />
                <span></span>
            </label>
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
</section>