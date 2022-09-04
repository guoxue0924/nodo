<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>
<link href="${STATIC_URL}/plugins/jquery-showloading/1.0/showloading.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
    <aside class="bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-1 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>
                        </div>
                    </div>
                    <form action="" class="form-inline">
                    <div class="col-sm-8 m-b-xs text-right">
                    	<div class="form-group">
                            <select id="categoryId" name="categoryId" class="form-control input-sm">
                                <c:forEach items="${categorys}" var="c">
                                	<option value="${c.categoryId}">${c.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-3 m-b-xs pull-right">
                        <div class="input-group">
                            <input type="text" name="key" class="input-sm form-control" placeholder="Search" />
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
                            </span>
                        </div>
                    </div>
                    </form>
                </div>
            </header>
            
            <section class="scrollable wrapper w-f chat-list" id="content_listing">
                <section class="chat-list panel-body" id="content_listing_message">
                </section>
                
                <section class="panel" id="content_listing_anchor"></section>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <form class="m-t-sm" action="">
                    <div class="input-group">
                        <input type="text" id="body_reply" placeholder="请输入..." class="input-sm form-control input-s-sm">
                        <div class="input-group-btn">
                            <button class="btn btn-sm btn-default" id="submit_reply">回复</button>
                        </div>
                    </div>
                </form>
            </footer>
        </section>
    </aside>
    
    <aside class="bg-light lter aside-md">
        <section class="vbox">
            <header class="b-b header"><p class="h4">联系人<font class="text-sm">（${fn:length(contacts)}）</font></p></header>
            <section class="scrollable w-f">
                <div class="list-group list-group-alt no-radius no-borders" id="latest_contacts">
                	<c:forEach items="${contacts}" var="c" varStatus="status">
                    <div class="list-group-item panel-body wrapper-sm cursor-pointer <c:if test="${status.index==0}">active</c:if>" userid="${c.userid}" gender="${c.gender}">
                        <a href="javascript:;" class="pull-left thumb-sm avatar">
                        	<c:if test="${c.avatar != ''}">
	                            <img src="${IMG_URL}${c.avatar}" class="img-circle">
                        	</c:if>
                        	<c:if test="${c.avatar == null || c.avatar == ''}">
	                            <c:if test="${c.gender == 2}">
	                            	<img src="${STATIC_URL}/back/images/avatar_default_female.jpg" class="img-circle">
	                            </c:if>
	                            <c:if test="${c.gender == 1}">
	                            	<img src="${STATIC_URL}/back/images/avatar_default.jpg" class="img-circle">
	                            </c:if>
                            </c:if>
                            <span class="text-nm">
                            	<c:if test="${c.nickname != null}">
                            		${c.nickname}
                            	</c:if>
                            	<c:if test="${c.username != null}">
                            		${c.username}
                            	</c:if>
                            </span>
                        </a>
                        <div class="clear text-right">
                            <span class="block text-snm last-message-ctime"><fmt:formatDate value="${c.ctime}" pattern="MM-dd hh:mm"/></span>
                            <span class="badge badge-sm bg-danger last-message-count"><c:if test="${c.total > 0}">${c.total}</c:if></span>
                            <input type="hidden" id="gender" value="${loggedInUser.gender}">
                        </div>
                    </div>
                    </c:forEach>
                  </div>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/jquery-showloading/1.0/jquery.showloading.min.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-masonry/4.0.0/masonry.pkgd.min.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/im/js/content.index.js" type="text/javascript"></script>