/**
 * 定义一些全局变量
 */
/* 消息模板 - 左 */
var template_left = '<article class="chat-item left">' + 
        '<a class="pull-left thumb-sm avatar" href="javascript:;">' + 
        '<img class="img-circle" src="{#avatar#}" />' + 
        '</a>' + 
        '<section class="chat-body">' + 
        '<div class="panel b-light text-sm m-b-none">' + 
        '<div class="panel-body">' + 
        '<span class="arrow left"></span>' + 
        '<p class="m-b-none">{#content#}</p>' + 
        '</div>' + 
        '</div>' + 
        '<small class="text-muted">{#ctime#}</small>' + 
        '</section>' + 
        '</article>';

/* 消息模板 - 右 */
var template_right = '<article class="chat-item right">' + 
        '<a class="pull-right thumb-sm avatar" href="javascript:;">' + 
        '<img class="img-circle" src="{#avatar#}" />' + 
        '</a>' + 
        '<section class="chat-body">' + 
        '<div class="panel bg-success text-sm m-b-none">' + 
        '<div class="panel-body">' + 
        '<span class="arrow right"></span>' + 
        '<p class="m-b-none">{#content#}</p>' + 
        '</div>' + 
        '</div>' + 
        '<small class="text-muted">{#ctime#}</small>' + 
        '</section>' + 
        '</article>';

/* 联系人模板 */
var template_contact = '<div class="list-group-item panel-body wrapper-sm cursor-pointer" userid="{#userid#}" gender="{#gender#}">' +  
        '<a href="javascript:;" class="pull-left thumb-sm avatar">' + 
        '<img src="{#avatar#}" class="img-circle"> ' + 
        '<span class="text-nm">{#username#}</span>' + 
        '</a>' + 
        '<div class="clear text-right">' + 
        '<span class="block text-snm last-message-ctime">{#last-message-ctime#}</span>' + 
        '<span class="badge badge-sm bg-danger last-message-count">{#last-message-count#}</span>' + 
        '</div>' + 
        '</div>';

$(document).ready(function() {
	
	$('#content_listing_message').masonry({
		columnWidth: 200,
		itemSelector: '.chat-item'
	});
	
    /**
     * 加载聊天记录
     */
    refreshListing();
    
    /**
     * 点击联系人，切换聊天记录
     */
    $('#latest_contacts').delegate('.list-group-item', 'click', function(){
        // 如果点击是当前的联系人，则不做任何处理
        if ($(this).hasClass('active')) {
            return false;
        }
        
        // 处理未读消息标记
        $('#latest_contacts').find('div.active').find('span.last-message-ctime').empty();
        $('#latest_contacts').find('div.active').find('span.last-message-count').empty();
        $(this).find('span.last-message-ctime').empty();
        $(this).find('span.last-message-count').empty();
        
        // 处理样式
        $('#latest_contacts').find('div.active').removeClass('active');
        $(this).addClass('active');
        
        // 加载聊天记录 - 先清空后加载
        refreshListing();
        
        return false;
    });
    
    /**
     * 刷新或搜索
     */
    $('.action-refresh').on('click', function(){
        refreshListing();
        return false;
    });
    
    /**
     * 关键字搜索 - 支持回车
     */
    $("input[name=key]").on('keypress', function(event){
        if (event.which == '13') {
            refreshListing();
            return false;
        }
    });
    
    /**
     * 客服回复
     */
    $('#submit_reply').on('click', function(){
        imReply();
        return false;
    });
    
    /**
     * 客服回复 - 支持回车
     */
    $('#body_reply').on('keypress', function(event){
        if (event.which == '13') {
            imReply();
            return false;
        }
    });
    
    /**
     * 实时加载用户新发消息
     */
    setInterval("loadNewMessage()", 5000);
});


/**
 * 加载聊天记录
 */
function refreshListing() {
    // 先清空旧数据
    $('#content_listing_message').empty().showLoading();
    
    /* 获取必需参数 */
    var send_userid = $('#latest_contacts').find('div.active').attr('userid'); // 当前的用户 ID
    var category_id = $("#categoryId").val(); // 当前消息类型
    var key = $('input[name=key]').val(); // 关键字搜索
    
    /* 组织查询条件 */
    var params = 'categoryId=' + category_id + '&sendUserId=' + send_userid;
    if (key) {
        params += '&key=' + key;
    }
    /* 获取当前用户的聊天记录 */
    $.ajax({
        type:'post',
        url: BASE_URL+'/back/imContent/page',
        data:params,
        dataType:'json',
        timeout:60000,
        success:function(data){
            $('#content_listing_message').hideLoading();
            if (data != '') {
                formatMessage(data.data, true);
            } 
            return false;
        }
    });
}

/**
 * 格式化消息内容
 * 
 * @param json d 消息内容
 * @param boolean is_scroll 是否滚动到底部
 */
