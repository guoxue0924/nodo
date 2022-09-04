<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- modal - 编辑品牌 -->
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${brand != null}">编辑品牌</c:when><c:otherwise>添加品牌</c:otherwise></c:choose></h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" action="${BASE_URL}/back/goodsBrand/add" method="post" id="edit_form" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="brandName" class="col-sm-3 control-label"><font class="red">* </font>品牌名称</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="brandName" name="brandName" value="${brand.brandName}" placeholder="请输入品牌名称" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="brandNameEn" class="col-sm-3 control-label">品牌英文名称</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="brandNameEn" name="brandNameEn" value="${brand.brandNameEn}" placeholder="请输入品牌英文名称" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="brandUrl" class="col-sm-3 control-label">官方网址</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="brandUrl" name="brandUrl" value="<c:choose><c:when test="${brand != null}">${brand.brandUrl}</c:when><c:otherwise>http://</c:otherwise></c:choose>" />
                    </div>
                </div>
                
                <div class="form-group file-reset">
                    <label class="col-sm-3 control-label">品牌 Logo</label>
                    <div class="col-sm-9">
                        <input type="file" name="brandLogoImage" id="brandLogoImage" class="filestyle" data-icon="false" data-classbutton="btn btn-default" data-classinput="form-control inline input-s" />
                        <c:if test="${brand.brandLogo != null && brand.brandLogo != ''}">
                            <a href="${IMG_URL}${brand.brandLogo}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${brand.brandLogo}" /></a>
                        </c:if>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="brandDesc" class="col-sm-3 control-label">品牌介绍</label>
                    <div class="col-sm-7">
                        <textarea class="form-control" id="brandDesc" name="brandDesc" rows="1">${brand.brandDesc}</textarea>
                    </div>
                </div>
                
                <input type="hidden" name="brandId" value="${brand.brandId}" />
                <input type="hidden" name="ctime" value="${brand.ctime}" />
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存并返回列表管理</button>
            <c:if test="${brand ==null }"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
            <button type="button" class="btn btn-danger btn-sm input-submit" data-dismiss="modal">取消</button>
        </div>
    </div>
</div>
<!-- / modal - 编辑品牌 -->

<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>    
<script src="${STATIC_URL}/back/modules/goods/js/brand.edit.js"></script>