<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4">系统参数</p>
                <span id="edit_notice"></span>
            </header>
                
            <section class="scrollable wrapper">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/systemParam/save" method="post">
                	<section class="edit-map">
			            <div class="form-group">
			        		<label class="col-sm-2 control-label">会员收货地址记录上限</label>
			                <div class="col-sm-6">
			                        <input type="text" class="form-control" name="consigneeLimit" value="${paramMap['consigneeLimit']}" placeholder="会员收货地址记录上限">
			                        <span class="help-block m-b-none">会员可以设置的收获地址最大条目数</span>
			        		</div>
			            </div>
			    		<div class="line line-dashed line pull-in"></div>
			    		
			    		<div class="form-group ">
					        <label class="col-sm-2 control-label">评价是否审核</label>
			                <div class="col-sm-8">
		                        <label class="checkbox-inline p-left-0">
					                <input type="radio" name="isAudit" value="1" <c:if test="${paramMap['isAudit'] == 1}">checked="checked"</c:if>>&nbsp;是
					            </label>
		                        <label class="checkbox-inline p-left-0">
					                <input type="radio" name="isAudit" value="0" <c:if test="${paramMap['isAudit'] == 0}">checked="checked"</c:if>>&nbsp;否
					            </label>
	                        	<span class="help-block m-b-none">只有平台可以审核用户发布的评价</span>
					        </div>
			            </div>
			            <div class="line line-dashed line pull-in"></div>
			    		
			    		<div class="form-group">
			        		<label class="col-sm-2 control-label">上传图片最大尺 寸</label>
			                <div class="col-sm-6">
			                        <input type="text" class="form-control" name="picMaxSize" value="${paramMap['picMaxSize']}" placeholder="上传图片最大尺 寸">
			                        <span class="help-block m-b-none">注意：请谨慎填写，单位是M，如果空间配置有限制，尺寸过大可能会导致系统瘫痪</span>
			        		</div>
			            </div>
			    		<div class="line line-dashed line pull-in"></div>
			    		
			    		<div class="form-group ">
					        <label class="col-sm-2 control-label">短信发送间隔</label>
			                <div class="col-sm-6">
					            <input type="text" class="form-control" name="sendInterval" value="${paramMap['sendInterval']}" placeholder="短信发送间隔" >
					            <span class="help-block m-b-none">短信发送间隔，单位是秒</span>
					        </div>
			            </div>
			            <div class="line line-dashed line pull-in"></div>
			            
			            <div class="form-group ">
					        <label class="col-sm-2 control-label">短信失效时间</label>
			                <div class="col-sm-6">
					            <input type="text" class="form-control" name="timeout" value="${paramMap['timeout']}" placeholder="短信失效时间" >
					            <span class="help-block m-b-none">短信失效时间，单位是秒</span>
					        </div>
			            </div>
			            <div class="line line-dashed line pull-in"></div>
					</section>
                </form>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/system/js/param.js" type="text/javascript"></script>