<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"><c:choose><c:when test="${property != null}">编辑属性</c:when><c:otherwise>添加属性</c:otherwise></c:choose></p>
            </header>
            
            <section class="scrollable wrapper panel w-f">
                <form class="form-horizontal" action="${BASE_URL}/back/trendProperty/add" method="post" id="edit_form" enctype="multipart/form-data">
	                <div class="form-group">
	                    <label for="labelName" class="col-sm-2 control-label"><font class="red">* </font>属性名称</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="labelName" name="labelName" value="${property.labelName}" placeholder="请输入属性名称" />
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label for="note" class="col-sm-2 control-label">备注</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="note" name="note" value="${property.note}" placeholder="请输入备注信息" />
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label for="sort_order" class="col-sm-2 control-label">序号</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${property.sortOrder}" />
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label class="col-sm-2 control-label">开启规格</label>
	                    <div class="col-sm-4">
	                        <label class="checkbox-inline p-left-0">
	                            <input type="radio" name="isSpec" value="1" <c:if test="${property.isSpec == 1}">checked="checked"</c:if> />&nbsp;是
	                        </label>
	                        <label class="checkbox-inline"><!-- {if $property.is_spec === '0' || ! $property.is_spec} -->
	                            <input type="radio" name="isSpec" value="0" <c:if test="${property.isSpec == 0}">checked="checked"</c:if> />&nbsp;否
	                        </label>
	                    </div>
	                    <div class="col-sm-6">
	                        <p class="form-control-static">若开启规格，则该属性将和商品价格、库存、重量等相关联</p>
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label class="col-sm-2 control-label">状态</label>
	                    <div class="col-sm-4">
	                        <label class="checkbox-inline p-left-0">
	                            <input type="radio" name="status" value="1" <c:if test="${property.status == 1 || ! $property.status}">checked="checked"</c:if> />&nbsp;启用
	                        </label>
	                        <label class="checkbox-inline">
	                            <input type="radio" name="status" value="0" <c:if test="${property.status == 0}">checked="checked"</c:if> />&nbsp;不启用
	                        </label>
	                    </div>
	                </div>
	                <div class="line line-dashed line-lg pull-in"></div>
	                
	                <div class="form-group">
                        <label for="sort_order" class="col-sm-2 control-label hide">属性值</label>
                        <div class="col-sm-12">
                            <section class="panel panel-default">
                                <header class="panel-heading">
			                        编辑属性值
			                        <a id="btn_add_property_value" class="btn btn-xs btn-primary pull-right">
		                                <i class="fa fa-plus text"></i>
		                                <span class="text">添加</span>
		                            </a>
			                    </header>
                                <table class="table table-striped m-b-none text-sm" id="table_property_value">
                                    <thead>
                                        <tr>
                                            <th>属性值</th>
                                            <th class="set-property-image default-hidden">规格图片</th>
                                            <th>序号</th>                    
                                            <th>默认值</th>                    
                                            <th width="70">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:choose>
                                    	<c:when test="${propertyValues != null}">
                                    	<c:forEach items="${propertyValues}" var="v" varStatus="k">
                                        <!-- {foreach from=$ item=v key=k} -->
                                        <tr>                    
                                            <td>
                                                <input type="text" class="form-control input-sm" name="pVal" value="${v.propertyValue}" placeholder="请输入属性值" />
                                            </td>
                                            <td class="set-property-image default-hidden">
                                                <input type="file" name="p_property_image_${k}" class="filestyle p-property-image" data-icon="false" data-buttonText="上传图片" data-classbutton="btn btn-default btn-sm" data-classinput="form-control inline input-s input-sm" />
                                                <input type="hidden" name="hasImage" value="false" class="hasImage" />
                                               <c:if test="${v.propertyImage != null && v.propertyImage != ''}">
                                                <a href="${IMG_URL}${v.propertyImage}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${v.propertyImage}" /></a>
                                                </c:if>
                                            </td>
                                            <td>
                                                <input type="text" class="form-control input-sm" name="pSortOrder" value="${v.sortOrder}" placeholder="请输入序号" />
                                            </td>
                                            <td>
                                                <label class="switch-sm m-b-none m-t-xs">
						                            <input type="checkbox" class="set-pv-default" name="pIsDefault" value="${k}" <c:if test="${v.isDefault == 1}">checked="checked"</c:if> />
						                            <span></span>
						                        </label>
                                            </td>
                                            <td class="text-right v-middle"><%-- <c:if test="${property_values|@count <= 1}"> --%><%-- <c:if test="${property_values}">hide</c:if> --%>
                                                <a href="javascript:;" class="operate-delete " property_value_id="${v.propertyValueId}" title="删除"><i class="fa fa-trash-o"></i></a>
                                                <input type="hidden" class="property_value_id_hidden" name="pPropertyValueId" value="${v.propertyValueId}" />
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                        <tr>                    
                                            <td>
                                                <input type="text" class="form-control input-sm" name="pVal" value="" placeholder="请输入属性值" />
                                            </td>
                                            <td class="set-property-image default-hidden">
                                                <input type="file" name="p_property_image_0" value="1" class="filestyle p-property-image" data-icon="false" data-buttonText="上传图片" data-classbutton="btn btn-default btn-sm" data-classinput="form-control inline input-s input-sm" />
                                                <input type="hidden" name="hasImage" value="false" class="hasImage" />
                                            </td>
                                            <td>
                                                <input type="text" class="form-control input-sm" name="pSortOrder" value="" placeholder="请输入序号" />
                                            </td>
                                            <td>
                                                <label class="switch-sm m-b-none m-t-xs">
                                                    <input type="checkbox" class="set-pv-default" name="pIsDefault" value="0" />
                                                    <span></span>
                                                </label>
                                            </td>
                                            <td class="text-right v-middle">
                                                <a href="javascript:;" class="operate-delete hide" property_value_id="0" title="删除"><i class="fa fa-trash-o"></i></a>
                                                <input type="hidden" class="property_value_id_hidden" name="propertyValueId" value="0" />
                                            </td>
                                        </tr>
                                       	</c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                            </section>
                        </div>
                    </div>
                    
	                <input type="hidden" name="propertyId" value="${property.propertyId}" />
	            </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${property == null}"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>


<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>
<script src="${STATIC_URL}/back/modules/trend/js/property.edit.js"></script>
