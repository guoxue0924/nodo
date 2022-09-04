<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>账户信息</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul id="navTab" class="tab">

            </ul>
        </div>
        <table id="messageList" class="ncm-default-table">
            <thead>
            <tr>
                <th class="w30"></th>
                <th class="w100 tl">发信人</th>
                <th class="tl">内容</th>
                <th class="w300">最后更新</th>
                <th class="w110">操作</th>
            </tr>
            <tr>
                <td colspan="20"><input type="checkbox" id="all" class="checkall">
                    <label for="all">全选</label>
                    <a href="javascript:void(0)" class="ncm-btn-mini" uri="index.php?act=member_message&amp;op=dropbatchmsg&amp;drop_type=msg_system" name="message_id" confirm="您确实要删除该站内信吗?" nc_type="batchbutton">删除</a>
                </td>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
            <td colspan="20">
                <ul id="pagination" class="pagination-sm"></ul>
            </td>
            <input id="page" type="hidden" value="1"/>
            </tfoot>
        </table>
    </div>
</div>
        <div class="clear"></div>
</div>

<script src="${STATIC_URL}/front/modules/cas/js/message.index.js"></script>


<script id="messageTpl" type="text/html">
    <tr class="bd-line">
        <td class="tc"><input type="checkbox" class="checkitem" value="{{id}}"></td>
        <td class="tl">系统消息</td>
        <td class="link2 font_bold tl">您的平台客服咨询已经回复。<a href="/cas/message/detail?id={{id}}" target="_blank">点击查看回复</a></td>
        <td>{{ctime}}</td>
        <td class="ncm-table-handle">
            <span>
                <a href="/cas/message/detail?id={{id}}" class="btn-acidblue"><i class="icon-eye-open"></i><p>查看</p></a>
            </span>
            <span>
                <a href="javascript:void(0)" onclick="" class="btn-red">
                <i class="icon-trash"></i><p>删除</p>
                </a>
            </span>
        </td>
    </tr>
</script>
<script id="navTpl" type="text/html">
    <li class="active">
        <a href="">系统消息(<span style="color: red;">{{system}}</span>)</a>
    </li>
    <li class="normal">
        <a href="">平台消息(<span style="color: red;">{{admin}}</span>)</a>
    </li>
</script>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>
