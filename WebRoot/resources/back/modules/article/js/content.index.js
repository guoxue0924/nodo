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
     * 发布
     */
    $('#content_listing').delegate('.switch-sm', 'click', function(){
    	if($(this).attr('content_id')){
	    	var content_id = $(this).attr('content_id');
	    	publishArticle(content_id);
	    	return false;
    	}
    });
	
	/**
     * 推荐
     */
    $('#content_listing').delegate('.switch-sm', 'click', function(){
    	if($(this).attr('content_a_id')){
	    	var content_a_id = $(this).attr('content_a_id');
	    	recommendedArticle(content_a_id);
	    	return false;
    	}
    });
    
	/**
	 * 扔进回收站 - 单条
	 */
    $("#content_listing").delegate('.operate-trash', 'click', function(){
		var content_id = $(this).attr("contentId");
		var id_arr = new Array();
		id_arr.push(content_id)
		doTrashContent(id_arr);
	});
	
	/**
	 * 扔进回收站 - 批量
	 */
	$('#action_trash').on('click', function(){
		var id_arr = new Array();
		var i = 0;
		$('#content_listing').find('.select-single').each(function(){
			if ($(this).is(':checked')) {
				id_arr.push($(this).val());
				i++;
			}
		});
		if (i == 0) {
			return false;
		}
		doTrashContent(id_arr);
	});
    
});

/**
 * 删除
 */
function doTrashContent(contentIds) {
	var del = confirm('确定要删除所选文章吗？');
	if (! del) {return false;}
	
	/* 执行 */
	$.ajax({
    	type:'post',
        url:BASE_URL+'/back/articleContent/delete',
        data:{contentIds : contentIds},
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			$('#content_listing').datagrid("reload");
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

/**
 * 发布
 */
function publishArticle(contentId) {
	$.ajax({
    	type:'post',
        url: BASE_URL +'/back/articleContent/publish',
        data:{contentId:contentId,isPublished:$('#publish_' + contentId).prop('checked')},
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
				$('#publish_' + contentId).prop('checked', !$('#publish_' + contentId).prop('checked'));
    		} else {
    			alert(data.error);
    		}
    		return false;
    	}
    });
}

/**
 * 推荐
 */
function recommendedArticle(contentId) {
	$.ajax({
    	type:'post',
        url:BASE_URL +'/back/articleContent/recommended',
        data:{contentId:contentId,isRecomended:$('#isRecommend_' + contentId).prop('checked')},
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
				$('#isRecommend_' + contentId).prop('checked', !$('#isRecommend_' + contentId).prop('checked'));
    		} else {
    			alert(data.error);
    		}
    		return false;
    	}
    });
}

function columnsDefined() {
	return [
			{
				property: 'checkbox',
				label:'<input type="checkbox" />',
			},
	        {
	        	property: 'a',
	        	label:'',
	        },
            {
                property: 'contentId',
                label: 'ID',
            },
            {
                property: 'categoryTitle',
                label: '分类',
            },
            {
                property: 'title',
                label: '标题',
            },
            {
                property: 'sortOrder',
                label: '权重',
            },
            {
                property: 'mtime',
                label: '修改时间',
            },
            {
                property: 'isRecommend',
                label: '推荐',
            },
            {
                property: 'status',
                label: '发布',
            },
            {
            	property: 'action',
            	label: '操作',
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
		item.checkbox = '<input type="checkbox" class="select-single" value="' + item.contentId + '">';
		item.a = '<a href="'+BASE_URL+'/back/articleContent/detail?contentId=' + item.contentId + '" data-toggle="ajaxModal" class="modal-detail"><i class="fa fa-search-plus"></i></a>';
        item.action = '<a href="'+BASE_URL+'/back/articleContent/edit?contentId=' + item.contentId + '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' +
        '<a href="javascript:;" class="operate-trash" id="contentId_' + item.contentId + '" contentId="' + item.contentId + '" title="删除"><i class="fa fa-trash-o"></i></a>';
        
        var ischecked = item.isRecommend == 1 ? 'checked="checked"' : '';
        if(ischecked){
            item.isRecommend = '<label class="switch-sm" title="关闭推荐" content_a_id="' + item.contentId + '">' + 
            		'<input type="checkbox" id="isRecommend_' + item.contentId + '" ' + ischecked + ' />' + 
            		'<span></span></label>';
        }else{
        	item.isRecommend = '<label class="switch-sm" title="启用推荐" content_a_id="' + item.contentId + '">' + 
    		'<input type="checkbox" id="isRecommend_' + item.contentId + '" ' + ischecked + ' />' + 
    		'<span></span></label>';
        }
        
        ischecked = item.status == 1 ? 'checked="checked"' : '';
        if(ischecked){
            item.status = '<label class="switch-sm" title="关闭发布" content_id="' + item.contentId + '">' + 
            		'<input type="checkbox" id="publish_' + item.contentId + '" ' + ischecked + ' />' + 
            		'<span></span></label>';
        }else{
        	item.status = '<label class="switch-sm" title="开启发布" content_id="' + item.contentId + '">' + 
    		'<input type="checkbox" id="publish_' + item.contentId + '" ' + ischecked + ' />' + 
    		'<span></span></label>';
        }
        item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
    });
}