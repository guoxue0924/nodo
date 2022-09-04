$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
    /**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('#content_listing').datagrid('reload');
		return false;
	});
	
	/**
	 * 关键字搜索
	 */
	$('input[name=key]').on('keypress', function (event) {
	    if (event.which == '13') {
	        $('#content_listing').datagrid("reload");
	        return false;
	    }
	});
	
	/**
	 * 重置密码
	 */
	$('body').delegate('.action-rpw', 'click', function(){
		var userid = $(this).attr('userid');
		var username = $(this).parent().parent().find('td').eq(2).text();
		$('input[name=userid]').val(userid);
		$('.username').text(username);
	});
	
	/**
	 * 重置密码 - 保存
	 */
	$("#submit_save_back").click(function(){
		form_submit($('input[name=userid]').val());
	});
	
	/**
	 * 删除 - 单条
	 */
    $("#content_listing").delegate('.operate-delete', 'click', function(){
		var userid = $(this).attr("userid");
		doDeleteUser(userid);
	});
    
    /**
     * 激活用户
     */
    $('#content_listing').delegate('.switch-sm', 'click', function(){
    	var userid = $(this).attr('userid');
    	updateUser(userid);
    	return false;
    });
});


/**
 * 审核处理用户
 */
function updateUser(userid) {
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/casuser/isVerify/edit',
        data:'userid=' + userid,
        dataType:'json',
        timeout:60000,
        success:function(d){
    		if (d.status == 0) {
				$('#user_' + userid).prop('checked', !$('#user_' + userid).prop('checked'));
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

/**
 * 重置密码
 */
function form_submit(userid) {
	var password = $('#password').val();
	var repassword = $('#repassword').val();
	var username = $('.username').text();
	
	if (! userid) {
		alert('好像发生了一些意外呢，请联系技术人员提供支援吧');
		return false;
	}
	var regx = /^[a-z0-9\_]{6,14}$/i;
	if (! regx.test(password)) {
		alert('密码只能使用以下字符：a-z、A-Z、0-9 以及下划线“_”，且至少6位，最多不超过14位');
		return false;
	}
	if (password == username) {
		alert('密码不能和用户名相同');
		return false;
	}
	if (repassword != password) {
		alert('两次输入密码不一致');
		return false;
	}
	
	$(".input-submit").attr('disabled', true);
	var saveCallBack = form_save_added;
	var options = {
            dataType:'json',
            timeout:60000,
            success:saveCallBack,
            error:ajaxError
    };
    $("#edit_form").ajaxSubmit(options);
    return false;
}

/**
 * 重置密码 - 成功，返回处理
 */
function form_save_added(data, textStatus) {
	if (data.status === 0) {
		$('#modal').modal("hide");
    }
    $(".input-submit").removeAttr('disabled');
}

/**
 * 删除
 */
function doDeleteUser(userid) {
	var del = confirm('确定要删除所选用户吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/casuser/delete',
        data:'userid=' + userid,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
				$("#userid_" + userid).parent().parent().remove();
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

function columnsDefined() {
	return [
            {
            	property: 'a',
            	label: ''
            },
            {
                property: 'userid',
                label: 'ID',
                sortable: true
            },
            {
                property: 'username',
                label: '用户名',
                sortable: false
            },
//            {
//            	property: 'userLvName',
//            	label: '等级',
//            	sortable: false
//            },
            {
            	property: 'realname',
            	label: '真实姓名',
            	sortable: false
            },
            {
                property: 'gender',
                label: '性别',
                sortable: false
            },
            {
            	property: 'phone',
            	label: '手机',
            	sortable: false
            },
            {
            	property: 'email',
            	label: 'E-mail',
            	sortable: false
            },
            {
            	property: 'lastLoginTime',
            	label: '最后登录时间',
            	sortable: true
            },
            {
            	property: 'isVerify',
            	label: '审核',
            	sortable: false
            },
            {
            	property: 'action',
            	label: '操作',
            	sortable: false
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
        item.a = '<a href="' + BASE_URL + '/back/casuser/detail?userid=' + item.userid + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
        
        var strGender = '未知';
        if (item.gender == 1) {
        	strGender = '男';
        } else if (item.gender == 2) {
        	strGender = '女';
        }
        item.gender = strGender;
        
        var ischecked = item.isVerify == 1 ? 'checked="checked"' : '';
        item.isVerify = '<label class="switch-sm" userid="' + item.userid + '">' + 
        		'<input type="checkbox" id="user_' + item.userid + '" ' + ischecked + ' />' + 
        		'<span></span></label>';
        
        item.action = '<a href="' + BASE_URL + '/back/casuser/edit?userid=' + item.userid + '" class="load-content" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' 
       /* +'<a href="javascript:;" class="operate-delete" id="userid_' + item.userid + '" userid="' + item.userid + '" title="删除"><i class="fa fa-times"></i></a>'*/;
        item.lastLoginTime = dateConverter(item.lastLoginTime, PATTERN_ENUM.datetime);
	});
}