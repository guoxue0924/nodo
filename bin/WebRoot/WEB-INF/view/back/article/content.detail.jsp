<%@ page contentType="text/html;charset=UTF-8" %>

<!-- modal - 编辑 -->
<div class="modal-dialog">
    <div class="modal-content">
    	<div class="modal-header">
	    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="modal_title">预览文章 - ${content.title}</h4>
    	</div>
	    <div class="modal-body">
	    	${content.body}
	    </div>
	    <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	    </div>
    </div>
</div>
