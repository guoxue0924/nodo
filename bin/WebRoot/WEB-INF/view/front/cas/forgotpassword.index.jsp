<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="wrapper.prefix.jsp"/>

<script src="${STATIC_URL}/front/modules/cas/js/forgotpassword.index.js" type="text/javascript"></script>
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4">找回密码</p>
            </header>
            
            <section class="scrollable wrapper panel w-f">
                <form class="form-horizontal" id="edit_form" action="${BASE_URL}/front/cas/forgotpassword" method="post" enctype="multipart/form-data">
                                        
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label"><font class="red">*</font>请输入您的账号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control " id="username" name="username" value="" placeholder="请输入您的账号"  />
                        </div>
                        <div class="col-sm-6">
                            <p class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    
                     <div class="form-group">
                        <label for="email" class="col-sm-2 control-label"><font class="red">*</font>请输入您的邮箱</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control " id="email" name="email" value="" placeholder="请输入您的邮箱"  />
                        </div>
                        <div class="col-sm-6">
                            <p class="form-control-static"></p>
                        </div>
                    </div>
                    
                <input type="hidden" name="mode" id="mode" value="email" />
                    
                                        
                </form>
            </section>
            
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">提交</button>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="wrapper.suffix.jsp"/>