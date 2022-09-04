<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>账户安全</span>
    </div>
</div>

<jsp:include page="../cas/user.nav.jsp"/>

<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active"><a href="">公告详情</a></li>
            </ul>
        </div>
        <div  style="#FFF; padding: 5px; margin-bottom: 30px; margin-top: 50px;" >
           
				${content.body }
            
        </div>
    </div>
</div>
<div class="clear"></div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>