function formatMessage(d, is_scroll) {
    /* 格式化 */
    $(d).each(function(index, item){
        // 处理头像
    	var user_avatar = $('#latest_contacts').find('div.active').find('a').find('img').attr('src');
    	var admin_avatar = $('#gender').val() == 2 ? BASE_URL+ '/resources/back/images/kefu-female-online.png' : BASE_URL+ '/resources/back/images/kefu-male-online.png'
        
        // 格式化
        var template_format = '';
        if (item.sendUserType == '1') {
            template_format = template_left.replace(/{#avatar#}/g, user_avatar);
        } else {
            template_format = template_right.replace(/{#avatar#}/g, admin_avatar);
        }
        template_format = template_format.replace(/{#content#}/g, this.content);
        template_format = template_format.replace(/{#ctime#}/g, dateConverter(this.ctime, 'MM-dd hh:mm'));
        
        // 加载格式化后的模板
        $('#content_listing_message').append(template_format);
    });
    
    // 滚动到锚点位置
    if (is_scroll == true) {
        var container = $('#content_listing'),
                scrollTo = $('#content_listing_anchor');
        container.scrollTop(
            scrollTo.offset().top - container.offset().top + container.scrollTop()
        );
    }
}

/**
 * 客服对当前对聊用户进行回复
 */
function imReply() {
    /* 获取参数 */
    var category_id = $("#categoryId").val();
    var to_userid = $('#latest_contacts').find('div.active').attr('userid');
    var content = $('#body_reply').val();
    
    /* 回复内容不能为空 */
    if (! content) {
        return false;
    }
    
    /* 提交 */
    $.ajax({
        type:'post',
        url: BASE_URL+'/back/imContent/reply',
        data:'categoryId=' + category_id + '&toUserId=' + to_userid + '&content=' + content,
        dataType:'json',
        timeout:60000,
        success:function(data){
            if (data.status == 0) {
                // 格式化
                formatMessage(data.data, true);
                
                /* 清空输入栏 */
                $('#body_reply').val('');
                
                /* 去掉当前对聊用户的未读条目数标记 */
                $('#latest_contacts').find('div.active').find('span.last-message-ctime').empty();
                $('#latest_contacts').find('div.active').find('span.last-message-count').empty();
            } else {
                alert(data.error);
            }
            return false;
        }
    });
}

/**
 * 实时加载用户新发消息
 */
function loadNewMessage() {
    /* 获取参数 */
    var category_id = $("#categoryId").val();
    var send_userid = $('#latest_contacts').find('div.active').attr('userid');
    
    /* 加载 */
    $.ajax({
        type:'post',
        url: BASE_URL+'/back/imContent/loadNewMessage',
        data:'categoryId=' + category_id + '&sendUserId=' + send_userid,
        dataType:'json',
        timeout:60000,
        success:function(data){
            if (data.status == 0) {
                // 格式化当前对聊用户的消息
                if (data.data.contents) {
                	// 先清空旧数据
                    $('#content_listing_message').empty();
                    formatMessage(data.data.contents, true);
                }
                
                // 非当前对聊用户的未读消息，标记条目数
                if (data.data.contacts) {
                    formatContact(data.data.contacts);
                }
            } else {
                alert(data.error);
            }
            return false;
        }
    });
}

/**
 * 格式化联系人信息
 */
function formatContact(d) {
    /* 定义变量 */
    var contact_exists = new Array(); // 联系人列表中已存在的用户
    
    /* 先处理联系人列表中已有的用户 */
    $('#latest_contacts > div').each(function(index){
        var userid = $(this).attr('userid');
        contact_exists.push(userid);
        
        if(!$(this).hasClass('active')) {
        	for(var i=0;i<d.length;i++){
        		if(userid == d[i].userid && d[i]['total'] > 0) {
        			$(this).find('span.last-message-ctime').html(dateConverter(d[i]['ctime'], 'MM-dd hh:mm'));
        			$(this).find('span.last-message-count').html(d[i]['total']);
        			break;
        		}
        	}
        }
    });
    
    /* 然后处理联系人中没有的用户 - 逐条往联系人列表顶端插入 */
    $.each(d, function(index, item){
        var template_format = '';
        if (contact_exists.indexOf(item.userid + '') == -1) {
            template_format = template_contact.replace(/{#userid#}/g, item.userid);
            template_format = template_format.replace(/{#gender#}/g, item.gender);
            var avatar = (item.avatar == '' || item.avatar == null) ? (gender == 2 ? BASE_URL + '/resources/back/images/avatar_default_female.jpg' : BASE_URL + '/resources/back/images/avatar_default.jpg') : item.avatar; 
            template_format = template_format.replace(/{#avatar#}/g, avatar);
            template_format = template_format.replace(/{#username#}/g, item.username);
            if(item.total > 0) {
            	template_format = template_format.replace(/{#last-message-ctime#}/g, html(dateConverter(item.ctime, 'MM-dd hh:mm')));
            	template_format = template_format.replace(/{#last-message-count#}/g, item.total);
            }
            $('#latest_contacts').prepend(template_format);
        }
    });
}