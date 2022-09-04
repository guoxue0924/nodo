<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="edit-map wrapper default-hidden" id="edit_image">
	<!-- 上传附件 -->
	<section class="editor" id="edit_fileupload">
	<%--
		<div class="dropfile-diy sortable-placeholder wrapper">
			<a href="javascript:;" class="btn btn-success fileinput-button"> <i class="fa fa-cloud-upload"></i> <span class="text">上传</span> <span class="text-active">上传中...</span> <input type="file" name="files" id="btn_fileupload" multiple /> </a> <a href="javascript:;" class="btn btn-danger" id="btn_delete_multiple"> <i class="fa fa-trash-o"></i> <span class="text">删除</span> </a> <i id="spin" class="fa fa-spin fa-spinner hide"></i> <span class="pull-right m-t-sm"> 你可以点击“上传”按钮选择单张或多张图片进行上传，也可以将图片拖拽到这里进行批量上传 </span>
		</div>

		<section class="panel panel-default m-t gallery-edit">
			<table class="table table-striped m-b-none text-sm" id="table_striped">
				<thead>
					<th class="col-sm-1" data-property="checkbox"><input type="checkbox">
					</th>
					<th class="col-sm-2">预览</th>
					<th>名称</th>
					<th class="col-sm-2">大小</th>
					<th class="col-sm-1">封面</th>
					<th class="col-sm-1">操作</th>
				</thead>
				<tbody>
					<c:if test="${contentAttachment != null}">
						<c:forEach items="${contentAttachment}" var="v">
							<tr attachmentid="${v.attachmentid}">
								<td class="v-middle"><input type="checkbox" class="select-single" value="${v.attachmentid}" /></td>
								<td class="v-middle"><a href="${v.filepath}" rel="prettyPhoto[gallery]" title="${v.title}"> <img src="${IMG_URL}${v.filepath}" border="0" style="height: 80px; width: 60;" /> </a></td>
								<td class="v-middle">${v.title}</td>
								<td class="v-middle">${v.filesize} Bytes</td>
								<td class="v-middle"><label class="switch-sm switch-image-default"> <!--                                 <input type="checkbox" class="image-default" <c:if test="${v.filepath == content.imageDefault}">checked</c:if> /> --> <input type="checkbox" class="image-default" /> <span></span> </label></td>
								<td class="v-middle">
									<button type="button" class="btn btn-danger btn-sm delete-single">
										<i class="fa fa-trash-o"></i> <span> 删除</span>
									</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</section>
		 --%>
		
		<section class="panel panel-default m-t gallery-edit">
            <div>
                <table style="width: 100%" id="skuImages">
                </table>
            </div>
        </section>
	</section>

    <input type='hidden' id='imageInfo' value='${skuImages}' />
	<%--     <input type="hidden" name="attachmentIds" value="${content.attachmentIds}" /> --%>
	<%--     <input type="hidden" name="imageDefault" value="${content.imageDefault}" /> --%>
	<!-- 上传附件 @end -->
</section>