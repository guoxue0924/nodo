<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${invoiceContent!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${invoiceContent!=null}">"${BASE_URL}/back/invoiceContent/edit"</c:when><c:otherwise>"${BASE_URL}/back/invoiceContent/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="userid" class="col-sm-3 control-label"><font class="red">* </font>用户id</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="userid" name="userid" value="${invoiceContent.userid}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="orderId" class="col-sm-3 control-label"><font class="red">* </font>订单id</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="orderId" name="orderId" value="${invoiceContent.orderId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="invoiceCode" class="col-sm-3 control-label"><font class="red">* </font>发票代码</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="invoiceCode" name="invoiceCode" value="${invoiceContent.invoiceCode}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="invoiceNumber" class="col-sm-3 control-label"><font class="red">* </font>发票号码</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="invoiceNumber" name="invoiceNumber" value="${invoiceContent.invoiceNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="machineNumber" class="col-sm-3 control-label"><font class="red">* </font>机打号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="machineNumber" name="machineNumber" value="${invoiceContent.machineNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="identificationNumber" class="col-sm-3 control-label"><font class="red">* </font>机器编号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="identificationNumber" name="identificationNumber" value="${invoiceContent.identificationNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="payee" class="col-sm-3 control-label"><font class="red">* </font>收款单位</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="payee" name="payee" value="${invoiceContent.payee}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="taxRegisterNumber" class="col-sm-3 control-label"><font class="red">* </font>税务登记号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="taxRegisterNumber" name="taxRegisterNumber" value="${invoiceContent.taxRegisterNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group m-b-xs">
                        <label for="invoiceDate" class="col-sm-3 control-label"><font class="red">* </font>开票日期</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="invoiceDate" name="invoiceDate" 
                            value="<fmt:formatDate value="${invoiceContent.invoiceDate}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择开票日期时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>

                    <div class="form-group">
                        <label for="receiver" class="col-sm-3 control-label"><font class="red">* </font>收款员</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="receiver" name="receiver" value="${invoiceContent.receiver}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="payer" class="col-sm-3 control-label"><font class="red">* </font>付款单位</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="payer" name="payer" value="${invoiceContent.payer}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="goodsContentSkuId" class="col-sm-3 control-label"><font class="red">* </font>项目</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="goodsContentSkuId" name="goodsContentSkuId" value="${invoiceContent.goodsContentSkuId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="price" class="col-sm-3 control-label"><font class="red">* </font>单价</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="price" name="price" value="${invoiceContent.price}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="amount" class="col-sm-3 control-label"><font class="red">* </font>数量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="amount" name="amount" value="${invoiceContent.amount}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="money" class="col-sm-3 control-label"><font class="red">* </font>金额</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="money" name="money" value="${invoiceContent.money}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="lumpSum" class="col-sm-3 control-label"><font class="red">* </font>合计</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="lumpSum" name="lumpSum" value="${invoiceContent.lumpSum}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <div class="form-group">
                        <label for="taxCode" class="col-sm-3 control-label"><font class="red">* </font>税控码</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="taxCode" name="taxCode" value="${invoiceContent.taxCode}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             </div>
                      </div>

                    <input type="hidden" name="id" value="${invoiceContent.id}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${invoiceContent==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${STATIC_URL}/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/back/modules/invoice/js/content.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->