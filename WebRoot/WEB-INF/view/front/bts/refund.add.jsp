<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span>我的商城</span><span class="arrow">&gt;</span>
        <span>实物交易订单</span>
    </div>
</div>

<jsp:include page="../cas/user.nav.jsp"/>


<div class="right-layout">
<div class="ncm-flow-layout">
<div class="ncm-flow-container">
    <div class="title">
        <h3>申请退款服务类型：</h3>
        <div class="refund-type-box">
            <div class="refund-type-text" data-value="2">退货退款</div>
        </div>
    </div>
    <div id="saleRefund" show_id="1">
        <div class="ncm-flow-step">
            <dl class="step-first current">
                <dt>买家申请退款</dt>
                <dd class="bg"></dd>
            </dl>
            <dl class="">
                <dt>商家处理退款申请</dt>
                <dd class="bg"> </dd>
            </dl>
            <dl class="">
                <dt>平台审核，退款完成</dt>
                <dd class="bg"> </dd>
            </dl>
        </div>
        <div class=" ncm-default-form">
            <div id="warning"></div>
            <form id="refundForm" method="post" enctype="multipart/form-data" action="${BASE_URL}/front/bts/refund/add">
                <input type="hidden" name="form_submit" value="ok">
                <input type="hidden" name="refund_type" value="2">
                <h3>请填写退货退款申请</h3>
                <dl>
                    <dt><i class="required">*</i>退货退款原因：</dt>
                    <dd>
                        <select class="select w150" name="reasonType">
                            <option value="0">请选择退货退款原因</option>
                            <option value="1">不能按时发货</option>
                            <option value="2">认为是假货</option>
                            <option value="3">保质期不符</option>
                            <option value="4">商品破损、有污渍</option>
                            <option value="5">效果不好不喜欢</option>
                            <option value="6">其他</option>
                        </select>
                        <span class="error"></span> </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>退货数量：</dt>
                    <dd>
                        <input type="text" class="text w50" name="goodsNum" value="1" data-max="${item.buyNum}">
                        （最多 <strong class="green" title="可退货数量">${item.buyNum}</strong> 件）
                        <span class="error"></span>
                        <p class="hint">退货矢量不能超过可退数量。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><i class="required">*</i>退货退款说明：</dt>
                    <dd>
                        <textarea name="reason" rows="3" class="textarea w400"></textarea>
                        <br>
                        <span class="error"></span> </dd>
                </dl>
                <dl>
                    <dt>上传凭证：</dt>
                    <dd>
                        <div class="evaluation-image">
			                <ul>
			                	<c:forEach begin="1" end="3" step="1">
				                    <li>
			                        	<div class="upload-thumb">
			                            	<div nctype="image_item" style="display:none;">
			                                	<img src="">
			                                	<input type="checkbox" nctype="input_image" style="display: none;" name="imageIds" value="">
			                                	<a href="javascript:;" nctype="del" class="del" title="移除">X</a> </div>
			                        	</div>
			                        	<div class="upload-btn">
			                        		<a href="javascript:void(0);">
			                        			<span>
			                						<input type="file" hidefocus="true" size="1" class="input-file" name="file">
			                					</span>
			                            		<p>图片上传</p>
			                        		</a>
			                        	</div>
			                    	</li>
			                    </c:forEach>
			                </ul>
			            </div>
                    </dd>
                </dl>
                <div class="bottom"> 
                    <label class="submit-border">
                        <input type="button" class="submit" value="确认提交">
                    </label>
                    <a href="javascript:history.go(-1);" class="ncm-btn ml10">取消并返回</a>
                </div>
                <input name="orderId" value="${item.orderId}" type="hidden"/>
                <input name="itemId" value="${item.itemId}" type="hidden"/>
                <input name="orderNumber" value="${item.orderNumber}" type="hidden"/>
            </form>
        </div>
    </div>
</div>

<div class="ncm-flow-item">
    <div class="title">相关商品交易信息</div>
    <div class="item-goods">
        <dl>
            <dt>
	            <div class="ncm-goods-thumb-mini">
	            	<a target="_blank" href="">
	                	<img src="${IMG_URL}${item.goodsImage}" onmouseover="toolTip('<img src=${IMG_URL}${item.goodsImage}>')" 
	                	onmouseout="toolTip()">
	                </a>
	            </div>
            </dt>
            <dd>
            	<a target="_blank" href="">${item.goodsName}</a>
            	<fmt:formatNumber value="${item.buyPrice}" pattern="￥#,#00.00#" /> * ${item.buyNum}
            	<font color="#AAA">(数量)</font>
                <span></span>
            </dd>
        </dl>
    </div>
    <div class="item-order">
        <dl>
            <dt>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</dt>
            <dd>（免运费）</dd>
        </dl>
        <dl>
            <dt>订单总额：</dt>
            <dd><strong><fmt:formatNumber value="${item.totalAmount}" pattern="￥#,#00.00#"/></strong> </dd>
        </dl>
        <dl class="line">
            <dt>订单编号：</dt>
            <dd>
            	<a target="_blank" href="${BASE_URL}/front/bts/order/detail?orderId=${order.orderId}">${item.orderNumber}</a>
                <a href="javascript:void(0);" class="a">更多<i class="icon-angle-down"></i>
                    <div class="more"> <span class="arrow"></span>
                        <ul>
<!--                             <li>付款单号：<span></span></li> -->
                            <li>支付方式：<span>在线付款</span></li>
                            <li>下单时间：<span><fmt:formatDate value="${item.ctime}" pattern="yyyy-MM-dd hh:mm:ss"/></span></li>
                        </ul>
                    </div>
                </a>
            </dd>
        </dl>
    </div>
</div>
</div>
</div>
<div class="clear"></div>
</div>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/jquery.fileupload.js"></script>
<script src="${STATIC_URL}/front/modules/bts/js/refund.add.js"></script>