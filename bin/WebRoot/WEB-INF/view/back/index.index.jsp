<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="wrapper.prefix.jsp"/>

<section class="vbox">          
    <header class="header bg-white b-b clearfix">
        <p class="h4">Welcome, ${loggedInUser.username}</p>
    </header>
    
    <section class="scrollable wrapper w-f">
      <div class="clearfix">
            <div class="row m-b-xl padder">
                <div class="col-sm-3 animated fadeInLeftBig">
                    <section class="panel b-light text-center m-t">
                        <header class="panel-heading bg-white b-light">
                            <div class="row m-l-none m-r-none bg-light lter">
                                <div class="padder-v b-light">
                                    <span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
                                        <i class="fa fa-circle fa-stack-2x text-info"></i>
                                        <i class="fa fa-male fa-stack-1x text-white"></i>
                                    </span>
                                    <a class="clear text-left padder-l-md" href="${BASE_URL}/back/casuser/index">
                                        <span class="h3 block m-t-xs"><strong>${totalUser}</strong></span>
                                        <small class="text-muted text-uc">注册会员数</small>
                                    </a>
                                </div>
                            </div>
                        </header>
                        <ul class="list-group">
                            <li class="list-group-item">
                                一天以来新增注册用户数：<a href="${BASE_URL}/back/casuser/index" class="text-danger font-bold">${oneDayRegister}</a> 个
                            </li>
                            <li class="list-group-item">
                                一周以来新增注册用户数：<a href="${BASE_URL}/back/casuser/index" class="text-danger font-bold">${oneWeekRegister}</a> 个
                            </li>
                            <li class="list-group-item">
                                一月以来新增注册用户数：<a href="${BASE_URL}/back/casuser/index" class="text-danger font-bold">${oneMonthRegister}</a> 个
                            </li>
                            <li class="list-group-item">
                                今日会员登录数：<a href="#" class="text-danger font-bold">${todayLogin}</a> 个
                            </li>
                        </ul>
                        <footer class="panel-footer">
                            <a class="btn btn-primary m-t m-b" href="${BASE_URL}/back/casuser/index">查看全部会员信息</a>
                        </footer>
                    </section>
                </div>
                
                <div class="col-sm-3 animated fadeInUp">
                    <section class="panel b-light text-center m-t">
                        <header class="panel-heading bg-white b-light">
                            <div class="row m-l-none m-r-none">
                                <div class="padder-v b-light">
                                    <span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
                                        <i class="fa fa-circle fa-stack-2x text-warning"></i>
                                        <i class="fa fa-file-o fa-stack-1x text-white"></i>
                                    </span>
                                    <a class="clear text-left padder-l-md" href="${BASE_URL}/back/order/index">
                                        <span class="h3 block m-t-xs"><strong>${totalOrder}</strong></span>
                                        <small class="text-muted text-uc">订单总数</small>
                                    </a>
                                </div>
                            </div>
                        </header>
                        <ul class="list-group">
                            <li class="list-group-item">
                                未处理订单：<a href="${BASE_URL}/back/order/index" class="text-danger font-bold">${waitProcessOrder}</a> 个
                            </li>
                            <li class="list-group-item">
                                待付款订单：<a href="#" class="text-danger font-bold">${waitPayOrder}</a> 个
                            </li>
                            <li class="list-group-item">
                                待发货订单：<a href="${BASE_URL}/back/order/index" class="text-danger font-bold">${waitDeliveryOrder}</a> 个
                            </li>
                            <li class="list-group-item">
                                退货订单：<a href="${BASE_URL}/back/order/index" class="text-danger font-bold">${refundOrder}</a> 个
                            </li>
                        </ul>
                        <footer class="panel-footer">
                            <a class="btn btn-primary m-t m-b" href="${BASE_URL}/back/order/index">查看所有订单信息</a>
                        </footer>
                    </section>
                </div>
                
                <div class="col-sm-3 animated fadeInDown">
                    <section class="panel b-light text-center m-t">
                        <header class="panel-heading bg-white b-light">
                            <div class="row m-l-none m-r-none">
                                <div class="padder-v b-light">
                                    <span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
                                        <i class="fa fa-circle fa-stack-2x text-danger"></i>
                                        <i class="fa fa-shopping-cart fa-stack-1x text-white"></i>
                                    </span>
                                    <a class="clear text-left padder-l-md" href="${BASE_URL}/back/goodsContent/index">
                                        <span class="h3 block m-t-xs"><strong>${totalGoods}</strong></span>
                                        <small class="text-muted text-uc">商品总数</small>
                                    </a>
                                </div>
                            </div>
                        </header>
                        <ul class="list-group">
                            <li class="list-group-item">
                                今日上架商品数：<a href="${BASE_URL}/back/goodsContent/index" class="text-danger font-bold">${todayOnSellGoods}</a> 个
                            </li>
                            <li class="list-group-item">
                                已上架商品数：<a href="${BASE_URL}/back/goodsContent/index" class="text-danger font-bold">${onSellGoods}</a> 个
                            </li>
                            <li class="list-group-item">
                                已下架商品数：<a href="${BASE_URL}/back/goodsContent/index" class="text-danger font-bold">${offSellGoods}</a> 个
                            </li>
                            <li class="list-group-item">
