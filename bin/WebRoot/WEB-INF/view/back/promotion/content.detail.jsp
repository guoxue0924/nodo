<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="bg-white">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="m-t-sm">
                    <a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0">
                        <i class="fa fa-caret-right text fa-lg"></i>
                        <i class="fa fa-caret-left text-active fa-lg"></i>
                    </a>
                        <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                </div>
            </header>
              
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">优惠促销活动主体表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">：</td>
                                <td class="col-sm-4">${promotionContent.promotionId}</td>
                                <td class="col-sm-2">分类：</td>
                                <td class="col-sm-4">${promotionContent.categoryId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">活动标题：</td>
                                <td class="col-sm-4">${promotionContent.title}</td>
                                <td class="col-sm-2">活动内容描述：</td>
                                <td class="col-sm-4">${promotionContent.content}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">序列化存储的活动规则具体数据：</td>
                                <td class="col-sm-4">${promotionContent.rules}</td>
								<td class="col-sm-2">是否是全场活动：</td>
                                <td class="col-sm-4">
									<c:if test="${promotionContent.isOverall == 0}">
									否
									</c:if>
									<c:if test="${promotionContent.isOverall == 1}">
									是
									</c:if>
								</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">是否启用：</td>
                                <td class="col-sm-4">
									<c:if test="${promotionContent.status == 0}">
									否
									</c:if>
									<c:if test="${promotionContent.status == 1}">
									是
									</c:if>
								</td>
								<td class="col-sm-2">是否标记为删除：</td>
                                <td class="col-sm-4">
									<c:if test="${promotionContent.isDel == 0}">
									否
									</c:if>
									<c:if test="${promotionContent.isDel == 1}">
									是
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">活动开始时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${promotionContent.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">活动结束时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${promotionContent.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">记录创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${promotionContent.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${promotionContent.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/promotion/js/content.detail.js" type="text/javascript"></script>
