<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="edit-map wrapper" id="edit_base">
    
	<div class="form-group m-b-xs">
		<label class="col-sm-2 control-label"><font class="red">* </font>签约开始时间</label>
		<div class="col-sm-10">
			<input id="signingTimeStart" name="signingTimeStartStr" class="input-sm input-s datepicker-input form-control" type="text" 
			        data-date-format="yyyy-mm-dd" value="${signingTimeStart}" /><%-- ${store.signing_time_start|truncate:10:'':true} --%>
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label class="col-sm-2 control-label"><font class="red">* </font>签约结束时间</label>
		<div class="col-sm-10">
			<input id="signingTimeEnd" name="signingTimeEndStr" class="input-sm input-s datepicker-input form-control" type="text" 
			        data-date-format="yyyy-mm-dd" value="${signingTimeEnd}" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="companyName" class="col-sm-2 control-label"><font class="red">* </font>公司名称</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="companyName" name="companyName" value="${store.companyName}" placeholder="请输入公司名称" />
		    <input type="hidden" name="companyNameEdit" id="companyNameEdit" value="${store.companyName}" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label class="col-sm-2 control-label">所在地区</label>
		<div class="col-sm-8">
		    <select data-init="3743" data-selected="${regionHidArr[2]}" class="input-sm form-control inline col-def-3 region">
                <option value="0">请选择</option>
            </select>
		    <select data-init="${regionHidArr[2]}" data-selected="${regionHidArr[3]}" class="input-sm form-control inline col-def-3 region">
                <option value="0">请选择</option>
            </select>
		    <select name="regionId" data-init="${regionHidArr[3]}" data-selected="${regionHidArr[4]}" class="input-sm form-control inline col-def-3 region">
                <option value="0">请选择</option>
            </select>
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="address" class="col-sm-2 control-label">详细地址</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="address" name="address" value="${store.address}" placeholder="请输入店铺详细地址" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="legalpName" class="col-sm-2 control-label">公司法人</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="legalpName" name="legalpName" value="${store.legalpName}" placeholder="请输入法人姓名" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="businessLicense" class="col-sm-2 control-label">营业执照号码</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="businessLicense" name="businessLicense" value="${store.businessLicense}" placeholder="请输入公司营业执照号码" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs file-reset">
		<label class="col-sm-2 control-label">营业执照图片</label>
		<div class="col-sm-8">
			<input type="file" name="businessImageFile" id="businessImageFile" class="filestyle" data-icon="false" data-buttonText="上传图片" data-classbutton="btn btn-default" data-classinput="form-control inline input-s" />
			<input type="hidden" name="businessImage" id="businessImage" value="" />
			<c:if test="${store.businessImage != null && store.businessImage != ''}">
            <a href="${IMG_URL}${store.businessImage}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${store.businessImage}" /></a>
            </c:if>
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="businessCategoryids" class="col-sm-2 control-label">主营业务</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="businessCategoryids" name="businessCategoryids" value="${store.businessCategoryids}" placeholder="请输入公司主营业务" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="tel" class="col-sm-2 control-label">公司电话</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="tel" name="tel" value="${store.tel}" placeholder="请输入公司电话" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="fax" class="col-sm-2 control-label">公司传真</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="fax" name="fax" value="${store.fax}" placeholder="请输入公司传真" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="email" class="col-sm-2 control-label">公司邮箱</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="email" name="email" value="${store.email}" placeholder="请输入公司邮箱" />
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>
	
	<div class="form-group m-b-xs">
        <label class="col-sm-2 control-label">是否审核</label>
        <div class="col-sm-4">
            <select name="isVerify" id="isVerify" class="form-control input-sm">
                <option value="0" <c:if test="${store.isVerify eq 0 }">selected</c:if>>待审核</option>
                <option value="1" <c:if test="${store.isVerify eq 1 }">selected</c:if>>通过审核</option>
                <option value="-1" <c:if test="${store.isVerify eq -1 }">selected</c:if>>拒绝审核</option>
            </select>
            <input type="hidden" name="isVerifyOld" value="${store.isVerify}" />
        </div>
    </div>
    <div class="line line-dashed line-sm pull-in"></div>
    
    <div id="rejectionReason" class="form-group m-b-xs {if $store.isVerify neq -1}default-hidden{/if}">
        <label for="rejectionReason" class="col-sm-2 control-label">拒绝审核原因</label>
        <div class="col-sm-4">
            <textarea name="rejectionReason" id="rejectionReason" rows="2" class="form-control" placeholder="请输入拒绝审核的原因">${store.rejectionReason}</textarea>
        </div>
    </div>

</section>