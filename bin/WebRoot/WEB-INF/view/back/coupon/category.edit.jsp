<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>

<input type="hidden" name="couponTypeVal" value="${couponTO.coupon.couponType}">
<input type="hidden" name="isExchangeVal" value="${couponTO.coupon.isExchange}">
<section class="hbox stretch">
    <aside class="bg-white">
        <section class="vbox">
            <header class="header b-b b-t bg-white-only">
            	<div class="m-t-sm">
                	<p class="h4"> <c:choose> <c:when test="${coupon!=null}">编辑优惠券</c:when><c:otherwise>添加优惠券</c:otherwise></c:choose></p>
                </div>
            </header>
                
            <section class="scrollable w-f">
                <form class="form-horizontal" id="edit_form" action=
                	<c:choose> <c:when test="${couponTO!=null}">"${BASE_URL}/back/couponCategory/edit"</c:when><c:otherwise>"${BASE_URL}/back/couponCategory/add"</c:otherwise></c:choose>
                	 method="post">
                	<section class="edit-map wrapper" id="edit_base">
                		<section class="panel panel-default">
                			<header class="panel-heading font-bold">基本信息</header>
                			<div class="panel-body">
                			
		                    	<div class="form-group m-b-xs">
						            <label for="couponName" class="col-sm-2 control-label"><font class="red">* </font>优惠券名称</label>			            
						            <div class="col-sm-3">
						                <input type="text" class="form-control" id="couponName" name="couponName" value="${coupon.couponName}" placeholder="请输入优惠券名称">
						            </div>
						        </div>
			                    <div class="line line-dashed line-lg pull-in"></div>
			                    
			                    <div class="form-group m-b-xs">
								    <label for="faceValue" class="col-sm-2 control-label"><font class="red">* </font>面额</label>
								    <div class="col-sm-2">
								    	<div class="input-group">
									        <input type="text" class="form-control" id="faceValue" name="faceValue" value="${coupon.faceValue}">
									   		<span class="input-group-addon">元</span>
									    </div>
								    </div>
								</div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
						            <label for="validTime" class="col-sm-2 control-label"><font class="red">* </font>有效期</label>
						            <div class="col-sm-2">
						                <div class="input-group">
						                   <input name="validStime" id="validStime" class="input-sm input-s datetimepicker-input form-control" type="text" 
						                   value="<fmt:formatDate value="${coupon.validStime}" pattern="yyyy-MM-dd hh:mm"/>" placeholder="有效开始时间" readonly="readonly">
						                </div>
						            </div>
						            <div class="col-sm-2">
						                <div class="input-group">
						                   <input name="validEtime" id="validEtime" class="input-sm input-s datetimepicker-input form-control" type="text" 
						                   value="<fmt:formatDate value="${coupon.validEtime}" pattern="yyyy-MM-dd hh:mm"/>" placeholder="有效结束时间" readonly="readonly">
						                </div>
						            </div>
						        </div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
						            <label for="grantTime" class="col-sm-2 control-label"><font class="red">* </font>发放时间</label>
						            <div class="col-sm-2">
						                <div class="input-group">
						                   <input name="grantStime" id="grantStime" class="input-sm input-s datetimepicker-input form-control" type="text" 
						                   value="<fmt:formatDate value="${coupon.grantStime}" pattern="yyyy-MM-dd hh:mm"/>" placeholder="发放开始时间" readonly="readonly">
						                </div>
						            </div>
						            <div class="col-sm-2">
						                <div class="input-group">
						                   <input name="grantEtime" id="grantEtime" class="input-sm input-s datetimepicker-input form-control" type="text" 
						                   value="<fmt:formatDate value="${coupon.grantEtime}" pattern="yyyy-MM-dd hh:mm"/>" placeholder="发放结束时间" readonly="readonly">
						                </div>
						            </div>
						        </div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
						            <label for="total" class="col-sm-2 control-label"><font class="red">* </font>发放量</label>
						            <div class="col-sm-2">
						                <div class="input-group">
						                    <input type="text" class="form-control total" id="total" name="total" value="${coupon.total}">
						                </div>
						            </div>
						        </div>
			                    <div class="line line-dashed line-lg pull-in"></div>
			                    
			                    <div class="form-group m-b-xs">
						            <label for="eachlimit" class="col-sm-2 control-label"><font class="red">* </font>每人限领</label>
						            <div class="col-sm-2">
						                <div class="input-group">
											<select class="input-sm form-control inline" name="eachlimit" id="eachlimit">
												<c:forEach begin="0" end="5" var="num" step="1">
													<option value="${num}" <c:if test="${(coupon == null && num == 0) || (num == coupon.eachlimit)}">selected</c:if>>
														<c:choose><c:when test="${num == 0}">不限</c:when><c:otherwise>${num}</c:otherwise></c:choose>
													</option>
												</c:forEach>
                   				            </select>						                
                 				        </div>
						            </div>
						        </div>
			                    <div class="line line-dashed line-lg pull-in"></div>
			                    
							    <div class="form-group m-b-xs">
						            <label class="col-sm-2 control-label">上传图片</label>
						            <div class="col-sm-6">
						                <input type="file" name="imageFile" id="imageFile" class="filestyle" 
						                data-icon="false" data-buttontext="上传图片" data-classbutton="btn btn-default" 
						                data-classinput="form-control inline input-s" style="position: fixed; left: -500px;">
						                <div class="bootstrap-filestyle" style="display: inline;">
						                	<input type="text" name="file" class="form-control inline input-s" disabled=""> 
						                	<label for="imageFile" class="btn btn-default"><span>上传图片</span></label>
						                	<input type="hidden" name="imageDefault" id="imageUrl">
						                </div>
						                <img id="image" alt="" src='<c:if test="${coupon != null and coupon.imageDefault != null and coupon.imageDefault != ''}">${IMG_URL}${coupon.imageDefault}</c:if>' height="30px" width="30px" style="margin-left: 10px">
						            </div>
						        </div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
                                    <label for="couponPoint" class="col-sm-2 control-label">兑换所需积分</label>
                                    <div class="col-sm-2">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="couponPoint" name="couponPoint" placeholder="留空表示不可兑换" value="${coupon.couponPoint}">
                                        </div>
                                    </div>
                                </div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
                                    <label for="basicPrice" class="col-sm-2 control-label">消费金额</label>
                                    <div class="col-sm-2">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="basicPrice" name="basicPrice" placeholder="留空表示不限制消费金额" value="${coupon.basicPrice}">
                    						<span class="input-group-addon">元</span>
                                        </div>
                                    </div>
                                </div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
                                    <label for="body" class="col-sm-2 control-label">使用详情</label>
                                    <div class="col-sm-6">
                                        <textarea name="body" id="body" rows="3" class="form-control" placeholder="请输入使用详情">${coupon.body}</textarea>
                                    </div>
                                </div>
							    <div class="line line-dashed line-lg pull-in"></div>
							    
							    <div class="form-group m-b-xs">
                                    <label for="rule" class="col-sm-2 control-label">使用规则</label>
                                    <div class="col-sm-6">
                                        <textarea name="rule" id="rule" rows="3" class="form-control" placeholder="请输入使用规则">${coupon.rule}</textarea>
                                    </div>
                                </div>
							    
			                    <input type="hidden" id="couponId"  name="couponId" value="${coupon.couponId}" />
			                    <input type="hidden" id="couponType"  name="couponType" value="${coupon.couponType}" />
		                    </div>
		                    <div class="line line-dashed line-sm pull-in"></div>
	                    </section>
                    </section>
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${coupon ==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${STATIC_URL}/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/vendor/jquery.ui.widget.js"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/jquery.fileupload.js"></script>
<script src="${STATIC_URL}/back/modules/coupon/js/category.edit.js" ></script>