<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
        	<header class="b-b header">
                <p class="h4"><c:choose><c:when test="${link !=null}">编辑友情链接</c:when><c:otherwise>添加友情链接</c:otherwise></c:choose></p>
            </header>
            
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/friendlink/add" method="post">
                    <div class="form-group">
	                    <label for="sort_order" class="col-sm-2 control-label">序号</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="sortOrder" name="sortOrder" 
	                        value="<c:choose><c:when test="${link !=null}">${link.sortOrder}</c:when><c:otherwise>0</c:otherwise></c:choose>" maxlength="5" />
	                    </div>
	                </div>
	                <div class="line line-dashed line pull-in"></div>
	                
	                <div class="form-group">
	                    <label for="link_name" class="col-sm-2 control-label"><font class="red">* </font>友情链接名称</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="linkName" name="linkName" value="${link.linkName}" placeholder="请输入友情链接名称" />
	                    </div>
	                </div>
	                <div class="line line-dashed line pull-in"></div>
	                
	                <div class="form-group">
	                    <label for="href" class="col-sm-2 control-label">链接地址</label>
	                    <div class="col-sm-4">
	                        <input type="text" class="form-control" id="href" name="href" value="${link.href}" />
	                    </div>
	                </div>
	                <div class="line line-dashed line pull-in"></div>
	                
	                <div class="form-group">
	                    <label class="col-sm-2 control-label">友情链接图片</label>
	                    <div class="col-sm-4">
	                    	<input type="file" name="imageFile" id="imageFile" class="filestyle" 
			                data-icon="false" data-buttontext="上传图片" data-classbutton="btn btn-default" 
			                data-classinput="form-control inline input-s" style="position: fixed; left: -500px;">
			                <div class="bootstrap-filestyle" style="display: inline;">
			                	<input type="text" name="file" class="form-control inline input-s" disabled=""> 
			                	<label for="imageFile" class="btn btn-default"><span>上传图片</span></label>
			                	<input type="hidden" name="imageUrl" id="imageUrl" value="${link.imageUrl}">
			                </div>
	                        <a href="${link.imageUrl}" class="thumb-sm" target="_blank"><img id="image" src="${IMG_URL}${link.imageUrl}" <c:if test="${link == null || link.imageUrl == null || link.imageUrl == '' }">class="hide"</c:if>/></a>
	                    </div>
	                </div>
	                <div class="line line-dashed line pull-in"></div>
	                
	                <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-4">
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" <c:if test="${link.status == 1}">checked</c:if> name="status" value="1" /> 启用
                            </label>
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" <c:if test="${link == null || link.status == 0}">checked</c:if> name="status" value="0" /> 关闭
                            </label>
                        </div>
                    </div>
	                
	                <input type="hidden" name="linkId" id="linkId" value="${link.linkId}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/vendor/jquery.ui.widget.js"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/jquery.fileupload.js"></script>
<script src="${STATIC_URL}/back/modules/advert/js/friendlink.edit.js" type="text/javascript"></script>