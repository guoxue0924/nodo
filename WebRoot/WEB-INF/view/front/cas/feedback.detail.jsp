{$wrapper_prefix_front|default}

<script src="{$_STATIC_CDN}/scripts/jquery-twbspagination/jquery.twbsPagination.js" type="text/javascript"></script>
<script src="{$_STATIC_CDN}/scripts/template/template.min.js" type="text/javascript"></script>
<script src="{$_STATIC_CDN}/cas/js/feedback.index.js" type="text/javascript"></script>
<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="/index.php">首页</a></span><span class="arrow">&gt;</span>
        <span>我的商城</span><span class="arrow">&gt;</span>
        <span>实物交易订单</span>
    </div>
</div>
{include file="cas/template/user.nav.html"}
<div class="right-layout">

    <div class="ncm-flow-layout">
        <div id="ncmInformFlow" class="ncm-flow-container">
            <div class="title">
                <h3>咨询平台客服</h3>
            </div>
            <div class="ncm-flow-step">
                <dl class="step-first {if $feedback.status==0}current{/if}">
                    <dt>填写咨询内容</dt>
                    <dd class="bg"></dd>
                </dl>
                <dl class="{if $feedback.status==1}current{/if}">
                    <dt>平台客服回复</dt>
                    <dd class="bg"></dd>
                </dl>
            </div>
            <div class="ncm-default-form">
                <dl>
                    <dt>咨询类型：</dt>
                    <dd>
                        {$feedback.title}
                    </dd>
                </dl>
                <dl>
                    <dt>咨询内容：</dt>
                    <dd>
                        {$feedback.body}
                    </dd>
                </dl>
                <dl>
                    <dt>咨询时间：</dt>
                    <dd>
                        {$feedback.ctime}
                    </dd>
                </dl>
                <dl>
                    <dt>回复状态：</dt>
                    <dd>
                        {if $feedback.status==1}已回复{else}未回复{/if}
                    </dd>
                </dl>
                <dl>
                    <dt>回复内容：</dt>
                    <dd>
                        {$feedback.reply}
                    </dd>
                </dl>
                <div class="bottom"><a href="javascript:history.go(-1);" class="ncm-btn ml10">返回列表</a></div>
            </div>
        </div>
        <div class="ncm-flow-item">
            <div class="title">温馨提示</div>
            <div class="content">
                <div class="alert">
                    <ul>
                        <li> 1.如果您对商品规格、介绍等有疑问，可以在商品详情页“购买咨询”处发起咨询，会得到及时专业的回复；</li>
                        <li> 2.如需处理交易中产生的纠纷，请选择投诉；</li>
                        <li> 3.举报时依次选择咨询类型，填写违规描述（必填，不超过200字）。请尽量详细填写您要咨询的内容，以方便我们用最短的时间解决您的疑问。</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

