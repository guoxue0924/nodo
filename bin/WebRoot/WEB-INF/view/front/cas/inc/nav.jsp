<div class="AreaL">
    <div class="box">
        <div class="box_1">
            <div class="userCenterBox">
                <div class="userMenu">
                    <!--<a href="user.php"><img src="themes/default/images/u1.gif"> 欢迎页</a>-->
                    <a {if $nav_id==1 }class="curs"{/if} href="/cas/user/index"><img src="{$_STATIC_CDN}/front/img/u2.gif"> 用户信息</a>
                    <a {if $nav_id==2 }class="curs"{/if} href="/cas/order/index"><img src="{$_STATIC_CDN}/front/img/u3.gif"> 我的订单</a>
                    <a {if $nav_id==3 }class="curs"{/if} href="/cas/address/index"><img src="{$_STATIC_CDN}/front/img/u4.gif"> 收货地址</a>
                    <a {if $nav_id==4 }class="curs"{/if} href="/cas/favorite/index"><img src="{$_STATIC_CDN}/front/img/u5.gif"> 我的收藏</a>
                    <a {if $nav_id==5 }class="curs"{/if} href="/cas/message/index"><img src="{$_STATIC_CDN}/front/img/u6.gif">
                        我的留言</a>
                    <!--<a href="user.php?act=tag_list"><img src="themes/default/images/u7.gif"> 我的标签</a>-->
                    <!--<a href="user.php?act=booking_list"><img src="themes/default/images/u8.gif"> 缺货登记</a>-->
                    <!--<a href="user.php?act=bonus"><img src="themes/default/images/u9.gif"> 我的红包</a>-->
                    <a {if $nav_id==6 }class="curs" {/if} href="/cas/comment/index"><img src="{$_STATIC_CDN}/front/img/u11.gif"> 我的评论</a>
                    <!--<a href="user.php?act=group_buy">我的团购</a>-->
                    <!--<a href="user.php?act=track_packages"><img src="themes/default/images/u12.gif"> 跟踪包裹</a>-->
                    <!--<a href="user.php?act=account_log"><img src="themes/default/images/u13.gif"> 资金管理</a>-->
                    <a  style="background:none; text-align:right; margin-right:10px;" href="/cas/sign/out">
                        <img src="{$_STATIC_CDN}/front/img/bnt_sign.gif">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>