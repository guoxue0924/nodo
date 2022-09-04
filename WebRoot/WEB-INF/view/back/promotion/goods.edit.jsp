<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp"/>

  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${promotionGoods!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${promotionGoods!=null}">"${BASE_URL}/promotionGoods/edit"</c:when><c:otherwise>"${BASE_URL}/promotionGoods/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="promotionId" class="col-sm-3 control-label">优惠促销活动</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="promotionId" name="promotionId" value="${promotionGoods.promotionId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>

                    <div class="form-group">
                        <label for="skuId" class="col-sm-3 control-label">关联商品的货号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="skuId" name="skuId" value="${promotionGoods.skuId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>

                    <input type="hidden" name="id" value="${promotionGoods.id}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${promotionGoods==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/promotion/js/goods.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->