<!-- .aside -->
<aside class="lter aside-md" id="nav">
    <section class="vbox">
        <section class="scrollable wrapper-sm">
            <p class="h5 padder">欢迎您：{if $loggedInUser.user_group_id == 2}{$loggedInUser.company_name}{else}{$loggedInUser.realname}{/if}</p>

            <div class="list-group">
                <a href="javascript:;" class="list-group-item active">通用</a>
                <a href="/btscas/order" class="list-group-item nav-class">注册</a>
                <a href="/cas" class="list-group-item nav-class">登录</a>
                <a href="javascript:;" class="list-group-item nav-class">找回密码</a>
            </div>

            <div class="list-group">
                <a href="javascript:;" class="list-group-item active">我的信息</a>
                <a href="/btscas/order" class="list-group-item nav-class">个人信息</a>
                <a href="javascript:;" class="list-group-item nav-class">账户安全</a>
                <a href="javascript:;" class="list-group-item nav-class">我的级别</a>
                <a href="/cas/consignee" class="list-group-item nav-class">收货地址</a>
                <a href="javascript:;" class="list-group-item nav-class">消费记录</a>
            </div>

            <div class="list-group">
                <a href="javascript:;" class="list-group-item active">我的交易</a>
                <a href="/btscas/order" class="list-group-item nav-class">我的订单</a>
                <a href="javascript:;" class="list-group-item nav-class">我的团购</a>
            </div>

            <div class="list-group">
                <a href="javascript:;" class="list-group-item active">我的关注</a>
                <a href="javascript:;" class="list-group-item nav-class">关注的商品</a>
                <a href="javascript:;" class="list-group-item nav-class">关注的店铺</a>
                <a href="javascript:;" class="list-group-item nav-class">关注的活动</a>
                <a href="javascript:;" class="list-group-item nav-class">浏览历史</a>
            </div>

            <div class="list-group">
                <a href="javascript:;" class="list-group-item active">我的资产</a>
                <a href="javascript:;" class="list-group-item nav-class">我的余额</a>
                <a href="javascript:;" class="list-group-item nav-class">我的优惠券</a>
            </div>

            <div class="list-group">
                <a href="javascript:;" class="list-group-item active">客户服务</a>
                <a href="javascript:;" class="list-group-item nav-class">返修退换货</a>
                <a href="javascript:;" class="list-group-item nav-class">取消订单记录</a>
                <a href="javascript:;" class="list-group-item nav-class">我的投诉</a>
            </div>
            
        </section>
    </section>
</aside>
<!-- /.aside -->