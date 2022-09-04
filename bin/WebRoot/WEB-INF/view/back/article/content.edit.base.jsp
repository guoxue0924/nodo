<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="edit-map wrapper" id="edit_base">
	<section class="panel ">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-2 control-label"><font class="red">* </font>所属分类</label>
				<div class="col-sm-6">
					<select name="categoryId" id="categoryId" class="form-control">
						<c:forEach items="${categorys}" var="v">
                            <option value="${v.categoryId}" <c:if test='${v.categoryId==content.categoryId}'>selected</c:if>>
                                ${v.title}
                            </option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>

			<div class="form-group">
				<label for="name" class="col-sm-2 control-label"><font class="red">* </font>文章标题</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="title" name="title" value="${content.title}" placeholder="请输入文章标题" />
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>

			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">副标题</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="subtitle" name="subtitle" value="${content.subtitle}" placeholder="请输入副标题" />
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>

			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">短标题</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="slug" name="slug" value="${content.slug}" placeholder="请输入短标题" />
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			
			<div class="form-group file-reset">
                <label class="col-sm-2 control-label">文章图片</label>
                <div class="col-sm-6">
                    <input type="file" name="image" id="image" class="filestyle" data-icon="false" data-classbutton="btn btn-default" 
                    data-classinput="form-control inline input-s" style="position: fixed; left: -500px;">
                </div>
            </div>
            <div class="form-group">
                <label for="label" class="col-sm-2 control-label">标签</label>
                <div class="col-sm-6">
                    <textarea name="label" id="label" rows="2" class="form-control" placeholder="多个以半角逗号分隔">${content.label}</textarea>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            
            <div class="form-group">
                <label class="col-sm-2 control-label">设置</label>
                <div class="col-sm-6">
                    <label for="status" class="checkbox-inline">
                        <input type="checkbox" name="status" id="status" value="1">&nbsp;发布
                    </label>
                    <label for="isRecommend" class="checkbox-inline">
                        <input type="checkbox" name="isRecommend" id="isRecommend" value="1">&nbsp;推荐
                    </label>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            
            <div class="form-group">
                 <label for="sortOrder" class="col-sm-2 control-label">置顶</label>
                 <div class="col-sm-2">
                     <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="0">
                 </div>
                 <div class="col-sm-8">
                     <p class="form-control-static">(置顶权重。数字越高，位置越靠前)</p>
                 </div>
             </div>
		</div>
	</section>

	<input type="hidden" id="contentId" name="contentId" value='${contentId}' />

</section>