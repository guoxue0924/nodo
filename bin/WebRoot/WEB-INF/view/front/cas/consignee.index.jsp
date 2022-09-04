<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>收货地址</span>
    </div>
</div>

<jsp:include page="user.nav.jsp"/>

<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="active">
                    <a href="/cas/consignee">地址列表</a>
                </li>
            </ul>
            <a href="javascript:void(0)" class="ncm-btn ncm-btn-orange" nc_type="dialog" dialog_title="新增地址"
               dialog_id="my_address_edit" uri="${BASE_URL}/front/cas/consignee/add" dialog_width="550" title="新增地址"><i
                    class="icon-map-marker"></i>新增地址</a>
        </div>
        <div class="alert alert-success">
            <h4>操作提示：</h4>
            <ul>
                <li>最多可保存20个有效地址</li>
            </ul>
        </div>
        <table id="consigneeList" class="ncm-default-table">
            <thead>
            <tr>
                <th class="w80">收货人</th>
                <th class="w150">所在地区</th>
                <th class="tl">街道地址</th>
                <th class="w120">电话/手机</th>
                <th class="w150"></th>
                <th class="w110">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <!-- <tfoot>
	            <td colspan="20">
	                <ul id="pagination" class="pagination-sm"></ul>
	            </td>
                <input id="pageIndex" type="hidden" value="1"/>
                <input id="pageSize" type="hidden" value="5"/>
            </tfoot> -->
            <tfoot>
                <tr>
                    <td colspan="19"><div><ul id="paginationBar" class="pagination-sm pagination"></ul> </div></td>
                </tr>
            </tfoot>
            
        </table>
    </div>
</div>
<div class="clear"></div>
</div>
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/consignee.index.js"></script>
<link rel="stylesheet" href="${STATIC_URL}/plugins/dialog/dialog.css"/>
<script src="${STATIC_URL}/plugins/dialog/dialog.js" id="dialog_js"></script>

<script id="consigneeTpl" type="text/html">
    <tr class="bd-line">
        <td>{{name}}</td>
        <td>{{regionName}}</td>
        <td class="tl"><em class="delivery"></em>{{address}}</td>
        <td><p><i class="icon-phone"></i>{{tel}}</p>

            <p><i class="icon-mobile-phone"></i>{{mobile}}</p></td>
        <td></td>
        <td class="ncm-table-handle"><span>
                    <a href="javascript:void(0);" class="btn-blue" dialog_id="my_address_edit" dialog_width="550"
                       dialog_title="编辑地址" nc_type="dialog" uri="${BASE_URL}/front/cas/consignee/edit?consigneeId={{consigneeId}}"><i
                            class="icon-edit"></i>

                        <p>编辑</p>
                    </a>
                    </span> <span><a href="javascript:void(0)" class="btn-red delete" data-id="{{consigneeId}}"><i class="icon-trash"></i>

            <p>删除</p>
        </a></span></td>
    </tr>
</script>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>