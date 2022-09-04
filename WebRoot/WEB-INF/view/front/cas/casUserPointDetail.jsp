<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/datepicker/css/datepicker2.css"/>
<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>
<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>我的积分</span>
    </div>
</div>

<jsp:include page="../cas/user.nav.jsp"/>

<div class="wrap" style="margin-left:220px;">
  <div class="tabmenu">
    <ul class="tab pngFix">
  <li class="active"><a href="index.php?act=member_points">积分明细</a></li><li class="normal"><a href="index.php?act=member_pointorder&amp;op=orderlist">兑换记录</a></li></ul>
  </div>
  <div class="alert alert-block">
  <h4>积分获得规则</h4>
<ul><li>成功注册会员：增加<em>100</em>积分；会员每天登录：增加<em>50</em>积分；评价完成订单：增加<em>50</em>积分。</li>
<li>购物并付款成功后将获得订单总价5%（最高限额不超过800）积分。</li><li>如订单发生退款、退货等问题时，积分将不予退还。</li></ul>
  </div>
    <form method="get" action="${BASE_URL}/front/casUserPoint/index" id='searchForm'>
    <table class="ncm-search-table">
      <!-- <input type="hidden" name="act" value="member_points"> -->
      <tbody><tr><td class="w10">&nbsp;</td>
        <td><strong>积分总数：</strong><strong style="color: #F00;" id="totlePoint"></strong></td>        
        <th>添加时间</th>
        <td class="w240">
        <input type="text" class="text w70 " name="startDate" id="startDate" readonly="readonly"><label class="add-on"><i class="icon-calendar"></i></label>
                    		&nbsp;–&nbsp;
        <input type="text" class="text w70 " name="endDate" id="endDate" readonly="readonly"><label class="add-on"><i class="icon-calendar"></i></label>
        <th>操作</th>
        <td class="w100"><select name="stage" id="stage">
            <option value="" selected="selected">请选择...</option>
            <option value="1">添加</option>
            <option value="4">消耗</option>
          </select></td>
        <th>描述</th>
        <td class="w160"><input type="text" class="text w150" id="description" name="description" value=""></td>
        <td class="w70 tc"><label class="submit-border">
            <input type="button" class="submit" value="搜索">
          </label></td>
      </tr>
    </tbody></table>
  </form>
  <table id="consigneeList" class="ncm-default-table" style="float:left;">
    <thead>
      <tr>
        <th class="w200">添加时间</th>
        <th class="w150">积分变更</th>
        <th class="w300">操作</th>
        <th class="tl">描述</th>
      </tr>
    </thead>
    <tbody>
                 
                </tbody>
    <tfoot>
                <tr>
                    <td colspan="19"><div><ul id="paginationBar" class="pagination-sm pagination"></ul> </div></td>
                </tr>
            </tfoot>
  </table>
</div>

  <div class="clear"></div>
  
<script type="text/javascript" src="${STATIC_URL}/front/modules/cas/js/casUserPointDetail.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN-2.js"></script>
<script src="${STATIC_URL}/plugins/jquery-validate/1.9/jquery.validation.min.js"></script>
<script src="${STATIC_URL}/front/modules/cas/js/common_select.js"></script>
<script id="consigneeTpl" type="text/html">

                  <tr class="bd-line">
        <td class="goods-time">{{ctime}}</td>
        <td class="goods-price">+{{point}}</td>
			<td>{{pointType}}</td>
        <td class="tl">{{pointName}}</td>
      </tr>
</script>
<script id="consigneeTplmin" type="text/html">

                  <tr class="bd-line">
        <td class="goods-time">{{ctime}}</td>
        <td class="goods-price">{{point}}</td>
			<td>{{pointType}}</td>
        <td class="tl">{{pointName}}</td>
      </tr>
</script>
<jsp:include page="../index/wrapper.suffix_front.jsp"/>
