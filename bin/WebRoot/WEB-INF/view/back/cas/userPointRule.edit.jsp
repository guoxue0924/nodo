<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp"/>

  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${casUserPointRule!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${casUserPointRule!=null}">"${BASE_URL}/back/casUserPointRule/edit"</c:when><c:otherwise>"${BASE_URL}/back/casUserPointRule/add"</c:otherwise></c:choose> method="post">
					<div class="form-group">
				        <label class="col-sm-3 control-label">积分类型</label>
				        <div class="col-sm-4">
				            <select id="pointType" name="pointType" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="1" <c:if test="${casUserPointRule.pointType == 1}">selected="selected"</c:if>>注册奖励</option>
				                	<option value="2" <c:if test="${casUserPointRule.pointType == 2}">selected="selected"</c:if>>每日登录</option>
				                	<option value="3" <c:if test="${casUserPointRule.pointType == 3}">selected="selected"</c:if>>每日签到）</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="pointName" class="col-sm-3 control-label">积分名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="pointName" name="pointName" value="${casUserPointRule.pointName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="point" class="col-sm-3 control-label">积分</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="point" name="point" value="${casUserPointRule.point}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="pointCoefficient" class="col-sm-3 control-label">积分系数</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="pointCoefficient" name="pointCoefficient" value="${casUserPointRule.pointCoefficient}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="pointRoleId" value="${casUserPointRule.pointRoleId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${casUserPointRule==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/cas/js/userPointRule.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->