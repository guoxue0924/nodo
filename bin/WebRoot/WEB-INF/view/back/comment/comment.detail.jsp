<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/back/modules/comment/css/index.detail.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                        <font class="h4 v-middle padder">评论详情</font>
                    </div>
                </div>
            </header>
              
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">用户评分</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                            <tr>                    
                                <td class="col-sm-2">购买商品</td>
                                <%-- <td class="col-sm-4">
                                    <a href="goods/index/detail?goods_id=${comment.to_id}" target="_blank">${comment.comment_object.name}</a>
                                </td> --%>
                                <td class="col-sm-2">物流满意度评分</td>
                                <td class="col-sm-4">${comment.rankBase}</td>
                            </tr>
                            <tr>                    
                                <td class="col-sm-2">商品满意度评分</td>
                                <td class="col-sm-4">${comment.rankLogistics}</td>
                                <td class="col-sm-2">发货速度满意度评分</td>
                                <td class="col-sm-4">${comment.rankLogistics}</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
                
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">用户反馈</header>
		            <section class="panel-body">
		            ${comment.content}
                    </section>
                </section>
                
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">用户晒单</header>
		            <section class="panel-body">
		              <c:if test="${attachment != null}">
		            	<c:forEach items="${attachment}" var="v">
                        <a href="uploads${v['filepath']}" class="img-thumbnail pull-left img-thumbnail-new" target="_blank">
                            <img class="img-thumbnail-multiple" src="uploads${v['filepath']}" />
                        </a>
                        </c:forEach>
                      </c:if>
                    </section>
                </section>
                
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/comment/js/index.detail.js" type="text/javascript"></script>