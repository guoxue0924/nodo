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
	                <header class="panel-heading">用户积分表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">积分ID：</td>
                                <td class="col-sm-4">${casUserPoint.pointId}</td>
                                <td class="col-sm-2">用户ID：</td>
                                <td class="col-sm-4">${casUserPoint.userid}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">积分类型：</td>
                                <td class="col-sm-4">
									<c:if test="${casUserPoint.pointType == 1}">
									注册奖励
									</c:if>
									<c:if test="${casUserPoint.pointType == 2}">
									每日登录
									</c:if>
									<c:if test="${casUserPoint.pointType == 3}">
									每日签到）
									</c:if>
								</td>
                                <td class="col-sm-2">积分名称：</td>
                                <td class="col-sm-4">${casUserPoint.pointName}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">积分：</td>
                                <td class="col-sm-4">${casUserPoint.point}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${casUserPoint.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/back/modules/cas/js/userPoint.detail.js" type="text/javascript"></script>
