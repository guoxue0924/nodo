<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link rel="stylesheet" href="${STATIC_URL}/plugins/jquery-fileupload/5.8.1/css/bootstrap-responsive.min.css" type="text/css" />
<link rel="stylesheet" href="${STATIC_URL}/plugins/jquery-fileupload/5.8.1/css/jquery.fileupload-ui.css" type="text/css" />
<link href="${STATIC_URL}/back/modules/trend/css/setting.index.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4">系统设置</p>
                <a href="trendadmin/setting/add" class="btn btn-sm btn-default load-content m-l-sm" style="margin-top:-5px;"><i class="fa fa-plus"></i> 添加(仅供开发者使用)</a>
            </header>
            
            <section class="scrollable wrapper panel">
                <form class="form-horizontal" action="${BASE_URL}/back/trendadmin/setting/setVal" method="post" id="edit_form" enctype="multipart/form-data">
                    
                    {foreach from=$settings item=v}
                    <div class="form-group {if $v.val_inputtype == 'file'}file-reset{/if}">
                        <label class="col-sm-2 control-label">${v.label_name}</label>
                        {if $v.val_inputtype == 'text'}
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="${v.name}" name="${v.name}" value="${v.val}" placeholder="${v.placeholder}" setting_id="${v.setting_id}" />
                            <span class="help-block m-b-none">${v.note}</span>
                        </div>
                        {else if $v.val_inputtype == 'select'}
                        <div class="col-sm-6">
		                    <select id="${v.name}" name="${v.name}" class="input-sm form-control inline" setting_id="${v.setting_id}">
		                        {foreach from=$v.val_options_arr item=vv}
		                        <option value="${vv.option_value}" {if $v.val == $vv.option_value}selected{/if}>${vv.option_title}</option>
		                        {/foreach}
		                    </select>
		                    <span class="help-block m-b-none">${v.note}</span>
	                    </div>
                        {else if $v.val_inputtype == 'checkbox'}
                        <div class="col-sm-8">
                            {foreach from=$v.val_options_arr item=vv}
                            <label class="checkbox-inline">
                                <input type="checkbox" name="${v.name}" value="${vv.option_value}" setting_id="${v.setting_id}" {if $vv.option_value|in_array:$v.val}checked="checked"{/if} />${vv.option_title}
                            </label>
                            {/foreach}
                            <span class="help-block m-b-none">${v.note}</span>
                        </div>
                        {else if $v.val_inputtype == 'radio'}
                        <div class="col-sm-8">
                            {foreach from=$v.val_options_arr item=vv}
                            <label class="checkbox-inline p-left-0">
                                <input type="radio" name="${v.name}" value="${vv.option_value}" setting_id="${v.setting_id}" {if $v.val == $vv.option_value}checked="checked"{/if} />&nbsp;${vv.option_title}
                            </label>
                            {/foreach}
                            <span class="help-block m-b-none">${v.note}</span>
                        </div>
                        {else if $v.val_inputtype == 'textarea'}
                        <div class="col-sm-6">
                            <textarea id="${v.name}" name="${v.name}" rows="2" class="form-control" placeholder="${v.placeholder}" setting_id="${v.setting_id}">${v.val}</textarea>
                            <span class="help-block m-b-none">${v.note}</span>
                        </div>
                        {else if $v.val_inputtype == 'file'}
                        <div class="col-sm-3">
                            <a href="javascript:;" class="img-thumbnail pull-left">
                                <img id="gallery_add_single_${v.setting_id}" class="img-thumbnail-single" src="{if $v.val}/uploads${v.val}{else}...{/if}" alt="..." />
                            </a>
                            <div class="col-md-4">
                                <p>
                                    <span class="btn btn-success fileinput-button">
                                        <i class="icon-plus icon-white"></i>
                                        <span>上传</span>
                                        <input type="file" name="${v.name}" setting_id="${v.setting_id}" />
                                    </span>
                                </p>
                                <br /><br />
                                <p><button type="button" id="gallery_delete_single_${v.setting_id}" class="btn btn-danger gallery-delete-single" 
                                        setting_id="{if $v.val}${v.setting_id}{/if}">删除</button></p>
                            </div>
                            <br />
                            <span class="help-block m-b-none padder-t-xs" style="clear: both;">${v.note}</span>
                        </div>
                        {/if}
                        
                        <div class="col-sm-2">
                            <span class="edit-notice" id="edit_notice_${v.setting_id}"></span>
                        </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
                    {/foreach}
                    
                </form>
            </section>
        </section>
    </aside>
</section>


<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/jquery-fileupload/5.8.1/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/5.8.1/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/jquery-fileupload/5.8.1/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${STATIC_URL}/plugins/file-input/bootstrap-filestyle.min.js"></script>
<script src="${STATIC_URL}/back/modules/trend/js/setting.index.js"></script>