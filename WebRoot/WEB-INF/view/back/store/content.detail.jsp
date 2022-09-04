<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                        <font class="h4 v-middle padder">店铺详情
                        </font>
                    </div>
                </div>
            </header>
              
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">基本信息</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                            <tr>                    
                                <td class="col-sm-2 text-right">公司名称</td>
                                <td class="col-sm-4">${store.companyName}</td>
                                <td class="col-sm-2 text-right">店铺名称:</td>
                                <td class="col-sm-4">${store.storeName}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="col-sm-2 text-right">签约开始时间</td><!-- |truncate:10:'':true -->
                                <td class="col-sm-4">${store.signingTimeStart}</td>
                                <td class="col-sm-2 text-right">签约结束时间</td>
                                <td class="col-sm-4">${store.signingTimeEnd}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="col-sm-2 text-right">所在地区</td>
                                <td class="col-sm-4">${store.regionName}</td>
                                <td class="col-sm-2 text-right">详细地址</td>
                                <td class="col-sm-4">${store.address}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="col-sm-2 text-right">主营业务</td>
                                <td class="col-sm-4">${store.businessCategoryids}</td>
                                <td class="col-sm-2 text-right">公司法人</td>
                                <td class="col-sm-4">${store.legalpName}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="col-sm-2 text-right">营业执照号码</td>
                                <td class="col-sm-4">${store.businessLicense}</td>
                                <td class="col-sm-2 text-right">营业执照图片</td>
                                <td class="col-sm-4">
                                	<c:if test="${store.businessImage}">
                                    <a href="${IMG_URL}${store.businessImage}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${store.businessImage}" /></a>
                                    </c:if>
                                </td>
                            </tr>
                            
                            <tr>                    
                                <td class="ccol-sm-2 text-right">公司电话</td>
                                <td class="col-sm-4">${store.tel}</td>
                                <td class="ccol-sm-2 text-right">公司传真</td>
                                <td class="col-sm-4">${store.fax}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="ccol-sm-2 text-right">公司邮箱</td>
                                <td class="col-sm-4">${store.email}</td>
                                <td class="ccol-sm-2 text-right">是否审核</td>
                                <td class="col-sm-4"><c:if test="${store.isVerify == 1}">审核通过</c:if><c:choose><c:when test="${store.isVerify == -1}">审核未通过</c:when><c:otherwise>待审核</c:otherwise></c:choose></td>
                            </tr>
                            <c:if test="${store.isVerify == -1}">
                            <tr>                    
                                <td class="ccol-sm-2 text-right">审核未通过原因</td>
                                <td class="col-sm-4" colspan="3">${store.rejectionReason}</td>
                            </tr>
                            </c:if>
                        </tbody>
                    </table>
                </section>
                
                <section class="panel panel-default portlet-item">
                    <header class="panel-heading">账户信息</header>
                    <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                            <tr>                    
                                <td class="ccol-sm-2 text-right">用户名</td>
                                <td class="col-sm-10">${store.username}</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
                
                <section class="panel panel-default portlet-item">
                    <header class="panel-heading">店铺设置</header>
                    <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                            <tr>                    
                                <td class="ccol-sm-2 text-right">店铺名称</td>
                                <td class="col-sm-4">${store.storeName}</td>
                                <td class="ccol-sm-2 text-right">店铺 Logo</td>
                                <td class="col-sm-4">
                                	<c:if test="${store.logo}">
						            <a href="${IMG_URL}${store.logo}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${store.logo}" /></a>
						            </c:if>
                                </td>
                            </tr>
                            
                            <tr>                    
                                <td class="ccol-sm-2 text-right">发货地区</td>
                                <td class="col-sm-4">${store.regionName}</td>
                                <td class="ccol-sm-2 text-right">退货地址</td>
                                <td class="col-sm-4">${store.addressReturn}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="ccol-sm-2 text-right">退货条约</td>
                                <td class="col-sm-4" colspan="3">${store.returnRule}</td>
                            </tr>
                            
                            <tr>                    
                                <td class="ccol-sm-2 text-right">发布商品是否需审核</td>
                                <td class="col-sm-4"><c:choose><c:when test="${store.goodsVerify == 1}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
                                <td class="ccol-sm-2 text-right"></td>
                                <td class="col-sm-4"></td>
                            </tr>
                        </tbody>
                    </table>
                </section>
                
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/store/js/content.detail.js" type="text/javascript"></script>