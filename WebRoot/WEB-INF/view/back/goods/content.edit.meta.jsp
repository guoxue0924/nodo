<%@ page contentType="text/html;charset=UTF-8"%>

<section class="edit-map wrapper default-hidden" id="edit_meta">
	<div class="form-group m-b-xs">
		<label for="metaTitle" class="col-sm-2 control-label">商品页面标题</label>
		<div class="col-sm-6">
			<textarea name="metaTitle" id="metaTitle" rows="2" class="form-control" placeholder="请输入商品页面标题">${content.metaTitle}</textarea>
			<span class="help-block m-b-none">留空则默认显示商品名称</span>
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="metaKeywords" class="col-sm-2 control-label">META_KEYWORDS<br />(页面关键词)</label>
		<div class="col-sm-6">
			<textarea name="metaKeywords" id="metaKeywords" rows="2" class="form-control" placeholder="请输入页面关键词">${content.metaKeywords}</textarea>
			<span class="help-block m-b-none">留空则默认继承分类或全局设置的KEYWORDS内容</span>
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>

	<div class="form-group m-b-xs">
		<label for="metaDescription" class="col-sm-2 control-label">META_DESCRIPTION<br />(页面描述)</label>
		<div class="col-sm-6">
			<textarea name="metaDescription" id="metaDescription" rows="2" class="form-control" placeholder="请输入页面描述">${content.metaDescription}</textarea>
			<span class="help-block m-b-none">留空则默认继承分类或全局设置的DESCRIPTION内容</span>
		</div>
	</div>
	<div class="line line-dashed line-sm pull-in"></div>
</section>