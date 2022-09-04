<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"><c:choose><c:when test="${version != null}">编辑版本</c:when><c:otherwise>添加版本</c:otherwise></c:choose></p>
            </header>
            
            <section class="scrollable wrapper panel w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/back/version/add" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><font class="red">* </font>平台类型</label>
                        <div class="col-sm-6">
                            <select name="platform" id=""platform"" class="form-control">
                          	    <option value="web" <c:if test="${version.platform == 'web'}">selected</c:if>>web</option>
                                <option value="ipad" <c:if test="${version.platform == 'ipad'}">selected</c:if>>ipad</option>
                                <option value="iphone" <c:if test="${version.platform == 'iphone'}">selected</c:if>>iphone</option>
                                <option value="android" <c:if test="${version.platform == 'android'}">selected</c:if>>android</option>
                                <option value="windows Phone" <c:if test="${version.platform == 'windows Phone'}">selected</c:if>>windows Phone</option>
                            </select>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                 
                    <div class="form-group">
                        <label for="v_code" class="col-sm-2 control-label"><font class="red">* </font>版本号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="vCode" name="vCode" value="${version.VCode}" placeholder="请输入版本号，一般为整数" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                 
                    <div class="form-group">
                        <label for="v_name" class="col-sm-2 control-label"><font class="red">* </font>版本名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="vName" name="vName" value="${version.VName}" placeholder="请输入版本名称，比如：1.01" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                 
                    <div class="form-group">
                        <label for="filepath" class="col-sm-2 control-label"><font class="red">* </font>文件路径</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="filepath" name="filepath" value="${version.filepath}" placeholder="请输入文件路径" />
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                 
                    <div class="form-group">
                        <label for="size" class="col-sm-2 control-label"><font class="red">* </font>安装包大小</label>
                        <div class="col-sm-6">
                            <div class="input-group">
	                            <input type="text" class="form-control" id="size" name="size" value="${version.size}" placeholder="请输入安装包大小" />
                                <span class="input-group-addon">KB</span>
                            </div>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                 
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">版本详情</label>
                        <div class="col-sm-6">
                            <textarea name="content" id="content" rows="3" class="form-control">${version.content}</textarea>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">设置</label>
                        <div class="col-sm-10">
                            <label for="status_publish" class="checkbox-inline p-left-0">
                                <input type="radio" name="status" id="status_publish" value="1" <c:if test="${version.status == 1}">checked=""</c:if> />&nbsp;发布
                            </label>
                            <label for="status_unpublish" class="checkbox-inline">
                                <input type="radio" name="status" id="status_unpublish" value="0" <c:if test="${version.status == 0}">checked=""</c:if> />&nbsp;暂不发布
                            </label>
                        </div>
                    </div>
                    
                    <input type="hidden" name="id" id="id" value="${version.id}" />
                    <input type="hidden" name="ctime" id="ctime" value="${version.ctime}" />
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${version != null}"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/back/modules/trend/js/version.edit.js" type="text/javascript"></script>