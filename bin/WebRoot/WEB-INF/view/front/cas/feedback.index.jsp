<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>平台客服</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">

    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active"><a
                        href="${BASE_URL}/front/cas/feedback/index">平台客服咨询列表</a>
                </li>
            </ul>
            <a class="ncm-btn ncm-btn-orange"
               href="${BASE_URL}/front/cas/add"><i
                    class="icon-comments-alt"></i>平台客服</a></div>
        <table id="feedbackList" class="ncm-default-table">
            <thead>
            <tr>
                <th class="w10"></th>
                <th class="tl">咨询内容</th>
                <th class="w150">咨询时间</th>
                <th class="w150">状态</th>
                <th class="w110">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="20">
                    <ul id="pagination" class="pagination-sm"></ul>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
<script id="feedbackTpl" type="text/html">
    <tr class="bd-line">
        <td></td>
        <td class="tl">{{body}}</td>
        <td>{{ctime}}</td>
        <td class="">{{if status==1}}已回复{{else}}未回复{{/if}}</td>
        <td class="ncm-table-handle">
                        <span><a
                                href="/cas/feedback/detail?content_id={{content_id}}"
                                class="btn-blue"><i class="icon-eye-open"></i>
                            <p>查看</p></a></span>
                        <span><a href="javascript:void(0);" data-content_id="{{content_id}}" class="btn-blue deleteBtn"><i
                                class="icon-trash"></i>
                            <p>删除</p></a></span>
        </td>
    </tr>
</script>

<script id="feedbackNoneTpl" type="text/html">
    <tr>
        <td colspan="20" class="norecord">
            <div class="warning-option"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></div>
        </td>
    </tr>
</script>

<script src="${STATIC_URL}/plugins/jquery-twbspagination/jquery.twbsPagination.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/template/template.min.js" type="text/javascript"></script>
<script src="${STATIC_URL}/front/modules/cas/js/feedback.index.js" type="text/javascript"></script>

