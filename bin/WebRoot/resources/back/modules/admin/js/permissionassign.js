$(document).ready(function() {
	/**
	 * 应用列表处理
	 */
	$(".nav-top-list").click(function(event){
		event.preventDefault();
        event.stopPropagation();
        
		var href = $(this).attr("href");
		
		$.history.load(href);
		return false;
	});
	
	/**
     *  全选 授权/解除授权
     */
	$('.select-all').on('click', function(){
		var checked = $(this).prop('checked');
		var permissionIdArr = "";
		$("#permission_content").find('.permission_assign').prop('checked',checked);
		$.each($("#permission_content").find('.permission_assign'),function(index,data){
			permissionIdArr += $(data).val()+",";
		});
		Permission_Assign_All(permissionIdArr.substring(0,permissionIdArr.length - 1),checked);
	});
	
	/**
	 * 单个 授权/解除授权
	 */
	$('input.permission_assign').click(function(){
        Permission_Assign($("input[name=groupId]:last").val(),  $(this).val(), $(this).is(':checked'),$(this).attr('pg'));
    });
});

/**
 * 单选 - 授权权限方法
 * @param $groupId
 * @param $permission_id
 * @param $assign
 * @param $permission_name
 */
function Permission_Assign($groupId, $permission_id, $assign,$permission_name) {
	var navigationId = $("input[name=navigationId]:last").val();
	$.ajax({
    	type:'post',
        url:BASE_URL + '/back/adminPermission/assignPermission',
        data:'groupId=' + $groupId + '&permissionId=' + $permission_id + '&assign=' + ($assign ? 1 : 0),
        dataType:'json',
        timeout:60000,
        success:function(data){
        	var op_title = ($assign ? '' : '解除') + '授权——权限ID【' + $permission_name+'】';
    		if (data.status == 0) {
    			notice('edit_notice_' + $permission_name, img_done + ' ' + op_title + '成功', true, 5000);
    		} else {
    			notice('edit_notice_' + $permission_name, img_delete + ' ' + op_title + '失败。原因：' + data.error, true, 5000);
    			if ($assign) {
    				$('#pm_' + $permission_id).attr('checked', false);
    			} else {
    				$('#pm_' + $permission_id).attr('checked', true);
    			}
    		}
    		return false;
    	},
        error:ajaxError
    });
	
	
}
	/**
	 * 全选 - 授权权限方法
	 * @param $groupId
	 * @param $permission_id
	 * @param $assign
	 */
	function Permission_Assign_All($permission_ids, $assign) {
		var $groupId = $("input[name=groupId]:last").val();
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/back/adminPermission/assignPermissionAll',
	        data:'groupId=' + $groupId + '&permissionIds=' + $permission_ids + '&assign=' + ($assign ? 1 : 0),
	        dataType:'json',
	        timeout:60000,
	        success:function(data){
	        	var op_title_all = ($assign ? '' : '解除') + '授权——';
	    		if (data.status == 0) {
	    			notice('edit_notice', img_done + ' ' + op_title_all + '成功', true, 5000);
	    		} else {
	    			notice('edit_notice', img_delete + ' ' + op_title_all + '失败。原因：' + data.error, true, 5000);
	    			if ($assign) {
	    				$('#pm_' + $permission_id).attr('checked', false);
	    			} else {
	    				$('#pm_' + $permission_id).attr('checked', true);
	    			}
	    		}
	    		return false;
	    	},
	        error:ajaxError
	    });
}
