<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>

<section class="vbox">
    <header class="header bg-white b-b clearfix">
        <div class="row m-t-sm">
            <div class="col-sm-8 m-b-xs">
                <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                <font class="h4 v-middle padder">${user.username}的个人档案</font>
            </div>
        </div>
    </header>
    
    <section class="scrollable">
        <section class="hbox stretch">
            <aside class="aside-lg bg-light lter b-r">
                <section class="vbox">
                    <section class="scrollable">
                        <div class="wrapper">
                            <div class="clearfix m-b">
                                <a href="#" class="pull-left thumb m-r">
                                    <img src="${IMG_URL}${user.avatar}" class="img-circle">
                                </a>
                                <div class="clear">
                                    <div class="h3 m-t-none m-b-xs">${user.username}</div>
                                    <small class="clearfix"><c:choose><c:when test="${user.realname != null}">真实姓名：${user.realname}</c:when><c:otherwise>昵称：${user.nickname}</c:otherwise></c:choose></small>
                                    <small class="text-muted"><i class="fa fa-map-marker"></i>  ${userDetail.regionName}</small>
                                </div>                
                            </div>
                            <div class="panel wrapper panel-success">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <a href="#">
                                            <span class="m-b-xs h4 block">${userOrderCount}</span>
                                            <small class="text-muted">订单</small>
                                        </a>
                                    </div>
                                    <div class="col-xs-4">
                                        <a href="#">
                                            <span class="m-b-xs h4 block">${userCartCount}</span>
                                            <small class="text-muted">购物车</small>
                                        </a>
                                    </div>
                                    <div class="col-xs-4">
                                        <a href="#">
                                            <span class="m-b-xs h4 block">${userCommentCount}</span>
                                            <small class="text-muted">收藏</small>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="btn-group btn-group-justified m-b">
                                <a class="btn btn-primary btn-rounded btn-send-msg" data-toggle="modal" data-target="#modal" data-backdrop="static" id="btn_pushmsg">
                                    <i class="fa fa-rocket"></i> 推送消息
                                </a>
                                <a class="btn btn-dark btn-rounded btn-send-msg" data-toggle="modal" data-target="#modal" data-backdrop="static" id="btn_sendnotify">
                                    <i class="fa fa-comment-o"></i> 站内信
                                </a>
                            </div>
                            <input type="hidden" id ="userid" value="${user.userid}"/>
                        </div>
                    </section>
                </section>
            </aside>
            
            <aside class="bg-white">
                <section class="vbox">
                    <header class="header bg-light bg-gradient">
                        <ul class="nav nav-tabs nav-white">
                            <li class="active"><a href="#userinfo_base" data-toggle="tab">基本资料</a></li>
                            <li class=""><a href="#userinfo_order" data-toggle="tab">订单记录</a></li>
                            <li class=""><a href="#userinfo_asset_balance" data-toggle="tab">资产记录</a></li>
                            <li class=""><a href="#userinfo_coupon" data-toggle="tab">优惠券记录</a></li>
                            <li class=""><a href="#userinfo_growup" data-toggle="tab">成长记录</a></li>
                            <li class=""><a href="#userinfo_notify" data-toggle="tab">消息记录</a></li>
                        </ul>
                    </header>
                    <section class="scrollable">
                        <div class="tab-content">
                            <!-- 基本资料 @start -->
                            <div class="tab-pane active" id="userinfo_base">
                                <jsp:include page="casUser.detail.base.jsp"/>
                            </div>
                            <!-- 基本资料 @end -->
                            
                            <!-- 订单记录 @start -->
                            <div class="tab-pane" id="userinfo_order">
                                <div class="text-center wrapper">
                                    <jsp:include page="casUser.detail.order.jsp"/>
                                </div>
                            </div>
                            <!-- 订单记录 @end -->
                            
                            <!-- 资产记录 @start -->
                            <div class="tab-pane" id="userinfo_asset_balance">
                                <div class="text-center wrapper">
                                    <jsp:include page="casUser.detail.asset.jsp"/>
                                </div>
                            </div>
                            <!-- 资产记录 @end -->
                            
                            <!-- 优惠券记录 @start -->
                            <div class="tab-pane" id="userinfo_coupon">
                                <div class="text-center wrapper">
                                    <jsp:include page="casUser.detail.coupon.jsp"/>
                                </div>
                            </div>
                            <!-- 优惠券记录 @end -->
                            
                            <!-- 成长值记录 @start -->
                            <div class="tab-pane" id="userinfo_growup">
                                <div class="text-center wrapper">
                                    <jsp:include page="casUser.detail.growup.jsp"/>
                                </div>
                            </div>
                            <!-- 成长值记录 @end -->
                            
                            <!-- 消息记录 @start -->
                            <div class="tab-pane" id="userinfo_notify">
                                <div class="text-center wrapper">
                                    <jsp:include page="casUser.detail.notify.jsp"/>
                                </div>
                            </div>
                            <!-- 消息记录 @end -->
                        </div>
                    </section>
                </section>
            </aside>
            
        </section>
    </section>
</section>

<!-- modal - 推送消息或发送站内信 -->
<div class="modal fade" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modal_title">添加规格</h4>
                <input type="text" style="display: none;" id="spec_content_id" value="" />
                <input type="text" style="display: none;" id="spec_is_del" value="" />
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="spec_sku" class="col-sm-2 control-label">SKU</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="spec_sku" name="spec_sku" value="" placeholder="请输入sku" />
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span class="edit_notice_modal"></span>
                <button type="button" class="btn btn-default btn-primary input-submit" id="submit_save_modal">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- / modal - 推送消息或发送站内信 -->

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/cas/js/casUser.detail.js" type="text/javascript"></script>

<%-- <!-- order -->
<script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/back/modules/cas/js/casUser.detail.order.js" type="text/javascript"></script>

<!-- category listing -->
<script src="${STATIC_URL}/back/modules/cas/js/casUser.detail.listing.js" type="text/javascript"></script> --%>
