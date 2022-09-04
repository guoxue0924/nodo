<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="inc/header.jsp"/>

<!-- {literal} -->
<script type="text/javascript">
function countDown(secs) {
    $('#countdown').text(secs);
    if (--secs > 0) {
        setTimeout("countDown(" + secs + ")", 1000);
    } else {
        window.location.href = url;
    }
}
</script>
<!-- {/literal} -->

<div id="Security_Center">
    <div class="sys-war">
        <p class="sw-txt deep-yellow f18 soft-black">
            <a href="{if $prompt.url}${prompt.url}{else}#{/if}">${prompt.msg}</a>
            {if $prompt.url and $prompt.autoredirect}
            <br /><br />
            <span class="c666 f13">
                <a href="{if $prompt.url}${prompt.url}{else}#{/if}">浏览器将在<em id="countdown">${prompt.countdown}</em>秒后自动跳转</a>
            </span>
            <script type="text/javascript">var url="${prompt.url}";countDown(${prompt.countdown});</script>
            {/if}
        </p>
    </div><!--/sc-safelv-->
</div><!--/Security_Center-->

<jsp:include page="inc/footer.jsp"/>