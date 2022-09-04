<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="edit-map wrapper" id="edit_base">
	<section class="panel panel-default">
		<header class="panel-heading font-bold">基本信息</header>
		<div class="panel-body">
			<div class="form-group m-b-xs">
				<label class="col-sm-2 control-label"><font class="red">* </font>所属分类</label>
				<div class="col-sm-6">
					<select name="categoryId" id="categoryId" class="form-control">
						<c:forEach items="${categories}" var="v">
							<c:if test="${v.grade == 1}">${v.categoryName}</c:if>
                            <c:if test="${v.grade == 2}">&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                            <c:if test="${v.grade == 3}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                            <option value="${v.categoryId}" <c:if test='${v.categoryId==content.categoryId}'>selected</c:if>>
                                <c:if test="${v.grade == 1}">${v.categoryName}</c:if>
                                <c:if test="${v.grade == 2}">&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                                <c:if test="${v.grade == 3}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${v.categoryName}</c:if>
                            </option>
						</c:forEach>
					</select> <input type="hidden" name="categoryIdLast" value="" />
				</div>
			</div>
			<div class="line line-dashed line-sm pull-in"></div>

			<div class="form-group m-b-xs">
				<label class="col-sm-2 control-label">所属品牌</label>
				<div class="col-sm-6">
					<select name="brandId" id="brandId" class="form-control" brand_id="<c:choose><c:when test="${content.brandId!=0}">${content.brandId}</c:when><c:otherwise>0</c:otherwise></c:choose>">
						<option value="0">请选择品牌</option>
					</select>
				</div>
			</div>
			<div class="line line-dashed line-sm pull-in"></div>

			<div class="form-group m-b-xs">
				<label for="name" class="col-sm-2 control-label"><font class="red">* </font>商品名称</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="name" name="name" value="${content.name}" placeholder="请输入商品名称" />
				</div>
			</div>
			<div class="line line-dashed line-sm pull-in"></div>

			<div class="form-group m-b-xs" style="display: none;">
				<label class="col-sm-2 control-label">开启规格</label>
				<div class="col-sm-6">
					<label class="switch"><input type="checkbox" name="isSpec" id="isSpec" checked="checked" /> <span></span> </label>
				</div>
			</div>
		</div>
	</section>

	<section class="panel panel-default default-hidden" id="edit_base_spec">
		<header class="panel-heading font-bold">选择规格</header>

		<div class="panel-body no-padder">
			<table class="table table-striped m-b-none text-sm" id="spec_option_list">
				<thead>
					<tr>
<!-- 						<th><input type="checkbox" /> -->
<!-- 						</th> -->
						<th>规格</th>
						<th>规格值</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<table class="table table-striped m-b-none text-sm" id="spec_list"></table>
		<!-- 已选用商品规格所对应属性 ID -->

		<div class="panel-body default-hidden">该分类尚未绑定任何规格。</div>
	</section>

	<section class="panel panel-default" id="edit_base_spec">
		<header class="panel-heading font-bold">编辑规格</header>
		<div align="center">
			<table id="createTable">
				<thead>
					<tr>
						<th align="center" style="width: 10%;text-align: center;">库存 <input type="text" data-filed="stock" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">销售价 <input type="text" data-filed="price" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">市场价 <input type="text" data-filed="priceMarket" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">积分价 <input type="text" data-filed="priceIntegral" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">成本价 <input type="text" data-filed="priceCost" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">重量 <input type="text" data-filed="weight" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">长 <input type="text" data-filed="length" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">宽 <input type="text" data-filed="wide" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
						<th align="center" style="width: 10%;text-align: center;">高 <input type="text" data-filed="height" style="width: 40px;" value="" /><input type="button" title="批量设置" value="设置" onclick="addValues(this);" /></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</section>

	<input type="hidden" id="contentId" name="contentId" value='${contentId}' /> <input type="hidden" id="properties" value='${properties}' /> <input type="hidden" id="valueId" value='${valueId}' /> <input type="hidden" id="contentSkus" value='${contentSkus}' /> <input type="hidden" id="contentSkuTos" value='${contentSkuTos}' />

	<section class="panel panel-default" id="edit_base_property">
		<header class="panel-heading font-bold">扩展属性</header>
		<div class="panel-body"></div>
	</section>
</section>