<!--                                 商品库存报警：<a href="#" class="text-danger font-bold">86</a> 个 -->
									&nbsp;&nbsp;&nbsp;
                            </li>
                        </ul>
                        <footer class="panel-footer">
                            <a class="btn btn-primary m-t m-b" href="${BASE_URL}/back/goodsContent/index">查看所有商品信息</a>
                        </footer>
                    </section>
                </div>
                
                <div class="col-sm-3 animated fadeInRightBig">
                    <section class="panel b-light text-center m-t">
                        <header class="panel-heading bg-white b-light">
                            <div class="row m-l-none m-r-none">
                                <div class="padder-v b-light">
                                    <span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
                                        <i class="fa fa-circle fa-stack-2x text-success"></i>
                                        <i class="fa fa-question-circle fa-stack-1x text-white"></i>
                                    </span>
                                    <a class="clear text-left padder-l-md m-t-sm padder-t-xs padder-b-xs" href="#">
                                        <span class="h3 block m-t-xs"><strong>使用帮助</strong></span>
                                    </a>
                                </div>
                            </div>
                        </header>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="#">常见问题</a>
                            </li>
                            <li class="list-group-item">
                                <a href="#">如何添加商品</a>
                            </li>
                            <li class="list-group-item">
                                <a href="#">如何添加商品属性</a>
                            </li>
                            <li class="list-group-item">
                                <a href="#">会员等级与积分规则说明</a>
                            </li>
                        </ul>
                        <footer class="panel-footer">
                            <a class="btn btn-primary m-t m-b" href="#">查看所有帮助信息</a>
                        </footer>
                    </section>
                </div>
            </div>
        </div>
        
        <div class="row padder">
            <div class="col-md-6">
                <section class="panel panel-default">
                    <header class="panel-heading font-bold">销量统计</header>
                    <div class="panel-body">
                        <div style="height: 210px; padding: 0px; position: relative;" id="flot-1ine"><canvas class="flot-base" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 745px; height: 210px;" width="745" height="210"></canvas><div class="flot-text" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);"><div class="flot-x-axis flot-x1-axis xAxis x1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div style="position: absolute; max-width: 62px; top: 195px; left: 14px; text-align: center;" class="flot-tick-label tickLabel">0</div><div style="position: absolute; max-width: 62px; top: 195px; left: 79px; text-align: center;" class="flot-tick-label tickLabel">1</div><div style="position: absolute; max-width: 62px; top: 195px; left: 144px; text-align: center;" class="flot-tick-label tickLabel">2</div><div style="position: absolute; max-width: 62px; top: 195px; left: 209px; text-align: center;" class="flot-tick-label tickLabel">3</div><div style="position: absolute; max-width: 62px; top: 195px; left: 274px; text-align: center;" class="flot-tick-label tickLabel">4</div><div style="position: absolute; max-width: 62px; top: 195px; left: 339px; text-align: center;" class="flot-tick-label tickLabel">5</div><div style="position: absolute; max-width: 62px; top: 195px; left: 405px; text-align: center;" class="flot-tick-label tickLabel">6</div><div style="position: absolute; max-width: 62px; top: 195px; left: 470px; text-align: center;" class="flot-tick-label tickLabel">7</div><div style="position: absolute; max-width: 62px; top: 195px; left: 535px; text-align: center;" class="flot-tick-label tickLabel">8</div><div style="position: absolute; max-width: 62px; top: 195px; left: 600px; text-align: center;" class="flot-tick-label tickLabel">9</div><div style="position: absolute; max-width: 62px; top: 195px; left: 662px; text-align: center;" class="flot-tick-label tickLabel">10</div><div style="position: absolute; max-width: 62px; top: 195px; left: 728px; text-align: center;" class="flot-tick-label tickLabel">11</div></div><div class="flot-y-axis flot-y1-axis yAxis y1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div style="position: absolute; top: 183px; left: 6px; text-align: right;" class="flot-tick-label tickLabel">0</div><div style="position: absolute; top: 147px; left: 6px; text-align: right;" class="flot-tick-label tickLabel">5</div><div style="position: absolute; top: 112px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">10</div><div style="position: absolute; top: 76px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">15</div><div style="position: absolute; top: 41px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">20</div><div style="position: absolute; top: 5px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">25</div></div></div><canvas class="flot-overlay" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 745px; height: 210px;" width="745" height="210"></canvas></div>
                    </div>
                    <footer class="panel-footer bg-white no-padder">
                        <div class="row text-center no-gutter">
                            <div class="col-xs-3 b-r b-light">
                                <span class="h4 font-bold m-t block">5,860</span>
                                <small class="text-muted m-b block">Orders</small>
                            </div>
                            <div class="col-xs-3 b-r b-light">
                                <span class="h4 font-bold m-t block">10,450</span>
                                <small class="text-muted m-b block">Sellings</small>
                            </div>
                            <div class="col-xs-3 b-r b-light">
                                <span class="h4 font-bold m-t block">21,230</span>
                                <small class="text-muted m-b block">Items</small>
                            </div>
                            <div class="col-xs-3">
                                <span class="h4 font-bold m-t block">7,230</span>
                                <small class="text-muted m-b block">Customers</small>
                            </div>
                        </div>
                    </footer>
                </section>
            </div>
            <div class="col-md-6">
                <section class="panel panel-default">
                    <header class="panel-heading font-bold">销售额统计</header>
                    <div class="bg-light dk wrapper">
                        <span class="pull-right">Friday</span>
                        <span class="h4">$540<br>
                            <small class="text-muted">+1.05(2.15%)</small>
                        </span>
                        <div class="text-center m-b-n m-t-sm">
                            <div values="280,320,220,385,450,320,345,250,250,250,400,380" data-resize="true" data-spot-radius="3" data-highlight-line-color="#fff" data-fill-color="" data-spot-color="#bbbbbb" data-line-color="#dddddd" data-line-width="2" data-width="100%" data-height="65" data-type="line" class="sparkline"><canvas style="display: inline-block; width: 342px; height: 65px; vertical-align: top;" width="342" height="65"></canvas></div>
                            <div data-bar-color="#65bd77" data-bar-spacing="6" data-bar-width="6" data-height="45" data-type="bar" class="sparkline inline"><canvas style="display: inline-block; width: 150px; height: 45px; vertical-align: top;" width="150" height="45"></canvas></div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div>
                            <span class="text-muted">Total:</span>
                            <span class="h3 block">$2500.00</span>
                        </div>
                        <div class="line pull-in"></div>
                        <div class="row m-t-sm">
                            <div class="col-xs-4">
                                <small class="text-muted block">Market</small>
                                <span>$1500.00</span>
                            </div>
                            <div class="col-xs-4">
                                <small class="text-muted block">Referal</small>
                                <span>$600.00</span>
                            </div>
                            <div class="col-xs-4">
                                <small class="text-muted block">Affiliate</small>
                                <span>$400.00</span>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    
    </section>
</section>

<jsp:include page="wrapper.suffix.jsp"/>
