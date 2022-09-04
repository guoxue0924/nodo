<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<section class="edit-map wrapper" id="edit_base">
    <div class="form-group">
        <label class="col-sm-2 control-label"><font class="red">* </font>所属页面</label>
        <div class="col-sm-6">
            <select id="pageId" name="pageId" class="form-control">
                <option value="0">请选择</option>
                <c:forEach items="${pages}" var="v">
                    <option value="${v.pageId}" <c:if test="${v.pageId == content.pageId}">selected</c:if>>${v.title}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
            
    <div class="form-group">
    <input type="hidden" name="contentId" value="${content.contentId}" />
        <label class="col-sm-2 control-label"><font class="red">* </font>广告类型</label>
        <div class="col-sm-6">
            <select id="type" name="type" class="form-control">
                <option value="1" <c:if test="${content.type == 1}">selected</c:if>>图片</option>
                <option value="2" <c:if test="${content.type == 2}">selected</c:if>>文字</option>
                <option value="3" <c:if test="${content.type == 3}">selected</c:if>>Flash</option>
                <option value="4" <c:if test="${content.type == 4}">selected</c:if>>视频</option>
                <option value="5" <c:if test="${content.type == 5}">selected</c:if>>轮播</option>
            </select>
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label"><font class="red">* </font>所属广告位</label>
        <div class="col-sm-6">
            <select name="boardId" id="boardId" class="form-control">
                <option value="0">请选择</option>
            </select>
            <input type="hidden" id="boardIdEdit" name="boardIdEdit" value="${content.boardId}" />
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label for="title" class="col-sm-2 control-label"><font class="red">* </font>广告标题</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="title" name="title" value="${content.title}" placeholder="请输入广告标题" />
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">广告详情</label>
        <div class="col-sm-6">
            <textarea id="content" name="content" class="form-control" placeholder="请输入广告详情">${content.content}</textarea>
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
            
    <div id="for_type" <c:if test="${content.type == 2}">class="default-hidden"</c:if>>
        <div class="form-group m-b-xs file-reset">
            <label class="col-sm-2 control-label">上传附件</label>
            <div class="col-sm-6">
                <input type="file" name="uploadFile" id="attachment" class="filestyle" data-icon="false" data-buttonText="上传附件" data-classbutton="btn btn-default" data-classinput="form-control inline input-s" />
                <c:if test="${content.attachmentid != null && content.attachmentid != '' && attachment.mediatype == 'image'}">
                    <a href="${IMG_URL}${attachment.filepath}" class="thumb-sm" target="_blank"><img src="${IMG_URL}${attachment.filepath}" /></a>
                </c:if>
            </div>
        </div>
        <div class="line line-dashed line pull-in"></div>
    </div>
            
    <div class="form-group">
        <label for="count" class="col-sm-2 control-label">点击次数</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="count" name="count" value="${content.count}" placeholder="输入数量以预设点击次数" />
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label">生效时间</label>
        <div class="col-sm-6">
            <input id="startTime" name="startTimeStr" class="input-sm input-s datepicker-input form-control" type="text" 
                    data-date-format="yyyy-mm-dd" value="${startTimeStr}" placeholder="点击选择生效时间" />
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label">结束时间</label>
        <div class="col-sm-6">
            <input id="endTime" name="endTimeStr" class="input-sm input-s datepicker-input form-control" type="text" 
                    data-date-format="yyyy-mm-dd" value="${endTimeStr}" placeholder="点击选择结束时间" />
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label for="sort_order" class="col-sm-2 control-label">序号</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${content.sortOrder == null ? 0 : content.sortOrder == '' ? 0 : content.sortOrder}" />
        </div>
    </div>
    <div class="line line-dashed line pull-in"></div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label">状态</label>
        <div class="col-sm-6">
            <label class="checkbox-inline p-left-0">
                <input type="radio" <c:if test="${content.status == 1}">checked</c:if> name="status" value="1" /> 启用
            </label>
            <label class="checkbox-inline p-left-0">
                <input type="radio" <c:if test="${content.status == '0'}">checked</c:if> name="status" value="0" /> 关闭
            </label>
        </div>
    </div>
</section>