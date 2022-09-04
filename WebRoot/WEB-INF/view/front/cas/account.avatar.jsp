<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>

<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
        <span><a href="${BASE_URL}/front/index/index">首页</a></span><span class="arrow">&gt;</span>
        <span><a href="${BASE_URL}/front/cas/index">我的商城</a></span><span class="arrow">&gt;</span>
        <span>账户信息</span>
    </div>
</div>
<jsp:include page="user.nav.jsp"/>
<div class="right-layout">
    <div class="wrap">
        <div class="tabmenu">
            <ul class="tab pngFix">
                <li class="normal"><a href="${BASE_URL}/front/cas/account">基本信息</a></li>
                <li class="active"><a href="${BASE_URL}/front/cas/account/avatar">更换头像</a></li>
            </ul>
        </div>
        <form action="${BASE_URL}/front/cas/account/uploadAvatar" enctype="multipart/form-data" id="form_avaster" method="post">
            <input type="hidden" name="form_submit" value="ok">
            <div class="ncm-default-form">
                <dl>
                    <dt>头像预览：</dt>
                    <dd>
                        <div class="user-avatar">
							<span> <c:choose>
									<c:when test="${loggedInUser.avatar != null}">
										<img src="${IMG_URL}${loggedInUser.avatar}">

									</c:when>
									<c:otherwise>
										<img src="${STATIC_URL}/front/images/index/aa.png">
									</c:otherwise>
								</c:choose>

							</span>
						</div>
                        <p class="hint mt5">完善个人信息资料，上传头像图片有助于您结识更多的朋友。<br><span style="color:orange;">头像默认尺寸为120x120像素，请根据系统操作提示进行裁剪并生效。</span></p>
                    </dd>
                </dl>
                <dl>
                    <dt>更换头像：</dt>
                    <dd>
                        <div class="ncm-upload-btn"> <a href="javascript:void(0);"><span>
            <input type="file" hidefocus="true" size="1" class="input-file" name="pic" id="pic" file_id="0" multiple="" maxlength="0">
            </span>
                            <p><i class="icon-upload-alt"></i>图片上传</p>
                            <input id="submit_button" style="display:none" type="button" value="&nbsp;" onclick="submit_form($(this))">
                        </a> </div>
                    </dd>
                </dl>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
            $('#pic').change(function(){
                var filepatd=$(this).val();
                var extStart=filepatd.lastIndexOf(".");
                var ext=filepatd.substring(extStart,filepatd.lengtd).toUpperCase();
                if(ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
                    alert("文件类型错误，请选择图片文件（png|gif|jpg|jpeg）");
                    $(this).attr('value','');
                    return false;
                }
                if ($(this).val() == '') return false;
                $("#form_avaster").submit();
            });
        });
    </script>
</div>
<div class="clear"></div>
</div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>