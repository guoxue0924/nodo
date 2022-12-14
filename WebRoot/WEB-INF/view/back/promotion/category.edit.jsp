<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp"/>

  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${promotionCategory!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${promotionCategory!=null}">"${BASE_URL}/promotionCategory/edit"</c:when><c:otherwise>"${BASE_URL}/promotionCategory/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="parentId" class="col-sm-3 control-label">父分类</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="parentId" name="parentId" value="${promotionCategory.parentId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>

                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">分类标题</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="title" name="title" value="${promotionCategory.title}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>

                    <input type="hidden" name="categoryId" value="${promotionCategory.categoryId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${promotionCategory==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/promotion/js/category.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->