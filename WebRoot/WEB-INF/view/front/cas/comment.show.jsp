<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>交易评价/晒单</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="ncm-flow-container">
    <div class="title"><a href="javascript:history.go(-1)" class="ncm-btn-mini fr"><i class="icon-reply"></i>返&nbsp;回</a>
        <h3>对商品进行晒单</h3>
    </div>
    <div class="alert">
        <h4>图片上传要求：</h4>
        请使用jpg\jpeg\png等格式、单张大小不超过1M的图片，最多可发布5张晒图，上传后的图片也将被保存在个人主页相册中以便其它使用。 </div>
    <form id="edit_form" action="${BASE_URL}/front/cas/comment/show" method="post">
        <div class=" ncm-default-form">
            <h3>评价信息</h3>
            <dl>
                <dt>商品信息：</dt>
                <dd>
                    <div>
                        <a href="${BASE_URL}/front/goods/detail?skuId=${item.skuId}" target="_blank"><img style="width:60px;height:60px" src="${IMG_URL}${detail.goodsImage}"/></a>
                        <a href="${BASE_URL}/front/goods/detail?skuId=${item.skuId}" target="_blank"></a>
                    </div>
                </dd>
            </dl>
            <dl>
                <dt>商品评分：</dt>
                <dd>
                    <div class="raty" style="display:inline-block;" data-score="${detail.rankBase}"></div>
                </dd>
            </dl>
            <dl>
                <dt>评价详情：</dt>
                <dd>${detail.content}</dd>
            </dl>
            <h3 class="mt20">上传晒单图片</h3>
            <div class="evaluation-image">
                <ul>
                	<c:forEach begin="1" end="5" step="1">
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
            <div class="bottom">
                <label class="submit-border">
                    <input id="btn_submit" class="submit" type="button" value="确定提交">
                </label>
            </div>
        </div>
        <input id="comment_id" type="hidden" name="id" value="${detail.id}"/>
    </form>
</div>
<div class="clear"></div>
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-fileupload/6.9.7/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-raty/jquery.raty.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/comment.show.js"></script>