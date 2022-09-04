$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
    /**
	 * 刷新或搜索
	 */
	$('.action-refresh').on('click', function(){
		$('.datagrid').datagrid('reload');
		return false;
	});
	
	/**
	 * 搜索对回车的支持
	 */
	$("input[name=key]").on('keypress', function (event) {
		if (event.which == '13') {
	        $('.datagrid').datagrid("reload");
	        return false;
	    }
	});
	
});

function columnsDefined() {
	return [
			{
				property: 'a',
				label: ''
			},
            {
                property: 'contentId',
                label: 'ID',
                sortable: true
            },
            {
                property: 'body',
                label: '反馈内容',
                sortable: false
            },
            {
                property: 'realname',
                label: '反馈用户',
                sortable: false
            },
            {
                property: 'status',
                label: '状态',
                sortable: true
            },
            {
                property: 'ctime',
                label: '反馈时间',
                sortable: true
            },
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
    	item.a = '<a href="' + BASE_URL + '/back/feedbackContent/detail?contentId=' + item.contentId + '" data-toggle="ajaxModal"><i class="fa fa-search-plus" title="查看详情"></i></a>';
    	if (item.status == 1){
    		item.status = '<span><i class="fa fa-check-square-o"></i></span>'
    	} else {
        	item.status = '<span>未处理</span>';

    	}
    	
    	if (item.userid == null){
    		item.realname = '匿名用户';
    	}
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
	});
}