<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>
<link href="${STATIC_URL}/plugins/codemirror/4.3/lib/codemirror.css" rel="stylesheet" type="text/css" />
<link href="${STATIC_URL}/plugins/codemirror/4.3/addon/display/fullscreen.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <p class="h4 m-t-xs"><c:choose><c:when test="${content !=null}">编辑单页</c:when><c:otherwise>添加单页</c:otherwise></c:choose></p>
                    </div>
                    <div class="col-sm-4 m-b-xs">
                        <div data-toggle="buttons" class="btn-group pull-right">
                            <c:choose>
                            	<c:when test="${content !=null}">
                            		<a href="${BASE_URL}/back/pageContent/edit?et=ck&contentId=${content.id}" class="btn btn-sm btn-default load-content ">
                            	</c:when>
                            	<c:otherwise>
                            		<a href="${BASE_URL}/back/pageContent/add?et=ck" class="btn btn-sm btn-default load-content ">
                            	</c:otherwise>
                            </c:choose>
                               <input type="checkbox" id="option1" name="options"> 富文本编辑模式
                           	</a>
                            <c:choose>
                            	<c:when test="${content !=null}">
	                            	<a href="${BASE_URL}/back/pageContent/edit?et=cm&contentId=${content.id}" class="btn btn-sm btn-default load-content active">
	                            </c:when>
                            	<c:otherwise>
	                            	<a href="${BASE_URL}/back/pageContent/add?et=cm" class="btn btn-sm btn-default load-content active">
	                            </c:otherwise>
                            </c:choose>
                            <input type="checkbox" id="option2" name="options"> 在线编辑模式
                            </a>
                        </div>
                    </div>
                </div>
            </header>
            
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/pageContent/add" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属分组</label>
                        <div class="col-sm-6">
	                        <select name="groupId" id="groupId" class="form-control">
	                        <c:forEach items="${groups}" var="g">
	                        	<option value="${g.groupId}" <c:if test="${g.groupId == content.groupId }">selected</c:if>>${g.groupName}</option>
	                        </c:forEach>
	                        </select>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">共享页面头尾</label>
                        <div class="col-sm-6">
	                        <select name="isShareCommon" id="isShareCommon" class="form-control">
	                            <option value="1" <c:if test="${ content == null || content.isShareCommon == 1}">selected</c:if>>是</option>
	                            <option value="0" <c:if test="${ content.isShareCommon == 0}">selected</c:if>>否</option>
	                        </select>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label for="page_folder" class="col-sm-2 control-label"><font class="red">* </font>网页名称</label>
                        <div class="col-sm-6">
                            <c:choose>
                            	<c:when test="${content ==null}">
		                            <input type="text" class="form-control" id="pageUrl" name="pageUrl" value="" placeholder="请输入网页名称" />
		                            <p class="form-control-static">必须是字母，且首字母小写。多个单词，则保持半驼峰式。比如：howToBuy</p>
                            	</c:when>
                            	<c:otherwise>
		                            <p class="form-control-static">${content.pageUrl}</p>
                            	</c:otherwise>
                           	</c:choose>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label"><font class="red">* </font>网页标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" value="${content.title}" placeholder="请输入网页标题" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label for="editor1" class="col-sm-2 control-label">网页内容</label>
                        <div class="col-sm-10">
                            <section class="panel panel-default">
			                    <header class="panel-heading text-right bg-light">
			                        <ul class="nav nav-tabs pull-left">
			                            <li class="active"><a data-toggle="tab" href="#edit_online"><i class="fa fa-edit text-default"></i> 在线编辑</a></li>
			                            <li class=""><a data-toggle="tab" href="#edit_upload"><i class="fa fa-upload text-default"></i> 上传文件</a></li>
			                        </ul>
			                        <ul class="nav nav-tabs pull-right">
                                        <li class="dropdown">
                                            <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-cog text-default"></i> themes <b class="caret"></b></a>
                                            <ul class="dropdown-menu text-left">
                                                <li><a href="javascript:;">暂未开放</a></li>
                                                <li><a href="javascript:;">敬请期待</a></li>
                                                <li><a href="javascript:;">default</a></li>
                                                <li><a href="javascript:;">night</a></li>
                                                <li><a href="javascript:;">neat</a></li>
                                                <li><a href="javascript:;">elegant</a></li>
                                            </ul>
                                        </li>
                                    </ul>
			                        <span class="hidden-sm">&nbsp;</span>
			                    </header>
			                    <div>
			                        <div class="tab-content">              
				                        <div id="edit_online" class="tab-pane fade active in">
                                            <textarea name="body" id="content_code" class="hide">${content.body}</textarea>
				                        </div>
				                        <div id="edit_upload" class="tab-pane fade panel-body">upload files ready...</div>
			                        </div>
			                    </div>
			                </section>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label for="memo" class="col-sm-2 control-label">内容摘要</label>
                        <div class="col-sm-6">
                            <textarea name="memo" id="memo" rows="2" class="form-control">${content.memo}</textarea>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label for="url" class="col-sm-2 control-label">链接转发</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="url" name="url" value="${content.forwardUrl}" />
                            <p class="form-control-static">进入到页面时，将跳转到该链接地址</p>
                        </div>
                    </div>
                    
                    <input type="hidden" name="id" id="id" value="${content.id}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/codemirror/4.3/lib/codemirror.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/mode/javascript/javascript.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/mode/xml/xml.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/addon/selection/active-line.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/addon/edit/matchbrackets.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/codemirror/4.3/addon/display/fullscreen.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/js/codemirror.js" type="text/javascript"></script>
<script src="${STATIC_URL}/back/modules/page/js/content.edit.js" type="text/javascript"></script>

<script type="text/javascript">
$(function() {
	CodeMirror_Helper('content_code', 'editor_header');
});

</script>