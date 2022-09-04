<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>咨询平台客服</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">

    <div class="ncm-flow-layout">
        <div id="ncmInformFlow" class="ncm-flow-container">
            <div class="title">
                <h3>咨询平台客服</h3>
            </div>
            <div class="ncm-flow-step">
                <dl class="step-first current">
                    <dt>填写咨询内容</dt>
                    <dd class="bg"></dd>
                </dl>
                <dl class="">
                    <dt>平台客服回复</dt>
                    <dd class="bg"></dd>
                </dl>
                <dl class="">
                    <dt>咨询完成</dt>
                    <dd class="bg"></dd>
                </dl>
            </div>
            <div class="ncm-default-form">
                <form id="mallconsultForm" method="post"
                      action="${BASE_URL}/front/cas/feedback/add">
                    <input type="hidden" name="form_submit" value="ok">
                    <dl>
                        <dt>咨询类型：</dt>
                        <dd>
                            <div>
	                            <select name="title" id="title">
	                                <option value="">请选择...</option>
	                                <option value="商品咨询">商品咨询</option>
	                                <option value="订单咨询">订单咨询</option>
	                            </select>
                                <span class="error"></span></div> 
                            <div>
                                <div></div>
                                <div style="display:none;">
                                	<p>
                                   	 	请写明商品链接，或平台货号。
                                	</p>
                                    <p>
                                       	如果您对商品规格、介绍等有疑问，可以在商品详情页“购买咨询”处发起咨询，会得到及时专业的回复。
                                    </p>
                                </div>
                                <div style="display:none;">
                                	<p>
                                    	请写明要咨询的订单编号。
                                	</p>
                                    <p>
                                    	如需处理交易中产生的纠纷，请选择投诉。
                                    </p>
                                </div>
                            </div>
                        </dd>
                    </dl>
                    <dl>
                        <dt>咨询内容：</dt>
                        <dd>
                            <textarea id="body" name="body" class="textarea w400"></textarea>
                            <br>
                            <span class="error"></span>
                        </dd>
                    </dl>
                    <div class="bottom">
                        <label class="submit-border">
                            <input type="button" class="submit" value="确认提交">
                        </label>
                        <a href="javascript:history.go(-1);" class="ncm-btn ml10">取消并返回</a></div>
                </form>
            </div>
        </div>
        <div class="ncm-flow-item">
            <div class="title">温馨提示</div>
            <div class="content">
                <div class="alert">
                    <ul>
                        <li> 1.如果您对商品规格、介绍等有疑问，可以在商品详情页“购买咨询”处发起咨询，会得到及时专业的回复；</li>
                        <li> 2.如需处理交易中产生的纠纷，请选择投诉；</li>
                        <li> 3.咨询时选择咨询类型，填写咨询内容（必填，不超过200字）。请尽量详细填写您要咨询的内容，以方便我们用最短的时间解决您的疑问。</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="{$_STATIC_CDN}/scripts/jquery-twbspagination/jquery.twbsPagination.js" type="text/javascript"></script>
<script src="{$_STATIC_CDN}/scripts/template/template.min.js" type="text/javascript"></script>
<script src="{$_STATIC_CDN}/cas/js/feedback.add.js" type="text/javascript"></script>
