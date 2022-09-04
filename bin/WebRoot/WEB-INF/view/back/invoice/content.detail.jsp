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
	                <header class="panel-heading">发票表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">：</td>
                                <td class="col-sm-4">${invoiceContent.id}</td>
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${invoiceContent.userid}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">订单id：</td>
                                <td class="col-sm-4">${invoiceContent.orderId}</td>
                                <td class="col-sm-2">发票代码：</td>
                                <td class="col-sm-4">${invoiceContent.invoiceCode}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">发票号码：</td>
                                <td class="col-sm-4">${invoiceContent.invoiceNumber}</td>
                                <td class="col-sm-2">机打号：</td>
                                <td class="col-sm-4">${invoiceContent.machineNumber}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">机器编号：</td>
                                <td class="col-sm-4">${invoiceContent.identificationNumber}</td>
                                <td class="col-sm-2">收款单位：</td>
                                <td class="col-sm-4">${invoiceContent.payee}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">税务登记号：</td>
                                <td class="col-sm-4">${invoiceContent.taxRegisterNumber}</td>
                                <td class="col-sm-2">开票日期：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${invoiceContent.invoiceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">收款员：</td>
                                <td class="col-sm-4">${invoiceContent.receiver}</td>
                                <td class="col-sm-2">付款单位：</td>
                                <td class="col-sm-4">${invoiceContent.payer}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">项目：</td>
                                <td class="col-sm-4">${invoiceContent.goodsContentSkuId}</td>
                                <td class="col-sm-2">单价：</td>
                                <td class="col-sm-4">${invoiceContent.price}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">数量：</td>
                                <td class="col-sm-4">${invoiceContent.amount}</td>
                                <td class="col-sm-2">金额：</td>
                                <td class="col-sm-4">${invoiceContent.money}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">合计：</td>
                                <td class="col-sm-4">${invoiceContent.lumpSum}</td>
                                <td class="col-sm-2">税控码：</td>
                                <td class="col-sm-4">${invoiceContent.taxCode}</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/invoice/js/content.detail.js" type="text/javascript"></script>
