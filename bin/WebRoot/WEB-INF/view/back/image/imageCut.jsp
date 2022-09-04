<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ImageDemo</title>
<link rel="stylesheet" href="${STATIC_URL}/plugins/Jcrop/0.9.8/css/jquery.Jcrop.css" type="text/css" />
<style type="text/css">
.crop_preview {
    position: absolute;
    left: 1280px;
    top: 10px;;
    width: 120px;
    height: 120px;
    overflow: hidden;
}
</style>
<script type="text/javascript" src="${STATIC_URL}/plugins/Jcrop/0.9.8/js/jquery-1.3.2-min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/Jcrop/0.9.8/js/jquery.Jcrop.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        //记得放在jQuery(window).load(...)内调用，否则Jcrop无法正确初始化
        $("#bigImage").Jcrop({
            onChange : showCoords,
            onSelect : showCoords
        });
        //简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
        function showCoords(obj) {
            $("#x1").val(obj.x);
            $("#y1").val(obj.y);
            $("#x2").val(obj.x2);
            $("#y2").val(obj.y2);
            $("#w").val(obj.w);
            $("#h").val(obj.h);
            
            if (parseInt(obj.w) > 0) {
                //计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
                var rx = $("#preview_box").width() / obj.w;
                var ry = $("#preview_box").height() / obj.h;
                //通过比例值控制图片的样式与显示
                $("#crop_preview").css({
                    width : Math.round(rx * $("#bigImage").width()) + "px", //预览图片宽度为计算比例值与原图片宽度的乘积
                    height : Math.round(rx * $("#bigImage").height()) + "px", //预览图片高度为计算比例值与原图片高度的乘积
                    marginLeft : "-" + Math.round(rx * obj.x) + "px",
                    marginTop : "-" + Math.round(ry * obj.y) + "px"
                });
            }
        }
        
        var img = document.getElementById("bigImage");
        img.onload = function() {
        
            var width = img.width;
            var height = img.height;
            var w = width / 1080;
            var h = height / 720;
            if (w > h) {
                $("#bigImage").attr("style","width:"+width/w+";height:"+height/w);
                $("#multiple").val(w);
            } else if (w < h) {
                $("#bigImage").attr("style","width:"+width/h+";height:"+height/h);
                $("#multiple").val(h);
            } else {
                $("#bigImage").attr("style","width:"+width/w+";height:"+height/w);
                $("#multiple").val(w);
            }
// 	        alert(img.width);
// 	        alert(img.height);
        }
//         var i1 = $("#bigImage");
//         var img = document.getElementById("bigImage");
//         var img = new Image;
//         alert(i1);
//         alert(i2);
//         alert(img);
//         img.onload = function() {
        
//             var width = img.width;
//             var height = img.height;
//             var w = width / 1080;
//             var h = height / 720;
//             if (w > h) {
//                 $("#bigImage").attr("style","width:"+width/w+";height:"+height/w);
//             } else if (w < h) {
//                 $("#bigImage").attr("style","width:"+width/h+";height:"+height/h);
//             } else {
//                 $("#bigImage").attr("style","width:"+width/w+";height:"+height/w);
//             }
        
//             alert(img.width);
//             alert(img.height);
//         };
//         img.src = $("#bigImage").attr("src");
	});
</script>
</head>
<body>
    <div class="img_pos">
        <form action="${BASE_URL}/back/imageCut/cutAndSave">
	        <div><img id="bigImage" src="${STATIC_URL}/back/images/test.jpg" /> <br /></div>
	        <input id="imageUrl" name="imageUrl" type="hidden" value="${STATIC_URL}/back/images/test.jpg" />
	        <input type="hidden" id="multiple" name="multiple" value="" />
	        <span id="preview_box" style="border-color: red;" class="crop_preview"><img id="crop_preview" style="width: 120px;height: 90px;" src="${STATIC_URL}/back/images/test.jpg" /> </span>
            <b>x1</b><input type="text" size="4" id="x1" name="x1" />
            <b class="ml5">y1</b><input type="text" size="4" id="y1" name="y1" />
            <b class="ml5">x2</b><input type="text" size="4" id="x2" name="x2" />
            <b class="ml5">y2</b><input type="text" size="4" id="y2" name="y2" />
            <b class="ml5">w</b><input type="text" size="4" id="w" name="width" />
            <b class="ml5">h</b><input type="text" size="4" id="h" name="height" />
            <input type="submit" value="提交" />
        </form>
    </div>
</body>
</html>
