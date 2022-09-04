<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="/index.php">首页</a></span><span class="arrow">&gt;</span>
        <span>我的商城</span><span class="arrow">&gt;</span>
        <span>账户信息</span>
    </div>
</div>
<jsp:include page="user.nav.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/Jcrop/0.9.8/js/jquery.Jcrop.js"></script>
<link href="${STATIC_URL}/plugins/Jcrop/0.9.8/css/jquery.Jcrop.css" rel="stylesheet" type="text/css" id="cssfile2">
<div class="right-layout">
<div class="wrap">
    <form action="${BASE_URL}/front/cas/cutAndSave" id="form_cut" method="post">
        <input type="hidden" name="form_submit" value="ok" />
        <input type="hidden" id="x1" name="x1" />
        <input type="hidden" id="x2" name="x2" />
        <input type="hidden" id="w" name="width" />
        <input type="hidden" id="y1" name="y1" />
        <input type="hidden" id="y2" name="y2" />
        <input type="hidden" id="h" name="height" />
        <input type="hidden" id="multiple" name="multiple" />
        <input type="hidden" id="imageUrl" name="imageUrl" value="${tempImage}" />
        <div class="pic-cut-120">
            <div class="work-title">工作区域</div>
            <div class="work-layer" id="test">
                <p><img id="nccropbox" src="${TEMP_IMG_URL}${tempImage}"/></p>
            </div>
            <div class="thumb-title">裁切预览</div>
            <div class="thumb-layer">
                <p><img id="preview" src="${TEMP_IMG_URL}${tempImage}"/></p>
            </div>
            <div class="cut-help">
                <h4>操作帮助</h4>
                <p>请在工作区域放大缩小及移动选取框，选择要裁剪的范围，裁切宽高比例固定；裁切后的效果为右侧预览图所显示；保存提交后生效。</p>
            </div>
            <div class="cut-btn">
                <input type="button" id="ncsubmit" class="submit" value="提交" />
            </div>
        </div>
    </form>
</div>

</div>
<div class="clear"></div>
<script src="${STATIC_URL}/front/modules/cas/js/account.avatarupload.js"></script>